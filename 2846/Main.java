import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int N = in.nextInt();
		int start = in.nextInt();
		int buf = start;
		int max = 0;
		for(int i = 1; i < N; i++) {
			int cur = in.nextInt();
			if(buf >= cur) {
				max = max > buf - start ? max : buf - start;
				start = cur; 
			}
			buf = cur;
		}
		max = max > buf - start ? max : buf - start;
		System.out.println(max);
	}
}
