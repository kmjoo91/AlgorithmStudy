package com.naver.baekjoon.problem11062;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/11062
 */
public class CardGame {
	private int[] cards;
	private int[][] dp;

	public CardGame(int[] cards) {
		this.cards = cards;
		dp = new int[cards.length][cards.length];
	}

	public int calculate(boolean myTurn) {
		return calculate(myTurn, 0, cards.length - 1);
	}

	private int calculate(boolean myTurn, int left, int right) {
		if (dp[left][right] != 0) {
			return dp[left][right];
		}

		if (left == right) {
			if (myTurn) {
				dp[left][right] = cards[left];
			}
		} else {
			if (myTurn) {
				dp[left][right] = Math.max(calculate(false, left + 1, right) + cards[left], calculate(false, left, right - 1) + cards[right]);
			} else {
				dp[left][right] = Math.min(calculate(true, left + 1, right), calculate(true, left, right - 1));
			}
		}

		return dp[left][right];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int gameCount = scanner.nextInt();
		CardGame[] cardGames = new CardGame[gameCount];

		for (int i = 0; i < gameCount; i++) {
			int cardCount = scanner.nextInt();
			int[] cards = new int[cardCount];

			for (int j = 0; j < cardCount; j++) {
				cards[j] = scanner.nextInt();
			}

			CardGame cardGame = new CardGame(cards);
			cardGames[i] = cardGame;
		}

		for (int i = 0; i< gameCount; i++) {
			System.out.println(cardGames[i].calculate(true));
		}
	}
}
