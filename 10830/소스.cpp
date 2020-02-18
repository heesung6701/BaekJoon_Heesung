#include<stdio.h>
#pragma warning(disable:4996)

#define MAX_N 5
#define LIMIT 1000

typedef long long llong;
typedef unsigned long long uint64;
typedef short matrix[MAX_N][MAX_N];

void print_array(short N, matrix arr);

short mul(short a, short b) {
	return ((llong)a * b) % LIMIT;
}
short add(short a, short b) {
	return ((llong)a + b) % LIMIT;

}
void mul_matrix(short N, matrix a, matrix b, matrix res) {
	for (short i = 0; i < N; i++) {
		for (short j = 0; j < N; j++) {
			short sum = 0;
			for (short t = 0; t < N; t++) {
				sum = add(sum, mul(a[i][t], b[t][j]));
			}
			res[i][j] = sum;
		}
	}
}

void be_idle_matrix(short N, matrix a) {
	for (short i = 0; i < MAX_N; i++) {
		for (short j = 0; j < MAX_N; j++) {
			a[i][j] = i == j ? 1 : 0;
		}
	}
}
void copy_matrix(short N, matrix from, matrix to) {
	for (short i = 0; i < MAX_N; i++) {
		for (short j = 0; j < MAX_N; j++) {
			to[i][j] = from[i][j];
		}
	}
}

void pow_matrix(short N, matrix a, uint64 B, matrix answer) {
	int degree = 1;
	matrix res;
	copy_matrix(N, a, res);
	while (B > 0) {
		if (B % 2 == 1) {
			matrix tmp;
			mul_matrix(N, answer, res, tmp);
			copy_matrix(N, tmp, answer);
		}
		B /= 2;
		degree++;
		matrix tmp;
		mul_matrix(N, res, res, tmp);
		copy_matrix(N, tmp, res);
	}
}

void read_array(short N, matrix arr) {
	for (short i = 0; i < N; i++) {
		for (short j = 0; j < N; j++) {
			scanf("%hd", &arr[i][j]);
		}
	}
}

int main() {
	short N;
	uint64 B;
	scanf("%hd %llu", &N, &B);
	matrix a;
	read_array(N, a);
	matrix answer;
	be_idle_matrix(N, answer);
	pow_matrix(N, a, B, answer);
	print_array(N, answer);
	return 0;
}

void print_array(short N, matrix arr) {
	for (short i = 0; i < N; i++) {
		for (short j = 0; j < N; j++) {
			printf("%d ", arr[i][j]);
		}
		printf("\n");
	}
}