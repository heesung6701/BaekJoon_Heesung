import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//start 20:00
//end 20:24

class Node{
	int x;
	int y;
	public Node(int x,int y) {
		this.x = x;
		this.y = y;
	}
	public int distance(Node _node) {
		return Math.abs(x - _node.x) + Math.abs(y - _node.y); 
	}
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
}
class DFS{
	int answer = -1;
	
	int n;
	int m;
	ArrayList<Node> chicks;
	ArrayList<Node> houses;
	ArrayList<Node> alive;
	
	public DFS(int n,int m, ArrayList<Node> chicks, ArrayList<Node> houses) {
		this.n = n;
		this.m = m;
		this.chicks = chicks;
		this.houses = houses;
		alive = new ArrayList<Node>();	
	}
	
	public void search(int level) {
		if (level == chicks.size()) {
			if (alive.size() < m) {
				return;
			}
//			System.out.println(alive);
			int i, j;
			int tot = 0;
			for (i = 0; i < houses.size(); i++) {
				Node house = houses.get(i);
				int min = alive.get(0).distance(house);
				for(j = 1; j < alive.size(); j++) {
					Node _node = alive.get(j);
					int distance = _node.distance(house);
					min = min < distance ? min : distance;
				}
				tot += min;
			}
			answer = answer == -1 || answer > tot ? tot : answer;
			return;
		}

		if(alive.size() == m) {
			search(level + 1);
			return;
		}
			
		Node _node = chicks.get(level);
		alive.add(_node);
		search(level + 1);
		alive.remove(_node);
		search(level + 1);
	}
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		
		int n = in.nextInt();
		int m = in.nextInt();
		int i, j;
		
		ArrayList<Node> chicks = new ArrayList<Node>();
		ArrayList<Node> houses = new ArrayList<Node>();
		int[][] arr = new int[n][n];
		
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				arr[i][j] = in.nextInt();
				switch (arr[i][j]) {
				case 1:
					houses.add(new Node(i, j));
					break;
				case 2:
					chicks.add(new Node(i, j));
					break;
				default :
					break;
					
				}
			}
		}
		
		DFS dfs = new DFS(n,m,chicks,houses);
		dfs.search(0);
		System.out.println(dfs.answer);
	}
}
