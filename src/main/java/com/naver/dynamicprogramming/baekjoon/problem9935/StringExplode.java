package com.naver.dynamicprogramming.baekjoon.problem9935;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/9935
 */
public class StringExplode {
	public static String explode(String target, String pattern) {
		if (target.length() < pattern.length()) {
			return target;
		}

		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < target.length(); i++) {
			char current = target.charAt(i);
			stringBuffer.append(current);
			//스택에 들어있는게 pattern 보다 적을경우 확인해볼 필요가없다.
			if (stringBuffer.length() < pattern.length()) {
				continue;
			}

			if (current != pattern.charAt(pattern.length() - 1)) {
				continue;
			}

			//스택에 들어있는게 pattern 보다 크면서 들어가는 문자가 pattern의 마지막이랑 같을 경우 확인해봐야한다.
			int start = stringBuffer.length() - pattern.length();
			int end = stringBuffer.length();
			if (stringBuffer.substring(start, end).equals(pattern)) {
				stringBuffer.delete(start, end);
			}
		}

		if (stringBuffer.length() == 0) {
			return "FRULA";
		}

		return stringBuffer.toString();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String target = scanner.next();
		String pattern = scanner.next();

		String result = explode(target, pattern);

		System.out.println(result);
	}
}
