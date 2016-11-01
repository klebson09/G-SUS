package beans;

import dao.ContatoJpaController;
import dao.UnidadeDeSaudeJpaController;
import dao.exceptions.PreexistingEntityException;
import entidades.Contato;
import entidades.UnidadeDeSaude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author klebson Cadastrar unidade de saúde
 */
@ManagedBean(name = "UnidadeDeSaudeBean_mb")
@RequestScoped
public class UnidadeDeSaudeBean implements Serializable {

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

    private UnidadeDeSaudeJpaController unidadeDAO; //US -> UNIDADE DE SAÚDE
    private ContatoJpaController contatoDAO;

    public UnidadeDeSaudeBean() {
        unidadeDAO = new UnidadeDeSaudeJpaController();
        contatoDAO = new ContatoJpaController();

    }

    public List<UnidadeDeSaude> listarUnidadeDeSAude(){
        return unidadeDAO.findUnidadeDeSaudeEntities();
    }
    
    public void adicionar() {
        UnidadeDeSaude und = new UnidadeDeSaude();
        und.setNomeUnidade(nome);
        und.setTipo(tipo);
        und.setCnpj(cnpj);
        und.setInformacao(informacao);
        und.setCep(cep);
        und.setNumero(numero);
        und.setComplemento(complemento);

        unidadeDAO.create(und); //envia und apos criar

        Contato cnt = new Contato();
        cnt.setTelefone(telefone);
        cnt.setEmail(email);
        cnt.setUnidadeDeSaudeIdunidadeDeSaude(und); // recebe o und (unidade de saúde)

        contatoDAO.create(cnt);

        System.out.println("OK");

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
