import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = in.nextInt();

		for(int _testcase = 0; _testcase < testcase; _testcase++) {
			int k = in.nextInt();
			int n = in.nextInt();
			int[][] arr =new int[k+1][n+1];
			for(int j = 1; j <=n; j++) {
				arr[0][j] = j;
			}
			for(int i = 1; i<=k; i++) {
				for(int j = 1; j <=n; j++) {
					arr[i][j] = arr[i][j-1] + arr[i-1][j];
				}
			}
			System.out.println(arr[k][n]);
		}
	}
}
