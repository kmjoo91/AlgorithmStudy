package com.naver.palindromenumber;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kim.minjoo on 2017-01-27.
 */
public class PalindromeNumber {
    int foundOrder;

    public PalindromeNumber() {
    }

    public PalindromeNumber(int foundOrder) {
        this.foundOrder = foundOrder;
    }

    public int getFoundOrder() {
        return foundOrder;
    }

    public void setFoundOrder(int foundOrder) {
        this.foundOrder = foundOrder;
    }

    public int findPalindromeNumber() {
        return findPalindromeNumber(foundOrder);
    }

    public int findPalindromeNumber(int foundOrder) {
        foundOrder--;

        if (foundOrder == 0) {
            return 0;
        }

        Map<String, Integer> halfPalindromeNumberAndCipher = getHalfPalindromeNumberAndCipher(foundOrder);
        int halfPalindromeNumber = halfPalindromeNumberAndCipher.get("halfPalindromeNumber");
        int cipher = halfPalindromeNumberAndCipher.get("cipher");


        int palindromeNumber = halfPalindromeNumber;

        if (cipher == 0) {
            return palindromeNumber;
        }

        for (int i = 0; i <= cipher/2; i++, halfPalindromeNumber/=10) {
            ;
            if (cipher%2 == 0 && i == 0) {
                continue;
            }

            palindromeNumber = palindromeNumber * 10 + (halfPalindromeNumber%10);
        }

        return palindromeNumber;

        //return -1;
    }


    /*
        어차피 반복되기 때문에 절반을 구하면 된다. 이 함수는 절반을 구하는 함수.
        @return PalindromeNumber의 절반
     */
    Map<String, Integer> getHalfPalindromeNumberAndCipher(int findOrder) {
        int cipherCalculateNumber = 9;
        for (int cipher = 0; true; cipher++) {
            findOrder -= cipherCalculateNumber;
            if (findOrder <= 0) {
                int halfPalindromeNumber = (int) Math.pow(10, (int)(cipher/2)) + findOrder + cipherCalculateNumber -1;
                Map<String, Integer> resultMap = new HashMap<String, Integer>();
                resultMap.put("halfPalindromeNumber", halfPalindromeNumber);
                resultMap.put("cipher", cipher);
                return resultMap;
            }

            if (cipher%2 == 1) {
                cipherCalculateNumber *= 10;
            }
        }
    }
}
