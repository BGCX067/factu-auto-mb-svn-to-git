package factu_mb_param.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Rapport_LeCable_View extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Font font = new Font("Arial", Font.BOLD, 10);
	public JPanel rapport_Panel;
	public JLabel lbFile,lbEquiv1,lbEquiv2,lbEquiv3,lbEquiv4,lbEquiv5,lbEquiv6;
	public JLabel lbTitle,lbTempFile,lbTempFileRes,lbInput,lbInputRes,
	lbAboFile,lbAboFileRes,lbFASFile,lbFASFileRes,lbUsageFile,lbUsageFileRes,
	lbRecapFile,lbRecapFileRes;
	public JTextArea rqSuccess,rqError;
	
	public Rapport_LeCable_View(){					
		//Panel Rapport Facture
		rapport_Panel= new JPanel();
		rapport_Panel.setLayout(new GridBagLayout());
		rapport_Panel.setBackground(Color.white);
		//Debut generation Facture
		GridBagConstraints gridBagConstraintsTitle = new GridBagConstraints();
		lbTitle = new JLabel("Début génération facture");
		lbTitle.setFont(font);
		gridBagConstraintsTitle.gridx = 0;
		gridBagConstraintsTitle.gridy = 0;
		gridBagConstraintsTitle.insets = new Insets(4, 2, 4, 2);
		gridBagConstraintsTitle.anchor = gridBagConstraintsTitle.WEST;
        rapport_Panel.add(lbTitle, gridBagConstraintsTitle);
        lbTitle.setVisible(false);
        
        GridBagConstraints gridBagConstraintsFileName = new GridBagConstraints();
        lbFile=new JLabel();
        lbFile.setFont(font);
        gridBagConstraintsFileName.gridx = 1;
        gridBagConstraintsFileName.gridy = 0;
        gridBagConstraintsFileName.insets = new Insets(4, 2, 4, 2);
        gridBagConstraintsFileName.anchor = gridBagConstraintsFileName.WEST;
        rapport_Panel.add(lbFile, gridBagConstraintsFileName);
        lbFile.setVisible(false);
		        
		//Modif fichier temp
		GridBagConstraints gridBagConstraintsTmpFile = new GridBagConstraints();
		lbTempFile = new JLabel("Modification fichier TEMP 'LC_INFOS_FACTU'");
		lbTempFile.setFont(font);
		gridBagConstraintsTmpFile.gridwidth =2;
		gridBagConstraintsTmpFile.gridx = 0;
		gridBagConstraintsTmpFile.gridy = 1;
		gridBagConstraintsTmpFile.insets = new Insets(4, 2, 4, 2);
		gridBagConstraintsTmpFile.anchor = gridBagConstraintsTmpFile.WEST;
        rapport_Panel.add(lbTempFile, gridBagConstraintsTmpFile);
        lbTempFile.setVisible(false);
        
        GridBagConstraints gridBagConstraintsEquiv1 = new GridBagConstraints();
        lbEquiv1=new JLabel("==>");
        lbEquiv1.setFont(font);
        gridBagConstraintsEquiv1.gridx = 2;
        gridBagConstraintsEquiv1.gridy = 1;
        gridBagConstraintsEquiv1.insets = new Insets(4, -60, 4, 2);
        gridBagConstraintsEquiv1.anchor = gridBagConstraintsEquiv1.WEST;
        rapport_Panel.add(lbEquiv1, gridBagConstraintsEquiv1);
        lbEquiv1.setVisible(false);
        
        GridBagConstraints gridBagConstraintsTmpFileRes = new GridBagConstraints();
        lbTempFileRes=new JLabel("En cours");
        lbTempFileRes.setFont(font);
        lbTempFileRes.setForeground(Color.blue);
        gridBagConstraintsTmpFileRes.gridx = 3;
        gridBagConstraintsTmpFileRes.gridy = 1;
        gridBagConstraintsTmpFileRes.insets = new Insets(4, -30, 4, 2);
        gridBagConstraintsTmpFileRes.anchor = gridBagConstraintsTmpFileRes.WEST;
        rapport_Panel.add(lbTempFileRes, gridBagConstraintsTmpFileRes);
        lbTempFileRes.setVisible(false);
        
        
        //Modification onglet imput
      	GridBagConstraints gridBagConstraintsTempFile = new GridBagConstraints();
      	lbInput = new JLabel("Modification onglet 'Inputs'");
      	lbInput.setFont(font);
      	gridBagConstraintsTempFile.gridwidth =2;
      	gridBagConstraintsTempFile.gridx = 0;
      	gridBagConstraintsTempFile.gridy = 2;
      	gridBagConstraintsTempFile.insets = new Insets(4, 2, 4, 2);
      	gridBagConstraintsTempFile.anchor = gridBagConstraintsTempFile.WEST;
      	rapport_Panel.add(lbInput, gridBagConstraintsTempFile);
      	lbInput.setVisible(false);
          
        GridBagConstraints gridBagConstraintsEquiv2 = new GridBagConstraints();
        lbEquiv2=new JLabel("==>");
        lbEquiv2.setFont(font);
        gridBagConstraintsEquiv2.gridx = 2;
        gridBagConstraintsEquiv2.gridy = 2;
        gridBagConstraintsEquiv2.insets = new Insets(4, -60, 4, 2);
        gridBagConstraintsEquiv2.anchor = gridBagConstraintsEquiv2.WEST;
        rapport_Panel.add(lbEquiv2, gridBagConstraintsEquiv2);
        lbEquiv2.setVisible(false);
          
        GridBagConstraints gridBagConstraintsTempFileRes = new GridBagConstraints();
        lbInputRes = new JLabel("En cours");
        lbInputRes.setFont(font);
        lbInputRes.setForeground(Color.blue);
        gridBagConstraintsTempFileRes.gridx = 3;
        gridBagConstraintsTempFileRes.gridy = 2;
        gridBagConstraintsTempFileRes.insets = new Insets(4, -30, 4, 2);
        gridBagConstraintsTempFileRes.anchor = gridBagConstraintsTempFileRes.WEST;
  		rapport_Panel.add(lbInputRes, gridBagConstraintsTempFileRes);
  		lbInputRes.setVisible(false);
              
        //Copie donnée temp ==> abo
      	GridBagConstraints gridBagConstraintsAbo = new GridBagConstraints();
      	lbAboFile = new JLabel("Copie des données 'Temp' ==> 'Abonnements'");
      	lbAboFile.setFont(font);
      	gridBagConstraintsAbo.gridwidth =2;
      	gridBagConstraintsAbo.gridx = 0;
      	gridBagConstraintsAbo.gridy = 3;
      	gridBagConstraintsAbo.insets = new Insets(4, 2, 4, 2);
      	gridBagConstraintsAbo.anchor = gridBagConstraintsAbo.WEST;
      	rapport_Panel.add(lbAboFile, gridBagConstraintsAbo);
      	lbAboFile.setVisible(false);
              
        GridBagConstraints gridBagConstraintsEquiv3 = new GridBagConstraints();
        lbEquiv3=new JLabel("==>");
        lbEquiv3.setFont(font);
        gridBagConstraintsEquiv3.gridx = 2;
        gridBagConstraintsEquiv3.gridy = 3;
        gridBagConstraintsEquiv3.insets = new Insets(4, -60, 4, 2);
        gridBagConstraintsEquiv3.anchor = gridBagConstraintsEquiv3.WEST;
        rapport_Panel.add(lbEquiv3, gridBagConstraintsEquiv3);
        lbEquiv3.setVisible(false);
              
        GridBagConstraints gridBagConstraintsAboRes = new GridBagConstraints();
        lbAboFileRes = new JLabel("En cours");
        lbAboFileRes.setFont(font);
        lbAboFileRes.setForeground(Color.blue);
        gridBagConstraintsAboRes.gridx = 3;
  		gridBagConstraintsAboRes.gridy = 3;
  		gridBagConstraintsAboRes.insets = new Insets(4, -30, 4, 2);
        gridBagConstraintsAboRes.anchor = gridBagConstraintsAboRes.WEST;
        rapport_Panel.add(lbAboFileRes, gridBagConstraintsAboRes);
        lbAboFileRes.setVisible(false);
              
              
        //Mis a jour des données FAS
        GridBagConstraints gridBagConstraintsFas = new GridBagConstraints();
      	lbFASFile = new JLabel("Mise à jour des données 'NRC'");
      	lbFASFile.setFont(font);
      	gridBagConstraintsFas.gridwidth =2;
      	gridBagConstraintsFas.gridx = 0;
      	gridBagConstraintsFas.gridy = 4;
      	gridBagConstraintsFas.insets = new Insets(4, 2, 4, 2);
      	gridBagConstraintsFas.anchor = gridBagConstraintsFas.WEST;
      	rapport_Panel.add(lbFASFile, gridBagConstraintsFas);
      	lbFASFile.setVisible(false);
        
        GridBagConstraints gridBagConstraintsEquiv4 = new GridBagConstraints();
        lbEquiv4=new JLabel("==>");
        lbEquiv4.setFont(font);
        gridBagConstraintsEquiv4.gridx = 2;
        gridBagConstraintsEquiv4.gridy = 4;
        gridBagConstraintsEquiv4.insets = new Insets(4, -60, 4, 2);
        gridBagConstraintsEquiv4.anchor = gridBagConstraintsEquiv4.WEST;
        rapport_Panel.add(lbEquiv4, gridBagConstraintsEquiv4);
        lbEquiv4.setVisible(false);
              
        GridBagConstraints gridBagConstraintsFasRes = new GridBagConstraints();
        lbFASFileRes = new JLabel("En cours");
        lbFASFileRes.setFont(font);
        lbFASFileRes.setForeground(Color.blue);
        gridBagConstraintsFasRes.gridx = 3;
  		gridBagConstraintsFasRes.gridy = 4;
  		gridBagConstraintsFasRes.insets = new Insets(4, -30, 4, 2);
  		gridBagConstraintsFasRes.anchor = gridBagConstraintsFasRes.WEST;
  		rapport_Panel.add(lbFASFileRes, gridBagConstraintsFasRes); 
  		lbFASFileRes.setVisible(false);
  		
  		//Mis a jour des données USAGE
        GridBagConstraints gridBagConstraintsUsage = new GridBagConstraints();
      	lbUsageFile = new JLabel("Mise à jour des données 'Usage'");
      	lbUsageFile.setFont(font);
      	gridBagConstraintsUsage.gridwidth =2;
      	gridBagConstraintsUsage.gridx = 0;
      	gridBagConstraintsUsage.gridy = 5;
      	gridBagConstraintsUsage.insets = new Insets(4, 2, 4, 2);
      	gridBagConstraintsUsage.anchor = gridBagConstraintsUsage.WEST;
      	rapport_Panel.add(lbUsageFile, gridBagConstraintsUsage);
      	lbUsageFile.setVisible(false);
        
        GridBagConstraints gridBagConstraintsEquiv5 = new GridBagConstraints();
        lbEquiv5=new JLabel("==>");
        lbEquiv5.setFont(font);
        gridBagConstraintsEquiv5.gridx = 2;
        gridBagConstraintsEquiv5.gridy = 5;
        gridBagConstraintsEquiv5.insets = new Insets(4, -60, 4, 2);
        gridBagConstraintsEquiv5.anchor = gridBagConstraintsEquiv5.WEST;
        rapport_Panel.add(lbEquiv5, gridBagConstraintsEquiv5);
        lbEquiv5.setVisible(false);
              
        GridBagConstraints gridBagConstraintsUsageRes = new GridBagConstraints();
        lbUsageFileRes = new JLabel("En cours");
        lbUsageFileRes.setFont(font);
        lbUsageFileRes.setForeground(Color.blue);
        gridBagConstraintsUsageRes.gridx = 3;
        gridBagConstraintsUsageRes.gridy = 5;
        gridBagConstraintsUsageRes.insets = new Insets(4, -30, 4, 2);
        gridBagConstraintsUsageRes.anchor = gridBagConstraintsUsageRes.WEST;
  		rapport_Panel.add(lbUsageFileRes, gridBagConstraintsUsageRes);
  		lbUsageFileRes.setVisible(false);
  		
  		//Recapitulatif
        GridBagConstraints gridBagConstraintsRecap = new GridBagConstraints();
      	lbRecapFile = new JLabel("Mise à jour de l'onglet 'Récapitulatif'");
      	lbRecapFile.setFont(font);
      	gridBagConstraintsRecap.gridwidth =2;
      	gridBagConstraintsRecap.gridx = 0;
      	gridBagConstraintsRecap.gridy = 6;
      	gridBagConstraintsRecap.insets = new Insets(4, 2, 4, 2);
      	gridBagConstraintsRecap.anchor = gridBagConstraintsRecap.WEST;
      	rapport_Panel.add(lbRecapFile, gridBagConstraintsRecap);
      	lbRecapFile.setVisible(false);
        
        GridBagConstraints gridBagConstraintsEquiv6 = new GridBagConstraints();
        lbEquiv6=new JLabel("==>");
        lbEquiv6.setFont(font);
        gridBagConstraintsEquiv6.gridx = 2;
        gridBagConstraintsEquiv6.gridy = 6;
        gridBagConstraintsEquiv6.insets = new Insets(4, -60, 4, 2);
        gridBagConstraintsEquiv6.anchor = gridBagConstraintsEquiv6.WEST;
        rapport_Panel.add(lbEquiv6, gridBagConstraintsEquiv6);
        lbEquiv6.setVisible(false);
              
        GridBagConstraints gridBagConstraintsRecapRes = new GridBagConstraints();
        lbRecapFileRes = new JLabel("En cours");
        lbRecapFileRes.setFont(font);
        lbRecapFileRes.setForeground(Color.blue);
        gridBagConstraintsRecapRes.gridx = 3;
        gridBagConstraintsRecapRes.gridy = 6;
        gridBagConstraintsRecapRes.insets = new Insets(4, -30, 4, 2);
        gridBagConstraintsRecapRes.anchor = gridBagConstraintsRecapRes.WEST;
  		rapport_Panel.add(lbRecapFileRes, gridBagConstraintsRecapRes);
  		lbRecapFileRes.setVisible(false);
						
	}		
	
	public JPanel rapport_Panel_View(){	
		return rapport_Panel;
	}
	
	public JTextArea remarqueSuccess(String fileName,String chemin){
		rqSuccess= new JTextArea("Note :\nLa facture "+fileName+"\na été générée avec succès dans le " +
		"repertoire :\n'"+chemin+"'.\nVeuillez vérifier les résultats par rapport aux données dans\n" +
		"'Customer Center' ");
		rqSuccess.setPreferredSize(new Dimension(390,100));
		rqSuccess.setLineWrap(true);		
		return rqSuccess;		
	}
	
	public JTextArea remarqueError(String fileName,String chemin){
		rqError= new JTextArea("Note :\nLa facture "+fileName+"\na été générée avec succès dans le " +
		"repertoire :\n'"+chemin+"'.\nVeuillez vérifier les résultats par rapport aux\ndonnées dans\n" +
		"'Customer Center' ");
		rqError.setPreferredSize(new Dimension(390,100));
		rqError.setLineWrap(true);
		return rqError;		
	}
	
}
