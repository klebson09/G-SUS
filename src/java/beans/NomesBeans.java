
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author klebson
 * 
 * 
 * classe gerenciadora dos beans
 * intermedio entre xhtml e java
 */
@SessionScoped //o tempo em q a classe vai ficar ativa
@ManagedBean
public class NomesBeans {
    private String nome;
    private String sobreNome;
    private String teste;


    public void testar(){
        teste = "cheguei aqui -->" + nome + " " + sobreNome;
    }
    
    
    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }
    
}
