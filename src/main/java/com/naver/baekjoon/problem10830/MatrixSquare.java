package com.naver.baekjoon.problem10830;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/10830
 */
public class MatrixSquare {
	public static int[][] calculate(int length, long number, int[][] matrix) {
		int[][] currentMatrix = new int[length][length];
		int[][] resultMatrix = new int[length][length];

		if (number == 1) {
			return matrix;
		}

		if (number % 2 == 0) {
			currentMatrix = multiplyMatrix(matrix, matrix);
			resultMatrix = calculate(length, number / 2, currentMatrix);
		} else {
			currentMatrix = multiplyMatrix(matrix, matrix);
			resultMatrix = calculate(length, number / 2, currentMatrix);
			resultMatrix = multiplyMatrix(resultMatrix, matrix);
		}

		return resultMatrix;
	}

	private static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2) {
		int[][] multiplyMatrix = new int[matrix1.length][matrix2[0].length];
		for (int row = 0; row < matrix1.length; row++) {
			for (int column = 0; column < matrix2[0].length; column++) {
				for (int j = 0; j < matrix2[0].length; j++) {
					multiplyMatrix[row][column] += matrix1[row][j]%1000 * matrix2[j][column]%1000;
				}
			}
		}
		return multiplyMatrix;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String firstLine = scanner.nextLine();

		String[] firstLines = firstLine.split(" ");

		int length = Integer.parseInt(firstLines[0]);
		long number = Long.parseLong(firstLines[1]);

		String[] strMatrix = new String[length];
		for (int i = 0; i < length; i++) {
			strMatrix[i] = scanner.nextLine();
		}

		int[][] matrix = new int[length][length];

		for (int i = 0; i < length; i++) {
			String[] row = strMatrix[i].split(" ");
			for (int column = 0; column < length; column++) {
				matrix[i][column] = Integer.parseInt(row[column]);
			}
		}


		matrix = calculate(length, number, matrix);

		for (int row = 0; row < length; row++) {
			for (int column = 0; column < length; column++) {
				System.out.print(matrix[row][column]%1000);
				if (column < length - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
