import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class Vect{
	int x;
	int y;
	public Vect(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	
	public static int[] delthX = new int[]{1,0,-1,0}; 
	public static int[] delthY = new int[]{0,-1,0,1};
	
	public static String[] sss = new String[] {"Right","Up","Left","Down"};
	
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

//		try{ in = new Scanner(new File("Input.txt")); }catch(Exception e) { e.printStackTrace(); }

		int n = in.nextInt();

		int[][] arr = new int[101][101];
		int i, j;

		for (i = 0; i < n; i ++) {
			int sx = in.nextInt();
			int sy = in.nextInt();
			
			int d = in.nextInt();
			int g = in.nextInt();

			ArrayList<Integer> list = new ArrayList<Integer>();
			
			list.add(d);
			
			for (j = 1; j <= g; j++) {
				ArrayList<Integer> created = new ArrayList<Integer>();
				for(int k = list.size()-1; k >= 0; k --){
					int dir = list.get(k);
					int newDir = (dir + 1) % 4;
					created.add(newDir);
				}
				list.addAll(created);
			}
			
			
//			System.out.println("#"+i);
			arr[sy][sx]=1;
			for(int dir : list) {
//				System.out.print(sss[dir]+" ");
				sx += delthX[dir];
				sy += delthY[dir];
				arr[sy][sx] = 1;	
			}
//			System.out.println();
		}
		int answer = 0;
		for(i = 0 ; i <100; i++) {
			for(j = 0 ; j <100; j++) {
				if(arr[i][j] + arr[i+1][j] + arr[i][j+1] +arr [i+1][j+1] == 4)
					answer ++;
			}
		}
		System.out.println(answer);
	}
}
