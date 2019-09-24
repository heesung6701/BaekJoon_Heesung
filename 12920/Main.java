import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	static int N;
	static int M;
	static int[] d;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try { in = new Scanner(new File("input.txt")); }catch(Exception e) { e.printStackTrace(); }
		N = in.nextInt();
		M = in.nextInt();
		
		ArrayList<Data> list = new ArrayList<Data>();
		for(int i = 0; i < N ;i++) {
			final int v = in.nextInt();
			final int c = in.nextInt();
			int k = in.nextInt();
			
			int bb = 1;
			while(k > 0) {
				if(k - bb <= 0) {
					list.add(new Data(v* k, c * k));
					break;
				}
				k -= bb;
				list.add(new Data(v * bb,c * bb));
				bb = bb << 1;
			}
		}
		Collections.sort(list);
//		System.out.println(list);
		d = new int[M + 1];
		d[0] = 1;
		int ans = 0;
		for(int j = 0; j < list.size(); j++) {
			Data cur = list.get(j);
			for(int i = M; i > 0; i--) {
				if(i - cur.v < 0) break;
				if(d[i-cur.v] == 0) continue;
				if(d[i] == 0) {
					d[i] =  d[i - cur.v] + cur.c;
				}else {
					d[i] =  d[i] > d[i - cur.v] + cur.c ? d[i] : d[i - cur.v] + cur.c;	
				}
				ans = ans > d[i] ? ans : d[i];				
			}
		}
		System.out.println(ans - 1);

	}
}
class Data implements Comparable<Data>{
	int v,c;
	public Data(int v, int c) {
		this.v = v;
		this.c = c;
	}
	@Override
	public int compareTo(Data o) {
		return v - o.v;
	}
	@Override
	public String toString() {
		return "("+v+","+c+")";
	}
}
