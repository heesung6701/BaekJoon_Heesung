import java.io.File;
import java.util.Scanner;


//start 19:30
//end 	20:21
public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		try { in = new Scanner(new File("input.txt")); } catch (Exception e) { e.printStackTrace(); }
		int i , j;
		int[] arr = new int[25];
		for(i = 1; i <= 24; i ++) {
			arr[i] = in.nextInt();
		}
		
		initLeft();
		initRight();
		initUp();
		initDown();
		initFront();
		initBack();
		
		int answer = 0;
		if(rotate(arr, left)) {
			answer = 1;
		}
		if(rotate(arr, right)) {
			answer = 1;
		}
		if(rotate(arr, up)) {
			answer = 1;
		}
		if(rotate(arr, down)) {
			answer = 1;
		}
		if(rotate(arr, front)) {
			answer = 1;
		}

		if(rotate(arr, back)) {
			answer = 1;
		}
		System.out.println(answer);
		in.close();
		return;
	}
	public static boolean rotate(int[] ori, int[] next) {
		int[] arr = move(ori, next);
		if(check(arr)) 
			return true;
		int[] arr2 = move(arr, next);
		int[] arr3 = move(arr2, next);
		if(check(arr3))
			return true;
		return false;
	}
	public static int[] move(int[] arr, int[] next) {
		int i;
		int[] leftArr = new int[25];
		for(i = 1; i <= 24; i++) {
			leftArr[i] = arr[next[i]];
		}
		return leftArr;
	}
	public static boolean check(int[] arr) {
		int i, j;
		for(i = 1; i <= 24; i+=4) {
			int pivot = arr[i];
			for(j = i + 1; j <= i + 3; j ++) {
				if(pivot != arr[j])
					break;
			}
			if(j <= i +3 ) {
				break;
			}
		}
		return i > 24; 
	}
	public static int[] copy(int[] arr) {
		int[] newOne = new int[arr.length];
		for(int i = 0 ; i < arr.length; i++) {
			newOne[i] =arr[i];
		}
		return newOne;
	}
//	public static void print(int[] arr) {
//		int i;
//		System.out.println("\t"+arr[1] + " " + arr[2]);
//		System.out.println("\t"+arr[3] + " " + arr[4]);
//		
//		System.out.println();
//		int[] show = new int[] {13, 5,17, 21};
//		for(i = 0; i < show.length; i++) System.out.print(arr[show[i]] + " " + arr[show[i]+1] + "\t");
//		System.out.println();
//		for(i = 0; i < show.length; i++) System.out.print(arr[show[i]+2] + " " + arr[show[i]+3]+ "\t");
//		System.out.println();
//		System.out.println();
//		System.out.println("\t"+arr[9] + " " + arr[10]);
//		System.out.println("\t"+arr[11] + " " + arr[12]);
//	
//	}
	public static int[] init() {
		int[] c = new int[25];
		int i;
		for(i = 1; i <=24; i++) 
			c[i] = i;
		return c;
	}
	public static int left[];
	public static void initLeft() {
		left = init();
		left[1] = 5;
		left[3] = 7;
		left[5] = 9;
		left[7] = 11;
		left[9] = 24;
		left[11] = 22;
		left[24] = 1;
		left[22] = 3;
	}
	public static int right[];
	public static void initRight() {
		right = init();
		right[2] = 6;
		right[4] = 8;
		right[6] = 10;
		right[8] = 12;
		right[10] = 23;
		right[12] = 21;
		right[23] = 2;
		right[21] = 4;
	}

	public static int up[];
	public static void initUp() {
		up = init();
		up[5] = 17;
		up[6] = 18;
		up[17] = 21;
		up[18] = 22;
		up[21] = 13;
		up[22] = 14;
		up[13] = 5;
		up[14] = 6;
	}

	public static int down[];
	public static void initDown() {
		down = init();
		down[7] = 19;
		down[8] = 20;
		down[19] = 23;
		down[20] = 24;
		down[23] = 15;
		down[24] = 16;
		down[15] = 7;
		down[16] = 8;
	}
	public static int front[];
	public static void initFront() {
		front = init();
		front[3] = 17;
		front[4] = 19;
		front[17] = 10;
		front[19] = 9;
		front[10] = 16;
		front[9] = 14;
		front[16] = 3;
		front[14] = 4;
		front[5] = 6;
		front[6] = 8;
		front[8] = 7;
		front[7] = 5;
	}

	public static int back[];
	public static void initBack() {
		back = init();
		back[21]= back[22];
		back[22] = back[24];
		back[24] = back[23];
		back[23] = back[21];
		back[1] = 15;
		back[2] = 13;
		back[15] = 12;
		back[13] = 11;
		back[12] = 18;
		back[11] = 20;
		back[18] = 1;
		back[20] = 2;
	}


}
