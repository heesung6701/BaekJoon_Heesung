import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//start 14:50
//end 	16:12
class Node{
	int x;
	int y;
	public Node(int x ,int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
}
class Solution{
	int n;
	int m;
	int[][] map;
	int[][] fromStart;
	int[][] fromEnd;
	
	ArrayList<Node> walls;
	int answer = -1;
	public Solution(int n, int m, int[][] map, ArrayList<Node> walls) {
		this.n = n;
		this.m = m;
		this.map = map;
		this.walls = walls;
		fromStart = new int[n][m];
		fromEnd = new int[n][m];
		search(fromStart, 0,0);
		search(fromEnd, n-1,m-1);

//		printArr(fromStart);
//		System.out.println("fromEnd");
//		printArr(fromEnd);
		
		if(fromStart[n-1][m-1] != 0)
			answer = fromStart[n-1][m-1];
	}
	
	public void search(int[][] arr, int sX,int sY) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(sX, sY));
		arr[sX][sY] = 1;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			for(int dir = 0; dir < dirX.length; dir++) {
				int nX = cur.x + dirX[dir];
				int nY = cur.y + dirY[dir];
				if(nX < 0 || nX >= n || nY < 0 || nY >=m) continue;
				if(map[nX][nY] != 0) continue;
				if(arr[nX][nY] != 0) continue;
				arr[nX][nY] = arr[cur.x][cur.y]+1;
				queue.add(new Node(nX, nY));
			}
		}
	}
	public void breakWAll() {
		for(Node wall : walls) {
//			System.out.println("Wall is "+ wall);
			for(int p = 0; p< dirX.length; p++) {
				int x1 = wall.x + dirX[p];
				int y1 = wall.y + dirY[p];
				if(x1 < 0 || x1 >= n || y1 < 0 || y1 >=m) continue;
				if(fromStart[x1][y1] == 0) continue;
				int pivot = fromStart[x1][y1];
//				System.out.println("시작지로부터 "+ dirName[p] + " " + pivot);
				
				
				int min = -1;
				for(int dir = 0; dir < dirX.length; dir++) {

					int x2 = wall.x + dirX[dir];
					int y2 = wall.y + dirY[dir];
					if(x2 < 0 || x2 >= n || y2 < 0 || y2 >=m) continue;
					if(fromEnd[x2][y2] == 0) continue;
//					System.out.println("도착지로 부터 "+ dirName[dir] + " " + fromEnd[x2][y2]);
					min = min == -1 || min > fromEnd[x2][y2] ? fromEnd[x2][y2] : min;
				}
				if(min == -1) continue;
				answer = answer == -1 || answer > pivot + min + 1 ? pivot + min + 1 : answer;
			}
		}
	}
	public void printArr(int[][] arr) {
		for(int i = 0; i<n; i++) {
			for(int j = 0; j < m; j++) {
				System.out.printf("%4d" , arr[i][j]);
			}
			System.out.println();
		}
		
	}
	
	final static int[] dirX = new int[] {-1,0,0,1};
	final static int[] dirY = new int[] {0,-1,1,0};
	final static String[] dirName = new String[] {"위쪽","왼쪽","오른쪽","아래"};
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] arr = new int[n][m];
		ArrayList<Node> list = new ArrayList<>();
		for(int i = 0 ; i < n; i ++) {
			String line = in.next();
			for(int j = 0 ; j < line.length(); j++) {
				arr[i][j] = line.charAt(j) - '0';
				if(arr[i][j] == 1)
					list.add(new Node(i,j));
			}
		}
		Solution sol = new Solution(n, m, arr, list);
		sol.breakWAll();
		System.out.println(sol.answer);
		
	}

}
