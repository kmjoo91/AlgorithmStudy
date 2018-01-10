/*
 *@(#)Coin.java 2018.01.10
 *
 * Copyright 2018 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.divideandconquer.coin;

/**
 *
 *
 * @author kim.minjoo
 */
public class Coin {
	public static int calculate(int money, int[] coins) {
		if (money == 0) {
			return 0;
		}

		int min = Integer.MAX_VALUE;

		for (int coin : coins) {
			if (money < coin) {
				continue;
			}

			int current = calculate(money - coin, coins) + 1;

			if (current < min) {
				min = current;
			}
		}

		return min;
	}
}