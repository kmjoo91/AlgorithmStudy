package com.naver.dynamicprogramming.baekjoon.problem10538.ahocorasick;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class AhoCorasickTest {
	@Test
	public void test() {
		AhoCorasick ahoCorasick = AhoCorasick.builder()
				.addPattern("ab")
				.addPattern("acd")
				.addPattern("c")
				.build();

		ahoCorasick.print();
	}
}