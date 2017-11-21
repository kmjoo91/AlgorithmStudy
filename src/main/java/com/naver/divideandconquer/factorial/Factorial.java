/*
 *@(#)Factorial.java 2017.11.21
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.divideandconquer.factorial;

/**
 *
 *
 * @author kim.minjoo
 */
public class Factorial {
	public int factorial(int num) {
		if (num < 1) {
			throw new IllegalArgumentException("The parameters of factorial method are greater than one, parameter : " + num);
		}

		if (num == 1) {
			return 1;
		}

		return num * factorial(num - 1);
	}
}