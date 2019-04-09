import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//start 14:51
//end 	16:41

class Dice{
	private int left;
	private int right;
	private int up;
	private int down;
	int top;
	private int bottom;
	
	public Dice() {
		left = 0;
		right = 0;
		up = 0;
		down = 0;
		top = 0;
		bottom = 0;
	}

	public Dice(int left, int right, int up, int down, int top, int bottom) {
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.top = top;
		this.bottom = bottom;
	}

	public void moveRight() {
		int tmp = bottom;
		bottom = right;
		right = top;
		top = left;
		left = tmp;
	}
	public void moveLeft() {
		int tmp = bottom;
		bottom = left;
		left = top;
		top = right;
		right = tmp;
	}
	public void moveUp() {
		int tmp = bottom;
		bottom = up;
		up = top;
		top = down;
		down = tmp;
	}
	public void moveDown() {
		int tmp = bottom;
		bottom = down;
		down = top;
		top = up;
		up = tmp;
	}
	
	public void move(int dir) {
		switch(dir) {
		case 0:
			moveRight();
			break;
		case 1:
			moveLeft();
			break;
		case 2:
			moveUp();
			break;
		case 3:
			moveDown();
			break;
		}
	}
	
	public void trasmit(int[][] arr ,int x,int y) {
		if (arr[x][y] == 0) {
			arr[x][y] = bottom;
			return;
		}
		bottom = arr[x][y];
		arr[x][y] = 0;
	}
	@Override
	public String toString() {
		String line1 = "(\t\t"+ up +"\n";
		String line2 = "\t"+ left +"\t"+ top+  "\t"+ right+")\n";
		String line3 = "\t\t"+ down + ", bottom: "+ bottom+ ")\n";
		
		return line1 + line2 + line3;
//		return top+"";
	}
	public Dice copy() {
		return new Dice(left, right, up, down, top, bottom);
	}
}

class Node{
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public boolean move(int dir) {

		int nX = x + dirX[dir];
		int nY = y + dirY[dir];
		if((nX < 0 || nX >=Main.n || nY < 0 || nY >= Main.m)) {
			return false;
		}
		x = nX;
		y = nY;
		return true;
	}
	
	static final int[] dirX = new int[] {0, 0, -1, 1};
	static final int[] dirY = new int[] {1, -1, 0, 0};
}

class Manager{
	ArrayList<Dice> answer; 
	int[][] arr;
	Node cur;
	Dice dice;
	Integer ttt = null;
	ArrayList<Node> list;
	public Manager(int[][] arr, Node cur){
		this.arr = arr;
		this.cur = cur;
		dice = new Dice();
		answer = new ArrayList<Dice>();
	}
	public void move(int dir) {
		if(cur.move(dir) == false)
			return;
		
		dice.move(dir);
		dice.trasmit(arr, cur.x, cur.y);
		
		System.out.println(dice.top);
		answer.add(dice.copy());
	}
}
public class Main {

	static int n, m;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try { in = new Scanner(new File("input.txt")); }catch(Exception e) { e.printStackTrace(); }
		
		n = in.nextInt();
		m = in.nextInt();
		int sX = in.nextInt();
		int sY = in.nextInt();
		int k = in.nextInt();
		
		int i, j;

		int[][] arr = new int[n][m];
		for (i = 0; i < n; i ++) {
			for (j = 0; j < m; j ++) {
				arr[i][j] = in.nextInt();
			}
		}
		
		Node cur = new Node(sX, sY);
		
		Manager mng = new Manager(arr, cur);
		
		
		for (i = 0; i < k; i ++) {
			int dir = in.nextInt() - 1;
			mng.move(dir);
		}
//		System.out.println(mng.answer);
	}
}
