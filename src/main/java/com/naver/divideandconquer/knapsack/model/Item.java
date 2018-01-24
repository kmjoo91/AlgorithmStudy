/*
 *@(#)Item.java 2018.01.24
 *
 * Copyright 2018 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.divideandconquer.knapsack.model;

/**
 *
 *
 * @author kim.minjoo
 */
public class Item {
	int capacity;
	int value;

	public Item(int capacity, int value) {
		this.capacity = capacity;
		this.value = value;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}