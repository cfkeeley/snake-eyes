package uk.co.chriskeeley.snake_eyes.web;

public class Outcome {

    private final int dice1;
    private final int dice2;
    private final float stake;
    private final float winnings;
    private final Payout payout_name;

    public Outcome() {
        this.dice1 = 0;
        this.dice2 = 0;
        this.stake = 0f;
        this.winnings = 0f;
        this.payout_name = Payout.NO_WIN;
    }

    public Outcome(final int dice1, final int dice2, final float stake, final float winnings, final Payout payout_name) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.stake = stake;
        this.winnings = winnings;
        this.payout_name = payout_name;
    }

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public float getStake() {
        return stake;
    }

    public float getWinnings() {
        return winnings;
    }

    public String getPayout_name() {
        return payout_name.getName();
    }

}
