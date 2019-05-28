package com.naver.baekjoon.problem15684;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ladder {
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

		int result = 0;
		//검증
		for (int i = 0; i < width; i++) {
			Result resultObj = calculate(i, 0, height, ladderMap, 0);
			 result += resultObj.getCount();
			 ladderMap = resultObj.getMap();

			 if (result > 3) {
				 System.out.println(-1);
				 return;
			 }
		}

		System.out.println(result);
	}

	private static Result calculate(int x, int fromY, int toY, boolean[][] ladderMap, int count) {
		if (count > 3) {
			return new Result(count, ladderMap);
		}

		List<Integer> list = new ArrayList<>();
		for (int y = fromY; y < toY; y++) {
			if (ladderMap[y][x]) {
				list.add(y);
			}
		}

		// 사다리가 한개도 없을 경우.
		if (list.size() == 0) {
			return new Result(count, ladderMap);
		}

		//사다리가 짝수개일 때
		if (list.size() % 2 == 0) {
			int result = count;
			boolean[][] resultMap = ladderMap;
			for (int i = 0; i < list.size(); i += 2) {
				Result resultObj = calculate(x + 1, list.get(i), list.get(i + 1), resultMap, count);
				result += resultObj.getCount();
				resultMap = resultObj.getMap();
			}
			return new Result(result, resultMap);
		} else {
			//여기선 추가를 해줘야하는데...... 추가를 할 수 있는곳에 전부 해보고 젤 작은게 답인데....
			int firstValue = list.get(0);

			int result = 4;
			boolean[][] resultMap = deepCopy(ladderMap);

			if (firstValue > fromY) {
				for (int y = fromY; y < firstValue; y++) {
					boolean[][] copyMap = deepCopy(ladderMap);
					//추가 가능할때만 추가해야함....
					if (copyMap[y][x + 1] || (x > 0 && copyMap[y][x-1])) {
						continue;
					}

					copyMap[y][x] = true;
					Result resultObj = calculate(x + 1, y , firstValue, copyMap, count + 1);

					if (result > resultObj.getCount() + count) {
						result = resultObj.getCount() + count;
						resultMap = resultObj.getMap();
					}
				}
			}

			for (int i = 0; i < list.size(); i++) {
				int current = 4;
				boolean[][] currentMap = deepCopy(resultMap);
				int top = list.get(i);
				//마지막 일 경우
				if (i == list.size() - 1) {
					//추가 못함.
					if (top == toY) {
						break;
					}

					for (int bottom = top + 1; bottom < toY; bottom++) {
						boolean[][] copyMap = deepCopy(ladderMap);
						Result resultObj = null;
						//추가 가능할 경우 추가해보고 결과 계산해봐야지...
						if (copyMap[bottom][x + 1] || (x > 0 && copyMap[bottom][x-1])) {
							continue;
						}

						copyMap[bottom][x] = true;
						resultObj = calculate(x + 1, top, bottom, copyMap, count + 1);

						if (current > resultObj.getCount()) {
							current = resultObj.getCount();
							currentMap = resultObj.getMap();
						}
					}

					if (result > current) {
						result = current;
						resultMap = currentMap;
					}
				} else {
					for (int bottom = top + 1; bottom < list.get(i + 1); bottom++) {
						boolean[][] copyMap = deepCopy(ladderMap);
						//추가 가능할때만 추가해야함....
						if (copyMap[bottom][x + 1] || (x > 0 && copyMap[bottom][x-1])) {
							continue;
						}

						copyMap[bottom][x] = true;
						Result resultObj = calculate(x + 1, top, bottom, copyMap, count + 1);

						if (result > resultObj.getCount()) {
							result = resultObj.getCount();
							resultMap = resultObj.getMap();
						}
					}
				}
			}

			return new Result(result, resultMap);
		}
	}

	private static boolean[][] deepCopy(boolean[][] original2) {
		if(original2 == null) return null;
		boolean[][] result = new boolean[original2.length][original2[0].length];

		for(int i=0; i<original2.length; i++){
			System.arraycopy(original2[i], 0, result[i], 0, original2[0].length);
		}

		return result;
	}
}

class Result {
	int count;
	boolean[][] map;

	public Result(int count, boolean[][] map) {
		this.count = count;
		this.map = map;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean[][] getMap() {
		return map;
	}

	public void setMap(boolean[][] map) {
		this.map = map;
	}
}