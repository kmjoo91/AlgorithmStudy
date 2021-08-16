package com.naver.kakao.year2019;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FailRate {
	public int[] solution(int N, int[] stages) {
		List<Stageinfo> stageinfoList = new ArrayList<>();

		for (int i = 0; i < N + 1; i++) {
			stageinfoList.add(new Stageinfo(i + 1));
		}

		for (int stage : stages) {
			for (int i = stage - 1; i >= 0; i--) {
				if (i == stage - 1) {
					stageinfoList.get(i).addCurrent();
				}

				stageinfoList.get(i).addChallenge();
			}
		}

		stageinfoList.remove(N);
		stageinfoList.sort(new StageComparator());
		int[] answer = new int[N];

		for (int i = 0; i < N; i++) {
			answer[i] = stageinfoList.get(i).getStage();
		}

		return answer;
	}

	class Stageinfo {
		private int stage;
		private int challenge;
		private int current;

		public int getStage() {
			return stage;
		}

		public int getChallenge() {
			return challenge;
		}

		public int getCurrent() {
			return current;
		}

		public Stageinfo(int stage) {
			this.stage = stage;
		}

		public void addCurrent() {
			this.current++;
		}

		public void addChallenge() {
			this.challenge++;
		}
	}

	class StageComparator implements Comparator<Stageinfo> {
		@Override
		public int compare(Stageinfo o1, Stageinfo o2) {
			double o1FailRate = o1.getChallenge() != 0 ? (double)o1.getCurrent() / (double)o1.getChallenge() : 0;
			double o2FailRate = o2.getChallenge() != 0 ? (double)o2.getCurrent() / (double)o2.getChallenge() : 0;
			if (o1FailRate > o2FailRate) {
				return -1;
			} else if (o1FailRate < o2FailRate) {
				return 1;
			}

			return Integer.compare(o1.getStage(), o2.getStage());
		}
	}
}
