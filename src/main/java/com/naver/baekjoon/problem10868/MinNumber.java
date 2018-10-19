package com.naver.baekjoon.problem10868;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/10868
 */
public class MinNumber {
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

			System.out.println(segmentTree.getData(left, right));
		}
	}
}

class SegmentTree {
	private Node root;

	SegmentTree(List<Integer> list) {
		List<Node> nodeList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			Node node = new Node();
			node.setData(list.get(i));
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
			parent.setData(leftChild.getData() < rightChild.getData() ? leftChild.getData() : rightChild.getData());
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

	public int getData(int left, int right) {
		return getData(left, right, root);
	}

	private int getData(int left, int right, Node currentRoot) {
		if (left <= currentRoot.getLeft() && right >= currentRoot.getRight()) {
			return currentRoot.getData();
		}

		Node leftChild = currentRoot.getLeftChild();
		Node rightChild = currentRoot.getRightChild();

		int leftMin = Integer.MAX_VALUE;
		int rightMin = Integer.MAX_VALUE;
		//1. 왼쪽만 보면 될때.
		if (left >= leftChild.getLeft() && right <= leftChild.getRight()) {
			leftMin = getData(left, right, leftChild);
		}
		//2. 오른쪽만 보면될때.
		else if (left >= rightChild.getLeft() && right <= rightChild.getRight()) {
			rightMin = getData(left, right, rightChild);
		}
		//3. 왼쪽 오른쪽 다봐야할때.
		else {
			leftMin = getData(left, leftChild.getRight(), leftChild);
			rightMin = getData(rightChild.getLeft(), right, rightChild);
		}

		return leftMin < rightMin ? leftMin : rightMin;
	}
}

class Node {
	private int left;
	private int right;
	private int data;
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

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
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
