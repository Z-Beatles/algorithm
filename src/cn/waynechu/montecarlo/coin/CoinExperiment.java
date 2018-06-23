package cn.waynechu.montecarlo.coin;

import java.util.Random;

/**
 * 男女掷硬币游戏
 * 同正：男+3
 * 同反：男+1
 * 一正一反：男-2
 * <p>
 * 设：男正x，女正y
 * E(男) = 3xy + (1-x)(1-y) -2[x(1-y) + y(1-x)] = (8x -3)y -3x + 1 < 0
 * 即 (8x -3)y < 3x - 1 => y < (3x - 1)/(8x - 3), x > 3/8 或  y > (3x - 1)/(8x - 3), x < 3/8
 * y < (3x - 1)/(8x - 3)  => 在 (3/8, 1] 区间为减函数 => 当 x = 1, 有 y < 2/5
 * y > (3x - 1)/(8x - 3)  => 在 [0, 3/8) 区间为减函数 => 当 x = 0, 有 y > 1/3
 * 则有 当女方控制正面概率为(1/3, 2/5)，随着游戏次数增加，男方钱会越亏越多
 *
 * @author waynechu
 * Created 2018-06-23 19:38
 */
public class CoinExperiment {
    /**
     * 玩游戏的次数
     **/
    private int times;

    private CoinExperiment(int times) {
        if (times <= 0) {
            throw new IllegalArgumentException("times must large than zero!");
        }
        this.times = times;
    }

    public void run(double rate) {
        // 男人初始钱数为0
        int money = 0;
        for (int i = 0; i < times; i++) {
            int moneyCost = play(rate);
            money += moneyCost;
            if (i % 100 == 0) {
                System.out.println("Times: " + i + ", Current money: " + money);
            }
        }
        System.out.println("Finally man's money: " + money);
    }

    /**
     * 一轮游戏
     *
     * @return 每轮游戏结束男人的钱数变化
     */
    private int play(double rate) {
        // 0代表正面，1代表反面
        Random random = new Random();
        int manCoin = random.nextInt(2);

        int womanCoin = getRandom(rate);
        if (manCoin == 0 && womanCoin == 0) {
            // 男女都正面，男人获得3元
            return 3;
        } else if (manCoin == 1 && womanCoin == 1) {
            // 男女都反面，男人获得1元
            return 1;
        } else {
            // 一正一反，男人损失2元
            return -2;
        }
    }

    private int getRandom(double rate) {
        Random random = new Random();
        double rand = random.nextInt(100) / 100.0;
        if (rand > rate) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int times = 1000000;

        // 控制正面率为：(1/3, 2/5)
        double rate = 11 / 30;
        CoinExperiment coinExperiment = new CoinExperiment(times);
        coinExperiment.run(rate);
    }
}
