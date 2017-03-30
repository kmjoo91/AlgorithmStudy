/*
 *@(#)BribeThePrisoners.java 2017.03.30
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.book.p155;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 *
 * @author kim.minjoo
 */
public class BribeThePrisoners {
	int prisonRoomNumber;
	int releaseNumber;
	int[] releasePrisoners;
	private int INT_MAX = 9999999;

	public BribeThePrisoners(int prisonRoomNumber, int releaseNumber, int[] releasePrisoners) {
		this.prisonRoomNumber = prisonRoomNumber;
		this.releaseNumber = releaseNumber;
		this.releasePrisoners = releasePrisoners;
	}

	public int getTotalBribe() {
		return getTotalBribe(prisonRoomNumber, releaseNumber, releasePrisoners);
	}

	public int getTotalBribe(int prisonRoomNumber, int releaseNumber, int[] releasePrisoners) {
		int totalBribe = 0;
		Arrays.sort(releasePrisoners);

		int[] bribes = new int[releaseNumber + 1];
		int[] bribesCost = new int[releaseNumber +1];

		bribes[0] = releasePrisoners[0]-1;
		bribesCost[0] = 1;
		bribes[releaseNumber] = prisonRoomNumber - releasePrisoners[releaseNumber-1];
		bribesCost[releaseNumber] = 1;

		for (int i = 1; i < bribes.length-1; i++) {
			bribes[i] = releasePrisoners[i] - releasePrisoners[i-1] -1;
			bribesCost[i] = 2;
		}

		for (int i = 0; i < releaseNumber; i++) {
			int currentReleaseNumber = getReleaseNumber(bribes, bribesCost);
			System.out.println(currentReleaseNumber);

			totalBribe += prisonRoomNumber - i - 1;

			for (int j = 0; j < bribes.length; j++) {
				if (bribesCost[j] == 0) {
					totalBribe -= bribes[j];
				}
			}

			bribesCost[currentReleaseNumber]--;
			bribesCost[currentReleaseNumber+1]--;
		}

		System.out.println(totalBribe);
		return 0;
	}

	private int getReleaseNumber(int[] bribes, int[] bribesCost) {
		int releaseNumber = 0;
		double releaseCost = bribes[0] / bribesCost[0];
		for (int i = 1; i < bribes.length; i++) {
			if (bribesCost[i] == 0) {
				continue;
			}
			double bribe = bribes[i] / bribesCost[i];
			if (releaseCost < bribe) {
				releaseNumber = i;
			} else if (releaseCost == bribe) {
				if (i == bribes.length) {
					releaseNumber = i;
				}
				if (releaseNumber == 0) {
					continue;
				}
				if (bribes[i+1] / bribesCost[i+1] < bribes[releaseNumber-1] / bribesCost[releaseNumber-1]) {
					releaseNumber = i;
				}
			}
		}

		if (releaseNumber == 0) {
			releaseNumber = 0;
		} else if (releaseNumber == bribes.length-1) {
			releaseNumber = releaseNumber - 1;
		} else if (bribesCost[releaseNumber+1] == 0) {
			releaseNumber = releaseNumber - 1;
		} else if (bribesCost[releaseNumber-1] == 0) {
			releaseNumber = releaseNumber;
		} else if (bribes[releaseNumber-1]/bribesCost[releaseNumber-1] < bribes[releaseNumber+1] / bribesCost[releaseNumber+1]) {
			releaseNumber = releaseNumber;
		} else {
			releaseNumber = releaseNumber - 1;
		}

		return releaseNumber;
	}

}