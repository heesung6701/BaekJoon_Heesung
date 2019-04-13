import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


//start 20:23
class Node{
	int x;
	int y;
	public Node(int x, int y) {
		this.x =x;
		this.y =y;
	}
}
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		try { in = new Scanner(new File("input.txt")); } catch(Exception e ) { e.printStackTrace(); }

		int r = in.nextInt();
		int c =  in.nextInt();
		int n = in.nextInt();

		int[][] arr = new int[r][c];
		int i, j;
		for(i = 0 ; i <r; i++) {
			String input = in.next();
			for(j = 0; j < input.length(); j++) {
				if(input.charAt(j) == '.')
					arr[i][j] = 0;
				else 
					arr[i][j] = 3;

			}
		}

		for(int time = 2; time <= n; time++) {
			if(time % 2 == 0) {
				for(i = 0; i < r; i ++) {
					for(j = 0; j < c; j++) {
						if(arr[i][j] > 0) continue;
						arr[i][j] = time + 3;
					}
				}
			}

			ArrayList<Node> list = new ArrayList<>();
			for(i = 0; i < r; i ++) {
				for(j = 0; j < c; j++) {
					if(time == arr[i][j]) {
						list.add(new Node(i,j));
					}
				}
			}
			for(Node o : list) {
				i = o.x;
				j = o.y;
				for(int dir = 0; dir < dirX.length; dir++) {
					int nX = i + dirX[dir];
					int nY = j + dirY[dir];
					if(nX < 0 || nY < 0 || nX>=r || nY >= c) continue;
					arr[nX][nY] = 0;
				}
				arr[i][j] = 0;
			}
//			System.out.println("#"+time);
//			for(i = 0; i < r; i ++) {
//				for(j = 0; j < c; j++) {
//					System.out.print(arr[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
		}

		for(i = 0; i < r; i ++) {
			for(j = 0; j < c; j++) {
				System.out.print(arr[i][j] > 0 ? 'O' : '.');
			}
			System.out.println();
		}
		in.close();
		return;
	}
	static int[] dirX = new int[] {-1,0,0,1};
	static int[] dirY = new int[] {0,-1,1,0};

}
