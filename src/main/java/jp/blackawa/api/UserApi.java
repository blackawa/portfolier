package jp.blackawa.api;

import jp.blackawa.model.User;
import jp.blackawa.services.UsersService;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import static spark.Spark.*;

/**
 * Provide JSON API interface
 */
public class UserApi {
    public static void routes() {
        before("/user/*", (req, res) -> {
            if (null == req.session().attribute("userId")) {
                halt(403, "you need authentication first.");
            }
        });

        get("/user", (req, res) -> {
            res.type("application/json");
            return JSON.encode(UsersService.findAll());
        });

        get("/user/:id", (req, res) -> {
            Long id = Long.valueOf(req.params(":id"));
            res.type("application/json");
            return JSON.encode(UsersService.findById(id));
        });

        post("/user", (req, res) -> {
            User user = new User();
            try {
                user = JSON.decode(req.body(), User.class);
                System.out.println("name: " + user.name + " email: " + user.email + " password: " + user.password);
                boolean isCreated = UsersService.insert(user);
                if (!isCreated) halt(400, "Database error occurred... sorry");
            } catch (JSONException e) {
                res.status(400);
                return "illegal format";
            }
            res.status(201);
            return user.id;
        });
    }
}
