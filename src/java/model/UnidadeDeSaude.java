package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author klebson
 */
@Entity
@Table(name = "unidade_de_saude")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadeDeSaude.findAll", query = "SELECT u FROM UnidadeDeSaude u"),
    @NamedQuery(name = "UnidadeDeSaude.findByIdunidadeDeSaude", query = "SELECT u FROM UnidadeDeSaude u WHERE u.idunidadeDeSaude = :idunidadeDeSaude"),
    @NamedQuery(name = "UnidadeDeSaude.findByNomeUnidade", query = "SELECT u FROM UnidadeDeSaude u WHERE u.nomeUnidade = :nomeUnidade"),
    @NamedQuery(name = "UnidadeDeSaude.findByTipo", query = "SELECT u FROM UnidadeDeSaude u WHERE u.tipo = :tipo"),
    @NamedQuery(name = "UnidadeDeSaude.findByCnpj", query = "SELECT u FROM UnidadeDeSaude u WHERE u.cnpj = :cnpj"),
    @NamedQuery(name = "UnidadeDeSaude.findByInformacao", query = "SELECT u FROM UnidadeDeSaude u WHERE u.informacao = :informacao")})
public class UnidadeDeSaude implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idunidade_de_saude")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idunidadeDeSaude;
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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "unidadeDeSaude")
    private Contato contato;
    @JoinColumn(name = "idunidade_de_saude", referencedColumnName = "id_contato", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Contato contato1;
    @JoinColumn(name = "idunidade_de_saude", referencedColumnName = "id_endereco", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Endereco2 endereco;

    public UnidadeDeSaude() {
    }

    public UnidadeDeSaude(Integer idunidadeDeSaude) {
        this.idunidadeDeSaude = idunidadeDeSaude;
    }

    public UnidadeDeSaude(Integer idunidadeDeSaude, String nomeUnidade, int tipo) {
        this.idunidadeDeSaude = idunidadeDeSaude;
        this.nomeUnidade = nomeUnidade;
        this.tipo = tipo;
    }

    public Integer getIdunidadeDeSaude() {
        return idunidadeDeSaude;
    }

    public void setIdunidadeDeSaude(Integer idunidadeDeSaude) {
        this.idunidadeDeSaude = idunidadeDeSaude;
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

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Contato getContato1() {
        return contato1;
    }

    public void setContato1(Contato contato1) {
        this.contato1 = contato1;
    }

    public Endereco2 getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco2 endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idunidadeDeSaude != null ? idunidadeDeSaude.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadeDeSaude)) {
            return false;
        }
        UnidadeDeSaude other = (UnidadeDeSaude) object;
        if ((this.idunidadeDeSaude == null && other.idunidadeDeSaude != null) || (this.idunidadeDeSaude != null && !this.idunidadeDeSaude.equals(other.idunidadeDeSaude))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.UnidadeDeSaude[ idunidadeDeSaude=" + idunidadeDeSaude + " ]";
    }

    public void setTipo(String emergencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
