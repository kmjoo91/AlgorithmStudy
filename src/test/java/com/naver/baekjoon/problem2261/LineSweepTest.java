package com.naver.baekjoon.problem2261;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.naver.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LineSweepTest {
	private LineSweep lineSweep = new LineSweep();
	@Test
	void test() {
		//given
		Point p1 = new Point(0, 0);
		Point p2 = new Point(10, 10);
		Point p3 = new Point(10, 0);
		Point p4 = new Point(0, 10);
		Point[] points = {p1, p2, p3, p4};
		Pair expected = new Pair(p1, p4, Math.sqrt(100));

		//when
		Pair actual = LineSweep.calculateDistance(points);

		//then
		assertEquals(expected.getP1(), actual.getP1());
		assertEquals(expected.getP2(), actual.getP2());
		assertEquals(expected.getDistance(), actual.getDistance());
	}
}