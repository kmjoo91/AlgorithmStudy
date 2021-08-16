package com.naver.quadranglevolume;

/**
 * Created by kim.minjoo on 2017-10-28.
 */
public class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getVolume(Point point) {
        int width = this.x > point.x ? this.x - point.x : point.x - this.x;
        int height = this.y > point.y ? this.y - point.y : point.y - this.y;

        return width * height;
    }
}
