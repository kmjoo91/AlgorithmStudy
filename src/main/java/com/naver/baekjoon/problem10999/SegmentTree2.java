package com.naver.baekjoon.problem10999;

import java.util.Scanner;

public class SegmentTree2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int number = scanner.nextInt();
		int caseNumber = scanner.nextInt() + scanner.nextInt();
		long[] numbers = new long[number];
		for (int i = 0; i < number; i++) {
			numbers[i] = scanner.nextInt();
		}

		long[] segmentTree = createSegmentTree(numbers);
		long[] lazy = new long[segmentTree.length];

		for (int i = 0; i < caseNumber; i++) {
			int select = scanner.nextInt();

			if (select == 1) {
				int from = scanner.nextInt();
				int to = scanner.nextInt();
				int value = scanner.nextInt();

				updateSegmentTree(segmentTree, lazy, 1, 1, numbers.length, value, from, to);
			} else {
				int from = scanner.nextInt();
				int to = scanner.nextInt();

				long sum = search(segmentTree, lazy, 1, 1, numbers.length, from, to);

				System.out.println(sum);
			}
		}
	}

	private static long[] createSegmentTree(long[] numbers) {
		int height = (int)Math.ceil(Math.log(numbers.length) / Math.log(2));
		int maxSize = 2 * (int)Math.pow(2, height);
		long[] tree = new long[maxSize];
		setSegmentTree(numbers, tree, 1, 0, numbers.length - 1);
		return tree;
	}

	private static void setSegmentTree(long[] numbers, long[] segmentTree, int node, int left, int right) {
		if (left == right) {
			segmentTree[node] = numbers[left];
			return;
		}

		int mid = (left + right) / 2;
		int leftChildNode = node * 2;
		int rightChildNode = leftChildNode + 1;

		setSegmentTree(numbers, segmentTree, leftChildNode, left, mid);
		setSegmentTree(numbers, segmentTree, rightChildNode, mid + 1, right);

		segmentTree[node] = segmentTree[leftChildNode] + segmentTree[rightChildNode];
	}

	private static long updateSegmentTree(long[]segmentTree, long[] lazy, int node, int left, int right, long value, int from, int to) {
		if (left > right || from > to) {
			return 0;
		}

		//구간합의 구간과 update구간이 아예 안겹칠 경우 아무작업하지않음.
		if (right < from || left > to) {
			return 0;
		}

		// 구간합의 구간이 update구간보다 더 작을경우 lazy만 더해주고 끝
		if (from <= left && right <= to) {
			lazy[node] += value;
			return value * (right - left + 1);
		}

		//구간합과 구간이 반씩 겹칠경우.
		if (right < to) {
			to = right;
		}

		if (from < left) {
			from = left;
		}

		long before = segmentTree[node];
		//구간합의 구간이 update 구간의 구간보다 더 클 경우(둘다 같은경우는 위에서 걸러짐)
		if (left <= from && to <= right) {
			int mid = (left + right) / 2;
			int leftChildNode = node * 2;
			int rightChildNode = leftChildNode + 1;

			long currentLazy = lazy[node];
			lazy[node] = 0;
			long leftPlus = 0;
			long rightPlus = 0;
			//update 구간이 왼쪽에만 있을때
			if (to <= mid) {
				//왼쪽은 나눠서 ㅋ
				leftPlus = updateSegmentTree(segmentTree, lazy, leftChildNode, left, mid, currentLazy, left, from - 1);
				leftPlus += updateSegmentTree(segmentTree, lazy, leftChildNode, left, mid, currentLazy + value, from, to);
				leftPlus += updateSegmentTree(segmentTree, lazy, leftChildNode, left, mid, currentLazy, to + 1, mid);
				//오른쪽에 그냥 lazy더해준다.
				rightPlus = updateSegmentTree(segmentTree, lazy, rightChildNode, mid + 1, right, currentLazy, mid + 1, right);
			}
			//update 구간이 오른쪽에만 있을때
			else if (mid < from) {
				leftPlus = updateSegmentTree(segmentTree, lazy, leftChildNode, left, mid, currentLazy, left, mid);

				rightPlus = updateSegmentTree(segmentTree, lazy, rightChildNode, mid + 1, right, currentLazy, mid + 1, from - 1);
				rightPlus += updateSegmentTree(segmentTree, lazy, rightChildNode, mid + 1, right, currentLazy + value, from, to);
				rightPlus += updateSegmentTree(segmentTree, lazy, rightChildNode, mid + 1, right, currentLazy, to + 1, right);
			}
			//update 구간이 양쪽 다있을경우
			else {
				leftPlus = updateSegmentTree(segmentTree, lazy, leftChildNode, left, mid, currentLazy, left, from - 1);
				leftPlus += updateSegmentTree(segmentTree, lazy, leftChildNode, left, mid, currentLazy + value, from, mid);

				rightPlus = updateSegmentTree(segmentTree, lazy, rightChildNode, mid + 1, right, currentLazy + value, mid + 1, to);
				rightPlus += updateSegmentTree(segmentTree, lazy, rightChildNode, mid + 1, right, currentLazy, to + 1, right);
			}


			segmentTree[node] += leftPlus + rightPlus;
		}


		return segmentTree[node] - before;
	}

	private static long search(long[] segmentTree, long[] lazy, int node, int left, int right, int from, int to) {
		if (left > right || from > to) {
			return 0;
		}

		//구간합의 구간과 search구간이 아예 안겹칠 경우
		if (right < from || left > to) {
			return 0;
		}

		//여기서부턴 구간이 무조건 겹치긴함.

		//구간이 같을 경우
		if (left == from && right == to) {
			return segmentTree[node] + (lazy[node] * (right - left + 1));
		}

		if (from < left && to <= right) {
			return search(segmentTree, lazy, node, left, right, left, to);
		}

		if (left <= from && right < to) {
			return search(segmentTree, lazy, node, left, right, from, right);
		}


		long leftSum = 0;
		long rightSum = 0;
		if (left <= from && to <= right) {
			int mid = (left + right) / 2;
			int leftChildNode = node * 2;
			int rightChildNode = leftChildNode + 1;

			//이러면 왼쪽만 보면됨~
			if (to <= mid) {
				leftSum = search(segmentTree, lazy, leftChildNode, left, mid, from, to);
			}
			//이러면 오른쪽만 보면됨~
			else if (from > mid) {
				rightSum = search(segmentTree, lazy, rightChildNode, mid + 1, right, from, to);
			}
			//이러면 mid는 to보단 작고, from 보단 크거나 같음
			else {
				leftSum = search(segmentTree, lazy, leftChildNode, left, mid, from, mid) + (lazy[node] * (mid - from + 1));
				rightSum = search(segmentTree, lazy, rightChildNode, mid + 1, right, mid + 1, to) + (lazy[node] * (to - (mid + 1) + 1));
			}
		}

		return leftSum + rightSum;
	}
}
