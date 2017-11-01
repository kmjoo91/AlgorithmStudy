/*
 *@(#)UnitValueComparator.java 2017.11.01
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.greedy.knapsack;

import java.util.Comparator;

/**
 *
 *
 * @author kim.minjoo
 */
public class UnitValueComparator implements Comparator<Item> {
	@Override
	public int compare(Item item1, Item item2) {
		return item1.getUnitValue() > item2.getUnitValue() ? -1 : item1.getUnitValue() < item2.getUnitValue() ? 1 : 0;
	}
}