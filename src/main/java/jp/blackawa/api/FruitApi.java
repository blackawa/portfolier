package jp.blackawa.api;

import static spark.Spark.*;

public class FruitApi {
    public static void routes() {
        get("/fruit", (req, res) -> "find all fruit");
        get("/fruit/:uuid", (req, res) -> req.params(":uuid"));
        post("/fruit", (req, res) -> "create new fruit");
        put("/fruit/:uuid", (req, res) -> req.params(":uuid"));
        delete("/fruit/:uuid", (req, res) -> req.params(":uuid"));
    }
}
