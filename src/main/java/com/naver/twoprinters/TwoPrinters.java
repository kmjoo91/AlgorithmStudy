/*
 *@(#)TwoPrinters.java 2017.03.22
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.twoprinters;

/**
 *
 *
 * @author kim.minjoo
 */
public class TwoPrinters {
	int printer1Speed;
	int printer2Speed;
	int totalPrintPage;

	public TwoPrinters(int printer1Speed, int printer2Speed, int totalPrintPage) {
		this.printer1Speed = printer1Speed;
		this.printer2Speed = printer2Speed;
		this.totalPrintPage = totalPrintPage;
	}

	public int getPrintingTime() {
		double printer1Page = ( totalPrintPage * printer2Speed ) / (double)(printer1Speed + printer2Speed);
		double printer2Page = totalPrintPage - printer1Page;

		int integerPrinter1Page = (int)printer1Page;
		int integerPrinter2Page = (int)printer2Page;

		int print1PrintingTime = integerPrinter1Page * printer1Speed;
		int print2PrintingTime = integerPrinter2Page * printer2Speed;

		if (integerPrinter1Page + integerPrinter2Page == totalPrintPage) {
			return print1PrintingTime > print2PrintingTime ? print1PrintingTime : print2PrintingTime;
		}

		print1PrintingTime += printer1Speed;
		print2PrintingTime += printer2Speed;

		return print1PrintingTime < print2PrintingTime ? print1PrintingTime : print2PrintingTime;
	}
}