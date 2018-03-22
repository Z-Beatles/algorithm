package cn.waynechu.montecarlo.threegates;

import java.util.Random;

/**
 * @author waynechu
 * Created 2018-03-22 21:23
 */
public class ThreeGatesExperiment {
    private int number;

    public ThreeGatesExperiment(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("number must large than zero!");
        }
        this.number = number;
    }

    public void run(boolean changeDoor) {
        int wins = 0;
        for (int i = 0; i < number; i++) {
            if (play(changeDoor)) {
                wins++;
            }
        }
        System.out.println("[" + (changeDoor ? "Change door" : "Not change door") + "] win rate: " + (double) wins / number);
    }

    /**
     * 判断是否中奖
     **/
    private boolean play(boolean changeDoor) {
        // Door 0, 1, 2
        Random random = new Random();
        int prizePoor = random.nextInt(3);
        int playerChoice = random.nextInt(3);
        if (prizePoor != playerChoice) {
            // 没选对门
            return changeDoor;
        } else {
            return !changeDoor;
        }
    }

    public static void main(String[] args) {
        int number = 10000000;

        ThreeGatesExperiment threeGatesExperiment = new ThreeGatesExperiment(number);
        threeGatesExperiment.run(true);
        threeGatesExperiment.run(false);
    }
}
