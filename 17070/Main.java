import java.io.File;
import java.util.Scanner;


//start 08:40
class Solution{
	int n;
	int[][] arr;
	int answer = 0;
	public Solution(int n, int[][] arr) {
		this.n = n;
		this.arr = arr;
		search(0,1, RIGHT);
	}
	
	public void search(int x,int y, int head) {
//		System.out.println("("+x+","+y+") " +opt[head]);
		if(x == n-1 && y == n-1) {
			answer ++;
			return;
		}
		boolean[] able = new boolean[3];
		able[RIGHT]=able[DOWN]=able[DIAGONAL] = true;
		if(head == RIGHT) able[DOWN] = false;
		if(head == DOWN) able[RIGHT] = false;
		
		int cnt = 0;
		for(int dir = 0; dir <dirX.length; dir++) {
			int nX = x + dirX[dir];
			int nY = y + dirY[dir];
			if(nX < 0 || nY < 0 || nX >=n || nY>=n) continue;
			if(arr[nX][nY] == 1) continue;
			cnt ++;
			if(dir == DIAGONAL) {
				if(cnt == 3) search(nX, nY, DIAGONAL);
				break;
			}
			if(able[dir])
				search(nX, nY, dir);
		}
	}
	final static int[] dirX = {1, 0, 1};
	final static int[] dirY = {0, 1, 1};
	final static int DOWN = 0;
	final static int RIGHT = 1;
	final static int DIAGONAL = 2;
	final static String[] opt = new String[] { "¡é","¡æ","¢Ù"};
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		try { in = new Scanner(new File("input.txt")); } catch(Exception e ) { e.printStackTrace(); }
		
		int n = in.nextInt();
		int[][] arr =new int[n][n];
		int i, j;
		for(i = 0; i < n; i ++) {
			for(j = 0; j < n; j++) {
				arr[i][j] = in.nextInt();
			}
		}
		
		Solution sol = new Solution(n, arr);
		System.out.println(sol.answer);

	}
}
