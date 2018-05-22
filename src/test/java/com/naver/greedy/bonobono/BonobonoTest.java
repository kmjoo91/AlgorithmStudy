package com.naver.greedy.bonobono;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.Test;


public class BonobonoTest {
	@Test
	public void example() {
		long start = System.currentTimeMillis();
		int[] torchs = {3, 1, 3, 3, 2, 2, 1, 1, 2, 1};

		System.out.println(Bonobono.calculate2(torchs.length, torchs));

		long end = System.currentTimeMillis();
		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
	}

	@Test
	public void example0() {
		long start = System.currentTimeMillis();
		int[] torchs = {1, 2, 2, 1, 3, 1, 1, 2, 4};

		System.out.println(Bonobono.calculate2(torchs.length, torchs));

		long end = System.currentTimeMillis();
		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
	}

	@Test
	public void example1() {
		long start = System.currentTimeMillis();

		//given
		Random random = new Random();
		int size = random.nextInt(10) + 1;
		size = 10;
		int[] torchs = new int[size];
		int expected = size;

		for (int i = 0; i < size; i++) {
			torchs[i] =  random.nextInt(3) + 1;
		}

		//when
		int actual = Bonobono.calculate2(size, torchs);

		//then
		//assertEquals(expected, actual);


		long end = System.currentTimeMillis();

		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
		System.out.println(size);
		for (int torch : torchs) {
			System.out.print(torch + " ");
		}
		System.out.println();
		System.out.println(actual);
	}

	@Test
	public void example2() {
		//give
		int[] torchs = {3, 3, 2};
		int expected = 1;

		//when
		int actual = Bonobono.calculate(3, torchs);

		//then
		assertEquals(expected, actual);
	}

	@Test
	public void example3() {
		long start = System.currentTimeMillis();
		//give
		int[] torchs = new int[200000];

		for (int i = 0; i < 200000; i++) {
			torchs[i] = 1;
		}

		//when
		int actual = Bonobono.calculate2(200000, torchs);


		long end = System.currentTimeMillis();

		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
		System.out.println(actual);

		//then
		//assertEquals(expected, actual);
	}
}