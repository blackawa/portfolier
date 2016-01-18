package jp.blackawa;

import jp.blackawa.api.LoginApi;
import jp.blackawa.api.UserApi;
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
    public static void main(String[] args) {
        // Define asset directory
        externalStaticFileLocation("assets");

        // Hibernateが使うDBアクセスのインターフェース
        EntityManagerFactory em = Persistence.createEntityManagerFactory("jp.blackawa.portfolier");

        // Define routing: /
        get("/", (req, res) -> {
            return new ModelAndView(new HashMap<>(), "index");
        }, new ThymeleafTemplateEngine());

        // Define routing: /register
        post("/register", new PostRegisterHandler(em));

        // Define routing: /login
        LoginApi.routes(em);

        // Define routing: /user
        UserApi.routes(em);

        // 終了処理
//        entityManagerFactory.close();
    }
}
