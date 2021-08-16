package com.naver.baekjoon.problem2261;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class LineSweep {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int pointNum = scanner.nextInt();

		Point[] points = new Point[pointNum];
		for (int i = 0; i < pointNum; i++) {
			Point point = new Point(scanner.nextInt(), scanner.nextInt());
			points[i] = point;
		}

		Pair pair = calculateDistance(points);

		long xgap = pair.getP1().getX() - pair.getP2().getX();
		long ygap = pair.getP1().getY() - pair.getP2().getY();

		System.out.println(xgap*xgap + ygap*ygap);
	}

	public static Pair calculateDistance(Point[] points) {
		Arrays.sort(points, new PointXComparator());

		Pair minPair = new Pair(points[0], points[1], getDistance(points[0], points[1]));

		//원래대로라면 BBST
		TreeSet<Point> possiblePoints = new TreeSet<>(new PointYComparator());
		possiblePoints.add(points[0]);
		possiblePoints.add(points[1]);

		int possibleIndex = 0;
		for (int i = 2; i < points.length; i++) {
			Point currentPoint = points[i];

			for (int j = possibleIndex; j < i; j++) {
				Point point = points[j];
				double xDistance = currentPoint.getX() - point.getX();

				if (xDistance <= minPair.getDistance()) {
					possibleIndex = j;
					break;
				} else {
					possiblePoints.remove(point);
				}
			}

			double lowerY = currentPoint.getY() - minPair.getDistance();
			Point lowerPoint = new Point(currentPoint.getX(), (int)lowerY - 1);
			double upperY = currentPoint.getY() + minPair.getDistance();
			Point upperPoint = new Point(currentPoint.getX(), (int)upperY + 1);


			SortedSet<Point> set = possiblePoints.tailSet(lowerPoint).headSet(upperPoint);

			for (Point point : set) {
				if (point.getY() < lowerY || point.getY() > upperY) {
					continue;
				}
				double distance = getDistance(currentPoint, point);
				if (distance < minPair.getDistance()) {
					minPair = new Pair(currentPoint, point, distance);
				}
			}

			if (!possiblePoints.add(currentPoint)) {
				return new Pair(currentPoint, currentPoint, 0);
			}
		}

		return minPair;
	}

	private static double getDistance(Point point1, Point point2) {
		int xgap = point1.getX() - point2.getX();
		int ygap = point1.getY() - point2.getY();
		double x = Math.pow(xgap, 2);
		double y = Math.pow(ygap, 2);
		return Math.sqrt(x + y);
	}

	static class PointXComparator implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			return Integer.compare(p1.getX(), p2.getX());
		}
	}

	static class PointYComparator implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			int yCompare = Integer.compare(p1.getY(), p2.getY());
			if (yCompare != 0) {
				return yCompare;
			}
			return Integer.compare(p1.getX(), p2.getX());
		}
	}
}

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}

class Pair {
	Point p1;
	Point p2;
	double distance;

	public Pair(Point p1, Point p2, double distance) {
		this.p1 = p1;
		this.p2 = p2;
		this.distance = distance;
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}