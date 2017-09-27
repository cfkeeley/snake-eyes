package uk.co.chriskeeley.snake_eyes.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={TestSnakeEyesService.Config.class})
public class TestSnakeEyesService {

    final static Logger log = LoggerFactory.getLogger(TestSnakeEyesService.class);

    @Autowired
    private SnakeEyesDiceValidator snakeEyesDiceValidator;

    @Autowired
    private SnakeEyesDice snakeEyesDice;



    @Test
    public void shouldRespondWithExpectedOtherPairOutcome() {

        final String diceValues = "1\t2\n";
        when(snakeEyesDice.roll()).thenReturn(diceValues);
        when(snakeEyesDiceValidator.validate(diceValues)).thenReturn(diceValues);

        final SnakeEyesService snakeEyesService = new SnakeEyesService(snakeEyesDice,snakeEyesDiceValidator);
        final Outcome outcome = snakeEyesService.getOutcome(1.00f);

        assertThat(outcome.getDice1()).as("unexpected dice 1 value").isEqualTo(1);
        assertThat(outcome.getDice2()).as("unexpected dice 2 value").isEqualTo(2);
        assertThat(outcome.getStake()).as("unexpected stake value").isEqualTo(1.00f);
        assertThat(outcome.getWinnings()).as("unexpected winnings value").isEqualTo(0.00f);
        assertThat(outcome.getPayout_name()).as("unexpected payout name value")
                .isEqualTo(Payout.NO_WIN.getName());

    }

    @Test
    public void shouldRespondWithExpectedSnakeEyesOutcome() {

        final String diceValues = "1\t1\n";
        when(snakeEyesDice.roll()).thenReturn(diceValues);
        when(snakeEyesDiceValidator.validate(diceValues)).thenReturn(diceValues);

        final SnakeEyesService snakeEyesService = new SnakeEyesService(snakeEyesDice,snakeEyesDiceValidator);
        final Outcome outcome = snakeEyesService.getOutcome(2.00f);

        assertThat(outcome.getDice1()).as("unexpected dice 1 value").isEqualTo(1);
        assertThat(outcome.getDice2()).as("unexpected dice 2 value").isEqualTo(1);
        assertThat(outcome.getStake()).as("unexpected stake value").isEqualTo(2.00f);
        assertThat(outcome.getWinnings()).as("unexpected winnings value").isEqualTo(60.00f);
        assertThat(outcome.getPayout_name()).as("unexpected payout name value")
                .isEqualTo(Payout.SNAKE_EYES.getName());
    }

    @Configuration
    @ComponentScan
    @EnableWebMvc
    static class Config {

        @Bean
        public SnakeEyesDiceValidator snakeEyesDiceValidator() {
            return Mockito.mock(SnakeEyesDiceValidator.class);
        }

        @Bean
        public SnakeEyesDice snakeEyesDice() {
            return Mockito.mock(SnakeEyesDice.class);
        }
    }
}
