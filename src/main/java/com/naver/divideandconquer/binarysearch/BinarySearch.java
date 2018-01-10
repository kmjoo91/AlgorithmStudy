/*
 *@(#)BinarySearch.java 2017.11.21
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.divideandconquer.binarysearch;

import java.util.List;

/**
 *
 *
 * @author kim.minjoo
 */
public class BinarySearch {
	public <T extends Comparable> int searchRecursive(List<T> list, T key) {
		return searchRecursive(list, 0, list.size() - 1, key);
	}

	public <T extends Comparable> int searchRecursive(List<T> list, int start, int end, T key) {
		if (start == end) {
			if (list.get(start).equals(key)) {
				return start;
			} else {
				return -1;
			}
		}

		int middle = (start + end) / 2;

		if (list.get(middle).compareTo(key) >= 0) {
			return searchRecursive(list, start, middle, key);
		} else {
			return searchRecursive(list, middle + 1, end, key);
		}
	}
}