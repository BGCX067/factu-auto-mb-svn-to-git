package factu_mb_param.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import factu_mb_param.view.Facture_Panel_View;



public class CheckBoxAvoirController implements ActionListener{
	
	private Facture_Panel_View facture_Panel_View;
	
	
	public CheckBoxAvoirController(Facture_Panel_View facture_Panel_View){
		this.facture_Panel_View= facture_Panel_View;
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(facture_Panel_View.cbAvoirs.isSelected()){
			System.out.println("FACTURE AVOIR DARTY SELECTED");
			facture_Panel_View.btAvoir.setVisible(true);
			facture_Panel_View.btValider.setVisible(false);
		}else{
			System.out.println("FACTURE AVOIR DARTY DESELECTED");
			facture_Panel_View.btValider.setVisible(true);
			facture_Panel_View.btAvoir.setVisible(false);
		}		
	}
}


