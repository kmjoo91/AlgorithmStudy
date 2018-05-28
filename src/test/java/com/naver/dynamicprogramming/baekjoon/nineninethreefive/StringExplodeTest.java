package com.naver.dynamicprogramming.baekjoon.nineninethreefive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class StringExplodeTest {
	@Test
	public void test() {
		//given
		String target = "mirkovC4nizCC44";
		String pattern = "C4";
		String expected = "mirkovniz";

		//when
		String actual = StringExplode.explode(target, pattern);

		//then
		assertEquals(expected, actual);
	}
	@Test
	public void test2() {
		//given
		String target = "ababababccc";
		String pattern = "abc";
		String expected = "ab";

		//when
		String actual = StringExplode.explode(target, pattern);

		//then
		assertEquals(expected, actual);
	}
	@Test
	public void test3() {
		//given
		String target = "ababababccc";
		String pattern = "d";
		String expected = target;

		//when
		String actual = StringExplode.explode(target, pattern);

		//then
		assertEquals(expected, actual);
	}
}