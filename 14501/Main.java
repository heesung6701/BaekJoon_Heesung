import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


class Node{
	int t;
	int p;
	public Node(int t, int p) {
		this.t = t;
		this.p = p;
	}
}
class Dfs{
	int n;
	Node[] arr;
	int answer = 0;
	public Dfs(int n, Node[] arr) {
		this.n = n;
		this.arr = arr;
	}
	
	public void search(int level, int sum) {
		if(level == n) {
			answer = answer > sum ? answer : sum;
			return;
		}
		

		Node cur = arr[level];

		if(level + cur.t > n) {
			search(level + 1, sum);
			return;
		}
		
		search(level + cur.t, sum + cur.p);
		search(level + 1, sum);
	}
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
//		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		
		int i, j;
		
		int n = in.nextInt();
		Node[] arr =new Node[n];
		
		for(i = 0; i < n; i++) {
			int t = in.nextInt();
			int p = in.nextInt();
			arr[i] = new Node(t, p); 
		}
		
		Dfs dd = new Dfs(n, arr);
		dd.search(0, 0);
		
		System.out.println(dd.answer);
	}
}
