import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// start	2:00
// end 2:24
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		try { in = new Scanner(new File("input.txt")); }catch(Exception e) { e.printStackTrace(); }
		
		String data = in.nextLine();
		int answer = data.length() - 1;

		char pivot = data.charAt(data.length() - 1);
		
		ArrayList<Integer> list = new ArrayList<Integer>();


		int max = 1;
		
		int q = data.indexOf(pivot);
		list.add(q);
		while(true) {
			q = data.indexOf(pivot, q + 1);
			if (q == -1) break;
			list.add(q);
		}
		for(int i = 0; i < list.size(); i++) {
			int j = list.size() - 1;
//			for(int j = i + 1; j < list.size(); j++) {
				String str1 = data.substring(list.get(i), list.get(j) + 1);
				String str2 = "";
				for(int k = 0; k < str1.length(); k++) {
					str2 += str1.charAt(str1.length() - 1 - k);
				}

				if(str1.equals(str2)) {
//					System.out.println(str1 + " : " + str2);
					max = max > str1.length() ? max : str1.length();
				}
//			}
		}
		System.out.println(data.length() * 2 - max);		
	}
}
