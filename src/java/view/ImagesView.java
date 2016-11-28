package view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 * @author klebson
 */

    
@ManagedBean (name = "ImagesView")
public class ImagesView{
    private String texto1 = "O E-SUS é um site onde o paciente tem acesso a informação da unidade de saúde, como endereço, número telefônico, marcação, exames oferecidos, especialistas e classificação dos melhores hospitais, postos de saúde, emergências e ambulatórios.";
    private List<String> images;
    
    @PostConstruct
    public void init(){
        images = new ArrayList<String>();
        for (int i=1; i<=3; i++){
            images.add("img"+ i + ".jpg");
        }
    }
    
    public List<String> getImages(){
        return images;
    }
            
    
    
    
}
