package uk.co.chriskeeley.snake_eyes.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

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

    @JsonCreator
    public static Payout forValue(String value) {
        return Arrays.stream(values()).filter(p -> p.getName().equalsIgnoreCase(value)).findFirst().get();
    }

    @JsonValue
    public String toValue() {
        return this.name;
    }

}
