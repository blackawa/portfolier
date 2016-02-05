package jp.blackawa.handler.stem;

import jp.blackawa.components.GeneralDao;
import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.model.Stem;
import net.arnx.jsonic.JSON;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class GetAllStemHandler extends AbstractHandler {

    public GetAllStemHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        List<Stem> result = new GeneralDao(emf).findAll(Stem.class);
        return JSON.encode(result);
    }
}
