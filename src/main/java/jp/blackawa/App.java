package jp.blackawa;

import jp.blackawa.handler.PostLoginHandler;
import jp.blackawa.handler.PostRegisterHandler;
import jp.blackawa.handler.before.ParseRequestParameterHandler;
import jp.blackawa.handler.login.GetLoginHandler;
import jp.blackawa.handler.stem.*;
import jp.blackawa.handler.top.GetTopHandler;
import jp.blackawa.handler.user.CreateUserHandler;
import jp.blackawa.handler.user.GetAllUserHandler;
import jp.blackawa.handler.user.GetUserHandler;
import jp.blackawa.model.Branch;
import jp.blackawa.services.BranchService;
import jp.blackawa.services.Service;
import net.arnx.jsonic.JSON;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

import static spark.Spark.*;

public class App {

    // FIXME Delete me when I am not used.
    public static EntityManagerFactory emf;

    public static void main(String[] args) {
        // Define asset directory
        externalStaticFileLocation("assets");

        // Hibernateが使うDBアクセスのインターフェース
        emf = Persistence.createEntityManagerFactory("jp.blackawa.portfolier");

        get("/", new GetTopHandler(), new ThymeleafTemplateEngine());

        post("/register", new PostRegisterHandler(emf));
        get("/login", new GetLoginHandler(), new ThymeleafTemplateEngine());
        post("/login", new PostLoginHandler(emf));

        // TODO いちいちログインするの面倒だから一回Authを切る
        // before("/user/*", new AuthenticationHandler());
        before("/user/*", new ParseRequestParameterHandler());
        get("/user", new GetAllUserHandler(emf));
        get("/user/:id", new GetUserHandler(emf));
        post("/user", new CreateUserHandler(emf));
        after("/user/*", (req, res) -> res.type("application/json"));

        // TODO いちいちログインするの面倒だから一回Authを切る
        // before("/stem/*", new AuthenticationHandler());
        before("/stem/*", new ParseRequestParameterHandler());
        get("/stem", new GetAllStemHandler(emf));
        get("/stem/:uuid", new GetStemHandler(emf));
        post("/stem", new CreateStemHandler(emf));
        put("/stem", new UpdateStemHandler(emf));
        delete("/stem", new DeleteStemHandler(emf));
        after("/stem/*", (req, res) -> res.type("application/json"));

        // TODO いちいちログインするの面倒だから一回Authを切る
        // before("/branch/*", new AuthenticationHandler());
        Service<Branch> branchService = new BranchService();
        before("/branch/*", new ParseRequestParameterHandler());
        get("/branch", (req, res) -> JSON.encode(branchService.findAll()));
        get("/branch/:uuid", (req, res) -> JSON.encode(branchService.findById(UUID.fromString(req.params(":uuid")))));
        post("/branch", (req, res) -> JSON.encode(branchService.insert(JSON.decode(req.body(), Branch.class))));
        put("/branch", (req, res) -> JSON.encode(branchService.update(JSON.decode(req.body(), Branch.class))));
        delete("/branch", (req, res) -> JSON.encode(branchService.delete(JSON.decode(req.body(), Branch.class).getId())));
        after("/branch/*", (req, res) -> res.type("application/json"));
    }
}
