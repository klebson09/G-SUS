package view;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 * @author klebson
 */

    
@ManagedBean
public class ImagesView {
    
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
