package com.naver.baekjoon.problem1562;

import java.util.Scanner;

public class StairNumbers {
	private static final int mod = 1000000000;

	//depth, number, masking
	private long[][][] dp;
	private int maxNumber;
	private int maxLength;
	private int maxIndex;

	public StairNumbers(int maxLength, int maxNumber) {
		this.maxLength = maxLength;
		this.maxNumber = maxNumber;
		this.maxIndex = (int)Math.pow(2, maxNumber);

		dp = new long[maxLength + 1][maxNumber][maxIndex];

		for (int i = 1; i < maxNumber; i++) {
			dp[1][i][(int)Math.pow(2, i)] = 1;
		}

		for (int depth = 2; depth < maxLength + 1; depth ++) {
			for (int number = 0; number < maxNumber; number++) {
				long[] current = dp[depth][number];

				long[] pre = null;
				long[] post = null;

				if (number == 0 ) {
					post = dp[depth - 1][1];
				} else if (number == maxNumber - 1) {
					pre = dp[depth - 1][maxNumber - 2];
				} else {
					pre = dp[depth - 1][number - 1];
					post = dp[depth - 1][number + 1];
				}

				if (pre != null) {
					setValue(current, pre, number);
				}

				if (post != null) {
					setValue(current, post, number);
				}
			}
		}
	}

	private void setValue(long[] current, long[] value, int number) {
		for (int mask = 0; mask < maxIndex; mask++) {
			current[(mask | (int)Math.pow(2, number))] += value[mask] % mod;
		}
	}

	public long calculate(int depth) {
		long result = 0;

		for (int i = 0; i < maxNumber; i++) {
			result += dp[depth][i][maxIndex - 1];
		}

		return result;
	}

	public static void main(String[] args) {
		StairNumbers stairNumber = new StairNumbers(100, 10);

		Scanner scanner = new Scanner(System.in);

		long result = stairNumber.calculate(scanner.nextInt());

		System.out.println(result % mod);
	}
}
