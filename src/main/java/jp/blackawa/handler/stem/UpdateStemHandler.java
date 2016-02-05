package jp.blackawa.handler.stem;

import jp.blackawa.components.GeneralDao;
import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.model.Stem;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UpdateStemHandler extends AbstractHandler {
    public UpdateStemHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Stem stem;
        try {
            stem = JSON.decode(request.body(), Stem.class);
        } catch (JSONException e) {
            // Failed to Parse JSON
            return null;
        }
        Map<String, UUID> result = new HashMap<>();
        UUID id = new GeneralDao(emf).update(stem);
        result.put("id", id);
        return JSON.encode(result);
    }
}
