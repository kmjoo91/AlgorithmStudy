package com.naver.book.p155;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by AD on 2017-03-30.
 */
public class BribeThePrisonersTest {
	BribeThePrisoners bribeThePrisoners = new BribeThePrisoners(20, 3, new int[] {3, 6, 14});

	@Test
	public void 테스트() {
		bribeThePrisoners.getTotalBribe();
	}

}