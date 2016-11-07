package beans;

import dao.ContatoJpaController;
import dao.UnidadeDeSaudeJpaController;
import entidades.Contato;
import entidades.UnidadeDeSaude;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author klebson
 */
@ManagedBean(name = "UnidadeView_mb")
@ViewScoped
public class UnidadeView implements Serializable {
    
    private List<UnidadeDeSaude> unidades;

    private UnidadeDeSaudeJpaController unidadeDAO; //US -> UNIDADE DE SAÚDE

    public UnidadeView() {
    }

    @PostConstruct
    public void init() {
        unidadeDAO = new UnidadeDeSaudeJpaController();
        unidades = unidadeDAO.findUnidadeDeSaudeEntities();
    }
    /**
     * @return the unidades
     */
    public List<UnidadeDeSaude> getUnidades() {
        return unidades;
    }

    /**
     * @param unidades the unidades to set
     */
    public void setUnidades(List<UnidadeDeSaude> unidades) {
        this.unidades = unidades;
    }

}
