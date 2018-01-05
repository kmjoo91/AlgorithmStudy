/*
 *@(#)MatrixChainMultiplication.java 2018.01.05
 *
 * Copyright 2018 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.divideandconquer.matrixchainmultiplication;

/**
 *
 *
 * @author kim.minjoo
 */
public class MatrixChainMultiplication {
	public int calculate(Matrix[] matrices, int start, int end) {
		if (start == end) {
			return 0;
		}

		if (start + 1 == end) {
			return matrices[start].getRow() * matrices[start].getColumn() * matrices[end].getColumn();
		}

		int min = Integer.MAX_VALUE;
		for (int i = start + 1; i < end; i++) {
			int left = calculate(matrices, start, i);
			int right = calculate(matrices, i + 1, end);
			int center = matrices[start].getRow() * matrices[i].getColumn() * matrices[end].getColumn();
			int value = left + center + right;

			if (value < min) {
				min = value;
			}
		}

		return min;
	}
}