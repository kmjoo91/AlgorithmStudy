package com.naver.kakao.year2018;

import java.util.HashMap;
import java.util.Map;

public class NewsClustering {
    public int solution(String str1, String str2) {
        int answer = 0;

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        Map<String, Integer> str1Map = createSubStringMap(str1);
        Map<String, Integer> str2Map = createSubStringMap(str2);

        if (str1Map.isEmpty() && str2Map.isEmpty()) {
            return 65536;
        }

        int intersectionCount = 0;
        int unionCount = 0;

        for (String key : str1Map.keySet()) {
            int str2Count = str2Map.getOrDefault(key, 0);

            if (str2Count == 0) {
                unionCount += str1Map.getOrDefault(key, 0);
                continue;
            }

            intersectionCount += Math.min(str2Count, str1Map.get(key));
            unionCount += Math.max(str2Count, str1Map.get(key));
        }

        //여기선 str2에만 있는걸 찾으면됨
        for (String key : str2Map.keySet()) {
            Integer str1Count = str1Map.get(key);

            if (str1Count == null) {
                unionCount += str2Map.get(key);
            }
        }

        answer = (int)((double)intersectionCount / (double)unionCount * 65536);
        return answer;
    }

    private Map<String, Integer> createSubStringMap(String str) {
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < str.length() - 1; i++) {
            //알파벳 아니면 넘어가야함
            if (!Character.isAlphabetic(str.charAt(i))) {
                continue;
            }

            //이건 다음꺼 할필요없으니까 다다음으로 넘겨줌
            if (!Character.isAlphabetic(str.charAt(i + 1))) {
                i++;
                continue;
            }

            String key = str.substring(i, i + 2);
            result.put(key, result.getOrDefault(key, 0) + 1);
        }

        return result;
    }
}
