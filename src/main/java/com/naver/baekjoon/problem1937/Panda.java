package com.naver.baekjoon.problem1937;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1937
 */
public class Panda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();

        int[][] map = new int[size][size];
        int[][] memoization = new int[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                map[y][x] = scanner.nextInt();
            }
        }

        int max = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                memoization[y][x] = depthFirstSearch(x, y, map, memoization);

                int currentPoint = memoization[y][x];
                if (currentPoint > max) {
                    max = currentPoint;
                }
            }
        }
        System.out.println(max);
    }

    private static int depthFirstSearch(int x, int y, int[][] map, int[][] memoization) {
        int value1 = searchRoot(x - 1, y, x, y, map, memoization);

        int value2 = searchRoot(x + 1, y, x, y, map, memoization);

        int value3 = searchRoot(x, y - 1, x, y, map, memoization);

        int value4 = searchRoot(x, y + 1, x, y, map, memoization);

        return Math.max(Math.max(value1, value2), Math.max(value3, value4)) + 1;
    }

    private static int searchRoot(int fromX, int fromY, int toX, int toY, int[][] map, int[][] memoization) {
        //정상범위가 아닐때.
        int size = map.length;
        if (fromX < 0 || fromY < 0 || fromX >= size || fromY >= size || map[fromY][fromX] >= map[toY][toX])  {
            return 0;
        }

        //미탐색
        if (memoization[fromY][fromX] == 0) {
            memoization[fromY][fromX] = depthFirstSearch(fromX, fromY, map, memoization);
        }

        return memoization[fromY][fromX];
    }
}