package com.naver.kakao.year2019;

import java.util.Arrays;

public class FoodTime {
	public int solution(int[] food_times, long k) {
		int[] sortedFoodTime = Arrays.copyOf(food_times, food_times.length);

		Arrays.sort(sortedFoodTime);

		int standard = 0;
		for (int i = 0; i < sortedFoodTime.length; i++) {
			int currentLength = sortedFoodTime.length - i;

			if (k < currentLength) {
				break;
			}

			boolean isBreak = false;
			int eatNumber = sortedFoodTime[i] - standard;
			for (int j = 0; j < eatNumber; j++) {
				if (k >= currentLength) {
					k -= currentLength;
					standard++;
				} else {
					isBreak = true;
					break;
				}
			}

			if (isBreak) {
				break;
			}
		}

		for (int i = 0; i < food_times.length; i++) {
			if (food_times[i] > standard ) {
				if (k == 0) {
					return i + 1;
				}

				k--;
			}
		}

		return -1;
	}
}
