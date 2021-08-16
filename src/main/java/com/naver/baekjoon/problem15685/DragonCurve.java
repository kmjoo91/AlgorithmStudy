package com.naver.baekjoon.problem15685;

import java.util.ArrayList;
import java.util.List;

public class DragonCurve {
	private static boolean IS_DRAGONCURVE = true;
	private static boolean NOT_DRAGONCURVE = false;

	private boolean[][] map;

	public DragonCurve() {
		this.map = new boolean[101][101];
	}

	public DragonCurve(int width, int height) {
		this.map = new boolean[width][height];
	}

	public void print() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(map[i][j] ? 1 : 0 );
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	public int calculate() {
		int count = 0;
		for (int height = 0; height < map.length - 1; height++) {
			for (int width = 0; width < map[0].length - 1; width++) {
				boolean point1 = map[height][width];
				boolean point2 = map[height][width + 1];
				boolean point3 = map[height + 1][width];
				boolean point4 = map[height + 1][width + 1];

				if (point1 && point2 && point3 && point4) {
					count ++;
				}
			}
		}
		return count;
	}

	public void createDragonCurve(int x, int y, int direction, int generation) {
		this.map[y][x] = true;
		List<Integer> directionList = new ArrayList<>();
		directionList.add(direction);
		for (int i = 0; i < generation; i++) {
			addNextGenerationDirection(directionList);
		}

		moveDirection(x, y, directionList);
	}

	private void addNextGenerationDirection(List<Integer> directionList) {
		for (int i = directionList.size() - 1; i >= 0; i--) {
			int addDirection = (directionList.get(i) + 1) % 4;
			directionList.add(addDirection);
		}
	}

	private void moveDirection(int x, int y, List<Integer> directionList) {
		for (int i = 0; i < directionList.size(); i++) {
			switch (directionList.get(i)) {
				case 0:
					x++;
					break;
				case 1:
					y--;
					break;
				case 2:
					x--;
					break;
				case 3:
					y++;
					break;
			}

			map[y][x] = true;
		}
	}
}

