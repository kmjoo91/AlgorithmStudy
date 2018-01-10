/*
 *@(#)Fibonacci.java 2018.01.10
 *
 * Copyright 2018 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.divideandconquer.fibonacci;

/**
 *
 *
 * @author kim.minjoo
 */
public class Fibonacci {
	public static int calculate(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must be greater than 0");
		}

		if (n <= 1) {
			return n;
		}

		return Fibonacci.calculate(n - 1) + Fibonacci.calculate(n - 2);
	}
}