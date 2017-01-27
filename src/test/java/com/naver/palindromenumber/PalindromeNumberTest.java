package com.naver.palindromenumber;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kim.minjoo on 2017-01-27.
 */
public class PalindromeNumberTest {
    PalindromeNumber palindromeNumber = new PalindromeNumber();

    @Test
    public void 잘나오나테스트() {
        assertEquals(0, palindromeNumber.findPalindromeNumber(1));
        assertEquals(3, palindromeNumber.findPalindromeNumber(4));
        assertEquals(202, palindromeNumber.findPalindromeNumber(30));
        assertEquals(909, palindromeNumber.findPalindromeNumber(100));
        assertEquals(2000002, palindromeNumber.findPalindromeNumber(3000));
        //palindromeNumber.findPalindromeNumber(1000000);
    }
}
