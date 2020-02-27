#include<stdio.h>
#pragma warning(disable:4996)

int N, K;

int check(int x) {
	int cnt = 0;
	for (int i = 1; i <= N; i++) {
		int val = x / i;
		if (val > N) val = N;
		cnt += val;
	}
	return cnt;
}
int main() {
	scanf("%d\n%d", &N, &K);

	int left = 1;
	int right = K;
	while (left < right) {
		int mid = left + (right - left) / 2;
		int idx = check(mid);
		if (idx >= K) {
			right = mid;
		}
		else {
			left = mid + 1;
		}
	}
	printf("%d", right);
	return 0;
}
