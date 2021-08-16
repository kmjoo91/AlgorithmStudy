package com.naver.baekjoon.problem1562;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.naver.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StairNumberTest {
	StairNumbers stairNumber = new StairNumbers(100, 10);
	@Test
	void test() {
		long result = 0;
		for (int i = 1; i <= 40; i++) {
			long currentResult = stairNumber.calculate(i);
			System.out.println("i : " + i + ", result : " + currentResult);
			result += currentResult;
		}

		assertEquals(126461847755l, result);
	}
}