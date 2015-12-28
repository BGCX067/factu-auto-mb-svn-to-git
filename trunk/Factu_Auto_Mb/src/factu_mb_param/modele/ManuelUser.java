package factu_mb_param.modele;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ManuelUser {
    
    /**
     * @param args
     * @throws WriteException 
     * @throws RowsExceededException 
     */
	private String path = "C:/Program Files/Facture_MB/";
	
	public ManuelUser(){
	}
     
     
    public  void loadManuelUtilisation() { 
    	File file= new File(path+"Manuel_Utilisateur_Factu_MB.pdf");
    	try {
    		System.out.println("avant ouverture");
			Desktop.getDesktop().open(file) ;
			System.out.println("apres ouverture");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
}
