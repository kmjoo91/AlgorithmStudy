/*
 *@(#)WordLadder.java 2017.01.20
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.WordLadder;

import java.util.Set;

/**
 *
 *
 * @author kim.minjoo
 */
public class WordLadder {
	private String startString;
	private String endString;
	private Set<String> dictionary;

	public WordLadder () {
	}

	public WordLadder(String startString, String endString, Set<String> dictionary) {
		this.startString = startString;
		this.endString = endString;
		this.dictionary = dictionary;
	}

	public Set<String> calculate() {

	}

}