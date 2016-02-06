package jp.blackawa.handler.user;

import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.services.UserService;
import net.arnx.jsonic.JSON;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.Map;

public class GetAllUserHandler extends AbstractHandler {
    public GetAllUserHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    protected HandlerResponse process(Map<String, String> params, String body) {
        // FIXME Mock
        return null;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        UserService userService = new UserService(emf);
        return JSON.encode(userService.findAll());
    }
}
