import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


class Node{
	int x, y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "("+x+ ", "+ y + ")";
	}
}
class DFS{
	ArrayList<Node> list;
	ArrayList<Node> virusList;
	int[][] arr;
	int n, m;
	
	ArrayList<Node> selected;
	
	int max = 0;
	public DFS(ArrayList<Node> list, ArrayList<Node> virusList, int[][] arr,int n, int m) {
		this.list = list;
		this.virusList = virusList;
		this.arr = arr;
		this.n = n;
		this.m = m;
		selected = new ArrayList<Node>();
	}

	public void search(int sIdx, int level) {
		if(level == 3) {
			int cnt = check();
			max = max > cnt ? max : cnt;
			return;
		}
		int i;
		for (i = sIdx; i < list.size(); i++) {
			Node cur = list.get(i);
			selected.add(cur);
			search(i + 1, level + 1);
			selected.remove(cur);
		}
		return;
	}
	public int check() {
		int[][] c = Main.copy(arr);

		for(Node o : selected) {
			c[o.x][o.y] = 1;
		}
			
		int i, j;


		Queue<Node> queue = new LinkedList<Node>();
		for(Node o : virusList) {
			queue.offer(o);	
		}
		while(queue.size() > 0) {
			Node cur = queue.poll();
			for(int dir = 0; dir < 4; dir ++) {
				int nI = cur.x + dirI[dir];
				int nJ = cur.y + dirJ[dir];
				if(nI < 0 || nI >= n ||  nJ < 0 || nJ >= m) continue;
				if(c[nI][nJ] == 0) {
					c[nI][nJ] = 2;
					queue.add(new Node(nI, nJ));
				}
			}
		}
		int cnt = 0;
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < m; j++) {
				if(c[i][j] == 0)
					cnt ++;					
			}
		}
		
		return cnt;
	}
	public static int[] dirI = new int[] {-1,0,0,1};
	public static int[] dirJ = new int[] {0,-1,1,0};
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
//		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		int i, j;
		int[][] arr = new int[n][m];

		ArrayList<Node> list = new ArrayList<>();
		ArrayList<Node> virusList = new ArrayList<>();
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				arr[i][j] = in.nextInt();
				if(arr[i][j] == 0) {
					list.add(new Node(i,j));
				}
				if(arr[i][j] == 2) {
					virusList.add(new Node(i,j));
				}
			}
		}
		DFS dfs = new DFS(list,virusList, arr, n, m);
		dfs.search(0, 0);
		System.out.println(dfs.max);
	}
	

	public static int[][] copy(int[][] arr){
		int[][] cpy = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j ++) {
				cpy[i][j] =arr[i][j];
			}
		}
		return cpy;
	}
}
