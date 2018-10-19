package com.naver.baekjoon.problem2357;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2357
 */
public class MinMaxNumber {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String[] condition = scanner.nextLine().split(" ");

		int condition1 = Integer.parseInt(condition[0]);

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < condition1; i++) {
			list.add(scanner.nextInt());
		}

		scanner.nextLine();
		SegmentTree segmentTree = new SegmentTree(list);
		int condition2 = Integer.parseInt(condition[1]);
		for (int i = 0; i < condition2; i++) {
			String[] range = scanner.nextLine().split(" ");

			int left = Integer.parseInt(range[0]) - 1;
			int right = Integer.parseInt(range[1]) - 1;

			System.out.println(segmentTree.getMin(left, right) + " " + segmentTree.getMax(left, right));
		}
	}

	private static void memoryOver() {
		Scanner scanner = new Scanner(System.in);

		String[] condition = scanner.nextLine().split(" ");

		int condition1 = Integer.parseInt(condition[0]);

		int[][] min = new int[condition1][];

		for (int i = 0; i < condition1; i ++) {
			min[i] = new int[condition1 - i];
		}

		min[0][0] = scanner.nextInt();

		for (int i = 1; i < condition1; i++) {
			int inputNumber = scanner.nextInt();

			min[i][0] = inputNumber;

			for (int j = 0; j < i; j++) {
				min[j][i - j] = min[j][i - j - 1] < inputNumber ? min[j][i - j - 1] : inputNumber;
			}
		}

		scanner.nextLine();
		int condition2 = Integer.parseInt(condition[1]);

		for (int i = 0; i < condition2; i++) {
			String[] range = scanner.nextLine().split(" ");

			int start = Integer.parseInt(range[0]) - 1;
			int end = Integer.parseInt(range[1]) - 1;

			System.out.println(min[start][end - start]);
		}
	}
}

class SegmentTree {
	private Node root;

	SegmentTree(List<Integer> list) {
		List<Node> nodeList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			Node node = new Node();
			node.setMin(list.get(i));
			node.setMax(list.get(i));
			node.setLeft(i);
			node.setRight(i);

			nodeList.add(node);
		}

		createParentList(nodeList);
	}

	private void createParentList(List<Node> list) {
		List<Node> parentList = new ArrayList<>();
		for (int i = 0; i < list.size(); i+=2) {
			Node parent = new Node();

			int left = i;
			int right = i + 1 < list.size() ? i + 1 : i;

			Node leftChild = list.get(left);
			Node rightChild = list.get(right);
			parent.setMin(leftChild.getMin() < rightChild.getMin() ? leftChild.getMin() : rightChild.getMin());
			parent.setMax(leftChild.getMax() > rightChild.getMax() ? leftChild.getMax() : rightChild.getMax());
			parent.setLeft(leftChild.getLeft());
			parent.setRight(rightChild.getRight());
			parent.setLeftChild(leftChild);
			parent.setRightChild(rightChild);
			parentList.add(parent);
		}

		if (parentList.size() == 1) {
			this.root = parentList.get(0);
			return;
		}

		createParentList(parentList);
	}

	public int getMin(int left, int right) {
		return getMin(left, right, root);
	}

	private int getMin(int left, int right, Node currentRoot) {
		if (left <= currentRoot.getLeft() && right >= currentRoot.getRight()) {
			return currentRoot.getMin();
		}

		Node leftChild = currentRoot.getLeftChild();
		Node rightChild = currentRoot.getRightChild();

		int leftMin = Integer.MAX_VALUE;
		int rightMin = Integer.MAX_VALUE;
		//1. 왼쪽만 보면 될때.
		if (left >= leftChild.getLeft() && right <= leftChild.getRight()) {
			leftMin = getMin(left, right, leftChild);
		}
		//2. 오른쪽만 보면될때.
		else if (left >= rightChild.getLeft() && right <= rightChild.getRight()) {
			rightMin = getMin(left, right, rightChild);
		}
		//3. 왼쪽 오른쪽 다봐야할때.
		else {
			leftMin = getMin(left, leftChild.getRight(), leftChild);
			rightMin = getMin(rightChild.getLeft(), right, rightChild);
		}

		return leftMin < rightMin ? leftMin : rightMin;
	}

	public int getMax(int left, int right) {
		return getMax(left, right, root);
	}

	private int getMax(int left, int right, Node currentRoot) {
		if (left <= currentRoot.getLeft() && right >= currentRoot.getRight()) {
			return currentRoot.getMax();
		}

		Node leftChild = currentRoot.getLeftChild();
		Node rightChild = currentRoot.getRightChild();

		int leftMin = Integer.MIN_VALUE;
		int rightMin = Integer.MIN_VALUE;
		//1. 왼쪽만 보면 될때.
		if (left >= leftChild.getLeft() && right <= leftChild.getRight()) {
			leftMin = getMax(left, right, leftChild);
		}
		//2. 오른쪽만 보면될때.
		else if (left >= rightChild.getLeft() && right <= rightChild.getRight()) {
			rightMin = getMax(left, right, rightChild);
		}
		//3. 왼쪽 오른쪽 다봐야할때.
		else {
			leftMin = getMax(left, leftChild.getRight(), leftChild);
			rightMin = getMax(rightChild.getLeft(), right, rightChild);
		}

		return leftMin > rightMin ? leftMin : rightMin;
	}
}

class Node {
	private int left;
	private int right;
	private int min;
	private int max;
	private Node leftChild;
	private Node rightChild;

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
}