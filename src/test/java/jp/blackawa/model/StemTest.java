package jp.blackawa.model;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

public class StemTest {
    private static Validator validator;
    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void stemIsValid() {
        Stem stem = new Stem(UUID.fromString("00e89d05-4aff-4546-8795-2a52e588a629"), "my-stem", null);
        System.out.println(stem.getId());
        Set<ConstraintViolation<Stem>> constraintViolations = validator.validate(stem);
        assertEquals(0, constraintViolations.size());
    }
}