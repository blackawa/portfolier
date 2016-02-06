package jp.blackawa.testutils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmfFactory {
    public static EntityManagerFactory generateEmf() {
        return Persistence.createEntityManagerFactory("jp.blackawa.portfolier");
    }
}
