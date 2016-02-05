package jp.blackawa.handler.user;

import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.services.UserService;
import net.arnx.jsonic.JSON;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.UUID;

public class GetUserHandler extends AbstractHandler {
    public GetUserHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        UserService userService = new UserService(emf);
        UUID id = UUID.fromString(request.params(":id"));
        return JSON.encode(userService.findById(id));
    }
}
