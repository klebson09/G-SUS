package testes;

import dao.UnidadeDeSaudeDAO;
import model.UnidadeDeSaude2;

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
        UnidadeDeSaude2 unidadeDeSaude = new UnidadeDeSaude2();
        
        unidadeDeSaude.setNomeUnidade("Hospital da Mulher");
        unidadeDeSaude.setTipo("Emergencia");
        unidadeDeSaude.setCnpj("12345");
        unidadeDeSaude.setInformacao("aqui contem informacoes da und de saude");
        unidadeDeSaudeDao.persist(unidadeDeSaude);
        System.out.println("Fim Teste unidade de saúde");
        
    }
    
}
