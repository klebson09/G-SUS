package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author klebson
 */
@Entity
@Table(name = "exames")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exames.findAll", query = "SELECT e FROM Exames e"),
    @NamedQuery(name = "Exames.findByIdExames", query = "SELECT e FROM Exames e WHERE e.idExames = :idExames"),
    @NamedQuery(name = "Exames.findByNome", query = "SELECT e FROM Exames e WHERE e.nome = :nome"),
    @NamedQuery(name = "Exames.findByDescricao", query = "SELECT e FROM Exames e WHERE e.descricao = :descricao")})
public class Exames implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_exames")
    private Integer idExames;
    @Basic(optional = false)
    @Column(name = "nome")
    @NotNull
    private String nome;
    @Column(name = "descricao")
    @Max(value=400)
    @NotNull
    private String descricao;
    @JoinColumn(name = "unidade_de_saude_id_unidade_de_saude", referencedColumnName = "id_unidade_de_saude")
    @ManyToOne(optional = false)
    private UnidadeDeSaude unidadeDeSaudeIdUnidadeDeSaude;

    public Exames() {
    }

    public Exames(Integer idExames) {
        this.idExames = idExames;
    }

    public Exames(Integer idExames, String nome) {
        this.idExames = idExames;
        this.nome = nome;
    }

    public Integer getIdExames() {
        return idExames;
    }

    public void setIdExames(Integer idExames) {
        this.idExames = idExames;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UnidadeDeSaude getUnidadeDeSaudeIdUnidadeDeSaude() {
        return unidadeDeSaudeIdUnidadeDeSaude;
    }

    public void setUnidadeDeSaudeIdUnidadeDeSaude(UnidadeDeSaude unidadeDeSaudeIdUnidadeDeSaude) {
        this.unidadeDeSaudeIdUnidadeDeSaude = unidadeDeSaudeIdUnidadeDeSaude;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExames != null ? idExames.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exames)) {
            return false;
        }
        Exames other = (Exames) object;
        if ((this.idExames == null && other.idExames != null) || (this.idExames != null && !this.idExames.equals(other.idExames))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Exames[ idExames=" + idExames + " ]";
    }
    
}
