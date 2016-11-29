package beans;
import dao.UsuariosJpaController;
import entidades.Usuarios;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 * @author klebson
 */
@ManagedBean (name = "Login_mb")
@SessionScoped
public class LoginBeans implements Serializable{
    String email;
    String senha;
    
    
     @PostConstruct
    public void init() {
        usuarioDAO = new UsuariosJpaController();
        
    }
    private UsuariosJpaController usuarioDAO;
    public LoginBeans() {
    }
   
    
    public String logar(){
        String mensagem = null;
        Usuarios u = usuarioDAO.findLogin(email, senha);
        //cria uma sessão caso o usuário exista
        if(u != null){
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("usuarioLogado", u);
            mensagem = "Usuário encontrado";
        }else {
            mensagem = "usuário não encontrado";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", mensagem));
        return "tp_index.xhtml";
    }
    public String logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "tp_index.xhtml";//redireção da página
    }
    
    public String bt_unidade_saude(){
        return "tp_ListarUnidadeDeSaude.xhtml";
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
