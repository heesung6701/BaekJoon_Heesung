import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String N = in.next();
		int B = in.nextInt();

		long answer = 0, dim = 1;
		for(int i = N.length()-1 ; i >=0; i--) {
			char cur = N.charAt(i);
			int val;
			if(cur >= 'A') val = cur - 'A' + 10;
			else val = cur - '0';
			answer += dim * val;
			dim *= B;
		}
		System.out.println(answer);
	}
}
