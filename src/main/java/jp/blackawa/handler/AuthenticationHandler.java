package jp.blackawa.handler;

import spark.Filter;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

public class AuthenticationHandler implements Filter {
    @Override
    public void handle(Request req, Response res) throws Exception {
        if (null == req.session().attribute("userId")) {
            halt(403, "you need authentication first.");
        }
    }
}
