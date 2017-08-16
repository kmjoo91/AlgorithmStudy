package com.naver.hash.chaining;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by AD on 2017-08-09.
 */
public class ChainingTest {
	Chaining<String, Object> chaining = new Chaining<>();

	@Test
	public void 테스트() {
		System.out.println("========================시작=======================");
		System.out.println(chaining.toString());

		for (int i = 0; i < 30; i++) {
			if (i >= 20 ) {
				chaining.add(String.valueOf(20), 20);
			} else {
				chaining.add(String.valueOf(i), i);
			}
		}
		System.out.println("========================데이터 넣은 후=======================");
		System.out.println(chaining.toString());

		System.out.println("========================찾는데 있을경우=======================");
		String key = "20";
		System.out.println(chaining.get(key));

		System.out.println("========================찾는데 지워져서 없을경우=======================");
		key = "1";
		chaining.remove(key);
		System.out.println(chaining.get(key));

		System.out.println("========================지워도 있을경우는 없지..=======================");
		key = "20";
		chaining.remove(key);
		System.out.println(chaining.get(key));
		System.out.println(chaining.toString());
	}
}