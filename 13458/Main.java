import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }
		int i, j;
		
		long answer = 0;
		int n = in.nextInt();
		int[] arr = new int[n];
		for (i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		int b = in.nextInt();
		int c = in.nextInt();
		
		for (i = 0; i < n; i++) {
			arr[i] -= b;
			answer ++;
			if(arr[i] <= 0) continue;
			int people = arr[i] / c;
			if (arr[i] % c > 0) people ++;
			answer +=people;
		}
		
		System.out.println(answer);
		
	}
}
