package beans;

/**
 * @author klebson
 */
public class UsuarioBeans {
    
    
    String nome;
    int tipo;
    String codigo_ss;
    String senha;
    String telefone;
    String email;

    public UsuarioBeans(){
        
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
