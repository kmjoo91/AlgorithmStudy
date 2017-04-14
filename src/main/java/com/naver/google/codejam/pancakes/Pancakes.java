/*
 *@(#)Pancakes.java 2017.04.14
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.google.codejam.pancakes;

/**
 *
 *
 * @author kim.minjoo
 */
public class Pancakes {
	private String pancakes;

	public void setPancakes(String pancakes) {
		this.pancakes = pancakes;
	}

	public int getFlipCount() {
		return getFlipCount(pancakes);
	}

	public int getFlipCount(String pancakes) {
		return executeFilpPancakes(pancakes, pancakes.length(), 0);
	}

	private int executeFilpPancakes(String pancakes, int index, int flipCount) {
		if (index == 1) {
			return (pancakes.charAt(pancakes.length()-1) == '+') ? flipCount : flipCount+1;
		}
		if ((pancakes.charAt(index-1) == pancakes.charAt(index-2))) {
			return executeFilpPancakes(pancakes, index-1, flipCount);
		} else {
			return executeFilpPancakes(pancakes, index-1, flipCount+1);
		}
	}

	public int getFlipCount2(String pancakes) {
		int flipCount = 0;
		for (int i = pancakes.length() - 1; i > 0 ; i--) {
			if (pancakes.charAt(i) != pancakes.charAt(i-1)) {
				flipCount++;
			}
		}

		return (pancakes.charAt(pancakes.length()-1) == '+') ? flipCount : flipCount+1;
	}
}