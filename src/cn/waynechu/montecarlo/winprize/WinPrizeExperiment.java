package cn.waynechu.montecarlo.winprize;

import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-22 22:03
 */
public class WinPrizeExperiment {
    private double rate;
    private int playTimes;
    private int number;

    public WinPrizeExperiment(double rate, int playTimes, int number) {
        if (rate < 0 || rate > 1) {
            throw new IllegalArgumentException("rate must be between 0 and 1 !");
        }
        if (playTimes <= 0 || number <= 0) {
            throw new IllegalArgumentException("playTimes and number must large than 0 !");
        }
        this.rate = rate;
        this.playTimes = playTimes;
        this.number = number;
    }

    public void run() {
        int wins = 0;
        for (int i = 0; i < number; i++) {
            if (play()) {
                wins++;
            }
        }
        System.out.println("win rate: " + (double) wins / number * 100 + " %");
    }

    private boolean play() {
        Random random = new Random();
        for (int i = 0; i < playTimes; i++) {
            if (random.nextDouble() < rate) {
                return true;
            }
        }
        return false;
    }

    /**
     * 打开一个宝箱的中奖概率为5%，打开20个这样的宝箱，中奖概率是多少 1 - 0.95^20 = 0.64151407759145776564258955955505
     * 这里的20代表期望值，就是所有用户平均每个人要开20个宝箱
     **/
    public static void main(String[] args) {
        // 中奖概率
        double rate = 0.05;
        // 抽宝箱次数
        int playTimes = 20;
        // 模拟次数
        int number = 10000;

        WinPrizeExperiment winPrizeExperiment = new WinPrizeExperiment(rate, playTimes, number);
        winPrizeExperiment.run();
    }
}
