package jp.blackawa.api;

import jp.blackawa.model.Stem;
import jp.blackawa.services.Service;
import jp.blackawa.services.StemService;
import net.arnx.jsonic.JSON;

import java.util.UUID;

import static spark.Spark.*;

public class StemApi {
    public static void routes() {
        Service<Stem> service = new StemService();

        get("/stem", (req, res) -> JSON.encode(service.findAll()));
        get("/stem/:uuid", (req, res) -> JSON.encode(service.findById(UUID.fromString(req.params(":uuid")))));
        post("/stem", (req, res) -> JSON.encode(service.insert(JSON.decode(req.body(), Stem.class))));
        put("/stem", (req, res) -> JSON.encode(service.update(JSON.decode(req.body(), Stem.class))));
        delete("/stem", (req, res) -> JSON.encode(service.delete(JSON.decode(req.body(), Stem.class).getId())));
    }
}
