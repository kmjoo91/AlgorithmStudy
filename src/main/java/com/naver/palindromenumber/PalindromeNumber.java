package com.naver.palindromenumber;

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

        int halfPalindromeNumber = getHalfPalindromeNumber(foundOrder);


        int palindromeNumber = halfPalindromeNumber;

        int cipher = (int) Math.log10(halfPalindromeNumber);

        if (cipher == 0) {
            return palindromeNumber;
        }

        for (int i = 0; i <= cipher; i++, halfPalindromeNumber/=10) {
            if (cipher%2 == 1 && i == 0) {
                continue;
            }

            palindromeNumber = palindromeNumber*10 + (halfPalindromeNumber%10);
        }

        return palindromeNumber;

        //return -1;
    }


    /*
        어차피 반복되기 때문에 절반을 구하면 된다. 이 함수는 절반을 구하는 함수.
        @return PalindromeNumber의 절반
     */
    int getHalfPalindromeNumber(int findOrder) {
        int cipherCalculateNumber = 9;
        for (int cipher = 0; true; cipher++) {
            findOrder -= cipherCalculateNumber;
            if (findOrder <= 0) {
                return (int) Math.pow(10, (int)(cipher/2)) + findOrder + cipherCalculateNumber -1;
            }

            if (cipher%2 == 1) {
                cipherCalculateNumber *= 10;
            }
        }
    }
}
