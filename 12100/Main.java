import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

enum Result {unable, able};

class Pan{
	
	int[][] arr;
	int move;

	public Pan(int[][] arr) {
		this(arr, 0);
	}
	public Pan(int[][] arr, int move) {
		this.arr = arr;
		this.move = move;
	}
	
	public Pan copy() {
		int[][] newArr = new int[Main.n][Main.n];
		for(int i = 0 ; i<Main.n; i ++) {
			for(int j = 0 ; j<Main.n; j ++) {
				newArr[i][j] = arr[i][j];
			}
		}
		return new Pan(newArr, move);
	}
	public boolean isSame(Pan _pan) {
		for(int i = 0 ; i<Main.n; i ++) {
			for(int j = 0 ; j<Main.n; j ++) {
				if(arr[i][j] != _pan.arr[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Result moveLeft() {
		Result result = Result.unable;

		int i, j;
		int cnt = 0;

		int[][] merged= new int[Main.n][Main.n];

		for (i = 0; i < Main.n; i++) {
			for (j = 1; j < Main.n; j++) {
				merged[i][j]=0;
			}
		}
		
		for (i = 0; i < Main.n; i++) {
			for (j = 1; j < Main.n; j++) {
				cnt += moveCommon(i, j, 0, -1, merged) ? 1 : 0;
			}	
		}
		if(cnt > 0) result = Result.able;
		if(result == Result.able) {
			move ++;
		}
		return result;
	}

	public Result moveRight() {
		Result result = Result.unable;

		int i, j;
		int cnt = 0;
		int[][] merged= new int[Main.n][Main.n];

		for (i = 0; i < Main.n; i++) {
			for (j = 1; j < Main.n; j++) {
				merged[i][j]=0;
			}
		}
		
		
		for (i = 0; i < Main.n; i++) {
			for (j = Main.n - 2; j >= 0; j--) {
				cnt += moveCommon(i, j, 0, 1,merged) ? 1 : 0;
			}	
		}
		if(cnt > 0) result = Result.able;
		if(result == Result.able) {
			move ++;
		}
		return result;
	}

	public Result moveUp() {
		Result result = Result.unable;

		int i, j;
		int cnt = 0;
		
		int[][] merged= new int[Main.n][Main.n];

		for (i = 0; i < Main.n; i++) {
			for (j = 1; j < Main.n; j++) {
				merged[i][j]=0;
			}
		}
		
		
		for (i = 1; i < Main.n; i++) {
			for (j = 0; j < Main.n; j++) {
				cnt += moveCommon(i, j, -1, 0,merged) ? 1 : 0;
			}	
		}
		if(cnt > 0) result = Result.able;
		if(result == Result.able) {
			move ++;
		}
		return result;
	}

	public Result moveDown() {
		Result result = Result.unable;

		int i, j;
		int cnt = 0;
		
		int[][] merged= new int[Main.n][Main.n];

		for (i = 0; i < Main.n; i++) {
			for (j = 1; j < Main.n; j++) {
				merged[i][j]=0;
			}
		}
		
		
		for (i = Main.n - 2; i >= 0; i--) {
			for (j = 0; j < Main.n; j++) {
				cnt += moveCommon(i, j, 1, 0, merged) ? 1 : 0;
			}	
		}
		if(cnt > 0) result = Result.able;
		if(result == Result.able) {
			move ++;
		}
		return result;
	}
	
	
	public boolean moveCommon(int i,int j, int di, int dj, int[][] merged) {
		if(arr[i][j] == 0) return false;
		if(merged[i+di][j + dj] == 1) {
			return false;
		}
		if(arr[i][j] == arr[i + di][j + dj]) {
			arr[i + di][j + dj] *= 2;
			merged[i + di][j + dj] = 1;
			answer = answer > arr[i + di][j + dj] ? answer : arr[i + di][j + dj];
			arr[i][j] = 0;
			return true;
		}
		if(arr[i + di][j + dj] == 0) {
			arr[i + di][j + dj] = arr[i][j];
			arr[i][j] = 0;
			if(i + di*2 >=0 && i + di*2 < Main.n && j + dj * 2 >=0 && j + dj * 2 < Main.n)
				moveCommon(i + di, j + dj, di, dj, merged);
			return true;
		}
		return false;
	}
	public Result move(int dir) {
		switch(dir) {
		case LEFT:
			return moveLeft();
		case RIGHT:
			return moveRight();
		case UP:
			return moveUp();
		case DOWN:
			return moveDown();
		}
		return Result.unable;
	}
	
	public int max() {
		int max = 0;
		for(int i = 0; i < Main.n; i ++) {
			for(int j = 0; j < Main.n; j ++) {
				max = max > arr[i][j]  ? max : arr[i][j];
			}
		}
		return max;
	}
	
	@Override
	public String toString() {
		String str = "move is " + move + "\n";
		
		for(int i = 0; i < Main.n; i ++) {
			for(int j = 0; j < Main.n; j ++) {
				str += arr[i][j] + " ";
			}
			str += "\n";
			
		}
		return str; 
	}

	public static int answer = 0;
	
	static final int LEFT = 0;
	static final int RIGHT = 1;
	static final int UP = 2;
	static final int DOWN = 3;
}
public class Main {

	public static int n;

//	static HashMap<Pan, Pan> parent = new HashMap<Pan, Pan>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
//		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		
		n = in.nextInt();
		int i, j;
		
		int answer = 0;

		int[][] origin = new int[n][n]; 
		for (i = 0; i < n; i++) {
			for(j = 0; j< n; j++) {
				origin[i][j] = in.nextInt();
				answer = answer > origin[i][j] ? answer : origin[i][j];
			}
		}

		Pan.answer = answer;
		
		Pan start = new Pan(origin);
		
		LinkedList<Pan> list = new LinkedList<Pan>();
		Queue<Pan> queue =  new LinkedList<Pan>();
		queue.offer(start);
		list.add(start);
		
//		String[] sss =new String[]{"left", "right", "up", "down"};
//		int debug = 2;
		
		while(!queue.isEmpty()) {
			Pan cur = queue.poll();
//			if(cur.move == debug)
//				System.out.println(cur);
			if(cur.move == 5) continue;
			for(i = 0; i < 4; i++) {
				Pan tmp = cur.copy();
				if(tmp.move(i) == Result.able) {
					if(tmp.isSame(cur))
						continue;
					boolean tf = false;
					for(Pan p : list) {
						if(tmp.isSame(p)) {
							tf = true;
							break;
						}
					}
					if(tf) continue;
					
//					if(cur.move == debug) {
//						System.out.println("able " + sss[i]);
//						System.out.println(tmp);
//					}
					
					queue.offer(tmp);
					list.add(tmp);
//					parent.put(tmp, cur);
//					if(Pan.answer == 64 && tmp.max() == 64) {
//						Pan ppp = tmp;
//
//						System.out.println(ppp);
//						
//						while(Main.parent.containsKey(ppp)) {
//							ppp = Main.parent.get(ppp);
//							System.out.println(ppp);
//						}
//					}
				}
			}	
		}
		answer = answer > Pan.answer ? answer : Pan.answer;
		System.out.println(answer);
	}
}
