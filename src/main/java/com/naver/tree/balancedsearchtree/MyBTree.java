/*
 *@(#)MyBTree.java 2017.09.01
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.tree.balancedsearchtree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.naver.tree.balancedsearchtree.model.Level;
import com.naver.tree.balancedsearchtree.model.Node;

/**
 *
 *
 * @author kim.minjoo
 */
public class MyBTree implements BTree {
	private static final int m = 5;

	private Level head;
	int height;

	public MyBTree() {
		this.head = new Level(m);
		this.height = 0;
	}

	public Level getHead() {
		return head;
	}

	public void setHead(Level head) {
		this.head = head;
	}

	@Override
	public Node search(int key) {
		Level currentLevel = head;
		Node currentNode = head.getNodes()[0];

		int index = 0;
		while (currentNode != null) {
			for (index = 0; index < m; index++) {
				int currentKey = currentNode.getKey();
				if (key == currentKey) {
					return currentNode;
				}

				if (key < currentKey) {
					break;
				}
			}

			currentLevel = currentLevel.getChildren()[index];
			if (currentLevel == null) {
				return null;
			}
			currentNode = currentLevel.getNodes()[0];
		}

		return null;
	}

	@Override
	public boolean addData(Node data) {
		Level parentLevel = null;
		Level currentLevel = head;
		Stack<Level> stack = new Stack<Level>();
		int childIndex = 0;
		int currentHeight = 0;
		while (currentLevel != null) {
			stack.push(currentLevel);
			for (childIndex = 0; childIndex < currentLevel.getCount(); childIndex++) {
				if (currentLevel.getNodes()[childIndex] == null) {
					currentLevel.getNodes()[childIndex] = data;
					return true;
				}
				if (data.getKey() == currentLevel.getNodes()[childIndex].getKey()) {
					System.out.println("중복키 오류!!");
					return false;
				}

				if (data.getKey() < currentLevel.getNodes()[childIndex].getKey()) {
					break;
				}
			}

			parentLevel = currentLevel;
			currentLevel = currentLevel.getChildren()[childIndex];
			currentHeight++;
		}

		if (isFull(parentLevel) == false) {
			int index = 0;
			for (index = 0; index < parentLevel.getCount(); index++) {
				if (parentLevel.getNodes()[index] == null) {
					parentLevel.getNodes()[index] = data;
					return true;
				}

				if (parentLevel.getNodes()[index].getKey() > data.getKey()) {
					boolean isMove = false;
					for (int i = parentLevel.getCount() - 1; i >= index ; i--) {
						if (isMove) {
							parentLevel.getNodes()[i+1] = parentLevel.getNodes()[i];
						}

						if (parentLevel.getNodes()[i] == null) {
							isMove = true;
						}
					}

					parentLevel.getNodes()[index] = data;
					return true;
				}
			}
		}

		//여긴 currentLevel이 null이란 소리. 즉 currentLevel을 만들어주고 거기에 집어넣는데 끝일수도있고 끝이아닐수도있긴한데... 스플릿 해야할때가 언제인지를 알아야하는데...
		int minDataNumber = calculateMinDataNumber(parentLevel.getCount());
		if (minDataNumber == 1 && currentHeight < this.height) {
			parentLevel.getChildren()[childIndex] = new Level(parentLevel.getCount());
			return true;
		}




		/************************************************************/
		//여기서부터 다시정리해보자 넣을곳은 찾았어 current 혹은 parent야.
		//꽉찼을땐
		if (isFull(parentLevel)) {
			//스플릿을 해야지. parent + data로

		}

		return false;
	}

	private boolean isFull(Level level) {
		for (int i = 0; i < level.getCount(); i++) {
			if (level.getNodes()[i] == null) {
				return false;
			}
		}

		return true;
	}

	private int calculateMinDataNumber(int count) {
		int minDataNumber = (int)Math.ceil((double)count / 2) - 1;
		return minDataNumber > 0 ? minDataNumber : 1;
	}

	@Override
	public void removeData(Node data) {

	}

	@Override
	public void show() {
		Queue<Level> queue = new LinkedList<>();
		queue.add(head);
		while(queue.isEmpty() == false) {
			Level level = queue.poll();
			for (int i = 0; i < level.getCount(); i++) {
				if (level.getNodes()[i] != null) {
					System.out.print(level.getNodes()[i]);
				}
				if (level.getChildren()[i] != null) {
					queue.add(level.getChildren()[i]);
				}
			}

			if (level.getChildren()[level.getCount()] != null) {
				queue.add(level.getChildren()[level.getCount()]);
			}
			System.out.println("");
		}
	}
}