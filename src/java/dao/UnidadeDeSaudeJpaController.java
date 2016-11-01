/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Especialidade;
import java.util.ArrayList;
import java.util.List;
import entidades.UnidadeDeSaudeHasUsuarios;
import entidades.Contato;
import entidades.Exames;
import entidades.UnidadeDeSaude;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author klebson
 */
public class UnidadeDeSaudeJpaController implements Serializable {

  
 private static UnidadeDeSaudeJpaController instance;

    protected EntityManager entityManager;

    public static UnidadeDeSaudeJpaController getInstance() {
        if (instance == null) {
            instance = new UnidadeDeSaudeJpaController();
        }
        return instance;
    }

    public UnidadeDeSaudeJpaController() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PDWPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    public void create(UnidadeDeSaude unidadeDeSaude) {
        if (unidadeDeSaude.getEspecialidadeList() == null) {
            unidadeDeSaude.setEspecialidadeList(new ArrayList<Especialidade>());
        }
        if (unidadeDeSaude.getUnidadeDeSaudeHasUsuariosList() == null) {
            unidadeDeSaude.setUnidadeDeSaudeHasUsuariosList(new ArrayList<UnidadeDeSaudeHasUsuarios>());
        }
        if (unidadeDeSaude.getContatoList() == null) {
            unidadeDeSaude.setContatoList(new ArrayList<Contato>());
        }
        if (unidadeDeSaude.getExamesList() == null) {
            unidadeDeSaude.setExamesList(new ArrayList<Exames>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Especialidade> attachedEspecialidadeList = new ArrayList<Especialidade>();
            for (Especialidade especialidadeListEspecialidadeToAttach : unidadeDeSaude.getEspecialidadeList()) {
                especialidadeListEspecialidadeToAttach = em.getReference(especialidadeListEspecialidadeToAttach.getClass(), especialidadeListEspecialidadeToAttach.getIdEspecialidade());
                attachedEspecialidadeList.add(especialidadeListEspecialidadeToAttach);
            }
            unidadeDeSaude.setEspecialidadeList(attachedEspecialidadeList);
            List<UnidadeDeSaudeHasUsuarios> attachedUnidadeDeSaudeHasUsuariosList = new ArrayList<UnidadeDeSaudeHasUsuarios>();
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuariosToAttach : unidadeDeSaude.getUnidadeDeSaudeHasUsuariosList()) {
                unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuariosToAttach = em.getReference(unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuariosToAttach.getClass(), unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuariosToAttach.getUnidadeDeSaudeHasUsuariosPK());
                attachedUnidadeDeSaudeHasUsuariosList.add(unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuariosToAttach);
            }
            unidadeDeSaude.setUnidadeDeSaudeHasUsuariosList(attachedUnidadeDeSaudeHasUsuariosList);
            List<Contato> attachedContatoList = new ArrayList<Contato>();
            for (Contato contatoListContatoToAttach : unidadeDeSaude.getContatoList()) {
                contatoListContatoToAttach = em.getReference(contatoListContatoToAttach.getClass(), contatoListContatoToAttach.getIdContato());
                attachedContatoList.add(contatoListContatoToAttach);
            }
            unidadeDeSaude.setContatoList(attachedContatoList);
            List<Exames> attachedExamesList = new ArrayList<Exames>();
            for (Exames examesListExamesToAttach : unidadeDeSaude.getExamesList()) {
                examesListExamesToAttach = em.getReference(examesListExamesToAttach.getClass(), examesListExamesToAttach.getIdExames());
                attachedExamesList.add(examesListExamesToAttach);
            }
            unidadeDeSaude.setExamesList(attachedExamesList);
            em.persist(unidadeDeSaude);
            for (Especialidade especialidadeListEspecialidade : unidadeDeSaude.getEspecialidadeList()) {
                especialidadeListEspecialidade.getUnidadeDeSaudeList().add(unidadeDeSaude);
                especialidadeListEspecialidade = em.merge(especialidadeListEspecialidade);
            }
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios : unidadeDeSaude.getUnidadeDeSaudeHasUsuariosList()) {
                UnidadeDeSaude oldUnidadeDeSaudeOfUnidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios = unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios.getUnidadeDeSaude();
                unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios.setUnidadeDeSaude(unidadeDeSaude);
                unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios = em.merge(unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios);
                if (oldUnidadeDeSaudeOfUnidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios != null) {
                    oldUnidadeDeSaudeOfUnidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosList().remove(unidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios);
                    oldUnidadeDeSaudeOfUnidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios = em.merge(oldUnidadeDeSaudeOfUnidadeDeSaudeHasUsuariosListUnidadeDeSaudeHasUsuarios);
                }
            }
            for (Contato contatoListContato : unidadeDeSaude.getContatoList()) {
                UnidadeDeSaude oldUnidadeDeSaudeIdunidadeDeSaudeOfContatoListContato = contatoListContato.getUnidadeDeSaudeIdunidadeDeSaude();
                contatoListContato.setUnidadeDeSaudeIdunidadeDeSaude(unidadeDeSaude);
                contatoListContato = em.merge(contatoListContato);
                if (oldUnidadeDeSaudeIdunidadeDeSaudeOfContatoListContato != null) {
                    oldUnidadeDeSaudeIdunidadeDeSaudeOfContatoListContato.getContatoList().remove(contatoListContato);
                    oldUnidadeDeSaudeIdunidadeDeSaudeOfContatoListContato = em.merge(oldUnidadeDeSaudeIdunidadeDeSaudeOfContatoListContato);
                }
            }
            for (Exames examesListExames : unidadeDeSaude.getExamesList()) {
                UnidadeDeSaude oldUnidadeDeSaudeIdUnidadeDeSaudeOfExamesListExames = examesListExames.getUnidadeDeSaudeIdUnidadeDeSaude();
                examesListExames.setUnidadeDeSaudeIdUnidadeDeSaude(unidadeDeSaude);
                examesListExames = em.merge(examesListExames);
                if (oldUnidadeDeSaudeIdUnidadeDeSaudeOfExamesListExames != null) {
                    oldUnidadeDeSaudeIdUnidadeDeSaudeOfExamesListExames.getExamesList().remove(examesListExames);
                    oldUnidadeDeSaudeIdUnidadeDeSaudeOfExamesListExames = em.merge(oldUnidadeDeSaudeIdUnidadeDeSaudeOfExamesListExames);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadeDeSaude unidadeDeSaude) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadeDeSaude persistentUnidadeDeSaude = em.find(UnidadeDeSaude.class, unidadeDeSaude.getIdUnidadeDeSaude());
            List<Especialidade> especialidadeListOld = persistentUnidadeDeSaude.getEspecialidadeList();
            List<Especialidade> especialidadeListNew = unidadeDeSaude.getEspecialidadeList();
            List<UnidadeDeSaudeHasUsuarios> unidadeDeSaudeHasUsuariosListOld = persistentUnidadeDeSaude.getUnidadeDeSaudeHasUsuariosList();
            List<UnidadeDeSaudeHasUsuarios> unidadeDeSaudeHasUsuariosListNew = unidadeDeSaude.getUnidadeDeSaudeHasUsuariosList();
            List<Contato> contatoListOld = persistentUnidadeDeSaude.getContatoList();
            List<Contato> contatoListNew = unidadeDeSaude.getContatoList();
            List<Exames> examesListOld = persistentUnidadeDeSaude.getExamesList();
            List<Exames> examesListNew = unidadeDeSaude.getExamesList();
            List<String> illegalOrphanMessages = null;
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListOldUnidadeDeSaudeHasUsuarios : unidadeDeSaudeHasUsuariosListOld) {
                if (!unidadeDeSaudeHasUsuariosListNew.contains(unidadeDeSaudeHasUsuariosListOldUnidadeDeSaudeHasUsuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UnidadeDeSaudeHasUsuarios " + unidadeDeSaudeHasUsuariosListOldUnidadeDeSaudeHasUsuarios + " since its unidadeDeSaude field is not nullable.");
                }
            }
            for (Contato contatoListOldContato : contatoListOld) {
                if (!contatoListNew.contains(contatoListOldContato)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contato " + contatoListOldContato + " since its unidadeDeSaudeIdunidadeDeSaude field is not nullable.");
                }
            }
            for (Exames examesListOldExames : examesListOld) {
                if (!examesListNew.contains(examesListOldExames)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Exames " + examesListOldExames + " since its unidadeDeSaudeIdUnidadeDeSaude field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Especialidade> attachedEspecialidadeListNew = new ArrayList<Especialidade>();
            for (Especialidade especialidadeListNewEspecialidadeToAttach : especialidadeListNew) {
                especialidadeListNewEspecialidadeToAttach = em.getReference(especialidadeListNewEspecialidadeToAttach.getClass(), especialidadeListNewEspecialidadeToAttach.getIdEspecialidade());
                attachedEspecialidadeListNew.add(especialidadeListNewEspecialidadeToAttach);
            }
            especialidadeListNew = attachedEspecialidadeListNew;
            unidadeDeSaude.setEspecialidadeList(especialidadeListNew);
            List<UnidadeDeSaudeHasUsuarios> attachedUnidadeDeSaudeHasUsuariosListNew = new ArrayList<UnidadeDeSaudeHasUsuarios>();
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuariosToAttach : unidadeDeSaudeHasUsuariosListNew) {
                unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuariosToAttach = em.getReference(unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuariosToAttach.getClass(), unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuariosToAttach.getUnidadeDeSaudeHasUsuariosPK());
                attachedUnidadeDeSaudeHasUsuariosListNew.add(unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuariosToAttach);
            }
            unidadeDeSaudeHasUsuariosListNew = attachedUnidadeDeSaudeHasUsuariosListNew;
            unidadeDeSaude.setUnidadeDeSaudeHasUsuariosList(unidadeDeSaudeHasUsuariosListNew);
            List<Contato> attachedContatoListNew = new ArrayList<Contato>();
            for (Contato contatoListNewContatoToAttach : contatoListNew) {
                contatoListNewContatoToAttach = em.getReference(contatoListNewContatoToAttach.getClass(), contatoListNewContatoToAttach.getIdContato());
                attachedContatoListNew.add(contatoListNewContatoToAttach);
            }
            contatoListNew = attachedContatoListNew;
            unidadeDeSaude.setContatoList(contatoListNew);
            List<Exames> attachedExamesListNew = new ArrayList<Exames>();
            for (Exames examesListNewExamesToAttach : examesListNew) {
                examesListNewExamesToAttach = em.getReference(examesListNewExamesToAttach.getClass(), examesListNewExamesToAttach.getIdExames());
                attachedExamesListNew.add(examesListNewExamesToAttach);
            }
            examesListNew = attachedExamesListNew;
            unidadeDeSaude.setExamesList(examesListNew);
            unidadeDeSaude = em.merge(unidadeDeSaude);
            for (Especialidade especialidadeListOldEspecialidade : especialidadeListOld) {
                if (!especialidadeListNew.contains(especialidadeListOldEspecialidade)) {
                    especialidadeListOldEspecialidade.getUnidadeDeSaudeList().remove(unidadeDeSaude);
                    especialidadeListOldEspecialidade = em.merge(especialidadeListOldEspecialidade);
                }
            }
            for (Especialidade especialidadeListNewEspecialidade : especialidadeListNew) {
                if (!especialidadeListOld.contains(especialidadeListNewEspecialidade)) {
                    especialidadeListNewEspecialidade.getUnidadeDeSaudeList().add(unidadeDeSaude);
                    especialidadeListNewEspecialidade = em.merge(especialidadeListNewEspecialidade);
                }
            }
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios : unidadeDeSaudeHasUsuariosListNew) {
                if (!unidadeDeSaudeHasUsuariosListOld.contains(unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios)) {
                    UnidadeDeSaude oldUnidadeDeSaudeOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios = unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios.getUnidadeDeSaude();
                    unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios.setUnidadeDeSaude(unidadeDeSaude);
                    unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios = em.merge(unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios);
                    if (oldUnidadeDeSaudeOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios != null && !oldUnidadeDeSaudeOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios.equals(unidadeDeSaude)) {
                        oldUnidadeDeSaudeOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios.getUnidadeDeSaudeHasUsuariosList().remove(unidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios);
                        oldUnidadeDeSaudeOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios = em.merge(oldUnidadeDeSaudeOfUnidadeDeSaudeHasUsuariosListNewUnidadeDeSaudeHasUsuarios);
                    }
                }
            }
            for (Contato contatoListNewContato : contatoListNew) {
                if (!contatoListOld.contains(contatoListNewContato)) {
                    UnidadeDeSaude oldUnidadeDeSaudeIdunidadeDeSaudeOfContatoListNewContato = contatoListNewContato.getUnidadeDeSaudeIdunidadeDeSaude();
                    contatoListNewContato.setUnidadeDeSaudeIdunidadeDeSaude(unidadeDeSaude);
                    contatoListNewContato = em.merge(contatoListNewContato);
                    if (oldUnidadeDeSaudeIdunidadeDeSaudeOfContatoListNewContato != null && !oldUnidadeDeSaudeIdunidadeDeSaudeOfContatoListNewContato.equals(unidadeDeSaude)) {
                        oldUnidadeDeSaudeIdunidadeDeSaudeOfContatoListNewContato.getContatoList().remove(contatoListNewContato);
                        oldUnidadeDeSaudeIdunidadeDeSaudeOfContatoListNewContato = em.merge(oldUnidadeDeSaudeIdunidadeDeSaudeOfContatoListNewContato);
                    }
                }
            }
            for (Exames examesListNewExames : examesListNew) {
                if (!examesListOld.contains(examesListNewExames)) {
                    UnidadeDeSaude oldUnidadeDeSaudeIdUnidadeDeSaudeOfExamesListNewExames = examesListNewExames.getUnidadeDeSaudeIdUnidadeDeSaude();
                    examesListNewExames.setUnidadeDeSaudeIdUnidadeDeSaude(unidadeDeSaude);
                    examesListNewExames = em.merge(examesListNewExames);
                    if (oldUnidadeDeSaudeIdUnidadeDeSaudeOfExamesListNewExames != null && !oldUnidadeDeSaudeIdUnidadeDeSaudeOfExamesListNewExames.equals(unidadeDeSaude)) {
                        oldUnidadeDeSaudeIdUnidadeDeSaudeOfExamesListNewExames.getExamesList().remove(examesListNewExames);
                        oldUnidadeDeSaudeIdUnidadeDeSaudeOfExamesListNewExames = em.merge(oldUnidadeDeSaudeIdUnidadeDeSaudeOfExamesListNewExames);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = unidadeDeSaude.getIdUnidadeDeSaude();
                if (findUnidadeDeSaude(id) == null) {
                    throw new NonexistentEntityException("The unidadeDeSaude with id " + id + " no longer exists.");
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
            UnidadeDeSaude unidadeDeSaude;
            try {
                unidadeDeSaude = em.getReference(UnidadeDeSaude.class, id);
                unidadeDeSaude.getIdUnidadeDeSaude();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadeDeSaude with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<UnidadeDeSaudeHasUsuarios> unidadeDeSaudeHasUsuariosListOrphanCheck = unidadeDeSaude.getUnidadeDeSaudeHasUsuariosList();
            for (UnidadeDeSaudeHasUsuarios unidadeDeSaudeHasUsuariosListOrphanCheckUnidadeDeSaudeHasUsuarios : unidadeDeSaudeHasUsuariosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UnidadeDeSaude (" + unidadeDeSaude + ") cannot be destroyed since the UnidadeDeSaudeHasUsuarios " + unidadeDeSaudeHasUsuariosListOrphanCheckUnidadeDeSaudeHasUsuarios + " in its unidadeDeSaudeHasUsuariosList field has a non-nullable unidadeDeSaude field.");
            }
            List<Contato> contatoListOrphanCheck = unidadeDeSaude.getContatoList();
            for (Contato contatoListOrphanCheckContato : contatoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UnidadeDeSaude (" + unidadeDeSaude + ") cannot be destroyed since the Contato " + contatoListOrphanCheckContato + " in its contatoList field has a non-nullable unidadeDeSaudeIdunidadeDeSaude field.");
            }
            List<Exames> examesListOrphanCheck = unidadeDeSaude.getExamesList();
            for (Exames examesListOrphanCheckExames : examesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UnidadeDeSaude (" + unidadeDeSaude + ") cannot be destroyed since the Exames " + examesListOrphanCheckExames + " in its examesList field has a non-nullable unidadeDeSaudeIdUnidadeDeSaude field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Especialidade> especialidadeList = unidadeDeSaude.getEspecialidadeList();
            for (Especialidade especialidadeListEspecialidade : especialidadeList) {
                especialidadeListEspecialidade.getUnidadeDeSaudeList().remove(unidadeDeSaude);
                especialidadeListEspecialidade = em.merge(especialidadeListEspecialidade);
            }
            em.remove(unidadeDeSaude);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidadeDeSaude> findUnidadeDeSaudeEntities() {
        return findUnidadeDeSaudeEntities(true, -1, -1);
    }

    public List<UnidadeDeSaude> findUnidadeDeSaudeEntities(int maxResults, int firstResult) {
        return findUnidadeDeSaudeEntities(false, maxResults, firstResult);
    }

    private List<UnidadeDeSaude> findUnidadeDeSaudeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadeDeSaude.class));
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

    public UnidadeDeSaude findUnidadeDeSaude(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadeDeSaude.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadeDeSaudeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadeDeSaude> rt = cq.from(UnidadeDeSaude.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
