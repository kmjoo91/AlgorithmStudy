package com.naver.dynamicprogramming.baekjoon.problem10538.ahocorasick;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.naver.dynamicprogramming.baekjoon.problem10538.ahocorasick.model.Node;

public class AhoCorasick {
	Node root;
	Node currentNode;

	private AhoCorasick() {
		root = new Node();

		root.setFailureLink(root);
		root.setSuccessNode(false);
		root.setOutputLink(null);
	}

	private void add(String pattern) {
		Node currentNode = this.root;
		int index = 0;

		while (index < pattern.length()) {
			char currentChar = pattern.charAt(index);
			currentNode.addChild(currentChar, index == pattern.length() - 1);
			currentNode = currentNode.getChild(currentChar);
			index++;
		}
	}

	/**
	 * FailureLink, OutputLink 셋팅
	 */
	private void setLink() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			Node parent = queue.poll();
			for (Map.Entry <String, Node> entry : parent.getChildren().entrySet()) {
				Node child = entry.getValue();

				queue.offer(child);

				if (parent == root) {
					child.setFailureLink(root);
				} else {
					Node parentFailureLink = parent.getFailureLink();
					Node parentFailureLinkChild = parentFailureLink.getChild(child.getCh());

					Node failureLink;
					if (parentFailureLinkChild == null) {
						failureLink = parentFailureLink;
					} else {
						failureLink = parentFailureLinkChild;
					}

					child.setFailureLink(failureLink);

					if (failureLink.isSuccessNode()) {
						child.setOutputLink(failureLink);
					} else {
						child.setOutputLink(failureLink.getOutputLink());
					}
				}
			}
		}
	}

	public void startRoot() {
		currentNode = root;
	}

	public boolean isMatching(char ch) {
		if (currentNode == null) {
			currentNode = root;
		}

		Boolean result = existMatchChild(ch);
		if (result != null)
			return result;

		currentNode = currentNode.getFailureLink();

		result = existMatchChild(ch);
		if (result != null)
			return result;

		return false;
	}

	private Boolean existMatchChild(char ch) {
		for (Map.Entry<String, Node> entry : currentNode.getChildren().entrySet()) {
			Node child = entry.getValue();

			if (child.getCh() == ch) {
				currentNode = child;

				if (currentNode.isSuccessNode()) {
					return true;
				}

				Node node = currentNode.getOutputLink();
				while (node != null) {
					if (node.isSuccessNode()) {
						return true;
					}

					node = node.getOutputLink();
				}

				return false;
			}
		}
		return null;
	}

	public void print() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			Node parent = queue.poll();
			for (Map.Entry<String, Node> entry : parent.getChildren().entrySet()) {
				Node child = entry.getValue();
				queue.offer(child);
			}

			if (parent == root) {
				continue;
			}
			parent.print();
			System.out.println();
		}
	}

	public static AhoCorasickBuilder builder() {
		return new AhoCorasickBuilder();
	}


	public static class AhoCorasickBuilder {
		AhoCorasick ahoCorasick;

		private AhoCorasickBuilder() {
			this.ahoCorasick = new AhoCorasick();
		}

		public AhoCorasickBuilder addPattern(String pattern) {
			ahoCorasick.add(pattern);
			return this;
		}

		public AhoCorasick build() {
			ahoCorasick.setLink();
			return ahoCorasick;
		}
	}
}
