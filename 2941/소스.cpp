#include<stdio.h>
#pragma warning(disable:4996)

struct Node {
	int x;
	Node* child[28];
};

Node root;
Node unable;

Node pool[1000];
int poolIdx = 0;

void initNode(Node *nxt) {
	nxt->x = 0;
	for (int i = 0; i < 28; i++) {
		nxt->child[i] = &unable;
	}
}

Node* makeNode() {
	Node *nxt = &(pool[poolIdx++]);
	initNode(nxt);
	return nxt;
}

int getNum(char c) {
	int nxt;
	if (c == '-') nxt = 26;
	else if (c == '=') nxt = 27;
	else nxt = c - 'a';
	return nxt;
}

void init() {
	root.x = 0;
	for (int i = 0; i < 28; i++) {
		root.child[i] = makeNode();
		root.child[i]->x = 1;
	}
	initNode(&unable);
	char croatiaStr[][4] = { "c=", "c-" , "dz=", "d-","lj","nj", "s=", "z=" };
	for (int i = 0; i < sizeof(croatiaStr) / sizeof(croatiaStr[0]); i++) {
		Node *cur = &root;
		for (int j = 0; croatiaStr[i][j] != '\0'; j++) {
			int nxt = getNum(croatiaStr[i][j]);

			if (cur -> child[nxt] == &unable) {
				cur->child[nxt] = makeNode();
				cur->child[nxt]->x = cur->x + 1;
			}
			cur = cur->child[nxt];
		}
		cur->x = 1;
	}
}

int main() {
	char str[101];
	init();
	scanf("%s", str);	
	Node *cur = &root;
	int cnt = 0;
	for (int i = 0; str[i] != '\0'; i++) {
		int nxt = getNum(str[i]);
		if (cur->child[nxt] == &unable) {
			cnt += cur->x;
			cur = root.child[nxt];
			continue;
		}
		cur = cur->child[nxt];
	}
	cnt += cur->x;
	printf("%d", cnt);
	return 0;
}