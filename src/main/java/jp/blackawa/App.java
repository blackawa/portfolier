package jp.blackawa;

import jp.blackawa.api.UserApi;
import jp.blackawa.handler.AuthenticationHandler;
import jp.blackawa.handler.PostLoginHandler;
import jp.blackawa.handler.PostRegisterHandler;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;

import static spark.Spark.*;

/**
 * Awake Application
 */
public class App {

    public static EntityManagerFactory em;

    public static void main(String[] args) {
        // Define asset directory
        externalStaticFileLocation("assets");

        // Hibernateが使うDBアクセスのインターフェース
        em = Persistence.createEntityManagerFactory("jp.blackawa.portfolier");

        get("/", (req, res) -> {
            // トップ画面を返却する
            return new ModelAndView(new HashMap<>(), "index");
        }, new ThymeleafTemplateEngine());

        post("/register", new PostRegisterHandler());

        get("/login", (req, res) -> {
            // ログイン画面を返却する
            return new ModelAndView(new HashMap<>(), "login");
        }, new ThymeleafTemplateEngine());
        post("/login", new PostLoginHandler());

        before("/user/*", new AuthenticationHandler());
        UserApi.routes(em);
    }
}
