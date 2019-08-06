package com.naver.baekjoon.problem13263;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/13263
 */
public class WoodCutting {
	private static final int HEIGHT = 0;
	private static final int CHARGING_TIME = 1;
	private static final int MEMOIZATION = 2;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int number = scanner.nextInt();

		long[][] wood = new long[number][3];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < number; j++) {
				wood[j][i] = scanner.nextInt();
			}
		}

		Linear[] stack = new Linear[number];
		int top = 0;
		int minLinearIndex = 0;
		for (int currentWood = 1; currentWood < number; currentWood ++) {
			Linear linear = new Linear(wood[currentWood - 1][CHARGING_TIME], wood[currentWood - 1][MEMOIZATION]);

			while (top > 0) {
				Linear topLinear = stack[top - 1];
				linear.setCrossXcoordinate(calculateCoordinate(linear, topLinear));
				if (topLinear.getCrossXcoordinate() < linear.getCrossXcoordinate()) {
					break;
				}

				top--;
				if (top == minLinearIndex) {
					minLinearIndex--;
				}
			}

			stack[top++] = linear;

			long x = wood[currentWood][HEIGHT];
			while (minLinearIndex + 1 < top && stack[minLinearIndex + 1].getCrossXcoordinate() < x) {
				minLinearIndex++;
			}

			wood[currentWood][MEMOIZATION] = stack[minLinearIndex].getGradient() * x + stack[minLinearIndex].getyIntercept();
		}

		System.out.println(wood[number - 1][MEMOIZATION]);
	}

	private static double calculateCoordinate(Linear linear, Linear linear1) {
		return ((double)linear.getyIntercept() - linear1.getyIntercept()) / ((double)linear1.getGradient() - linear.getGradient());
	}
}

class Linear {
	private long gradient;
	private long yIntercept;
	private double crossXcoordinate;

	public Linear(long gradient, long yIntercept) {
		this.gradient = gradient;
		this.yIntercept = yIntercept;
	}

	public long getGradient() {
		return gradient;
	}

	public void setGradient(long gradient) {
		this.gradient = gradient;
	}

	public long getyIntercept() {
		return yIntercept;
	}

	public void setyIntercept(long yIntercept) {
		this.yIntercept = yIntercept;
	}

	public double getCrossXcoordinate() {
		return crossXcoordinate;
	}

	public void setCrossXcoordinate(double crossXcoordinate) {
		this.crossXcoordinate = crossXcoordinate;
	}
}