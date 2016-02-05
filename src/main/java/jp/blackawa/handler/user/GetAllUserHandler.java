package jp.blackawa.handler.user;

import jp.blackawa.handler.AbstractHandler;
import jp.blackawa.services.UserService;
import net.arnx.jsonic.JSON;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;

public class GetAllUserHandler extends AbstractHandler {
    public GetAllUserHandler(EntityManagerFactory emf) {
        super(emf);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        UserService userService = new UserService(emf);
        return JSON.encode(userService.findAll());
    }
}
