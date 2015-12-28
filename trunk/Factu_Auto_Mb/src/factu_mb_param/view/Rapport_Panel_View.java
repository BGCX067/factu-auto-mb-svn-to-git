package factu_mb_param.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Rapport_Panel_View extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Font font = new Font("Comic Sans MS", Font.BOLD, 12);
	private JPanel rapport_Panel;
	private JPanel north_Panel;
	private JPanel center_Panel;
	public JPanel center_north;
	public JPanel center_south;
	public JLabel lbEquiv1,lbEquiv2,lbEquiv3,lbEquiv4;
	public JLabel lbDB, lbDBResult,lbFact,lbFactResult,lbActNo,lbActNoResult,lbBillRefNo,lbBillRefNoResult;
	
	
	public Rapport_Panel_View(){
		
	}
	
	public JPanel rapport_Panel_View(){		
		//Rapport Facture Marque Blanche
		rapport_Panel= new JPanel();
		rapport_Panel.setPreferredSize(new Dimension(450,400));
		Border titledBorderRapport = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.getHSBColor(93/360F, 0.57F, 0.60F)), "Rapport Facture Marque Blanche :", TitledBorder.LEFT, TitledBorder.TOP, font, Color.getHSBColor(93/255F, 0.57F, 0.60F));
		rapport_Panel.setBorder(BorderFactory.createTitledBorder(titledBorderRapport));
		rapport_Panel.setLayout(new BorderLayout());
		
		//Panel Nord detail connexion
		north_Panel=new JPanel();
		north_Panel.setLayout(new BorderLayout());
		north_Panel.setPreferredSize(new Dimension(400,100));
		Border titledBorderHaut = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Récap Facture :", TitledBorder.RIGHT, TitledBorder.TOP, font, Color.gray);
		north_Panel.setBorder(BorderFactory.createTitledBorder(titledBorderHaut));
		north_Panel.setBackground(Color.white);
		center_Panel=new JPanel();
		center_Panel.setLayout(new BorderLayout());
		center_Panel.setPreferredSize(new Dimension(400,300));
		Border titledBorderBas = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), "Détail Rapport Facture :", TitledBorder.RIGHT, TitledBorder.TOP, font, Color.gray);
		center_Panel.setBorder(BorderFactory.createTitledBorder(titledBorderBas));
		center_Panel.setBackground(Color.white);				
		
		//Panel Recap Facture
		JPanel recapPanel= new JPanel();
		recapPanel.setLayout(new GridBagLayout());
		recapPanel.setBackground(Color.white);
		//DB connexion
		GridBagConstraints gridBagConstraintsDB = new GridBagConstraints();
		lbDB = new JLabel();
        gridBagConstraintsDB.gridx = 0;
        gridBagConstraintsDB.gridy = 0;
        gridBagConstraintsDB.insets = new Insets(2, 5, 2, 5);
        gridBagConstraintsDB.anchor = gridBagConstraintsDB.WEST;
        recapPanel.add(lbDB, gridBagConstraintsDB);
        
        GridBagConstraints gridBagConstraintsEquiv1 = new GridBagConstraints();
        lbEquiv1=new JLabel("==>");
        gridBagConstraintsEquiv1.gridx = 1;
        gridBagConstraintsEquiv1.gridy = 0;
        gridBagConstraintsEquiv1.insets = new Insets(2, 30, 2, 30);
        gridBagConstraintsEquiv1.anchor = gridBagConstraintsEquiv1.WEST;
        recapPanel.add(lbEquiv1, gridBagConstraintsEquiv1);
        lbEquiv1.setVisible(false);
        
        GridBagConstraints gridBagConstraintsDBRes = new GridBagConstraints();
		lbDBResult = new JLabel();
		gridBagConstraintsDBRes.gridx = 2;
        gridBagConstraintsDBRes.gridy = 0;
        gridBagConstraintsDBRes.insets = new Insets(2, 5, 2, 5);
        gridBagConstraintsDBRes.anchor = gridBagConstraintsDBRes.WEST;
        recapPanel.add(lbDBResult, gridBagConstraintsDBRes);
        
      //Facture
      	GridBagConstraints gridBagConstraintsFact = new GridBagConstraints();
      	lbFact = new JLabel();
      	gridBagConstraintsFact.gridx = 0;
      	gridBagConstraintsFact.gridy = 1;
      	gridBagConstraintsFact.insets = new Insets(2, 5, 2, 5);
      	gridBagConstraintsFact.anchor = gridBagConstraintsFact.WEST;
      	recapPanel.add(lbFact, gridBagConstraintsFact);
          
        GridBagConstraints gridBagConstraintsEquiv2 = new GridBagConstraints();
        lbEquiv2=new JLabel("==>");
        gridBagConstraintsEquiv2.gridx = 1;
        gridBagConstraintsEquiv2.gridy = 1;
        gridBagConstraintsEquiv2.insets = new Insets(2, 30, 2, 30);
        gridBagConstraintsEquiv2.anchor = gridBagConstraintsEquiv2.WEST;
        recapPanel.add(lbEquiv2, gridBagConstraintsEquiv2);
        lbEquiv2.setVisible(false);
          
        GridBagConstraints gridBagConstraintsFactRes = new GridBagConstraints();
        lbFactResult = new JLabel();
  		gridBagConstraintsFactRes.gridx = 2;
  		gridBagConstraintsFactRes.gridy = 1;
  		gridBagConstraintsFactRes.insets = new Insets(2, 5, 2, 5);
  		gridBagConstraintsFactRes.anchor = gridBagConstraintsFactRes.WEST;
  		recapPanel.add(lbFactResult, gridBagConstraintsFactRes);
              
        //Account_no
      	GridBagConstraints gridBagConstraintsActNo = new GridBagConstraints();
      	lbActNo = new JLabel();
      	gridBagConstraintsActNo.gridx = 0;
      	gridBagConstraintsActNo.gridy = 2;
      	gridBagConstraintsActNo.insets = new Insets(2, 5, 2, 5);
      	gridBagConstraintsActNo.anchor = gridBagConstraintsActNo.WEST;
      	recapPanel.add(lbActNo, gridBagConstraintsActNo);
              
        GridBagConstraints gridBagConstraintsEquiv3 = new GridBagConstraints();
        lbEquiv3=new JLabel("==>");
        gridBagConstraintsEquiv3.gridx = 1;
        gridBagConstraintsEquiv3.gridy = 2;
        gridBagConstraintsEquiv3.insets = new Insets(2, 30, 2, 30);
        gridBagConstraintsEquiv3.anchor = gridBagConstraintsEquiv3.WEST;
        recapPanel.add(lbEquiv3, gridBagConstraintsEquiv3);
        lbEquiv3.setVisible(false);
              
        GridBagConstraints gridBagConstraintsActNoRes = new GridBagConstraints();
        lbActNoResult = new JLabel();
  		gridBagConstraintsActNoRes.gridx = 2;
  		gridBagConstraintsActNoRes.gridy = 2;
        gridBagConstraintsActNoRes.insets = new Insets(2, 5, 2, 5);
        gridBagConstraintsActNoRes.anchor = gridBagConstraintsActNoRes.WEST;
        recapPanel.add(lbActNoResult, gridBagConstraintsActNoRes);
              
              
        //Bill ref
        GridBagConstraints gridBagConstraintsBillRef = new GridBagConstraints();
      	lbBillRefNo = new JLabel();
      	gridBagConstraintsBillRef.gridx = 0;
      	gridBagConstraintsBillRef.gridy = 3;
      	gridBagConstraintsBillRef.insets = new Insets(2, 5, 2, 5);
      	gridBagConstraintsBillRef.anchor = gridBagConstraintsBillRef.WEST;
      	recapPanel.add(lbBillRefNo, gridBagConstraintsBillRef);
        
        GridBagConstraints gridBagConstraintsEquiv4 = new GridBagConstraints();
        lbEquiv4=new JLabel("==>");
        gridBagConstraintsEquiv4.gridx = 1;
        gridBagConstraintsEquiv4.gridy = 3;
        gridBagConstraintsEquiv4.insets = new Insets(2, 30, 2, 30);
        gridBagConstraintsEquiv4.anchor = gridBagConstraintsEquiv4.WEST;
        recapPanel.add(lbEquiv4, gridBagConstraintsEquiv4);
        lbEquiv4.setVisible(false);
              
        GridBagConstraints gridBagConstraintsBillRefRes = new GridBagConstraints();
        lbBillRefNoResult = new JLabel();
  		gridBagConstraintsBillRefRes.gridx = 2;
  		gridBagConstraintsBillRefRes.gridy = 3;
  		gridBagConstraintsBillRefRes.insets = new Insets(2, 5, 2, 5);
  		gridBagConstraintsBillRefRes.anchor = gridBagConstraintsBillRefRes.WEST;
  		recapPanel.add(lbBillRefNoResult, gridBagConstraintsBillRefRes);            

        north_Panel.add(recapPanel,BorderLayout.WEST);
		rapport_Panel.add(north_Panel,BorderLayout.NORTH); 
		
		center_north=new JPanel();
		center_north.setBackground(Color.WHITE);
		center_north.setLayout(new BorderLayout());
		center_south=new JPanel();
		center_south.setBackground(Color.WHITE);
		center_south.setLayout(new BorderLayout());
		center_Panel.add(center_north,BorderLayout.NORTH);
		center_Panel.add(center_south,BorderLayout.SOUTH);
		rapport_Panel.add(center_Panel,BorderLayout.CENTER);
		
		
		return rapport_Panel;
						
	}		
}
