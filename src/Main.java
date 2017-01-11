/*
 *@(#)Main.java 2017.01.11
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.Scanner;
import google.NumberCountCalculator;
import nexon.GeneratorNumber;

/**
 *
 *
 * @author kim.minjoo
 */
public class Main {
	public static void main(String[] args) {
		//executeNumberCountCalculator();
		System.out.println(0%2);
		GeneratorNumber generatorNumber = new GeneratorNumber();
		int sum2 = 0;
		for (int i= 1; i <= 5000; i++) {
			sum2 += i;
		}
		System.out.println(sum2);
		int sum = 0;
		for (int i= 1; i <= 10; i++) {
			if (generatorNumber.isGeneratorNumber(i) == false) {
				System.out.println(i);
				sum += i;
			}
		}
		System.out.println(sum);
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
}