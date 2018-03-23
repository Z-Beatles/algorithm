package cn.waynechu.bubble;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

/**
 * Graphics2d 绘图工具类
 *
 * @author waynechu
 * Created 2018-03-11 17:27
 */
public class AlgoVisHelper {
    private AlgoVisHelper() {
    }

    public static final Color Red = new Color(0xF44336);
    public static final Color Pink = new Color(0xE91E63);
    public static final Color Purple = new Color(0x9C27B0);
    public static final Color DeepPurple = new Color(0x673AB7);
    public static final Color Indigo = new Color(0x3F51B5);
    public static final Color Blue = new Color(0x2196F3);
    public static final Color LightBlue = new Color(0x03A9F4);
    public static final Color Cyan = new Color(0x00BCD4);
    public static final Color Teal = new Color(0x009688);
    public static final Color Green = new Color(0x4CAF50);
    public static final Color LightGreen = new Color(0x8BC34A);
    public static final Color Lime = new Color(0xCDDC39);
    public static final Color Yellow = new Color(0xFFEB3B);
    public static final Color Amber = new Color(0xFFC107);
    public static final Color Orange = new Color(0xFF9800);
    public static final Color DeepOrange = new Color(0xFF5722);
    public static final Color Brown = new Color(0x795548);
    public static final Color Grey = new Color(0x9E9E9E);
    public static final Color BlueGrey = new Color(0x607D8B);
    public static final Color Black = new Color(0x000000);
    public static final Color White = new Color(0xFFFFFF);

    /**
     * 随机返回一种颜色
     */
    public static Color randomColor(Integer key) {
        if (key < 1 || key > 20) {
            throw new IllegalArgumentException("key must between 1 and 20 !");
        }
        HashMap<Integer, Color> colors = new HashMap<>(20);
        colors.put(1, Red);
        colors.put(2, Pink);
        colors.put(3, Purple);
        colors.put(4, DeepPurple);
        colors.put(5, Indigo);
        colors.put(6, Blue);
        colors.put(7, LightBlue);
        colors.put(8, Cyan);
        colors.put(9, Teal);
        colors.put(10, Green);
        colors.put(11, LightGreen);
        colors.put(12, Lime);
        colors.put(13, Yellow);
        colors.put(14, Amber);
        colors.put(15, Orange);
        colors.put(16, DeepOrange);
        colors.put(17, Brown);
        colors.put(18, Grey);
        colors.put(19, BlueGrey);
        colors.put(20, Black);
        return colors.get(key);
    }

    /**
     * 设置画笔宽度
     **/
    public static void setStrokeWidth(Graphics2D g2d, int w) {
        g2d.setStroke(new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    /**
     * 设置画笔颜色
     **/
    public static void setColor(Graphics2D g2d, Color color) {
        g2d.setColor(color);
    }

    /**
     * 绘制空心圆
     **/
    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(circle);
    }

    /**
     * 绘制实心圆
     **/
    public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(circle);
    }

    /**
     * 绘制空心矩形
     **/
    public static void strokeRectangle(Graphics2D g2d, int x, int y, int w, int h) {
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        g2d.draw(rectangle);
    }

    /**
     * 绘制实心矩形
     **/
    public static void fillRectangle(Graphics2D g2d, int x, int y, int w, int h) {
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        g2d.fill(rectangle);
    }

    /**
     * 添加图片
     **/
    public static void putImage(Graphics2D g2d, int x, int y, String imageURL) {
        ImageIcon icon = new ImageIcon(imageURL);
        Image image = icon.getImage();
        g2d.drawImage(image, x, y, null);
    }

    /**
     * 添加文字
     **/
    public static void drawText(Graphics2D g, String text, int centerX, int centerY) {
        if (text == null) {
            throw new IllegalArgumentException("text is null!");
        }
        FontMetrics metrics = g.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getDescent();
        g.drawString(text, centerX - w / 2, centerY + h);
    }


    /**
     * 进程睡眠
     **/
    public static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
