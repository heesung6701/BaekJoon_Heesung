import java.io.File;
import java.util.Scanner;

// date : 2019.04.10
// start 	11:45
// end  	12:30
class Shape{
	int[] dirs = new int[3];
	private int rotate;
	public Shape(int type) {
		rotate = -1;
		copyArr(dirs, shape[type]);
	}
	public void copyArr(int[] to,int[] from) {
		for(int i = 0 ; i < to.length; i ++) 
			to[i] = from[i];
	}
	public boolean rotate() {
		rotate ++;
		if(rotate == 4) return false;
		for(int i  = 0; i < 3; i ++) {
			dirs[i] = next[dirs[i]];
		}
		return true;
	}
	
	private static int[] next = new int[] { 0, 3, 1, 4, 2};
	private static int[] shape1 = new int[] {1,1,1};
	private static int[] shape2 = new int[] {3,4,2};
	private static int[] shape3 = new int[] {4,4,3};
	private static int[] shape4 = new int[] {4,4,2};
	private static int[] shape5 = new int[] {4,3,4};
	private static int[] shape6 = new int[] {4,2,4};
	
	public static int[][] shape = new int[][] {
			shape1,
			shape2,
			shape3,
			shape4,
			shape5,
			shape6
	};	
	//shape T는 예외처리
}
class DFS{
	int n;
	int m;
	int[][] arr;
	int answer;
	public DFS(int[][] arr, int n, int m) {
		this.arr = arr;
		this.n = n;
		this.m = m;
	}
	public void search() {
		int i, j, k;
		
		for(i = 0; i <n; i ++) {
			for(j = 0; j < m; j++) {
				for(int type =0; type <Shape.shape.length; type ++) { 
					int value = checkAble(i,j, type); 
					if(value == -1) continue;
					answer = answer > value ? answer : value;
				}
				int valueT = checkT(i, j);
				if(valueT == -1) continue;
				answer = answer > valueT ? answer : valueT; 
			}
		}
	}
	public int checkAble(int x, int y, int type) {
		int max = 0;
		Shape shape = new Shape(type);
		while(shape.rotate()) {
			int sum = arr[x][y];
			int nX = x;
			int nY = y;
			for(int pivot = 0; pivot < 3; pivot ++) {
				int head = shape.dirs[pivot];
				try {
					nX = nX + dirX[head];
					nY = nY + dirY[head];
					sum += arr[nX][nY];
				}catch(IndexOutOfBoundsException e) {
					sum =- 1;
					break;
				}
			}
			if(sum ==-1) continue;
			max = max > sum ? max : sum;
		}
		return max;
	}
	public int checkT(int x,int y) {
		int left= -1;
		int right = -1;
		int top = -1;
		int bottom = -1;
		if(y != 0) 
			left = arr[x][y-1];
		if(y != m-1) 
			right = arr[x][y+1];
		if(x != 0) 
			top = arr[x-1][y];
		if(x != n-1) 
			bottom = arr[x+1][y];
		int unable = 0;
		if(left ==-1) unable ++;
		if(right ==-1) unable ++;
		if(top ==-1) unable ++;
		if(bottom ==-1) unable ++;
		
		if(unable > 1) return -1;

		int data = left + right + top + bottom + arr[x][y];
		if(unable == 1) {
			return data + 1;
		}
		int min = left;
		min = min>right ? right : min;
		min = min>top ? top : min;
		min = min>bottom ? bottom : min;
		return data - min;
	}
	
	final static int[] dirX = new int[]{0, 0, -1, 1, 0};
	final static int[] dirY = new int[]{0, -1, 0, 0, 1};
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		int[][] arr = new int[n][m];
		for(int i = 0 ; i < n; i++) {
			for (int j = 0; j < m; j ++) {
				arr[i][j] = in.nextInt();
			}
		}
		DFS dfs = new DFS(arr, n, m);
		dfs.search();
		System.out.println(dfs.answer);
	}

}
