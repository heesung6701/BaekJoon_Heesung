import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


//start 19:10??
//end

class Node{
	int x;
}

class Num extends Node{
	public Num(int x) {
		this.x = x;
	}
	@Override
	public String toString() {
		return this.x+ ""; 
	}
}

class Oper extends Node{
}
class Add extends Oper{
	public Add(int x) {
		this.x = x;
	}
	@Override
	public String toString() {
		return this.x == 1 ? "+" : "-"; 
	}
}
class Mul extends Oper{
	public Mul(int x) {
		this.x = x;
	}
	@Override
	public String toString() {
		return this.x == 1 ? "*" : "/"; 
	}
}
public class Main {

	static int[] oper = new int[4];
	static int n;
	static int[] arr;
	static int max = -2000000000 ;
	static int min = 2000000000;

	static ArrayList<Node> list = new ArrayList<>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		try { in = new Scanner(new File("input.txt")); } catch(Exception e) { e.printStackTrace(); }

		int i, j;
		n = in.nextInt();

		arr = new int[n];
		for (i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}

		for (i = 0; i < 4; i++) 
			oper[i] = in.nextInt();

		list.add(new Num(arr[0]));
		dfs(0);
		System.out.println(max);
		System.out.println(min);
	}

	static void dfs(int level) {
		int i;
		if (level == n - 1) {
			int result = 0;

			ArrayList<Node> list = new ArrayList<>();
			list.addAll(Main.list);
//			System.out.println(list);
			for (i = 0; i < list.size(); i++) {
				if ((list.get(i) instanceof Mul)){


					Node after = list.remove(i + 1);
					Node dir = list.remove(i);
					Node before = list.remove(i - 1);
					//				System.out.println(before.x + " (mul : "+ dir.x + " )" + after.x);
					int acc = before.x;

					switch(dir.x) {
					case 1:
						acc *= after.x;
						break;
					case -1:
						acc /= after.x;
						break;
					default:
						try {
							throw new Exception("????");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					list.add(i-1,new Num(acc));	
					i--;
				}

				if ((list.get(i) instanceof Add)) {
					Node after = list.remove(i + 1);
					Node dir = list.remove(i);
					Node before = list.remove(i - 1);

					int acc = before.x + after.x * dir.x;

					list.add(i-1,new Num(acc));	
					i--;
				}
			}
			if(list.size() != 1) {
				try {
					throw new Exception("???? whit list size is not 1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			result = list.get(0).x;
			max = max > result ? max : result;
			min = min > result ? result : min;
			return;
		}
		for (i = 0 ; i < 4; i++) {
			if (oper[i] == 0) continue;
			Node tmp = null;
			Node next = new Num(arr[level + 1]);
			switch(i) {
			case 0:
				tmp = new Add(1);
				break;
			case 1:
				tmp = new Add(-1);
				break;
			case 2:
				tmp = new Mul(1);
				break;
			case 3:
				tmp = new Mul(-1);
				break;
			default:
				try {
					throw new Exception("????");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			oper[i] --;
			list.add(tmp);
			list.add(next);
			dfs(level + 1);
			oper[i] ++;
			list.remove(tmp);
			list.remove(next);
		}
	}
}
