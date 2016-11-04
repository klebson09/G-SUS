package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.UnidadeDeSaudeHasUsuarios;
import entidades.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author klebson
 */
public class UsuariosJpaController implements Serializable {

       protected EntityManager entityManager;

    public static UsuariosJpaController getInstance() {
        if (instance == null) {
            instance = new UnidadeDeSaudeJpaController();
        }
        return instance;
    }

    public UsuariosJpaController() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PDWPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }


    public void create(Usuarios usuarios) {
        if (usuarios.getUnidadeDeSaudeHasUsuariosList() == null) {
            usuarios.setUnidadeDeSaudeHasUsuariosList(new ArrayList<UnidadeDeSaudeHasUsuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<UnidadeDeSaudeHasUsuarios> attachedUnidadeDeSaudeHasUsuariosList = new ArrayList<UnidadeDeSaudeHasUsuarios>();
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuariosToAttach : usuarios.getUnidadeDeSaudeHasUsuariosList()) {
                unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuariosToAttach = em.getReference(unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuariosToAttach.getClass(), unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuariosToAttach.getUnidadeDeSaudeHasUsuariosPK());
                attachedUnidadeDeSaudeHasUsuariosList.add(unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuariosToAttach);
            }
            usuarios.setUnidadeDeSaudeHasUsuariosList(attachedUnidadeDeSaudeHasUsuariosList);
            em.persist(usuarios);
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios : usuarios.getUnidadeDeSaudeHasUsuariosList()) {
                Usuarios oldUsuariosOfUnidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios = unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios.getUsuarios();
                unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios.setUsuarios(usuarios);
                unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios = em.merge(unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios);
                if (oldUsuariosOfUnidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios != null) {
                    oldUsuariosOfUnidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosList().remove(unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios);
                    oldUsuariosOfUnidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios = em.merge(oldUsuariosOfUnidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdUsuarios());
            List<UnidadeDeSaudeHasUsuarios> unidadeDeSaudeHasUsuariosListOld = persistentUsuarios.getUnidadeDeSaudeHasUsuariosList();
            List<UnidadeDeSaudeHasUsuarios> unidadeDeSaudeHasUsuariosListNew = usuarios.getUnidadeDeSaudeHasUsuariosList();
            List<String> illegalOrphanMessages = null;
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListOldUnidadeDeSaudeHasUsuarios : unidadeDeSaudeHasUsuariosListOld) {
                if (!unidadeDeSaudeHasUsuariosListNew.contains(unidadeDeSaudeHasUsuariosListOldUnidadeDeSaudeHasUsuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UnidadeDeSaudeHasUsuarios " + unidadeDeSaudeHasUsuariosListOldUnidadeDeSaudeHasUsuarios + " since its usuarios field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UnidadeDeSaudeHasUsuarios> attachedUnidadeDeSaudeHasUsuariosListNew = new ArrayList<UnidadeDeSaudeHasUsuarios>();
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuariosToAttach : unidadeDeSaudeHasUsuariosListNew) {
                unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuariosToAttach = em.getReference(unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuariosToAttach.getClass(), unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuariosToAttach.getUnidadeDeSaudeHasUsuariosPK());
                attachedUnidadeDeSaudeHasUsuariosListNew.add(unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuariosToAttach);
            }
            unidadeDeSaudeHasUsuariosListNew = attachedUnidadeDeSaudeHasUsuariosListNew;
            usuarios.setUnidadeDeSaudeHasUsuariosList(unidadeDeSaudeHasUsuariosListNew);
            usuarios = em.merge(usuarios);
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios : unidadeDeSaudeHasUsuariosListNew) {
                if (!unidadeDeSaudeHasUsuariosListOld.contains(unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios)) {
                    Usuarios oldUsuariosOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios = unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios.getUsuarios();
                    unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios.setUsuarios(usuarios);
                    unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios = em.merge(unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios);
                    if (oldUsuariosOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios != null && !oldUsuariosOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios.equals(usuarios)) {
                        oldUsuariosOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosList().remove(unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios);
                        oldUsuariosOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios = em.merge(oldUsuariosOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getIdUsuarios();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdUsuarios();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UnidadeDeSaudeHasUsuarios> unidadeDeSaudeHasUsuariosListOrphanCheck = usuarios.getUnidadeDeSaudeHasUsuariosList();
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListOrphanCheckUnidadeDeSaudeHasUsuarios : unidadeDeSaudeHasUsuariosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the UnidadeDeSaudeHasUsuarios " + unidadeDeSaudeHasUsuariosListOrphanCheckUnidadeDeSaudeHasUsuarios + " in its unidadeDeSaudeHasUsuariosList field has a non-nullable usuarios field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
