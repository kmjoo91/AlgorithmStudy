/*
 *@(#)NumberCountCalculatorTest.java 2017.01.12
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.google;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 *
 * @author kim.minjoo
 */
public class NumberCountCalculatorTest {
	private NumberCountCalculator numberCountCalculator = new NumberCountCalculator();

	@Test
	public void test_calculateNumberCount() {
		//given
		int[][] expectedArray = {
			{8, 10000, 4000},
			{8, 9900, 3980},
			{0, 10000, 4004},
			{8, 1000, 300},
			{0, 1000, 303},
			{1, 1, 1},
			{8, 1, 0},
			{8, 9, 1},
			{8, 10, 1},
			{8, 858, 225}
		};

		for (int i=0; i < expectedArray.length; i++) {
			//when
			int actual = numberCountCalculator.calculateNumberCount(expectedArray[i][0], expectedArray[i][1]);
			//then
			assertEquals(expectedArray[i][2], actual);
		}
	}
}