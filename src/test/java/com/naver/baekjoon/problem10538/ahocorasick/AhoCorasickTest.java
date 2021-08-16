package com.naver.baekjoon.problem10538.ahocorasick;

import static com.naver.baekjoon.problem10538.ahocorasick.BigPicture.calculate;

import java.util.List;

import org.junit.Test;

public class AhoCorasickTest {
	@Test
	public void test() {
//		String[] pattern = {"oxxo", "xoox", "xoox", "oxxo"};
//		AhoCorasick ahoCorasick = AhoCorasick.builder()
//				.addPattern(pattern)
//				.build();
//
//		String test = "oxxoooxoox";
//
//		for (int i = 0; i < test.length(); i++) {
//			List<Integer> integerList = ahoCorasick.getMatchingIndexList(test.charAt(i));
//			System.out.print("i : " + i + "matchingList :");
//			for (Integer integer : integerList) {
//				System.out.print(" " + integer);
//			}
//			System.out.println();
//		}
	}

	@Test
	public void test2() {
		long start = System.currentTimeMillis();

		String[] picture= new String[1000];
		String[] bigPicture = new String[2000];

		for (int i = 0; i < 1000; i++) {
			String str = "";
			for (int j = 0; j < 1000; j ++) {
				str += "o";
			}
			picture[i] = str;
		}

		for (int i = 0; i < 2000; i++) {
			String row = "";
			for (int j = 0; j< 2000; j++) {
				row +="o";
			}
			bigPicture[i] = row;
		}

		int result = calculate(picture, bigPicture);
		System.out.println(result);

		// 끝에 아래와 같이 삽입

		long end = System.currentTimeMillis();

		System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
	}
}