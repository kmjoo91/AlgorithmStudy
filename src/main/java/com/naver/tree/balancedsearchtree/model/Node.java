/*
 *@(#)Node.java 2017.09.01
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.tree.balancedsearchtree.model;

/**
 *
 *
 * @author kim.minjoo
 */
public class Node {
	int key;
	String value;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node{" +
				"key=" + key +
				", value='" + value + '\'' +
				'}';
	}
}