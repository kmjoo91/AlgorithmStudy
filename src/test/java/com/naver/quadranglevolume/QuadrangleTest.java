package com.naver.quadranglevolume;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kim.minjoo on 2017-10-28.
 */
public class QuadrangleTest {
    @Test
    public void 안겹칠때_test() {
        //given
        Point point1 = new Point(700, 400);
        Point point2 = new Point(1600, 1100);
        Point point3 = new Point(0, 100);
        Point point4 = new Point(100, 200);
        Point point5 = new Point(100, 0);
        Point point6 = new Point (200, 100);

        Quadrangle quadrangle1 = new Quadrangle(point1, point2);
        Quadrangle quadrangle2 = new Quadrangle(point3, point4);
        Quadrangle quadrangle3 = new Quadrangle(point5, point6);

        int expected = 630000;

        //when
        int actual = quadrangle1.getVolume() - quadrangle1.getContainVolume(quadrangle2) - quadrangle1.getContainVolume(quadrangle3) + quadrangle2.getContainVolume(quadrangle3);

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void 겹칠때_test() {
        //given
        Point point1 = new Point(700, 400);
        Point point2 = new Point(1600, 1100);
        Point point3 = new Point(0, 400);
        Point point4 = new Point(1100, 900);
        Point point5 = new Point(900, 0);
        Point point6 = new Point (1800, 650);

        Quadrangle quadrangle1 = new Quadrangle(point1, point2);
        Quadrangle quadrangle2 = new Quadrangle(point3, point4);
        Quadrangle quadrangle3 = new Quadrangle(point5, point6);

        int expected = 305000;

        //when
        int actual = quadrangle1.getVolume() - quadrangle1.getContainVolume(quadrangle2) - quadrangle1.getContainVolume(quadrangle3) + quadrangle2.getContainVolume(quadrangle3);

        //then
        assertEquals(expected, actual);
    }
}