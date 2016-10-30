package entidades;


/**
 *
 * @author klebson
 */
public class UnidadeDeSaude {

//    private Integer idUnidadeDeSaude;
    private String nomeUnidade;
    private String tipo;
    private String cnpj;
    private String telefone;
    private String email;
    private String informacao;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;

    public UnidadeDeSaude() {
    }

//    public UnidadeDeSaude(Integer idUnidadeDeSaude) {
//        this.idUnidadeDeSaude = idUnidadeDeSaude;
//    }
//
//    public Integer getIdUnidadeDeSaude() {
//        return idUnidadeDeSaude;
//    }
//
//    public void setIdUnidadeDeSaude(Integer idUnidadeDeSaude) {
//        this.idUnidadeDeSaude = idUnidadeDeSaude;
//    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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
}
