package uk.co.chriskeeley.snake_eyes.web;

public enum PayoutName {
    SNAKE_EYES("snake eyes"),OTHER_PAIR("other pair");
    final String payoutName;
    PayoutName(final String payoutName) {
        this.payoutName = payoutName;
    }
    public String getPayoutName() {
        return this.payoutName;
    }

}
