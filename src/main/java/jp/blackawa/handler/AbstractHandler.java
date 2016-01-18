package jp.blackawa.handler;

import spark.Route;

import javax.persistence.EntityManagerFactory;

public abstract class AbstractHandler implements Route {
    protected EntityManagerFactory entityManagerFactory;

    public AbstractHandler(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
