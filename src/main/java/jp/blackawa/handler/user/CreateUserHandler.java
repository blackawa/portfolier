package jp.blackawa.handler.user;

import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.handler.HandlerResponse;
import jp.blackawa.model.User;
import jp.blackawa.services.UserService;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.Map;
import java.util.UUID;

public class CreateUserHandler extends AbstractHandler {
    public CreateUserHandler(EntityManagerFactory emf) {
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
        User user = new User();
        UUID createdUserId;
        Map<String, UUID> result;
        try {
            user = JSON.decode(request.body(), User.class);
            result = userService.insert(user);
        } catch (JSONException e) {
            response.status(400);
            return "illegal format";
        }
        response.status(201);
        return JSON.encode(result);
    }
}
