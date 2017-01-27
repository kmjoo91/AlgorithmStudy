/*
 *@(#)WordLadder.java 2017.01.20
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.WordLadder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

import com.naver.WordLadder.model.Node;

/**
 *
 *
 * @author kim.minjoo
 */
public class WordLadder {
	private String startString;
	private String endString;
	private Set<String> dictionary;
	Queue<String> list = new LinkedList<String>();
	List<String> path = new ArrayList<String>();

	public void setStartString(String startString) {
		this.startString = startString;
	}

	public void setEndString(String endString) {
		this.endString = endString;
	}

	public void setDictionary(Set<String> dictionary) {
		this.dictionary = dictionary;
	}

	public WordLadder(String startString, String endString, Set<String> dictionary) {
		this.startString = startString;
		this.endString = endString;
		this.dictionary = dictionary;
	}

	public int  calculateWordLadder() {
		return calculateWordLadder(startString, endString);
	}

	public int  calculateWordLadder(String startString, String endString) {
		int editdistance = calculateEditDistance(startString, endString);

		if (editdistance < 2) {
			return editdistance;
		}

		//리스트에서 꺼내서 다시 돌려!
		int distance = 1;
		list.offer(startString);
		int level = list.size();
		int nextLevel = 0;
		String str;
		while ((str = list.poll()) != null) {
			path.add(str);
			Set<String> oneDistanceWords = getOneDistanceWords(str);
			nextLevel += oneDistanceWords.size();
			for (String oneDistanceWord : oneDistanceWords) {
				if (path.contains(oneDistanceWord)) {
					continue;
				}
				if (calculateEditDistance(oneDistanceWord, endString) == 1) {
					//start랑 end도 디스턴스에 포함.
					return distance+2;
				}
				list.offer(oneDistanceWord);
			}
			level--;
			if (level == 0) {
				level = nextLevel;
				nextLevel = 0;
				distance++;
			}
		}

		//길이없다.
		return -1;
	}

	private Set<String> getOneDistanceWords(String startString) {
		Set<String> oneDistanceWords = new HashSet<String>();
		for (String string : dictionary) {
			int distance = calculateEditDistance(startString, string);
			if (distance == 1) {
				oneDistanceWords.add(string);
			}
		}
		return oneDistanceWords;
	}

	private int calculateEditDistance(String startString, String endString) {
		if (startString.equals(endString)) {
			return 0;
		}
		int[][] distanceArray = createDistanceArray(startString, endString);
		for (int i = 1; i < distanceArray.length; i++) {
			for (int j = 1; j < distanceArray[0].length; j++) {
				if (startString.charAt(i-1) == endString.charAt(j-1)) {
					distanceArray[i][j] = distanceArray[i-1][j-1];
				} else {
					distanceArray[i][j] = NumberUtils.min(distanceArray[i - 1][j], distanceArray[i][j - 1], distanceArray[i - 1][j - 1]) + 1;
				}
			}
		}

		return distanceArray[startString.length()][endString.length()];
	}

	private int[][] createDistanceArray(String startString, String endString) {
		int[][] distanceArray = new int[startString.length()+1][endString.length()+1];
		for (int i = 0; i < distanceArray.length; i++) {
			distanceArray[i][0] = i;
 		}

 		for (int i = 0; i < distanceArray[0].length; i++) {
			distanceArray[0][i] = i;
	    }

	    return distanceArray;
	}

	public Node<String> getLeaf() {
		Queue<Node<String>> queue = new LinkedList<Node<String>>();
		List<String> path = new ArrayList<String>();


		queue.offer(new Node<String>(startString));
		Node<String> currentNode;
		while ((currentNode = queue.poll()) != null) {
		 	String str = currentNode.getData();
			path.add(str);
			Set<String> oneDistanceWords = getOneDistanceWords(str);
			for (String oneDistanceWord : oneDistanceWords) {
				if (path.contains(oneDistanceWord)) {
					continue;
				}
				Node<String> child = new Node<String>(oneDistanceWord);
				child.setParent(currentNode);
				if (calculateEditDistance(oneDistanceWord, endString) == 1) {
					//start랑 end도 디스턴스에 포함.
					Node<String> leaf = new Node<String>(endString);
					leaf.setParent(child);

					return leaf;
				}
				queue.offer(child);
			}
		}

		return new Node<String>(startString);
	}

	public void printTree() {
		Queue<Node<String>> queue = new LinkedList<Node<String>>();
		List<String> path = new ArrayList<String>();


		queue.offer(new Node<String>(startString));
		Node<String> currentNode;
		while ((currentNode = queue.poll()) != null) {
			String str = currentNode.getData();
			path.add(str);
			Set<String> oneDistanceWords = getOneDistanceWords(str);
			for (String oneDistanceWord : oneDistanceWords) {
				if (path.contains(oneDistanceWord)) {
					continue;
				}
				Node<String> child = new Node<String>(oneDistanceWord);
				child.setParent(currentNode);
				if (calculateEditDistance(oneDistanceWord, endString) == 1) {
					Node<String> leaf = new Node<String>(endString);
					leaf.setParent(child);
					leaf.printTree();
				}
				queue.offer(child);
			}
		}
	}



}