import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[][][] d;

	static int inputDir[] = {-1, 2, 0, 1, 3}; 
	static int dirX[] = {0,1,0,-1};
	static int dirY[] = {-1,0,1,0};
	static int dirSize = 4;
	
	public static int get(Node obj) {
		return d[obj.x][obj.y][obj.dir];
	}
	public static boolean set(Node obj, int v) {
		if(d[obj.x][obj.y][obj.dir] == 0 || d[obj.x][obj.y][obj.dir] > v) {
			d[obj.x][obj.y][obj.dir] = v;	
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try { in = new Scanner(new File("input.txt")); }catch(Exception e) { e.printStackTrace(); }
		
		N = in.nextInt();
		M = in.nextInt();
		map = new int[N][M];
		d = new int[N][M][4];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = in.nextInt();
			}
		}	
		int sx,sy,sz;
		sx = in.nextInt() - 1;
		sy = in.nextInt() - 1;
		sz = inputDir[in.nextInt()];
		Node start = new Node(sx,sy,sz);

		int ex,ey,ez;
		ex = in.nextInt() - 1;
		ey = in.nextInt() - 1;
		ez = inputDir[in.nextInt()];
		Node end = new Node(ex,ey,ez);
		Queue<Node> q = new LinkedList<Node>();
		q.offer(start);
		set(start,1);
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int nX = cur.x + dirX[cur.dir];
			int nY = cur.y + dirY[cur.dir];
			for(int ccc = 1; ccc <=3; ccc++) {
				if(nX < 0 || nX >= N || nY < 0 || nY >= M) break;
				if(map[nX][nY] == 1) break;
				Node newNode = new Node(nX, nY, cur.dir);
				if(set(newNode, get(cur) + 1)) {
					q.offer(newNode);
				}
				nX += dirX[cur.dir];
				nY += dirY[cur.dir];
			}
			for(int r = 1; r <= 3; r++) {
				int move = r + (r == 3 ? -2 : 0);
				int newDir = r + cur.dir;
				if(newDir > 3) newDir -= 4;
				Node newNode = new Node(cur.x, cur.y, newDir);
				if(set(newNode, get(cur) + move)) {
					q.offer(newNode);	
				}
			}
		}
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				int min = d[i][j][0];
//				for(int k = 1; k < 4; k++) { min = min > d[i][j][k] ? d[i][j][k] : min; }				
//				System.out.print((min-1 >= 0 ? min -1 : 0)+" ");
////				System.out.print(d[i][j][2]+" ");
//			}
//			System.out.println();
//		}
		if(get(end) == 0) throw new IllegalArgumentException("unable!");
		System.out.println(get(end) - 1);
	}
}
class Node{
	int x, y, dir;
	public Node(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}
