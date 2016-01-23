package jp.blackawa.api;

import jp.blackawa.model.Stem;
import jp.blackawa.services.Service;
import jp.blackawa.services.StemService;
import net.arnx.jsonic.JSON;

import javax.persistence.EntityManagerFactory;

import java.util.UUID;

import static spark.Spark.*;

public class StemApi {
    public static void routes(EntityManagerFactory em) {
        Service service = new StemService();

        get("/stem", (req, res) -> JSON.encode(service.findAll()));
        get("/stem/:uuid", (req, res) -> JSON.encode(service.findById(UUID.fromString(req.params(":uuid")))));
        post("/stem", (req, res) -> service.insert(JSON.decode(req.body(), Stem.class)));
        put("/stem", (req, res) -> service.update(JSON.decode(req.body(), Stem.class)));
        delete("/stem", (req, res) -> service.delete(JSON.decode(req.body(), Stem.class).getId()));
    }
}
