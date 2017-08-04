/*
 *@(#)OpenAddressing.java 2017.08.03
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
public interface OpenAddressing {
	int hashKeySize = 30;
	public static enum HashBucketStatus {
		EMPTY,
		USED,
		DELETED
	}

	public class Bucket {
		public static final String EMPTY = "";

		String key;
		Object value;
		HashBucketStatus hashBucketStatus;

		public Bucket() {
			this.key = EMPTY;
			this.value = null;
			this.hashBucketStatus = HashBucketStatus.EMPTY;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String value) {
			this.key = value;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public HashBucketStatus getHashBucketStatus() {
			return hashBucketStatus;
		}

		public void setHashBucketStatus(HashBucketStatus hashBucketStatus) {
			this.hashBucketStatus = hashBucketStatus;
		}

		@Override
		public String toString() {
			return "Bucket{" +
					"key=" + key +
					", value='" + value + '\'' +
					", hashBucketStatus=" + hashBucketStatus +
					'}';
		}
	}

	public class HashTable{
		private int bucketSize;
		private int currentCount;
		private Bucket[] buckets;

		public int getBucketSize() {
			return bucketSize;
		}

		public void setBucketSize(int bucketSize) {
			this.bucketSize = bucketSize;
		}

		public int getCurrentCount() {
			return currentCount;
		}

		public void setCurrentCount(int currentCount) {
			this.currentCount = currentCount;
		}

		public Bucket[] getBuckets() {
			return buckets;
		}

		public void setBuckets(Bucket[] bucket) {
			this.buckets = bucket;
		}

		public void print() {
			for (Bucket bucket : buckets) {
				System.out.println(bucket);
			}
		}
	}

	public HashTable createHashTable(int bucketSize);
	public void deleteHashTable(HashTable hashTable);
	public boolean addData(HashTable hashTable, Bucket bucket);
	public boolean removeData(HashTable hashTable, String searchKey);
	public Bucket search(HashTable hashTable, String searchKey);
	public int getCount(HashTable hashTable);
}