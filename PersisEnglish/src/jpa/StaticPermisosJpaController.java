/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entity.StaticPermisos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author MARIO
 */
public class StaticPermisosJpaController implements Serializable {

    public StaticPermisosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StaticPermisos staticPermisos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(staticPermisos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StaticPermisos staticPermisos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            staticPermisos = em.merge(staticPermisos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = staticPermisos.getId();
                if (findStaticPermisos(id) == null) {
                    throw new NonexistentEntityException("The staticPermisos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            StaticPermisos staticPermisos;
            try {
                staticPermisos = em.getReference(StaticPermisos.class, id);
                staticPermisos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The staticPermisos with id " + id + " no longer exists.", enfe);
            }
            em.remove(staticPermisos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StaticPermisos> findStaticPermisosEntities() {
        return findStaticPermisosEntities(true, -1, -1);
    }

    public List<StaticPermisos> findStaticPermisosEntities(int maxResults, int firstResult) {
        return findStaticPermisosEntities(false, maxResults, firstResult);
    }

    private List<StaticPermisos> findStaticPermisosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StaticPermisos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public StaticPermisos findStaticPermisos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StaticPermisos.class, id);
        } finally {
            em.close();
        }
    }

    public int getStaticPermisosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StaticPermisos> rt = cq.from(StaticPermisos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
