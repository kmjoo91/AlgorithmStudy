package com.naver.baekjoon.problem3985;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/3985
 */
public class RollCake {
	public static void calculate(int length, int numbers, String[] lines) {
		boolean[] selected = new boolean[length];

		String[] firstLine = lines[0].split(" ");
		int left = Integer.parseInt(firstLine[0]);
		int right = Integer.parseInt(firstLine[1]);

		int maxExpectedPerson = 0;
		int maxExpectedPrice = right - left + 1;
		int maxActualPerson = 0;
		int maxActualPrice = maxExpectedPrice;

		for (int i = left - 1; i < right; i++) {
			selected[i] = true;
		}

		for (int i = 1; i < numbers; i ++) {
			String[] line = lines[i].split(" ");
			left = Integer.parseInt(line[0]);
			right = Integer.parseInt(line[1]);

			int expectedPrice = right - left + 1;

			if (expectedPrice > maxExpectedPrice) {
				maxExpectedPrice = expectedPrice;
				maxExpectedPerson = i;
			}

			int actualPrice = 0;
			for (int j = left - 1; j < right; j++ ) {
				if (!selected[j]) {
					actualPrice++;
					selected[j] = true;
				}
			}

			if (actualPrice > maxActualPrice) {
				maxActualPrice = actualPrice;
				maxActualPerson = i;
			}
		}

		System.out.println(maxExpectedPerson + 1);
		System.out.println(maxActualPerson + 1);
		for (boolean select : selected) {
			System.out.print(select ? "1" : "0");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int length = scanner.nextInt();
		int numbers = scanner.nextInt();

		scanner.nextLine();
		String[] lines = new String[numbers];
		for (int i = 0; i < numbers; i++) {
			lines[i] = scanner.nextLine();
		}

		calculate(length, numbers, lines);
	}
}
