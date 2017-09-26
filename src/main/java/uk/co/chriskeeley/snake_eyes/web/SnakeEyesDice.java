package uk.co.chriskeeley.snake_eyes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.ClientBuilder;

@Service
public class SnakeEyesDice {

    final static Logger log = LoggerFactory.getLogger(SnakeEyesDice.class);

    public String roll() {
        log.info("Rolling the dice...");
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
