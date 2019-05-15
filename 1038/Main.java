import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		long answer = -1;
		int i;
		int target = in.nextInt();
		if(target==0) {
			System.out.println(0);
			return;
		}
		
		long num, cnt = 0;
		long limit = 987654321; 
		for(num = 1; num<=limit; ) {
			String strNum = num + "";
			boolean isDecrease = true;
			int diff = 1;
			for(i = strNum.length() - 1; i > 0; i--) {
				if((strNum.charAt(i - 1)- '0') <= (strNum.charAt(i)- '0') ) {
					isDecrease = false;
					break;
				}
				diff *= 10;
			}
			if(!isDecrease) {
				num+=diff;
				continue;
			}
			cnt += 1;
			System.out.println(num);
			if(cnt == target) {
				answer = num;
				break;
			}
			num++;
		}
		if(cnt + 1 == target) {
			System.out.println("9876543210");
			return;
		}
		System.out.println(answer);
		return;
		
	}

}
