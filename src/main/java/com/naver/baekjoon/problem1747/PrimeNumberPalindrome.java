package com.naver.baekjoon.problem1747;

import java.util.Scanner;

public class PrimeNumberPalindrome {
	public static int calculate(int n) {
		while (true) {
			if (isPrimeNumber(n) && isPalindrome(n)) {
				return n;
			}
			n++;
		}
	}

	private static boolean isPalindrome(int num) {
		if (num < 10) {
			return true;
		}

		String str = String.valueOf(num);
		int length = str.length();
		int lastIndex = length - 1;

		for (int i = 0; i < length / 2; i++) {
			if (str.charAt(i) != str.charAt(lastIndex - i)) {
				return false;
			}
		}

		return true;
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

		int result = calculate(n);

		System.out.println(result);
	}
}
