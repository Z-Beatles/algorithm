package cn.waynechu.montecarlo.pi;

import java.awt.*;

/**
 * @author waynechu
 * Created 2018-03-22 19:07
 */
public class Circle {
    private int x, y, r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    /**
     * 判断点是否在圆内
     **/
    public boolean contain(Point point) {
        return Math.pow(point.getX() - x, 2) + Math.pow(point.getY() - y, 2) <= r * r;
    }
}
