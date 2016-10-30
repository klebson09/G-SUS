package beans;

import entidades.UnidadeDeSaude;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author klebson
 * Cadastrar unidade de saúde
 */
@ManagedBean
@SessionScoped
public class UnidadeDeSaudeBean {
    
    private UnidadeDeSaude unidadedesaude;
    private List <UnidadeDeSaude> unidades = new ArrayList<>();
    
    
    public void adicionar(){
        System.out.println("inicio metodo adicionar");
        unidades.add(unidadedesaude);
        unidadedesaude = new UnidadeDeSaude(); // os campos da daviavel será limpada (zera a variavel unidadedesaude)
        System.out.println("Fim metodo adicionar  em unidade de saude bean");
    }

    
    public UnidadeDeSaude getUnidadedesaude() {
        return unidadedesaude;
    }

    public void setUnidadedesaude(UnidadeDeSaude unidadedesaude) {
        this.unidadedesaude = unidadedesaude;
    }

    public List<UnidadeDeSaude> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<UnidadeDeSaude> unidades) {
        this.unidades = unidades;
    }
    
    
    
}
