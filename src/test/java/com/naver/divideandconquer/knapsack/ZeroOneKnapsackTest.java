package com.naver.divideandconquer.knapsack;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.naver.divideandconquer.knapsack.model.Item;

public class ZeroOneKnapsackTest {
	@Test
	public void 테스트() {
		List<Item> itemList = new ArrayList<Item>();
		//given
		itemList.add(new Item(1, 600));
		itemList.add(new Item(2, 1000));
		itemList.add(new Item(3, 1200));
		int expected = 2200;

		//when
		int actual = ZeroOneKnapsack.calculate(5, itemList, itemList.size());

		assertEquals(expected, actual);
	}

}