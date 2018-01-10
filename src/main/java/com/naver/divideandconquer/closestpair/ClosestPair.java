/*
 *@(#)ClosestPair.java 2017.12.05
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.divideandconquer.closestpair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

/**
 *
 *
 * @author kim.minjoo
 */
public class ClosestPair {

	public Pair calculate(Point[] points, int start, int end) {
		if (end - start < 3) {
			if (end - start == 2) {
				return calculateAllCaseClosestPair(Arrays.copyOfRange(points, start, end));
			}

			Pair pair = new Pair(points[start], points[end], calculateDistance(points[start], points[end]));
			return pair;
		}

		int mid = (start + end) / 2;
		Pair leftPir = calculate(points, start, mid);
		Pair rigthPair = calculate(points, mid, end);

		Pair minPair = leftPir.getDistance() < rigthPair.getDistance() ? leftPir : rigthPair;

		Pair centerClosestPair = getCenterClosestPair(Arrays.copyOfRange(points, start, end), minPair.getDistance());

		if (centerClosestPair != null) {
			return centerClosestPair.getDistance() < minPair.getDistance() ? centerClosestPair : minPair;
		}

		return minPair;
	}

	private Pair getCenterClosestPair(Point[] points, double minDistance) {
		Point[] closestPairCandidatePoints = new Point[points.length];
		int count = 0;
		int mid = points.length / 2;
		Point standardPoint = points[mid];

		for (int i = 0; i < points.length;  i++) {
			double distance = standardPoint.getX() > points[i].getX() ? standardPoint.getX() - points[i].getX() : points[i].getX() - standardPoint.getX();
			if (distance < minDistance) {
				closestPairCandidatePoints[count++] = points[i];
			}
		}

		closestPairCandidatePoints = Arrays.copyOfRange(closestPairCandidatePoints, 0 , count);

		Arrays.sort(closestPairCandidatePoints, Comparator.comparing(Point::getY));

		Pair centerClosestPair = null;
		for (int i = 0; i < closestPairCandidatePoints.length - 5; i++) {
			for (int j = i+1; j < i + 6 && j < closestPairCandidatePoints.length; j++) {
				double distance = calculateDistance(closestPairCandidatePoints[i], closestPairCandidatePoints[j]);
				if (distance < minDistance) {
					Pair pair = new Pair(closestPairCandidatePoints[i], closestPairCandidatePoints[j], distance);
					centerClosestPair = pair;
				}
			}
		}

		return centerClosestPair;
	}


	public Pair calculateAllCaseClosestPair(Point[] points) {
		Pair minPair = new Pair(points[0], points[1], calculateDistance(points[0], points[1]));

		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				double distance = calculateDistance(points[i], points[j]);
				if (distance <= minPair.getDistance()) {
					minPair.setDistance(distance);
					minPair.setPoint1(points[i]);
					minPair.setPoint2(points[j]);
				}
			}
		}

		return minPair;
	}

	private double calculateDistance(Point p1, Point p2) {
		double xDistance = Math.pow((p1.getX() - p2.getX()), 2);
		double yDistance = Math.pow((p1.getY() - p2.getY()), 2);

		return Math.sqrt(xDistance + yDistance);
	}
}