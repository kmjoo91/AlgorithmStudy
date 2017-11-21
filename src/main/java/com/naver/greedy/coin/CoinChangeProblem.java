/*
 *@(#)CoinChargeProblem.java 2017.10.24
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.greedy.coin;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 *
 * @author kim.minjoo
 */
public class CoinChangeProblem {
	Integer[] conins;

	public CoinChangeProblem(Integer[] conins) {
		this.conins = conins;
		Arrays.sort(conins, Collections.reverseOrder()); // 이 문제에서 소팅까지 구현할 필요가 없으므로 구현된걸 사용하기 위해 int가 아닌 Integer사용
	}

	public void printCoinChange(int totalChange) {
		for (Integer coin : conins) {
			int coinNum = totalChange / coin;
			totalChange = totalChange - (coin * coinNum);
			System.out.println(coin + "원의 개수 : " + coinNum);
		}
	}
}