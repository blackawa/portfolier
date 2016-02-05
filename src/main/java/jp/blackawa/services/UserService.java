package jp.blackawa.services;

import jp.blackawa.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Provide Access to USERS Table
 */
public class UserService implements Service<User> {

    private EntityManagerFactory emf;

    public UserService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Map<String, UUID> insert(User user) {
        user.setId(UUID.randomUUID());
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return new HashMap<String, UUID>() {{
            put("id", user.getId());
        }};
    }

    @Override
    public User findById(UUID id) {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.createQuery("from User where id = ?", User.class).setParameter(1, id).getSingleResult();
    }

    @Override
    public User findBy(String column, Object value) {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.createQuery("from User where " + column + " = ?", User.class).setParameter(1, value).getSingleResult();
    }

    @Override
    public List<User> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public Map<String, UUID> update(User user) {
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
