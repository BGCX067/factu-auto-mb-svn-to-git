package factu_mb_param.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import factu_mb_param.GenerateFact.GenerationAvoirsDRT;
import factu_mb_param.dao.DartyDao;
import factu_mb_param.modele.AvoirAncienAboModel;
import factu_mb_param.modele.AvoirNouvelAboModel;
import factu_mb_param.modele.DateConvertor;
import factu_mb_param.modele.InfosAvoirsModel;
import factu_mb_param.modele.NbrDayPerMonth;
import factu_mb_param.view.Facture_Panel_View;
import factu_mb_param.view.Rapport_Darty_Avoirs_View;
import factu_mb_param.view.Rapport_Panel_View;



public class Rapport_AVOIR_DRT_Controller{
	
	private Facture_Panel_View facture_Panel_View;
	private Rapport_Panel_View rapport_Panel_View;
	private Rapport_Darty_Avoirs_View rapport_DRT_Avoirs_View;
	private DartyDao dartyDao;
	private DateConvertor dateConvertor;
	private NbrDayPerMonth nbrDayPerMonth;
	private AvoirAncienAboModel avoirAncienAboModel;
	private AvoirNouvelAboModel avoirNouvelAboModel;
	private GenerationAvoirsDRT generationAvoirsDRT;
	private InfosAvoirsModel infosAvoirsModel;
	private String fromDate="";
	private String toDate="";
	private String nextFromDate="";
	private String nextToDate="";
	private String month="";
	private String moisDecimal="";
	private String year="";
	private String mois1="";
	private String mois2="";
	private String annee="";
	private String fileName="";
	private String chemin="";
	private String numFacture="";
	private int nbrJr=0;
	private int nbrJrMoisFact=0;
    boolean isOpen = false;
    boolean suite =false;
	
	public Rapport_AVOIR_DRT_Controller(){
		
	}	
	
	public void rapport_avoir_drt(Facture_Panel_View facture_Panel_View,Rapport_Panel_View rapport_Panel_View,String fileName,String chemin) {	
		this.facture_Panel_View=facture_Panel_View;
		this.rapport_Panel_View=rapport_Panel_View;	
		this.fileName=fileName;
		this.chemin=chemin;
		dateConvertor= new DateConvertor();
		nbrDayPerMonth= new NbrDayPerMonth();
		avoirAncienAboModel=new AvoirAncienAboModel();
		avoirNouvelAboModel=new AvoirNouvelAboModel();
		rapport_DRT_Avoirs_View= new Rapport_Darty_Avoirs_View();
		dartyDao=new DartyDao();
		generationAvoirsDRT=new GenerationAvoirsDRT();
		infosAvoirsModel=new InfosAvoirsModel();
		rapport_Panel_View.center_north.removeAll();
		rapport_Panel_View.center_south.removeAll();
		rapport_Panel_View.center_north.repaint();
		rapport_Panel_View.center_south.repaint();
		rapport_Panel_View.center_north.add(rapport_DRT_Avoirs_View.rapport_Panel,BorderLayout.WEST);
		rapport_DRT_Avoirs_View.lbTitle.setVisible(true);
		numFacture=facture_Panel_View.txtNumFact.getText();
		rapport_DRT_Avoirs_View.lbFile.setText(fileName);
		rapport_DRT_Avoirs_View.lbFile.setVisible(true);
		
		Thread t = new Thread() {
            public void run() {             
            getDrt_infos_avoir();
            if(suite==false){
            	getInfos_resiliation();
            		if(suite==false){
			            ajoutAncienAbo();
			            ajoutNouvelAbo();
			            getRecapitulatif();
			            getRecapRapport();
            		}
            	}
            }
          };          
          t.start();	 
	}

	public void getDrt_infos_avoir(){
		//Recuperaction infos avoir du fichier 'AAE Darty frais de résiliation ' en cours
		try {	
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
            Thread.sleep(1000);
            rapport_DRT_Avoirs_View.lbTempFile.setVisible(true);
    		rapport_DRT_Avoirs_View.lbEquiv1.setVisible(true);
    		rapport_DRT_Avoirs_View.lbTempFileRes.setVisible(true);
    		try {
    			infosAvoirsModel=generationAvoirsDRT.infos_Avoir_Darty();
    		} catch (IOException e) {
    			System.out.println(e);
    			suite=true;
    			String openFile = "Veuillez fermer les fichiers d'avoirs\nouverts puis reappuyer sur 'Générer Avoir Facture'";
    			JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
    			rapport_Panel_View.center_north.removeAll();
        		setOpen(true);
    		}
    		Thread.sleep(1000);
    		rapport_DRT_Avoirs_View.lbTempFileRes.setText("[ ok ]");
    		rapport_DRT_Avoirs_View.lbTempFileRes.setForeground(Color.green);
    		facture_Panel_View.btValider.setEnabled(true);
    		facture_Panel_View.cbFacture.setEnabled(true);
          } catch (InterruptedException Te) {
        	  System.out.println(Te);
          }
		}
		
	public void getInfos_resiliation(){
		//Modification onglet frais de Résiliation DSL.
		try {	
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
            Thread.sleep(1000);
            fromDate=dateConvertor.getConvertMonth(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
    		toDate=dateConvertor.getConvertMonth(facture_Panel_View.tableModel.getValueAt(0, 1).toString());
    		moisDecimal=facture_Panel_View.tableModel.getValueAt(0, 1).toString().substring(3, 5);
    		year=facture_Panel_View.tableModel.getValueAt(0, 0).toString().substring(6);
    		rapport_DRT_Avoirs_View.lbInput.setVisible(true);
    		rapport_DRT_Avoirs_View.lbEquiv2.setVisible(true);
    		rapport_DRT_Avoirs_View.lbInputRes.setVisible(true);
    		try {
    			generationAvoirsDRT.fraisResiliationDSL(infosAvoirsModel,fromDate,toDate,year,fileName,chemin,moisDecimal);
    		} catch (IOException e) {
    			suite=true;
    			String openFile = "Veuillez fermer les fichiers d'avoirs\nouverts puis reappuyer sur 'Générer Avoir Facture'";
    			JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
    			rapport_Panel_View.center_north.removeAll();
        		setOpen(true);
    		}
    		Thread.sleep(1000);
    		rapport_DRT_Avoirs_View.lbInputRes.setText("[ ok ]");
    		rapport_DRT_Avoirs_View.lbInputRes.setForeground(Color.green);
    		facture_Panel_View.btValider.setEnabled(true);
    		facture_Panel_View.cbFacture.setEnabled(true);
          } catch (InterruptedException Te) {
        	  System.out.println(Te);
          }
		}
		
	public void ajoutAncienAbo(){
		if(suite==false){
		//ajout liste Ancien abo 2P THD
			try {	
				facture_Panel_View.btValider.setEnabled(false);
				facture_Panel_View.cbFacture.setEnabled(false);
	            Thread.sleep(1000);
	            rapport_DRT_Avoirs_View.lbAboFile.setVisible(true);
	    		rapport_DRT_Avoirs_View.lbEquiv3.setVisible(true);
	    		rapport_DRT_Avoirs_View.lbAboFileRes.setVisible(true);
	    		ArrayList<AvoirAncienAboModel> listAvoirAncienAboModel =new ArrayList<AvoirAncienAboModel>();
	    		nbrJr=dateConvertor.getEnglishMonth(facture_Panel_View.tableModel.getValueAt(0, 1).toString());
	    		nbrJr=nbrDayPerMonth.getNbrDayForMonth(nbrJr);
	    		nextToDate=nbrJr+facture_Panel_View.tableModel.getValueAt(0, 1).toString().substring(2);
	    		month=facture_Panel_View.tableModel.getValueAt(0, 1).toString().substring(6)+facture_Panel_View.tableModel.getValueAt(0, 1).toString().substring(3,5);
	    		dartyDao.updateAncienAbo(Integer.valueOf(month), nbrJr);
//	    		dartyDao.updateAncienAbo(201404, 30);
	    		listAvoirAncienAboModel=dartyDao.getListAvoirAncienAbo();			
	    		dartyDao.closeConnection();
	    		try {
	    			generationAvoirsDRT.ancienAbo(listAvoirAncienAboModel,nbrJr);
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		Thread.sleep(1000);
	    		rapport_DRT_Avoirs_View.lbAboFileRes.setText("[ ok ]");
	    		rapport_DRT_Avoirs_View.lbAboFileRes.setForeground(Color.green);
	    		facture_Panel_View.btValider.setEnabled(true);
	    		facture_Panel_View.cbFacture.setEnabled(true);
	          } catch (InterruptedException Te) {
	        	  System.out.println(Te);
	          }
			}
		}
		
		public void ajoutNouvelAbo(){
		//ajout liste Nouvel abo 2P THD
			try {	
				facture_Panel_View.btValider.setEnabled(false);
				facture_Panel_View.cbFacture.setEnabled(false);
	            Thread.sleep(1000);
	            rapport_DRT_Avoirs_View.lbFASFile.setVisible(true);
	    		rapport_DRT_Avoirs_View.lbEquiv4.setVisible(true);
	    		rapport_DRT_Avoirs_View.lbFASFileRes.setVisible(true);		
	    		ArrayList<AvoirNouvelAboModel> listAvoirNouvelAboModel =new ArrayList<AvoirNouvelAboModel>();
	    		dartyDao.updateNouvelAbo(Integer.valueOf(month), nbrJr);
//	    		dartyDao.updateNouvelAbo(201404, 30);
	    		listAvoirNouvelAboModel=dartyDao.getListAvoirNouvelAbo();	
	    		dartyDao.closeConnection();
	    		try {
	    			generationAvoirsDRT.nouvelAbo(listAvoirNouvelAboModel,nbrJr);
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		Thread.sleep(1000);
	    		rapport_DRT_Avoirs_View.lbFASFileRes.setText("[ ok ]");
	    		rapport_DRT_Avoirs_View.lbFASFileRes.setForeground(Color.green);
	    		facture_Panel_View.btValider.setEnabled(true);
	    		facture_Panel_View.cbFacture.setEnabled(true);
	          } catch (InterruptedException Te) {
	        	  System.out.println(Te);
	          }
		}
		
		
		public void getRecapitulatif(){
		//Recapitulatif terminé
			try {	
				facture_Panel_View.btValider.setEnabled(false);
				facture_Panel_View.cbFacture.setEnabled(false);
	            Thread.sleep(1000);
	            rapport_DRT_Avoirs_View.lbRecapFile.setVisible(true);
	    		rapport_DRT_Avoirs_View.lbEquiv6.setVisible(true);
	    		rapport_DRT_Avoirs_View.lbRecapFileRes.setVisible(true);
	    			try {
	    				generationAvoirsDRT.ongletRecapAvoirs(fromDate,toDate,year);
	    			} catch (IOException e) {
	    				System.out.println(e);
	    				suite=true;
	    				String openFile = "Veuillez fermer les fichiers d'avoirs\nouverts puis reappuyer sur 'Générer Avoir Facture'";
	    				JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
	    				rapport_Panel_View.center_north.removeAll();
	    	    		setOpen(true);
	    			}				
	    			facture_Panel_View.btValider.setEnabled(true);
	        		facture_Panel_View.cbFacture.setEnabled(true);
	          } catch (InterruptedException Te) {
	        	  System.out.println(Te);
	          }
		}
		
		public void getRecapRapport(){
			try {	
				facture_Panel_View.btValider.setEnabled(false);
				facture_Panel_View.cbFacture.setEnabled(false);
	            Thread.sleep(1000);
	            try {
	    			generationAvoirsDRT.modifRecapAvoirFichierFactureDarty();
	    		} catch (IOException e) {
	    			System.out.println(e);
	    			suite=true;
	    			String openFile = "Veuillez fermer les fichiers de factures\nouvertes puis reappuyer sur 'Générer Avoir Facture'";
	    			JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
	    			rapport_Panel_View.center_north.removeAll();
	        		setOpen(true);
	    		}				
	    		rapport_Panel_View.center_south.add(rapport_DRT_Avoirs_View.remarqueSuccess(fileName,generationAvoirsDRT.repertoireSortie()),BorderLayout.WEST);	
	    		rapport_DRT_Avoirs_View.lbRecapFileRes.setText("[ ok ]");
	    		rapport_DRT_Avoirs_View.lbRecapFileRes.setForeground(Color.green);
	    		facture_Panel_View.btValider.setEnabled(true);
	    		facture_Panel_View.cbFacture.setEnabled(true);
	          } catch (InterruptedException Te) {
	        	  System.out.println(Te);
	          }
		}
		
	public boolean isOpen()
    {
        return isOpen;
    }

    public void setOpen(boolean isOpen)
    {
        this.isOpen = isOpen;
    }
}


