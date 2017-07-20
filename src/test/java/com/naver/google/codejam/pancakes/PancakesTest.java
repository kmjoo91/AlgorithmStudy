package com.naver.google.codejam.pancakes;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;

/**
 * Created by AD on 2017-04-14.
 */
public class PancakesTest {
	private Pancakes pancakes = new Pancakes();

	@Test
	public void 테스트() {
		assertEquals(1, pancakes.getFlipCount("-"));
		assertEquals(1, pancakes.getFlipCount("-+"));
		assertEquals(2, pancakes.getFlipCount("+-"));
		assertEquals(0, pancakes.getFlipCount("+++"));
		assertEquals(3, pancakes.getFlipCount("--+-"));
		assertEquals(0, pancakes.getFlipCount("++++++++++++++++++++++++++++++++++++++++++++++++++"));
	}


	@Test
	public void 테스트2() throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("PancakesLarge.txt"));
		int caseCount = Integer.parseInt(in.readLine());
		for (int i = 0; i < caseCount; i++) {
			String pancake = in.readLine();
			assertEquals(pancakes.getFlipCount(pancake), pancakes.getFlipCount2(pancake));
		}
	}
}