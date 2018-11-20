package com.naver.baekjoon.problem1708;

import java.util.*;

/**
 * https://www.acmicpc.net/problem/1708
 */
public class ConvexHull {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //1. 입력받고.
        int pointNum = scanner.nextInt();

        List<Point> pointList = new ArrayList<>();

        for (int i = 0; i < pointNum; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            pointList.add(new Point(x, y));
        }

        //2. Y좌표 제일 작은애 찾고.
        int minIndex = 0;
        Point minPoint = pointList.get(0);
        for (int i = 1; i < pointNum; i++) {
            Point currentPoint = pointList.get(i);
            if (minPoint.getY() > currentPoint.getY()) {
                minIndex = i;
                minPoint = currentPoint;
            } else if (minPoint.getY() == currentPoint.getY() && minPoint.getX() > currentPoint.getX()) {
                minIndex = i;
                minPoint = currentPoint;
            }
        }

        pointList.remove(minIndex);
        minPoint.setCosValue(1);

        //3. 각도별로 정렬하기 위해서 cos값 구하고
        for (int i = 0; i < pointList.size(); i++) {
            Point currentPoint = pointList.get(i);
            double distance = Math.sqrt(
                    Math.pow(Math.abs((double)currentPoint.getX() - minPoint.getX()),2) + Math.pow(Math.abs((double)currentPoint.getY() - minPoint.getY()), 2)
            );

            currentPoint.setDistance(distance);
            currentPoint.setAngle(getAngle(minPoint, currentPoint));
        }

        //4. 각도별로 정렬하고
        pointList.sort(new CosComparator());

        //5. 이제 점 찾아다니면서 확인하자.
        Stack<Point> cunvexHullStack = new Stack<>();

        cunvexHullStack.push(minPoint);
        cunvexHullStack.push(pointList.get(0));

        //시계방향일 경우 기존꺼빼고 다시 기존꺼랑 비교~
        for (int i = 1; i < pointList.size(); i++) {
            Point point2 = cunvexHullStack.pop();
            Point point1 = cunvexHullStack.peek();

            Point point3 = pointList.get(i);

            int ccw = ccw(point1.getX(), point1.getY(), point2.getX(), point2.getY(), point3.getX(), point3.getY());

            //반시계일때
            if (ccw > 0) {
                cunvexHullStack.push(point2);
                cunvexHullStack.push(point3);
            }
            //일직선일때 (point1, 2 ,3이 일직선이다.)
            else if (ccw == 0) {
                cunvexHullStack.push(point3);
            }
            //시계일때
            else {
                i--;
            }
        }

        //마지막 검사.
        Point point2 = cunvexHullStack.pop();
        Point point1 = cunvexHullStack.peek();
        Point point3 = minPoint;
        int ccw = ccw(point1.getX(), point1.getY(), point2.getX(), point2.getY(), point3.getX(), point3.getY());
        if (ccw > 0) {
            cunvexHullStack.push(point2);
        }

        System.out.println(cunvexHullStack.size());
    }

    /**
     *
     * @return 1 반시계 0 일직선 -1 시계
     */
    private static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long temp = x1*y2 + x2*y3 + x3*y1;
        long temp2 = y1*x2 + y2*x3 + y3*x1;
        return Long.compare(temp - temp2, 0);
    }

    public static double getAngle(Point one, Point two) {
        long temp1 = two.getY() - one.getY();
        long temp2 = two.getX() - one.getX();

        double rad = Math.atan2(temp1, temp2);
        return (rad * 180) / Math.PI;
    }
}

class Point {
    private final int x;
    private final int y;
    private double cosValue;
    private double distance;
    private double angle;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getCosValue() {
        return cosValue;
    }

    public void setCosValue(double cosValue) {
        this.cosValue = cosValue;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}

class CosComparator implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        int cosCompare = Double.compare(p1.getCosValue(), p2.getCosValue()) * -1;
        if (cosCompare != 0) {
            return cosCompare;
        }

        return Double.compare(p1.getDistance(), p2.getDistance());
    }
}

class AngleComparator implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        int angleCompare = Double.compare(p1.getAngle(), p2.getAngle()) * -1;
        if (angleCompare != 0) {
            return angleCompare;
        }

        return Double.compare(p1.getDistance(), p2.getDistance());
    }
}