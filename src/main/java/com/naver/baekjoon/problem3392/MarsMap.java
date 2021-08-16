package com.naver.baekjoon.problem3392;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MarsMap {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int mapNumber = scanner.nextInt();

		List<Node> nodeList = new ArrayList<>();

		int high = Integer.MIN_VALUE;
 		for (int i = 0; i < mapNumber; i++) {
			int x1 = scanner.nextInt();
			int y1 = scanner.nextInt();
			int x2 = scanner.nextInt();
			int y2 = scanner.nextInt();

			if (high < y2) {
				high = y2;
			}

			nodeList.add(new Node(x1, y1, y2, true));
			nodeList.add(new Node(x2, y1, y2, false));
		}

		int[] counts = new int[high];

 		nodeList.sort(Comparator.comparing(Node::getX));

 		int value = 0;
		int beforeX = 0;
		int result = 0;
 		for (Node node : nodeList) {
			result += (node.getX() - beforeX) * value;

 			//start일때.
			if (node.isStart()) {
				for (int i = node.getLow(); i < node.getHigh(); i++) {
					counts[i]++;

					if (counts[i] == 1) {
						value++;
					}
				}
			}
			//end일때
			else {
				for (int i = node.getLow(); i < node.getHigh(); i++) {
					counts[i]--;

					if (counts[i] == 0) {
						value--;
					}
				}
			}

			beforeX = node.getX();
		}

		System.out.println(result);
	}
}

class Node {
	private int x;
	private int low;
	private int high;
	private boolean isStart;

	public Node(int x, int low, int high, boolean isStart) {
		this.x = x;
		this.low = low;
		this.high = high;
		this.isStart = isStart;
	}

	public int getX() {
		return x;
	}

	public int getLow() {
		return low;
	}

	public int getHigh() {
		return high;
	}

	public boolean isStart() {
		return isStart;
	}
}

