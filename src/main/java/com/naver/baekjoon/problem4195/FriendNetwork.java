package com.naver.baekjoon.problem4195;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FriendNetwork {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int questionNumber = Integer.parseInt(br.readLine());

		for (int i = 0;i < questionNumber; i++) {
			int networkNumber = Integer.parseInt(br.readLine());

			Map<String, Integer> peopleMap = new HashMap<>();
			Integer[] parents = new Integer[networkNumber * 2];
			int[] sizes = new int[networkNumber * 2];
			int index = 0;

			for (int j = 0; j < networkNumber; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				String people1 = st.nextToken();
				String people2 = st.nextToken();

				if (!peopleMap.containsKey(people1)) {
					peopleMap.put(people1, index++);
				}

				if (!peopleMap.containsKey(people2)) {
					peopleMap.put(people2, index++);
				}

				//자기번호찾기
				int people1Index = peopleMap.get(people1);
				int people2Index = peopleMap.get(people2);

				//한번도 안나왔었으면 초기화.
				if (sizes[people1Index] == 0 && sizes[people2Index] == 0) {
					sizes[people1Index] = 2;
					sizes[people2Index] = 1;
					parents[people2Index] = people1Index;
				} else if (sizes[people1Index] == 0) {
					sizes[people1Index] = 1;
					sizes[findAncestor(people2Index, parents)] += 1;
					parents[people1Index] = people2Index;
				} else if (sizes[people2Index] == 0) {
					sizes[people2Index] = 1;
					sizes[findAncestor(people1Index, parents)] += 1;
					parents[people2Index] = people1Index;
				}

				//조상 찾기
				int ancestor1 = findAncestor(people1Index, parents);
				int ancestor2 = findAncestor(people2Index, parents);

				//둘 조상이 같으면 원래 알던사이니까 사이즈는 그대로. 다를때만 둘이 합쳐준다.
				if (ancestor1 != ancestor2) {
					parents[ancestor2] = ancestor1;
					sizes[ancestor1] += sizes[ancestor2];
				}

				bw.write(sizes[ancestor1] + "\n");
			}
		}

		bw.flush();
	}

	private static int findAncestor(int peopleIndex, Integer[] parents) {
		//null이면 자기가 root
		return parents[peopleIndex] == null ? peopleIndex : findAncestor(parents[peopleIndex], parents);
	}
}
