package com.naver.linesweep;

import com.naver.linesweep.model.Point;

import java.util.*;
import java.util.stream.Collectors;

public class LineSweep {
	public double calculateDistance(Point[] points) {
		Arrays.sort(points, new PointXComparator());

		double minDistance = getDistance(points[0], points[1]);

		Point[] possiblePoints = {points[0], points[1]};

		int possibleIndex = 0;
		for (int i = 2; i < points.length; i++) {
			Point currentPoint = points[i];

			for (int j = possibleIndex; j < i; j++) {
				Point point = points[j];
				double xDistance = currentPoint.getX() - point.getX();

				if (xDistance < minDistance) {
					possibleIndex = j;
					break;
				}
			}

			double lowerY = currentPoint.getY() - minDistance;
			double upperY = currentPoint.getY() + minDistance;

			possiblePoints = Arrays.copyOfRange(points, possibleIndex, i);


			for (Point point : possiblePoints) {
				if (point.getY() < lowerY || point.getY() > upperY) {
					continue;
				}

				double distance = getDistance(currentPoint, point);
				if (distance < minDistance) {
					minDistance = distance;
				}
			}

		}

		return minDistance;
	}

	private double getDistance(Point point1, Point point2) {
		return Math.sqrt(
				Math.pow(point1.getX() - point2.getX(), 2)
						+ Math.pow(point1.getY() - point2.getY(), 2)
		);
	}

	class PointXComparator implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			return Double.compare(p1.getX(), p2.getX());
		}
	}
}
