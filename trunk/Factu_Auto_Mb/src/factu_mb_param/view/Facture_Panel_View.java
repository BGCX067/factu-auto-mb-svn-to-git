package factu_mb_param.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import factu_mb_param.controller.CheckBoxAvoirController;
import factu_mb_param.controller.ComboboxFactureController;
import factu_mb_param.controller.GenerateAvoirFactureDRTController;
import factu_mb_param.controller.GenerateFactureController;

public class Facture_Panel_View extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTable tableau;
    public DefaultTableModel tableModel;
	
	private Font font = new Font("Comic Sans MS", Font.BOLD, 12);
	private Font fontBouton = new Font("Broadway", Font.BOLD, 12);
	private JPanel facture_Panel;
	private JPanel factPanel,datePanel;
	public JButton btValider,btAvoir;
	public JCheckBox cbAvoirs;
	public JTextFieldDateEditor dateEditorEchoir,dateEditorEchu;
	public JDateChooser dateChooserEchoir,dateChooserEchu;
	public String selectDate = "Séléction de Date";
	public JLabel lbFacture,lbCompteFact,lbNumFact, lbDateEchu, lbDateEchoir,lbResume,lbAvoirs;
	public JComboBox<String> cbFacture;
	public JTextField txtNumCompte, txtNumFact ;
	public Rapport_Panel_View rapport_Panel_View;	

	public String [] mb = {"","NUMERICABLE","BOUYGUES","LE CABLE","OMT","DARTY"};
    
	public JPanel facture_Panel_View(Rapport_Panel_View rapport_Panel_View){
		this.rapport_Panel_View=rapport_Panel_View;
	    		
		
		//Parametrage Facture Marque Blanche
		facture_Panel= new JPanel();
		facture_Panel.setPreferredSize(new Dimension(400,400));
		Border titledBorderTrafic = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.getHSBColor(93/360F, 0.57F, 0.60F)), "Paramétrage Facture Marque Blanche :", TitledBorder.LEFT, TitledBorder.TOP, font, Color.getHSBColor(93/255F, 0.57F, 0.60F));
		facture_Panel.setBorder(BorderFactory.createTitledBorder(titledBorderTrafic));
		facture_Panel.setLayout(new BoxLayout(facture_Panel,BoxLayout.Y_AXIS));						
		
		//Detail facture panel
		factPanel =new JPanel();
		factPanel.setLayout(new GridBagLayout());
		//Facture
        GridBagConstraints gridBagConstraintsFacture = new GridBagConstraints();
        lbFacture = new JLabel("Client : *");
        gridBagConstraintsFacture.gridx = 0;
        gridBagConstraintsFacture.gridy = 0;
        gridBagConstraintsFacture.insets = new Insets(10, 5, 10, 5);
        gridBagConstraintsFacture.anchor = gridBagConstraintsFacture.WEST;
        factPanel.add(lbFacture, gridBagConstraintsFacture);
        
        GridBagConstraints gridBagConstraintsChoixFactMB = new GridBagConstraints();
        cbFacture = new JComboBox<String>();
        for( int i=0;i<mb.length;i++){
        	cbFacture.addItem(mb[i]);
        }
        cbFacture.setPreferredSize(new Dimension(150, 21));
        gridBagConstraintsChoixFactMB.gridx = 1;
        gridBagConstraintsChoixFactMB.gridy = 0;
        gridBagConstraintsChoixFactMB.insets = new Insets(10, 5, 10, 110);
        gridBagConstraintsChoixFactMB.anchor = gridBagConstraintsChoixFactMB.WEST;
        factPanel.add(cbFacture, gridBagConstraintsChoixFactMB);
        
      //AVOIR DARTY
        GridBagConstraints gridBagConstraintsAvoirBOX = new GridBagConstraints();
        cbAvoirs = new JCheckBox();
        gridBagConstraintsAvoirBOX.gridx = 2;
        gridBagConstraintsAvoirBOX.gridy = 0;
        gridBagConstraintsAvoirBOX.insets = new Insets(2,-80, 2, 2);
        gridBagConstraintsAvoirBOX.anchor = gridBagConstraintsAvoirBOX.WEST;
        factPanel.add(cbAvoirs, gridBagConstraintsAvoirBOX);
        cbAvoirs.setVisible(false);
        
        GridBagConstraints gridBagConstraintsAvoirDRT = new GridBagConstraints();
        lbAvoirs = new JLabel("Avoirs");
        gridBagConstraintsAvoirDRT.gridx = 3;
        gridBagConstraintsAvoirDRT.gridy = 0;
        gridBagConstraintsAvoirDRT.insets = new Insets(2, -50, 2, 2);
        gridBagConstraintsAvoirDRT.anchor = gridBagConstraintsAvoirDRT.WEST;
        factPanel.add(lbAvoirs, gridBagConstraintsAvoirDRT);
        lbAvoirs.setVisible(false);
        
      //Numero de Compte
        GridBagConstraints gridBagConstraintsNumCpt = new GridBagConstraints();
        lbCompteFact = new JLabel("N° de Compte : ");
        gridBagConstraintsNumCpt.gridx = 0;
        gridBagConstraintsNumCpt.gridy = 1;
        gridBagConstraintsNumCpt.insets = new Insets(10, 5, 10, 5);
        gridBagConstraintsNumCpt.anchor = gridBagConstraintsNumCpt.WEST;
        factPanel.add(lbCompteFact, gridBagConstraintsNumCpt);
        
        GridBagConstraints gridBagConstraintsTxtNumCpt = new GridBagConstraints();
        txtNumCompte = new JTextField();
        txtNumCompte.setPreferredSize(new Dimension(100, 21));
        txtNumCompte.setEditable(false);
        gridBagConstraintsTxtNumCpt.gridx = 1;
        gridBagConstraintsTxtNumCpt.gridy = 1;
        gridBagConstraintsTxtNumCpt.insets = new Insets(10, 5, 10, 110);
        gridBagConstraintsTxtNumCpt.anchor = gridBagConstraintsTxtNumCpt.WEST;
        factPanel.add(txtNumCompte, gridBagConstraintsTxtNumCpt);
                
      //Numero de Compte
        GridBagConstraints gridBagConstraintsNumFact = new GridBagConstraints();
        lbNumFact = new JLabel("N° de Facture : ");
        gridBagConstraintsNumFact.gridx = 0;
        gridBagConstraintsNumFact.gridy = 2;
        gridBagConstraintsNumFact.insets = new Insets(10, 5, 10, 5);
        gridBagConstraintsNumFact.anchor = gridBagConstraintsNumFact.WEST;
        factPanel.add(lbNumFact, gridBagConstraintsNumFact);
        
        GridBagConstraints gridBagConstraintsTxtNumFact = new GridBagConstraints();
        txtNumFact = new JTextField();
        txtNumFact.setPreferredSize(new Dimension(100, 21));
        txtNumFact.setEditable(false);
        gridBagConstraintsTxtNumFact.gridx = 1;
        gridBagConstraintsTxtNumFact.gridy = 2;
        gridBagConstraintsTxtNumFact.insets = new Insets(10, 5, 10, 110);
        gridBagConstraintsTxtNumFact.anchor = gridBagConstraintsTxtNumFact.WEST;
        factPanel.add(txtNumFact, gridBagConstraintsTxtNumFact);
        
        GridBagConstraints gridBagConstraintsResume = new GridBagConstraints();
        lbResume = new JLabel("Resumé : ");
        gridBagConstraintsResume.gridx = 0;
        gridBagConstraintsResume.gridy = 3;
        gridBagConstraintsResume.insets = new Insets(10, 5, 10, 58);
        gridBagConstraintsResume.anchor = gridBagConstraintsResume.WEST;
        factPanel.add(lbResume, gridBagConstraintsResume);
        
        facture_Panel.add(factPanel);
        
        
        //Table resume
              
        JPanel tailleTab = new JPanel();
        tailleTab.setLayout(new BorderLayout());
        tailleTab.setPreferredSize(new Dimension(360,12));
        tableModel = new DefaultTableModel();
        tableModel.addColumn("From Date");
        tableModel.addColumn("To Date");
        tableModel.addColumn("Prep Date");
        tableModel.addColumn("Statement Date");
        tableau = new JTable(tableModel);       
        tableau.setEnabled(false);
        tableau.getColumnModel().getColumn(0).setMaxWidth(85);
        tableau.getColumnModel().getColumn(1).setMaxWidth(85);
        tableau.getColumnModel().getColumn(2).setMaxWidth(135);
        tableau.getColumnModel().getColumn(3).setMaxWidth(95);
        centrerTable(tableau);
		Object[] columnValues = {};			
		tableModel.addRow(columnValues);
		tailleTab.add(new JScrollPane(tableau),BorderLayout.CENTER);
        facture_Panel.add(tailleTab);
              
        //Date echu
        datePanel = new JPanel();
        datePanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gridBagConstraintsDtEchu = new GridBagConstraints();
        lbDateEchu = new JLabel("From Date : *");
        gridBagConstraintsDtEchu.gridx = 0;
        gridBagConstraintsDtEchu.gridy = 0;
        gridBagConstraintsDtEchu.insets = new Insets(10, 0, 10, 40);
        gridBagConstraintsDtEchu.anchor = gridBagConstraintsDtEchu.WEST;
        datePanel.add(lbDateEchu, gridBagConstraintsDtEchu);
        
        GridBagConstraints gridBagConstraintsTxtDtEchu = new GridBagConstraints();     
        dateChooserEchu = new JDateChooser();
        dateChooserEchu.setDateFormatString("dd/MM/yyyy");
        dateChooserEchu.setPreferredSize(new Dimension(135, 21));
        dateEditorEchu = (JTextFieldDateEditor)dateChooserEchu.getDateEditor();
        dateEditorEchu.setEditable(false);
        dateEditorEchu.setText(selectDate);
        dateEditorEchu.setForeground(Color.gray);
        gridBagConstraintsTxtDtEchu.gridx = 1;
        gridBagConstraintsTxtDtEchu.gridy = 0;
        gridBagConstraintsTxtDtEchu.insets = new Insets(10, 5, 10, 50);
        gridBagConstraintsTxtDtEchu.anchor = gridBagConstraintsTxtDtEchu.WEST;
        datePanel.add(dateChooserEchu, gridBagConstraintsTxtDtEchu);
        
        //Date echoir
        GridBagConstraints gridBagConstraintsDtEchoir = new GridBagConstraints();
        lbDateEchoir = new JLabel("To Date : *");
        gridBagConstraintsDtEchoir.gridx = 0;
        gridBagConstraintsDtEchoir.gridy = 1;
        gridBagConstraintsDtEchoir.insets = new Insets(10, 0, 10, 40);
        gridBagConstraintsDtEchoir.anchor = gridBagConstraintsDtEchoir.WEST;
        datePanel.add(lbDateEchoir, gridBagConstraintsDtEchoir);
        
        GridBagConstraints gridBagConstraintsTxtDtEchoir = new GridBagConstraints();     
        dateChooserEchoir = new JDateChooser();
        dateChooserEchoir.setDateFormatString("dd/MM/yyyy");
        dateChooserEchoir.setPreferredSize(new Dimension(135, 21));
        dateEditorEchoir = (JTextFieldDateEditor)dateChooserEchoir.getDateEditor();
        dateEditorEchoir.setEditable(false);
        dateEditorEchoir.setText(selectDate);
        dateEditorEchoir.setForeground(Color.gray);
        gridBagConstraintsTxtDtEchoir.gridx = 1;
        gridBagConstraintsTxtDtEchoir.gridy = 1;
        gridBagConstraintsTxtDtEchoir.insets = new Insets(10, 5, 10, 50);
        gridBagConstraintsTxtDtEchoir.anchor = gridBagConstraintsTxtDtEchoir.WEST;
        datePanel.add(dateChooserEchoir, gridBagConstraintsTxtDtEchoir);               
        
        //libelle boutton
        GridBagConstraints gridBagConstraintsbtValider = new GridBagConstraints();
        btValider = new JButton("Générer Facture .xls");
        btValider.setPreferredSize(new Dimension(200,21));
        btValider.setBackground(Color.black);
        btValider.setFont(fontBouton);
        btValider.setForeground(Color.getHSBColor(39,233,255));
        gridBagConstraintsbtValider.gridx = 1;
        gridBagConstraintsbtValider.gridy = 2;
        gridBagConstraintsbtValider.insets = new Insets(10, 0, 10, 50);
        gridBagConstraintsbtValider.anchor = gridBagConstraintsbtValider.WEST;
        datePanel.add(btValider, gridBagConstraintsbtValider);
        
      //libelle boutton AVOIR
        GridBagConstraints gridBagConstraintsbtAvoir = new GridBagConstraints();
        btAvoir = new JButton("Générer Avoir Facture");
        btAvoir.setPreferredSize(new Dimension(200,21));
        btAvoir.setBackground(Color.black);
        btAvoir.setFont(fontBouton);
        btAvoir.setForeground(Color.getHSBColor(39,233,255));
        gridBagConstraintsbtAvoir.gridx = 1;
        gridBagConstraintsbtAvoir.gridy = 2;
        gridBagConstraintsbtAvoir.insets = new Insets(10, 0, 10, 50);
        gridBagConstraintsbtAvoir.anchor = gridBagConstraintsbtAvoir.WEST;
        datePanel.add(btAvoir, gridBagConstraintsbtAvoir);
        btAvoir.setVisible(false);
             
        
        facture_Panel.add(datePanel); 
        cbFacture.addItemListener(new ComboboxFactureController(this,rapport_Panel_View));
        btValider.addActionListener(new GenerateFactureController(this,rapport_Panel_View));
        cbAvoirs.addActionListener(new CheckBoxAvoirController(this));
        btAvoir.addActionListener(new GenerateAvoirFactureDRTController(this, rapport_Panel_View));
	    return facture_Panel;
	    	    
	  }
	
	private void centrerTable(JTable table){
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(0);
        for(int i = 0; i < table.getColumnCount(); i++)
        {
            table.getColumnModel().getColumn(i).setCellRenderer(custom);
        }

    }
}
