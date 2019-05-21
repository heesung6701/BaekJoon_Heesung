import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		int line = 1;
		while(n > line) {
			n-=line;
			line ++;
		}
		line ++;
		if(line %2 == 0)
			System.out.println((line-n)+"/"+n);
		else 
			System.out.println(n+"/"+(line-n));
	}
}
