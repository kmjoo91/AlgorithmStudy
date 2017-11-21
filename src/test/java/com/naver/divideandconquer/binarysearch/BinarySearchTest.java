package com.naver.divideandconquer.binarysearch;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.naver.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BinarySearchTest {
	BinarySearch binarySearch = new BinarySearch();
	@BeforeEach
	public void init() {
		System.out.println("=====테스트 시작====");
	}

	@AfterEach
	public void done() {
		System.out.println("=====테스트 종료====");
	}

	@DisplayName("Junit5를 활용한 BinarySearch테스트")
	@ParameterizedTest
	@MethodSource("BinarySearchTestParam")
	public void BinarySearchTest(Parameters<List<Integer>> parameters) {

		//when
		int actual = binarySearch.searchRecursive(parameters.getParam(), parameters.getKey());

		//then
		assertEquals(parameters.getExpected(), actual);

		System.out.println(parameters.getDisplayName());
		System.out.println("리스트 : " + parameters.getParam());
		System.out.println("기대값 : " + parameters.getExpected() + " 실제인덱스 : " + actual);
	}

	public static List<Parameters> BinarySearchTestParam() {
		List<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(20);
		list.add(50);
		list.add(60);
		list.add(70);
		list.add(80);

		Parameters<List<Integer>> parameters1 = new Parameters<>("10찾기", list, 10, 0);
		Parameters<List<Integer>> parameters2 = new Parameters<>("20찾기", list, 20, 1);
		Parameters<List<Integer>> parameters3 = new Parameters<>("50찾기", list, 50, 2);
		Parameters<List<Integer>> parameters4 = new Parameters<>("60찾기", list, 60, 3);
		Parameters<List<Integer>> parameters5 = new Parameters<>("70찾기", list, 70, 4);
		Parameters<List<Integer>> parameters6 = new Parameters<>("80찾기", list, 80, 5);

		List<Parameters> parametersList = new ArrayList<>();
		parametersList.add(parameters1);
		parametersList.add(parameters2);
		parametersList.add(parameters3);
		parametersList.add(parameters4);
		parametersList.add(parameters5);
		parametersList.add(parameters6);

		return parametersList;
	}
}

class Parameters<T> {
	private String displayName;
	private T param;
	private int key;
	private int expected;

	public Parameters(String displayName, T param, int key, int expected) {
		this.displayName = displayName;
		this.param = param;
		this.key = key;
		this.expected = expected;
	}

	public String getDisplayName() {
		return displayName;
	}

	public T getParam() {
		return param;
	}

	public int getKey() {
		return key;
	}

	public int getExpected() {
		return expected;
	}

	@Override
	public String toString() {
		return displayName + "{" +
				"param=" + param +
				", key=" + key +
				", expected=" + expected +
				'}';
	}
}