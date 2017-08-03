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
			if (i >= 20 ) {
				openAddressing.addData(hashTable, String.valueOf(20));
			} else {
				openAddressing.addData(hashTable, String.valueOf(i));
			}
		}

		System.out.println("========================데이터넣은후=======================");
		hashTable.print();

		System.out.println("========================테이블이 꽉찼을경우=======================");
		openAddressing.addData(hashTable, String.valueOf(1));



		System.out.println("========================찾는데 있을경우=======================");
		char searchKey = '1';
		OpenAddressing.Bucket bucket = openAddressing.search(hashTable, searchKey);
		if (bucket != null) {
			System.out.println("Find in the HashTable, Bucket : " + bucket);
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
		searchKey = '2' + '0';
		bucket = openAddressing.search(hashTable, searchKey);
		if (bucket != null) {
			System.out.println("Find in the HashTable, Bucket : " + bucket);
		} else {
			System.out.println("HashTable don't has " + searchKey);
		}

	}
}