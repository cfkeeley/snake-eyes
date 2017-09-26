package uk.co.chriskeeley.snake_eyes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/snakeeyes")
class SnakeEyesController {

    private final SnakeEyesService outcomeService;

    @Autowired
    public SnakeEyesController(final SnakeEyesService outcomeService) {
        this.outcomeService = outcomeService;
    }

    @GetMapping(path = "/play", produces = "application/json; charset=UTF-8")
    public Outcome generateOutcome(@RequestParam("stake") Double stake) {
        return outcomeService.getOutcome(stake);
    }
}