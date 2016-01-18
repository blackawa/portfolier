package jp.blackawa.services;

import jp.blackawa.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.UUID;

/**
 * Provide Access to USERS Table
 */
public class UsersService {

    public static UUID insert(EntityManagerFactory entityManagerFactory, User user) {
        user.setId(UUID.randomUUID());
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user.getId();
    }

    public static User findById(EntityManagerFactory entityManagerFactory, UUID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("from User where id = ?", User.class).setParameter(1, id).getSingleResult();
    }

    public static User findByEmail(EntityManagerFactory entityManagerFactory, String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("from User where email = ?", User.class).setParameter(1, email).getSingleResult();
    }

    public static List<User> findAll(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("from User", User.class).getResultList();
    }
}
