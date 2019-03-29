import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class Node{
	int x, y;
	public Node(int x,int y) {
		this.x = x;
		this.y = y;
	}
	public boolean isEqual(Node _node) {
		return x == _node.x && y == _node.y;
	}
}
class DFS{
	int n, l;
	int[][] arr;
	int max = 0;
	public DFS(int n, int l, int[][] arr) {
		this.n = n;
		this.l = l;
		this.arr = arr;
		dfsRow(0,0);
	}

	public void dfsRow(int level, int  cnt) {
		if(level == n) {
			dfsCol(0, cnt);
			return;
		}
		int j, k;

		int cur = arr[level][0];
		int dup = 1;

		for(j = 1; j < n; j ++) {
			if(cur == arr[level][j]) {
				dup++;
				continue;
			}
			if(Math.abs(cur - arr[level][j]) != 1) 
				break;

			if(cur + 1 == arr[level][j]) {
				if(dup < l) 
					break;

				cur += 1;
			}

			dup = 1;

			if(cur - 1 == arr[level][j]) {
				if(j + l -1 >= n) break;
				for(k = 1; k < l; k ++) {
					if(cur - 1 == arr[level][j + k])
						continue;
					break;
				}
				if(k == l) {
					cur-=1;
					j += l;
					j--;
					dup = 0;
				}
				else
					break;
			}
		}
		if(j >= n) {
			dfsRow(level +1 , cnt + 1);
		}else {
			dfsRow(level + 1, cnt);
		}
	}
	public void dfsCol(int level, int cnt) {
		if(level == n) {
			max = max > cnt ? max : cnt;
			return;
		}

		int j, k;

		int cur = arr[0][level];
		int dup = 1;

		for(j = 1; j < n; j ++) {
			if(cur == arr[j][level]) {

				dup++;
				continue;

			}
			if(Math.abs(cur - arr[j][level]) != 1) 
				break;

			if(cur + 1 == arr[j][level]) {
				if(dup < l) 
					break;
				
				cur += 1;
			}

			dup = 1;

			if(cur - 1 == arr[j][level]) {
				if(j + l - 1 >= n) break;
				for(k = 1; k < l; k ++) {
					if(cur - 1 == arr[j + k][level])
						continue;
					break;
				}
				if (k == l) {
					cur-=1;
					j += l;
					j--;
					dup = 0;
				}
				else
					break;
			}
		}
		if(j >= n) {
			dfsCol(level +1 , cnt + 1);
		}else
			dfsCol(level +1 , cnt);
	}
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);


		int n = in.nextInt();
		int l = in.nextInt();
		int i, j;
		int[][] arr = new int[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				arr[i][j] = in.nextInt();
			}
		}
		DFS dfs = new DFS(n,l,arr);
		System.out.println(dfs.max);
	}
}
