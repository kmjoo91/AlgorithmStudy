package com.naver.baekjoon.problem1929;

import java.util.Scanner;

public class PrimeNumberFinder {
	public static void Find(int m, int n) {
		if (m <= 2) {
			System.out.println("2");
			m = 3;
		}

		for (int i = m; i <= n; i++) {
			if (isPrimeNumber(i)) {
				System.out.println(i);
			}
		}
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
		String input = scanner.nextLine();
		String[] inputs = input.split(" ");

		Find(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
	}
}
