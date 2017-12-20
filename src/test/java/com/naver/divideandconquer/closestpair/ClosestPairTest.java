package com.naver.divideandconquer.closestpair;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class ClosestPairTest {
	ClosestPair closestPair = new ClosestPair();

	@Test
	public void 귀찮으니JUNIT4() {
		Point point1 = new Point(2, 5);
		Point point2 = new Point(15, 30);
		Point point3 = new Point(40, 50);
		Point point4 = new Point(5, 2);
		Point point5 = new Point(15, 10);
		Point point6 = new Point(10, 5);

		List<Point> pointList = new ArrayList<>();
		pointList.add(point1);
		pointList.add(point2);
		pointList.add(point3);
		pointList.add(point4);
		pointList.add(point5);
		pointList.add(point6);

		Point[] points = new Point[pointList.size()];
		for (int i = 0; i < pointList.size(); i++) {
			points[i] = pointList.get(i);
		}

		Pair minPair = closestPair.calculate(points, 0, points.length - 1);
		System.out.println(minPair);
	}

	@Test
	public void 랜덤테스트() {
		int pointSize = 100000;
		int boundSize = pointSize * 100;
		Point[] points = new Point[pointSize];
		Random random = new Random();
		for (int i = 0; i < pointSize; i++) {
			Point point = new Point(random.nextInt(boundSize) + 1, random.nextInt(boundSize) + 1);
			points[i] = point;
		}

		System.out.println("======");
		for (Point point : points) {
			System.out.println(point);
		}
		System.out.println("======");

		Arrays.sort(points, Comparator.comparing(Point::getX));
		long start = System.currentTimeMillis(); //시작하는 시점 계산
		Pair minPair = closestPair.calculate(points, 0, points.length - 1);
		long end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
		System.out.println( "실행 시간 : " + ( end - start ) + "밀리초"); //실행 시간 계산 및 출력

		//System.out.println(minPair);

		start = System.currentTimeMillis(); //시작하는 시점 계산
		Pair min = closestPair.calculateAllCaseClosestPair(points);
		end = System.currentTimeMillis(); //프로그램이 끝나는 시점 계산
		System.out.println( "실행 시간 : " + ( end - start ) + "밀리초"); //실행 시간 계산 및 출력
		System.out.println("모든경우 최소 : " + min);
	}

	private Pair calculateAllCaseClosestPair(Point[] points) {
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

	@Test
	public void 테스트() {
		if (null instanceof String) {
			System.out.println("ㅋㅋ");
		} else {
			System.out.println("zzzzz");
		}
	}

}