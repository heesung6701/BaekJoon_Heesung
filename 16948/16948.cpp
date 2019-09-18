#define DEBUG 10

#include<iostream>
#define MAX_N 200
#define MAX_Q 4000000

#ifdef DEBUG
#pragma warning(disable:4996)
#endif

using namespace std;

typedef struct Pos {
	int x, y;
} Pos;
int N;

short dirX[] = { -2, -2, 0, 0, 2, 2 };
short dirY[] = { -1, 1, -2, 2, -1, 1 };
short dirSize = 6;

int d[MAX_N][MAX_N];
Pos q[MAX_Q];
int f, r;

void init() {
	f = -1;
	r = -1;
}

int main() {
#ifdef DEBUG
	freopen("input.txt", "r", stdin);
#endif
	cin >> N;
	short sx, sy, ex, ey;
	short i;
	cin >> sx >> sy >> ex >> ey;

	Pos to = { ex, ey };

	init();
	q[++r] = { sx, sy };
	while (f < r) {
		Pos cur = q[++f];
		for (i = 0; i < dirSize; i++) {
			short nX = cur.x + dirX[i];
			short nY = cur.y + dirY[i];
			if (nX < 0 || nY < 0 || nX >= N || nY >= N) continue;
			if (d[nX][nY] != 0 && d[nX][nY] <= d[cur.x][cur.y] + 1) continue;
			d[nX][nY] = d[cur.x][cur.y] + 1;
			q[++r] = { nX, nY };
		}
	}
	if (d[ex][ey] == 0) cout << "-1" << endl;
	else cout << d[ex][ey] << endl;
	return 0;
}