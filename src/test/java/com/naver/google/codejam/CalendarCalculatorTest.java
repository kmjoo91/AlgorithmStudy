package com.naver.google.codejam;

import static org.junit.Assert.*;

import org.junit.Test;

import com.naver.google.codejam.calendarcalculator.CalendarCalculator;

/**
 * Created by AD on 2017-02-09.
 */
public class CalendarCalculatorTest {
	@Test
	public void 테스트_CalendarCalculator() {
		CalendarCalculator calendarCalculator = new CalendarCalculator(3, 11, 4);

		assertEquals(11, calendarCalculator.getCalendarLine());
	}
}