import java.util.Scanner;

public class Main {

	static boolean valid[] = new boolean[1000001];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		double start = in.nextDouble();
		double end = in.nextDouble();

		for(double i = 2; i<=Math.sqrt(10e12); i++) {
			double ss = i * i;
//			System.out.println("1:"+ss);
			if(ss > end) break;

			double head = ss * (Math.floor(start/ss));
//			System.out.println("2:"+head+","+ss);
			while(head<=end) {
				if(head >= start) {
					int pos = (int)(head - start);
					valid[pos] = true;
				}
				head+=ss;
				//System.out.println("3-1:"+head);
			}
//			System.out.println("4:");
		}
//		System.out.println("???");

		double h = start;
		int ans = 0;
		while(h <=end) {
			int pos = (int)(h - start);
			if(!valid[pos]) ans++;
			h++;
		}
		System.out.println(ans);
	}
}
