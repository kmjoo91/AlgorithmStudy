package com.naver.baekjoon.problem6603.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
	int index;
	int level;
	List<Node> children;

	public Node(int index) {
		this.index = index;
		this.level = 0;
		this.children = new ArrayList<>();
	}

	public Node(int index, int level) {
		this.index = index;
		this.level = level;
		this.children = new ArrayList<>();
	}

	public int getIndex() {
		return index;
	}

	public int getLevel() {
		return level;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void addChild(int index) {
		Node child = new Node(index, this.level + 1);
		addChild(child);
	}

	public void addChild(Node node) {
		children.add(node);
	}
}
