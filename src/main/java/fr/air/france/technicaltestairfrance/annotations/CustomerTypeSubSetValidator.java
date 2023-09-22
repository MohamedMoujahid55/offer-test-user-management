package fr.air.france.technicaltestairfrance.annotations;

import fr.air.france.technicaltestairfrance.enums.UserGender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

/**
 * This is a sample Spring class.
 *
 * <p>This class permit to create Custom logic for UserGender enum validation.
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
public class CustomerTypeSubSetValidator implements ConstraintValidator<CustomerTypeSubset, UserGender> {
    private UserGender[] subset;

    @Override
    public void initialize(CustomerTypeSubset constraint) {
        this.subset = constraint.anyOf();
    }

    /**
     * @param userGender
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(UserGender value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
