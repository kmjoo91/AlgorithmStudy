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

	int[][] map2 = {
		{0, 3, 100, 4, 4, 100, 100, 100},
		{3, 0, 2, 100, 100, 100, 100, 100},
		{0, 2, 0, 1, 100, 100, 100, 3},
		{4, 100, 1, 0, 2, 100, 6, 100},
		{4, 100, 100, 2, 0, 4 , 100, 100},
		{100, 100, 100, 100, 4, 0, 3, 2},
		{100, 100, 100, 6, 100, 3, 0, 100},
		{100, 100, 3, 100, 100, 2, 100, 0}
	};
	Djikstra djikstra = new Djikstra(map2);

	@Test
	public void 테스트() {
		int distance = djikstra.calculateShortestPath(0, 7);

		System.out.println(distance);
	}


}