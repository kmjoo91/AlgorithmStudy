/*
 *@(#)GeneratorNumber.java 2017.01.11
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.nexon;

import java.util.ArrayList;
import java.util.List;

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
		//계산을 편하게 하기위해 범위+1크기의 배열을 만든다
		boolean[] selfNumberFlagArray = new boolean[range+1];

		//범위를 돌며 Generator 숫자를 구하고 배열에 체크해준다.
		for (int i = 1; i <= selfNumberFlagArray.length; i++) {
			int generatorNumber = calculateGenerator(i); //식.1
			if (generatorNumber >= selfNumberFlagArray.length) {
				continue;
			}
			selfNumberFlagArray[generatorNumber] = true;
		}
		return selfNumberFlagArray;
	}

	private int calculateGenerator(int targetNumber) {
		int sum = targetNumber;
		int cipher = (int)Math.log10(targetNumber);
		for (int i = 0; i <= cipher; i++) {
			sum += targetNumber%10;
			targetNumber /= 10;
		}
		return sum;
	}

	public int getGeneratorNumber(int targetNumber) {
		int minPossipleNumber = getMinPossibleNumber(targetNumber);
		int minCipher = (int)Math.log10(minPossipleNumber);
		int minNumber = 0;
		for (int i = 1; i <= minCipher; i++) {
			minNumber += minPossipleNumber/(int)Math.pow(10, i);
		}
		int maxCipher = (int)Math.log10(targetNumber);
		int maxNumber = 0;
		for (int i = 1; i <= maxCipher; i++) {
			maxNumber += targetNumber/(int)Math.pow(10, i);
		}


		for (int i = minNumber; i <= maxNumber; i++) {
			int generatorNumber = (targetNumber + (9 * i)) / 2;
			int generator = calculateGenerator(generatorNumber);
			if (generator == targetNumber) {
				return generatorNumber;
			}
		}
		return -1;
	}

	/**
	 * 범위내 selfNumber들을 구하는 함수
	 *
	 * @param range selfNumber를 구할 범위
	 * @return 범위 내 selfNumber의 집합
	 */
	public List<Integer> getSelfNumberList(int range) {
		List<Integer> selfNumberList = new ArrayList<Integer>();
		for (int i = 1; i <= range; i++) {
			if (isSelfNumber(i)) {
				selfNumberList.add(i);
			}
		}
		return selfNumberList;
	}

	/**
	 * 셀프 넘버인지 확인하는 함수
	 *
	 * @param targetNumber SelfNumber인지 구할 수
	 * @return 셀프넘버인지 아닌지
	 */
	public boolean isSelfNumber(int targetNumber) {
		int minPossipleNumber = getMinPossibleNumber(targetNumber);

		for (int i = minPossipleNumber; i < targetNumber; i++) {
			int generator = calculateGenerator(i);
			if (generator == targetNumber) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 최소 범위의 수를 구하는 함수
	 *
	 * @param targetNumber SelfNumber인지 확인할 수
	 * @return 최소범위의 수.
	 */
	private int getMinPossibleNumber(int targetNumber) {
		int cipher = (int)Math.log10(targetNumber);
		int maxDistinct = 9*cipher + targetNumber/(int)Math.pow(10,cipher);
		return targetNumber - maxDistinct;
	}
}