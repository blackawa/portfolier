package jp.blackawa.services;

import java.util.List;
import java.util.UUID;

public interface Service<T> {

    UUID insert(T t);
    List<T> findAll();
    T findById(UUID id);
    T findBy(String column, Object value);
    UUID update(T t);
    T delete(UUID id);
}
