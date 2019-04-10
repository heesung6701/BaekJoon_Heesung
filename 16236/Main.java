import java.io.File;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//start	18:11

class Node{
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class Shark extends Node implements Comparable<Shark>{
	int size;
	public Shark(int x, int y, int size) {
		super(x,  y);
		this.size = size;
	}
	@Override
	public int compareTo(Shark o) {
		if(size != o.size)
			return size - o.size;
		if(x != o.x)
			return x - o.x;
		return y - o.y;
	}
	@Override
	public String toString() {
		return "("+this.x + "," + y+ "):"+size;
	}
}
class Player extends Node{
	int size;
	int ate;

	public Player(int x, int y) {
		super(x,  y);
		this.size = 2;
		this.ate = 0;
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void eat(Shark shark) {
		ate ++;
		if(ate == size) {
			ate = 0;
			size ++;
		}
		move(shark.x, shark.y);
	}
}
public class Main {

	static int n;
	static int[][] arr;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }

		n = in.nextInt();
		arr = new int[n][n];
		int i, j;

		Player p = null;
		ArrayList<Shark> list = new ArrayList<>();
		for (i = 0; i < n; i ++) {
			for ( j = 0 ; j < n; j ++) {
				arr[i][j] = in.nextInt();
				switch(arr[i][j]) {
				case 9:
					p = new Player(i, j);
					arr[i][j] = 0;
					break;
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
					list.add(new Shark(i,j, arr[i][j]));
					break;
				}
			}
		}
		Collections.sort(list);

		int time = 0;

		while(true) {
			if(list.size() == 0) break;
			if(p.size <= list.get(0).size) break;

			Shark target = null;
			int min =  -1;
			

			for (i = 0; i < list.size(); i++) {
				if (list.get(i).size >= p.size)
					break;

				Shark feed = list.get(i);
				int distance = ableMove(p, feed, p.size);
				if(distance == -1) continue;
				if(min == distance) {
					if(target.x < feed.x) continue;

					if(target.x != feed.x) {
						target = feed;
						continue;
					}

					if(target.y < feed.y) continue;

					if(target.y != feed.y) {
						target = feed;
						continue;
					}
				}
				else if(min == - 1 || min > distance) {
					min = distance;
					target = feed;
				}
			}
			if(target == null) break;
			p.eat(target);
			
			list.remove(target);
			time += min;
		}
		System.out.println(time);
	}

	public static int ableMove(Node a, Node b, int size) {
		Queue<Node> q = new LinkedList<Node>();
		int[][] c = new int[n][n];

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				c[i][j] = 0;
			}
		}
		
		q.add(a);	
		c[a.x][a.y] = 1; 
		while(!q.isEmpty() && c[b.x][b.y] == 0 ) {
			Node cur = q.poll();
			for(int dir = 0; dir < 4; dir ++) {
				int nX= cur.x + dirX[dir];
				int nY= cur.y + dirY[dir];
				if(nX <0 || nY < 0 || nX >= n || nY >= n) continue;
				if(c[nX][nY] != 0) continue;
				if(arr[nX][nY] > size) continue;
				c[nX][nY] = c[cur.x][cur.y] + 1;
				q.offer(new Node(nX, nY));
			}
		}
		if(c[b.x][b.y] == 0) return -1;

		return c[b.x][b.y] - 1;
	}

	final static int[] dirX = new int[] {-1,0,0,1};
	final static int[] dirY = new int[] {0, -1,1,0};
}
