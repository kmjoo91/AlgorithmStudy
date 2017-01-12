/*
 *@(#)GeneratorNumber.java 2017.01.11
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package nexon;

/**
 *
 *
 * @author kim.minjoo
 */
public class SelfNumber {
	private int range;

	public SelfNumber(){}

	public SelfNumber(int range) {
		this.range = range;
	}

	public void printSelfNumber() {
		printSelfNumber(range);
	}

	public void printSelfNumber(int range) {
		boolean[] selfNumberFlagArray = getSelfNumberFlagArray(range);
		for (int i = 1; i < selfNumberFlagArray.length; i++) {
			if (selfNumberFlagArray[i] == false) {
				System.out.println(i);
			}
		}
	}

	private boolean[] getSelfNumberFlagArray(int range) {
		boolean[] selfNumberFlagArray = new boolean[range+1];
		for (int i = 0; i <= selfNumberFlagArray.length; i++) {
			int generatorNumber = getGeneratorNumber(i);
			if (generatorNumber >= selfNumberFlagArray.length) {
				continue;
			}
			selfNumberFlagArray[generatorNumber] = true;
		}
		return selfNumberFlagArray;
	}

	private int getGeneratorNumber(int targetNumber) {
		int sum = targetNumber;
		int cipher = (int)Math.log10(targetNumber);
		for (int i = 0; i <= cipher; i++) {
			sum += targetNumber%10;
			targetNumber /= 10;
		}
		return sum;
	}
}