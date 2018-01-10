/*
 *@(#)Pair.java 2017.12.06
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.divideandconquer.closestpair;

/**
 *
 *
 * @author kim.minjoo
 */
public class Pair {
	private Point point1;
	private Point point2;
	private double distance;

	public Pair(Point point1, Point point2, double distance) {
		this.point1 = point1;
		this.point2 = point2;
		this.distance = distance;
	}

	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Pair{" +
				"point1=" + point1 +
				", point2=" + point2 +
				", distance=" + distance +
				'}';
	}
}