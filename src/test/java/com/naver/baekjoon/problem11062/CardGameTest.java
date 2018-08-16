package com.naver.baekjoon.problem11062;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.naver.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CardGameTest {
	@Test
	void test() {
		//given
		int[] cards = {1, 2, 5, 2};
		CardGame cardGame = new CardGame(cards);
		int expected = 6;

		//when
		int actual = cardGame.calculate(true);

		//then
		assertEquals(expected, actual);
	}
}