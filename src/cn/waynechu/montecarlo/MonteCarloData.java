package cn.waynechu.montecarlo;

import java.awt.*;
import java.util.LinkedList;

/**
 * @author waynechu
 * Created 2018-03-22 20:08
 */
public class MonteCarloData {
    private Circle circle;
    private LinkedList<Point> points;
    private int insideCircle = 0;

    public MonteCarloData(Circle circle) {
        this.circle = circle;
        points = new LinkedList<>();
    }

    public Circle getCircle() {
        return circle;
    }

    public void addPoint(Point point) {
        points.add(point);
        if (circle.contain(point)) {
            insideCircle++;
        }
    }

    public Point getPoint(int index) {
        if (index < 0 || index > points.size()) {
            throw new IllegalArgumentException("out of bound in getPoint!");
        }
        return points.get(index);
    }

    public int getPointNumber() {
        return points.size();
    }

    public double estimatePi() {
        if (points.size() == 0) {
            return 0.00;
        }
        int circleArea = insideCircle;
        int squareArea = points.size();
        return (double) 4 * circleArea / squareArea;
    }

}
