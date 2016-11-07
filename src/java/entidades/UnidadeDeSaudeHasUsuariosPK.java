package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author klebson
 */
@Embeddable
public class UnidadeDeSaudeHasUsuariosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "unidade_de_saude_idunidade_de_saude")
    private int unidadeDeSaudeIdunidadeDeSaude;
    @Basic(optional = false)
    @Column(name = "usuarios_id_usuarios")
    private int usuariosIdUsuarios;

    public UnidadeDeSaudeHasUsuariosPK() {
    }

    public UnidadeDeSaudeHasUsuariosPK(int unidadeDeSaudeIdunidadeDeSaude, int usuariosIdUsuarios) {
        this.unidadeDeSaudeIdunidadeDeSaude = unidadeDeSaudeIdunidadeDeSaude;
        this.usuariosIdUsuarios = usuariosIdUsuarios;
    }

    public int getUnidadeDeSaudeIdunidadeDeSaude() {
        return unidadeDeSaudeIdunidadeDeSaude;
    }

    public void setUnidadeDeSaudeIdunidadeDeSaude(int unidadeDeSaudeIdunidadeDeSaude) {
        this.unidadeDeSaudeIdunidadeDeSaude = unidadeDeSaudeIdunidadeDeSaude;
    }

    public int getUsuariosIdUsuarios() {
        return usuariosIdUsuarios;
    }

    public void setUsuariosIdUsuarios(int usuariosIdUsuarios) {
        this.usuariosIdUsuarios = usuariosIdUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) unidadeDeSaudeIdunidadeDeSaude;
        hash += (int) usuariosIdUsuarios;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadeDeSaudeHasUsuariosPK)) {
            return false;
        }
        UnidadeDeSaudeHasUsuariosPK other = (UnidadeDeSaudeHasUsuariosPK) object;
        if (this.unidadeDeSaudeIdunidadeDeSaude != other.unidadeDeSaudeIdunidadeDeSaude) {
            return false;
        }
        if (this.usuariosIdUsuarios != other.usuariosIdUsuarios) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UnidadeDeSaudeHasUsuariosPK[ unidadeDeSaudeIdunidadeDeSaude=" + unidadeDeSaudeIdunidadeDeSaude + ", usuariosIdUsuarios=" + usuariosIdUsuarios + " ]";
    }
    
}
