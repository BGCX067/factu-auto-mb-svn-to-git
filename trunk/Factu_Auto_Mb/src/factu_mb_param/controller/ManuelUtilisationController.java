package factu_mb_param.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import factu_mb_param.modele.ManuelUser;



public class ManuelUtilisationController implements ActionListener{
	
	private ManuelUser manuelUser;
	
	public ManuelUtilisationController(){
		manuelUser=new ManuelUser();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
				System.out.println("JE SUIS DANS LE MAN UTIL");	
				
					manuelUser.loadManuelUtilisation();
					
		}				
}



