package jp.blackawa.handler;

import jp.blackawa.model.User;
import jp.blackawa.services.UsersService;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.UUID;

public class PostRegisterHandler extends AbstractHandler {

    public PostRegisterHandler(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    /**
     * Sparkが提供するRouteインターフェースの実装
     * @param request HttpRequest
     * @param response HttpResponse
     * @return Response Value
     * @throws Exception すべての例外
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        User userForRegister = new User(
                UUID.randomUUID(),
                request.queryParams("name"),
                request.queryParams("email"),
                request.queryParams("password")
        );

        if (userForRegister.isValid()) {
            UsersService.insert(entityManagerFactory, userForRegister);
            response.status(200);
            response.redirect("/");
            return "";
        } else {
            // illegal input
            response.status(400);
            response.redirect("/login");
            return "";
        }
    }
}
