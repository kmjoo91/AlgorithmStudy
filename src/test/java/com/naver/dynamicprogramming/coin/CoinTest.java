package com.naver.dynamicprogramming.coin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoinTest {
	@Test
	public void CoinChangeProblemTest1() {
		//given
		int[] coins = {10, 100, 500, 50};
		int expected = 4;

		//when
		int actual = Coin.calculate(170, coins);

		//then
		assertEquals(expected, actual);
	}

	/**
	 * 최적해가 아닌 경우 테스트
	 * 120원 2개가 최적해.
	 */
	@Test
	public void CoinChangeProblemTest2() {
		//given
		int[] coins = {10, 120, 100, 200, 50};
		int expected = 2;

		//when
		int actual = Coin.calculate(240, coins);

		//then
		assertEquals(expected, actual);
	}
}