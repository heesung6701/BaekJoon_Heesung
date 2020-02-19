#include<stdio.h>
#pragma warning(disable:4996)

#define MAX_N 100000
#define UN_VALID -1
#define max2(a, b) (a > b ? a : b)
#define min2(a, b) (a > b ? b : a)

typedef unsigned long long uint;
int N;
int arr[MAX_N];

uint ans;
int heap[MAX_N * 20];
struct Range {
	int s;
	int e;
	bool isSame(Range _r) {
		return _r.s == s && _r.e == e;
	}
	void update(Range find) {
		s = max2(s, find.s);
		e = min2(e, find.e);
	}
};

int make_tree(int x, Range range) {
	if (range.s == range.e) {
		heap[x] = range.s;
		return range.s;
	}
	int mid = range.s + (range.e - range.s) / 2;
	int left = make_tree(x * 2, { range.s, mid });
	int right = make_tree(x * 2 + 1, { mid + 1 , range.e });
	if (arr[left] < arr[right]) {
		heap[x] = left;
		return left;
	}
	else {
		heap[x] = right;
		return right;
	}
}
int trim(int a, int b) {
	return 0;
}

int get_tree(int x, Range find, Range range) {
	if (find.s > find.e) return UN_VALID;
	if (range.s == find.s && find.e == range.e) {
		return heap[x];
	}
	int mid = range.s + (range.e - range.s) / 2;
	Range left_range = {range.s, mid };
	Range right_range = { mid  + 1, range.e};

	Range find_left = find;
	find_left.update(left_range);
	Range find_right = find;
	find_right.update(right_range);
	int left = get_tree(x * 2, find_left, left_range);
	int right = get_tree(x * 2 + 1, find_right, right_range);

	if (left == UN_VALID && right == UN_VALID) return UN_VALID;
	if (left == UN_VALID) return right;
	if (right == UN_VALID) return left;
	if (arr[left] < arr[right]) {
		return left;
	}
	else {
		return right;
	}
}
void min_idx_init() {
	make_tree(1, { 0, N - 1 });
}
int min_idx(int s, int e) {
	return get_tree(1, { s, e }, { 0, N - 1 });
}
void divide_and_conquest(int s, int e) {
	if (s > e) return;
	if (s == e) {
		ans = (uint)max2(ans, arr[s]);
		return;
	}
	int min_i = min_idx(s, e);
	int min = arr[min_i];
	int w = e - s + 1;
	ans = max2(ans, (uint)w * min);
	divide_and_conquest(s , min_i - 1);
	divide_and_conquest(min_i + 1, e);
}
int main() {
	while (true) {
		scanf("%d", &N);
		if (N == 0) break;
		for (int i = 0; i < N; i++) {
			scanf("%d", &arr[i]);
		}
		min_idx_init();
		ans = 0;
		divide_and_conquest(0, N - 1);
		printf("%llu\n", ans);
	}
	return 0;
}