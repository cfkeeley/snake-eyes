package uk.co.chriskeeley.snake_eyes.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSnakeEyesService {

    final static Logger log = LoggerFactory.getLogger(TestSnakeEyesService.class);

    @Test
    public void shouldRespondWithExpectedOutcome() {
        final SnakeEyesService roller = new SnakeEyesService();
        final Outcome outcome = roller.getOutcome();
        log.info("Outcome:'{}'",outcome);
    }
}
