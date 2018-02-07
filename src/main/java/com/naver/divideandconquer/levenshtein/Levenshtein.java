/*
 *@(#)Levenshtein.java 2018.02.07
 *
 * Copyright 2018 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.divideandconquer.levenshtein;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 *
 * @author kim.minjoo
 */
public class Levenshtein {
	public static int calculateDistance(String str1, String str2) {
		return calculateDistance(str1, str2, 0);
	}

	private static int calculateDistance(String str1, String str2, int distance) {
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
			return calculateDistance(str1.substring(0, str1.length() - 1), str2.substring(0, str2.length() - 1), distance);
		} else {
			int case1 = calculateDistance(str1.substring(0, str1.length() - 1), str2.substring(0, str2.length() - 1), distance + 1);
			int case2 = calculateDistance(str1, str2.substring(0, str2.length() - 1), distance + 1);
			int case3 = calculateDistance(str1.substring(0, str1.length() - 1), str2, distance + 1);

			return NumberUtils.min(case1, case2, case3);
		}
	}
}