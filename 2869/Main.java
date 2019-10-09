import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final long A = in.nextInt();
		final long B = in.nextInt();
		final long V = in.nextInt();
		long diff = A - B;
		long day = (V - A) / diff;
		long h = diff * day;
		
		while(h < V) {
			day ++;
			h+=A;
			if(h >= V) break;
			h-=B;
		}
		System.out.println(day);
	}
}
