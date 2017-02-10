/*
 *@(#)CalendarCalculator.java 2017.02.09
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.google.codejam;

/**
 *
 *
 * @author kim.minjoo
 */
public class CalendarCalculator {
	long month;
	int dayOfMonth;
	int dayOfWeeks;

	public CalendarCalculator(long month, int dayOfMonth, int dayOfWeeks) {
		this.month = month;
		this.dayOfMonth = dayOfMonth;
		this.dayOfWeeks = dayOfWeeks;
	}

	public long getCalendarLine() {
		//공식은 (총일수 / 주당일수)  1년에 몇주. + 총 달수(한달이 변할때마다 새로시작하므로 1줄씩추가) - (주가같이바뀔때랑 달이같이바뀔때 둘다 더해졌으므로 빼준다.)
		long line = ((month * dayOfMonth ) / dayOfWeeks) + month ;

		int dayOfLastWeeks = dayOfMonth;
		if (dayOfMonth > dayOfWeeks) {
			//몇주마다 달이랑 주랑 같이변하나~
			dayOfLastWeeks %= dayOfWeeks;
		}

		if (dayOfLastWeeks != 0) {
			if (dayOfWeeks % dayOfLastWeeks == 0) {
				line -= month / (dayOfWeeks / dayOfLastWeeks);
			} else {
				int lcm = getLCM(dayOfWeeks, dayOfLastWeeks);
				line -= month / (lcm / dayOfLastWeeks);
			}
		} else {
			line -= month;
		}

		return line;
	}


	private int getGCD(int num1, int num2) {
		if (num1 < num2) {
			int temp = num1;
			num1 = num2;
			num2 = temp;
		}

		while (num1 % num2 > 0) {
			int temp = num1 % num2;
			num1 = num2;
			num2 = temp;
		}

		return num2;
	}

	private int getLCM(int num1, int num2) {
		int gcd = getGCD(num1, num2);

		return num1*num2 / gcd;
	}

}