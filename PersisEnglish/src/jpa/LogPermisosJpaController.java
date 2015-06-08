/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entity.LogPermisos;
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
public class LogPermisosJpaController implements Serializable {

    public LogPermisosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LogPermisos logPermisos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(logPermisos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LogPermisos logPermisos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            logPermisos = em.merge(logPermisos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = logPermisos.getId();
                if (findLogPermisos(id) == null) {
                    throw new NonexistentEntityException("The logPermisos with id " + id + " no longer exists.");
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
            LogPermisos logPermisos;
            try {
                logPermisos = em.getReference(LogPermisos.class, id);
                logPermisos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logPermisos with id " + id + " no longer exists.", enfe);
            }
            em.remove(logPermisos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LogPermisos> findLogPermisosEntities() {
        return findLogPermisosEntities(true, -1, -1);
    }

    public List<LogPermisos> findLogPermisosEntities(int maxResults, int firstResult) {
        return findLogPermisosEntities(false, maxResults, firstResult);
    }

    private List<LogPermisos> findLogPermisosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LogPermisos.class));
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

    public LogPermisos findLogPermisos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LogPermisos.class, id);
        } finally {
            em.close();
        }
    }

    public int getLogPermisosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LogPermisos> rt = cq.from(LogPermisos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
