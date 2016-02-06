package jp.blackawa.handler.stem;

import jp.blackawa.form.stem.CreateStemForm;
import jp.blackawa.form.stem.CreateStemResponseForm;
import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.model.Stem;
import net.arnx.jsonic.JSON;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreateStemHandler extends AbstractHandler {
    public CreateStemHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected HandlerResponse process(Map<String, String> params, String body) {
        CreateStemForm form = JSON.decode(body, CreateStemForm.class);

        UUID id = UUID.randomUUID();
        new Stem(id, form.getName(), null);
        CreateStemResponseForm response = new CreateStemResponseForm();
        response.setId(id);
        return new HandlerResponse<>(200, true, response);
    }
}
