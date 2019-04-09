import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


// start 13:07
// end 15:20
// time over
class Node implements Comparable{
	int x;
	int y;
	int able;
	public Node(int x, int y, int able){
		this.x = x;
		this.y = y;
		this.able = able;
	}
	@Override
	public String toString() {
		return "("+x+","+y+")."+able;
	}
	@Override
	public int compareTo(Object o) {
		Node node = (Node)o;
		return node.able - able;
	}
}
class DFS{
	int N;
	int[][] row;
	int[][] col;
	int[][] arr;
	ArrayList<Node> list;
	ArrayList<Node> selected = new ArrayList<>();
	int[] check = new int[] {0,5,5,5,5,5};
	int answer;
	public DFS(int[][] arr, int N) {
		this.N = N;
		this.arr = arr;
		this.list = new ArrayList<>();

		int i, j, k, l;

		row = new int[N][N];
		col = new int[N][N];
		int cnt = 0;
		for (i = N - 1; i >= 0; i--) {
			for (j = N -1; j >= 0; j--) {
				if(arr[i][j] == 0) continue;
				cnt ++;
				row[i][j] = j == N-1 ? arr[i][j] : row[i][j+1] + arr[i][j];
				col[i][j] = i == N-1 ? arr[i][j] : col[i+1][j] + arr[i][j];
			}
		}
		if(cnt <= 5) answer = cnt;
		else this.answer = -1;

		for (i = 0; i < N; i ++) {
			for (j = 0; j < N; j ++) {
				if(arr[i][j] == 0)
					continue;
				int able = Math.min(Math.min(row[i][j], col[i][j]),5);
				int maxAble = 1;
				for(k = able; k > 1; k--) {
					for(l = 1; l <k; l++) {
						if(row[i + l][j] < k)
							break;
						if(col[i][j + l] < k)
							break;
					}
					if(l != k) continue;
					
					maxAble = k;
					break;
				}
				list.add(new Node(i, j, maxAble));
			}
		}		
		search(0,0, cnt);
	}
	public void search(int level, int cnt, int remain) {
		if (level == list.size()) {
			if(remain != 0) return;
			answer = answer == -1 || answer > cnt ? cnt : answer;
			return;
		}
		if (answer != -1 && answer < cnt) {
			return;
		}
		
		Node cur = list.get(level);
		int x = cur.x;
		int y = cur.y;
		
		if(arr[x][y] == 0) {
			search(level + 1, cnt, remain);
			return;
		}

		int width,i,j;

		ArrayList<Node> deleted = new ArrayList<>();
		
		for(width = cur.able; width > 1; width--) {
			if(check[width] == 0) continue;
			
			for(i = x; i < x + width; i++) {
				for(j = y; j < y + width; j++) {
					if(arr[i][j] == 0) break;
					arr[i][j] = 0;
					deleted.add(new Node(i,j,-1)); 
				}
				if (j < y + width)
					break;
			}
			if (deleted.size() != width * width) {
				for(Node _node : deleted) {
					arr[_node.x][_node.y] = 1; 
				}
				deleted.clear();
				continue;
			}
			check[width]--;
//			selected.add(cur);
			search(level + 1, cnt + 1 , remain - deleted.size());
//			selected.remove(cur);
			for(Node _node : deleted) {
				arr[_node.x][_node.y] = 1; 
			}
			deleted.clear();
			check[width]++;
		}

		if(check[1] == 0)
			return;
		check[1]--;
		arr[x][y] = 0;  
		search(level + 1, cnt + 1, remain - 1);
		arr[x][y] = 1;
		check[1]++;
	}
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }

		int i, j;
		int[][] arr = new int[N][N];
		for (i = 0; i < N; i++) {
			for (j = 0 ; j < N; j ++) {
				arr[i][j] = in.nextInt();
			}
		}
		DFS dfs = new DFS(arr, N);
		System.out.println(dfs.answer);
	}

	static final int N = 10;
}
