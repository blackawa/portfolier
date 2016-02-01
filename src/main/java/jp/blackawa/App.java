package jp.blackawa;

import jp.blackawa.api.BranchApi;
import jp.blackawa.api.StemApi;
import jp.blackawa.api.UserApi;
import jp.blackawa.handler.PostLoginHandler;
import jp.blackawa.handler.PostRegisterHandler;
import jp.blackawa.handler.before.ParseRequestParameterHandler;
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

    public static EntityManagerFactory emf;

    public static void main(String[] args) {
        // Define asset directory
        externalStaticFileLocation("assets");

        // Hibernateが使うDBアクセスのインターフェース
        emf = Persistence.createEntityManagerFactory("jp.blackawa.portfolier");

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

        // TODO いちいちログインするの面倒だから一回Authを切る
        // before("/user/*", new AuthenticationHandler());
        before("/user/*", new ParseRequestParameterHandler());
        UserApi.routes();
        after("/user/*", (req, res) -> res.type("application/json"));

        // TODO いちいちログインするの面倒だから一回Authを切る
        // before("/stem/*", new AuthenticationHandler());
        before("/stem/*", new ParseRequestParameterHandler());
        StemApi.routes();
        after("/stem/*", (req, res) -> res.type("application/json"));

        // TODO いちいちログインするの面倒だから一回Authを切る
        // before("/branch/*", new AuthenticationHandler());
        before("/branch/*", new ParseRequestParameterHandler());
        BranchApi.routes();
        after("/branch/*", (req, res) -> res.type("application/json"));
    }
}
