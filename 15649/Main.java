import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int N = in.nextInt();
		final int M = in.nextInt();
		Solution sol = new Solution(N,M);
		sol.work();
	}
}
class Solution{
	final int n;
	final int m;
	
	int[] list;
	int[] c;
	public Solution(int n, int m) {
		this.n = n;
		this.m = m;
		list = new int[n];
		c = new int[n + 1];
	}
	public void work() {
		search(0);
	}
	private void search(int level) {
		if(level == m) { 
			for(int i = 0; i < m; i++) System.out.print(list[i]+" ");
			System.out.println();
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(c[i] == 1) continue;
			c[i] = 1;
			list[level] = i;
			search(level +1);
			c[i] = 0;
		}
	}
}
