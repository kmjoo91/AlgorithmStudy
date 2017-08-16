/*
 *@(#)MyHashMap.java 2017.08.04
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.hash;

/**
 *
 *
 * @author kim.minjoo
 */
public class MyHashMap<K,V> {
	public static final int BUCKET_SIZE = 100;
	private Object[] hashTable = new Object[BUCKET_SIZE];

	public static enum HashBucketStatus {
		EMPTY,
		USED,
		DELETED
	}

	private class Bucket<K, V> {
		K key;
		V value;
		HashBucketStatus hashBucketStatus;

		Bucket() {
			this.hashBucketStatus = HashBucketStatus.EMPTY;
		}

		Bucket(K key, V value) {
			this.key = key;
			this.value = value;
			this.hashBucketStatus = HashBucketStatus.EMPTY;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public HashBucketStatus getHashBucketStatus() {
			return hashBucketStatus;
		}

		public void setHashBucketStatus(HashBucketStatus hashBucketStatus) {
			this.hashBucketStatus = hashBucketStatus;
		}
	}

	public boolean put(K key, V value) {
		if (key == null) {
			throw new IllegalArgumentException("Key should not be null. but the current key is null.");
		}

		int firstIndex = hashFunction(key);
		int bucketIndex = hashFunction(key);
		int tryCount = 0;
		do {
			Bucket currentBucket = (Bucket)hashTable[bucketIndex];
			if (currentBucket == null) {
				currentBucket = new Bucket();
				hashTable[bucketIndex] = currentBucket;
			}

			if (isEmptyBucket(currentBucket.getHashBucketStatus())) {
				currentBucket.setKey(key);
				currentBucket.setValue(value);
				currentBucket.setHashBucketStatus(HashBucketStatus.USED);
				return true;
			} else {
				tryCount++;
				bucketIndex = hashFunction(key) + tryCount;
			}

			if (bucketIndex < 0 || bucketIndex >= BUCKET_SIZE) {
				throw new IndexOutOfBoundsException("BucketIndex is Out of BucketSize");
			}
		} while (firstIndex != bucketIndex);

		return false;
	}

	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Key should not be null. but the current key is null.");
		}

		Bucket<K, V> searchBucket = search(key);
		if (searchBucket == null) {
			return null;
		}

		return searchBucket.getValue();
	}

	private Bucket search(K key) {
		int firstIndex = hashFunction(key);
		int bucketIndex = hashFunction(key);
		int tryCount = 0;
		do {
			Bucket currentBucket = (Bucket)hashTable[bucketIndex];
			if (currentBucket == null) {
				return null;
			}
			if (HashBucketStatus.USED.equals(currentBucket.getHashBucketStatus())) {
				return currentBucket;
			} else if (HashBucketStatus.DELETED.equals(currentBucket.getHashBucketStatus())) {
				tryCount++;
				bucketIndex = hashFunction(key) + tryCount;
			} else {
				return null;
			}

			if (bucketIndex < 0 || bucketIndex >= BUCKET_SIZE) {
				throw new IndexOutOfBoundsException("BucketIndex is Out of BucketSize");
			}
		} while (firstIndex != bucketIndex);

		return null;
	}

	public boolean remove(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Key should not be null. but the current key is null.");
		}

		Bucket<K, V> removeBucket = search(key);
		if (removeBucket == null) {
			return false;
		}

		removeBucket.setHashBucketStatus(HashBucketStatus.DELETED);
		return true;
	}

	private boolean isEmptyBucket(HashBucketStatus hashBucketStatus) {
		return HashBucketStatus.EMPTY.equals(hashBucketStatus)
				|| HashBucketStatus.DELETED.equals(hashBucketStatus);
	}

	private int hashFunction(K key) {
		if (key instanceof Integer) {
			return (Integer)key % 100;
		}

		if (key instanceof String) {
			return convertStringToNmber((String)key) % 100;
		}

		return key.hashCode();
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
}