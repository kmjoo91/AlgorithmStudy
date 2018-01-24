/*
 *@(#)Coin.java 2018.01.10
 *
 * Copyright 2018 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.dynamicprogramming.coin;

/**
 *
 *
 * @author kim.minjoo
 */
public class Coin {
	public static int calculate(int money, int[] coins) {
		int[] memo = new int[money];
		for (int i = 0; i < money; i++) {
			memo[i] = -1;
		}
		return calculate(money, coins, memo);
	}

	private static int calculate(int money, int[] coins, int[] memo) {
		if (money == 0) {
			return 0;
		}

		int min = Integer.MAX_VALUE;

		for (int coin : coins) {
			if (money < coin) {
				continue;
			}

			int currentMoney = money - coin;
			int current = memo[currentMoney];

			if (current == -1) {
				current = calculate(currentMoney, coins);
				memo[currentMoney] = current;
			}

			if (current < min) {
				min = current;
			}
		}

		return min;
	}
}