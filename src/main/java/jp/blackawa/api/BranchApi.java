package jp.blackawa.api;

import static spark.Spark.*;

public class BranchApi {
    public static void routes() {
        get("/branch", (req, res) -> "find all branch");
        get("/branch/:uuid", (req, res) -> req.params(":uuid"));
        post("/branch", (req, res) -> "create new branch");
        put("/branch/:uuid", (req, res) -> req.params(":uuid"));
        delete("/branch/:uuid", (req, res) -> req.params(":uuid"));
    }
}
