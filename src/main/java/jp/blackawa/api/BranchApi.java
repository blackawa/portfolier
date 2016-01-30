package jp.blackawa.api;

import jp.blackawa.model.Branch;
import jp.blackawa.services.BranchService;
import jp.blackawa.services.Service;
import net.arnx.jsonic.JSON;

import java.util.UUID;

import static spark.Spark.*;

public class BranchApi {
    public static void routes() {
        Service<Branch> service = new BranchService();
        get("/branch", (req, res) -> service.findAll());
        get("/branch/:uuid", (req, res) -> service.findById(UUID.fromString(req.params(":uuid"))));
        post("/branch", (req, res) -> service.insert(JSON.decode(req.body(), Branch.class)));
        put("/branch", (req, res) -> service.update(JSON.decode(req.body(), Branch.class)));
        delete("/branch", (req, res) -> service.delete(JSON.decode(req.body(), Branch.class).getId()));
    }
}
