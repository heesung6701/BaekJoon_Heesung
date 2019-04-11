import java.io.File;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//start 19:15
//end	19:55

class Node{
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
}
class Schedule{
	int time;
	char key;
	public Schedule(int time, char key) {
		this.time = time;
		this.key = key;
	}
	@Override
	public String toString() {
		return "("+time+":"+key+")";
	}
}
class Snake{
	private Deque<Node> queue;
	private int dir;
	private Schedule[] schedule;
	private int scheduleIndex = 0;
	private int n;

	@Override
	public String toString() {
		return queue+", dir: "+ dir;
		
	}
	public Snake(Schedule[] schedule,int n) {
		this.schedule = schedule;
		this.n = n;
		queue = new LinkedList<Node>();
		queue.add(new Node(0,0));
		dir = 0;
	}
	public Node getLast() {
		return queue.peekLast();
	}
	public boolean move() {
		if(queue.size() == 0) return false;
		Node last = getLast();
		int nX = last.x + dirX[dir];
		int nY = last.y + dirY[dir];
		if(nX < 0 || nX >= n || nY < 0 || nY >= n) return false;
		for(Node _node : queue) {
			if (nX == _node.x  && nY == _node.y)
				return false;
		}
		queue.addLast(new Node(nX, nY));
		return true;
	}

	public void removeTail() {
		queue.removeFirst();
	}
	public void checkRotate(int time) {
		if(scheduleIndex >= schedule.length) return;
		if(schedule[scheduleIndex].time != time) return;

		switch(schedule[scheduleIndex].key) {
		case 'L':
			dir = rotateL[dir];
			break;
		case 'D':
			dir = rotateR[dir];
			break;
		default:
			try {
				throw new Exception("??");
			}catch (Exception e) {
				e.printStackTrace();
			}

		}
		scheduleIndex++;
	}

	final static int dirY[] = new int[] {1,0,-1,0};  // 2<-  -> 0
	final static int dirX[] = new int[] {0,1,0,-1};  //    1
	final static int rotateR[] = new int[] {1, 2, 3, 0};
	final static int rotateL[] = new int[] {3, 0, 1, 2};
}
class Manager{
	Snake snake;
	int[][] fruits;
	int answer;
	public Manager(Snake snake, int[][] fruits) {
		this.snake = snake;
		this.fruits = fruits;

	}

	public void play() {
		int time = 0;
		while(true) {
			time ++;
			if(!snake.move())
				break;
			Node post = snake.getLast();
			if(fruits[post.x][post.y] == 1) {
				fruits[post.x][post.y] = 0; 
			}else {
				snake.removeTail();
			}
			snake.checkRotate(time);
		}
		answer = time;
	}
}
public class Main {

	public static void main(String[] args) {
		Scanner in  = new Scanner(System.in);

		try { in = new Scanner(new File("input.txt")); } catch(Exception e) {e.printStackTrace(); }

		int n = in.nextInt();
		int k = in.nextInt();
		int i, j, _case;

		int[][] fruit = new int[n][n];
		for (i = 0; i < n; i++) {
			for(j = 0; j < n; j++) {
				fruit[i][j] = 0;
			}
		}
		for(_case = 0; _case < k; _case++) {
			int x = in.nextInt() - 1;
			int y = in.nextInt() - 1;
			fruit[x][y] = 1;
		}
		int l = in.nextInt();
		Schedule[] arr = new Schedule[l];
		for(_case = 0; _case < l; _case ++) {
			int x = in.nextInt();
			char c = in.next().charAt(0);
			arr[_case] = new Schedule(x, c);
		}

		Snake snake = new Snake(arr, n);

		Manager mng = new Manager(snake, fruit);
		mng.play();
		System.out.println(mng.answer);

	}
}
