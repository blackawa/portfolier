package jp.blackawa.api;

import jp.blackawa.model.User;
import jp.blackawa.services.UsersService;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class LoginApi {
    public static void routes() {
        get("/login", (req, res) -> {
            return new ModelAndView(new HashMap<>(), "login");
        }, new ThymeleafTemplateEngine());

        // login action
        post("/login", (req, res) -> {
            String email = req.queryParams("email");
            String password = req.queryParams("password");
            User userForLogin = UsersService.findByEmail(email);

            if (userForLogin != null && userForLogin.password.equals(password)) {
                // login success
                req.session().attribute("userId", userForLogin.id);
                res.status(200);
                req.session(true);
                res.redirect("/");
                return "";
            } else {
                res.status(400);
                res.redirect("/login");
                return "";
            }
        });
    }
}
