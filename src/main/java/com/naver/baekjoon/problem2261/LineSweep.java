package com.naver.baekjoon.problem2261;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LineSweep {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int pointNum = scanner.nextInt();


		Point[] points = new Point[pointNum];
		for (int i = 0; i < pointNum; i++) {
			points[i] = new Point(scanner.nextInt(), scanner.nextInt());
		}

		System.out.println(calculateDistance(points));
	}

	public static double calculateDistance(Point[] points) {
		Arrays.sort(points, new PointXComparator());

		double minDistance = getDistance(points[0], points[1]);

		//원래대로라면 BBST
		List<Point> possiblePoints = new ArrayList<>();
		possiblePoints.add(points[0]);
		possiblePoints.add(points[1]);

		int possibleIndex = 0;
		for (int i = 2; i < points.length; i++) {
			if (minDistance == 0) {
				return 0;
			}

			Point currentPoint = points[i];

			for (int j = possibleIndex; j < i; j++) {
				Point point = points[j];
				double xDistance = currentPoint.getX() - point.getX();

				if (xDistance < Math.sqrt(minDistance)) {
					possibleIndex = j;
					break;
				} else {
					possiblePoints.remove(point);
					//System.out.println("removePoint : " + point);
				}
			}

			if (possiblePoints.size() >  6) {
				throw new IllegalStateException();
			}

			double lowerY = currentPoint.getY() - Math.sqrt(minDistance);
			double upperY = currentPoint.getY() + Math.sqrt(minDistance);

			for (Point point : possiblePoints) {
				if (point.getY() > upperY || point.getY() < lowerY) {
					continue;
				}

				double distance = getDistance(currentPoint, point);
				if (distance < minDistance) {
					minDistance = distance;
				}
			}

			possiblePoints.add(currentPoint);
		}

		return minDistance;
	}

	private static double getDistance(Point point1, Point point2) {
		int xgap = point1.getX() - point2.getX();
		int ygap = point1.getY() - point2.getY();
		double x = Math.pow(xgap, 2);
		double y = Math.pow(ygap, 2);
		return x + y;
	}

	static class PointXComparator implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
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