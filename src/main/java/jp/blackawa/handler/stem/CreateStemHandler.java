package jp.blackawa.handler.stem;

import jp.blackawa.form.stem.CreateStemRequestForm;
import jp.blackawa.form.stem.CreateStemResponseForm;
import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.model.Stem;
import net.arnx.jsonic.JSON;

import javax.persistence.EntityManagerFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateStemHandler extends AbstractHandler {
    public CreateStemHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected HandlerResponse process(Map<String, String> params, String body) {
        CreateStemRequestForm form = JSON.decode(body, CreateStemRequestForm.class);
        CreateStemResponseForm response = new CreateStemResponseForm();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<CreateStemRequestForm>> constraintViolations = validator.validate(form);

        if (constraintViolations.size() != 0) {
            List<String> errors = constraintViolations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            response.setErrors(errors);
            return new HandlerResponse<>(400, true, response);
        }

        UUID id = UUID.randomUUID();
        new Stem(id, form.getName(), null);
        response.setId(id);
        return new HandlerResponse<>(200, true, response);
    }
}
