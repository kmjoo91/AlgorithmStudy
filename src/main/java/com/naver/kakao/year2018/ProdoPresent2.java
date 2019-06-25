package com.naver.kakao.year2018;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/15958
 */
public class ProdoPresent2 {
	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int X = 0;
	private static final int Y = 1;
	private static Map<Integer, Map<Integer, Long>> MEMOIZATION = new HashMap<>();

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

		long result = calculate(pointList, lowestIndexTree, 0, pointList.length - 1);
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

	private static long calculate(long[][] pointList, int[] segmentTree, int from, int to) {
		if (from > to || from < 0 || to >= pointList.length) {
			return 0;
		}

		int lowestIndex = getLowestIndex(segmentTree, pointList, 1, 0, pointList.length - 1, from, to);

		if (lowestIndex % 2 == 1) {
			lowestIndex--;
		}

		long left = maxArea(pointList, lowestIndex, segmentTree, from, to, LEFT);
		long right = maxArea(pointList, lowestIndex + 1, segmentTree, from, to, RIGHT);
		long leftSubArea = calculate(pointList, segmentTree, from, lowestIndex - 1);
		long rightSubArea = calculate(pointList, segmentTree, lowestIndex + 2, to);

		return Math.max(Math.max(left, right), Math.max(leftSubArea, rightSubArea));
	}

	private static long maxArea(long[][] pointList, int standardIndex, int[] segmentTree, int from, int to, int direction) {
		if (from >= to) {
			return 0;
		}

		if (direction == LEFT) {
			long rightArea = pointList[standardIndex][Y] * (pointList[to][X] - pointList[standardIndex][X]);

			long result = 0;
			int left = from;
			int right = standardIndex - 1;

			while (left < right) {
				Map<Integer, Long> rightMap = MEMOIZATION.get(left);

				if (rightMap == null) {
					rightMap = new HashMap<>();
				}

				Long area = rightMap.get(right);

				if (area != null) {
					if (area > result) {
						result = area;
					}

					return result + rightArea;
				}


				int lowestIndex = getLowestIndex(segmentTree, pointList, 1, 0, pointList.length - 1, left, right);

				if (lowestIndex % 2 == 1) {
					lowestIndex--;
				}

				long currentArea = pointList[lowestIndex][Y] * (pointList[right][X] - pointList[left][X]);

				rightMap.put(right, currentArea);
				MEMOIZATION.put(left, rightMap);

				if (currentArea > result) {
					result = currentArea;
				}

				left = lowestIndex + 1;
			}

			return result + rightArea;
		} else {
			long leftArea = pointList[standardIndex][Y] * (pointList[standardIndex][X] - pointList[from][X]);

			long result = 0;
			int left = standardIndex + 1;
			int right = to;

			while (left < right) {
				Map<Integer, Long> rightMap = MEMOIZATION.get(left);

				if (rightMap == null) {
					rightMap = new HashMap<>();
				}

				Long area = rightMap.get(right);

				if (area != null) {
					if (area > result) {
						result = area;
					}

					return result + leftArea;
				}


				int lowestIndex = getLowestIndex(segmentTree, pointList, 1, 0, pointList.length - 1, left, right);

				if (lowestIndex % 2 == 1) {
					lowestIndex--;
				}

				long currentArea = pointList[lowestIndex][Y] * (pointList[right][X] - pointList[left][X]);

				rightMap.put(right, currentArea);
				MEMOIZATION.put(left, rightMap);

				if (currentArea > result) {
					result = currentArea;
				}

				right = lowestIndex - 1;
			}

			return result + leftArea;
		}
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