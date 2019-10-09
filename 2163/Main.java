import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int N = in.nextInt();
		final int M = in.nextInt();
		int result = divide(1,1,N,M);
		System.out.println(result);
	}
	static int divide(int sx, int sy, int ex, int ey) {
//		System.out.println(sx+":"+sy+","+ex+":"+ey);
		if(sx == ex && sy == ey) return 0;
		if(ey - sy > ex - sx) {
			int my = (int)((sy + ey) / 2);
			return divide(sx, sy, ex, my)
					+ divide(sx, my + 1, ex, ey)
					+ 1;
		}else {
			int mx = (int)((sx + ex) / 2);
			return divide(sx, sy, mx, ey)
					+ divide(mx + 1, sy, ex, ey)
					+ 1;	
		}
	}
}
