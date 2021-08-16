package com.naver.baekjoon.problem1937;

import java.util.Scanner;

public class Panda2 {
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
                searchRoot(x, y, map, memoization);

                int currentPoint = memoization[y][x];
                if (currentPoint > max) {
                    max = currentPoint;
                }
            }
        }
        System.out.println(max + 1);
    }

    private static void searchRoot(int x, int y, int[][] map, int[][] memoization) {
        //위에서 현재로 이동가능할 때,
        if (y > 0 && map[y][x] > map[y - 1][x]) {
            //탐색을 안했으면 탐색을하고 탐색을 했으면 할필요없다.
            if (memoization[y - 1][x] == 0) {
                searchRoot(x, y - 1, map, memoization);
            }
            //현재 값보다 위에서 현재로 올때가 더 크면 현재껄 바꿔준다.
            memoization[y][x] = memoization[y][x] > memoization[y - 1][x] + 1 ? memoization[y][x] : memoization[y - 1][x] + 1;
        }

        //아래에서 현재로 이동가능할 때,
        if (y + 1 < map.length && map[y][x] > map[y + 1][x]) {
            //탐색을 안했으면 탐색을하고 탐색을 했으면 할필요없다.
            if (memoization[y + 1][x] == 0) {
                searchRoot(x, y + 1, map, memoization);
            }
            //현재 값보다 아래에서 현재로 올때가 더 크면 현재껄 바꿔준다.
            memoization[y][x] = memoization[y][x] > memoization[y + 1][x] + 1 ? memoization[y][x] : memoization[y + 1][x] + 1;
        }

        //왼쪽에서 현재로 이동가능할 때,
        if (x > 0 && map[y][x] > map[y][x - 1]) {
            //탐색을 안했으면 탐색을하고 탐색을 했으면 할필요없다.
            if (memoization[y][x - 1] == 0) {
                searchRoot(x - 1, y, map, memoization);
            }
            //현재 값보다 아래에서 현재로 올때가 더 크면 현재껄 바꿔준다.
            memoization[y][x] = memoization[y][x] > memoization[y][x - 1] + 1 ? memoization[y][x] : memoization[y][x - 1] + 1;
        }

        //오른쪽에서 현재로 이동가능할 때,
        if (x + 1 < map[0].length && map[y][x] > map[y][x + 1]) {
            //탐색을 안했으면 탐색을하고 탐색을 했으면 할필요없다.
            if (memoization[y][x + 1] == 0) {
                searchRoot(x + 1, y, map, memoization);
            }
            //현재 값보다 아래에서 현재로 올때가 더 크면 현재껄 바꿔준다.
            memoization[y][x] = memoization[y][x] > memoization[y][x + 1] + 1 ? memoization[y][x] : memoization[y][x + 1] + 1;
        }
    }
}
