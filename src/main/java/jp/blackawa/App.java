package jp.blackawa;

import com.iciql.Db;
import jp.blackawa.api.LoginApi;
import jp.blackawa.api.UserApi;
import jp.blackawa.handler.PostRegisterHandler;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Awake Application
 */
public class App {
    public static void main(String[] args) {
        // Define asset directory
        externalStaticFileLocation("assets");

        // Define Database Configurations
        Db iciqlDb = Db.open("jdbc:h2:file:./target/iciql", "sa", "sa");

        // Define routing: /
        get("/", (req, res) -> {
            return new ModelAndView(new HashMap<>(), "index");
        }, new ThymeleafTemplateEngine());

        // Define routing: /register
        post("/register", new PostRegisterHandler(iciqlDb));

        // Define routing: /login
        LoginApi.routes();

        // Define routing: /user
        UserApi.routes();
    }
}
