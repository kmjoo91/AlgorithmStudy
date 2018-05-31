package com.naver.dynamicprogramming.baekjoon.problem10538.ahocorasick;

import java.util.Scanner;

public class BigPicture {
	public static int calculate(String[] picture, String[] bigPicture) {
		AhoCorasick ahoCorasick = AhoCorasick.builder()
				.addPattern(picture[0])
				.build();

		int width = picture[0].length();
		int height = picture.length;
		int bigWidth = bigPicture[0].length();
		int bigHeight = bigPicture.length;
		boolean[][] patternMatchingColumn = new boolean[bigWidth][height-1];

		int result = 0;

		for (int i = 0; i < bigHeight; i++) {
			ahoCorasick.startRoot();
			//한 행을 돌면서 새로 찾아야되는애 찾기. 그리고 지금까지 맞는애 찾기.
			for (int j = 0; j < bigWidth; j++) {
				for (int k = 0; k < height - 1; k++) {
					if (patternMatchingColumn[j][k]) {
						String substring = bigPicture[i].substring(j - width + 1, j + 1);
						if (substring.equals(picture[k + 1])) {
							//마지막
							if (k + 1 == height - 1) {
								//다음껀 찾을필요없이 걍 숫자올려주고
								result++;
								//이번껀 안찾고
								patternMatchingColumn[j][k] = false;
							}
							//안마지막
							else {
								//다음껀 찾고
								patternMatchingColumn[j][k+1] = true;
								//이번껀 안찾고
								patternMatchingColumn[j][k] = false;
							}
						}
					}
				}

				boolean isMatching = ahoCorasick.isMatching(bigPicture[i].charAt(j));

				if (isMatching) {
					patternMatchingColumn[j][0] = true;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] input = scanner.nextLine().split(" ");

		int height = Integer.parseInt(input[1]);
		int bigHeight = Integer.parseInt(input[3]);;

		String[] picture= new String[height];
		String[] bigPicture = new String[bigHeight];

		for (int i = 0; i < height; i++) {
			picture[i] = scanner.next();
		}

		for (int i = 0; i < bigHeight; i++) {
			bigPicture[i] = scanner.next();
		}

		int result = calculate(picture, bigPicture);
		System.out.println(result);
	}
}

