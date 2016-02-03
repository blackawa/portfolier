package jp.blackawa.api;

import jp.blackawa.components.GeneralDao;
import jp.blackawa.form.DeleteForm;
import jp.blackawa.model.Stem;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static spark.Spark.*;

public class StemApi {
    public static void routes() {

        get("/stem", getStem());
        get("/stem/:uuid", getStemUuid());

        post("/stem", (req, res) -> {
            Stem stem;
            try {
                stem = JSON.decode(req.body(), Stem.class);
            } catch (JSONException e) {
                // Failed to Parse JSON
                return null;
            }
            Map<String, UUID> result = new HashMap<>();
            UUID id = GeneralDao.insert(stem);
            result.put("id", id);
            return JSON.encode(result);
        });

        put("/stem", (req, res) -> {
            Stem stem;
            try {
                stem = JSON.decode(req.body(), Stem.class);
            } catch (JSONException e) {
                // Failed to Parse JSON
                return null;
            }
            Map<String, UUID> result = new HashMap<>();
            UUID id = GeneralDao.update(stem);
            result.put("id", id);
            return JSON.encode(result);
        });

        delete("/stem", (req, res) -> {
            DeleteForm form;
            try {
                form = JSON.decode(req.body());
            } catch (JSONException e) {
                // Failed to Parse JSON
                return null;
            }
            return JSON.encode(GeneralDao.delete(Stem.class, form));
        });
    }

    public static Route getStem() {
        return (req, res) -> {
            List<Stem> result = GeneralDao.findAll(Stem.class);
            return JSON.encode(result);
        };
    }

    public static Route getStemUuid() {
        return (req, res) -> {
            String param = req.params(":uuid");
            UUID uuid;
            try {
                uuid = UUID.fromString(param);
            } catch (IllegalArgumentException e) {
                // Catch Illegal Uri
                return null;
            }
            Stem result = GeneralDao.findById(Stem.class, uuid);
            return JSON.encode(result);
        };
    }
}
