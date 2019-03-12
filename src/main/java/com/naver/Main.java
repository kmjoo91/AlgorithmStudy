package com.naver;
/*
 *@(#)Main.java 2017.01.11
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import com.google.gson.Gson;
import com.naver.kakao.Command;
import com.naver.kakao.ElevatorStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author kim.minjoo
 */
public class Main {
	public static final Gson GSON = new Gson();
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	private static CloseableHttpClient httpClient = HttpClients.createDefault();;
	public static void main(String[] args) throws Exception {
		ElevatorStatus elevatorStatus = onStart();
		System.out.println(elevatorStatus);

		while (true) {
			elevatorStatus = onCall(elevatorStatus.getToken());
			System.out.println(elevatorStatus);

			if (elevatorStatus.isEnd()) {
				break;
			}

			//여기에 진짜 로직 들어가야함.
			List<Command> commands = calculateCommands(elevatorStatus);

			onAction(elevatorStatus, commands);
		}
	}

	//TODO 생각좀 잘해보장.
	private static List<Command> calculateCommands(ElevatorStatus elevatorStatus) throws InterruptedException {
		Thread.sleep(1000);
		return null;
	}

	private static void onAction(ElevatorStatus elevatorStatus, List<Command> commands) throws IOException {
		HttpPost request = new HttpPost("http://localhost:8000/action");
		request.addHeader("X-Auth-Token", elevatorStatus.getToken());
		request.addHeader("Content-Type", "application/json");

		Map<String, List<Command>> params = new HashMap<>();
		params.put("commands", commands);

		String string = GSON.toJson(params);
		System.out.println(string);
		request.setEntity(new StringEntity(string));

		HttpResponse httpResponse = httpClient.execute(request);

		System.out.println(parseResponse(httpResponse, ElevatorStatus.class).toString());
	}

	private static ElevatorStatus onCall(String token) throws IOException {
		HttpGet request = new HttpGet("http://localhost:8000/oncalls");

		request.addHeader("X-Auth-Token", token);

		HttpResponse httpResponse = httpClient.execute(request);
		return parseResponse(httpResponse, ElevatorStatus.class);
	}

	private static ElevatorStatus onStart() throws IOException {
		HttpPost request = new HttpPost("http://localhost:8000/start/kmj/1/1");
		HttpResponse httpResponse = httpClient.execute(request);

		ElevatorStatus elevatorStatus = parseResponse(httpResponse, ElevatorStatus.class);

		return elevatorStatus;
	}

	private static<T> T parseResponse(HttpResponse httpResponse, Class type) throws IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				httpResponse.getEntity().getContent()));

		String response = "";
		String str = "";
		while ((str = rd.readLine()) != null) {
			response += str;
		}

		return (T) GSON.fromJson(response, type);
	}
}