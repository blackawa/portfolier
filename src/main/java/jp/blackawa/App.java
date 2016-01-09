package jp.blackawa;

import jp.blackawa.api.LoginApi;
import jp.blackawa.api.RegisterApi;
import jp.blackawa.api.UsersApi;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.get;

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

        // Define routing: /register
        RegisterApi.routes();

        // Define routing: /login
        LoginApi.routes();

        // Define routing: /users
        UsersApi.routes();
    }
}
