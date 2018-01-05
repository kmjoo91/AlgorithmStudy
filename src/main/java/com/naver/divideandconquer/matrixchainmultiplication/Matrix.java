/*
 *@(#)Matrix.java 2018.01.05
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
public class Matrix {
	int row;
	int column;
	int[][] value;

	// 테스트를 위해 간단히 만들 수 있는 생성자 생성.
	public Matrix(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
}