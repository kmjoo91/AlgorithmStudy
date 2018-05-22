package com.naver.dynamicprogramming.baekjoon.sixsixzerothree;

import java.util.Arrays;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

import com.naver.dynamicprogramming.baekjoon.sixsixzerothree.model.Node;

public class Sixsixzerothree {
	public static void calculate(String condition) {
		String[] arr = StringUtils.split(condition, " ");

		int k = Integer.parseInt(arr[0]);

		if (k == 6) {
			System.out.println(StringUtils.substring(condition, 2, condition.length()));
			return;
		}

		int[] numbers = new int[k];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Integer.parseInt(arr[i + 1]);
		}

		Node root = new Node(0);

		makeTree(k, root);
		printTree(numbers, root);

		StringBuilder nextCondition = new StringBuilder(String.valueOf(k - 1));
		int[] ints = Arrays.copyOfRange(numbers, 1, numbers.length);
		for (int a : ints) {
			nextCondition.append(" ").append(String.valueOf(a));
		}

		calculate(nextCondition.toString());
	}

	private static void printTree(int[] numbers, Node root) {
		Stack<Node> stack = new Stack<>();
		printTree(numbers, stack, root);
	}

	private static void printTree(int[] numbers, Stack<Node> stack, Node node) {
		//넣고나서 6이면 뒤에를 진행할 필요없이 출력하고 부모로가면되니까 그작업
		if (stack.size() == 5) {
			for (Object obj : stack.toArray()) {
				Node current = (Node)obj;
				System.out.print(numbers[current.getIndex()] + " ");
			}
			System.out.println(numbers[node.getIndex()]);
			return;
		}

		stack.push(node);
		for (Node child : node.getChildren()) {
			printTree(numbers, stack, child);
		}
		stack.pop();
	}

	private static void makeTree(int k, Node node) {
		for (int i = node.getIndex() + 1; i < k; i++) {
			if (k - i < 6 - node.getLevel() - 1) {
				break;
			}
			node.addChild(i);
		}

		if (node.getChildren().size() == 0) {
			return;
		}

		for (Node child : node.getChildren()) {
			makeTree(k, child);
		}
	}


	public static void calculate2(String condition) {
		String[] arr = StringUtils.split(condition, " ");

		int k = Integer.parseInt(arr[0]);
		int[] numbers = new int[k];

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Integer.parseInt(arr[i + 1]);
		}
	}
}