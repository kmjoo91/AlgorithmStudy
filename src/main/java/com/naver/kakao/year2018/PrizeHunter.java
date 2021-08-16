package com.naver.kakao.year2018;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/15953
 */
public class PrizeHunter {
	private static int[] festival1 = {500, 300, 200, 50, 30, 10};
	private static int[] festival2 = {512, 256, 128, 64, 32};
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int number = scanner.nextInt();

		for (int i = 0; i < number; i++) {
			int prize1 = getFestival1Prize(scanner.nextInt());
			int prize2 = getFestival2Prize(scanner.nextInt());

			int totalPrize = prize1 + prize2;
			System.out.println(totalPrize * 10000);
		}
	}

	private static int getFestival1Prize(int ranking) {
		if (ranking < 1) {
			return 0;
		}

		int sum = 0;
		for (int i = 0; i < festival1.length; i++) {
			sum += i + 1;

			if (sum >= ranking) {
				return festival1[i];
			}
		}

		return 0;
	}

	private static int getFestival2Prize(int ranking) {
		if (ranking < 1) {
			return 0;
		}

		int index = (int)(Math.log(ranking) / Math.log(2));

		return index >= festival2.length ? 0 : festival2[index];
	}
}
