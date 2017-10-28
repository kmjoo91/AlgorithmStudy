package com.naver.quadranglevolume;

/**
 * Created by kim.minjoo on 2017-10-28.
 */
public class Quadrangle {
    private Point point1;
    private Point point2;
    private int volume;

    public int getVolume() {
        return volume;
    }

    public Quadrangle(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        this.volume = point1.getVolume(point2);
    }

    public int getContainVolume(Quadrangle quadrangle) {
        //x범위부터 구하기!
        if (this.point1.x > this.point2.x) {
            Point temp = point1;
            this.point1 = this.point2;
            this.point2 = temp;
        }

        if (quadrangle.point1.x > quadrangle.point2.x) {
            Point temp = point1;
            quadrangle.point1 = quadrangle.point2;
            quadrangle.point2 = temp;
        }

        //일단 안겹칠때
        if (this.point1.x >= quadrangle.point2.x || this.point2.x <= this.point1.x) {
            return 0;
        }

        int width = 0;
        if (this.point1.x >=  quadrangle.point1.x) {
            if (this.point2.x <= quadrangle.point2.x) {
                width = this.point2.x - this.point1.x;
            } else {
                width = quadrangle.point2.x - this.point1.x;
            }
        } else {
            if (this.point2.x <= quadrangle.point2.x) {
                width = this.point2.x - quadrangle.point1.x;
            } else {
                width = quadrangle.point2.x - quadrangle.point1.x;
            }
        }

        //y 범위 구하기!!!!

        if (this.point1.y > this.point2.y) {
            Point temp = point1;
            this.point1 = this.point2;
            this.point2 = temp;
        }

        if (quadrangle.point1.y > quadrangle.point2.y) {
            Point temp = point1;
            quadrangle.point1 = quadrangle.point2;
            quadrangle.point2 = temp;
        }

        if (this.point1.y >= quadrangle.point2.y || this.point2.y <= this.point1.y) {
            return 0;
        }

        int height = 0;
        if (this.point1.y >=  quadrangle.point1.y) {
            if (this.point2.y <= quadrangle.point2.y) {
                height = this.point2.y - this.point1.y;
            } else {
                height = quadrangle.point2.y - this.point1.y;
            }
        } else {
            if (this.point2.y <= quadrangle.point2.y) {
                height = this.point2.y - quadrangle.point1.y;
            } else {
                height = quadrangle.point2.y - quadrangle.point1.y;
            }
        }

        return width * height;
    }
}
