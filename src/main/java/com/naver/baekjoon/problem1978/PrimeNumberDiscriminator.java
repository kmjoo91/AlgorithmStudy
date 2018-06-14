package com.naver.baekjoon.problem1978;

import java.util.Scanner;

public class PrimeNumberDiscriminator {
	public static int Discriminate(int[] numbers) {
		int result = 0;

		for (int number : numbers) {
			if (isPrimeNumber(number)) {
				result++;
			}
		}

		return result;
	}

	private static boolean isPrimeNumber(int num) {
		if (num < 2) {
			return false;
		}

		for (int i = 2; i <= Math.sqrt(num); i++) {
			int remainder = num % i;
			if (remainder == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int[] inputs = new int[n];
		for (int i = 0; i < n; i++) {
			inputs[i] = scanner.nextInt();
		}

		int result = Discriminate(inputs);
		System.out.println(result);
	}
}
