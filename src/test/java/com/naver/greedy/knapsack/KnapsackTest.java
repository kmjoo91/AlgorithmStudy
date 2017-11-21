package com.naver.greedy.knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.naver.MockitoExtension;

/**
 * Created by AD on 2017-11-01.
 */
@ExtendWith(MockitoExtension.class)
public class KnapsackTest {
	@BeforeEach
	public void init() {
		System.out.println("=====테스트 시작====");
	}

	@AfterEach
	public void done() {
		System.out.println("=====테스트 종료====");
	}


	@DisplayName("Junit5를 활용한 Knapsack테스트")
	@ParameterizedTest
	@MethodSource("knapsackParam")
	public void KanpsackTest(Parameters<List> parameters) {
		System.out.println(parameters.getDisplayName());
		//when
		Knapsack knapsack = new Knapsack(parameters.getParam(), parameters.getWeight());

		//then
		knapsack.printFractionalKnapsackProblem();
	}

	public static Stream<Parameters> knapsackParam() {
		Item item1 = new Item("A", 500, 1, 1);
		Item item2 = new Item("B", 300, 2, 1);
		Item item3 = new Item("C", 900, 3, 1);
		Item item4 = new Item("D", 800, 4, 1);
		List<Item> itemList1 = new ArrayList<>();
		itemList1.add(item1);
		itemList1.add(item2);
		itemList1.add(item3);
		itemList1.add(item4);
		Parameters<List> parameter1 = new Parameters<List>("KnapsackSimpleExample", itemList1, 5);

		Item item5 = new Item("A", 500, 1, 1);
		Item item6 = new Item("B", 300, 2, 1);
		Item item7 = new Item("C", 900, 3, 1);
		Item item8 = new Item("D", 800, 4, 1);
		Item item9 = new Item("E", 1000000, 10, 1);
		List<Item> itemList2 = new ArrayList<>();
		itemList2.add(item5);
		itemList2.add(item6);
		itemList2.add(item7);
		itemList2.add(item8);
		itemList2.add(item9);
		Parameters<List> parameter2 = new Parameters<List>("효율엄청좋은데_배낭무게넘는아이템", itemList2,5);

		Item item = new Item("A", 500, 2, 5);
		List<Item> itemList3 = new ArrayList<>();
		itemList3.add(item);
		Parameters<List> parameter3 = new Parameters<List>("부분배낭문제", itemList3, 9);

		List<Parameters> parmetersList = new ArrayList<>();
		parmetersList.add(parameter1);
		parmetersList.add(parameter2);
		parmetersList.add(parameter3);

		return parmetersList.stream();
	}
}
class Parameters<T> {
	private String displayName;
	private T param;
	private int weight;

	public Parameters(String displayName, T param, int weight) {
		this.displayName = displayName;
		this.param = param;
		this.weight = weight;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public T getParam() {
		return param;
	}

	public void setParam(T param) {
		this.param = param;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return displayName
				+ " {"
				+ "param=" + param
				+ '}';
	}
}