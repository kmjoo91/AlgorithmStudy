package com.naver.baekjoon.problem10999;

import java.util.Scanner;

public class SegmentTree3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int number = scanner.nextInt();
		int caseNumber = scanner.nextInt() + scanner.nextInt();
		long[] numbers = new long[number];
		for (int i = 0; i < number; i++) {
			numbers[i] = scanner.nextLong();
		}

		long[] segmentTree = createSegmentTree(numbers);
		long[] lazy = new long[segmentTree.length];

		for (int i = 0; i < caseNumber; i++) {
			int select = scanner.nextInt();

			if (select == 1) {
				int from = scanner.nextInt();
				int to = scanner.nextInt();
				long value = scanner.nextLong();

				updateSegmentTree(segmentTree, lazy, 1, 1, numbers.length, value, from, to);
			} else {
				int from = scanner.nextInt();
				int to = scanner.nextInt();

				long sum = search(segmentTree, lazy, 1, 1, numbers.length, from, to);

				System.out.println(sum);
			}
		}
	}

	private static long search(long[] segmentTree, long[] lazy, int node, int left, int right, int from, int to) {
		if (to < left || right < from) {
			return 0;
		}

		propagateLazy(segmentTree,lazy, node, left, right);

		if (from <= left && right <= to) {
			return segmentTree[node];
		}

		int mid = (left + right) / 2;
		int leftChild = node * 2;
		int rightChild = node * 2 + 1;
		long leftSearch = search(segmentTree, lazy, leftChild, left, mid, from, to) + lazy[leftChild] * (mid - left + 1);
		long rightSearch = search(segmentTree, lazy, rightChild, mid + 1, right, from, to) + lazy[rightChild] * (right - (mid + 1) + 1);
		return leftSearch + rightSearch;
	}

	private static void updateSegmentTree(long[] segmentTree, long[] lazy, int node, int left, int right, long value, int from, int to) {
		if (left > to || right < from) {
			return;
		}

		propagateLazy(segmentTree, lazy, node, left, right);

		//현재구간이 update구간보다 작을경우엔 그냥 update하면됨.
		if (from <= left && right <= to) {
			lazy[node] += value;
			propagateLazy(segmentTree, lazy, node, left, right);
			return;
		}

		int mid = (left + right) / 2;
		int leftChild = node * 2;
		int rightChild = node * 2 + 1;

		updateSegmentTree(segmentTree, lazy, leftChild, left, mid, value, from, to);
		updateSegmentTree(segmentTree, lazy, rightChild, mid + 1, right, value, from, to);
		segmentTree[node] = (segmentTree[leftChild] + lazy[leftChild] * (mid - left + 1)) + (segmentTree[rightChild] + lazy[rightChild] * (right - (mid + 1) + 1));
	}

	private static void propagateLazy(long[] segmentTree, long[] lazy, int node, int left, int right) {
		if (lazy[node] == 0) {
			return;
		}
		segmentTree[node] += lazy[node] * (right - left + 1);

		//leaf일땐 쌓일수가없긴하지만...
		if (left == right) {
			lazy[node] = 0;
			return;
		}

		int leftChild = node * 2;
		int rightChild = node * 2 + 1;
		lazy[leftChild] += lazy[node];
		lazy[rightChild] += lazy[node];

		lazy[node] = 0;
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
}
