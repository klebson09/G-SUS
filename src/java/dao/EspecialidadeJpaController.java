/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.NonexistentEntityException;
import entidades.Especialidade;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.UnidadeDeSaude;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author klebson
 */
public class EspecialidadeJpaController implements Serializable {

    public EspecialidadeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Especialidade especialidade) {
        if (especialidade.getUnidadeDeSaudeList() == null) {
            especialidade.setUnidadeDeSaudeList(new ArrayList<UnidadeDeSaude>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UnidadeDeSaude> attachedUnidadeDeSaudeList = new ArrayList<UnidadeDeSaude>();
            for (UnidadeDeSaude unidadeDeSaudeListUnidadeDeSaudeToAttach : especialidade.getUnidadeDeSaudeList()) {
                unidadeDeSaudeListUnidadeDeSaudeToAttach = em.getReference(unidadeDeSaudeListUnidadeDeSaudeToAttach.getClass(), unidadeDeSaudeListUnidadeDeSaudeToAttach.getIdUnidadeDeSaude());
                attachedUnidadeDeSaudeList.add(unidadeDeSaudeListUnidadeDeSaudeToAttach);
            }
            especialidade.setUnidadeDeSaudeList(attachedUnidadeDeSaudeList);
            em.persist(especialidade);
            for (UnidadeDeSaude unidadeDeSaudeListUnidadeDeSaude : especialidade.getUnidadeDeSaudeList()) {
                unidadeDeSaudeListUnidadeDeSaude.getEspecialidadeList().add(especialidade);
                unidadeDeSaudeListUnidadeDeSaude = em.merge(unidadeDeSaudeListUnidadeDeSaude);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Especialidade especialidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especialidade persistentEspecialidade = em.find(Especialidade.class, especialidade.getIdEspecialidade());
            List<UnidadeDeSaude> unidadeDeSaudeListOld = persistentEspecialidade.getUnidadeDeSaudeList();
            List<UnidadeDeSaude> unidadeDeSaudeListNew = especialidade.getUnidadeDeSaudeList();
            List<UnidadeDeSaude> attachedUnidadeDeSaudeListNew = new ArrayList<UnidadeDeSaude>();
            for (UnidadeDeSaude unidadeDeSaudeListNewUnidadeDeSaudeToAttach : unidadeDeSaudeListNew) {
                unidadeDeSaudeListNewUnidadeDeSaudeToAttach = em.getReference(unidadeDeSaudeListNewUnidadeDeSaudeToAttach.getClass(), unidadeDeSaudeListNewUnidadeDeSaudeToAttach.getIdUnidadeDeSaude());
                attachedUnidadeDeSaudeListNew.add(unidadeDeSaudeListNewUnidadeDeSaudeToAttach);
            }
            unidadeDeSaudeListNew = attachedUnidadeDeSaudeListNew;
            especialidade.setUnidadeDeSaudeList(unidadeDeSaudeListNew);
            especialidade = em.merge(especialidade);
            for (UnidadeDeSaude unidadeDeSaudeListOldUnidadeDeSaude : unidadeDeSaudeListOld) {
                if (!unidadeDeSaudeListNew.contains(unidadeDeSaudeListOldUnidadeDeSaude)) {
                    unidadeDeSaudeListOldUnidadeDeSaude.getEspecialidadeList().remove(especialidade);
                    unidadeDeSaudeListOldUnidadeDeSaude = em.merge(unidadeDeSaudeListOldUnidadeDeSaude);
                }
            }
            for (UnidadeDeSaude unidadeDeSaudeListNewUnidadeDeSaude : unidadeDeSaudeListNew) {
                if (!unidadeDeSaudeListOld.contains(unidadeDeSaudeListNewUnidadeDeSaude)) {
                    unidadeDeSaudeListNewUnidadeDeSaude.getEspecialidadeList().add(especialidade);
                    unidadeDeSaudeListNewUnidadeDeSaude = em.merge(unidadeDeSaudeListNewUnidadeDeSaude);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = especialidade.getIdEspecialidade();
                if (findEspecialidade(id) == null) {
                    throw new NonexistentEntityException("The especialidade with id " + id + " no longer exists.");
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
            Especialidade especialidade;
            try {
                especialidade = em.getReference(Especialidade.class, id);
                especialidade.getIdEspecialidade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The especialidade with id " + id + " no longer exists.", enfe);
            }
            List<UnidadeDeSaude> unidadeDeSaudeList = especialidade.getUnidadeDeSaudeList();
            for (UnidadeDeSaude unidadeDeSaudeListUnidadeDeSaude : unidadeDeSaudeList) {
                unidadeDeSaudeListUnidadeDeSaude.getEspecialidadeList().remove(especialidade);
                unidadeDeSaudeListUnidadeDeSaude = em.merge(unidadeDeSaudeListUnidadeDeSaude);
            }
            em.remove(especialidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Especialidade> findEspecialidadeEntities() {
        return findEspecialidadeEntities(true, -1, -1);
    }

    public List<Especialidade> findEspecialidadeEntities(int maxResults, int firstResult) {
        return findEspecialidadeEntities(false, maxResults, firstResult);
    }

    private List<Especialidade> findEspecialidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Especialidade.class));
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

    public Especialidade findEspecialidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Especialidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getEspecialidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Especialidade> rt = cq.from(Especialidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
