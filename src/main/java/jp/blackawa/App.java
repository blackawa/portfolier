package jp.blackawa;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

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
    }
}
