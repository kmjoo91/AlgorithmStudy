package com.naver.baekjoon.problem9576;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/9576
 */
public class BookSharing {
	private static final int NOT_MATCHING = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int testCase = scanner.nextInt();

		for (int i = 0; i < testCase; i++) {
			int bookNumber = scanner.nextInt();
			int studentNumber = scanner.nextInt();

			int[] bookMatching = new int[bookNumber + 1];
			int[] bookLevel = new int[bookNumber + 1];
			int[] studentMatching = new int[studentNumber + 1];
			int[] studentLevel = new int[studentNumber + 1];
			boolean[][] studentSelectBook = new boolean[studentNumber + 1][bookNumber + 1];

			//학생들이 원하는 값 입력받아서 초기 간선을 그려준다.
			for (int student = 1; student <= studentNumber; student++) {
				int from = scanner.nextInt();
				int to = scanner.nextInt();

				for (int select = from; select <= to; select++) {
					studentSelectBook[student][select] = true;
					bookLevel[select] = 1;

					if (studentMatching[student] == NOT_MATCHING && bookMatching[select] == NOT_MATCHING) {
						studentMatching[student] = select;
						bookMatching[select] = student;
					}
				}
			}

			while(true) {
				//level을 셋팅한다.
				setLevel(bookMatching, bookLevel, studentMatching, studentLevel, studentSelectBook);

				//증가 경로를 찾는다.
				for (int student = 1; student < studentNumber + 1; student++) {

				}

				//증가 경로가 없으면 종료.

				//증가 경로가 있으면 증가경로 반대로해서 간선 다시그려준다.
			}
			//학생쪽 or 책쪽에서 연결된 간선갯수를 찾아서 출력.
		}
	}

	private static void setLevel(int[] bookMatching, int[] bookLevel, int[] studentMatching, int[] studentLevel, boolean[][] studentSelectBook) {
		//여기엔 학생만 집어넣는다.
		Queue<Integer> levelSearchQueue = new LinkedList<>();

		//일단 간선없는애들 찾아낸다.
		for (int student = 1; student < studentMatching.length; student++) {
			if (studentMatching[student] == NOT_MATCHING) {
				levelSearchQueue.offer(student);
				studentLevel[student] = 0;
			} else {
				studentLevel[student] = Integer.MAX_VALUE;
			}
		}

		while(!levelSearchQueue.isEmpty()) {
			int student = levelSearchQueue.poll();

			for (int book = 1; book < studentSelectBook[student].length; book++) {
				//학생이 안골랐으면 갈 수 없으므로 넘어간다.
				if (studentSelectBook[student][book] == false) {
					continue;
				}

				//현재가 매칭이 아닐경우.
				if (studentMatching[student] == NOT_MATCHING) {
					//book은 무조건 매칭이어야 다음으로 넘어감. 매칭이 아닐경우 둘이 연결만 해주고 끝!
					if (bookMatching[book] == NOT_MATCHING) {
						studentMatching[student] = book;
						bookMatching[book] = student;
						studentLevel[student] = Integer.MAX_VALUE;
						break;
					} else {
						int nextStudent = bookMatching[book];
						//
						if (nextStudent != NOT_MATCHING && studentLevel[nextStudent] > studentLevel[student] + 1) {
							studentLevel[nextStudent] = studentLevel[student] + 1;
							levelSearchQueue.offer(nextStudent);
						}
					}
				}
				//현재가 매칭일 경우
				else {
					//선택한것중 매칭한애가 아닌애로 가야한다.
					for (int nextBook = 1; nextBook < studentSelectBook[student].length; nextBook++) {
						if (studentSelectBook[student][book] == false || nextBook == studentMatching[student]) {
							continue;
						}

						//선택됐으면서 나한테 온 애가 아닌애.
						int nextStudent = bookMatching[book];
						//
						if (nextStudent != NOT_MATCHING && studentLevel[nextStudent] > studentLevel[student] + 1) {
							studentLevel[nextStudent] = studentLevel[student] + 1;
							levelSearchQueue.offer(nextStudent);
							break;
						}
					}
				}
			}
		}
	}
}
