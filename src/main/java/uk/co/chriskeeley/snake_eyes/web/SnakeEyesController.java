package uk.co.chriskeeley.snake_eyes.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/snakeeyes")
class SnakeEyesController {
    @GetMapping(path = "/play")
    public Outcome generateOutcome() {
        return new Outcome(1,1, 1.00, 30.00, "snake eyes");
    }
}
