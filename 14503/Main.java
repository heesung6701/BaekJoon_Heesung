import java.io.File;
import java.util.Scanner;

//start  2019/3/25 13:18
//start 2019/3/25 13;43
class Node{
	int x;
	int y;
	int dir;
	
	public Node(int x, int y, int dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
/*
	현재 위치를 청소한다.
	현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
	왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
	왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
	네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
	네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
*/
	public boolean move(int[][] map) {
		if(map[x][y] == 0) {
			map[x][y] = 2;
		}
		
		int i;
		for(i = 0 ; i < 4; i ++) {
			rotateLeft();
			int nX = x + dirX[dir];
			int nY = y + dirY[dir];
			if (map[nX][nY] == 0) {
				x = nX;
				y = nY;
				return true;
			}
		}
		if (i == 4) {
			int nDir = (dir + 2)  % 4; //후진
			int nX = x + dirX[nDir];
			int nY = y + dirY[nDir];
			if(map[nX][nY] == 1) {
				return false;
			}
			x = nX;
			y = nY;
			return true;
		}
		return false;
	}
	
	static String[] dirS = new String[] {"North","East","South","West"};
	static int[] dirX = new int[] {-1,0,1,0};
	static int[] dirY = new int[] {0,1,0,-1};
	
	final static int UP = 0;
	final static int RIGHT = 1;
	final static int DOWN = 2;
	final static int LEFT = 3;
	
	public void rotateLeft() {
		dir = (dir + 3) % 4;
	}

}

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try {in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		
		int i, j;
		
		int n = in.nextInt();
		int m = in.nextInt();

		int[][] arr = new int[n][m];
		
		int r = in.nextInt();
		int c = in.nextInt();
		int dir = in.nextInt();
		Node robot = new Node(r,c,dir);
		
		for(i = 0; i < n; i ++) {
			for (j = 0; j < m; j++) {
				arr[i][j] = in.nextInt();
			}
		}
		while(robot.move(arr));

		int answer = 0;
		for(i = 0; i < n; i ++) {
			for (j = 0; j < m; j++) {
//				System.out.printf("%4d", arr[i][j]);
				if(arr[i][j] == 2) {
					answer ++;
				}
			}
//			System.out.println();
		}
		System.out.println(answer);
	}

}
