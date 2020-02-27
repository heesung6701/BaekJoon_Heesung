#include<stdio.h>
#pragma warning(disable:4996)

#define MAX_N 200000

#define max2(a, b) (a > b ? a : b)

int N, C;
int arr[MAX_N];

void sort(int * arr, int size);

bool check(int x) {
	int buf = arr[0];
	int cnt = 1;
	for (int h = 1; h < N; h++) {
		if (arr[h] >= buf + x) {
			cnt++;
			buf = arr[h];
		}
	}
	return cnt >= C;
}

int main() {
	scanf("%d\n%d", &N, &C);
	for (int i = 0; i < N; i++) {
		scanf("%d", &arr[i]);
	}
	sort(arr, N);
	int left = 1;
	int right = arr[N - 1];
	while (left < right) {
		int mid = left + (right - left + 1) / 2;
		if (check(mid)) {
			left = mid;
		}
		else {
			right = mid - 1;
		}
	}
	printf("%d", left);
	return 0;
}

int tmp[MAX_N];
void merge(int *arr, int start, int mid, int end) {
	int h1 = start;
	int h2 = mid + 1;
	for (int h = start; h <= end; h++) {
		int target;
		if (h1 > mid) target = h2++;
		else if (h2 > end) target = h1++;
		else if (arr[h1] < arr[h2]) target = h1++;
		else target = h2++;
		tmp[h] = arr[target];
	}
	for (int h = start; h <= end; h++) {
		arr[h] = tmp[h];
	}
}
void merge_sort(int *arr, int start, int end) {
	if (start == end) return;
	int mid = start + (end - start) / 2;
	merge_sort(arr, start, mid);
	merge_sort(arr, mid + 1, end);
	merge(arr, start, mid, end);
}
void sort(int * arr, int size) {
	merge_sort(arr, 0, size - 1);
}