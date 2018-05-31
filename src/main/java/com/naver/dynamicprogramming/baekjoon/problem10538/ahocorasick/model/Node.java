package com.naver.dynamicprogramming.baekjoon.problem10538.ahocorasick.model;

import java.util.HashMap;
import java.util.Map;

public class Node {
	private Map<String, Node> children;
	private Node failureLink;
	private Node outputLink;
	private boolean isSuccessNode;
	private char ch;

	public Node() {
		children = new HashMap<>();
	}

	public Map<String, Node> getChildren() {
		return children;
	}

	public void setChildren(Map<String, Node> children) {
		this.children = children;
	}

	public Node getFailureLink() {
		return failureLink;
	}

	public void setFailureLink(Node failureLink) {
		this.failureLink = failureLink;
	}

	public Node getOutputLink() {
		return outputLink;
	}

	public void setOutputLink(Node outputLink) {
		this.outputLink = outputLink;
	}

	public boolean isSuccessNode() {
		return isSuccessNode;
	}

	public void setSuccessNode(boolean successNode) {
		isSuccessNode = successNode;
	}

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}

	public void addChild(char ch, boolean isFinish) {
		Node child = this.children.get(String.valueOf(ch));
		if (child == null) {
			child = new Node();
			child.setCh(ch);
			this.children.put(String.valueOf(ch), child);
		}

		if (isFinish) {
			child.isSuccessNode = true;
		}
	}

	public Node getChild(char ch) {
		return this.children.get(String.valueOf(ch));
	}

	public void print() {
		System.out.println("ch : " + ch);
		System.out.println("children :");
		for (Map.Entry<String, Node> entry : children.entrySet()) {
			System.out.println(entry.getKey());
		}
		System.out.println("failureLink : " + failureLink.getCh());
		if (outputLink == null) {
			System.out.println("outputLink : null");
		} else {
			System.out.println("outputLink : " + outputLink.getCh());
		}
		System.out.println("isSuccessNode : " +isSuccessNode);
	}
}
