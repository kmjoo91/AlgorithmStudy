/*
 *@(#)Level.java 2017.09.01
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.tree.balancedsearchtree.model;

/**
 *
 *
 * @author kim.minjoo
 */
public class Level {
	int m;
	int count;
	Node[] nodes;
	Level[] children;

	public Level(int m) {
		this.m = m;
		this.count = m;
		this.nodes = new Node[m];
		this.children = new Level[m+1];
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Node[] getNodes() {
		return nodes;
	}

	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}

	public Level[] getChildren() {
		return children;
	}

	public void setChildren(Level[] children) {
		this.children = children;
	}
}