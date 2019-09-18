import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;


class Item implements Comparable<Item>{
	int pos;
	int money;
	int dist;
	public Item(int pos,int money,int dist) {
		this.pos = pos;
		this.money = money;
		this.dist = dist;
	}
	@Override
	public String toString() {
		return "{pos:"+ pos+",money:"+money+",dist:"+dist+"} ";
	}
	@Override
	public int compareTo(Item o) {
		if(dist == o.dist) return money < o.money ? 1 : -1;
		return dist > o.dist ? 1 : -1;
	}
}
class Node implements Comparable<Node>{
	int v,c,d;
	public Node(int v,int c,int d) {
		this.v = v;
		this.c = c;
		this.d = d;
	}
	@Override
	public String toString() {
		return "{v:"+ v+",c:"+c+",d:"+d+"} ";
	}
	@Override
	public int compareTo(Node o) {
		if(c == o.c) return d > o.d ? 1 : -1;
		return c > o.c ? 1 : -1;
	}
}

public class Main {

	static final int MAX_N = 101;
	static final int MAX_M = 10001;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		
		int T = in.nextInt();

		PriorityQueue<Item> items = new PriorityQueue<Item>();
		ArrayList<Node>[] adjs = new ArrayList[MAX_N];
		int[][] d = new int[MAX_N][MAX_M]; // i도시까지 j비용으로 가는 최소 거리
		
		for(int test_case = 0; test_case < T; test_case++) {
			int N = in.nextInt();
			int M = in.nextInt();
			int K = in.nextInt();

			for(int i = 1; i <= N; i++) {
				if(adjs[i] == null) adjs[i] = new ArrayList<Node>();
				else adjs[i].clear();
				for(int j = 0; j <= M; j++) {
					d[i][j] = -1;
				}
			}
			
			for(int i = 0; i < K; i++) {
				int u = in.nextInt();
				Node node = new Node(in.nextInt(),in.nextInt(),in.nextInt());
				adjs[u].add(node);
			}

			for(int i = 1; i <= N; i++) {
				Collections.sort(adjs[i]);
			}
			
			items.add(new Item(1,M,0));
			d[1][M] = 0;
			boolean finded = false;
			while(!items.isEmpty()) {
				Item cur = items.poll();
				if(cur.pos == N) {
					finded = true;
					System.out.println(cur.dist);
					break;
				}
				for(Node node : adjs[cur.pos]) {
					int to = node.v;
					int cost = node.c;
					int dis = node.d;
					if(cost > cur.money) break;
					if(d[to][cur.money - cost] == -1 || d[to][cur.money - cost] > d[cur.pos][cur.money] + dis) {
						d[to][cur.money - cost] = d[cur.pos][cur.money] + dis;
						items.add(new Item(to, cur.money - cost, d[cur.pos][cur.money] + dis));
					}
				}
			}
			if(finded == false) System.out.println("Poor KCM");
		}
	}
}
