package com.naver.twoprinters;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by AD on 2017-03-22.
 */
public class TwoPrintersTest {
	int[][] example = {
			{1, 1, 5},
			{3, 5, 4}
	};
	@Test
	public void 테스트() {
		for (int i = 0; i < example.length; i++) {
			TwoPrinters twoPrinters = new TwoPrinters(example[i][0], example[i][1], example[i][2]);
			System.out.println(twoPrinters.getPrintingTime());
		}
	}
}