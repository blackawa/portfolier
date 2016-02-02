package jp.blackawa.services;

import jp.blackawa.App;
import jp.blackawa.model.Branch;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BranchService implements Service<Branch>{
    @Override
    public HashMap<String, UUID> insert(Branch branch) {
        EntityManager em = App.emf.createEntityManager();
        em.getTransaction().begin();
        branch.setId(UUID.randomUUID());
        em.persist(branch);
        em.getTransaction().commit();
        em.close();
        return new HashMap<String, UUID>(){{
            put("id", branch.getId());
        }};
    }

    @Override
    public List<Branch> findAll() {
        EntityManager em = App.emf.createEntityManager();
        return em.createQuery("from Branch", Branch.class).getResultList();
    }

    @Override
    public Branch findById(UUID id) {
        EntityManager em = App.emf.createEntityManager();
        return em.createQuery("from Branch where id = ?", Branch.class).setParameter(1, id).getSingleResult();
    }

    @Override
    public Branch findBy(String column, Object value) {
        EntityManager em = App.emf.createEntityManager();
        return em.createQuery("from Stem where " + column + " = ?", Branch.class).setParameter(1, value).getSingleResult();
    }

    @Override
    public HashMap<String, UUID> update(Branch branch) {
        EntityManager em = App.emf.createEntityManager();
        try {
            findById(branch.getId());
        } catch (NoResultException e) {
            return null;
        }
        em.getTransaction().begin();
        em.merge(branch);
        em.getTransaction().commit();
        em.close();
        return new HashMap<String, UUID>() {{
            put("id", branch.getId());
        }};
    }

    @Override
    public Branch delete(UUID id) {
        EntityManager em = App.emf.createEntityManager();
        Branch branch = findById(id);
        em.getTransaction().begin();
        em.remove(branch);
        em.getTransaction().commit();
        em.close();
        return branch;
    }
}
