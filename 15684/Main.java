import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//date 19.04.10
//start 12:30
//end	13:13

//class Node{
//	int x, y;
//	public Node(int x, int y) {
//		this.x =x ;
//		this.y =y;
//	}
//	public String toString() {
//		return "("+x+", "+ y+")";
//	}
//}
class DFS{
	int n;
	int m;
	int[][] arr;
	
	int answer = -1;
	
//	ArrayList<Node> list = new ArrayList<>();
	public DFS(int[][] arr, int n, int m) {
		this.arr = arr;
		this.n = n;
		this.m = m;
	}
	
	public void search(int level, int added) {
		if(level == n * m) {
			int i, j, start;
//			boolean debug = false && list.size() ==3 && list.get(0).x == 0 && list.get(0).y == 2 && 
//					list.get(1).x == 3 && list.get(1).y == 1 && 
//							list.get(2).x == 3 && list.get(2).y == 3;
			
//			if(debug) System.out.println("added : " + added);
			
//			if(debug) System.out.println(list);
//			if(debug) printArr();
			for(start = 0; start<m; start++) {
				j = start;
				for(i = 0; i < n; i++) {
					if(arr[i][j] == 0) continue;
					if(arr[i][j] == 1) {
						j ++;
						continue;
					}
					if(arr[i][j] == 2) {
						j --;
						continue;
					}
					
				}
				if(j != start) {
//					if(debug) System.out.println("start : "+ start + " -> " + j);
					break;
				}
			}
			if(start == m) {
				answer = answer == -1 || answer > added ? added : answer;
			}
			return;
			
		}
		search(level +1, added);
		
		if(added >= 3) return; //최대 3개 설치 가능
		
		int x = level / m;
		int y = level % m;
		
		if(y == m-1) return; //마지막행은 놓을수 없음.
		
		if(arr[x][y+1] == 2 || (y !=0 && arr[x][y-1] == 1)) return; //인접하는 경우
		
		if(arr[x][y+1] != 0 || arr[x][y] != 0) return; //이미 놓여있는 겨우
		
		arr[x][y+1] = 2;
		arr[x][y] = 1;
//		Node _n = new Node(x,y);
//		list.add(_n);
		search(level +1, added + 1);
//		list.remove(_n);
		arr[x][y+1] = 0;
		arr[x][y] = 0;
	}
	
//	public void printArr() {
//		int i, j;
//
//		for(i = 0; i < n; i++) {
//			for(j = 0; j < m; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
//	}
	
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
//		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }

		int m = in.nextInt();
		int k = in.nextInt();
		int n = in.nextInt();
		
		int i, j;
		int[][] arr = new int[n][m];
		for(i = 0; i < n; i++) {
			for(j = 0; j < m; j++) {
				arr[i][j] = 0;
			}
		}
		for (i = 0; i < k ; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			arr[a][b] = 1;
			arr[a][b+1] = 2;
		}
		DFS dfs = new DFS(arr ,n, m);
		dfs.search(0, 0);
		
		System.out.println(dfs.answer);
	}

}
