package jp.blackawa;

import com.iciql.Db;
import jp.blackawa.model.User;
import net.arnx.jsonic.JSON;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;

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

        // index page
        get("/", (req, res) -> {
            return new ModelAndView(new HashMap<>(), "index");
        }, new ThymeleafTemplateEngine());

        // Define Database Connection

        get("/login", (req, res) -> {
            return new ModelAndView(new HashMap<>(), "login");
        }, new ThymeleafTemplateEngine());

        get("/users", (req, res) -> {
            if (null == req.session().attribute("userId")) {
                res.status(403);
                return "you need authentication first";
            }
            List<User> usersForUsers;
            try (Db db = Db.open("jdbc:h2:file:./target/iciql", "sa", "sa")) {
                usersForUsers = db.from(new User()).select();
                db.close();
            }
            res.type("application/json");
            return JSON.encode(usersForUsers);
        });

        // login action
        post("/login", (req, res) -> {
            String email = req.queryParams("email");
            String password = req.queryParams("password");
            User userForLogin = new User();
            try (Db db = Db.open("jdbc:h2:file:./target/iciql", "sa", "sa")) {
                userForLogin = db.from(userForLogin).where(userForLogin.email).is(email).selectFirst();
                db.close();
            }
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
                try (Db db = Db.open("jdbc:h2:file:./target/iciql", "sa", "sa")) {
                    db.insert(userForRegister);
                    db.close();
                }
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
