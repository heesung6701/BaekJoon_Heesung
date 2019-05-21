import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int move = 1;
		int sum = 1;
		while(n > sum) {
			sum += move * 6;
			move ++;
		}
		System.out.println(move);
	}
}
