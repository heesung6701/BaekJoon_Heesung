import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int B = in.nextInt();

		StringBuffer answer = new StringBuffer();
		while(N > 0) {
			int cur = N % B;
			char val;
			if(cur >= 10) val = (char) (cur - 10 + 'A');
			else val = (char) (cur + '0');
			answer.insert(0, val);
			N/= B;
		}
		System.out.println(answer);
	}
}
