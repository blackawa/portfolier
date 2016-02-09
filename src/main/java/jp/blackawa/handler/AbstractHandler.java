package jp.blackawa.handler;

import net.arnx.jsonic.JSON;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.persistence.EntityManagerFactory;
import java.util.Map;

public abstract class AbstractHandler implements Route {
    public AbstractHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }
    protected EntityManagerFactory emf;

    protected abstract HandlerResponse process(Map<String, String> params, String body);

    @Override
    public Object handle(Request request, Response response) throws Exception {
        HandlerResponse handlerResponse = process(request.params(), request.body());

        response.status(handlerResponse.getStatusCode());
        return JSON.encode(handlerResponse.getContent());
    }
}
