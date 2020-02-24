#include<stdio.h>
#pragma warning(disable:4996)

#define MAX_K 10000
#define max2(a, b) (a > b ? a : b)

typedef unsigned int uint;

uint arr[MAX_K];
int N, K;
uint check(uint x) {
	uint sum = 0;
	for (int i = 0; i < K; i++) {
		sum += arr[i] / x;
	}
	return sum;
}
int main() {
	scanf("%d %d", &K, &N);
	uint max = 0;
	for (int i = 0; i < K; i++) {
		scanf("%u", &arr[i]);
		max = max2(max, arr[i]);
	}
	uint left = 1;
	uint right = max;
	while (left < right) {
		uint mid = left + (right - left + 1) / 2;
		int value = check(mid);
		if (value < N) {
			right = mid - 1;
			continue;
		}
		else {
			left = mid;
		}
	}
	printf("%u", left);
	return 0;
}
