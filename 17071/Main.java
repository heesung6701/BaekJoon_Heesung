import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//	start 	16:35
//	end		17:56
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); };

		int n = in.nextInt();
		int k = in.nextInt();

		ArrayList<Integer> brother = new ArrayList<>();
		brother.add(k);
		int tmp = k;
		int delta = 1;
		while(true) {
			tmp += delta;
			if(tmp > 500000) break;
			brother.add(tmp);
			delta ++;
		}
		int i, j;
		int time;
		int[][] c = new int[500001][2];

		for(i = 0; i <= 500000; i++) c[i][0] = c[i][1] = -1;
		c[n][0] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(n);
		queue.add(0);
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			int odd = queue.poll();
			addNew(c,odd, cur, cur*2, queue);
			addNew(c,odd, cur, cur+1, queue);				
			addNew(c,odd, cur, cur-1, queue);
		}

		int answer = -1;

		for(i = 0; i < brother.size(); i++) {
			int b = brother.get(i);
			for(int odd = 0; odd< 2; odd++) {
				if(c[b][odd] == -1) continue;

				if(c[b][odd] > i) continue;
//				System.out.println("b:" + b + " : c[b] " + c[b] + ", i :"+ i);

				if(c[b][odd] == i) {
//					System.out.println(b+"/"+ "c[b] : "+ c[b] + " , " + "i : " + i);
					answer = answer == -1 || answer > i ? i : answer;
					break;
				}
				if((c[b][odd] - i) % 2 == 0) { 
					//				System.out.println(b+"/"+ "c[b] : "+ c[b] + " , " + "i : " + i);
					answer = answer == -1 || answer > i ? i : answer;
					break;
				}
			}


		}
		System.out.println(answer);
	}
	public static void addNew(int[][] c, int odd, int cur, int newCur, Queue<Integer> nextLevel) {
		if(newCur > 500000 || newCur < 0) return;

		if(c[newCur][1 - odd] == -1 || c[newCur][1 - odd] > c[cur][odd] + 1) {
			c[newCur][1 - odd] = c[cur][odd] + 1;
			nextLevel.offer(newCur);	
			nextLevel.offer(1 - odd);
		}
	}
}
