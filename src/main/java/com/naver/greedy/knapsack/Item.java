/*
 *@(#)Item.java 2017.11.01
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.greedy.knapsack;

/**
 *
 *
 * @author kim.minjoo
 */
public class Item {
	private String name;
	private double price;
	private double weight;
	private int number;

	public Item(String name, double price, double weight, int number) {
		if (weight <= 0) {
			throw new IllegalArgumentException("The weight can never be less than zero!!!!");
		}
		if (price <= 0) {
			throw new IllegalArgumentException("The price can never be less than zero!!!!");
		}
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getUnitValue() {
		return price / weight;
	}

	@Override
	public String toString() {
		return "Item {" +
				"name='" + name + '\'' +
				", price=" + price +
				", weight=" + weight +
				", number=" + number +
				'}';
	}
}