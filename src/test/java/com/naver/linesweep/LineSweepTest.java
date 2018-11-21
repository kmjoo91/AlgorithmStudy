package com.naver.linesweep;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.naver.MockitoExtension;
import com.naver.linesweep.model.Point;

@ExtendWith(MockitoExtension.class)
class LineSweepTest {
	private LineSweep lineSweep = new LineSweep();
	@Test
	void test() {
		Point[] points = {
				new Point(0, 0),
				new Point(10, 10),
				new Point(10, 0),
				new Point(11, 0)
		};

		System.out.println(lineSweep.calculateDistance(points));
	}
}