package com.naver.greedy.bonobono;

/**
 * https://algospot.com/judge/problem/read/CAVEMAN
 */
public class Bonobono {
	public static int calculate(int n, int[] torchs) {
		int left = 0;
		int result = 0;

		for (int i = n - 1; i >= left; i--) {
			int current = torchs[i];

			if (i - left <= current - 1) {
				result++;
				left = i + current;
				i = n - 1;
			}

			if (n == left + 1) {
				return result + 1;
			}
		}

		return result;
	}

	public static int calculate2(int n, int[] torchs) {
		int[] memo = new int[n];

		for (int i = 0; i < n; i++) {
			int length = torchs[i] - 1;
			int left = i - length < 0 ? 0 : i - length;
			int right = i + length > n-1 ? n-1 : i + length;

			for (int j = left; j <=right; j++) {
				if (i + torchs[i] - 1 > memo[j]) {
					memo[j] = i + torchs[i] - 1;
				}
			}
		}

		int count = 0;
		for (int i = 0; 1 + memo[i] < n; i = 1 + memo[i]) {
			count++;
		}
		return count + 1;
	}
}
