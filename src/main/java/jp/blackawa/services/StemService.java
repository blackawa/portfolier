package jp.blackawa.services;

import jp.blackawa.App;
import jp.blackawa.model.Stem;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StemService implements Service<Stem> {
    @Override
    public Map<String, UUID> insert(Stem stem) {
        EntityManager em = App.emf.createEntityManager();
        stem.setId(UUID.randomUUID());
        em.getTransaction().begin();
        em.persist(stem);
        em.getTransaction().commit();
        em.close();
        return new HashMap<String, UUID>() {{
            put("id", stem.getId());
        }};
    }

    @Override
    public List<Stem> findAll() {
        EntityManager em = App.emf.createEntityManager();
        return em.createQuery("from Stem", Stem.class).getResultList();
    }

    @Override
    public Stem findById(UUID id) {
        EntityManager em = App.emf.createEntityManager();
        return em.createQuery("from Stem where id = ?", Stem.class).setParameter(1, id).getSingleResult();
    }

    @Override
    public Stem findBy(String column, Object value) {
        EntityManager em = App.emf.createEntityManager();
        return em.createQuery("from Stem where " + column + " = ?", Stem.class).setParameter(1, value).getSingleResult();
    }

    @Override
    public Map<String, UUID> update(Stem stem) {
        EntityManager em = App.emf.createEntityManager();
        try {
            findById(stem.getId());
        } catch (NoResultException e) {
            return null;
        }
        em.getTransaction().begin();
        em.merge(stem);
        em.getTransaction().commit();
        em.close();
        return new HashMap<String, UUID>() {{
            put("id", stem.getId());
        }};
    }

    @Override
    public Stem delete(UUID id) {
        EntityManager em = App.emf.createEntityManager();
        Stem stem = findById(id);
        em.getTransaction().begin();
        em.remove(stem);
        em.getTransaction().commit();
        em.close();
        return stem;
    }
}
