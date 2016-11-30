package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author klebson
 */
@Entity
@Table(name = "especialidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Especialidade.findAll", query = "SELECT e FROM Especialidade e"),
    @NamedQuery(name = "Especialidade.findByIdEspecialidade", query = "SELECT e FROM Especialidade e WHERE e.idEspecialidade = :idEspecialidade"),
    @NamedQuery(name = "Especialidade.findByNome", query = "SELECT e FROM Especialidade e WHERE e.nome = :nome"),
    @NamedQuery(name = "Especialidade.findByTipo", query = "SELECT e FROM Especialidade e WHERE e.tipo = :tipo")})
public class Especialidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_especialidade")
    private Integer idEspecialidade;
    @Column(name = "nome")
    @NotNull
    private String nome;
    @Column(name = "tipo")
    private Integer tipo;
    @JoinTable(name = "unidade_de_saude_has_especialidade", joinColumns = {
        @JoinColumn(name = "especialidade_id_especialidade", referencedColumnName = "id_especialidade")}, inverseJoinColumns = {
        @JoinColumn(name = "unidade_de_saude_id_unidade_de_saude", referencedColumnName = "id_unidade_de_saude")})
    @ManyToMany
    private List<UnidadeDeSaude> unidadeDeSaudeList;

    public Especialidade() {
    }

    public Especialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<UnidadeDeSaude> getUnidadeDeSaudeList() {
        return unidadeDeSaudeList;
    }

    public void setUnidadeDeSaudeList(List<UnidadeDeSaude> unidadeDeSaudeList) {
        this.unidadeDeSaudeList = unidadeDeSaudeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEspecialidade != null ? idEspecialidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidade)) {
            return false;
        }
        Especialidade other = (Especialidade) object;
        if ((this.idEspecialidade == null && other.idEspecialidade != null) || (this.idEspecialidade != null && !this.idEspecialidade.equals(other.idEspecialidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Especialidade[ idEspecialidade=" + idEspecialidade + " ]";
    }
    
}
