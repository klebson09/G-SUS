package beans;

import dao.UsuariosJpaController;
import entidades.Usuarios;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import static org.eclipse.persistence.expressions.ExpressionOperator.notNull;
import org.hibernate.validator.constraints.Email;

/**
 * @author klebson
 */
@ManagedBean(name = "UsuarioBean_mb")
@RequestScoped
public class UsuarioBeans {
    
    @NotNull
    String nome;
    @NotNull
    int tipo;
    @NotNull
    String codigo_ss;
    @NotNull
    String senha;
    @NotNull
    String telefone;
    @NotNull
    @Email (message = "Email inválido")        
    String email;

    private UsuariosJpaController usuarioDAO;
    
    public UsuarioBeans(){
        usuarioDAO = new UsuariosJpaController();
    }
    
    public List<Usuarios> listarUsuario(){
        return usuarioDAO.findUsuariosEntities();
    }
    
    public void adicionar(){
        Usuarios usr = new Usuarios();
        usr.setNome(nome);
        usr.setTipo(tipo);
        usr.setCodigoSs(codigo_ss);
        usr.setTelefone(telefone);
        usr.setEmail(email);
        usr.setSenha(senha);
        
        usuarioDAO.create(usr);
        
        System.out.println("Fim do método adicionar ---->>>> adicionado");
        
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

    public String getCodigo_ss() {
        return codigo_ss;
    }

    public void setCodigo_ss(String codigo_ss) {
        this.codigo_ss = codigo_ss;
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
    
}
