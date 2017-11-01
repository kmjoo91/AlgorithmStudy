package com.naver.greedy.knapsack;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Created by AD on 2017-11-01.
 */
public class KnapsackTest {
	@Test
	public void KanpsackTest() {
		//given
		Item item1 = new Item("A", 500, 1, 1);
		Item item2 = new Item("B", 300, 2, 1);
		Item item3 = new Item("C", 900, 3, 1);
		Item item4 = new Item("D", 800, 4, 1);
		List<Item> itemList = new ArrayList<>();
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		itemList.add(item4);

		//when
		Knapsack knapsack = new Knapsack(itemList, 5);
		knapsack.printFractionalKnapsackProblem();
	}

	@Test
	public void Knapsack_효율엄청좋은데_배낭무게넘는아이템_test() {
		//given
		Item item1 = new Item("A", 500, 1, 1);
		Item item2 = new Item("B", 300, 2, 1);
		Item item3 = new Item("C", 900, 3, 1);
		Item item4 = new Item("D", 800, 4, 1);
		Item item5 = new Item("E", 1000000, 10, 1);
		List<Item> itemList = new ArrayList<>();
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		itemList.add(item4);
		itemList.add(item5);

		//when
		Knapsack knapsack = new Knapsack(itemList, 5);
		knapsack.printFractionalKnapsackProblem();
	}
}