/*
 *@(#)Knapsack.java 2017.11.01
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.greedy.knapsack;

import java.util.Collections;
import java.util.List;

/**
 *
 *
 * @author kim.minjoo
 */
public class Knapsack {
	private List<Item> itemList;
	private double weight;

	public Knapsack(List<Item> itemList, double weight) {
		this.itemList = itemList;
		this.weight = weight;
	}

	public void printFractionalKnapsackProblem() {
		Collections.sort(itemList, new UnitValueComparator());

		double remainnigWeight = weight;
		for (Item item : itemList) {
			if (remainnigWeight > 0) {
				double number = remainnigWeight / item.getWeight();
				number = number > (double)item.getNumber() ? item.getNumber() : number;
				System.out.println("Item : " + item.getName() + " num : " + number);
				remainnigWeight -= item.getWeight() * number;
			} else {
				System.out.println("Item : " + item.getName() + " num : " + 0);
			}
		}
	}

	/**
	 * 배낭문제에서 쓰일 함수... 부분배낭문제에선 안쓰임!
	 * @param itemList
	 * @param weight
	 */
	private void removeUnnecessaryItems(List<Item> itemList, double weight) {
		int listSize = itemList.size();
		for (int i = listSize - 1; i >= 0; i--) {
			if (itemList.get(i).getWeight() > weight) {
				System.out.println("Item : " + itemList.get(i).getName() + " num : " + 0);
				itemList.remove(i);
			}
		}
	}
}