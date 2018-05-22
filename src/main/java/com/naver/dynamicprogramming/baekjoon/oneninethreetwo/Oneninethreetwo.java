package com.naver.dynamicprogramming.baekjoon.oneninethreetwo;

/**
 * https://www.acmicpc.net/problem/1932
 */
public class Oneninethreetwo {
	public static int calculate(int n, String[] strNumbers) {
		int[][] numbers = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] row = strNumbers[i].split(" ");
			for (int j = 0; j < row.length; j++) {
				numbers[i][j] = Integer.parseInt(row[j]);
			}
		}

		int[][] memo = new int[n][n];

		for (int row = 1; row < n; row++) {
			for(int col = 1; col < n; col++) {
				memo[row][col] = 0;
			}
		}

		memo[0][0] = numbers[0][0];

		for (int row = 1; row < n; row++) {
			memo[row][0] = memo[row - 1][0] + numbers[row][0];
		}

		for (int row = 1; row < n; row++) {
			for(int col = 1; col < n; col++) {
				memo[row][col] = Math.max(memo[row-1][col-1], memo[row-1][col]) + numbers[row][col];
			}
		}

		int max = memo[n-1][0];

		for (int col = 1; col < n; col++) {
			if (memo[n-1][col] > max) {
				max = memo[n-1][col];
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(memo[i][j] + " ");
			}
			System.out.println();
		}

		return max;
	}

	public static int calculate2(int n, String[] strNumbers) {
		int[]numbers = new int[n];
		numbers[0] = Integer.parseInt(strNumbers[0]);

//		for (int i = 0; i < n; i++) {
//			System.out.print(numbers[i] + " ");
//		}
//		System.out.println();

		for (int i = 1; i < n; i++) {
			int prev = numbers[0];
			String[] row = strNumbers[i].split(" ");
			numbers[0] = prev + Integer.parseInt(row[0]);
			for (int j = 1; j < row.length; j++) {
				int current = numbers[j];
				numbers[j] = Math.max(prev, current) + Integer.parseInt(row[j]);
				prev = current;
			}
//
//			for (int j = 0; j < n; j++) {
//				System.out.print(numbers[j] + " ");
//			}
//			System.out.println();
		}

		int max = numbers[0];
		for (int i = 1; i < n; i++) {
			if (numbers[i] > max) {
				max = numbers[i];
			}
		}

		return max;
	}
}
