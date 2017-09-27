package uk.co.chriskeeley.snake_eyes.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={TestSnakeEyesController.Config.class})
public class TestSnakeEyesController {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    private SnakeEyesService snakeEyesService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void controllerShouldReturnExpectedOutcomeForSnakeEyes() throws Exception {

        when(snakeEyesService.getOutcome(1.00f)).thenReturn(
                new Outcome(1,1, 1.00f, 30.0f, Payout.SNAKE_EYES));

        mockMvc.perform(
                get("/snakeeyes/play").param("stake","1.00"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("dice1").value(1))
                .andExpect(jsonPath("dice2").value(1))
                .andExpect(jsonPath("stake").value(1.00))
                .andExpect(jsonPath("winnings").value(30.00))
                .andExpect(jsonPath("payout_name").value("snake eyes"));
    }

    @Configuration
    @ComponentScan
    @EnableWebMvc
    static class Config {
        @Bean
        public SnakeEyesService snakeEyesService() {
            return Mockito.mock(SnakeEyesService.class);
        }
    }
}
