/*
 *@(#)Main.java 2017.01.11
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.Scanner;

/**
 *
 *
 * @author kim.minjoo
 */
public class Main {
	public static void Main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the number you want to find (0~9)");
		int inputNumber = scan.nextInt();
		System.out.println("Please enter a range of numbers to find");
		int findRange = scan.nextInt();
		NumberCountCalculator numberCountCalculator = new NumberCountCalculator();

		int numberCount = numberCountCalculator.calculateNumberCount(inputNumber, findRange);

		System.out.println("Number of appearances : " + numberCount);
	}
}