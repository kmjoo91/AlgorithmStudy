/*
 *@(#)BookSort.java 2017.07.20
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.sort;

/**
 *
 *
 * @author kim.minjoo
 */
public class BookSort {
	public void quickSort (int[] value, int start, int end) {
		int pivot = 0;
		if (start < end) {
			pivot = partitionQuickSort(value, start, end);
			quickSort(value, start, pivot -1);
			quickSort(value, pivot+1, end);
		}
	}

	private int partitionQuickSort(int[] value, int start, int end) {
		int pivot = end;
		int right = end;
		int left = start;
		int temp = 0;
		while (left < right) {
			while (value[left] < value[pivot] && left < right) {
				left++;
			}

			while (value[right] >= value[pivot] && left < right) {
				right--;
			}

			if (left < right) {
				temp = value[left];
				value[left] = value[right];
				value[right] = temp;
			}

		}

		temp = value[pivot];
		value[pivot] = value[right];
		value[right] = temp;

		print(value, start, end);
		return right;
	}

	private void print(int[] value, int start, int end) {
		System.out.println(start + " ~ " + end + "까지 정렬");
		for (int num : value) {
			System.out.print(num + ",");
		}
		System.out.println("\n");
	}
}