package jp.blackawa.handler;

import spark.Route;

import javax.persistence.EntityManagerFactory;

public abstract class AbstractHandler implements Route {
    public AbstractHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }
    protected EntityManagerFactory emf;
}
