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
public class GeneratorNumber {
	public boolean isGeneratorNumber(int targetNumber) {
		int targetNumberCipher = (int)Math.log10(targetNumber);
		if (isNecessaryCaculation(targetNumber, targetNumberCipher)) {
			//미니멈도
			if (caculateGenerator(targetNumber, targetNumberCipher-1)) {
				return true;
			}
		}
		boolean a = caculateGenerator(targetNumber, targetNumberCipher);
		System.out.println(a);
		return a;
	}

	private boolean caculateGenerator(int targetNumber, int cipher) {
		int startNumber = (int)Math.pow(10, cipher);
		if (startNumber < 1) {
			startNumber = 1;
		}
		System.out.println("targetNumber : " + targetNumber + " startNumber : " + startNumber + " cipher : " + cipher + " 숫자 : " + getGenerator(startNumber));
		System.out.println("숫자 2 : " + (targetNumber%getGenerator(startNumber))%2 + " 숫자3 : " + cipher%2);
		return (targetNumber%getGenerator(startNumber))%2 == cipher%2;
	}

	public boolean isNecessaryCaculation(int targetNumber, int cipher) {
		int cipherMaxNumber = (int)Math.pow(10, cipher) - 1;
		if (cipherMaxNumber < 1) {
			cipherMaxNumber = 1;
		}
		int maxGeneratorNumber = getGenerator(cipherMaxNumber);

		return targetNumber < maxGeneratorNumber;
	}

	public int getGenerator(int number) {
		int sum = number;
		int cipher = (int)Math.log10(number)+1;
		for(int i = 1; i <= cipher ; i++) {
			sum += number %Math.pow(10, i);
			number /= Math.pow(10, i);
		}
		return sum;
	}
}