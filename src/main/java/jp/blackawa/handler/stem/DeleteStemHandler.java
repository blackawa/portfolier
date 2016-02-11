package jp.blackawa.handler.stem;

import jp.blackawa.components.GeneralDao;
import jp.blackawa.form.DeleteForm;
import jp.blackawa.form.stem.DeleteStemResponseForm;
import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.model.Stem;
import net.arnx.jsonic.JSON;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Map;

public class DeleteStemHandler extends AbstractHandler {
    public DeleteStemHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected HandlerResponse process(Map<String, String> params, String body) {
        DeleteForm form = JSON.decode(body, DeleteForm.class);
        Stem stem = new GeneralDao(emf).delete(Stem.class, form);
        DeleteStemResponseForm response = new DeleteStemResponseForm();
        response.setStem(stem);
        return new HandlerResponse<>(204, response);
    }
}
