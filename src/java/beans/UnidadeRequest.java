package beans;

import dao.ContatoJpaController;
import dao.UnidadeDeSaudeJpaController;
import dao.exceptions.IllegalOrphanException;
import dao.exceptions.NonexistentEntityException;
import entidades.Contato;
import entidades.UnidadeDeSaude;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author klebson
 */
@ManagedBean(name = "UnidadeRequest_mb")
@ViewScoped
public class UnidadeRequest implements Serializable {
    
    String nome;
    int tipo;
    String cnpj;
    String informacao;
    String telefone;
    String email;
    String rua;
    String cep;
    String numero;
    
    String complemento;
    String bairro;
    String cidade;
    String estado;
    
    private UnidadeDeSaudeJpaController unidadeDAO;
    private ContatoJpaController contatoDAO;

    /**
     * Creates a new instance of UnidadeRequest
     */
    public UnidadeRequest() {
    }
    
    @PostConstruct //gerencia o JSF métodos é criado após a instancia do objeto
    public void init() {
        unidadeDAO = new UnidadeDeSaudeJpaController();
        contatoDAO = new ContatoJpaController();
    }
    
    public String adicionar() {
        UnidadeDeSaude und = new UnidadeDeSaude();
        und.setNomeUnidade(nome);
        und.setTipo(tipo);
        und.setCnpj(cnpj);
        und.setInformacao(informacao);
        und.setCep(cep);
        und.setNumero(numero);
        und.setComplemento(complemento);
        
        unidadeDAO.create(und); //envia und apos criar para ser enviado para o dao

        Contato cnt = new Contato();
        cnt.setTelefone(telefone);
        cnt.setEmail(email);
        cnt.setUnidadeDeSaudeIdunidadeDeSaude(und); // recebe o und (unidade de saúde)

        contatoDAO.create(cnt);
        
        System.out.println("OK");
        return "ListarUnidadeDeSaude_2";
    }
    
    public void ExcluirUnidade(UnidadeDeSaude unidade) {
        try {
            for (Contato c : unidade.getContatoList()) {
                contatoDAO.destroy(c.getIdContato());
            }
            unidadeDAO.destroy(unidade.getIdUnidadeDeSaude());
            
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(req.getRequestURI());
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(UnidadeRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UnidadeRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UnidadeRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public String getRua() {
        return rua;
    }
    
    public void setRua(String rua) {
        this.rua = rua;
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
    
    public String getBairro() {
        return bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getCidade() {
        return cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getComplemento() {
        return complemento;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
}
