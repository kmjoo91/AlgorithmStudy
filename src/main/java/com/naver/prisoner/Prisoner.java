/*
 *@(#)Prisoner.java 2017.02.24
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.prisoner;

/**
 *
 *
 * @author kim.minjoo
 */
public class Prisoner {
	int range;

	public Prisoner(int range) {
		this.range = range;
	}

	public int getReleasePrisoner() {
		return getReleasePrisoner(range);
	}

	public int getReleasePrisoner(int range) {
		for (int number = 1; number <= range; number++) {
			if (number*number > range) {
				return number-1;
			}
		}

		//위에서 끝날 수 박에없음. 따라서 여기로 오면 에러.
		return -1;
	}

}