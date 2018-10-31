package com.naver.baekjoon.problem11437;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LowestCommonAncestor {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int nodeNum = scanner.nextInt();

		NclNode[] nclNodeArr = new NclNode[nodeNum];

		NclNode root = new NclNode();
		root.setLevel(0);
		root.setNumber(1);
		nclNodeArr[0] = root;

		for (int i = 0; i < nodeNum - 1; i ++) {
			int nodeNum1 = scanner.nextInt();
			int nodeNum2 = scanner.nextInt();
			
			NclNode node1 = nclNodeArr[nodeNum1 - 1];
			if (node1 == null) {
				node1 = new NclNode();
				node1.setNumber(nodeNum1);

				nclNodeArr[nodeNum1 - 1] = node1;
			}

			NclNode node2 = nclNodeArr[nodeNum2 - 1];
			if (node2 == null) {
				node2 = new NclNode();
				node2.setNumber(nodeNum2);

				nclNodeArr[nodeNum2 - 1] = node2;
			}

			node1.getNeighbor().add(node2);
			node2.getNeighbor().add(node1);
		}

		calculateParent(nclNodeArr[0]);

		int searchCount = scanner.nextInt();

		for (int i = 0; i < searchCount; i++) {
			NclNode nclNode = searchNcl(nclNodeArr[scanner.nextInt() - 1], nclNodeArr[scanner.nextInt() - 1]);
			System.out.println(nclNode.getNumber());
		}
	}

	private static void calculateParent(NclNode parent) {
		for (NclNode child : parent.getNeighbor()) {
			if (child.getNumber() == parent.getNumber()) {
				continue;
			}

			child.setLevel(parent.getLevel() + 1);
			child.setParent(parent);
			child.getNeighbor().remove(parent);
			calculateParent(child);
		}
	}

	private static NclNode searchNcl(NclNode node1, NclNode node2) {
		while (node1.getNumber() != node2.getNumber()) {
			if (node1.getLevel() > node2.getLevel()) {
				node1 = node1.getParent();
			} else if (node1.getLevel() < node2.getLevel()) {
				node2 = node2.getParent();
			} else {
				node1 = node1.getParent();
				node2 = node2.getParent();
			}
		}

		return node1;
	}
}
class NclNode {
	private int number;
	private int level;
	private List<NclNode> neighbor = new ArrayList<>();
	private NclNode parent;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<NclNode> getNeighbor() {
		return neighbor;
	}

	public void setNeighbor(List<NclNode> neighbor) {
		this.neighbor = neighbor;
	}

	public NclNode getParent() {
		return parent;
	}

	public void setParent(NclNode parent) {
		this.parent = parent;
	}
}