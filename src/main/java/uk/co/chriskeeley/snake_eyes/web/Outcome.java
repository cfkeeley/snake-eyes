package uk.co.chriskeeley.snake_eyes.web;

public class Outcome {

    private final Integer dice1;
    private final Integer dice2;
    private final Double stake;
    private final Double winnings;
    private final Payout payout_name;

    public Outcome(final Integer dice1, final Integer dice2, final Double stake, final Double winnings, final Payout payout_name) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.stake = stake;
        this.winnings = winnings;
        this.payout_name = payout_name;
    }

    public Integer getDice1() {
        return dice1;
    }

    public Integer getDice2() {
        return dice2;
    }

    public Double getStake() {
        return stake;
    }

    public Double getWinnings() {
        return winnings;
    }

    public String getPayout_name() {
        return payout_name.getName();
    }
}
