package com.naver.divideandconquer.factorial;

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
public class FactorialTest {
	Factorial factorial = new Factorial();
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
	@MethodSource("FactorialTestParam")
	public void FactorialTest(Parameters parameters) {
		//when
		int actual = factorial.factorial(parameters.getNum());

		//then
		assertEquals(parameters.getExpected(), actual);

		System.out.println(parameters.getDisplayName());
		System.out.println("숫자 : " + parameters.getNum());
		System.out.println("기대값 : " + parameters.getExpected() + " 실제값 : " + actual);
	}

	public static List<Parameters> FactorialTestParam() {
		Parameters parameters1 = new Parameters("1팩토리얼", 1, 1);
		Parameters parameters2 = new Parameters("1팩토리얼", 2, 2);
		Parameters parameters3 = new Parameters("1팩토리얼", 3, 6);
		Parameters parameters4 = new Parameters("1팩토리얼", 4, 24);
		Parameters parameters5 = new Parameters("1팩토리얼", 5, 120);

		List<Parameters> parametersList = new ArrayList<>();
		parametersList.add(parameters1);
		parametersList.add(parameters2);
		parametersList.add(parameters3);
		parametersList.add(parameters4);
		parametersList.add(parameters5);

		return parametersList;
	}
}
class Parameters {
	private String displayName;
	private int num;
	private int expected;

	public Parameters(String displayName, int num, int expected) {
		this.displayName = displayName;
		this.num = num;
		this.expected = expected;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getNum() {
		return num;
	}

	public int getExpected() {
		return expected;
	}

	@Override
	public String toString() {
		return displayName + "{" +
				"num=" + num +
				", expected=" + expected +
				'}';
	}
}