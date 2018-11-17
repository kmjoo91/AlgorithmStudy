package com.naver.linesweep;

import com.naver.linesweep.model.Point;

import java.util.*;
import java.util.stream.Collectors;

public class LineSweep {

	public LineSweep(Point[] points) {
	}

	public double calculateDistance(Point[] points) {
		Arrays.sort(points, new PointXComparator());

		double minDistance = getDistance(points[0], points[1]);
		int start = 0;

		List<Point> possiblePoints = new ArrayList<>();
		possiblePoints.add(points[0]);
		possiblePoints.add(points[1]);

		for (int i = 2; i < points.length; i++) {
			Point currentPoint = points[i];
			for (int j = start; j < i; j++) {
				double xDistance = currentPoint.getX() - points[j].getX();
				//클때
				if (xDistance > minDistance) {
					possiblePoints.remove(points[j]);
				}
				//작을때
				else {
					start = j;
					break;
				}
			}

			double lowerY = currentPoint.getY() - minDistance;
			double upperY = currentPoint.getY() - minDistance;

			possiblePoints = possiblePoints.stream()
					.filter(point -> point.getY() >= lowerY && point.getY() <= upperY)
					.collect(Collectors.toList());

			for (Point point : possiblePoints) {
				double distance = getDistance(currentPoint, point);
				if (distance < minDistance) {
					minDistance = distance;
				}
			}

			possiblePoints.add(currentPoint);
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
