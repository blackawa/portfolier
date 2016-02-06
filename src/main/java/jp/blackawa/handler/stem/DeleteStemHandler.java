package jp.blackawa.handler.stem;

import jp.blackawa.components.GeneralDao;
import jp.blackawa.form.DeleteForm;
import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.model.Stem;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.Map;

public class DeleteStemHandler extends AbstractHandler {
    public DeleteStemHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected HandlerResponse process(Map<String, String> params, String body) {
        // FIXME Mock
        return null;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        DeleteForm form;
        try {
            form = JSON.decode(request.body());
        } catch (JSONException e) {
            // Failed to Parse JSON
            return null;
        }
        return JSON.encode(new GeneralDao(emf).delete(Stem.class, form));
    }
}
