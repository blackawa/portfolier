package jp.blackawa;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

/**
 * Awake Application
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("src/main/webapp/public");
        get("/", (req, res) -> "connected!");
    }
}
