package com.naver.divideandconquer.matrixchainmultiplication;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MatrixChainMultiplicationTest {
	private MatrixChainMultiplication matrixChainMultiplication = new MatrixChainMultiplication();

	@Test
	public void 테스트() {
		//given
		Matrix matrix1 = new Matrix(1, 100);
		Matrix matrix2 = new Matrix(100, 1);
		Matrix matrix3 = new Matrix(1, 100);
		Matrix[] matrices = new Matrix[3];
		matrices[0] = matrix1;
		matrices[1] = matrix2;
		matrices[2] = matrix3;
		int expected = 200;

		int actual = matrixChainMultiplication.calculate(matrices, 0, matrices.length - 1);

		assertEquals(expected, actual);
	}

}