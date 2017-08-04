package com.naver.hash;

import org.junit.Test;

/**
 * Created by AD on 2017-08-03.
 */
public class OpenAddressingBookVersionTest {
	OpenAddressing openAddressing = new OpenAddressingBookVersion();

	@Test
	public void 테스트() {
		OpenAddressing.HashTable hashTable = openAddressing.createHashTable(30);
		System.out.println("========================시작=======================");
		hashTable.print();

		for (int i = 0; i < 30; i++) {
			OpenAddressing.Bucket bucket = new OpenAddressing.Bucket();
			if (i >= 20 ) {
				bucket.setKey(String.valueOf(20));
				bucket.setValue(20);
			} else {
				bucket.setKey(String.valueOf(i));
				bucket.setValue(i);
			}
			openAddressing.addData(hashTable, bucket);
		}

		System.out.println("========================데이터넣은후=======================");
		hashTable.print();

		System.out.println("========================테이블이 꽉찼을경우=======================");
		OpenAddressing.Bucket bucket = new OpenAddressing.Bucket();
		bucket.setKey(String.valueOf(1));
		bucket.setValue(1);
		openAddressing.addData(hashTable, bucket);



		System.out.println("========================찾는데 있을경우=======================");
		String searchKey = "1";
		OpenAddressing.Bucket searchBucket = openAddressing.search(hashTable, searchKey);
		if (bucket != null) {
			System.out.println("Find in the HashTable, Bucket : " + searchBucket);
		} else {
			System.out.println("HashTable don't has " + searchKey);
		}

		System.out.println("========================찾는데 지워져서 없을경우=======================");
		openAddressing.removeData(hashTable, searchKey);
		bucket = openAddressing.search(hashTable, searchKey);
		if (bucket != null) {
			System.out.println("Find in the HashTable, Bucket : " + '1');
		} else {
			System.out.println("HashTable don't has " + searchKey);
		}



		System.out.println("========================지워도 있을경우=======================");
		searchKey = "20";
		bucket = openAddressing.search(hashTable, searchKey);
		if (bucket != null) {
			System.out.println("Find in the HashTable, Bucket : " + bucket);
		} else {
			System.out.println("HashTable don't has " + searchKey);
		}

	}
}