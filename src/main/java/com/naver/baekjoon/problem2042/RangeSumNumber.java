package com.naver.baekjoon.problem2042;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2042
 */
public class RangeSumNumber {
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
		int condition2 = Integer.parseInt(condition[1]) + Integer.parseInt(condition[2]);
		for (int i = 0; i < condition2; i++) {
			String[] range = scanner.nextLine().split(" ");

			if (Integer.parseInt(range[0]) == 2) {
				int left = Integer.parseInt(range[1]) - 1;
				int right = Integer.parseInt(range[2]) - 1;

				System.out.println(segmentTree.getData(left, right));
			} else {
				int index =Integer.parseInt(range[1]) - 1;
				int number = Integer.parseInt(range[2]);
				segmentTree.update(index, number);
			}
		}
	}
}

class SegmentTree {
	private Node root;
	private List<Integer> list;


	SegmentTree(List<Integer> list) {
		this.list = list;
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
			parent.setData(left != right ? leftChild.getData() + rightChild.getData() : leftChild.getData());
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

	public long getData(int left, int right) {
		return getData(left, right, root);
	}

	private long getData(int left, int right, Node currentRoot) {
		if (left <= currentRoot.getLeft() && right >= currentRoot.getRight()) {
			return currentRoot.getData();
		}

		Node leftChild = currentRoot.getLeftChild();
		Node rightChild = currentRoot.getRightChild();

		long leftData = 0;
		long rightData = 0;
		//1. 왼쪽만 보면 될때.
		if (left >= leftChild.getLeft() && right <= leftChild.getRight()) {
			leftData = getData(left, right, leftChild);
		}
		//2. 오른쪽만 보면될때.
		else if (left >= rightChild.getLeft() && right <= rightChild.getRight()) {
			rightData = getData(left, right, rightChild);
		}
		//3. 왼쪽 오른쪽 다봐야할때.
		else {
			leftData = getData(left, leftChild.getRight(), leftChild);
			rightData = getData(rightChild.getLeft(), right, rightChild);
		}

		return leftData + rightData;
	}

	public void update(int index, int number) {
		int gap = number - list.get(index);
		list.remove(index);
		list.add(index, number);

		root.setData(root.getData() + gap);

		Node node = index <= root.getLeftChild().getRight() ? root.getLeftChild() : root.getRightChild();

		while(true) {
			node.setData(node.getData() + gap);

			if (node.getLeftChild() == null) {
				break;
			}
			node = index <= node.getLeftChild().getRight() ? node.getLeftChild() : node.getRightChild();
		}
	}
}

class Node {
	private int left;
	private int right;
	private long data;
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

	public long getData() {
		return data;
	}

	public void setData(long data) {
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