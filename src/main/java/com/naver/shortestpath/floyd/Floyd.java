/*
 *@(#)Floyd.java 2017.09.12
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.shortestpath.floyd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author kim.minjoo
 */
public class Floyd {
	private static final Logger LOGGER = LoggerFactory.getLogger(Floyd.class);
	boolean isCalculate;
	private int[][] map;
	private int[][] distance;

	public int[][] getDistance() {
		return distance;
	}

	public Floyd(int[][] map) {
		this.map = map;
		this.distance = new int[map.length][];
		for (int row = 0; row < map.length; row++) {
			this.distance[row] = new int[map[row].length];
			for (int column = 0; column < map[row].length; column++) {
				this.distance[row][column] = map[row][column];
			}
		}
		this.isCalculate = false;
	}

	public void calculateShortestPath() {
		for (int middle = 0; middle < map.length; middle++) {
			for (int start = 0; start < map.length; start++) {
				for (int end = 0; end < map.length; end++) {
					int newDistance = distance[start][middle] + distance[middle][end];
					int originDistance = distance[start][end];
					if (originDistance > newDistance) {
						System.out.println("변신!!!!!!!!!!!!");
						distance[start][end] = newDistance;
					}
					System.out.println(String.format("시작노드 : %d, 끝노드 : %d, 중간노드 : %d", start, end, middle));
					print();
				}
			}
		}
		isCalculate = true;
	}

	public void calculateNodePath(int middle) {
		for (int start = 0; start < map.length; start++) {
			for (int end = 0; end < map.length; end++) {
				int newDistance = distance[start][middle] + distance[middle][end];
				int originDistance = distance[start][end];
				if (originDistance > newDistance) {
					//System.out.println("변신!!!!!!!!!!!!");
					distance[start][end] = newDistance;
				}
				//System.out.println(String.format("시작노드 : %d, 끝노드 : %d, 중간노드 : %d", start, end, middle));
				//print();
			}
		}
	}

	public int getDistance(int start, int end) {
		if (isCalculate) {
			return distance[start][end];
		}

		LOGGER.info("최단거리 계산후 이용좀!");
		return -1;
	}

	private void print() {
		for (int[] row : distance) {
			for (int colum : row) {
				System.out.print(colum + " ");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
	}
}