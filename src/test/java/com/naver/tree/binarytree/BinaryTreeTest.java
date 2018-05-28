package com.naver.tree.binarytree;

import org.junit.Test;


public class BinaryTreeTest {
	@Test
	public void addTest() {
		BinaryTree binaryTree = new BinaryTree();

		for (int i = 1; i < 16; i++) {
			binaryTree.add(i);
			System.out.println(binaryTree.getHeight());
		}
	}

	@Test
	public void preOrderTest() {
		BinaryTree binaryTree = new BinaryTree();

		for (int i = 1; i < 16; i++) {
			binaryTree.add(i);
		}

		binaryTree.preOrder();
	}

	@Test
	public void inOrderTest() {
		BinaryTree binaryTree = new BinaryTree();

		for (int i = 1; i < 16; i++) {
			binaryTree.add(i);
		}

		binaryTree.inOrder();
	}

	@Test
	public void postOrderTest() {
		BinaryTree binaryTree = new BinaryTree();

		for (int i = 1; i < 16; i++) {
			binaryTree.add(i);
		}

		binaryTree.postOrder();
	}

	@Test
	public void using1StackPostOrderTest() {
		BinaryTree binaryTree = new BinaryTree();

		for (int i = 1; i < 16; i++) {
			binaryTree.add(i);
		}

		binaryTree.postOrder();
		binaryTree.using1StackPostOrder();
	}

	@Test
	public void levelOrderTest() {
		BinaryTree binaryTree = new BinaryTree();

		for (int i = 1; i < 16; i++) {
			binaryTree.add(i);
		}

		binaryTree.levelOrder();
	}
}
