package dao;

import dao.exceptions.NonexistentEntityException;
import dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.UnidadeDeSaude;
import entidades.UnidadeDeSaudeHasUsuarios;
import entidades.UnidadeDeSaudeHasUsuariosPK;
import entidades.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author klebson
 */
public class UnidadeDeSaudeHasUsuariosJpaController implements Serializable {

    private static UnidadeDeSaudeHasUsuariosJpaController instance;
    protected EntityManager entityManager;

    public static UnidadeDeSaudeHasUsuariosJpaController getInstance() {
        if (instance == null) {
            instance = new UnidadeDeSaudeHasUsuariosJpaController();
        }
        return instance;
    }

    public UnidadeDeSaudeHasUsuariosJpaController() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PDWPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    public void create(UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuarios) throws PreexistingEntityException, Exception {
        if (unidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosPK() == null) {
            unidadeDeSaudeHasUsuarios.setUnidadeDeSaudeHasUsuariosPK(new UnidadeDeSaudeHasUsuariosPK());
        }
        unidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosPK().setUnidadeDeSaudeIdunidadeDeSaude(unidadeDeSaudeHasUsuarios.getUnidadeDeSaude().getIdUnidadeDeSaude());
        unidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosPK().setUsuariosIdUsuarios(unidadeDeSaudeHasUsuarios.getUsuarios().getIdUsuarios());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadeDeSaude unidadeDeSaude = unidadeDeSaudeHasUsuarios.getUnidadeDeSaude();
            if (unidadeDeSaude != null) {
                unidadeDeSaude = em.getReference(unidadeDeSaude.getClass(), unidadeDeSaude.getIdUnidadeDeSaude());
                unidadeDeSaudeHasUsuarios.setUnidadeDeSaude(unidadeDeSaude);
            }
            Usuarios usuarios = unidadeDeSaudeHasUsuarios.getUsuarios();
            if (usuarios != null) {
                usuarios = em.getReference(usuarios.getClass(), usuarios.getIdUsuarios());
                unidadeDeSaudeHasUsuarios.setUsuarios(usuarios);
            }
            em.persist(unidadeDeSaudeHasUsuarios);
            if (unidadeDeSaude != null) {
                unidadeDeSaude.getUnidadeDeSaudeHasUsuariosList().add(unidadeDeSaudeHasUsuarios);
                unidadeDeSaude = em.merge(unidadeDeSaude);
            }
            if (usuarios != null) {
                usuarios.getUnidadeDeSaudeHasUsuariosList().add(unidadeDeSaudeHasUsuarios);
                usuarios = em.merge(usuarios);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUnidadeDeSaudeHasUsuarios(unidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosPK()) != null) {
                throw new PreexistingEntityException("UnidadeDeSaudeHasUsuarios " + unidadeDeSaudeHasUsuarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuarios) throws NonexistentEntityException, Exception {
        unidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosPK().setUnidadeDeSaudeIdunidadeDeSaude(unidadeDeSaudeHasUsuarios.getUnidadeDeSaude().getIdUnidadeDeSaude());
        unidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosPK().setUsuariosIdUsuarios(unidadeDeSaudeHasUsuarios.getUsuarios().getIdUsuarios());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadeDeSaudeHasUsuarios persistentUnidadeDeSaudeHasUsuarios = em.find(UnidadeDeSaudeHasUsuarios.class, unidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosPK());
            UnidadeDeSaude unidadeDeSaudeOld = persistentUnidadeDeSaudeHasUsuarios.getUnidadeDeSaude();
            UnidadeDeSaude unidadeDeSaudeNew = unidadeDeSaudeHasUsuarios.getUnidadeDeSaude();
            Usuarios usuariosOld = persistentUnidadeDeSaudeHasUsuarios.getUsuarios();
            Usuarios usuariosNew = unidadeDeSaudeHasUsuarios.getUsuarios();
            if (unidadeDeSaudeNew != null) {
                unidadeDeSaudeNew = em.getReference(unidadeDeSaudeNew.getClass(), unidadeDeSaudeNew.getIdUnidadeDeSaude());
                unidadeDeSaudeHasUsuarios.setUnidadeDeSaude(unidadeDeSaudeNew);
            }
            if (usuariosNew != null) {
                usuariosNew = em.getReference(usuariosNew.getClass(), usuariosNew.getIdUsuarios());
                unidadeDeSaudeHasUsuarios.setUsuarios(usuariosNew);
            }
            unidadeDeSaudeHasUsuarios = em.merge(unidadeDeSaudeHasUsuarios);
            if (unidadeDeSaudeOld != null && !unidadeDeSaudeOld.equals(unidadeDeSaudeNew)) {
                unidadeDeSaudeOld.getUnidadeDeSaudeHasUsuariosList().remove(unidadeDeSaudeHasUsuarios);
                unidadeDeSaudeOld = em.merge(unidadeDeSaudeOld);
            }
            if (unidadeDeSaudeNew != null && !unidadeDeSaudeNew.equals(unidadeDeSaudeOld)) {
                unidadeDeSaudeNew.getUnidadeDeSaudeHasUsuariosList().add(unidadeDeSaudeHasUsuarios);
                unidadeDeSaudeNew = em.merge(unidadeDeSaudeNew);
            }
            if (usuariosOld != null && !usuariosOld.equals(usuariosNew)) {
                usuariosOld.getUnidadeDeSaudeHasUsuariosList().remove(unidadeDeSaudeHasUsuarios);
                usuariosOld = em.merge(usuariosOld);
            }
            if (usuariosNew != null && !usuariosNew.equals(usuariosOld)) {
                usuariosNew.getUnidadeDeSaudeHasUsuariosList().add(unidadeDeSaudeHasUsuarios);
                usuariosNew = em.merge(usuariosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UnidadeDeSaudeHasUsuariosPK id = unidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosPK();
                if (findUnidadeDeSaudeHasUsuarios(id) == null) {
                    throw new NonexistentEntityException("The unidadeDeSaudeHasUsuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UnidadeDeSaudeHasUsuariosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuarios;
            try {
                unidadeDeSaudeHasUsuarios = em.getReference(UnidadeDeSaudeHasUsuarios.class, id);
                unidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadeDeSaudeHasUsuarios with id " + id + " no longer exists.", enfe);
            }
            UnidadeDeSaude unidadeDeSaude = unidadeDeSaudeHasUsuarios.getUnidadeDeSaude();
            if (unidadeDeSaude != null) {
                unidadeDeSaude.getUnidadeDeSaudeHasUsuariosList().remove(unidadeDeSaudeHasUsuarios);
                unidadeDeSaude = em.merge(unidadeDeSaude);
            }
            Usuarios usuarios = unidadeDeSaudeHasUsuarios.getUsuarios();
            if (usuarios != null) {
                usuarios.getUnidadeDeSaudeHasUsuariosList().remove(unidadeDeSaudeHasUsuarios);
                usuarios = em.merge(usuarios);
            }
            em.remove(unidadeDeSaudeHasUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidadeDeSaudeHasUsuarios> findUnidadeDeSaudeHasUsuariosEntities() {
        return findUnidadeDeSaudeHasUsuariosEntities(true, -1, -1);
    }

    public List<UnidadeDeSaudeHasUsuarios> findUnidadeDeSaudeHasUsuariosEntities(int maxResults, int firstResult) {
        return findUnidadeDeSaudeHasUsuariosEntities(false, maxResults, firstResult);
    }

    private List<UnidadeDeSaudeHasUsuarios> findUnidadeDeSaudeHasUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadeDeSaudeHasUsuarios.class));
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

    public UnidadeDeSaudeHasUsuarios findUnidadeDeSaudeHasUsuarios(UnidadeDeSaudeHasUsuariosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadeDeSaudeHasUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadeDeSaudeHasUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadeDeSaudeHasUsuarios> rt = cq.from(UnidadeDeSaudeHasUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
