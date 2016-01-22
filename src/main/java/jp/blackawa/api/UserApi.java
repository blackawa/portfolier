package jp.blackawa.api;

import jp.blackawa.model.User;
import jp.blackawa.services.Service;
import jp.blackawa.services.UserService;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import javax.persistence.EntityManagerFactory;
import java.util.UUID;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Provide JSON API interface
 */
public class UserApi {
    public static void routes(EntityManagerFactory em) {

        Service userService = new UserService();

        get("/user", (req, res) -> {
            res.type("application/json");
            return JSON.encode(userService.findAll());
        });

        get("/user/:id", (req, res) -> {
            UUID id = UUID.fromString(req.params(":id"));
            res.type("application/json");
            return JSON.encode(userService.findById(id));
        });

        post("/user", (req, res) -> {
            User user = new User();
            UUID createdUserId;
            try {
                user = JSON.decode(req.body(), User.class);
                createdUserId = userService.insert(user);
            } catch (JSONException e) {
                res.status(400);
                return "illegal format";
            }
            res.status(201);
            return createdUserId.toString();
        });
    }
}
