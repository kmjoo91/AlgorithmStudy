/*
 *@(#)OpenAddressingBookVersion.java 2017.08.03
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author kim.minjoo
 */
public class OpenAddressingBookVersion implements OpenAddressing{
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenAddressingBookVersion.class);
	@Override
	public HashTable createHashTable(int bucketSize) {
		if (bucketSize <= 0) {
			throw new IllegalArgumentException("The bucketsize is less than zero.");
		}

		HashTable hashTable = new HashTable();
		hashTable.setCurrentCount(0);
		hashTable.setBucketSize(bucketSize);

		Bucket[] buckets = new Bucket[hashTable.getBucketSize()];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new Bucket();
		}
		hashTable.setBuckets(buckets);

		return hashTable;
	}

	@Override
	public void deleteHashTable(HashTable hashTable) {
		hashTable = null;
	}

	@Override
	public boolean addData(HashTable hashTable, String data) {
		if (hashTable == null) {
			throw new IllegalArgumentException("HashTable is null!!!");
		}

		int hash = convertStringToNmber(data);
		int firstIndex = hashFunction(hash, hashTable.getBucketSize());
		int bucketIndex = hashFunction(hash, hashTable.getBucketSize());
		int tryCount = 0;
		do {
			Bucket currentBucket = hashTable.getBuckets()[bucketIndex];
			if (isEmptyBucket(currentBucket.getHashBucketStatus())) {
				currentBucket.setHashBucketStatus(HashBucketStatus.USED);
				int key = convertStringToNmber(data);
				currentBucket.setKey(key);
				currentBucket.setValue(data);
				hashTable.setCurrentCount(hashTable.getCurrentCount() + 1);
				return true;
			} else {
				tryCount++;
				bucketIndex = hashFunction(hash + tryCount, hashTable.getBucketSize());
			}

			if (bucketIndex < 0 || bucketIndex >= hashTable.getBucketSize()) {
				throw new IndexOutOfBoundsException("BucketIndex is Out of BucketSize");
			}
		} while (firstIndex != bucketIndex);

		LOGGER.error("HashTable is Full!!!!!!");
		System.out.println("HashTable is Full!!!! data :" + data + " key : " + convertStringToNmber(data));
		return false;
	}

	private int convertStringToNmber(String data) {
		int sum = 0;
		for (int i = 0; i < data.length(); i++) {
			if ("10".equals(data)) {
				System.out.println("");
			}
			int ascii = data.charAt(i);
			sum += ascii;
		}
		return sum;
	}

	private boolean isEmptyBucket(HashBucketStatus hashBucketStatus) {
		return hashBucketStatus.equals(HashBucketStatus.EMPTY)
				|| hashBucketStatus.equals(HashBucketStatus.DELETED);
	}

	private int hashFunction(int data, int bucketSize) {
		return data % bucketSize;
	}

	@Override
	public boolean removeData(HashTable hashTable, int searchKey) {
		if (hashTable == null) {
			throw new IllegalArgumentException("HashTable is null!!!");
		}

		Bucket bucket = search(hashTable, searchKey);
		if (bucket != null) {
			bucket.setKey(Bucket.EMPTY);
			bucket.setHashBucketStatus(HashBucketStatus.DELETED);
			hashTable.setCurrentCount(hashTable.getCurrentCount());
			return true;
		}

		LOGGER.info("Fail to remove, HashTable don't has {}", searchKey);
		return false;
	}

	@Override
	public Bucket search(HashTable hashTable, int searchKey) {
		if (hashTable == null) {
			throw new IllegalArgumentException("HashTable is null!!!");
		}

		int firstIndex = hashFunction(searchKey, hashTable.getBucketSize());

		if (firstIndex < 0 || firstIndex >= hashTable.getBucketSize()) {
			throw new IndexOutOfBoundsException("BucketIndex is Out of BucketSize");
		}

		int bucketIndex = hashFunction(searchKey, hashTable.getBucketSize());
		int tryCount = 0;
		do {
			Bucket currentBucket = hashTable.getBuckets()[bucketIndex];
			if (currentBucket.getHashBucketStatus().equals(HashBucketStatus.USED)) {
				if (currentBucket.getKey() == (searchKey)) {
					return currentBucket;
				} else {
					tryCount++;
					bucketIndex = hashFunction(searchKey + tryCount, hashTable.getBucketSize());
				}
			} else if (currentBucket.getHashBucketStatus().equals(HashBucketStatus.DELETED)) {
				tryCount++;
				bucketIndex = hashFunction(searchKey + tryCount, hashTable.getBucketSize());
			} else{
				return null;
			}

			if (bucketIndex < 0 || bucketIndex >= hashTable.getBucketSize()) {
				throw new IndexOutOfBoundsException("BucketIndex is Out of BucketSize");
			}
		} while (firstIndex != bucketIndex);

		LOGGER.info("HashTable don't has {}", searchKey);
		return null;
	}

	@Override
	public int getCount(HashTable hashTable) {
		if (hashTable == null) {
			throw new IllegalArgumentException("HashTable is null!!!");
		}

		return hashTable.getCurrentCount();
	}
}