import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

enum Result{unable, able, success};


class Pan{
	public static Result left(State state) {
		return move(state, 0, -1);
	}

	public static Result right(State state) {
		return move(state, 0, 1);
	}

	public static Result up(State state) {
		return move(state, -1, 0);
	}

	public static Result down(State state) {
		return move(state, 1, 0);
	}

	public static Result move(State state, int di, int dj) {

		Node red = state.red;
		Node blue = state.blue;
		Node _red = red.copy();
		Node _blue = blue.copy();

		Result result = Result.able;

		boolean lockRed = false;
		boolean lockBlue = false;
		boolean holeRed = false;
		boolean holeBlue = false;
		while (!lockRed || !lockBlue) {

			switch (Main.map[red.x + di][red.y + dj]) {
			case 'O':
				if(lockRed)
					break;
				holeRed = true;
				red.x += di;
				red.y += dj;
				lockRed = true;
				break;
			case '.':
				red.x += di;
				red.y += dj;
				if(red.isCollision(blue)) {
					red.x -= di;
					red.y -= dj; 
					lockRed = true;
				}else {
					lockRed =false;
				}
				break;
			case '#':
				lockRed = true;
				break;
			}

			switch (Main.map[blue.x + di][blue.y + dj]) {
			case 'O':
				if(lockBlue)
					break;
				holeBlue = true;
				blue.x += di;
				blue.y += dj;
				lockBlue = true;
				break;
			case '.':
				blue.x += di;
				blue.y += dj;

				if(red.isCollision(blue)) {
					blue.x -= di;
					blue.y -= dj; 
					lockBlue = true;
				}else {
					lockBlue = false;
				}
				break;
			case '#':
				lockBlue = true;
				break;
			}

		}
		if(holeRed == true) 
			result = Result.success;
		if(holeBlue == true)
			result = Result.unable;

		if(result == Result.able && red.isCollision(_red) && blue.isCollision(_blue))
			result = Result.unable;

		return result;
	}
}
class State{
	Node red;
	Node blue;
	int level;

	public State(Node red, Node blue, int level) {
		this.red = red;
		this.blue = blue;
		this.level = level;
	}
	public State copy() {
		return new State(red.copy(), blue.copy(), level);
	}
	@Override
	public String toString() {
		return level + "[R:"+red + ", B:" + blue + "]";
	}
}
class Node{
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y =y;
	}

	public boolean isCollision(Node _node) {
		return (x == _node.x && y == _node.y);
	}
	public Node copy() {
		return new Node(x,y);
	}
	@Override
	public String toString() {
		return "("+x + ", " + y+")";
	} 
}
public class Main {


	static char[][] map;
	static int n, m;
	static int answer = -1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		try { in = new Scanner(new File("input.txt")); } catch(Exception e) {}
		int i, j;
		n = in.nextInt();
		m = in.nextInt();
		in.nextLine();
		map = new char[n][m];
		Node red = null;
		Node blue = null;
		for (i = 0; i < n; i++) {
			String input = in.nextLine();
			for (j = 0; j < m; j++) {
				map[i][j] = input.charAt(j);

				switch(map[i][j]) {
				case 'B':
					blue = new Node(i,j);
					map[i][j] = '.';
					break;
				case 'R':
					red = new Node(i,j);
					map[i][j] = '.';
					break;
				}

			}
		}
		State state = new State(red, blue, 0);
		dfs(state);

		System.out.println(answer);
	}
	public static void dfs(State start) {

		Queue<State> queue = new LinkedList<State>();
		queue.add(start);
		while(queue.size() > 0) {
			State state = queue.poll();
			if(state.level >= 10) continue;


			String[] sss = new String[] { "left", "right", "top","down"};
			for(int i = 0; i <= 3; i++) {
				Result result = Result.unable;

				State _state = state.copy();
				switch(i) {
				case 0:
					result = Pan.left(_state); 
					break;
				case 1:
					result = Pan.right(_state);
					break;
				case 2:
					result = Pan.up(_state);
					break;
				case 3:
					result = Pan.down(_state);
					break;
				}

				switch(result) {
				case unable:
					break;
				case success:
					answer = state.level + 1;
					return;
				case able:
					_state.level += 1;
					queue.add(_state);
					break;
				}	
			}	
		}
	}
}
