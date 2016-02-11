package jp.blackawa.handler.stem;

import jp.blackawa.components.GeneralDao;
import jp.blackawa.form.DeleteForm;
import jp.blackawa.form.stem.DeleteStemResponseForm;
import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.model.Stem;
import net.arnx.jsonic.JSON;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Map;

public class DeleteStemHandler extends AbstractHandler {
    public DeleteStemHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected HandlerResponse process(Map<String, String> params, String body) {
        DeleteForm form = JSON.decode(body, DeleteForm.class);
        DeleteStemResponseForm response = new DeleteStemResponseForm();
        try {
            Stem stem = new GeneralDao(emf).delete(Stem.class, form);
            response.setStem(stem);
        } catch (NoResultException e) {
            response.setErrors(new ArrayList<String>() {{
                // TODO メッセージの出処を一本化する
                add("No Record Exists");
            }});
            return new HandlerResponse<>(400, response);
        }
        return new HandlerResponse<>(204, response);
    }
}
