package factu_mb_param.view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import factu_mb_param.controller.ManuelUtilisationController;
import factu_mb_param.footer.header.Footer;
import factu_mb_param.footer.header.Header;

public class Fact_Auto_MB_Main_View extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Header header;
	private Footer footer;
	public JMenuBar menuBar= new JMenuBar();
	public JMenu menuAide= new JMenu("Fichier");
	public JMenuItem menuAideContent= new JMenuItem("Manuel Utilisateur");
	
	private JPanel panelNord;
	private JPanel panelHaut;
	private JPanel panelEditFacture;
	private Facture_Panel_View facture_Panel_View;
	private Rapport_Panel_View rapport_Panel_View;
	private Rapport_NC_View rapport_NC_View;
	
    
	public Fact_Auto_MB_Main_View(){
		
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	  
	    this.setPreferredSize(new Dimension(900,570));
	    
		//Menus
		menuAide.add(menuAideContent);
	  		
		//menuBar
		menuBar.add(menuAide);
	    
	    Container pane = this.getContentPane();	    
		
		//Panel Nord
		panelNord = new JPanel();
		panelNord.setLayout(new FlowLayout());
		
		//Panel Haut
		panelHaut = new JPanel();
		panelHaut.setLayout(new BorderLayout());
		
		//Panel Open_Point
		panelEditFacture = new JPanel();
		panelEditFacture.setLayout(new BorderLayout());
		//ZABPQ OPEN_CONTACT LAYOUT
        header = new Header();	
        footer = new Footer();
        panelHaut.add(menuBar,BorderLayout.NORTH);
        panelHaut.add(header.headerPanel,BorderLayout.CENTER);

		panelEditFacture.add(panelHaut,BorderLayout.NORTH);
        panelEditFacture.add(footer.footerPanel,BorderLayout.SOUTH);
		
		//add facture panel view
		facture_Panel_View = new Facture_Panel_View();
		rapport_Panel_View = new Rapport_Panel_View();
		rapport_NC_View = new Rapport_NC_View();
		panelNord.add(facture_Panel_View.facture_Panel_View(rapport_Panel_View));
		panelNord.add(rapport_Panel_View.rapport_Panel_View());			
        
		//ZABPQ OPEN_CONTACT LAYOUT     
        panelEditFacture.add(panelNord,BorderLayout.CENTER);
        menuAideContent.addActionListener(new ManuelUtilisationController());
	    pane.add(panelEditFacture,BorderLayout.CENTER);
	    this.setVisible(true);
	  }
		
}
