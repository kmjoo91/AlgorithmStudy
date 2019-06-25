package com.naver.kakao.year2018;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/15954
 */
public class Dolls {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();

		int[] dolls = new int[n];

		for (int i = 0; i < n; i++) {
			dolls[i] = scanner.nextInt();
		}

		double min = Double.MAX_VALUE;
		while (k <= n) {
			for (int start = 0; start <= n - k; start++) {
				double average = calculateAverage(start, k, dolls);
				double variance = calculateVariance(start, k, dolls, average);

				if (min > variance) {
					min = variance;
				}
			}
			k++;
		}

		System.out.println(Math.sqrt(min));
	}

	private static double calculateVariance(int start, int number, int[] dolls, double average) {
		double sum = 0;
		for (int i = 0; i < number; i++) {
			sum += Math.pow(dolls[start + i] - average, 2);
		}
		return sum / number;
	}

	private static double calculateAverage(int start, int number, int[] dolls) {
		double sum = 0;
		for (int i = 0; i < number; i++) {
			sum += dolls[start + i];
		}
		return sum / number;
	}
}

