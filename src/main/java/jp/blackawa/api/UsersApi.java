package jp.blackawa.api;

import com.iciql.Db;
import jp.blackawa.model.User;
import jp.blackawa.services.UsersService;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import java.util.List;

import static spark.Spark.*;

/**
 * Provide JSON API interface
 */
public class UsersApi {
    public static void apis() {
        before("/users", (req, res) -> {
            if (null == req.session().attribute("userId")) {
                halt(403, "you need authentication first.");
            }
        });

        get("/users", (req, res) -> {
            List<User> usersForUsers;
            try (Db db = Db.open("jdbc:h2:file:./target/iciql", "sa", "sa")) {
                usersForUsers = db.from(new User()).select();
                db.close();
            }
            res.type("application/json");
            return JSON.encode(usersForUsers);
        });

        get("/users/:id", (req, res) -> {
            Long id = Long.valueOf(req.params(":id"));
            User userForUsers = new User();
            try (Db db = Db.open("jdbc:h2:file:./target/iciql", "sa", "sa")) {
                userForUsers = db.from(userForUsers).where(userForUsers.id).is(id).selectFirst();
            }
            return JSON.encode(userForUsers);
        });

        post("/users", (req, res) -> {
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
