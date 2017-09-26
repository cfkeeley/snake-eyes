package uk.co.chriskeeley.snake_eyes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/snakeeyes")
class SnakeEyesController {

    private final SnakeEyesService outcomeService;

    @Autowired
    public SnakeEyesController(final SnakeEyesService outcomeService) {
        this.outcomeService = outcomeService;
    }

    @GetMapping(path = "/play")
    public Outcome generateOutcome() {
        return outcomeService.getOutcome(1.00);
    }
}