/*
 *@(#)djikstra.java 2017.09.14
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.shortestpath.djikstra;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 *
 * @author kim.minjoo
 */
public class Djikstra {

	private int[][] map;
	private int[][] distance;

	public Djikstra(int[][] map) {
		this.map = map;
		this.distance = new int[map.length][];
		for (int row = 0; row < map.length; row++) {
			this.distance[row] = new int[map[row].length];
			for (int column = 0; column < map[row].length; column++) {
				this.distance[row][column] = map[row][column];
			}
		}
	}

	public Queue<Integer> calculateShortestPath(int start, int end) {
		Queue<Integer> route = new LinkedList<>();
		route.offer(start);

		while(true) {
			int nextNode = getShortestNode(start, distance, route);
			route.offer(nextNode);
			if (nextNode == end) {
				return route;
			}

			for (int node = 0; node < distance[start].length; node++) {
				if (route.contains(node)) {
					continue;
				}
				//없을땐 거리갱신해줘야지
				for (int i = 0; i < distance.length; i++) {
					if (distance[i][node] > distance[i][nextNode] + distance[nextNode][node]) {
						distance[i][node] = distance[i][nextNode] + distance[nextNode][node];
					}
				}
			}
		}
	}

	private int getShortestNode(int start, int[][] distance, Queue<Integer> route) {
		int nextNode = start;
		int minDistance = 100;
		for (int node = 1; node <  distance[start].length; node++) {
			if (node == start) {
				continue;
			}
			if (route.contains(node)) {
				continue;
			}
			if (minDistance > distance[start][node]) {
				minDistance = distance[start][node];
				nextNode = node;
			}
		}
		return nextNode;
	}
}