/*
 *@(#)MyNode.java 2017.08.09
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.hash.chaining.linkedlist;

/**
 *
 *
 * @author kim.minjoo
 */
public class MyNode<K, V> {
	MyNode prev;
	MyNode next;
	K key;
	V data;

	public MyNode() {
	}

	public MyNode(K key, V data) {
		this.key = key;
		this.data = data;
	}

	public MyNode getPrev() {
		return prev;
	}

	void setPrev(MyNode prev) {
		this.prev = prev;
	}

	public MyNode getNext() {
		return next;
	}

	void setNext(MyNode next) {
		this.next = next;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getData() {
		return data;
	}

	public void setData(V data) {
		this.data = data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MyNode<?, ?> myNode = (MyNode<?, ?>)o;

		return key != null ? key.equals(myNode.key) : myNode.key == null;
	}

	@Override
	public int hashCode() {
		return key != null ? key.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "MyNode{" +
				"key=" + key +
				", data=" + data +
				'}';
	}
}