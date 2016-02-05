package jp.blackawa.components;

import jp.blackawa.form.DeleteForm;
import jp.blackawa.model.AbstractModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 汎用的に使える、基本動作をまとめたDaoクラス
 */
public class GeneralDao {

    private EntityManagerFactory emf;

    public GeneralDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public <T extends AbstractModel> UUID insert(T entity) {
         EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity.getId();
    }

    public <T extends AbstractModel> List<T> findAll(final Class<T> entityClass) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
    }

    public <T extends AbstractModel> T findById(final Class<T> entityClass, final UUID id) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from " + entityClass.getSimpleName() + " where id = ?", entityClass)
                .setParameter(1, id).getSingleResult();
    }

    public <T extends AbstractModel> T findBy(final Class<T> entityClass, final Map<String, String> params) {
        EntityManager em = emf.createEntityManager();
        // FIXME 複数パラメータ受け付け
        return em.createQuery("from Stem where " + params.keySet().toArray()[0] + " = ?", entityClass).setParameter(1, params.get(params.keySet().toArray()[0])).getSingleResult();
    }

    public <T extends AbstractModel> UUID update(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            findById(entity.getClass(), entity.getId());
        } catch (NoResultException e) {
            return null;
        }
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return entity.getId();
    }

    public <T extends AbstractModel> T delete(Class<T> entityClass, DeleteForm form) {
        EntityManager em = emf.createEntityManager();
        T entity;
        try {
            entity = findById(entityClass, form.getId());
        } catch (NoResultException e) {
            return null;
        }
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }
}

