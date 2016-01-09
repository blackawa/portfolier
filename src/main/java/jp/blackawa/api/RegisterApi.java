package jp.blackawa.api;

import jp.blackawa.model.User;
import jp.blackawa.services.UsersService;

import static spark.Spark.post;

public class RegisterApi {
    public static void routes() {
        post("/register", (req, res) -> {
            User userForRegister = new User(
                    req.queryParams("name"),
                    req.queryParams("email"),
                    req.queryParams("password")
            );
            if (userForRegister.isValid()) {
                UsersService.insert(userForRegister);
                res.status(200);
                res.redirect("/");
                return "";
            } else {
                // illegal input
                res.status(400);
                res.redirect("/login");
                return "";
            }
        });
    }
}
