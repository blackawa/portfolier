package jp.blackawa.handler;

import com.iciql.Db;
import jp.blackawa.model.User;
import jp.blackawa.services.UsersService;
import spark.Request;
import spark.Response;

public class PostRegisterHandler extends AbstractHandler {

    public PostRegisterHandler(Db iciqlDb) {
        super(iciqlDb);
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
                request.queryParams("name"),
                request.queryParams("email"),
                request.queryParams("password")
        );

        if (userForRegister.isValid()) {
            UsersService.insert(userForRegister);
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
