package uk.co.chriskeeley.snake_eyes.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StakeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Stake.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final Stake stake = (Stake)target;
        if(stake.getStake() != 1.00 || stake.getStake() != 2.00 || stake.getStake() != 10.00) {
            errors.rejectValue("stake","please choose a valid stake value");
        }
    }
}
