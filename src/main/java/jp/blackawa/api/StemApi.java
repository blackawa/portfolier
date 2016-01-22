package jp.blackawa.api;

import javax.persistence.EntityManagerFactory;

import static spark.Spark.*;

public class StemApi {
    public static void routes(EntityManagerFactory em) {
        get("/stem", (req, res) -> "find all stem");
        get("/stem/:uuid", (req, res) -> req.params(":uuid"));
        post("/stem", (req, res) -> "create new stem");
        put("/stem/:uuid", (req, res) -> req.params(":uuid"));
        delete("/stem/:uuid", (req, res) -> req.params(":uuid"));
    }
}
