package com.naver.baekjoon.problem1344;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1344
 */
public class Soccer {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double prob1 = scanner.nextDouble();
		double prob2 = scanner.nextDouble();

		Soccer soccer = new Soccer();

		System.out.println(soccer.calculate(prob1/100, prob2/100));
	}

	public double calculate(double prob1, double prob2) {
		double probA = 0.0;
		double probB = 0.0;

		for (int i = 1; i < 19; i++) {
			if (isPrimeNumber(i) == false) {
				continue;
			}

			 probA += calculateGoalProb(prob1, i);
			 probB += calculateGoalProb(prob2, i);

		}

		return probA + probB - (probA * probB);
	}

	/**
	 * num골을 넣을 확률을 구해주는 함수
	 * @param goalProb 5분동안 골넣을 확률
	 * @param num 골의 수
	 * @return 경기동안 골의 수 만큼 넣을 확률을 구해주는 함수.
	 */
	private double calculateGoalProb(double goalProb, int num) {
		double prob = Math.pow(goalProb, num) * Math.pow(1 - goalProb, 18 - num);
		double numberOfCase = combination(18, num);

		return prob * numberOfCase;
	}

	/**
	 * 콤비네이션
	 * @param n
	 * @param r
	 * @return
	 */
	private int combination(int n, int r) {
		if (n == r || r == 0)
			return 1;
		else
			return combination(n - 1, r - 1) + combination(n - 1, r);
	}

	private boolean isPrimeNumber(int num) {
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
}
