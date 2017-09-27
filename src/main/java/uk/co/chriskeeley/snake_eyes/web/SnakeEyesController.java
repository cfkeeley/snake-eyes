package uk.co.chriskeeley.snake_eyes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/snakeeyes")
class SnakeEyesController {

    final static Logger log = LoggerFactory.getLogger(SnakeEyesController.class);

    private final SnakeEyesService snakeEyesService;

    @Autowired
    public SnakeEyesController(final SnakeEyesService snakeEyesService) {
        this.snakeEyesService = snakeEyesService;
    }

    @GetMapping(path = "/play", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Outcome> generateOutcome(@RequestParam("stake") float stake) {
        if(stake == 1.00f || stake == 2.00f || stake == 10.00f)
            return new ResponseEntity<>(snakeEyesService.getOutcome(stake), HttpStatus.OK);
        else
            throw new IllegalStateException("please supply a valid stake value");
    }
}