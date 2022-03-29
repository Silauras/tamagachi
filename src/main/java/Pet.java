import javax.naming.ldap.ManageReferralControl;

import java.util.Date;

import static java.lang.Thread.sleep;

public class Pet implements Runnable {

    private int maxCheerfulness = 100;
    private int maxSatiety = 100;
    private int maxHappiness = 100;

    private int cheerfulness = maxCheerfulness;
    private int satiety = maxSatiety;
    private int happiness = maxHappiness;

    private boolean sleeping = false;
    private boolean alive = true;

    private long tickRate = 1000;

    public Pet(long tickRate) {
        this.tickRate = tickRate;
    }

    public Pet(int maxCheerfulness, int maxSatiety, int maxHappiness) {
        this.maxCheerfulness = maxCheerfulness;
        this.maxSatiety = maxSatiety;
        this.maxHappiness = maxHappiness;
        this.cheerfulness = maxCheerfulness;
        this.satiety = maxSatiety;
        this.happiness = maxHappiness;
    }

    public Pet(int maxCheerfulness, int maxSatiety, int maxHappiness, long tickRate) {
        this.maxCheerfulness = maxCheerfulness;
        this.maxSatiety = maxSatiety;
        this.maxHappiness = maxHappiness;
        this.cheerfulness = maxCheerfulness;
        this.satiety = maxSatiety;
        this.happiness = maxHappiness;
        this.tickRate = tickRate;
    }

    @Override
    public void run() {
        while (alive) {
            printIndicators();
            if (satiety == 0 || happiness <= 20 && satiety <= 20) {
                alive = false;
                continue;
            }
            if (cheerfulness == 0) {
                sleeping = true;
            } else if (cheerfulness == maxCheerfulness) {
                sleeping = false;
                happiness = Math.min(maxHappiness, happiness += maxHappiness / 5);
            }
            if (sleeping) {
                cheerfulness += 2;
            }
            cheerfulness = Math.max(0, cheerfulness - 1);
            satiety = Math.max(0, satiety - 1);
            happiness = Math.max(0, happiness - 1);
            try {
                sleep(tickRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("is dead");
    }

    private void printIndicators() {
        System.out.println(new Date(System.currentTimeMillis())
                + "\n cheerfulness = " + cheerfulness
                + "\n satiety = " + satiety
                + "\n happiness = " + happiness
                + "\n is " + (!sleeping ? "not " : "") + "sleeping"
        );
    }


    public boolean isAlive() {
        return alive;
    }

    public void feed() {
        if (sleeping) {
            happiness = Math.max(0, happiness - maxHappiness / 5);
            cheerfulness = Math.max(0, cheerfulness - maxCheerfulness / 10);
            satiety = Math.max(satiety, maxSatiety / 2);
        } else if (cheerfulness > 10) {
            satiety = maxSatiety / 2;
        } else {
            satiety = maxSatiety;
        }
    }

    public void play() {
        if (sleeping) {
            satiety = Math.max(0, satiety - maxSatiety / 10);
            cheerfulness = Math.max(0, cheerfulness - maxCheerfulness / 10);
            happiness = Math.max(happiness, maxHappiness / 2);
        } else if (cheerfulness > 10) {
            happiness = happiness / 2;
        } else {
            happiness = maxHappiness;
        }
    }
}