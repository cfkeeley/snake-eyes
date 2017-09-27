package uk.co.chriskeeley.snake_eyes.web;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
 class SnakeEyesDiceValidator {

    public String validate(final String snakeEyesDiceResult) {
        final Pattern diceRollValidation = Pattern.compile("([1-6])\\t([1-6])\n");
        final Matcher matcher = diceRollValidation.matcher(snakeEyesDiceResult);

        if(!matcher.matches() || matcher.groupCount() != 2) {
            throw new IllegalStateException("failed to get a valid dice roll outcome. Got:'"+snakeEyesDiceResult+"'");
        }

        return snakeEyesDiceResult;
    }
}
