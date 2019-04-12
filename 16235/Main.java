import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//	start	11:13
//	time over 11:57...
//  end  	12:30
class Tree implements Comparable<Tree>{
	int x;
	int y;
	int z;
	public Tree(int x, int y,int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public boolean grow() {
		z++;
		return z % 5 == 0;
	}
	@Override
	public int compareTo(Tree o) {
		return o.z - z;
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+"):"+z;
	}
}
class Solution{
	int n;
	int m;
	int k;
	int[][] charge;
	int[][] arr;
	ArrayList<Integer>[][] listArr;
	
	int answer = 0;
	public Solution(int n,int m, int[][] charge, int[][] arr, ArrayList<Tree> list, int k) {
		this.n = n;
		this.m = m;
		this.charge = charge;
		this.arr = arr;
		this.k = k;

		Collections.sort(list);
		listArr = new ArrayList[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				listArr[i][j] =new ArrayList<Integer>();
			}
		}
		for(Tree _tree : list) {
			listArr[_tree.x][_tree.y].add(_tree.z);
		}
			
		go();
	}
	public void go() {
		int time = 0;
		ArrayList<Tree> parent = new ArrayList<Tree>();
		while(time < k) {
			parent.clear();
			springANDSummer(parent);
			fall(parent);
			winter();
			time ++;
		}
		int sum =0;
		for(int i = 0; i <n; i ++) {
			for(int j = 0; j < n; j++) {
				sum+=listArr[i][j].size();
			}
		}
		answer = sum;
	}
	public void printArr(String kk) {
		int i, j;
		System.out.println(kk);
		for(i = 0; i < n; i++) {
			for(j = 0; j < n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	public ArrayList<Tree> springANDSummer(ArrayList<Tree> parent) {
		ArrayList<Tree> deleted = new ArrayList<Tree>();
		int i, j, q;
//		printArr("spring");
		for(i =0; i < n; i ++) {
			for(j = 0; j <n; j++) {
				if(listArr[i][j].size()==0) continue;
//				System.out.println("("+i+","+j+") "+listArr[i][j]);
				for(q = listArr[i][j].size()-1; q >=0; q--) {
					int w = listArr[i][j].get(q);
					if(arr[i][j] < w) {
						deleted.add(new Tree(i,j,q));
						break;
					}
					arr[i][j] -= w;
					listArr[i][j].set(q,w + 1);
					if((w+1) % 5 ==0)
						parent.add(new Tree(i,j, w + 1));
				}
			}
		}		
		for(Tree _tree : deleted) {
			q = _tree.z;
			while(q >= 0) {
				int w = listArr[_tree.x][_tree.y].remove(q);
				arr[_tree.x][_tree.y] += (int) (w / 2);
				q --;
			}
		}
		return deleted;
	}
	public void fall(ArrayList<Tree> added) {
		for(Tree tree : added) {
			for(int dir = 0; dir < dirX.length; dir++) {
				int nX = tree.x + dirX[dir];
				int nY = tree.y + dirY[dir];
				if(nX < 0 || nX >=n || nY < 0 || nY >=n) continue;
				if(arr[nX][nY] + charge[nX][nY] ==0) continue;
				listArr[nX][nY].add(1);
			}
		}
	}
	public void winter() {
		int i, j;
		for(i = 0; i < n; i++) {
			for(j = 0; j < n; j++) {
				arr[i][j] += charge[i][j];
			}
		}
	}
	
	final static int[] dirX = new int[] {-1,-1,-1,0,0,1,1,1};
	final static int[] dirY = new int[] {-1,0,1,-1,1,-1,0,1};
	
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		
		int i, j;
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		
		int[][] charge = new int[n][n];
		int[][] arr = new int[n][n];
		for(i = 0; i < n; i++) {
			for(j = 0; j < n; j++) {
				charge[i][j] = in.nextInt();
				arr[i][j] = 5;
			}
		}
		ArrayList<Tree> list = new ArrayList<Tree>();
		for(i = 0; i < m; i ++) {
			int x = in.nextInt() - 1;
			int y = in.nextInt() - 1;
			int z = in.nextInt();
			list.add(new Tree(x,y,z));
		}
		Solution sol = new Solution(n, m , charge, arr, list, k);
		System.out.println(sol.answer);
	}
}
