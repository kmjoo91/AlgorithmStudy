package com.naver.baekjoon.problem6603;

import java.util.Arrays;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;

import com.naver.baekjoon.problem6603.model.Node;

/**
 * https://www.acmicpc.net/problem/6603
 */
public class Lotto {
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

		lotto2(0, 0, k, new int[6], numbers);
	}

	private static void lotto2(int start, int count, int k, int[] lotto, int[] numbers) {
		for (int i = start; i < k + count - 5 ; i++) {
			lotto[count] = numbers[i];
			if (count == 5) {
				for (int number : lotto) {
					System.out.print(number + " ");
				}
				System.out.println();
				continue;
			}
			lotto2(i + 1, count + 1, k, lotto, numbers);
		}
	}

	public static void calculate3(String condition) {
		String[] arr = StringUtils.split(condition, " ");

		int k = Integer.parseInt(arr[0]);
		int[] numbers = new int[k];

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = Integer.parseInt(arr[i + 1]);
		}

		System.out.println((int)combination(k, 6));
		int[][] lotto = new int[(int)combination(k, 6)][6];
		lotto3(lotto, k, 0, 0);

		for(int[] row : lotto) {
			for (int number : row) {
				System.out.print(numbers[number] + " ");
			}
			System.out.println();
		}
	}

	private static void lotto3(int[][] lotto, int k, int startIndex, int column) {
		if (column >= 6 ) {
			return;
		}
		int row = 0;
		for (int i = 0; i <= k - (6 - column) - startIndex; i++) {
			for (int j = 0; j < combination(k - column - 1 - i, 6- column - 1); j++) {
				lotto[j + row][column] = i + startIndex;
			}
			row += combination(k - column - 1 - i, 6- column - 1);
			lotto3(lotto, k, startIndex + 1, column + 1);
		}
	}

	private static double combination(int n, int r) {
		double parent = 1;
		double child = 1;
		for (int i = 1; i <= n; i++) {
			child *= i;
		}

		for (int i = 1; i <= r; i++) {
			parent *= i;
		}

		for (int i = 1; i <= n - r; i++) {
			parent *= i;
		}

		return child / parent;
	}
}