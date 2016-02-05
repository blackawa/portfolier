package jp.blackawa.handler;

import jp.blackawa.model.User;
import jp.blackawa.services.Service;
import jp.blackawa.services.UserService;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManagerFactory;
import java.util.UUID;

public class PostRegisterHandler extends AbstractHandler {

    public PostRegisterHandler (EntityManagerFactory emf) {
        super(emf);
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
        User user = new User(
                UUID.randomUUID(),
                request.queryParams("name"),
                request.queryParams("email"),
                request.queryParams("password")
        );

        Service<User> userService = new UserService(emf);
        if (user.isValid()) {
            userService.insert(user);
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
