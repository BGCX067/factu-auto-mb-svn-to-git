package factu_mb_param.controller;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import factu_mb_param.modele.Config;
import factu_mb_param.modele.ConnexionModel;
import factu_mb_param.view.Connection_View;
import factu_mb_param.view.Fact_Auto_MB_Main_View;



public class ConnexionController implements ActionListener,KeyListener{
	
	private Connection_View connexionPanel;
	private ConnexionModel connexionModel;
	private Fact_Auto_MB_Main_View bulk_Param_Main_View;
	private Config config;
	
	
	
	
	public ConnexionController(Connection_View connexionPanel){
		this.connexionPanel=connexionPanel;
		connexionModel=new ConnexionModel();
		config=new Config();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource().equals(connexionPanel.btConnection)){
			System.out.println(" Access connexion application ");
			if(connexionPanel.txtNameUser.getText().equalsIgnoreCase(config.getLogin().toString())
			&&	connexionPanel.txtPass.getText().equalsIgnoreCase(config.getPassword().toString())){
				System.out.println("Login mot de passe Oki");
					bulk_Param_Main_View=new Fact_Auto_MB_Main_View();
					connexionPanel.setVisible(false);
					bulk_Param_Main_View.setTitle("FACTU AUTO MARQUE BLANCHE");
					Image icone = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cptl.png"));
					bulk_Param_Main_View.setIconImage(icone);
					bulk_Param_Main_View.pack();
					bulk_Param_Main_View.setLocationRelativeTo(bulk_Param_Main_View.getParent());
					bulk_Param_Main_View.setVisible(true);
			}
			else{
				JOptionPane.showMessageDialog(null,connexionModel.getLOGIN_REGEX_MESSAGE(),"Attention",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			if(connexionPanel.txtNameUser.getText().equalsIgnoreCase(config.getLogin().toString())
					&&	connexionPanel.txtPass.getText().equalsIgnoreCase(config.getPassword().toString())){
						System.out.println("Login mot de passe Oki");
							bulk_Param_Main_View=new Fact_Auto_MB_Main_View();
							connexionPanel.setVisible(false);
							bulk_Param_Main_View.setTitle("FACTU AUTO MARQUE BLANCHE");
							Image icone = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/cptl.png"));
							bulk_Param_Main_View.setIconImage(icone);
							bulk_Param_Main_View.pack();
							bulk_Param_Main_View.setLocationRelativeTo(bulk_Param_Main_View.getParent());
							bulk_Param_Main_View.setVisible(true);				
					}
					else{
						JOptionPane.showMessageDialog(null,connexionModel.getLOGIN_REGEX_MESSAGE(),"Attention",JOptionPane.WARNING_MESSAGE);
					}
				}
		}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub		
	}
}


