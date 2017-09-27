package uk.co.chriskeeley.snake_eyes.web;

enum Payout {
    SNAKE_EYES("snake eyes", 30),OTHER_PAIR("other pair",7),NO_WIN("no win",0);
    final String name;
    int multiplier;
    Payout(final String name, int multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }
    public String getName() {
        return this.name;
    }
    public int getMultiplier() {
        return this.multiplier;
    }

}
