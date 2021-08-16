package com.naver.kakao.year2018;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/15958
 */
public class ProdoPresent {
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int X = 0;
	private static final int Y = 1;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int number = scanner.nextInt();

		//원점 버려
		scanner.nextInt();
		scanner.nextInt();

		long[][] pointList = new long[number - 2][2];
		for (int i = 0; i < number - 2; i++) {
			pointList[i][X] = scanner.nextInt();
			pointList[i][Y] = scanner.nextInt();
		}

		//마지막점 버려
		scanner.nextInt();
		scanner.nextInt();

		int[] lowestIndexTree = createLowestIndexTree(pointList, pointList.length - 1);

		List<long[][]> squareList = new ArrayList<>();

		long result = calculate(pointList, lowestIndexTree, squareList, 0, pointList.length - 1);
		System.out.println(result);
	}

	private static int[] createLowestIndexTree(long[][] pointList, int length) {
		int x = (int)Math.ceil(Math.log(pointList.length) / Math.log(2));
		int max_size = 2 * (int)Math.pow(2, x);
		int[] segmentTree = new int[max_size];
		setSegment(pointList, segmentTree, 1, 0, length);
		return segmentTree;
	}

	private static void setSegment(long[][] pointList, int[] segmentTree, int node, int left, int right) {
		if (left == right) {
			segmentTree[node] = left;
			return;
		}

		int mid = (left + right) / 2;
		int leftChildNode = node * 2;
		int rightChildNode = leftChildNode + 1;

		setSegment(pointList, segmentTree, leftChildNode, left, mid);
		setSegment(pointList, segmentTree, rightChildNode, mid + 1, right);

		if (pointList[segmentTree[leftChildNode]][Y] > pointList[segmentTree[rightChildNode]][Y]) {
			segmentTree[node] = segmentTree[rightChildNode];
		} else {
			segmentTree[node] = segmentTree[leftChildNode];
		}
	}

	private static long calculate(long[][] pointList, int[] segmentTree, List<long[][]> squareList, int from, int to) {
		if (from > to || from < 0 || to >= pointList.length) {
			return 0;
		}

		int lowestIndex = getLowestIndex(segmentTree, pointList, 1, 0, pointList.length - 1, from, to);

		if (lowestIndex % 2 == 1) {
			lowestIndex--;
		}

		long[] lowestPoint = pointList[lowestIndex];

		long[] leftPoint = new long[2];
		leftPoint[X] = pointList[from][X];
		leftPoint[Y] = lowestPoint[Y];

		long[] rightPoint = new long[2];
		rightPoint[X] = pointList[to][X];
		rightPoint[Y] = lowestPoint[Y];

		long currentResult = leftPoint[Y] * (rightPoint[X] - leftPoint[X]);

		long[][] square = new long[2][2];
		square[LEFT] = leftPoint;
		square[RIGHT] = rightPoint;

		List<long[][]> nextSquareList = new ArrayList<>(squareList);
		nextSquareList.add(square);

		for (long[][] lowerSquare : squareList) {
			if (lowerSquare[LEFT][Y] == lowestPoint[Y]) {
				continue;
			}

			long squareArea = rightPoint[Y] * (rightPoint[X] - leftPoint[X]);

			long rightLine = lowerSquare[RIGHT][X] - rightPoint[X];
			long leftLine = leftPoint[X] - lowerSquare[LEFT][X];

			long current = squareArea + (lowerSquare[LEFT][Y] * Math.max(rightLine, leftLine));


			if (current > currentResult) {
				currentResult = current;
			}
		}


		if (to - from < 2) {
			return currentResult;
		}

		long leftResult = calculate(pointList, segmentTree, nextSquareList, from, lowestIndex - 1);
		long rightResult = calculate(pointList, segmentTree, nextSquareList, lowestIndex + 2 , to);

		return Math.max(currentResult, Math.max(leftResult, rightResult));
	}

	private static int getLowestIndex(int[] segmentTree, long[][] pointList, int node, int from, int to, int left, int right) {
		if (left > to || right < from) {
			return -1;
		}

		if (left <= from && to <= right) {
			return segmentTree[node];
		}

		int mid = (from + to) / 2;
		int leftChildNode = node * 2;
		int rightChildNode = leftChildNode + 1;

		int leftIndex = getLowestIndex(segmentTree, pointList, leftChildNode, from, mid, left, right);
		int rightIndex = getLowestIndex(segmentTree, pointList, rightChildNode, mid + 1, to, left, right);

		if (leftIndex == -1) {
			return rightIndex;
		} else if (rightIndex == -1) {
			return leftIndex;
		} else {
			return pointList[leftIndex][Y] < pointList[rightIndex][Y] ? leftIndex : rightIndex;
		}
	}
}