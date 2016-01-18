package jp.blackawa.handler;

import com.iciql.Db;
import spark.Route;

public abstract class AbstractHandler implements Route {
    protected Db iciqlDb;

    public AbstractHandler(Db iciqlDb) {
        this.iciqlDb = iciqlDb;
    }
}
