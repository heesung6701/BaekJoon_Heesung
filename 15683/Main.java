import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//start 15:27
//end 16:15

class Node{
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class CCTV extends Node{
	int type; //type 1, 2, 3, 4, 5
	int rotate;
	
	public CCTV(int x, int y, int type) {
		super(x, y);
		this.type = type;
		rotate = 0;
	}
	public boolean rotate() {
		if(rotate == rotateTable[type].length - 1) return false;
		rotate++;
		return true;
	}
	@Override
	public String toString() {
		return "("+x +", " + y +"): type ->"+ type +" rotate is " + rotate; 
		
	}
	
	static int[][] rotateTable = new int[][] {
		{}, 
		{1, 4, 8, 2},
		{9, 6}, 
		{5, 12, 10, 3}, 
		{7, 13, 14, 11},
		{15}
		};
}
class DFS {
	int n;
	int m;
	int[][] arr;
	ArrayList<CCTV> cctvs;
	
	int answer;
	public DFS(int n, int m, int[][] arr, ArrayList<CCTV> cctvs, int blank) {
		this.n = n;
		this.m = m;
		this.arr = arr;
		this.cctvs = cctvs;
		answer = blank;
	}
	
	public void search(int level) {
		if(level == cctvs.size()) {
			int v = check();
			answer = answer > v ? v : answer;
			return;
		}
		search(level + 1);
		while(cctvs.get(level).rotate()) {
			search(level + 1);
		}
		cctvs.get(level).rotate = 0;
	} 
	public int check() {
		int[][] c = copyArr(arr);
		for(CCTV cctv : cctvs) {
			int move = 0;
			
			int dir = CCTV.rotateTable[cctv.type][cctv.rotate];
			while(dir != 0) {
				move ++;
				
				int ddd = 1;
				for(int d = 0; d < 4;  d++) {
					if ((dir & ddd) > 0) {
						int nX = cctv.x + dirX[d] * move;
						int nY = cctv.y + dirY[d] * move;
						if(nX < 0 || nX >= n || nY < 0 || nY >= m) {
							dir -= ddd;
							ddd = ddd << 1;
							continue;
						}
						switch(c[nX][nY]) {
						case 0:
							c[nX][nY] = VIEWED;
							break;
						case 6:
							dir -= ddd;
							break;
						default:
							break;
						}
					}
					ddd = ddd << 1;
				}
			}
		}
		int check = 0;
		for(int i = 0; i < n; i ++) {
			for (int j = 0; j < m; j++) {
				if(c[i][j] == 0) {
					check ++;
				}
			}
		}
		
		return check;
	}

	public void showArr(int[][] arr){
		int i, j;
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j ++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public int[][] copyArr(int[][] arr){
		int[][] c = new int[n][m];
		int i, j;
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j ++) {
				c[i][j] = arr[i][j];
			}
		}
		return c;
	}
	
	final static int[] dirX = new int[] {-1, 0, 0, 1};
	final static int[] dirY = new int[] {0, -1, 1, 0};
	
	final static int VIEWED = 7;
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		
		int n = in.nextInt();
		int m = in.nextInt();
		int i, j;
		int[][] arr = new int[n][m];
		
		
		ArrayList<CCTV> list = new ArrayList<CCTV>();
		int blank = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				arr[i][j] = in.nextInt();
				if (arr[i][j] == 0) {
					blank ++;
					continue;
				}
				if (arr[i][j] == 6) continue;
				list.add(new CCTV(i, j, arr[i][j]));
			}
		}
		
		DFS dfs = new DFS(n, m, arr, list, blank);
		dfs.search(0);
		
		System.out.println(dfs.answer);
	}

}
