package com.naver.baekjoon.problem1724;

import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/1724
 */
public class FloodFill2 {
	private static final Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int height = scanner.nextInt();
		int width = scanner.nextInt();

		Space2[][] spaces = createSpace(height, width);


		int maxVolume = Integer.MIN_VALUE;
		int minVolume = Integer.MAX_VALUE;

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (spaces[row][col].isVisited()) {
					continue;
				}

				int currentVolume = getSpaceVolume(spaces, spaces[row][col]);

				if (maxVolume < currentVolume) {
					maxVolume = currentVolume;
				}

				if (minVolume > currentVolume) {
					minVolume = currentVolume;
				}
			}
		}

		System.out.println(maxVolume);
		System.out.println(minVolume);
	}

	private static int getSpaceVolume(Space2[][] spaces, Space2 root) {
		Stack<Space2> stack = new Stack<>();

		stack.push(root);

		int spaceVolume = 0;
		while (!stack.empty()) {
			Space2 space = stack.pop();

			if (space.isVisited()) {
				continue;
			}

			space.setVisited(Space2.VISITED);
			spaceVolume++;

			//상
			if (space.isCanBeMovedUp()) {
				Space2 upSpace = spaces[space.getRow() - 1][space.getColumn()];
				if (!upSpace.isVisited()) {
					stack.push(upSpace);
				}
			}
			//하
			if (space.isCanBeMovedBottom()) {
				Space2 bottomSpace = spaces[space.getRow() + 1][space.getColumn()];
				if (!bottomSpace.isVisited()) {
					stack.push(bottomSpace);
				}
			}
			//좌
			if (space.isCanBeMovedLeft()) {
				Space2 leftSpace = spaces[space.getRow()][space.getColumn() - 1];
				if (!leftSpace.isVisited()) {
					stack.push(leftSpace);
				}
			}
			//우
			if (space.isCanBeMovedRight()) {
				Space2 rightSpace = spaces[space.getRow()][space.getColumn() + 1];
				if (!rightSpace.isVisited()) {
					stack.push(rightSpace);
				}
			}
		}

		return spaceVolume;
	}

	private static Space2[][] createSpace(int height, int width) {
		Space2[][] spaces = new Space2[height][width];

		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				spaces[row][column] = new Space2();
				spaces[row][column].setVisited(Space2.UNVISITED);
				spaces[row][column].setRow(row);
				spaces[row][column].setColumn(column);
			}
		}

		for (int row = 0; row < height; row++) {
			//0번은 왼쪽이 벽이나 다름없고
			spaces[row][0].setCanBeMovedLeft(Space2.isCanNotBeMoved);
			//마지막은 오른쪽이 벽이나 다름없음
			spaces[row][width - 1].setCanBeMovedRight(Space2.isCanNotBeMoved);
		}

		for (int column = 0; column < width; column++) {
			spaces[0][column].setCanBeMovedUp(Space2.isCanNotBeMoved);
			spaces[height - 1][column].setCanBeMovedBottom(Space2.isCanNotBeMoved);
		}

		addWall(spaces);

		return spaces;
	}

	private static void addWall(Space2[][] spaces) {
		int lineNumber = scanner.nextInt();

		for (int i = 0; i < lineNumber; i++) {
			int row1 = scanner.nextInt();
			int col1 = scanner.nextInt();
			int row2 = scanner.nextInt();
			int col2 = scanner.nextInt();

			//x좌표가 같으니까 세로
			if (col1 == col2) {
				if (col1 >= spaces[0].length || col1 <= 0) {
					continue;
				}

				if (row1 > row2) {
					int temp = row1;
					row1 = row2;
					row2 = temp;
				}

				if (row2 >= spaces.length) {
					row2 = spaces.length;
				}

				for (int row = row1; row < row2; row++) {
					spaces[row][col1].setCanBeMovedLeft(Space2.isCanNotBeMoved);
					spaces[row][col1 - 1].setCanBeMovedRight(Space2.isCanNotBeMoved);
				}
			}
			//y좌표가 같으니까 가로
			else {
				if (row1 >= spaces.length || row1 <= 0) {
					continue;
				}

				if (col1 > col2) {
					int temp = col1;
					col1 = col2;
					col2 = temp;
				}

				if (col2 >= spaces[0].length) {
					col2 = spaces[0].length;
				}

				for (int col = col1; col < col2; col++) {
					spaces[row1][col].setCanBeMovedUp(Space2.isCanNotBeMoved);
					spaces[row1 - 1][col].setCanBeMovedBottom(Space2.isCanNotBeMoved);
				}
			}
		}
	}
}

class Space2 {
	public static final boolean UNVISITED = false;
	public static final boolean VISITED = true;
	public static final boolean isCanBeMoved = false;
	public static final boolean isCanNotBeMoved = true;

	private boolean visited;
	private int column;
	private int row;
	private boolean isCanBeMovedUp;
	private boolean isCanBeMovedBottom;
	private boolean isCanBeMovedLeft;
	private boolean isCanBeMovedRight;

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public boolean isCanBeMovedUp() {
		return isCanBeMovedUp == Space2.isCanBeMoved;
	}

	public void setCanBeMovedUp(boolean canBeMovedUp) {
		isCanBeMovedUp = canBeMovedUp;
	}

	public boolean isCanBeMovedBottom() {
		return isCanBeMovedBottom == Space2.isCanBeMoved;
	}

	public void setCanBeMovedBottom(boolean canBeMovedBottom) {
		isCanBeMovedBottom = canBeMovedBottom;
	}

	public boolean isCanBeMovedLeft() {
		return isCanBeMovedLeft == Space2.isCanBeMoved;
	}

	public void setCanBeMovedLeft(boolean canBeMovedLeft) {
		isCanBeMovedLeft = canBeMovedLeft;
	}

	public boolean isCanBeMovedRight() {
		return isCanBeMovedRight == Space2.isCanBeMoved;
	}

	public void setCanBeMovedRight(boolean canBeMovedRight) {
		isCanBeMovedRight = canBeMovedRight;
	}
}