package jp.blackawa.handler.stem;

import jp.blackawa.components.GeneralDao;
import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.model.Stem;
import net.arnx.jsonic.JSON;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.UUID;

public class GetStemHandler extends AbstractHandler {

    public GetStemHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
             String param = request.params(":uuid");
            UUID uuid;
            try {
                uuid = UUID.fromString(param);
            } catch (IllegalArgumentException e) {
                // Catch Illegal Uri
                return null;
            }
            Stem result = new GeneralDao(emf).findById(Stem.class, uuid);
            return JSON.encode(result);
    }
}
