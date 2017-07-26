/*
 *@(#)BookSort.java 2017.07.20
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.sort;

import java.util.LinkedList;
import java.util.Queue;

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

	public void repeatQuickSort(int[] value, int start, int end) {
		//stack으로하나 queue로하나 동작은 정상적으로되나 stack으로 구현할 경우 재귀와 똑같은 순서로 동작하도록 만들 수 있다.
		Queue<Integer> startQueue = new LinkedList();
		Queue<Integer> endQueue = new LinkedList();
		startQueue.add(start);
		endQueue.add(end);

		while(startQueue.isEmpty() == false && endQueue.isEmpty() == false) {
			start = startQueue.poll();
			end = endQueue.poll();
			int pivot = partitionQuickSort(value, start, end);
			if (start < pivot-1) {
				startQueue.add(start);
				endQueue.add(pivot - 1);
			}
			if (pivot+1 < end) {
				startQueue.add(pivot + 1);
				endQueue.add(end);
			}
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