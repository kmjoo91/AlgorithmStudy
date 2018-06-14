package com.naver.baekjoon.problem1929;


import org.junit.Test;

public class PrimeNumberFinderTest {
	@Test
	public void test() {
		long start = System.currentTimeMillis();

		PrimeNumberFinder.Find(1, 1000000);

		long end = System.currentTimeMillis();

		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
	}
}