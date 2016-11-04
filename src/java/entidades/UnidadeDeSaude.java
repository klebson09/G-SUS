package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author klebson
 */
@Entity
@Table(name = "unidade_de_saude")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadeDeSaude.findAll", query = "SELECT u FROM UnidadeDeSaude u"),
    @NamedQuery(name = "UnidadeDeSaude.findByIdUnidadeDeSaude", query = "SELECT u FROM UnidadeDeSaude u WHERE u.idUnidadeDeSaude = :idUnidadeDeSaude"),
    @NamedQuery(name = "UnidadeDeSaude.findByNomeUnidade", query = "SELECT u FROM UnidadeDeSaude u WHERE u.nomeUnidade = :nomeUnidade"),
    @NamedQuery(name = "UnidadeDeSaude.findByTipo", query = "SELECT u FROM UnidadeDeSaude u WHERE u.tipo = :tipo"),
    @NamedQuery(name = "UnidadeDeSaude.findByCnpj", query = "SELECT u FROM UnidadeDeSaude u WHERE u.cnpj = :cnpj"),
    @NamedQuery(name = "UnidadeDeSaude.findByInformacao", query = "SELECT u FROM UnidadeDeSaude u WHERE u.informacao = :informacao"),
    @NamedQuery(name = "UnidadeDeSaude.findByCep", query = "SELECT u FROM UnidadeDeSaude u WHERE u.cep = :cep"),
    @NamedQuery(name = "UnidadeDeSaude.findByNumero", query = "SELECT u FROM UnidadeDeSaude u WHERE u.numero = :numero"),
    @NamedQuery(name = "UnidadeDeSaude.findByComplemento", query = "SELECT u FROM UnidadeDeSaude u WHERE u.complemento = :complemento")})
public class UnidadeDeSaude implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidade_de_saude")
    private Integer idUnidadeDeSaude;
    @Basic(optional = false)
    @Column(name = "nome_unidade")
    private String nomeUnidade;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "informacao")
    private String informacao;
    @Column(name = "cep")
    private String cep;
    @Column(name = "numero")
    private String numero;
    @Column(name = "complemento")
    private String complemento;
    @ManyToMany(mappedBy = "unidadeDeSaudeList")
    private List<Especialidade> especialidadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadeDeSaude")
    private List<UnidadeDeSaudeHasUsuarios> unidadeDeSaudeHasUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadeDeSaudeIdunidadeDeSaude")
    private List<Contato> contatoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadeDeSaudeIdUnidadeDeSaude")
    private List<Exames> examesList;

    public UnidadeDeSaude() {
    }

    public UnidadeDeSaude(Integer idUnidadeDeSaude) {
        this.idUnidadeDeSaude = idUnidadeDeSaude;
    }

    public UnidadeDeSaude(Integer idUnidadeDeSaude, String nomeUnidade, int tipo) {
        this.idUnidadeDeSaude = idUnidadeDeSaude;
        this.nomeUnidade = nomeUnidade;
        this.tipo = tipo;
    }

    public Integer getIdUnidadeDeSaude() {
        return idUnidadeDeSaude;
    }

    public void setIdUnidadeDeSaude(Integer idUnidadeDeSaude) {
        this.idUnidadeDeSaude = idUnidadeDeSaude;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @XmlTransient
    public List<Especialidade> getEspecialidadeList() {
        return especialidadeList;
    }

    public void setEspecialidadeList(List<Especialidade> especialidadeList) {
        this.especialidadeList = especialidadeList;
    }

    @XmlTransient
    public List<UnidadeDeSaudeHasUsuarios> getUnidadeDeSaudeHasUsuariosList() {
        return unidadeDeSaudeHasUsuariosList;
    }

    public void setUnidadeDeSaudeHasUsuariosList(List<UnidadeDeSaudeHasUsuarios> unidadeDeSaudeHasUsuariosList) {
        this.unidadeDeSaudeHasUsuariosList = unidadeDeSaudeHasUsuariosList;
    }

    @XmlTransient
    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void setContatoList(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }

    @XmlTransient
    public List<Exames> getExamesList() {
        return examesList;
    }

    public void setExamesList(List<Exames> examesList) {
        this.examesList = examesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadeDeSaude != null ? idUnidadeDeSaude.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadeDeSaude)) {
            return false;
        }
        UnidadeDeSaude other = (UnidadeDeSaude) object;
        if ((this.idUnidadeDeSaude == null && other.idUnidadeDeSaude != null) || (this.idUnidadeDeSaude != null && !this.idUnidadeDeSaude.equals(other.idUnidadeDeSaude))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UnidadeDeSaude[ idUnidadeDeSaude=" + idUnidadeDeSaude + " ]";
    }
    
}
