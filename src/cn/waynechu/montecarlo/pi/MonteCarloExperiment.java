package cn.waynechu.montecarlo.pi;

import java.awt.*;
import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-22 20:32
 */
public class MonteCarloExperiment {
    private int squareSide;
    private int number;
    private int outputInterval = 100;

    private MonteCarloExperiment(int squareSide, int number) {
        if (squareSide <= 0 || number <= 0) {
            throw new IllegalArgumentException("squareSide and number must larger than zero");
        }
        this.squareSide = squareSide;
        this.number = number;
    }

    public void setOutputInterval(int outputInterval) {
        if (outputInterval <= 0) {
            throw new IllegalArgumentException("outputInterval must be large than zero");
        }
        this.outputInterval = outputInterval;
    }

    public void run() {
        Circle circle = new Circle(squareSide / 2, squareSide / 2, squareSide / 2);
        MonteCarloData data = new MonteCarloData(circle);
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            // 每隔100次输出一次PI值
            if (i % outputInterval == 0) {
                System.out.println("[" + i + "]PI ≈ " + data.estimatePi());
            }
            int x = random.nextInt(squareSide) + 1;
            int y = random.nextInt(squareSide) + 1;
            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args) {
        // 投掷区域大小
        int squareSide = 800;
        // 随机点个数
        int number = 10000000;
        // 输出间隔
        int outputInterval = 10000;

        MonteCarloExperiment monteCarloExperiment = new MonteCarloExperiment(squareSide, number);
        monteCarloExperiment.setOutputInterval(outputInterval);
        monteCarloExperiment.run();
    }
}
