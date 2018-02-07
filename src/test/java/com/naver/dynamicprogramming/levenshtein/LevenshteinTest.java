package com.naver.dynamicprogramming.levenshtein;

import static org.junit.Assert.*;

import org.junit.Test;

public class LevenshteinTest {
	@Test
	public void testRecursive() {
		//given
		String str1 = "abc";
		String str2 = "ebd";
		int expected = 2;

		//when
		int actual = Levenshtein.calculateDistanceRecursive(str1, str2);

		//then
		assertEquals(expected, actual);
	}

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