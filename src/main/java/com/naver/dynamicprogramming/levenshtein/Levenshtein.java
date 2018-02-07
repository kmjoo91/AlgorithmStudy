/*
 *@(#)Levenshtein.java 2018.02.07
 *
 * Copyright 2018 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.dynamicprogramming.levenshtein;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 *
 * @author kim.minjoo
 */
public class Levenshtein {
	public static int calculateDistanceRecursive(String str1, String str2) {
		return calculateDistanceRecursive(str1, str2, 0);
	}

	private static int calculateDistanceRecursive(String str1, String str2, int distance) {
		if (StringUtils.isBlank(str1) && StringUtils.isBlank(str2)) {
			return distance;
		}

		if (StringUtils.isBlank(str1)) {
			return distance + str2.length();
		}

		if (StringUtils.isBlank(str2)) {
			return distance + str1.length();
		}

		boolean isEqualEndChar = str1.charAt(str1.length() - 1) == str2.charAt(str2.length() - 1);

		if (isEqualEndChar) {
			return calculateDistanceRecursive(str1.substring(0, str1.length() - 1), str2.substring(0, str2.length() - 1), distance);
		} else {
			int case1 = calculateDistanceRecursive(str1.substring(0, str1.length() - 1), str2.substring(0, str2.length() - 1), distance + 1);
			int case2 = calculateDistanceRecursive(str1, str2.substring(0, str2.length() - 1), distance + 1);
			int case3 = calculateDistanceRecursive(str1.substring(0, str1.length() - 1), str2, distance + 1);

			return NumberUtils.min(case1, case2, case3);
		}
	}


	public static int calculateDistance(String str1, String str2) {
		int[][] distanceArr = new int[str1.length()][str2.length()];
		for (int row = 0; row < distanceArr.length; row++) {
			for (int column = 0; column < distanceArr[row].length; column++) {
				distanceArr[row][column] = 0;
			}
		}

		for (int row = 0; row < distanceArr.length; row++) {
			for (int column = 0; column < distanceArr[row].length; column++) {
				boolean isEqualEndCahr = str1.charAt(row) == str2.charAt(column);
				//ArrayOutOfIndexException을 피하기 위해 row, column값이 0일때를 피해준다.
				if (row == 0 && column == 0) {
					if (isEqualEndCahr) {
						distanceArr[row][column] = 0;
					} else {
						distanceArr[row][column] = 1;
					}
				} else if (row == 0) {
					if (isEqualEndCahr) {
						distanceArr[row][column] = distanceArr[row][column - 1];
					} else {
						distanceArr[row][column] = distanceArr[row][column - 1] + 1;
					}
				} else if (column == 0) {
					if (isEqualEndCahr) {
						distanceArr[row][column] = distanceArr[row - 1][column];
					} else {
						distanceArr[row][column] = distanceArr[row - 1][column] + 1;
					}
				} else {
					if (isEqualEndCahr) {
						distanceArr[row][column] = NumberUtils.min(distanceArr[row - 1][column - 1],
								distanceArr[row][column - 1], distanceArr[row - 1][column]);
					} else {
						distanceArr[row][column] =
								NumberUtils.min(distanceArr[row - 1][column - 1], distanceArr[row][column - 1],
										distanceArr[row - 1][column]) + 1;
					}
				}
			}
		}

		return distanceArr[str1.length() -1][str2.length() - 1];
	}
}