/*
 *@(#)WordLadderTest.java 2017.01.23
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.wordladder;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.naver.wordladder.model.Node;

/**
 *
 *
 * @author kim.minjoo
 */
public class WordLadderTest {
	String start = "hit";
	String end = "cogh";
	Set<String> dictionary = new HashSet<String>();
	{
		dictionary.add("hot");
		dictionary.add("dot");
		dictionary.add("dog");
		dictionary.add("doh");
		dictionary.add("coh");
		dictionary.add("lot");
		dictionary.add("log");
		dictionary.add("cog");
	}

	public WordLadder wordLadder = new WordLadder(start, end, dictionary);

	@Test
	public void 테스트() {
		int distance = wordLadder.calculateWordLadder();
		System.out.println(distance);
	}

	@Test
	public void 트리테스트() {
		Node<String> leaf = wordLadder.getLeaf();
		leaf.printTree();
	}

	@Test
	public void 전부출력트리테스트() {
		wordLadder.printTree();
	}

}