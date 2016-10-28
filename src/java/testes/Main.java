package testes;

import dao.UnidadeDeSaudeDAO;
import model.UnidadeDeSaude;

/**
 *
 * @author klebson
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Teste insert unidade de saude");
        UnidadeDeSaudeDAO unidadeDeSaudeDao = new UnidadeDeSaudeDAO();
        UnidadeDeSaude unidadeDeSaude = new UnidadeDeSaude();
        
        unidadeDeSaude.setNomeUnidade("Hospital da Mulher");
        unidadeDeSaude.setTipo("Emergencia");
        unidadeDeSaude.setCnpj("12345");
        unidadeDeSaude.setInformacao("aqui contem informacoes da und de saude");
        unidadeDeSaudeDao.persist(unidadeDeSaude);
        System.out.println("Fim Teste unidade de saúde");
        
    }
    
}
