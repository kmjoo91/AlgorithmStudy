/*
 *@(#)ZeroOneKnapsack.java 2018.01.24
 *
 * Copyright 2018 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.dynamicprogramming.knapsack;

import java.util.List;

import com.naver.dynamicprogramming.knapsack.model.Item;

/**
 *
 *
 * @author kim.minjoo
 */
public class ZeroOneKnapsack {
	public static int calculate(int capacity, List<Item> itemList, int itemCount) {
		int[][] memo = new int[itemCount + 1][capacity + 1];
		for (int i = 0; i < itemCount + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				memo[i][j] = 0;
			}
		}

		for (int i = 1; i < itemCount + 1; i++) {
			for (int j = 0; j < capacity + 1; j++) {
				int unSelected = memo[i - 1][j];
				int selected = 0;

				if (j >= itemList.get(i - 1).getCapacity()) {
					selected = memo[i - 1][j - itemList.get(i - 1).getCapacity()] + itemList.get(i - 1).getValue();
				}

				memo[i][j] = selected > unSelected ? selected : unSelected;
			}
		}

		return memo[itemList.size()][capacity];
	}

}