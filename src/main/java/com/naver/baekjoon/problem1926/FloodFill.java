package com.naver.baekjoon.problem1926;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Scanner;
import java.util.Stack;

public class FloodFill {
    private static final int WALL = 0;
    private static final int UNVISITED = 1;
    private static final int VISITED = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int height = scanner.nextInt();
        int width = scanner.nextInt();

        int[][] space = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                space[i][j] = scanner.nextInt();
            }
        }

        int spaceNumber = 0;
        int maxSpaceVolume = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (space[i][j] != UNVISITED) {
                    continue;
                }

                int currentVolume = calculateSpaceVolume(space, i, j);

                if (currentVolume > maxSpaceVolume) {
                    maxSpaceVolume = currentVolume;
                }
                spaceNumber++;
            }
        }

        System.out.println(spaceNumber);
        System.out.println(maxSpaceVolume);
    }

    private static int calculateSpaceVolume(int[][] space, int row, int column) {
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(column, row));

        int spaceVolume = 0;

        while (!stack.empty()) {
            Point currentPoint = stack.pop();


            int currentSpace = space[currentPoint.getY()][currentPoint.getX()];

            if (currentSpace != UNVISITED) {
                continue;
            }

            spaceVolume++;
            space[currentPoint.getY()][currentPoint.getX()] = VISITED;

            int upSpace = currentPoint.getY() > 0 ? space[currentPoint.getY() - 1][currentPoint.getX()] : WALL;
            if (upSpace == UNVISITED) {
                stack.push(new Point(currentPoint.getX(), currentPoint.getY() - 1));
            }
            int bottomSpace = currentPoint.getY() < space.length - 1 ? space[currentPoint.getY() + 1][currentPoint.getX()] : WALL;
            if (bottomSpace == UNVISITED) {
                stack.push(new Point(currentPoint.getX(), currentPoint.getY() + 1));
            }
            int leftSpace = currentPoint.getX() > 0 ? space[currentPoint.getY()][currentPoint.getX() - 1] : WALL;
            if (leftSpace == UNVISITED) {
                stack.push(new Point(currentPoint.getX() - 1, currentPoint.getY()));
            }
            int rightSpace = currentPoint.getX() < space[currentPoint.getY()].length - 1 ? space[currentPoint.getY()][currentPoint.getX() + 1] : WALL;
            if (rightSpace == UNVISITED) {
                stack.push(new Point(currentPoint.getX() + 1, currentPoint.getY()));
            }
        }

        return spaceVolume;
    }
}
class Point {
    private int x;
    private int y;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}