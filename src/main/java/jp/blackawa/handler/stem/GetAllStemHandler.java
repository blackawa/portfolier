package jp.blackawa.handler.stem;

import jp.blackawa.components.GeneralDao;
import jp.blackawa.form.stem.GetAllStemResponseForm;
import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.model.Stem;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Map;

public class GetAllStemHandler extends AbstractHandler {

    public GetAllStemHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected HandlerResponse process(Map<String, String> params, String body) {
        List<Stem> stems = new GeneralDao(emf).findAll(Stem.class);
        GetAllStemResponseForm response = new GetAllStemResponseForm();
        response.setStems(stems);
        return new HandlerResponse<>(200, response);
    }
}
