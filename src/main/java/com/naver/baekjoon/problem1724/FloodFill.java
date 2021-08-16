package com.naver.baekjoon.problem1724;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/1724
 */
public class FloodFill {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<List<Space>> spaces = createSpaces();

        int maxSpaceVolume = Integer.MIN_VALUE;
        int minSpaceVolume = Integer.MAX_VALUE;

        for (int row = 0; row < spaces.size() - 1; row++) {
            for (int column = 0; column < spaces.get(0).size() - 1; column++) {
                if (spaces.get(row).get(column) != Space.UNVISITED) {
                    continue;
                }

                int currentSpaceVolume = getSpaceVolume(spaces, row, column);

                if (currentSpaceVolume > maxSpaceVolume) {
                    maxSpaceVolume = currentSpaceVolume;
                }
                if (currentSpaceVolume < minSpaceVolume) {
                    minSpaceVolume = currentSpaceVolume;
                }
            }
        }

        System.out.println(maxSpaceVolume);
        System.out.println(minSpaceVolume);
    }

    private static int getSpaceVolume(List<List<Space>> spaces, int row, int column) {
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(column, row));

        int result = 0;
        while (!stack.empty()) {
            Point currentPoint = stack.pop();

            //현재꺼를 VISTITED로 바꿔준다.
            Space currentSpace = spaces.get(currentPoint.getY()).get(currentPoint.getX());

            if (currentSpace == Space.UNVISITED) {
                result++;
            } else if (currentSpace != Space.NOT_VOLUME_SPACE) {
                continue;
            }

            spaces.get(currentPoint.getY()).remove(currentPoint.getX());
            spaces.get(currentPoint.getY()).add(currentPoint.getX(), Space.VISITED);

            //위
            Space upSpace = currentPoint.getY() > 0 ? spaces.get(currentPoint.getY() - 1).get(currentPoint.getX()) : Space.WALL;
            if (upSpace == Space.UNVISITED || upSpace == Space.NOT_VOLUME_SPACE) {
                stack.push(new Point(currentPoint.getX(), currentPoint.getY() - 1));
            }
            //아래
            Space bottomSpace = currentPoint.getY() < spaces.size() - 1 ? spaces.get(currentPoint.getY() + 1).get(currentPoint.getX()) : Space.WALL;
            if (bottomSpace == Space.UNVISITED || bottomSpace == Space.NOT_VOLUME_SPACE) {
                stack.push(new Point(currentPoint.getX(), currentPoint.getY() + 1));
            }
            //좌
            Space leftSpace = currentPoint.getX() > 0 ? spaces.get(currentPoint.getY()).get(currentPoint.getX() - 1) : Space.WALL;
            if (leftSpace == Space.UNVISITED || leftSpace == Space.NOT_VOLUME_SPACE) {
                stack.push(new Point(currentPoint.getX() - 1, currentPoint.getY()));
            }
            //우
            Space rightSpace = currentPoint.getX() < spaces.get(currentPoint.getY()).size() - 1 ? spaces.get(currentPoint.getY()).get(currentPoint.getX() + 1) : Space.WALL;
            if (rightSpace == Space.UNVISITED || rightSpace == Space.NOT_VOLUME_SPACE) {
                stack.push(new Point(currentPoint.getX() + 1, currentPoint.getY()));
            }
        }
        return result;
    }

    private static void addWall(List<List<Space>> spaces) {
        int lineNumber = scanner.nextInt();

        for (int i = 0; i < lineNumber; i++) {
            int row1 = scanner.nextInt();
            int col1 = scanner.nextInt();
            int row2 = scanner.nextInt();
            int col2 = scanner.nextInt();

            //세로로 벽생길때
            if (col1 == col2) {
                int col = 0;
                for (int spaceColumn = 0; spaceColumn < col1; col++) {
                    if (spaces.get(spaces.size() - 1).get(col) == Space.VIRTUAL) {
                        continue;
                    }
                    spaceColumn++;
                }

                if (row1 > row2) {
                    int temp = row1;
                    row1 = row2;
                    row2= temp;
                }

                int row = 0;
                for (int spaceRow = 0; spaceRow < spaces.size() - 1; spaceRow++) {
                    List<Space> currentRow = spaces.get(spaceRow);
                    if (currentRow.get(currentRow.size() - 1) == Space.VIRTUAL) {
                        currentRow.add(col, Space.WALL);
                    } else {
                        if (row1 <= row && row < row2) {
                            currentRow.add(col, Space.WALL);
                        } else {
                            currentRow.add(col, Space.NOT_VOLUME_SPACE);
                        }
                        row++;
                    }
                }
                spaces.get(spaces.size() - 1).add(col, Space.VIRTUAL);
            }
            //가로로 벽생길때
            else {
                int row = 0;
                for (int spaceRow = 0; spaceRow < row1 ;row++) {
                    if (spaces.get(row).get(spaces.get(row).size() - 1) == Space.VIRTUAL) {
                        continue;
                    }
                    spaceRow++;
                }

                List<Space> line = new ArrayList<>();
                List<Space> columnJudge = spaces.get(spaces.size() - 1);
                if (col1 > col2) {
                    int temp = col1;
                    col1 = col2;
                    col2= temp;
                }
                int col = 0;
                for (int spaceColumn = 0; spaceColumn < spaces.get(0).size() - 1; spaceColumn++) {
                    if (columnJudge.get(spaceColumn) == Space.VIRTUAL) {
                        line.add(Space.WALL);
                    } else {
                        //컬럼이 범위일때
                        if (col1 <= col && col < col2) {
                            line.add(Space.WALL);
                        } else {
                            line.add(Space.NOT_VOLUME_SPACE);
                        }
                        col++;
                    }
                }
                line.add(Space.VIRTUAL);
                spaces.add(row, line);
            }
        }
    }

    private static List<List<Space>> createSpaces() {
        List<List<Space>> spaces = new ArrayList<>();

        int height = scanner.nextInt();
        int width = scanner.nextInt();

        for (int i = 0; i < height; i++) {
            spaces.add(new ArrayList<>());
            List<Space> row = spaces.get(i);
            for (int j = 0; j < width; j++) {
                row.add(Space.UNVISITED);
            }
            row.add(Space.REAL);
        }

        spaces.add(new ArrayList<>());
        for (int i = 0; i < width; i++) {
            spaces.get(height).add(Space.REAL);
        }

        addWall(spaces);

        return spaces;
    }
}
enum Space {
    UNVISITED,
    VISITED,
    WALL,
    NOT_VOLUME_SPACE,
    REAL,
    VIRTUAL;
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
}
