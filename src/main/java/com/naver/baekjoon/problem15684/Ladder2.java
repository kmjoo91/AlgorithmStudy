package com.naver.baekjoon.problem15684;

import java.util.Scanner;

public class Ladder2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int width = scanner.nextInt();
		int lineNumber = scanner.nextInt();
		int height = scanner.nextInt();

		if (lineNumber == 0) {
			System.out.println(0);
			return;
		}

		boolean[][] ladderMap = new boolean[height][width];

		for (int i = 0; i < lineNumber; i++) {
			int y = scanner.nextInt();
			int x = scanner.nextInt();

			ladderMap[y - 1][x - 1] = true;
		}

		int result = calculate(width, height, 0, ladderMap, 0);

		System.out.println(result > 3 ? -1 : result);
	}

	private static int calculate(int width, int height, int startY, boolean[][] map, int value) {
		//지금 끝나면 끝내~
		if (isFinish(map)) {
			return value;
		}

		if (value == 3) {
			return 4;
		}

		int min = 4;
		for (int y = startY; y < height; y++) {
			for (int x = 0; x < width - 1; x++) {
				//추가가 가능하냐?
				if (map[y][x] || (x > 0 && map[y][x - 1]) || (x < width - 1 && map[y][x + 1])) {
					continue;
				}

				boolean[][] nextMap = deepCopy(map);
				nextMap[y][x] = true;
				int result = calculate(width, height, y, nextMap, value + 1);

				if (result == 1) {
					return 1;
				}
				if (min > result) {
					min = result;
				}
			}
		}

		return min;
	}

	private static boolean isFinish(boolean[][] map) {
		for (int x = 0; x < map[0].length; x++) {
			int moveX = x;
			for (int y = 0; y < map.length; y++) {
				//오른쪽으로 가면~
				if (map[y][moveX]) {
					moveX++;
				}
				//왼쪽으로 가면
				else if (moveX > 0 && map[y][moveX - 1]) {
					moveX--;
				}
				//아무데도 안가면
				else {
					// doNothing~
				}
			}

			if (x != moveX) {
				return false;
			}
		}

		return true;
	}

	private static boolean[][] deepCopy(boolean[][] original) {
		boolean[][] copyArr = new boolean[original.length][original[0].length];

		for (int y = 0; y < original.length; y++) {
			System.arraycopy(original[y], 0, copyArr[y], 0, original[0].length);
		}

		return copyArr;
	}
}
