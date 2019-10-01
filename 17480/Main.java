//import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		try{ in = new Scanner(new File("input.txt"));} catch(Exception e) { e.printStackTrace(); }
		final int N = in.nextInt();
		int M = 0;
		int[] cond = new int[27];
		int remain = N;
		for(int i = 0; i < N; i++) {
			char alpha = in.next().charAt(0);
			int cnt = in.nextInt();
			M+= cnt;
			cond[alpha - 'a'] = cnt;
		}
		String str = in.next();
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			if(cond[cur-'a'] == 1) {
				remain -=1;
			}
			cond[cur-'a'] --;
			int buf = i - M;
			if(buf >= 0) {
				cond[str.charAt(buf)-'a'] ++ ;
				if(cond[str.charAt(buf)-'a'] > 0) remain +=1;
			}
			if(remain == 0) list.add(str.substring(buf + 1, buf + M + 1));
			continue;
		}
		int ans = 0;
		HashMap<String, Boolean> chk = new HashMap<String, Boolean>();
		for(int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			Solution sol = new Solution(s, chk);
			ans += sol.work();
		}
		System.out.println(ans);
	}
}
class Solution{
	char[] arr;
	int size;
	int ans;
	HashMap<String, Boolean> chk;
	public Solution(String str, HashMap<String, Boolean> chk) {
		size = str.length();
		this.chk = chk;
		arr = new char[size];
		for(int i = 0; i < size; i++) {
			arr[i] = str.charAt(i);
		}
		ans = 0;
	}
	public int work() {
		search(0,size - 1);
		return ans;
	}
	public void search(int s, int e) {
		if(s >= e) {
			StringBuilder strBuild = new StringBuilder();
			for(int i = 0; i < arr.length; i++) {
				strBuild.append(arr[i]);
			}
			String str = strBuild.toString();
			if(!chk.containsKey(str)) {
				chk.put(str, true);
				ans ++;
//				System.out.println(str);
			}
			return;
		}
		int m = s + (e - s + 1) / 2;
//		System.out.println(s+"~"+e+"=>"+m);
		reverse(arr, s, m - 1);
		search(m, e);
		reverse(arr, s, m - 1);

		reverse(arr, m, e);
		search(s, m - 1);
		reverse(arr, m, e);

		if((e - s + 1) % 2 == 1) {
			reverse(arr, s, m);
			search(m + 1, e);
			reverse(arr, s, m);
			
			reverse(arr, m + 1, e);
			search(s, m);
			reverse(arr, m + 1, e);
		}
	}
	public void reverse(char[] arr, int x, int y) {
		char[] arr2 = arr.clone();
		int from = y;
		for(int i = x; i <= y; i++) {
			arr2[i] = arr[from];
			from --;
		}
		for(int i = x; i <= y; i++) 
			arr[i] = arr2[i];
	}
}
