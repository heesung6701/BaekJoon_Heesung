#define DEBUG 
#include<iostream>
#define MAX_N 100
#define MAX_Q MAX_N * MAX_N
#define max(a, b) (a > b ? a : b)
#define min(a, b) (a < b ? a : b)

#ifdef DEBUG
#pragma warning(disable:4996)
#endif

using namespace std;

typedef struct Pos {
	short x, y;
} Pos;
short N;

short dirX[] = { 1, 0 };
short dirY[] = { 0, 1 };
short dirSize = 2;

short arr[MAX_N][MAX_N];
long long d[MAX_N][MAX_N];
Pos q[MAX_Q];
short f, r;

void init() {
	f = -1;
	r = -1;
}

int main() {
#ifdef DEBUG
	freopen("input.txt", "r", stdin);
	//freopen("output.txt", "w", stdout);
#endif
	cin >> N;
	short i, j;
	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}
	init();
	d[0][0] = 1;
	for (short rad = 0; rad < N * 2 -2; rad++) {
		for (i = 0; i < min(rad + 1,N); i++) {
			if (rad - i >= N || rad - i < 0) continue;
			short x = i;
			short y = rad - i;
			q[++r] = { x, y };
		}
	}
	while (f < r) {
		Pos cur = q[++f];
		if (d[cur.x][cur.y] == 0) continue;
		if (arr[cur.x][cur.y] == 0) continue;

		for (i = 0; i < dirSize; i++) {
			short nX = cur.x + dirX[i] * arr[cur.x][cur.y];
			short nY = cur.y + dirY[i] * arr[cur.x][cur.y];
			if (nX < 0 || nY < 0 || nX >= N || nY >= N) continue;
			d[nX][nY] = d[nX][nY] + d[cur.x][cur.y];
		}

#ifdef DEBUG2
		cout << cur.x << ", " << cur.y << endl;
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				printf("%5d ", d[i][j]);
			}
			cout << endl;
		}
		cout << endl;
#endif
	}
	if (d[N - 1][N - 1] == 0) cout << "-1";
	else cout << d[N - 1][N - 1];
	return 0;
}