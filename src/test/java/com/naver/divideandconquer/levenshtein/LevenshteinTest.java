package com.naver.divideandconquer.levenshtein;

import static org.junit.Assert.*;

import org.junit.Test;

public class LevenshteinTest {
	@Test
	public void test() {
		//given
		String str1 = "abc";
		String str2 = "ebd";
		int expected = 2;

		//when
		int actual = Levenshtein.calculateDistance(str1, str2);

		//then
		assertEquals(expected, actual);
	}
}