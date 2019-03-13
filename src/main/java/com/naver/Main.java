package com.naver;
/*
 *@(#)Main.java 2017.01.11
 *
 * Copyright 2017 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import com.naver.kakao.Elevator.Command;
import com.naver.kakao.Elevator.Elevator;
import com.naver.kakao.Elevator.ElevatorStatus;
import com.naver.kakao.Elevator.Passenger;

/**
 *
 *
 * @author kim.minjoo
 */
public class Main {
	public static final Gson GSON = new Gson();
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	public static final int ELEVATOR_MAX_PEOPLE = 8;
	public static final int ELEVATOR_NUMBER = 4;
	public static final String URL = "http://localhost:8000";
	public static final int PROBLEM_NUMBER = 2;

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

			//승객할당
			List<List<Passenger>> assignCalls = assignCalls(elevatorStatus);
			//엘리베이터 이동명령 계산
			List<Command> commands = calculateCommands(elevatorStatus, assignCalls);

			onAction(elevatorStatus, commands);

		}
	}

	private static List<List<Passenger>> assignCalls(ElevatorStatus elevatorStatus) {
		List<List<Passenger>> assignCalls = new ArrayList<>();
		for (int i = 0; i < ELEVATOR_NUMBER; i++) {
			assignCalls.add(new ArrayList<>());
		}

		List<Elevator> elevators = elevatorStatus.getElevators();
		List<Passenger> calls = elevatorStatus.getCalls();

		for (Passenger call : calls) {
			//가중치 계산.
			int[] weights = new int[elevators.size()];
			for (int i = 0; i < weights.length; i++) {
				weights[i] = Integer.MAX_VALUE;
			}

			for (int i = 0; i < elevators.size(); i++) {
				Elevator elevator = elevators.get(i);
				// 엘리베이터에 승객이 타있는지, 혹은 타있지 않은지.
				if (elevator.getPassengers().size() > 0) {
					for (Passenger passenger : elevator.getPassengers()) {
						//내릴애가 있으면 어차피 걔 내릴때 태우면되니까 추가비용이 0이든다.
						if (passenger.getEnd() == call.getStart()) {
							weights[i] = 0;
							break;
						}
					}

					//어차피 순서대로 처리하니까 마지막애랑 제일 가까운애로 넣자.... ㅠㅠ
					int lastPassengerEnd = elevator.getPassengers().get(elevator.getPassengers().size() - 1).getEnd();
					weights[i] = weights[i] > Math.abs(call.getStart() - lastPassengerEnd) ?  Math.abs(call.getStart() - lastPassengerEnd) : weights[i];
				} else {
					weights[i] = Math.abs(elevator.getFloor() - call.getStart());
				}
			}

			int min = Integer.MAX_VALUE;
			int minIndex = 0;
			for (int i = 0; i < ELEVATOR_NUMBER; i++) {
				int weight = weights[i];
				if (min > weight) {
					min = weight;
					minIndex = i;
				}
			}

			assignCalls.get(minIndex).add(call);
		}

		return assignCalls;
	}

	//TODO 생각좀 잘해보장.
	private static List<Command> calculateCommands(ElevatorStatus elevatorStatus, List<List<Passenger>> assignCalls) {
		List<Command> commands = new ArrayList<>();
		for (int i = 0; i < elevatorStatus.getElevators().size(); i++) {
			Elevator elevator = elevatorStatus.getElevators().get(i);
			Command command = new Command(elevator);
			commands.add(command);
			List<Passenger> passengers = elevator.getPassengers();

			//승객이 없을때
			if (passengers.isEmpty()) {
				//기존 콜들에서 가장 먼저들어온애 태우러간다.

				//기존 콜들도 없으면 걍 멈춰라~
				if (assignCalls.get(i).isEmpty()) {
					switch (elevator.getStatus()) {
						case "STOPPED":
							command.setCommand("STOP");
							break;
						case "UPWARD":
							command.setCommand("STOP");
							break;
						case "DOWNWARD":
							command.setCommand("STOP");
							break;
						case "OPENED":
							command.setCommand("CLOSE");
							break;
					}
					continue;
				}

				Passenger call = assignCalls.get(i).get(0);

				int start = call.getStart();
				int floor = elevator.getFloor();

				if (start == floor) {
					//같은층이면 태워야지!
					switch (elevator.getStatus()) {
						case "STOPPED" :
							command.setCommand("OPEN");
							break;
						case "UPWARD" :
							command.setCommand("STOP");
							break;
						case "DOWNWARD" :
							command.setCommand("STOP");
							break;
						case "OPENED" :
							command.setCommand("ENTER");
							command.getCall_ids().add(call.getId());
							assignCalls.get(i).remove(call);
							for (Passenger assignCall : assignCalls.get(i)) {
								if (assignCall.getStart() == floor) {
									command.getCall_ids().add(assignCall.getId());

									if (command.getCall_ids().size() == ELEVATOR_MAX_PEOPLE) {
										break;
									}
								}
							}
							break;
					}
				} else if (start > floor) {
					switch (elevator.getStatus()) {
						case "STOPPED" :
							command.setCommand("UP");
							break;
						case "UPWARD" :
							command.setCommand("UP");
							break;
						case "DOWNWARD" :
							command.setCommand("STOP");
							break;
						case "OPENED" :
							command.setCommand("CLOSE");

							for (Passenger assignCall : assignCalls.get(i)) {
								if (elevator.getPassengers().size() == ELEVATOR_MAX_PEOPLE) {
									break;
								}

								if (assignCall.getStart() == floor) {
									command.setCommand("ENTER");
									command.getCall_ids().add(assignCall.getId());
								}
							}
							break;
					}
				} else {
					switch (elevator.getStatus()) {
						case "STOPPED" :
							command.setCommand("DOWN");
							break;
						case "UPWARD" :
							command.setCommand("STOP");
							break;
						case "DOWNWARD" :
							command.setCommand("DOWN");
							break;
						case "OPENED" :
							command.setCommand("CLOSE");

							for (Passenger assignCall : assignCalls.get(i)) {
								if (elevator.getPassengers().size() == ELEVATOR_MAX_PEOPLE) {
									break;
								}

								if (assignCall.getStart() == floor) {
									command.setCommand("ENTER");
									command.getCall_ids().add(assignCall.getId());
								}
							}
							break;
					}
				}
			}
			//승객이 있을때
			else {
				//일단 있는놈부터 내려준다.
				int end = passengers.get(0).getEnd();
				int floor = elevator.getFloor();

				if (end == floor) {
					switch (elevator.getStatus()) {
						case "STOPPED" :
							command.setCommand("OPEN");
							break;
						case "UPWARD" :
							command.setCommand("STOP");
							break;
						case "DOWNWARD" :
							command.setCommand("STOP");
							break;
						case "OPENED" :
							//내려주고
							command.setCommand("EXIT");

							//내릴애들 다내려!
							for (Passenger passenger : passengers) {
								if (passenger.getEnd() == floor) {
									command.getCall_ids().add(passenger.getId());
								}
							}
							break;
					}
				} else if (end > floor) {
					switch (elevator.getStatus()) {
						case "STOPPED" :
							command.setCommand("UP");
							break;
						case "UPWARD" :
							command.setCommand("UP");
							break;
						case "DOWNWARD" :
							command.setCommand("STOP");
							break;
						case "OPENED" :
							//여긴 내릴애가 있으면 내리고 없으면 문닫고 출발~
							boolean isExit = false;
							for (Passenger passenger : passengers) {
								if (passenger.getEnd() == floor) {
									command.setCommand("EXIT");
									command.getCall_ids().add(passenger.getId());
									isExit = true;
								}
							}

							if (isExit) {
								break;
							}

							boolean isEnter = false;
							//내릴애가없으면 태울수있으면 태우자
							for (Passenger assignCall : assignCalls.get(i)) {
								if (elevator.getPassengers().size() == ELEVATOR_MAX_PEOPLE) {
									break;
								}

								if (assignCall.getStart() == floor) {
									command.setCommand("ENTER");
									command.getCall_ids().add(assignCall.getId());
									isEnter = true;
								}
							}

							if (isEnter) {
								break;
							}

							command.setCommand("CLOSE");
							break;
					}
				} else {
					switch (elevator.getStatus()) {
						case "STOPPED" :
							command.setCommand("DOWN");
							break;
						case "UPWARD" :
							command.setCommand("STOP");
							break;
						case "DOWNWARD" :
							command.setCommand("DOWN");
							break;
						case "OPENED" :
							//여긴 내릴애가 있으면 내리고 없으면 문닫고 출발~
							boolean isExit = false;
							for (Passenger passenger : passengers) {
								if (passenger.getEnd() == floor) {
									command.setCommand("EXIT");
									command.getCall_ids().add(passenger.getId());
									isExit = true;
								}
							}

							if (isExit) {
								break;
							}

							boolean isEnter = false;
							//내릴애가없으면 태울수있으면 태우자
							for (Passenger assignCall : assignCalls.get(i)) {
								if (elevator.getPassengers().size() == ELEVATOR_MAX_PEOPLE) {
									break;
								}

								if (assignCall.getStart() == floor) {
									command.setCommand("ENTER");
									command.getCall_ids().add(assignCall.getId());
									isEnter = true;
								}
							}

							if (isEnter) {
								break;
							}

							command.setCommand("CLOSE");
							break;
					}
				}
			}
		}

		return commands;
	}

	private static void onAction(ElevatorStatus elevatorStatus, List<Command> commands) throws IOException {
		HttpPost request = new HttpPost(URL + "/action");
		request.addHeader("X-Auth-Token", elevatorStatus.getToken());
		request.addHeader("Content-Type", "application/json");

		Map<String, List<Command>> params = new HashMap<>();
		params.put("commands", commands);

		String string = GSON.toJson(params);
		System.out.println(string);
		request.setEntity(new StringEntity(string));

		HttpResponse httpResponse = httpClient.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				httpResponse.getEntity().getContent()));

		String response = "";
		String str = "";
		while ((str = rd.readLine()) != null) {
			response += str;
		}
		System.out.println(response);
	}

	private static ElevatorStatus onCall(String token) throws IOException {
		HttpGet request = new HttpGet(URL + "/oncalls");

		request.addHeader("X-Auth-Token", token);

		HttpResponse httpResponse = httpClient.execute(request);
		return parseResponse(httpResponse, ElevatorStatus.class);
	}

	private static ElevatorStatus onStart() throws IOException {
		HttpPost request = new HttpPost(URL + "/start/kmj/" + PROBLEM_NUMBER + "/" + ELEVATOR_NUMBER);
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

		System.out.println(response);
		return (T) GSON.fromJson(response, type);
	}
}