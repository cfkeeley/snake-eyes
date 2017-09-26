package uk.co.chriskeeley.snake_eyes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnakeEyesService {

    final static Logger log = LoggerFactory.getLogger(SnakeEyesService.class);

    private SnakeEyesDice snakeEyesDice;

    private SnakeEyesDiceValidator snakeEyesDiceValidator;

    @Autowired
    public SnakeEyesService(final SnakeEyesDice snakeEyesDice,final SnakeEyesDiceValidator snakeEyesDiceValidator) {
        this.snakeEyesDice = snakeEyesDice;
        this.snakeEyesDiceValidator = snakeEyesDiceValidator;
    }

    public Outcome getOutcome(final double stake) {

        final String[] diceValues = snakeEyesDiceValidator.validate(
                snakeEyesDice.roll()).split(" ");

        log.info("dice values:'{}','{}'",diceValues[0],diceValues[1]);

        int dice1 = Integer.parseInt(diceValues[0]);
        int dice2 = Integer.parseInt(diceValues[1]);

        final boolean isSnakeEyes = dice1 == 1 && dice2 == 1;
        final double winnings = calculateWinnings(isSnakeEyes,stake);
        final Payout payoutName = isSnakeEyes ? Payout.SNAKE_EYES : Payout.OTHER_PAIR;

        return new Outcome(dice1, dice2, stake, winnings, payoutName);
    }

    private double calculateWinnings(final boolean isSnakeEyes, double stake) {
        return isSnakeEyes ? stake * Payout.SNAKE_EYES.multiplier
                : stake * Payout.OTHER_PAIR.multiplier;
    }
}