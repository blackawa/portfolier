package jp.blackawa.handler.user;

import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.services.UserService;
import net.arnx.jsonic.JSON;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.Map;
import java.util.UUID;

public class GetUserHandler extends AbstractHandler {
    public GetUserHandler(EntityManagerFactory emf) {
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
        UUID id = UUID.fromString(request.params(":id"));
        return JSON.encode(userService.findById(id));
    }
}
