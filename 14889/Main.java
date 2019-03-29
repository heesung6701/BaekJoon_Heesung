import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int n;
	static int[][] adj; 
	static int answer = -1;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
//		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }

		int i, j;
		
		n = in.nextInt();
		
		adj = new int[n+1][n+1];
		
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				adj[i][j] = in.nextInt();
				adj[j][i] += adj[i][j];
			}
		}
		dfs(0);
		System.out.println(answer);
	}
	
	static ArrayList<Integer> list = new ArrayList<>();
	static ArrayList<Integer> unList = new ArrayList<>();
	
	public static void dfs(int level) {
		if(level == n) {
			if(list.size() != n / 2)
				return;
//			System.out.println("list : " + list);
//			System.out.println("unList : " + unList);
			
			int sum = 0;
			for(int i = 0; i < list.size(); i++) {
				for(int j = i + 1; j < list.size(); j++) {
					sum += adj[list.get(i)][list.get(j)];
				}
			}

			int sum2 = 0;
			for(int i = 0; i < unList.size(); i++) {
				for(int j = i + 1; j < unList.size(); j++) {
					sum2 += adj[unList.get(i)][unList.get(j)];
				}
			}
//			System.out.println(sum + " - " + sum2);
			int diff = Math.abs(sum2 - sum);
			answer = answer == -1 || answer > diff ? diff : answer;
			
			return;
		}
		
		Integer tmp = new Integer(level + 1);
		
		if(list.size() < n / 2) {
			list.add(tmp);
			dfs(level + 1);
			list.remove(tmp);
		}
		unList.add(tmp);
		dfs(level + 1);
		unList.remove(tmp);
	}
}
