package beans;

import dao.UsuariosJpaController;
import entidades.Usuarios;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 * @author klebson
 */
@ManagedBean (name = "GestorView_mb")
@ViewScoped
public class GestorView {

    public GestorView() {
    }
    
    private List<Usuarios> usuarios;
    
    private UsuariosJpaController usuariosDAO;
    
    @PostConstruct
    public void init(){ //por q o nome desse metodo é init... é padrão? ou é um nome qualquer ?
        usuariosDAO = new UsuariosJpaController();
        usuarios = usuariosDAO.findUsuariosEntities();
    }
    
    public List<Usuarios> getUsuarios(){
        return usuarios;
    }
    
    public void setUsuarios (List<Usuarios> usuarios){
        this.usuarios = usuarios;
    }
}
