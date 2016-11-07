package dao;

import dao.exceptions.NonexistentEntityException;
import entidades.Exames;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.UnidadeDeSaude;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author klebson
 */
public class ExamesJpaController implements Serializable {

    private static ExamesJpaController instance;
    protected EntityManager entityManager;

    public static ExamesJpaController getInstance() {
        if (instance == null) {
            instance = new ExamesJpaController();
        }
        return instance;
    }

    public ExamesJpaController() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PDWPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    public void create(Exames exames) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadeDeSaude unidadeDeSaudeIdUnidadeDeSaude = exames.getUnidadeDeSaudeIdUnidadeDeSaude();
            if (unidadeDeSaudeIdUnidadeDeSaude != null) {
                unidadeDeSaudeIdUnidadeDeSaude = em.getReference(unidadeDeSaudeIdUnidadeDeSaude.getClass(), unidadeDeSaudeIdUnidadeDeSaude.getIdUnidadeDeSaude());
                exames.setUnidadeDeSaudeIdUnidadeDeSaude(unidadeDeSaudeIdUnidadeDeSaude);
            }
            em.persist(exames);
            if (unidadeDeSaudeIdUnidadeDeSaude != null) {
                unidadeDeSaudeIdUnidadeDeSaude.getExamesList().add(exames);
                unidadeDeSaudeIdUnidadeDeSaude = em.merge(unidadeDeSaudeIdUnidadeDeSaude);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Exames exames) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exames persistentExames = em.find(Exames.class, exames.getIdExames());
            UnidadeDeSaude unidadeDeSaudeIdUnidadeDeSaudeOld = persistentExames.getUnidadeDeSaudeIdUnidadeDeSaude();
            UnidadeDeSaude unidadeDeSaudeIdUnidadeDeSaudeNew = exames.getUnidadeDeSaudeIdUnidadeDeSaude();
            if (unidadeDeSaudeIdUnidadeDeSaudeNew != null) {
                unidadeDeSaudeIdUnidadeDeSaudeNew = em.getReference(unidadeDeSaudeIdUnidadeDeSaudeNew.getClass(), unidadeDeSaudeIdUnidadeDeSaudeNew.getIdUnidadeDeSaude());
                exames.setUnidadeDeSaudeIdUnidadeDeSaude(unidadeDeSaudeIdUnidadeDeSaudeNew);
            }
            exames = em.merge(exames);
            if (unidadeDeSaudeIdUnidadeDeSaudeOld != null && !unidadeDeSaudeIdUnidadeDeSaudeOld.equals(unidadeDeSaudeIdUnidadeDeSaudeNew)) {
                unidadeDeSaudeIdUnidadeDeSaudeOld.getExamesList().remove(exames);
                unidadeDeSaudeIdUnidadeDeSaudeOld = em.merge(unidadeDeSaudeIdUnidadeDeSaudeOld);
            }
            if (unidadeDeSaudeIdUnidadeDeSaudeNew != null && !unidadeDeSaudeIdUnidadeDeSaudeNew.equals(unidadeDeSaudeIdUnidadeDeSaudeOld)) {
                unidadeDeSaudeIdUnidadeDeSaudeNew.getExamesList().add(exames);
                unidadeDeSaudeIdUnidadeDeSaudeNew = em.merge(unidadeDeSaudeIdUnidadeDeSaudeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = exames.getIdExames();
                if (findExames(id) == null) {
                    throw new NonexistentEntityException("The exames with id " + id + " no longer exists.");
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
            Exames exames;
            try {
                exames = em.getReference(Exames.class, id);
                exames.getIdExames();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The exames with id " + id + " no longer exists.", enfe);
            }
            UnidadeDeSaude unidadeDeSaudeIdUnidadeDeSaude = exames.getUnidadeDeSaudeIdUnidadeDeSaude();
            if (unidadeDeSaudeIdUnidadeDeSaude != null) {
                unidadeDeSaudeIdUnidadeDeSaude.getExamesList().remove(exames);
                unidadeDeSaudeIdUnidadeDeSaude = em.merge(unidadeDeSaudeIdUnidadeDeSaude);
            }
            em.remove(exames);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Exames> findExamesEntities() {
        return findExamesEntities(true, -1, -1);
    }

    public List<Exames> findExamesEntities(int maxResults, int firstResult) {
        return findExamesEntities(false, maxResults, firstResult);
    }

    private List<Exames> findExamesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exames.class));
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

    public Exames findExames(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Exames.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Exames> rt = cq.from(Exames.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
