import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


//	start 0230
// end 0307
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }

		int i, j;

		int CASE = in.nextInt();

		for(int test_case = 0; test_case < CASE; test_case ++){
			int n = in.nextInt();
			int m = in.nextInt();

			int[] price = new int[n];
			ArrayList<Integer>[] arr = new ArrayList[n];
			for (i = 0; i < n; i ++) {
				price[i] = in.nextInt();
				arr[i] = new ArrayList<Integer>();
			}

			int[][] adj = new int[n][n];
			for (i = 0; i < m; i++) {
				int x = in.nextInt() - 1;
				int y = in.nextInt() - 1;
				adj[x][y] = 1;
				arr[y].add(x);
			}
			int w = in.nextInt() - 1;

			ArrayList<Integer> list = findStart(arr, w, n);
			int answer = bfs(arr, w, n, list, adj, price);
			System.out.println(answer);
		}
	}

	public static ArrayList<Integer> findStart(ArrayList<Integer>[] arr, int w, int n) {
		int i;

		int[] c = new int[n];
		for (i = 0; i < n; i++) c[i] = 0;

		ArrayList<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(w);
		c[w] = 1;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			if(arr[cur].size() == 0) {
				list.add(cur);
			}
			for (i = 0; i < arr[cur].size(); i++) {
				if(c[arr[cur].get(i)] == 1) continue;
				c[arr[cur].get(i)] = 1;
				queue.offer(arr[cur].get(i));
			}
		}
		return list;

	}
	public static int bfs(ArrayList<Integer>[] arr, int w, int n, ArrayList<Integer> list, int[][] adj, int[] price) {
		int i, j;

		int[] c = new int[n];
		for (i = 0; i < n; i++) c[i] = 0;

		int[] weight = new int[n];

		Queue<Integer> queue = new LinkedList<Integer>();
		for(int start : list) {
			queue.offer(start);
			c[start] = 1;
			weight[start] = price[start];
		}

		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(i = 0; i < n; i++) {
				if (adj[cur][i] == 0 || c[i] == 1)  continue;
				
				for(j = 0; j < arr[i].size(); j++) {
					int need = arr[i].get(j);
					if(c[need] == 0) {
						break;
					}
				}
				if (j == arr[i].size()) {
					c[i] = 1;
					int max = weight[cur];
					for(j = 0; j < arr[i].size(); j++) {
						int need = arr[i].get(j);
						max = max > weight[need] ? max : weight[need]; 
					}
					weight[i] = max + price[i];
					queue.offer(i);
				}
			}
//			for(i = 0; i < n; i++) {
//				System.out.print(c[i]+":"+weight[i]+" ");
//			}
//			System.out.println();
		}

		return weight[w];
	}
}
