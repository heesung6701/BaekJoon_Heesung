import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//start 20:35
class Face{
	char[][] arr;
	Face left;
	Face right;
	Face up;
	Face down;
	public Face(char init) {
		this.arr = new char[3][3];
		for (int i = 0; i <3; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] =  init; //(char) ('0'+(i*3+j));
			}
		}
	}

	@Override 
	public String toString() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j ++) {
				str.append(arr[i][j]);
			}
			if(i != 2) str.append("\n");
		}
		return str.toString();
	}

	public void rotateR() {

		int i, j;
		char[][] tmp = moveArr(arr);

		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j ++) {
				arr[j][3 - 1 -i] = tmp[i][j]; 
			}
		}		
	}
	public void rotateL() {

		int i, j;
		char[][] tmp = moveArr(arr);

		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j ++) {
				arr[3 - 1 -j][i] = tmp[i][j]; 
			}
		}		
	}
	public char[][] moveArr(char[][] arr){
		char[][] tmp = new char[3][3];

		int i, j;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j ++) {
				tmp[i][j] = arr[i][j];
				arr[i][j] = 0;
			}
		}
		return tmp;
	}
}
class Node{
	int i;
	int j;
	boolean inverse;
	public Node(int i, int j) {
		this.i = i;
		this.j = j;
		inverse = false;
	}
	public Node(int i, int j, boolean inverse) {
		this.i = i;
		this.j = j;
		this.inverse = inverse;
	}

}
class Cube{
	Face top;
	Face bottom;
	Face left;
	Face right;
	Face front;
	Face back;
	public Cube() {
		//À­ ¸éÀº Èò»ö, ¾Æ·§ ¸éÀº ³ë¶õ»ö, ¾Õ ¸éÀº »¡°£»ö, µÞ ¸éÀº ¿À·»Áö»ö, ¿ÞÂÊ ¸éÀº ÃÊ·Ï»ö, ¿À¸¥ÂÊ ¸éÀº ÆÄ¶õ»ö
		top = new Face('w');
		bottom = new Face('y');
		front = new Face('r');
		back = new Face('o');
		left = new Face('g');
		right = new Face('b');	

		top.left = left;
		left.left = bottom;
		bottom.left = right;
		right.left = top;

		top.right = right;
		right.right = bottom;
		bottom.right = left;
		left.right = top;

		top.down = front;
		front.down = bottom;
		bottom.down = back;
		back.down = top;

		top.up = back;
		back.up = bottom;
		bottom.up = front;		
		front.up = top;	
	}
	public void rotate(char what, int dir) {
		// U: À­ ¸é, D: ¾Æ·§ ¸é, F: ¾Õ ¸é, B: µÞ ¸é, L: ¿ÞÂÊ ¸é, R: ¿À¸¥ÂÊ ¸éÀÌ´Ù

		ArrayList<Face> list = new ArrayList<>();
		ArrayList<Node> fix = new ArrayList<>();
		Face cur;
		switch(what) {

		case 'D':
			if(dir == 1) {
				list.add(left);
				fix.add(new Node(-1, 0));
				list.add(front);
				fix.add(new Node(2, -1));
				list.add(right);
				fix.add(new Node(-1, 2, true));
				list.add(back);
				fix.add(new Node(0, -1, true));
				bottom.rotateR();
				list.add(left);
				fix.add(new Node(-1, 0));
			}else {
				list.add(left);
				fix.add(new Node(-1, 0, true));
				list.add(back);
				fix.add(new Node(0, -1));
				list.add(right);
				fix.add(new Node(-1, 2));
				list.add(front);
				fix.add(new Node(2, -1, true));
				bottom.rotateL();
				list.add(left);
				fix.add(new Node(-1, 0, true));
			}
			break;
		case 'U':
			if(dir == 1) {
				list.add(left);
				fix.add(new Node(-1, 2, true));
				list.add(back);
				fix.add(new Node(2, -1));
				list.add(right);
				fix.add(new Node(-1, 0));
				list.add(front);
				fix.add(new Node(0, -1, true));
				top.rotateR();
				list.add(left);
				fix.add(new Node(-1, 2, true));
			}else {
				list.add(left);
				fix.add(new Node(-1, 2));
				list.add(front);
				fix.add(new Node(0, -1));
				list.add(right);
				fix.add(new Node(-1, 0, true));
				list.add(back);
				fix.add(new Node(2, -1, true));
				top.rotateL();
				list.add(left);
				fix.add(new Node(-1, 2));
			}
			break;
		case 'F':
			cur = top;
			list.add(top);
			fix.add(new Node(2, -1));
			while(true) {
				Face next = dir == 1 ? cur.right : cur.left;
				list.add(next);
				fix.add(new Node(2, -1));
				if(next == top) break;
				cur = next;
			}
			if(dir == 1) front.rotateR();
			else front.rotateL();
			break;
		case 'B':
			cur = top;
			list.add(top);
			fix.add(new Node(0, -1));
			while(true) {
				Face next = dir == 1 ? cur.left : cur.right;
				list.add(next);
				fix.add(new Node(0, -1));
				if(next == top) break;
				cur = next;
			}
			if(dir == 1) back.rotateR();
			else back.rotateL();
			break;
		case 'L':
			cur = top;
			list.add(top);
			fix.add(new Node(-1, 0));
			while(true) {
				Face next = dir == 1 ? cur.down : cur.up;
				list.add(next);
				if(next == bottom) fix.add(new Node(-1, 2, true));
				else fix.add(new Node(-1, 0));
				if(next == top) break;
				cur = next;
			}
			if(dir == 1) left.rotateR();
			else left.rotateL();
			break;
		case 'R':
			cur = top;
			list.add(top);
			fix.add(new Node(-1, 2));
			while(true) {
				Face next = dir == 1 ? cur.up : cur.down;
				list.add(next);
				if(next == bottom) fix.add(new Node(-1, 0, true));
				else fix.add(new Node(-1, 2));
				if(next == top) break;
				cur = next;
			}
			if(dir == 1) right.rotateR();
			else right.rotateL();
			break;
		}

		char[] mem = new char[3];
		char[] buf = new char[3];

		saveBuffer(fix.get(0), buf, list.get(0));
		int k, l;
		for(k = 0; k < list.size() - 1; k++) {
			cur = list.get(k);
			Face next = list.get(k + 1);

			saveBuffer(fix.get(k+1), mem, next);

			int nextI = fix.get(k + 1).i;
			int nextJ = fix.get(k + 1).j;

			for(l = 0; l < 3; l++) {
				int i = nextI == -1 ? l : nextI;
				int j = nextJ == -1 ? l : nextJ;
				int trans = l;
				if(fix.get(k).inverse ^ fix.get(k+1).inverse) {
					trans = 2-l;
				}
				next.arr[i][j] = buf[trans];
			}
			for(l = 0; l < 3; l++) {
				buf[l] = mem[l];
				mem[l] = 0;
			}
			if(next == list.get(0)) break;
		}
	}
	public void saveBuffer(Node fix, char[] buf, Face cur) {

		int fI = fix.i;
		int fJ = fix.j;

		for(int l = 0; l < 3; l++) {
			int i = fI == -1 ? l : fI;
			int j = fJ == -1 ? l : fJ;
			buf[l] = cur.arr[i][j];
		}
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < 3; i++) {
			str.append("\t");
			for (int j = 0; j < 3; j ++) {
				str.append(back.arr[i][j]);
			}
			str.append("\n");
		}
		str.append("\n");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j ++) {
				str.append(left.arr[i][j]);
			}
			str.append('\t');
			for (int j = 0; j < 3; j ++) {
				str.append(top.arr[i][j]);
			}

			str.append('\t');
			for (int j = 0; j < 3; j ++) {
				str.append(right.arr[i][j]);
			}
			str.append('\t');
			for (int j = 0; j < 3; j ++) {
				str.append(bottom.arr[i][j]);
			}
			str.append("\n");
		}

		str.append("\n");
		for (int i = 0; i < 3; i++) {
			str.append("\t");
			for (int j = 0; j < 3; j ++) {
				str.append(front.arr[i][j]);
			}
			str.append("\n");
		}
		
		
		return str.toString();
	}

}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

//		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }

		int i ,j;
		int test_cases = in.nextInt();
		for (int _case = 0; _case < test_cases; _case++) {

			Cube cube = new Cube();

			int n = in.nextInt();
			for (i = 0; i<n; i++) {
				String input = in.next();
				char what = input.charAt(0);
				int dir = input.charAt(1) == '+' ? 1 : -1;
				cube.rotate(what, dir);

//				System.out.println(cube.toString());
			}
			System.out.println(cube.top.toString());
			
		}
	}

}
