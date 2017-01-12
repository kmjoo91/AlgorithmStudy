/*
 *@(#)SelfNumberTest.java 2017.01.12
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.nexon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 *
 * @author kim.minjoo
 */
public class SelfNumberTest {
	private SelfNumber selfNumber = new SelfNumber();

	@Test
	public void test_getGeneratorNumber() {
		int[][] expectedArray = {
			{499, 479},
			{101, 91},
			{2, 1},
			{3, -1},
			{99, 90},
			{490, -1}
		};

		for (int i = 0; i < expectedArray.length; i++) {
			int actual = selfNumber.getGeneratorNumber(expectedArray[i][0]);
			assertEquals(expectedArray[i][1], actual);
		}
	}
}