package com.naver.shortestpath.floyd;

import org.junit.Test;

import com.naver.shortestpath.floyd.Floyd;

/**
 * Created by AD on 2017-09-12.
 */
public class FloydTest {
	int[][] map = {
			{0, 100, 1, 100},
			{100, 0, 1, 1},
			{1, 1, 0, 100},
			{100, 1, 100, 0}
	};
	int[][] map2 = {
			{0, 100, 100, 1, 100},
			{100, 0, 1, 100, 1},
			{100, 1, 0, 1, 100},
			{1, 100, 1, 0, 100},
			{1001, 1, 100, 100, 0}
	};
	Floyd floyd = new Floyd(map2);

	@Test
	public void 테스트() {
		floyd.calculateShortestPath();
	}

	@Test
	public void 테스트2() {
		for (int i = 0; i < map2.length; i++) {
			System.out.println(String.format("%d번째 노드를 경유지로한 뒤 그래프", i));
			Floyd floyd2 = new Floyd(map2);
			floyd2.calculateNodePath(i);
			int[][] graph = floyd2.getDistance();
			print(graph);
		}
	}

	private void print(int[][] graph) {
		for (int row = 0; row < graph.length; row++) {
			for (int column = 0; column < graph[row].length; column++) {
				System.out.print(graph[row][column] + " ");
			}
			System.out.println();
		}
	}
}