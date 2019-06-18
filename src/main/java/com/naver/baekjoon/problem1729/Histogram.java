package com.naver.baekjoon.problem1729;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1725
 */
public class Histogram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();

        int[] heightArr = new int[size];
        for (int i = 0; i < size; i++) {
            heightArr[i] = scanner.nextInt();
        }

        int[] histogram = setHistogram(heightArr);

        System.out.println(getLargestArea(histogram, heightArr));
    }

    private static long getLargestArea(int[] histogram, int[] heightArr) {
        //histogram 노드랑 왼쪽 오른쪽 범위
        return getLargestArea(histogram, heightArr, 0, heightArr.length - 1);
    }

    private static long getLargestArea(int[] histogram, int[] heightArr, int from, int to) {
        if (from < 0 || to > heightArr.length || from >= to) {
            return 0;
        }
        //자기 자신이랑 왼쪽 오른쪽 넓이 구해서 비교
        int lowestNode = searchLowestNode(histogram, heightArr, 1, 0, heightArr.length - 1, from, to);
        int lowestIndex = histogram[lowestNode];

        long center = (to + 1 - from) * heightArr[lowestIndex];
        long leftArea = getLargestArea(histogram, heightArr, from, lowestIndex - 1);
        long rightArea = getLargestArea(histogram, heightArr, lowestIndex + 1, to);

        return Math.max(center, Math.max(leftArea, rightArea));
    }

    private static int searchLowestNode(int[] histogram, int[] heightArr, int node, int left, int right, int from, int to) {
        if (right < from || left > to) {
            return -1;
        }

        if (from <= left && right <= to) {
            return node;
        }

        int mid = (left + right) / 2;
        int leftChildNode = node * 2;
        int rightChildNode = leftChildNode + 1;
        int leftLowestNode = searchLowestNode(histogram, heightArr, leftChildNode, left, mid, from, to);
        int rightLowestNode = searchLowestNode(histogram, heightArr, rightChildNode, mid + 1, right, from, to);

        if (leftLowestNode == -1) {
            return rightLowestNode;
        } else if (rightLowestNode == -1) {
            return leftLowestNode;
        } else if (heightArr[histogram[leftLowestNode]] > heightArr[histogram[rightLowestNode]]) {
            return rightLowestNode;
        } else {
            return leftLowestNode;
        }
    }

    private static int[] setHistogram(int[] heightArr) {
        int treeHeight = (int) Math.ceil(Math.log(heightArr.length) / Math.log(2));

        int histogramSize = (int) Math.pow(2, treeHeight + 1);

        int[] histogram = new int[histogramSize];
        setHistogram(heightArr, histogram, 1, 0, heightArr.length - 1);

        return histogram;
    }

    private static void setHistogram(int[] heightArr, int[] histogram, int node, int from, int to) {
        if (from == to) {
            histogram[node] = from;
            return;
        }

        int mid = (from + to) / 2;
        int leftChildNode = node * 2;
        int rightChildNode = leftChildNode + 1;

        setHistogram(heightArr, histogram, leftChildNode, from, mid);
        setHistogram(heightArr, histogram, rightChildNode, mid + 1, to);

        histogram[node] = heightArr[histogram[leftChildNode]] > heightArr[histogram[rightChildNode]] ? histogram[rightChildNode] : histogram[leftChildNode];
    }
}
