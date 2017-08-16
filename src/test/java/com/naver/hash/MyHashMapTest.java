package com.naver.hash;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by AD on 2017-08-04.
 */
public class MyHashMapTest {
	MyHashMap<String, Object> myHashMap = new MyHashMap<String, Object>();

	@Test
	public void 테스트() {
		myHashMap.put("이름", "김민주");
		myHashMap.put("학번", 201011245);
		myHashMap.put("학번", "이공일공일일이사오");

		System.out.println(myHashMap.get("이름"));
		System.out.println(myHashMap.get("학번"));

		System.out.println(myHashMap.remove("학번"));
		System.out.println(myHashMap.get("학번"));
	}
}