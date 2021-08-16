package com.naver.baekjoon.problem3985;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;


public class RollCakeTest {
	@Test
	public void test() {
		System.out.println(Math.log(100) / Math.log(2));
		int length = 1000;
		int numbers = 1000;

		String[] lines = new String[numbers];

		for (int i = 0; i < numbers; i++) {
			lines[i] = "1 1000";
		}

		RollCake.calculate(length, numbers, lines);
	}
}