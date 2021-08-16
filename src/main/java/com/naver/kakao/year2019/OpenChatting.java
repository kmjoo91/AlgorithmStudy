package com.naver.kakao.year2019;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class OpenChatting {
	public String[] solution(String[] record) {
		Map<String, String> nickNameMap = new HashMap<>();
		Queue<Event> eventQueue = new LinkedList<>();

		int eventCount = 0;
		for (String event : record) {
			String[] parseEvent = event.split(" ");
			Action action = Action.valueOf(parseEvent[0].toUpperCase());
			String id = parseEvent[1];

			if (action == Action.LEAVE) {
				eventQueue.offer(new Event(action, id));
				continue;
			}

			String nickName = parseEvent[2];

			nickNameMap.put(id, nickName);

			if (action == Action.CHANGE) {
				continue;
			}

			eventQueue.offer(new Event(action, id));
		}

		String[] answer = new String[eventQueue.size()];

		for (int i = 0; i < answer.length; i++) {
			Event event = eventQueue.poll();
			answer[i] = String.format(event.getAction().getFormat(), nickNameMap.get(event.getId()));
		}

		return answer;
	}

	enum Action {
		ENTER("%s님이 들어왔습니다."),
		LEAVE("%s님이 나갔습니다."),
		CHANGE("%s님이 닉네임을 변경하였습니다.");

		private final String format;

		Action(String format) {
			this.format = format;
		}

		public String getFormat() {
			return format;
		}
	}

	class Event {
		private Action action;
		private String id;

		public Event(Action action, String id) {
			this.action = action;
			this.id = id;
		}

		public Action getAction() {
			return action;
		}

		public String getId() {
			return id;
		}
	}
}
