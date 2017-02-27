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
		return (int) Math.sqrt(range);
	}

}