package factu_mb_param.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


import factu_mb_param.GenerateFact.GenerationFactureNC;
import factu_mb_param.dao.NumericableDao;
import factu_mb_param.modele.ChargesReccurentesModel;
import factu_mb_param.modele.DateConvertor;
import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.modele.NbrDayPerMonth;
import factu_mb_param.modele.OngletNrcModel;
import factu_mb_param.modele.OngletUsageModel;
import factu_mb_param.modele.TempInfosFactuModel;
import factu_mb_param.view.Facture_Panel_View;
import factu_mb_param.view.Rapport_NC_View;
import factu_mb_param.view.Rapport_Panel_View;



public class Rapport_NC_Controller{
	
	private Facture_Panel_View facture_Panel_View;
	private Rapport_Panel_View rapport_Panel_View;
	private Rapport_NC_View rapport_NC_View;
	private NumericableDao numericableDao;
	private DateConvertor dateConvertor;
	private NbrDayPerMonth nbrDayPerMonth;
	private GenerationFactureNC generationFactureNC;
	private ChargesReccurentesModel chargesReccurentesModel;
	private String fromDate="";
	private String toDate="";
	private String nextFromDate="";
	private String nextToDate="";
	private String month="";
	private String year="";
	private String mois="";
	private String annee="";
	private String fileName="";
	private String chemin="";
	private String numFacture="";
	private int nbrJr=0;
	private int nbrJrMoisFact=0;
	private TempInfosFactuModel ncInfosFactuModel;
	private MontantBillInvoiceDetailModel ncMontantBillInvoiceDetailModel;
	public JLabel test;
    boolean isOpen = false;
    boolean suite =false;
	
	public Rapport_NC_Controller(){
		
	}	
	
	public void rapport_nc(Facture_Panel_View facture_Panel_View,Rapport_Panel_View rapport_Panel_View,String fileName,String chemin,String mois,String annee) {	
		this.facture_Panel_View=facture_Panel_View;
		this.rapport_Panel_View=rapport_Panel_View;		
		this.fileName=fileName;
		this.chemin=chemin;
		this.mois=mois;
		this.annee=annee;
		dateConvertor=new DateConvertor();
		nbrDayPerMonth=new NbrDayPerMonth();
		rapport_NC_View= new Rapport_NC_View();
		numericableDao=new NumericableDao();
		ncInfosFactuModel=new TempInfosFactuModel();
		ncMontantBillInvoiceDetailModel=new MontantBillInvoiceDetailModel();
		generationFactureNC=new GenerationFactureNC();
		chargesReccurentesModel= new ChargesReccurentesModel();
		rapport_Panel_View.center_north.removeAll();
		rapport_Panel_View.center_south.removeAll();
		rapport_Panel_View.center_north.repaint();
		rapport_Panel_View.center_south.repaint();
		rapport_Panel_View.center_north.add(rapport_NC_View.rapport_Panel,BorderLayout.WEST);
		rapport_NC_View.lbTitle.setVisible(true);
		numFacture=facture_Panel_View.txtNumFact.getText();
		rapport_NC_View.lbFile.setText(fileName);
		rapport_NC_View.lbFile.setVisible(true);
		
		Thread t = new Thread() {
            public void run() {             
            getNc_infos_facture();
            if(suite==false){
            	getInfoInputs();
            		if(suite==false){
			            getTempAbo();
			            getNRC();
			            getUsage();
			            getRecapitulatif();
			            getRecapRapport();
            		}
            	}
            }
          };          
          t.start();	          
	}
		
	public void getNc_infos_facture(){
		try {		
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
          //Modification fichier 'NC_INFOS_FACTURE' en cours
			Thread.sleep(1000);	
    		rapport_NC_View.lbTempFile.setVisible(true);
    		rapport_NC_View.lbEquiv1.setVisible(true);
    		rapport_NC_View.lbTempFileRes.setVisible(true);    		
    		ArrayList<TempInfosFactuModel> listInfosFactuNC =new ArrayList<TempInfosFactuModel>();
    		listInfosFactuNC=numericableDao.getNC_INFO_FATU(numFacture);
    		month=dateConvertor.getMonthFact(facture_Panel_View.tableModel.getValueAt(0, 0).toString());;
    		year=dateConvertor.getYearFact(facture_Panel_View.tableModel.getValueAt(0, 0).toString());;
    		try {
    			generationFactureNC.fillBC_INFOS_FACTU(listInfosFactuNC,month,year,fileName,chemin);
    		} catch (IOException e) {
    			suite=true;
    			String openFile = "Veuillez fermer les fichiers de factures\nouvertes puis reappuyer sur 'Generer Facture.xls'";
    			JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
    			rapport_Panel_View.center_north.removeAll();
        		setOpen(true);
    		}
    		if(suite==false){
    		ArrayList<MontantBillInvoiceDetailModel> listNcMontantBillInvoiceDetail =new ArrayList<MontantBillInvoiceDetailModel>();
    		listNcMontantBillInvoiceDetail=numericableDao.getMontantBillInvoiceDetail(numFacture);	
    		fromDate=facture_Panel_View.tableModel.getValueAt(0, 0).toString();
    		toDate=facture_Panel_View.tableModel.getValueAt(0, 3).toString();
    		nextFromDate=facture_Panel_View.tableModel.getValueAt(0, 1).toString();
    		nbrJr=dateConvertor.getEnglishMonth(facture_Panel_View.tableModel.getValueAt(0, 1).toString());
    		nbrJr=nbrDayPerMonth.getNbrDayForMonth(nbrJr);
    		nextToDate=nbrJr+facture_Panel_View.tableModel.getValueAt(0, 1).toString().substring(2);
    		try {
    			generationFactureNC.fillBill_Invoice_Detail(listNcMontantBillInvoiceDetail,fromDate,toDate,nextFromDate,nextToDate);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		Thread.sleep(1000);	
    		rapport_NC_View.lbTempFileRes.setText("[ ok ]");
    		rapport_NC_View.lbTempFileRes.setForeground(Color.green);
    		rapport_NC_View.repaint();
    		}
    		facture_Panel_View.btValider.setEnabled(true);
    		facture_Panel_View.cbFacture.setEnabled(true);
          } catch (InterruptedException Te) {
        	  System.out.println(Te);
          }		
		}

		public void getInfoInputs(){
		suite=false;
		//Modification onglet 'Inputs' en cours
		try {
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
			Thread.sleep(1000);
			rapport_NC_View.lbInput.setVisible(true);
			rapport_NC_View.lbEquiv2.setVisible(true);
			rapport_NC_View.lbInputRes.setVisible(true);			
			rapport_NC_View.repaint();
			int jr=dateConvertor.getEnglishMonth(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
			nbrJrMoisFact=nbrDayPerMonth.getNbrDayForMonth(jr);
			try {
				generationFactureNC.ongletInputs(nbrJrMoisFact, numFacture, mois, annee,fileName);
			} catch (IOException e) {
				suite=true;
				String openFile = "Veuillez fermer les fichiers de factures\nouvertes puis reappuyer sur 'Generer Facture.xls'";
				JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
				rapport_Panel_View.center_north.removeAll();
	    		setOpen(true);
			}				
			if(suite==false){
			Thread.sleep(1000);
			rapport_NC_View.lbInputRes.setText("[ ok ]");
			rapport_NC_View.lbInputRes.setForeground(Color.green);
			rapport_NC_View.repaint();
			facture_Panel_View.btValider.setEnabled(true);
			facture_Panel_View.cbFacture.setEnabled(true);
			}
            
          } catch (InterruptedException Te) {
        	  System.out.println(Te);
          }
		
		}
		
		public void getTempAbo(){
		//Copie des données 'Temp'==>'Abonnements' en cours
		try {		
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
			Thread.sleep(1000);
			rapport_NC_View.lbAboFile.setVisible(true);
			rapport_NC_View.lbEquiv3.setVisible(true);
			rapport_NC_View.lbAboFileRes.setVisible(true);
			rapport_NC_View.repaint();
			ArrayList<ChargesReccurentesModel> listChargesReccurentes= new ArrayList<ChargesReccurentesModel>();
			try {
				listChargesReccurentes=generationFactureNC.listChargesReccurentes();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				generationFactureNC.ongletAbonnements(listChargesReccurentes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(1000);
			rapport_NC_View.lbAboFileRes.setText("[ ok ]");
			rapport_NC_View.lbAboFileRes.setForeground(Color.green);
			rapport_NC_View.repaint();         
			facture_Panel_View.btValider.setEnabled(true);
			facture_Panel_View.cbFacture.setEnabled(true);
          } catch (InterruptedException Te) {
        	  System.out.println(Te);
          }	
		
		}
		
		public void getNRC(){
		try {			
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
            Thread.sleep(1000);
          //Mis a jour des données 'FAS' en cours
    		rapport_NC_View.lbFASFile.setVisible(true);
    		rapport_NC_View.lbEquiv4.setVisible(true);
    		rapport_NC_View.lbFASFileRes.setVisible(true);
    		rapport_NC_View.repaint();
    		ArrayList<OngletNrcModel> listNrcNumericable=new ArrayList<OngletNrcModel>();
    		listNrcNumericable=numericableDao.getListNRC(numFacture, fromDate, nextFromDate);
    		try {
    			generationFactureNC.ongletFas(listNrcNumericable);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		Thread.sleep(1000);
    		rapport_NC_View.lbFASFileRes.setText("[ ok ]");
    		rapport_NC_View.lbFASFileRes.setForeground(Color.green);
    		rapport_NC_View.repaint();
    		facture_Panel_View.btValider.setEnabled(true);
    		facture_Panel_View.cbFacture.setEnabled(true);
          } catch (InterruptedException Te) {
        	  System.out.println(Te);
          }		
		}
		
		public void getUsage(){
		try {			
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
            Thread.sleep(1000);
          //Mis a jour des données 'USAGE' en cours
    		rapport_NC_View.lbUsageFile.setVisible(true);
    		rapport_NC_View.lbEquiv5.setVisible(true);
    		rapport_NC_View.lbUsageFileRes.setVisible(true);
    		rapport_NC_View.repaint();
    		ArrayList<OngletUsageModel> listUsageNumericable=new ArrayList<OngletUsageModel>();
    		listUsageNumericable=numericableDao.getListUsage(numFacture);
    		try {
    			generationFactureNC.ongletUsage(listUsageNumericable);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}			
    		Thread.sleep(1000);
    		rapport_NC_View.lbUsageFileRes.setText("[ ok ]");
    		rapport_NC_View.lbUsageFileRes.setForeground(Color.green);
    		rapport_NC_View.repaint();
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
            rapport_NC_View.lbRecapFile.setVisible(true);
    		rapport_NC_View.lbEquiv6.setVisible(true);
    		rapport_NC_View.lbRecapFileRes.setVisible(true);
            try {
    			generationFactureNC.ongletRecap();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}            
          } catch (InterruptedException Te) {
        	  System.out.println(Te);
          }
		}
		
		public void getRecapRapport(){
		try {			
            Thread.sleep(1000);
            rapport_Panel_View.center_south.add(rapport_NC_View.remarqueSuccess(fileName,generationFactureNC.repertoireSortie()),BorderLayout.WEST);
            rapport_Panel_View.repaint();
            rapport_NC_View.lbRecapFileRes.setText("[ ok ]");
    		rapport_NC_View.lbRecapFileRes.setForeground(Color.green);
    		rapport_NC_View.repaint();
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


