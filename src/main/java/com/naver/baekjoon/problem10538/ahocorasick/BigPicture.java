package com.naver.baekjoon.problem10538.ahocorasick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class BigPicture {
	public static int calculate(String[] picture, String[] bigPicture) {
		AhoCorasick ahoCorasick = AhoCorasick.builder()
				.addPattern(picture)
				.build();

		int width = picture[0].length();
		int height = picture.length;
		int bigWidth = bigPicture[0].length();
		int bigHeight = bigPicture.length;
		List<Queue<Integer>> matchingQueueList = new ArrayList<>();

		for (int i = 0; i < height - 1; i++) {
			matchingQueueList.add(new LinkedList<>());
		}

		if (width > bigWidth || height > bigHeight) {
			return 0;
		}

		int result = 0;

		for (int i = 0; i < bigHeight; i++) {
			ahoCorasick.startRoot();
			List<Queue<Integer>> nextMatchingQueueList = new ArrayList<>();

			for (int j = 0; j < height - 1; j++) {
				nextMatchingQueueList.add(new LinkedList<>());
			}

			for (int j = 0; j < bigWidth; j++) {
				List<List<Integer>> matchingIndexList = ahoCorasick.getMatchingIndexList(bigPicture[i].charAt(j));

				if (matchingIndexList.isEmpty()) {
					continue;
				}

				if (height == 1) {
					result++;
					continue;
				}

				for (List<Integer> indexList : matchingIndexList) {
					for (Integer index : indexList) {
						if (index > i) {
							break;
						}

						if (bigHeight - i < height - index) {
							continue;
						}

						if (index == 0) {
							nextMatchingQueueList.get(index).offer(j);
						}
						//0인경우에는 무조건 다음행에서 찾아야하니까 넣어준다. 0이 아닐경우 이번행에서 찾아야하는애인지 확인~
						if (index != 0) {
							if (matchingQueueList.get(index - 1).isEmpty()) {
								continue;
							}

							int searchingIndex = matchingQueueList.get(index - 1).peek();
							if (searchingIndex == j) {
								if (index == height - 1) {
									result++;
								} else {
									nextMatchingQueueList.get(index).offer(j);
								}
							}

							if (searchingIndex <= j) {
								matchingQueueList.get(index - 1).poll();
							}
						}
					}
				}
			}

			matchingQueueList = nextMatchingQueueList;
		}

		return result;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] input = scanner.nextLine().split(" ");

		int height = Integer.parseInt(input[0]);
		int bigHeight = Integer.parseInt(input[2]);

		String[] picture= new String[height];
		String[] bigPicture = new String[bigHeight];

		for (int i = 0; i < height; i++) {
			picture[i] = scanner.next();
		}

		for (int i = 0; i < bigHeight; i++) {
			bigPicture[i] = scanner.next();
		}

		int result = calculate(picture, bigPicture);
		System.out.println(result);
	}
}

class AhoCorasick {
	Node root;
	Node currentNode;

	private AhoCorasick() {
		root = new Node();

		root.setFailureLink(root);
		root.setSuccessNode(false);
		root.setOutputLink(null);
		root.setIndexList(null);
	}

	private void add(String pattern, int patternIndex) {
		Node currentNode = this.root;
		int index = 0;

		while (index < pattern.length()) {
			char currentChar = pattern.charAt(index);
			currentNode.addChild(currentChar, index == pattern.length() - 1, patternIndex);
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

	public List<List<Integer>> getMatchingIndexList(char ch) {
		if (currentNode == null) {
			currentNode = root;
		}


		while (true) {
			List<List<Integer>> matchingIndexList = new ArrayList<>();

			Node child = currentNode.getChild(ch);

			if (child != null) {
				currentNode = child;

				if (currentNode.isSuccessNode()) {
					matchingIndexList.add(currentNode.getIndexList());
				}

				Node node = currentNode.getOutputLink();
				while (node != null) {
					if (node.isSuccessNode()) {
						matchingIndexList.add(node.getIndexList());
					}

					node = node.getOutputLink();
				}

				return matchingIndexList;
			}

			if (currentNode == root) {
				return new ArrayList<>();
			}

			currentNode = currentNode.getFailureLink();
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

		public AhoCorasickBuilder addPattern(String[] patterns) {
			for (int i = 0; i < patterns.length; i++) {
				addPattern(patterns[i], i);
			}
			return this;
		}

		public AhoCorasickBuilder addPattern(String pattern, int patternIndex) {
			ahoCorasick.add(pattern, patternIndex);
			return this;
		}

		public AhoCorasick build() {
			ahoCorasick.setLink();
			return ahoCorasick;
		}
	}
}

class Node {
	private Map<String, Node> children;
	private Node failureLink;
	private Node outputLink;
	private boolean isSuccessNode;
	private char ch;
	private List<Integer> indexList;

	public Node() {
		children = new HashMap<>();
		indexList = new ArrayList<>();
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

	public void addChild(char ch, boolean isFinish, int patternIndex) {
		Node child = this.children.get(String.valueOf(ch));
		if (child == null) {
			child = new Node();
			child.setCh(ch);
			this.children.put(String.valueOf(ch), child);
		}

		if (isFinish) {
			child.isSuccessNode = true;
			child.indexList.add(patternIndex);
		}
	}

	public Node getChild(char ch) {
		return this.children.get(String.valueOf(ch));
	}

	public List<Integer> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<Integer> indexList) {
		this.indexList = indexList;
	}
}
