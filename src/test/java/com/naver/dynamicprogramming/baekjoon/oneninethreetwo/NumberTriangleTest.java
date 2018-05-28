package com.naver.dynamicprogramming.baekjoon.oneninethreetwo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class NumberTriangleTest {
	@Test
	public void test() {
		//given
		String[] strNumbers = {"7", "3 8", "8 1 0", "2 7 4 4", "4 5 2 6 5"};
		int expected = 30;

		//when
		int actual = NumberTriangle.calculate2(5, strNumbers);

		//then
		assertEquals(expected, actual);
	}

	@Test
	public void test2() {
		//given
		String[] strNumbers = {"1", "1 1"};
		int expected = 2;

		//when
		int actual = NumberTriangle.calculate2(strNumbers.length, strNumbers);

		//then
		assertEquals(expected, actual);
	}
}