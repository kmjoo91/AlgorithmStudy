package com.naver.greedy.coin;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by AD on 2017-10-24.
 */
public class CoinChangeProblemTest {

	/**
	 * 최적해인 경우 테스트
	 */
	@Test
	public void CoinChangeProblemTest1() {
		Integer[] coins = {10, 100, 500, 50};
		CoinChangeProblem coinChangeProblem = new CoinChangeProblem(coins);
		coinChangeProblem.printCoinChange(170);
	}

	/**
	 * 최적해가 아닌 경우 테스트
	 * 120원 2개가 최적해.
	 */
	@Test
	public void CoinChangeProblemTest2() {
		Integer[] coins = {10, 120, 100, 200, 50};
		CoinChangeProblem coinChangeProblem = new CoinChangeProblem(coins);
		coinChangeProblem.printCoinChange(240);
	}
}