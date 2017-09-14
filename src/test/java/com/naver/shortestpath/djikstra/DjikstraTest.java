package com.naver.shortestpath.djikstra;

import static org.junit.Assert.*;

import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

/**
 * Created by AD on 2017-09-14.
 */
public class DjikstraTest {
	int[][] map = {
			{0, 100, 100, 1, 100},
			{100, 0, 1, 100, 1},
			{100, 1, 0, 1, 100},
			{1, 100, 1, 0, 100},
			{1001, 1, 100, 100, 0}
	};
	Djikstra djikstra = new Djikstra(map);

	@Test
	public void 테스트() {
		Queue<Integer> route = djikstra.calculateShortestPath(0, 4);

		while (route.isEmpty() == false) {
			int node = route.poll();
			System.out.println(node);
		}
	}
}