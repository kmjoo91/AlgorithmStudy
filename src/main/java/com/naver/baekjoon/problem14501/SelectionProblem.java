package com.naver.baekjoon.problem14501;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/14501
 */
public class SelectionProblem {

	public static final int NON_CALCULATION = -1;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int number = scanner.nextInt();

		int[] periodArr = new int[number + 1];
		int[] valueArr = new int[number + 1];
		for (int i = 0; i < number; i++) {
			periodArr[i] = scanner.nextInt();
			valueArr[i] = scanner.nextInt();
		}

		int[] memoization = new int[number + 1];

		for (int standardDay = 0; standardDay <= number; standardDay++) {
			int day1 = standardDay + periodArr[standardDay];
			int day2 = standardDay + 1;
			int value1 = 0;
			int value2 = 0;

			if (day1 <= number) {
				value1 = Math.max(memoization[day1], memoization[standardDay] + valueArr[standardDay]);
				memoization[day1] = value1;
			}

			if (day2 <= number) {
				value2 = Math.max(memoization[day2], memoization[standardDay]);
				memoization[day2] = value2;
			}
		}

		System.out.println(memoization[number]);
	}
}