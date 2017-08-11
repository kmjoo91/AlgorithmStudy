/*
 *@(#)Chaining.java 2017.08.09
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.hash.chaining;

import java.util.Arrays;
import java.util.LinkedList;

import com.naver.hash.chaining.linkedlist.MyLinkedList;
import com.naver.hash.chaining.linkedlist.MyNode;

/**
 *
 *
 * @author kim.minjoo
 */
public class Chaining<K, V> {
	private static final int TABLE_SIZE = 30;

	private class Bucket<K, V> {
		MyLinkedList<K, V> slots;

		public Bucket() {
			slots = new MyLinkedList<K, V>();
		}

		public MyLinkedList<K, V> getSlots() {
			return slots;
		}

		public void setSlots(MyLinkedList<K, V> slots) {
			this.slots = slots;
		}

		@Override
		public String toString() {
			return "Bucket{" +
					"slots=" + slots +
					'}';
		}
	}

	private Bucket[] hashTable;

	public Chaining() {
		this.hashTable = new Bucket[TABLE_SIZE];
		for (int i = 0; i < this.hashTable.length; i++) {
			this.hashTable[i] = new Bucket();
		}
	}

	/**
	 * 같은 해시일 경우 늦게들어온 slot이 앞에 추가 되는 함수.
	 * @param key 검색키
	 * @param value 값
	 */
	public void add(K key, V value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Parameter should be not Null, but parameter is null");
		}

		int hash = hashFunction(key);

		if (hash < 0 || hash >= TABLE_SIZE) {
			throw new IndexOutOfBoundsException("BucketIndex is Out of BucketSize");
		}

		MyLinkedList<K, V> slots = hashTable[hash].slots;
		MyNode node = slots.search(key);
		if (node != null) {
			node.setData(value);
			return;
		}

		slots.add(key, value);
	}

	/**
	 * 같은 해시일 경우 늦게들어온 slot이 뒤에 추가 되는 함수.
	 * @param key 검색키
	 * @param value 값
	 */
	public void put(K key, V value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Parameter should be not Null, but parameter is null");
		}

		int hash = hashFunction(key);

		if (hash < 0 || hash >= TABLE_SIZE) {
			throw new IndexOutOfBoundsException("BucketIndex is Out of BucketSize");
		}

		MyLinkedList<K, V> slots = hashTable[hash].slots;
		MyNode node = slots.search(key);
		if (node != null) {
			node.setData(value);
		}

		slots.put(key, value);
	}

	public void remove(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Key should be not Null, but key is null");
		}

		int hash = hashFunction(key);

		if (hash < 0 || hash >= TABLE_SIZE) {
			throw new IndexOutOfBoundsException("BucketIndex is Out of BucketSize");
		}

		MyLinkedList<K, V> slots = hashTable[hash].slots;
		slots.remove(key);
	}

	public V get(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Key should be not Null, but key is null");
		}

		int hash = hashFunction(key);

		if (hash < 0 || hash >= TABLE_SIZE) {
			throw new IndexOutOfBoundsException("BucketIndex is Out of BucketSize");
		}

		MyLinkedList<K, V> slots = hashTable[hash].slots;
		MyNode node = slots.search(key);

		if (node == null) {
			return null;
		}

		return (V)slots.search(key).getData();
	}

	private int hashFunction(K key) {
		if (key instanceof Integer) {
			return (Integer)key % TABLE_SIZE;
		} else if (key instanceof String ) {
			return convertStringToNmber((String)key)%TABLE_SIZE;
		}
		return key.hashCode()%TABLE_SIZE;
	}

	private int convertStringToNmber(String data) {
		int sum = 0;
		for (int i = 0; i < data.length(); i++) {
			int ascii = data.charAt(i);
			sum += ascii;
		}
		return sum;
	}

	@Override
	public String toString() {
		return "Chaining{" +
				"hashTable=" + Arrays.toString(hashTable) +
				'}';
	}
}