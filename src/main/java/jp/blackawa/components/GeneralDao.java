package jp.blackawa.components;

import java.util.List;
import java.util.UUID;

public class GeneralDao {
    public static <T> UUID insert(T entity) {
        return null;
    }

    public static <T> List<T> findAll(final Class<T> entityClass) { return null; }

    public static <T> T findById(final Class<T> entityClass, final UUID id) {
        return null;
    }

    public static <T> T findBy(final Class<T> entityClass, final String... params) {
        return null;
    }

    public static <T> UUID update(T entity) {
        return null;
    }

    public static <T> T delete(Class<T> entityClass, UUID id) {
        return null;
    }
}

