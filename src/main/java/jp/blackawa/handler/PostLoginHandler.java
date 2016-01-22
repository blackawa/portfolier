package jp.blackawa.handler;

import jp.blackawa.model.User;
import jp.blackawa.services.Service;
import jp.blackawa.services.UserService;
import spark.Request;
import spark.Response;

public class PostLoginHandler extends AbstractHandler {
    @Override
    public Object handle(Request req, Response res) throws Exception {
        Service userService = new UserService();
        String email = req.queryParams("email");
        String password = req.queryParams("password");
        User user = (User) userService.findBy("email", email);

        if (user != null && user.getPassword().equals(password)) {
            // login success
            req.session().attribute("userId", user.getId());
            res.status(200);
            req.session(true);
            res.redirect("/");
            return "";
        } else {
            res.status(400);
            res.redirect("/login");
            return "";
        }
    }
}
