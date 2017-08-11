/*
 *@(#)MyLinkedList.java 2017.08.09
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.hash.chaining.linkedlist;

/**
 *
 *
 * @author kim.minjoo
 */
public class MyLinkedList<K, V> {
	MyNode<K, V> head;
	int size = 0;

	/**
	 * 리스트의 맨 앞에 추가하는함수.
	 * @param key head의 검색키
	 * @param value head에 들어갈 데이터.
	 */
	public void add(K key, V value) {
		MyNode<K, V> node = new MyNode<K, V>(key, value);
		//아무것도 없을 경우 추가해준다~
		add(node);

		size++;
	}

	private void add(MyNode<K, V> node) {
		if (head == null) {
			head = node;
			head.setNext(head);
			head.setPrev(head);
			return;
		}

		//1. 새로들어온 노드의 앞 뒤 노드를 구한다.
		MyNode next = head;
		MyNode prev = getTail();

		//2. 추가할 노드를 맨 앞에 추가해준다.
		head = node;

		//3. next셋팅
		node.setNext(next);
		next.setPrev(node);

		//4. prev셋팅
		node.setPrev(prev);
		prev.setNext(node);
	}

	public MyNode<K, V> getHead() {
		return head;
	}

	private MyNode getTail() {
		if (head == null) {
			return null;
		}
		return head.getPrev();
	}

	/**
	 * 리스트의 맨 뒤에 추가하는 함수~
	 * @param key tail의 검색키
	 * @param value tail에 들어갈 데이터
	 */
	public void put(K key, V value) {
		MyNode<K, V> node = new MyNode<K, V>(key, value);
		put(node);

		size++;
	}

	private void put(MyNode<K, V> node) {
		//아무것도 없을 경우 추가해준다~
		if (head == null) {
			head = node;
			head.setNext(head);
			head.setPrev(head);
			return;
		}

		//1. 새로들어온 노드의 앞 뒤 노드를 구한다.
		MyNode next = head;
		MyNode prev = getTail();

		//2. 새로들어온 노드를 맨 뒤에 추가해준다.
		head.setPrev(node);

		//3. next 셋팅
		node.setNext(next);
		next.setPrev(node);

		//4. prev셋팅
		node.setPrev(prev);
		prev.setNext(node);
	}

	/**
	 * 노드를 찾는 함수!
	 * @param key 찾으려는 검색키
	 * @return key를 검색키로 가진 노드
	 */
	public MyNode search(K key) {
		if (head == null) {
			return null;
		}
		MyNode currentNode = head;
		do {
			if (currentNode.getKey().equals(key)) {
				return currentNode;
			}

			currentNode = currentNode.getNext();
		} while (currentNode != head);

		return null;
	}

	public boolean remove(K key) {
		MyNode removeNode = search(key);

		if (removeNode == null) {
			return false;
		}

		remove(removeNode);

		size--;
		return true;
	}

	private void remove(MyNode removeNode) {
		if (size == 1) {
			head = null;
			return;
		}

		//1. 삭제할 노드의 앞 뒤를 가져온다
		MyNode next = removeNode.getNext();
		MyNode prev = removeNode.getPrev();

		//2. 삭제할 노드를 제외한 앞 뒤 노드를 이어준다.
		next.setPrev(prev);
		prev.setNext(next);

		//만약 삭제한 노드가 헤드라면 next가 헤드가 된다.
		if (removeNode.equals(head)) {
			head = next;
		}
	}

	public boolean contains(K key) {
		MyNode node = search(key);

		return node != null;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		String toString = "";
		if (head == null) {
			return toString;
		}
		MyNode currentNode = head;
		do {
			toString += currentNode.toString();
			currentNode = currentNode.getNext();
		} while (currentNode != head);

		return toString;
	}
}