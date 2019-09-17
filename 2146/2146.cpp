#define DEBUG 10

#include<iostream>
#define MAX_N 100
#define MAX_QUEUE MAX_N * MAX_N * 20

#ifdef DEBUG
#pragma warning(disable:4996)
#endif
using namespace std;


typedef struct Node {
	short x;
	short y;
} Node;

short N;
short arr[MAX_N][MAX_N];
short group[MAX_N][MAX_N];
short groupIdx;
short bridge[MAX_N][MAX_N];

Node queue[MAX_QUEUE];
int f, r;

short dirX[] = { 0, -1, 1, 0 };
short dirY[] = { -1, 0, 0, 1 };
const short dirSize = 4;
void init() {
	groupIdx = 1;
	f = -1;
	r = -1;
}

Node qq[MAX_QUEUE];
void findLand(short x, short y, short idx) {

	int ff, rr;
	ff = rr = -1;

	qq[++rr] = { x, y };
	while (ff < rr) {
		short x = qq[++ff].x;
		short y = qq[ff].y;
		bool moveable = false;
		for (int k = 0; k < dirSize; k++) {
			short nX = x + dirX[k];
			short nY = y + dirY[k];
			if (nX < 0 || nY < 0 || nX >= N || nY >= N) continue;
			if (arr[nX][nY] == 0) {
				moveable = true;
				continue;
			}
			if (group[nX][nY] != 0) continue;
			group[nX][nY] = idx;
			qq[++rr] = { nX, nY };
		}
		queue[++r] = { x, y };
	}
}

short expandLand(short x, short y) {
	int idx = group[x][y];
	for (int k = 0; k < dirSize; k++) {
		short nX = x + dirX[k];
		short nY = y + dirY[k];
		if (nX < 0 || nY < 0 || nX >= N || nY >= N) continue;
		if (group[nX][nY] == idx) continue;
		if (arr[nX][nY] > 0) {
			throw "error occur";
			return bridge[x][y];
		}
		if (group[nX][nY] > 0) return bridge[nX][nY] + bridge[x][y];
		group[nX][nY] = idx;
		bridge[nX][nY] = bridge[x][y] + 1;
		queue[++r] = { nX, nY };
	}
	return -1;
}

#ifdef DEBUG
void printArr(short tmp[MAX_N][MAX_N]) {
	short i, j;
	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			cout << tmp[i][j] << " ";
		}
		cout << endl;
	}
	cout << endl;
}
#endif
int main() {
#ifdef DEBUG
	freopen("input.txt", "r", stdin);
#endif // DEBUG

	short i, j;
	cin >> N;
	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}
	init();

	for (i = 0; i < N; i++) {
		for (j = 0; j < N; j++) {
			if (arr[i][j] == 0) continue;
			if (group[i][j] != 0) continue;
			group[i][j] = groupIdx;
			findLand(i, j, groupIdx);
			groupIdx++;
		}
	}
	//printArr(group);
	int time = 1;
	int answer = -1;
	while (true) {
		while (f < r) {
			Node cur = queue[++f];
			short result = expandLand(cur.x, cur.y);
			if (result == -1) continue;
			answer = (answer == -1 || answer > result ) ? result : answer;
			if (answer == time * 2 - 1) break;
		}
		if (answer > -1) break;
		time++;
		if (time > 100) throw "Error";
	}
	cout << answer;
	return 0;
}