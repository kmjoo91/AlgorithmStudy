package com.naver.backtracking;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.naver.backtracking.model.Point;

public class NQueenTest {
	@Test
	public void Eight_Queen() {
		List<Point> eightQueen = NQueen.getNQueenPosition(8);

		NQueen.showPointList(eightQueen);
	}
}