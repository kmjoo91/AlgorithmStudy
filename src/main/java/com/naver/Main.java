package com.naver;
/*
 *@(#)Main.java 2017.01.11
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.naver.amazon.EmployeeNumber;
import com.naver.google.NumberCountCalculator;
import com.naver.google.codejam.CalendarCalculator;
import com.naver.nexon.SelfNumber;

/**
 *
 *
 * @author kim.minjoo
 */
public class Main {
	public static void main(String[] args) throws Exception {
		//executeNumberCountCalculator();
		//executePrintSelfNumber();
		//executeEmployeeNumber();
		executeCalendarCalculator();
	}

	private static void executeCalendarCalculator() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("calendarExample.txt"));
		int maxCase = Integer.parseInt(in.readLine());
		for (int i=1; i <= maxCase; i++) {
			String line = in.readLine();
			String[] inputStr = line.split(" ");
			CalendarCalculator calendarCalculator = new CalendarCalculator(Long.parseLong(inputStr[0]), Integer.parseInt(inputStr[1]), Integer.parseInt(inputStr[2]));
			System.out.print("Case #" + i +": ");
			System.out.println(calendarCalculator.getCalendarLine());
		}
		in.close();
	}

	private static void executeEmployeeNumber() {
		EmployeeNumber employeeNumber = new EmployeeNumber();
		System.out.println(employeeNumber.getEmployeeNumber("EmployeeLog.txt", "10:00:00"));
	}

	private static void executeNumberCountCalculator() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the number you want to find (0~9)");
		int inputNumber = scan.nextInt();
		System.out.println("Please enter a range of numbers to find");
		int findRange = scan.nextInt();
		NumberCountCalculator numberCountCalculator = new NumberCountCalculator();

		int numberCount = numberCountCalculator.calculateNumberCount(inputNumber, findRange);

		System.out.println("Number of appearances : " + numberCount);
	}

	private static void executePrintSelfNumber() {
		SelfNumber selfNumber = new SelfNumber();
		selfNumber.printSelfNumber(500);
	}
}