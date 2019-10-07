import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int N = in.nextInt();
		final int M = in.nextInt();
		int[][] arr = new int[N][M];
		for(int i = 0; i < N ;i++) {
			for(int j = 0; j < M; j++) {
				arr[i][j] = in.nextInt();
			}
		}
		Solution sol = new Solution(N,M,arr);
		int answer = sol.work();
		System.out.println(answer);
	}
}
class Solution{
	final int N;
	final int M;
	final int[][] arr;
	int[][] d;
	ArrayList<Node> list;
	public Solution(final int N, final int M, final int [][] arr) {
		this.N = N;
		this.M = M;
		this.arr = arr;
		list = new ArrayList<Node>();
		this.d = new int[N][M];
		for(int i =0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				list.add(new Node(i,j,arr[i][j]));
			}
		}
		Collections.sort(list);
	}
	public int work() {
		d[0][0] = 1;
		int head = -1;
		while(head < list.size()) {
			head++;
			Node cur = list.get(head); 
//			System.out.println(cur);
			if(cur.v >= arr[0][0]) continue;
			d[cur.x][cur.y] = 0;
			for(int i = 0; i < 4; i++) {
				int nX = cur.x + dirX[i];
				int nY = cur.y + dirY[i];
				if(nX < 0 || nY < 0 || nX >= N || nY >= M) continue;
				if(arr[nX][nY] <= arr[cur.x][cur.y]) continue;
				d[cur.x][cur.y] += d[nX][nY];
			}
			if(cur.x == N-1 && cur.y == M - 1) break;

//			for(int i =0; i < N; i++) {
//				for(int j = 0; j < M; j++) {
//					System.out.print(d[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		return d[N-1][M-1];
	}

	static int[] dirX = new int[] {-1,0,0,1};
	static int[] dirY = new int[] {0,-1,1,0};
}
class Node implements Comparable<Node>{
	int x;
	int y;
	int v;
	public Node(int x, int y, int v) {
		this.x = x;
		this.y = y;
		this.v = v;
	}
	@Override
	public int compareTo(Node o) {
		return o.v - v;
	}
	@Override
	public String toString() {
		return "("+x+","+y+":"+v+") ";
		
	}
}
