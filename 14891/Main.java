import java.io.File;
import java.util.Scanner;


//start 15:48
//end 16:42 

class Gear{
	int x = 0;
	Gear left = null;
	Gear right = null;

	public Gear(int x) {
		this.x = x;
	}

	public void rotate(int dir) {
		rotate(dir, null);
	}
	public void rotate(int dir, Gear from) {
		int tmp;
		if (left != null && from != left &&  left.getRight() + getLeft() == 1) {
			left.rotate(dir * -1, this);
		}

		if (right != null && from != right && right.getLeft() + getRight() == 1) {
			right.rotate(dir * -1, this);
		}

		if (dir == DIR_CLOCK) {
			tmp = (x & 1) == 0 ? 0 : 1 << 7;
			x = (x >> 1);
			x = x | tmp;
		} else {
			tmp = (x & (1 << 7)) == 0 ? 0 : 1;
			x = (x << 1) & MASK;
			x = x | tmp;
		}
	}

	public int getLeft() {
		return (x & (1 << 1)) == 0 ? 0 : 1;
	}
	public int getRight() {
		return (x & (1 << 5)) == 0 ? 0 : 1;
	}
	public int getScore() {
		return (x & (1 << 7)) == 0 ? 0 : 1;
	}

	final static int DIR_CLOCK = 1;
	final static int DIR_REVERSE_CLOCK = -1;

	final static int N = 0;
	final static int S = 1;
	

	final static int MASK = 0xFF;

}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

//		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }

		int i, j;

		Gear[] gears = new Gear[4];
		
		for (i = 0; i < 4; i++) {
			String input = in.nextLine();
			int tmp = 1;
			
			int x = 0;
			for (j = input.length() - 1; j >= 0; j--) {
				if(input.charAt(j) == '1') {
					x += tmp;
				}
				tmp = tmp << 1;
			}
			gears[i] = new Gear(x); 
		}

		
		for (i = 0; i < 4; i++) {
			if(i != 0) {
				gears[i].left = gears[i-1];
				gears[i-1].right = gears[i];
			}
			if(i != 3) {
				gears[i].right = gears[i+1];
				gears[i+1].left = gears[i];
			}
		}

//		System.out.println("-----test 0-------");
//		for (i = 0; i < 4; i++) {
//			System.out.print(gears[i].x + " (");
//			System.out.print((gears[i].getLeft() == Gear.N ? "N" : "S"));
//			System.out.print("/");
//			System.out.print((gears[i].getRight() == Gear.N ? "N" : "S"));
//			System.out.print(") ");
//		}
//		System.out.println("--------------");
		int n = in.nextInt();
		for (int _case = 0; _case < n; _case++) {
			int target = in.nextInt() - 1;
			int dir = in.nextInt();
			gears[target].rotate(dir);
//			System.out.println("-----test "+ (i + 1)+ "-------");
//			for (i = 0; i < 4; i++) {
//				System.out.print(gears[i].x + " (");
//				System.out.print((gears[i].getLeft() == Gear.N ? "N" : "S"));
//				System.out.print("/");
//				System.out.print((gears[i].getRight() == Gear.N ? "N" : "S"));
//				System.out.print(") ");
//			}
//			System.out.println("--------------");
		}

		int score = 1;
		int answer = 0;
		for (i = 0; i < 4; i++) {
			if(gears[i].getScore() == Gear.S) {
				answer += score;
			}
			score = score << 1;
		}
		

		System.out.println(answer);
	}

}
