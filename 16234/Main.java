import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//start 13:45
//end 14:15
class Node{
	int x;
	int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "("+x+", "+ y+")";
	}
}
class DFS {
	int n, L,R;
	int[][] arr;

	int answer = 0;

	int[][] c;

	public DFS(int n, int[][] arr, int L,int R) {
		this.n = n;
		this.arr = arr;
		this.L = L;
		this.R = R;
		c = new int[n][n];
		clearC();
	}

	void search() {

		ArrayList<ArrayList<Node>> countries = new ArrayList<ArrayList<Node>>();

		int i, j;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (c[i][j] == 0) {
					ArrayList<Node> list = new ArrayList<Node>();
					Node start = new Node(i, j);
					list.add(start);
					countries.add(list);
					find(start, countries.size(), list);
				}
			}
		}
		if(countries.size() == n*n) {
			return;
		}
		answer ++;
		
		for(ArrayList<Node> list : countries) {
			int sum = list.stream().mapToInt(_node -> arr[_node.x][_node.y]).sum();
			int same = sum / list.size();
			for(Node _node : list) {
				arr[_node.x][_node.y] = same;
			}
		}
		clearC();
		search();
	}
	void find(Node _node, int idx, ArrayList<Node> list) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(_node);
		c[_node.x][_node.y] = idx; 
		while(q.isEmpty() == false) {
			Node cur = q.poll();
			for (int dir = 0; dir < 4; dir ++) {
				int i = cur.x + dirX[dir];
				int j = cur.y + dirY[dir];
				if (i < 0 || i >= n || j < 0 || j >= n) continue;
				
				int diff = Math.abs(arr[i][j] - arr[cur.x][cur.y]);
				if (diff <L || diff > R) continue;
				
				if(c[i][j] != 0) {
					if(c[i][j] != idx) {
						try {
							throw new Exception("?????");
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
					continue;
				}
				c[i][j] = idx;
				Node next = new Node(i,j);
				list.add(next);
				q.offer(next);
			}
		}
	}

	void showArr(int[][] ac) {
		System.out.println("--------------");
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(ac[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("--------------");
	}
	
	void clearC() {
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				c[i][j] = 0;
			}
		}
	}

	final static int dirX[] = {0, -1, 1, 0};
	final static int dirY[] = {-1, 0, 0, 1};
}
public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

//		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }

		int n = in.nextInt();
		int L = in.nextInt();
		int R = in.nextInt();

		int[][] arr = new int[n][n];
		for(int i = 0; i < n; i ++) {
			for(int j = 0; j < n; j++) {
				arr[i][j] =in.nextInt();
			}
		}

		DFS dfs = new DFS(n, arr, L, R);
		dfs.search();

		System.out.println(dfs.answer);
	}
}
