package uk.co.chriskeeley.snake_eyes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
 class SnakeEyesService {

    final static Logger log = LoggerFactory.getLogger(SnakeEyesService.class);

    private SnakeEyesDice snakeEyesDice;

    private SnakeEyesDiceValidator snakeEyesDiceValidator;

    @Autowired
    public SnakeEyesService(final SnakeEyesDice snakeEyesDice,final SnakeEyesDiceValidator snakeEyesDiceValidator) {
        this.snakeEyesDice = snakeEyesDice;
        this.snakeEyesDiceValidator = snakeEyesDiceValidator;
    }

    public Outcome getOutcome(final float stake) {

        final String diceValues = snakeEyesDiceValidator.validate(
                snakeEyesDice.roll());

        final String[] tokens = diceValues.replace("\n","").split("\t");

        log.info("dice values:'{}','{}'",tokens[0],tokens[1]);

        int dice1 = Integer.parseInt(tokens[0]);
        int dice2 = Integer.parseInt(tokens[1]);

        final boolean isSnakeEyes = dice1 == 1 && dice2 == 1;
        final Payout payout = isSnakeEyes ? Payout.SNAKE_EYES : dice1 == dice2
                ? Payout.OTHER_PAIR :Payout.NO_WIN;

        final float winnings = stake * payout.multiplier;

        return new Outcome(dice1, dice2, stake, winnings, payout);
    }
}
