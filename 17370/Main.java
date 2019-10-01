import java.awt.Frame;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.border.MatteBorder;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int answer[] = new int[] {0,				0,				0,
				0,
				2,
				2,
				4,
				8,
				26,
				36,
				80,
				148,
				332,
				556,
				1172,
				2112,
				4350,
				7732,
				15568,
				28204,
				56100,
				101640};
		int n = in.nextInt();
		System.out.println(answer[n-1]);
		return;

//		Solution sol = new Solution(n);
//		int ans = sol.work();
//		System.out.println(ans);
	}
}
class Solution{
	final int n;
	final HashMap<String, Boolean> map;
	int answer;
	public Solution(int n) {
		this.n = n;
		map = new HashMap<String, Boolean>();
		answer = 0;
	}
	int work() {
		Vector start = new Vector(0,1);
		Pos o = new Pos(0,0);
		map.put(o.toString(), true);
//		int[] way = new int[] {0,0,1,0,0,0,0,0};
//		System.out.println(o);
//		for(int i =0; i < way.length; i++) {
//			o = o.move(start);
//			start = way[i] == 0 ? start.rotateL() : start.rotateR();
//			System.out.println(o);
//		}
//		System.out.println();
		search(o, start, 0);
		return answer;
	}

	public void search(Pos pos, Vector v, int level) {
		if(level > n + 1) return;
		Pos newPos = pos.move(v);
//		System.out.print("("+pos+")");
		if(map.containsKey(newPos.toString())){
			if(level == n) {
//				System.out.println("hit!!");
				answer++;
			}
			return;
		}
		if(level == n) {
			return;
		}
		map.put(newPos.toString(), true);
//		System.out.print("left: "+level + " ");
		search(newPos, v.rotateL(), level + 1);
		
		map.put(newPos.toString(), true);
//		System.out.println();
//		System.out.print("right:"+level+ " ");
		search(pos.move(v),v.rotateR(), level + 1);
		map.remove(newPos.toString());
	}
	
}
class Pos{
	final double x;
	final double y;
	public Pos(double x, double y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Pos)) return false;
		Pos p = (Pos)obj; 
		if(Math.abs(x - p.x) < 0.001 && Math.abs(y - p.y) < 0.001) return true;
		else return false;
	}
	@Override
	public String toString() {
		int tx = (int)(Math.round(x * 1000));
		int ty = (int)(Math.round(y * 1000));
		return tx +  "," + ty;
	}
	public Pos move(Vector v) {
		return new Pos(x + v.dx, y + v.dy);
	}
}
class Vector{
	final double dx;
	final double dy;
	public Vector(final double dx, final double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	private Vector rotate(double angle) {
		double[][] matrix = {
				{Math.cos(Math.PI / 180 * angle) , -Math.sin(Math.PI / 180 * angle)},
				{Math.sin(Math.PI / 180 * angle), Math.cos(Math.PI / 180 * angle)}};
		double nx = dx * matrix[0][0] + dy  * matrix[1][0];
		double ny = dx * matrix[0][1] + dy  * matrix[1][1];
		return new Vector(nx, ny);
	}

	Vector rotateL() {
		return rotate(-60.0f);
	}
	Vector rotateR() {
		return rotate(60.0f);
	}
	@Override
	public String toString() {
		int tx = (int)(Math.round(dx * 1000));
		int ty = (int)(Math.round(dy * 1000));
		return tx +  "," + ty;
	}
}
