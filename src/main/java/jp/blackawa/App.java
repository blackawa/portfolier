package jp.blackawa;

import jp.blackawa.api.UsersApi;
import jp.blackawa.model.User;
import jp.blackawa.services.UsersService;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

import static spark.Spark.*;

/**
 * Awake Application
 */
public class App {
    public static void main(String[] args) {
        // Define asset directory
        externalStaticFileLocation("assets");

        // index page
        get("/", (req, res) -> {
            return new ModelAndView(new HashMap<>(), "index");
        }, new ThymeleafTemplateEngine());

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

        // Call Users resource API
        UsersApi.apis();
    }
}
