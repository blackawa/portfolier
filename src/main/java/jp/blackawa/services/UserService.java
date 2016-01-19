package jp.blackawa.services;

import jp.blackawa.App;
import jp.blackawa.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.UUID;

/**
 * Provide Access to USERS Table
 */
public class UserService implements Service<User> {

    @Override
    public UUID insert(User user) {
        user.setId(UUID.randomUUID());
        EntityManager entityManager = App.em.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user.getId();
    }

    @Override
    public User findById(UUID id) {
        EntityManager entityManager = App.em.createEntityManager();
        return entityManager.createQuery("from User where id = ?", User.class).setParameter(1, id).getSingleResult();
    }

    @Override
    public User findBy(String column, Object value) {
        EntityManager entityManager = App.em.createEntityManager();
        return entityManager.createQuery("from User where " + column + " = ?", User.class).setParameter(1, value).getSingleResult();
    }

    @Override
    public List<User> findAll() {
        EntityManager entityManager = App.em.createEntityManager();
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public UUID update(User user) {
        // スタブメソッド
        System.out.println("This method is not implemented yet.");
        return null;
    }

    @Override
    public User delete(UUID id) {
        // スタブメソッド
        System.out.println("This method is not implemented yet.");
        return null;
    }
}
