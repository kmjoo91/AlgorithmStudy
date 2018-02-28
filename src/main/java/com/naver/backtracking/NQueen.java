/*
 *@(#)EightQueen.java 2018.02.28
 *
 * Copyright 2018 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.naver.backtracking.model.Point;

/**
 *
 *
 * @author kim.minjoo
 */
public class NQueen {
	/**
	 * n * n 의 맵에서 n개의 퀸을 놓을 위치를 구해주는 함수.
	 * @param n 퀸의 갯수
	 * @return map에 n개의 퀸을 놓을 위치들을 가진 리스트
	 */
	public static List<Point> getNQueenPosition(int n) {
		Point[] points = new Point[n];
		calculateNQueenPosition(n, points, 0);
		return Arrays.asList(points);
	}

	private static void calculateNQueenPosition(int n, Point[] points, int row) {
		for (int column = 0; column < n; column++) {
			if (isPossibleColumn(column, row, points)) {
				points[row] = new Point(column, row);
				calculateNQueenPosition(n, points, row + 1);
				if (points[n-1] != null) {
					break;
				}
			}
		}
	}

	private static boolean isPossibleColumn(int column, int row, Point[] points) {
		for (int i = 0; i < row; i++) {
			Point point = points[i];
			if (point.getX() == column) {
				return false;
			}

			int distanceColumn = Math.abs(point.getX() - column);
			int distanceRow = row - point.getY();
			if (distanceColumn == distanceRow) {
				return false;
			}
		}

		return true;
	}

	public static void showPointList(List<Point> pointList) {
		int size = pointList.size();
		boolean[][] map = new boolean[size][size];

		for (int row = 0; row < size; row++) {
			Point rowPosition = pointList.get(row);
			for (int column = 0; column < size; column++) {
				System.out.print("|");
				if (rowPosition.getX() == column && rowPosition.getY() == row) {
					System.out.print("Q");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("|");
		}
	}
}