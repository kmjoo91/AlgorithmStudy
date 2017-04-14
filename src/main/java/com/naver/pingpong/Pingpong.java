/*
 *@(#)Pingpong.java 2017.03.31
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.pingpong;

/**
 *
 *
 * @author kim.minjoo
 */
public class Pingpong {
	public int pingpong(int index) {
		int result = 0;
		boolean isMinus = false;
		for (int i = 1; i <= index; i++) {
			if (isMinus) {
				result--;
			} else {
				result++;
			}

			if (i % 7 == 0) {
				isMinus = !isMinus;
				//System.out.println("여기서바뀜ㅋ");
			} else {
				int cipher = (int)Math.log10(i);
				int num = i;
				for (int j = 0; j <= cipher; j++) {
					if (num % 10 == 7) {
						isMinus = !isMinus;
						//System.out.println("여기서바뀜ㅋ");
					}
					num /= 10;
				}
			}


			//System.out.println(String.format("%d번째 : %d", i, result));
		}
		return result;
	}

	public int pingpong2(int index) {
		return recursivePingpong2(index, 1, 0, false);
	}

	private int recursivePingpong2(int index, int count, int result, boolean isMinus) {
		if (isMinus) {
			result--;
		} else {
			result++;
		}

		if (count % 7 == 0) {
			isMinus = !isMinus;
			//System.out.println("여기서바뀜ㅋ");
		} else {
			if (containsSeven(count)) {
				isMinus = !isMinus;
			}
		}

		if (index == count) {
			return result;
		}

		return recursivePingpong2(index, count+1, result, isMinus);
	}

	private boolean containsSeven(int count) {
		if (count > 0) {
			if (count % 10 == 7) {
				return true;
			}
			return containsSeven(count/10);
		}

		return false;
	}

}