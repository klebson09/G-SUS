package beans;

import com.sun.net.httpserver.HttpServer;
import dao.UsuariosJpaController;
import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import entidades.Usuarios;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@ManagedBean(name = "UsuarioRequest_mb")
@RequestScoped
public class UsuarioRequest implements Serializable {

    String nome;
    int tipo;
    String codig_ss;
    String senha;
    String telefone;
    String email;

    private UsuariosJpaController UsuariosDAO;

    public UsuarioRequest() {
    }

    public void init() {
        UsuariosDAO = new UsuariosJpaController();
    }

    public String adicionar() {
        Usuarios us = new Usuarios();
        us.setNome(nome);
        us.setTipo(tipo);
        us.setCodigoSs(codig_ss);
        us.setEmail(email);
        us.setTelefone(telefone);
        us.setSenha(senha);

        UsuariosDAO.create(us);
        return "tp_ListarGestor";
    }

    public void ExcluirUsuario(Usuarios usuario) {
        try {
            UsuariosDAO.destroy(usuario.getIdUsuarios());
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(req.getRequestURI());
            
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(UsuarioRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("----->>>chegou aqui método excluir usuario<<<-------");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCodig_ss() {
        return codig_ss;
    }

    public void setCodig_ss(String codig_ss) {
        this.codig_ss = codig_ss;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsuariosJpaController getUsuariosDAO() {
        return UsuariosDAO;
    }

    public void setUsuariosDAO(UsuariosJpaController UsuariosDAO) {
        this.UsuariosDAO = UsuariosDAO;
    }
}
