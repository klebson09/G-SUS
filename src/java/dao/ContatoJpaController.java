package dao;

import dao.exceptions.NonexistentEntityException;
import entidades.Contato;
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
public class ContatoJpaController implements Serializable {

    private static ContatoJpaController instance;
    protected EntityManager entityManager;

    public static ContatoJpaController getInstance() {
        if (instance == null) {
            instance = new ContatoJpaController();
        }
        return instance;
    }

    public ContatoJpaController() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PDWPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    public void create(Contato contato) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadeDeSaude unidadeDeSaudeIdunidadeDeSaude = contato.getUnidadeDeSaudeIdunidadeDeSaude();
            if (unidadeDeSaudeIdunidadeDeSaude != null) {
                unidadeDeSaudeIdunidadeDeSaude = em.getReference(unidadeDeSaudeIdunidadeDeSaude.getClass(), unidadeDeSaudeIdunidadeDeSaude.getIdUnidadeDeSaude());
                contato.setUnidadeDeSaudeIdunidadeDeSaude(unidadeDeSaudeIdunidadeDeSaude);
            }
            em.persist(contato);
            if (unidadeDeSaudeIdunidadeDeSaude != null) {
                unidadeDeSaudeIdunidadeDeSaude.getContatoList().add(contato);
                unidadeDeSaudeIdunidadeDeSaude = em.merge(unidadeDeSaudeIdunidadeDeSaude);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contato contato) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contato persistentContato = em.find(Contato.class, contato.getIdContato());
            UnidadeDeSaude unidadeDeSaudeIdunidadeDeSaudeOld = persistentContato.getUnidadeDeSaudeIdunidadeDeSaude();
            UnidadeDeSaude unidadeDeSaudeIdunidadeDeSaudeNew = contato.getUnidadeDeSaudeIdunidadeDeSaude();
            if (unidadeDeSaudeIdunidadeDeSaudeNew != null) {
                unidadeDeSaudeIdunidadeDeSaudeNew = em.getReference(unidadeDeSaudeIdunidadeDeSaudeNew.getClass(), unidadeDeSaudeIdunidadeDeSaudeNew.getIdUnidadeDeSaude());
                contato.setUnidadeDeSaudeIdunidadeDeSaude(unidadeDeSaudeIdunidadeDeSaudeNew);
            }
            contato = em.merge(contato);
            if (unidadeDeSaudeIdunidadeDeSaudeOld != null && !unidadeDeSaudeIdunidadeDeSaudeOld.equals(unidadeDeSaudeIdunidadeDeSaudeNew)) {
                unidadeDeSaudeIdunidadeDeSaudeOld.getContatoList().remove(contato);
                unidadeDeSaudeIdunidadeDeSaudeOld = em.merge(unidadeDeSaudeIdunidadeDeSaudeOld);
            }
            if (unidadeDeSaudeIdunidadeDeSaudeNew != null && !unidadeDeSaudeIdunidadeDeSaudeNew.equals(unidadeDeSaudeIdunidadeDeSaudeOld)) {
                unidadeDeSaudeIdunidadeDeSaudeNew.getContatoList().add(contato);
                unidadeDeSaudeIdunidadeDeSaudeNew = em.merge(unidadeDeSaudeIdunidadeDeSaudeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contato.getIdContato();
                if (findContato(id) == null) {
                    throw new NonexistentEntityException("The contato with id " + id + " no longer exists.");
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
            Contato contato;
            try {
                contato = em.getReference(Contato.class, id);
                contato.getIdContato();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contato with id " + id + " no longer exists.", enfe);
            }
            UnidadeDeSaude unidadeDeSaudeIdunidadeDeSaude = contato.getUnidadeDeSaudeIdunidadeDeSaude();
            if (unidadeDeSaudeIdunidadeDeSaude != null) {
                unidadeDeSaudeIdunidadeDeSaude.getContatoList().remove(contato);
                unidadeDeSaudeIdunidadeDeSaude = em.merge(unidadeDeSaudeIdunidadeDeSaude);
            }
            em.remove(contato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contato> findContatoEntities() {
        return findContatoEntities(true, -1, -1);
    }

    public List<Contato> findContatoEntities(int maxResults, int firstResult) {
        return findContatoEntities(false, maxResults, firstResult);
    }

    private List<Contato> findContatoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contato.class));
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

    public Contato findContato(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contato.class, id);
        } finally {
            em.close();
        }
    }

    public int getContatoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contato> rt = cq.from(Contato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
