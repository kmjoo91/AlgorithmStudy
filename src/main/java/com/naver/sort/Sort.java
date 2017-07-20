/*
 *@(#)SortMain.java 2017.07.14
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
public class Sort {
	public static void main(String[] args) {
		System.out.println("====selection sort====");
		Sort sort = new Sort();
		int[] arr = {70, 50, 80, 60, 30, 90, 60, 40};

		sort.selectionSort(arr);

		System.out.println("====quick sort====");
		int[] arr2 = {70, 50, 80, 60, 30, 90, 60, 40};
		sort.quickSort(arr2, 0, arr2.length-1);

		System.out.println("====merge sort====");
		int[] arr3 = new int[arr2.length];
		sort.mergeSort(arr2, arr3, 0, arr2.length - 1);
		sort.print(arr3, 0);


		BookSort test = new BookSort();
		int[] arr4 = {70, 50, 80, 60, 30, 90, 60, 40, 1000};
		test.quickSort(arr4, 0, arr4.length - 1);
		System.out.println("출력");
		sort.print(arr4, 0);
	}

	public void selectionSort(int[] value) {
		for (int i=0; i < value.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < value.length; j++) {
				if (value[j] < value[minIndex]) {
					minIndex = j;
				}
			}
			int temp = value[i];
			value[i] = value[minIndex];
			value[minIndex] = temp;

			print(value, i);
		}
	}

	public void quickSort(int[] value, final int start, final int end) {
		if (start == end) {
			return;
		}
		int pivot = start;
		int left = start+1;
		int right = end;
		while (left < right) {
			for (; left < right; left++) {
				if (value[left] >= value[pivot]) {
					break;
				}
			}

			for (;right > left ; right--) {
				if (value[right] < value[pivot]) {
					break;
				}
			}

			if (left < right) {
				int temp = value[left];
				value[left] = value[right];
				value[right]= temp;
			}
		}

		if (value[pivot] > value[left]) {
			int temp = value[left];
			value[left] = value[pivot];
			value[pivot]= temp;
		}

		print(value, start, end);
		quickSort(value, start, left - 1);
		quickSort(value, right, end);
	}

	public void mergeSort(int[] value, int[] sortArr, int start, int end) {
		if (start < end) {
			int middle = (start + end) / 2;
			mergeSort(value, sortArr, start, middle);
			mergeSort(value, sortArr, middle+1, end);
			merge(value, sortArr, start, middle, end);
			print(sortArr, start, end);
		}
	}

	private void merge(int[] value, int[] sortArr, int start, int middle, int end) {
		int left = start;
		int right = middle + 1;
		int index = start;

		while (left <= middle && right <= end) {
			if (value[left] <= value[right]) {
				sortArr[index++] = value[left];
				left++;
			} else {
				sortArr[index++] = value[right];
				right++;
			}
		}

		for (; left <= middle; left++) {
			sortArr[index++] = value[left];
		}

		for (; right <= end; right++) {
			sortArr[index++] = value[right];
		}
	}

	private void print(int[] value, int i) {
		System.out.print((i + 1) + "번째 Step : ");
		for (int num : value) {
			System.out.print(num + ",");
		}
		System.out.println("\n");
	}

	private void print(int[] value, int start, int end) {
		System.out.println(start + " ~ " + end + "까지 정렬");
		for (int num : value) {
			System.out.print(num + ",");
		}
		System.out.println("\n");
	}
}