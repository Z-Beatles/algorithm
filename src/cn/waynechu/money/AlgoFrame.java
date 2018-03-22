package cn.waynechu.money;

import cn.waynechu.bubble.AlgoVisHelper;
import cn.waynechu.bubble.Circle;

import javax.swing.*;
import java.awt.*;

/**
 * @author waynechu
 * Created 2018-02-27 22:44
 */
public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;
    private int[] money;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        // 设置Content Pane内容面板
        this.setContentPane(canvas);
        // 窗口大小不可变
        setResizable(false);
        // 默认关闭操作
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 窗口可见
        setVisible(true);
        // 调整frame大小以适合子组件的首选大小和布局
        pack();
    }

    public void render(int[] money) {
        this.money = money;
        // 重新绘制
        repaint();
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            // 启用双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 获得绘图上下文环境
            Graphics2D graphics2D = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.addRenderingHints(hints);

            // 绘制图形
            int w = canvasWidth / money.length;
            for (int i = 0; i < money.length; i++) {
                if (money[i] > 0) {
                    AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.Blue);
                    AlgoVisHelper.fillRectangle(graphics2D, w * i, canvasHeight / 2 - money[i], w - 1, money[i]);
                } else if (money[i] < 0) {
                    AlgoVisHelper.setColor(graphics2D, AlgoVisHelper.DeepOrange);
                    AlgoVisHelper.fillRectangle(graphics2D, w * i, canvasHeight / 2, w - 1, -money[i]);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
