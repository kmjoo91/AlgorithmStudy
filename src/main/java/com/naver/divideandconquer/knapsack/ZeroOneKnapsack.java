/*
 *@(#)ZeroOneKnapsack.java 2018.01.17
 *
 * Copyright 2018 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.divideandconquer.knapsack;

import java.util.List;

import com.naver.divideandconquer.knapsack.model.Item;

/**
 *
 *
 * @author kim.minjoo
 */
public class ZeroOneKnapsack {
	public static int calculate(int capacity, List<Item> itemList, int itemCount) {
		if (capacity == 0 || itemCount == 0) {
			return 0;
		}

		if (itemList.get(itemCount - 1).getCapacity() > capacity) {
			return calculate(capacity, itemList, itemCount - 1);
		}

		int selected = calculate(capacity - itemList.get(itemCount - 1).getCapacity(), itemList, itemCount - 1) + itemList.get(itemCount - 1).getValue();
		int unSelected = calculate(capacity, itemList, itemCount - 1);

		return selected > unSelected ? selected : unSelected;
	}
}