import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//start 10:20
class Pan{
	private int idx;
	private int[][] arr;
	private int angle;
	int n = 5;
	public Pan(int idx, int[][] arr) {
		this.idx = idx;
		this.arr = arr;
		this.angle = 0;
	}

	public Pan(int idx, int[][] arr, int angle) {
		this.idx = idx;
		this.arr = arr;
		this.angle = angle;
	}
	public boolean rotate() {
		if(angle == 3) return false;
		angle ++;
		
		return true;
	}
	public int get(int x, int y) {
		switch(angle) {
		case 0:
			return arr[x][y];
		case 1:
			return arr[y][n - 1 - x];
		case 2:
			return arr[n - 1 - x][n - 1 - y];
		case 3:
			return arr[n - 1 - y][x];
		}
		try{
			throw new Exception(">>???");
		}catch (Exception e) {
		}
		return -1;
	}
	public boolean isUnableStart() {
		return get(0,0) + get(0,n-1) + get(n-1,0) + get(n-1,n-1) == 0;
	}
	@Override
	public String toString() {
//		return "("+idx +" : "+ angle+")"; 
		int i, j;
		StringBuffer bf = new StringBuffer();
		bf.append("\n");
		for(i = 0; i < 5; i ++) {
			for(j = 0; j < 5; j ++) {
				bf.append(get(i,j)+" ");
			}
			bf.append("\n");
		}
		return bf.toString();
	}
	public Pan copy() {
		return new Pan(idx, arr, angle);
	}
}
class Solution{
	ArrayList<Pan> pans;
	ArrayList<Pan> list;
	int[] used;
	int answer = -1;
	public Solution(ArrayList<Pan> pans) {
		this.pans = pans;
		this.list = new ArrayList<Pan>();
		this.used = new int[5];
		for(int i = 0; i < 5; i++)
			used[i] = 0;
		
	}
	public void search(int level) {
		if(level == 1 && list.get(0).get(0, 0) == 0) return;
		
		if(level == 5) {
			if(list.get(4).get(4, 4) == 0) return;
			int min = bfs();
			if(min == -1) return;
			min -= 1;
			answer = answer == -1 || answer > min ? min : answer;
			return;
		}
		for(int i = 0; i < used.length; i ++) {
			if(used[i] == 1) continue;
			if(level == 0 && pans.get(i).isUnableStart()) continue;
			used[i] = 1;
			Pan cur = pans.get(i).copy();
			list.add(cur);
			search(level+1);
			while(cur.rotate()) {
				search(level+1);
			}
			list.remove(cur);
			used[i] = 0;
		}
	}
	public int bfs() {
		int[][][] arr = new int[5][5][5];
		int[][][] c = new int[5][5][5];
		int i, j, k;
		for(i = 0; i < 5; i ++) {
			for(j = 0; j < 5; j ++) {
				for(k = 0; k < 5; k ++) {
					arr[i][j][k] = list.get(i).get(j, k);
					c[i][j][k] = 0;
				}
			}
		}
		Queue<Dim3D> queue = new LinkedList<Dim3D>();
		queue.add(new Dim3D(0,0,0));
		c[0][0][0] = 1;
		while(!queue.isEmpty()) {
			Dim3D cur = queue.poll();
			
			for(int dir = 0; dir < dirX.length; dir++) {
				int nX = cur.x + dirX[dir];
				int nY = cur.y + dirY[dir];
				int nZ = cur.z + dirZ[dir];
				try {
					if(arr[nX][nY][nZ] == 0) continue;
				}catch(IndexOutOfBoundsException e) {
					continue;
				}
				if(c[nX][nY][nZ] != 0) continue;
				c[nX][nY][nZ] = c[cur.x][cur.y][cur.z] + 1;
				queue.add(new Dim3D(nX, nY, nZ));
			}
		}
		if(c[4][4][4] == 0) return -1;
		return c[4][4][4];
	}
	static final int[] dirX = new int[] {-1, 1, 0, 0, 0, 0};
	static final int[] dirY = new int[] {0, 0, -1, 1, 0, 0};
	static final int[] dirZ = new int[] {0, 0, 0, 0, -1, 1};
}
class Dim3D{
	int x;
	int y;
	int z;
	public Dim3D(int x,int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override
	public String toString() {
		return "("+x+","+y+","+z+")";
	}
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
//		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		
		ArrayList<Pan> list = new ArrayList<Pan>();
		int i, j, k;
		for(i = 0; i < 5; i ++) {
			int[][] arr = new int[5][5];
			for ( j = 0; j < 5; j++) {
				for ( k = 0; k < 5; k++) {
					arr[j][k] = in.nextInt();
				}
			}
			list.add(new Pan(i, arr));
		}
		
		Solution sol = new Solution(list);
		sol.search(0);
		System.out.println(sol.answer);

	}
}
