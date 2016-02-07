package jp.blackawa.form;

import jp.blackawa.form.stem.CreateStemRequestForm;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateStemRequestFormTest {


    private static Validator validator;
    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void formIsValid() {
        CreateStemRequestForm form = new CreateStemRequestForm("valid name");
        Set<ConstraintViolation<CreateStemRequestForm>> constraintViolations = validator.validate(form);
        assertThat("No Error Occurred", 0, is(constraintViolations.size()));
    }

    @Test
    public void formIsInvalid() {
        CreateStemRequestForm form = new CreateStemRequestForm(null);
        Set<ConstraintViolation<CreateStemRequestForm>> constraintViolations = validator.validate(form);
        assertThat("NotNull Validation Error", 1, is(constraintViolations.size()));
    }
}
