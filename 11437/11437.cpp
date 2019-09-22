#define DEBUG
#include<stdio.h>
#include<list>
#ifdef DEBUG 
#pragma warning(disable:4996) 
#endif
#define MAX_N 50000
#define min(a, b) (a < b ? a : b)
using namespace std;

int p[MAX_N + 1];
int lv[MAX_N + 1];

list<int> adj[MAX_N + 1];

int q[10000000];
int f = -1, r = -1;

int main() {
	int N, M;
#ifdef DEBUG 
	freopen("input.txt", "r", stdin);
#endif
	//cin >> N;
	scanf("%d", &N);

	int i;
	for (i = 0; i < N - 1; i++) {
		int a, b;
		//cin >> a >> b
		scanf("%d %d", &a, &b);
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	q[++r] = 1;
	lv[1] = 1;
	p[1] = -1;
	while (f < r) {
		int cur = q[++f];

		for (auto next : adj[cur]) {
			if (lv[next] != 0) continue;
			p[next] = cur;
			lv[next] = lv[cur] + 1;
			q[++r] = next;
		}
	}
	//cin >> M;
	scanf("%d", &M);
	int a, b;
	for (i = 0; i < M; i++) {
		//cin >> a >> b;
		scanf("%d %d", &a, &b);
		int common = min(lv[a], lv[b]);
		while (lv[b] > common) { b = p[b]; }
		while (lv[a] > common) { a = p[a]; }
		while (a != b) {
			a = p[a];
			b = p[b];
		}
		printf("%d\n", a);
	}
	return 0;
}