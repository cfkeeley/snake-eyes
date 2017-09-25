package uk.co.chriskeeley.snake_eyes.web;

import org.springframework.stereotype.Service;

import javax.ws.rs.client.ClientBuilder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class SnakeEyesService {

    public Outcome getOutcome() {
        return new Outcome(1,1, 1.00, 30.00, "snake eyes");
    }

    private String getRandomNumbersForDiceRoll() {
        return ClientBuilder.newClient()
                .target("https://www.random.org").path("integers")
                .queryParam("num",2)
                .queryParam("min",1)
                .queryParam("max",6)
                .queryParam("col",2)
                .queryParam("base",10)
                .queryParam("format","plain")
                .request().get().readEntity(String.class);
    }
}
