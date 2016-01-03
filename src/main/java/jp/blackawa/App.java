package jp.blackawa;

import com.iciql.Db;
import jp.blackawa.model.User;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.get;

/**
 * Awake Application
 */
public class App {
    public static void main(String[] args) {
        // Define asset directory
        externalStaticFileLocation("assets");

        // Define Database Connection
        try (Db db = Db.open("jdbc:h2:file:./target/iciql", "sa", "sa")) {
            User user = new User(1L, "HereIAm!");
            db.insert(user);
            List<User> users = db.from(new User()).select();
            for (User u : users) System.out.println(u.name);
        }

        // index page
        get("/", (req, res) -> {
            return new ModelAndView(new HashMap<>(), "index");
        }, new ThymeleafTemplateEngine());
    }
}
