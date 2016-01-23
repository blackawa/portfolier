package jp.blackawa.services;

import jp.blackawa.App;
import jp.blackawa.model.Stem;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class StemService implements Service<Stem> {
    @Override
    public UUID insert(Stem stem) {
        EntityManager em = App.emf.createEntityManager();
        stem.setId(UUID.randomUUID());
        stem.setCreatedTime(new Date());
        em.getTransaction().begin();
        em.persist(stem);
        em.getTransaction().commit();
        em.close();
        return stem.getId();
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
    public UUID update(Stem stem) {
        EntityManager em = App.emf.createEntityManager();
        Stem found = findById(stem.getId());
        // ゴミコードくさいけどいったん動くかやってみる
        found.setName(stem.getName());
        found.setUpdateUserId(stem.getUpdateUserId());
        found.setUpdatedTime(new Date());
        em.getTransaction().begin();
        em.persist(stem);
        em.getTransaction().commit();
        em.close();
        return stem.getId();
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
