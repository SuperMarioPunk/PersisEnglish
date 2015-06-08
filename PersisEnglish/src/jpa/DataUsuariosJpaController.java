/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import entity.DataUsuarios;
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
public class DataUsuariosJpaController implements Serializable {

    public DataUsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DataUsuarios dataUsuarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dataUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DataUsuarios dataUsuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dataUsuarios = em.merge(dataUsuarios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dataUsuarios.getId();
                if (findDataUsuarios(id) == null) {
                    throw new NonexistentEntityException("The dataUsuarios with id " + id + " no longer exists.");
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
            DataUsuarios dataUsuarios;
            try {
                dataUsuarios = em.getReference(DataUsuarios.class, id);
                dataUsuarios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dataUsuarios with id " + id + " no longer exists.", enfe);
            }
            em.remove(dataUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DataUsuarios> findDataUsuariosEntities() {
        return findDataUsuariosEntities(true, -1, -1);
    }

    public List<DataUsuarios> findDataUsuariosEntities(int maxResults, int firstResult) {
        return findDataUsuariosEntities(false, maxResults, firstResult);
    }

    private List<DataUsuarios> findDataUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DataUsuarios.class));
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

    public DataUsuarios findDataUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DataUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getDataUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DataUsuarios> rt = cq.from(DataUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
