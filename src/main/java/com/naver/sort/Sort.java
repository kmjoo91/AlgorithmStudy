/*
 *@(#)SortMain.java 2017.07.14
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 *
 * @author kim.minjoo
 */
public class Sort {
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		System.out.println("====selection sort====");
		Sort sort = new Sort();
		int[] arr = {70, 50, 80, 60, 30, 90, 60, 40};

		sort.selectionSort(arr);
		long endTime = System.nanoTime();
		System.out.println("Selection Sort 실행시간 : " + (endTime - startTime));

		startTime = System.nanoTime();
		System.out.println("====quick sort====");
		int[] arr2 = {70, 50, 80, 60, 30, 90, 60, 40};
		sort.quickSort(arr2, 0, arr2.length-1);
		endTime = System.nanoTime();
		System.out.println("Quick Sort 실행시간 : " + (endTime - startTime));

		startTime = System.nanoTime();
		System.out.println("====merge sort====");
		int[] arr3 = new int[arr2.length];
		sort.mergeSort(arr2, arr3, 0, arr2.length - 1);
		endTime = System.nanoTime();
		System.out.println("Merge Sort 실행시간 : " + (endTime - startTime));
		sort.print(arr3, 0);

		startTime = System.nanoTime();
		BookSort test = new BookSort();
		int[] arr4 = {70, 50, 80, 60, 30, 90, 60, 40};
		test.quickSort(arr4, 0, arr4.length - 1);
		endTime = System.nanoTime();
		System.out.println("Book Quick Sort 실행시간 : " + (endTime - startTime));
		System.out.println("출력");
		sort.print(arr4, 0);

		startTime = System.nanoTime();
		int[] arr5 = {70, 50, 80, 60, 30, 90, 60, 40};
		sort.radixSort(arr5);
		endTime = System.nanoTime();
		System.out.println("Radix Sort 실행시간 : " + (endTime - startTime));
		sort.print(arr5, 0);

		startTime = System.nanoTime();
		System.out.println("====quick sort====");
		int[] arr6 = {70, 50, 80, 60, 30, 90, 60, 40};
		test.repeatQuickSort(arr6, 0, arr6.length-1);
		endTime = System.nanoTime();
		System.out.println("Repeat Quick Sort 실행시간 : " + (endTime - startTime));
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

	public void radixSort(int[] value) {

		int maxCipher = 0;
		for (int num : value) {
			int cipher = (int)Math.log10(num) + 1;
			if (maxCipher < cipher) {
				maxCipher = cipher;
			}
		}

		for (int i = 1; i <= maxCipher; i++) {
			List<List<Integer>> bucket = makeBucket();
			for (int num : value) {
				if (i == 1) {
					bucket.get(num%10).add(num);
				} else {
					bucket.get((num / (int)Math.pow(10,(i-1) ))%10).add(num);
				}
			}

			for (int j = 1; j <= value.length; j++) {
				value[j - 1] = getNumber(bucket, j);
			}
		}
	}

	private int getNumber(List<List<Integer>> bucket, int count) {
		for (int i = 0; i < 10; i++) {
			List<Integer> list = bucket.get(i);

			if (list.isEmpty() || list.size() < count) {
				count = count - list.size();
				continue;
			}

			return list.get(count - 1);
		}

		throw new IllegalStateException("여기오면안댐");
	}

	private List<List<Integer>> makeBucket() {
		List<List<Integer>> bucket = new ArrayList<List<Integer>>() {
			{
				add(new ArrayList<Integer>()); //0
				add(new ArrayList<Integer>()); //1
				add(new ArrayList<Integer>()); //2
				add(new ArrayList<Integer>()); //3
				add(new ArrayList<Integer>()); //4
				add(new ArrayList<Integer>()); //5
				add(new ArrayList<Integer>()); //6
				add(new ArrayList<Integer>()); //7
				add(new ArrayList<Integer>()); //8
				add(new ArrayList<Integer>()); //9
			}
		};

		return bucket;
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