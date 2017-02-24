package com.naver.prisoner;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by AD on 2017-02-24.
 */
public class PrisonerTest {
	Prisoner prisoner = new Prisoner(120);

	@Test
	public void 석방테스트() {
		assertEquals(10, prisoner.getReleasePrisoner());
		assertEquals(11, prisoner.getReleasePrisoner(121));
	}

}