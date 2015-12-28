package factu_mb_param.main;
import java.awt.Image;
import java.awt.Toolkit;

import factu_mb_param.view.Connection_View;



public class FactuAutoMBMain {
	
	public static void main(String[]args){
	Connection_View con= new Connection_View();
	Image icone = Toolkit.getDefaultToolkit().getImage(FactuAutoMBMain.class.getResource("/cptl.png"));
	con.setIconImage(icone);
	System.setProperty( "file.encoding", "UTF-8" );
	con.setTitle("Authentification Factu Auto Marque Blanche");
	con.pack();
	con.setLocationRelativeTo(con.getParent());
	con.setVisible(true);
	}
}