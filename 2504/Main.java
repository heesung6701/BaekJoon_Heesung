import java.io.File;
import java.util.Scanner;
import java.util.Stack;


//start 	1:30
//end		1:56
interface Node{
}
class Bracket implements Node{
	char x;
	public Bracket(char x) {
		this.x = x;
	}
	public int isPair(char c) {
		if (x == '[' && c == ']') return 3;
		if (x == '(' && c == ')') return 2;
		return -1;
	}
	@Override
	public String toString() {
		return x+" ";
	}
}
class Num implements Node{
	int x;
	public Num(int x) {
		this.x = x;
	}
	@Override
	public String toString() {
		return x+" ";
	}
}
public class Main {


	static int answer = 0;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
//		try { in = new Scanner(new File("input.txt")); }catch(Exception e) { e.printStackTrace(); }
		
		String data = in.nextLine();

		Stack<Node> stack = new Stack<Node>();
		
		for(int i = 0; i < data.length(); i++) {
			char cur = data.charAt(i);
			switch(cur) {
			case '(':
				stack.push(new Bracket(cur));
				break;
			case '[':
				stack.push(new Bracket(cur));
				break;
			case ')':
			case ']':
				if (pop(stack, cur)== false) {
					System.out.println("0");
					return;
				}
				break;
			}
//			System.out.println(stack);
		}
		System.out.println(answer);		
	}
	public static boolean pop(Stack<Node> stack, char cur) {
		int data = 0;
		while(!stack.isEmpty()) {
			Node tmp = stack.pop();
			if (tmp instanceof Num) {
				data += ((Num) tmp).x;
				continue;
			}
			if (tmp instanceof Bracket) {
				int weight = ((Bracket) tmp).isPair(cur);
				if (weight == -1) {
					return false;
				}
				int v = data == 0 ? weight : weight * data;
				
				if (stack.isEmpty()) {
					answer += v;
				} else {
					stack.push(new Num(v));
				}
				return true;
			}
		}
		return false;
	}
}
