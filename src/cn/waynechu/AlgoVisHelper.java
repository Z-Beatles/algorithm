package cn.waynechu;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Graphics2d 绘图工具类
 *
 * @author waynechu
 * Created 2018-03-11 17:27
 */
public class AlgoVisHelper {
    private AlgoVisHelper() {
    }

    public static void setStrokeWidth(Graphics2D g2d, int w) {
        g2d.setStroke(new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void setColor(Graphics2D g2d, Color color) {
        g2d.setColor(color);
    }

    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Float(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(circle);
    }

    public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Float(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(circle);
    }

    public static void pause(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
