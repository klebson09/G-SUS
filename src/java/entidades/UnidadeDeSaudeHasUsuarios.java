/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author klebson
 */
@Entity
@Table(name = "unidade_de_saude_has_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadeDeSaudeHasUsuarios.findAll", query = "SELECT u FROM UnidadeDeSaudeHasUsuarios u"),
    @NamedQuery(name = "UnidadeDeSaudeHasUsuarios.findByUnidadeDeSaudeIdunidadeDeSaude", query = "SELECT u FROM UnidadeDeSaudeHasUsuarios u WHERE u.unidadeDeSaudeHasUsuariosPK.unidadeDeSaudeIdunidadeDeSaude = :unidadeDeSaudeIdunidadeDeSaude"),
    @NamedQuery(name = "UnidadeDeSaudeHasUsuarios.findByUsuariosIdUsuarios", query = "SELECT u FROM UnidadeDeSaudeHasUsuarios u WHERE u.unidadeDeSaudeHasUsuariosPK.usuariosIdUsuarios = :usuariosIdUsuarios"),
    @NamedQuery(name = "UnidadeDeSaudeHasUsuarios.findByNota", query = "SELECT u FROM UnidadeDeSaudeHasUsuarios u WHERE u.nota = :nota")})
public class UnidadeDeSaudeHasUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UnidadeDeSaudeHasUsuariosPK unidadeDeSaudeHasUsuariosPK;
    @Column(name = "nota")
    private Integer nota;
    @JoinColumn(name = "unidade_de_saude_idunidade_de_saude", referencedColumnName = "id_unidade_de_saude", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UnidadeDeSaude unidadeDeSaude;
    @JoinColumn(name = "usuarios_id_usuarios", referencedColumnName = "id_usuarios", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuarios usuarios;

    public UnidadeDeSaudeHasUsuarios() {
    }

    public UnidadeDeSaudeHasUsuarios(UnidadeDeSaudeHasUsuariosPK unidadeDeSaudeHasUsuariosPK) {
        this.unidadeDeSaudeHasUsuariosPK = unidadeDeSaudeHasUsuariosPK;
    }

    public UnidadeDeSaudeHasUsuarios(int unidadeDeSaudeIdunidadeDeSaude, int usuariosIdUsuarios) {
        this.unidadeDeSaudeHasUsuariosPK = new UnidadeDeSaudeHasUsuariosPK(unidadeDeSaudeIdunidadeDeSaude, usuariosIdUsuarios);
    }

    public UnidadeDeSaudeHasUsuariosPK getUnidadeDeSaudeHasUsuariosPK() {
        return unidadeDeSaudeHasUsuariosPK;
    }

    public void setUnidadeDeSaudeHasUsuariosPK(UnidadeDeSaudeHasUsuariosPK unidadeDeSaudeHasUsuariosPK) {
        this.unidadeDeSaudeHasUsuariosPK = unidadeDeSaudeHasUsuariosPK;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public UnidadeDeSaude getUnidadeDeSaude() {
        return unidadeDeSaude;
    }

    public void setUnidadeDeSaude(UnidadeDeSaude unidadeDeSaude) {
        this.unidadeDeSaude = unidadeDeSaude;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unidadeDeSaudeHasUsuariosPK != null ? unidadeDeSaudeHasUsuariosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadeDeSaudeHasUsuarios)) {
            return false;
        }
        UnidadeDeSaudeHasUsuarios other = (UnidadeDeSaudeHasUsuarios) object;
        if ((this.unidadeDeSaudeHasUsuariosPK == null && other.unidadeDeSaudeHasUsuariosPK != null) || (this.unidadeDeSaudeHasUsuariosPK != null && !this.unidadeDeSaudeHasUsuariosPK.equals(other.unidadeDeSaudeHasUsuariosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UnidadeDeSaudeHasUsuarios[ unidadeDeSaudeHasUsuariosPK=" + unidadeDeSaudeHasUsuariosPK + " ]";
    }
    
}
