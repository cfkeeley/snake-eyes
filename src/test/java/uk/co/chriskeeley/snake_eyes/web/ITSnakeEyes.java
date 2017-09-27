package uk.co.chriskeeley.snake_eyes.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.ClientBuilder;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ITSnakeEyes {

    @Test
    public void expectSnakeEyesServerJsonResponse() throws IOException {

        final ClientResponse response = ClientBuilder.newClient()
        .target("http://localhost:8080").path("snakeeyes/play")
        .queryParam("stake",2.00)
        .request().get(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);

        final String responseBody = response.readEntity(String.class);

        final ObjectMapper mapper = new ObjectMapper();
        final Outcome outcome = mapper.readValue(responseBody, Outcome.class);

        assertThat(outcome.getStake()).as("expected output to be same as input").isEqualTo(2.00f);
    }

    @Test(expected = InternalServerErrorException.class)
    public void expectSnakeEyesServerExceptionResponse() {

        final ClientResponse response = ClientBuilder.newClient()
                .target("http://localhost:8080").path("snakeeyes/play")
                .queryParam("stake",20.00)
                .request().get(ClientResponse.class);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
