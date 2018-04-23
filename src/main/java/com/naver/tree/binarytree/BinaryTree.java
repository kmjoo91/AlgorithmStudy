package com.naver.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

import com.naver.tree.binarytree.model.Node;

public class BinaryTree {
	Node root;
	int nodeNum;

	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(Node node) {
		if (node == null) {
			return;
		}

		System.out.println(node.getValue());
		preOrder(node.getLeftChild());
		preOrder(node.getRightChild());
	}

	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(Node node) {
		if (node == null) {
			return;
		}

		inOrder(node.getLeftChild());
		System.out.println(node.getValue());
		inOrder(node.getRightChild());
	}

	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(Node node) {
		if (node == null) {
			return;
		}

		postOrder(node.getLeftChild());
		postOrder(node.getRightChild());
		System.out.println(node.getValue());
	}

	public void levelOrder() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		levelOrder(queue);
	}

	private void levelOrder(Queue<Node> queue) {
		Node node = queue.poll();

		if (node == null) {
			return;
		}

		System.out.println(node.getValue());
		queue.offer(node.getLeftChild());
		queue.offer(node.getRightChild());
		levelOrder(queue);
	}

	public void add(int value) {
		if (root == null) {
			root = new Node();
			root.setValue(value);
			this.nodeNum++;
			return;
		}

		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		add(queue, value);
		this.nodeNum++;
	}

	private void add(Queue<Node> queue, int value) {
		Node node = queue.poll();

		if (node.getLeftChild() == null) {
			node.setLeftChild(new Node());
			node.getLeftChild().setValue(value);
			return;
		}

		if (node.getRightChild() == null) {
			node.setRightChild(new Node());
			node.getRightChild().setValue(value);
			return;
		}

		queue.offer(node.getLeftChild());
		queue.offer(node.getRightChild());
		add(queue, value);
	}

	public int getHeight() {
		return (int)(Math.log(this.nodeNum) / Math.log(2)) + 1;
	}
}
