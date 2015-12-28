package factu_mb_param.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import factu_mb_param.GenerateFact.GenerationFactureDRT;
import factu_mb_param.dao.DartyDao;
import factu_mb_param.modele.ChargesReccurentesModel;
import factu_mb_param.modele.DateConvertor;
import factu_mb_param.modele.ElementIDModel;
import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.modele.NbrDayPerMonth;
import factu_mb_param.modele.OngletNrcModel;
import factu_mb_param.modele.OngletUsageModelDarty;
import factu_mb_param.modele.SubtypeCodeModel;
import factu_mb_param.modele.TempInfosFactuModel;
import factu_mb_param.view.Facture_Panel_View;
import factu_mb_param.view.Rapport_Darty_View;
import factu_mb_param.view.Rapport_Panel_View;



public class Rapport_DRT_Controller{
	
	private Facture_Panel_View facture_Panel_View;
	private Rapport_Panel_View rapport_Panel_View;
	private Rapport_Darty_View rapport_DRT_View;
	private DartyDao dartyDao;
	private DateConvertor dateConvertor;
	private NbrDayPerMonth nbrDayPerMonth;
	private GenerationFactureDRT generationFactureDRT;
	private ChargesReccurentesModel chargesReccurentesModel;
	private SubtypeCodeModel subtypeCodeModel;
	private ElementIDModel elementIDModel;
	private ArrayList<ElementIDModel> listEIdModels;
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
	private TempInfosFactuModel drtInfosFactuModel;
	private MontantBillInvoiceDetailModel drtMontantBillInvoiceDetailModel;
	public JLabel test;
    boolean isOpen = false;
    boolean suite =false;
	
	public Rapport_DRT_Controller(){
		
	}	
	
	public void rapport_drt(Facture_Panel_View facture_Panel_View,Rapport_Panel_View rapport_Panel_View,String filename,String chemin,String mois,String annee) {	
		this.facture_Panel_View=facture_Panel_View;
		this.rapport_Panel_View=rapport_Panel_View;	
		this.fileName=filename;
		this.chemin=chemin;
		this.mois=mois;
		this.annee=annee;
		dateConvertor=new DateConvertor();
		nbrDayPerMonth=new NbrDayPerMonth();
		rapport_DRT_View= new Rapport_Darty_View();
		dartyDao=new DartyDao();
		drtInfosFactuModel=new TempInfosFactuModel();
		subtypeCodeModel=new SubtypeCodeModel();
		drtMontantBillInvoiceDetailModel=new MontantBillInvoiceDetailModel();
		generationFactureDRT=new GenerationFactureDRT();
		chargesReccurentesModel= new ChargesReccurentesModel();
		elementIDModel= new ElementIDModel();
		rapport_Panel_View.center_north.removeAll();
		rapport_Panel_View.center_south.removeAll();
		rapport_Panel_View.center_north.repaint();
		rapport_Panel_View.center_south.repaint();
		rapport_Panel_View.center_north.add(rapport_DRT_View.rapport_Panel,BorderLayout.WEST);
		rapport_DRT_View.lbTitle.setVisible(true);
		numFacture=facture_Panel_View.txtNumFact.getText();
		rapport_DRT_View.lbFile.setText(fileName);
		rapport_DRT_View.lbFile.setVisible(true);
		
		Thread t = new Thread() {
            public void run() {             
            getDrt_infos_facture();
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
		
	public void getDrt_infos_facture(){
	//Modification fichier 'DRT_INFOS_FACTURE' en cours
		try {			
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
            Thread.sleep(1000);
            rapport_DRT_View.lbTempFile.setVisible(true);
        	rapport_DRT_View.lbEquiv1.setVisible(true);
        	rapport_DRT_View.lbTempFileRes.setVisible(true);
        	ArrayList<TempInfosFactuModel> listInfosFactuDRT =new ArrayList<TempInfosFactuModel>();
        	listInfosFactuDRT=dartyDao.getDRT_INFO_FATU(numFacture);
        	dartyDao.closeConnection();
        	month=dateConvertor.getMonthFact(facture_Panel_View.tableModel.getValueAt(0, 0).toString());;
        	year=dateConvertor.getYearFact(facture_Panel_View.tableModel.getValueAt(0, 0).toString());;
        	try {
        		generationFactureDRT.fillDRT_INFOS_FACTU(listInfosFactuDRT,month,year,mois,fileName,chemin);
        	} catch (IOException e) {
        		suite=true;
        		String openFile = "Veuillez fermer les fichiers de factures\nouvertes puis reappuyer sur 'Generer Facture.xls'";
        		JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
        		rapport_Panel_View.center_north.removeAll();
        		setOpen(true);
        	}
        	if(suite==false){
        		//ajout liste BID
        	ArrayList<MontantBillInvoiceDetailModel> listDRTMontantBillInvoiceDetail =new ArrayList<MontantBillInvoiceDetailModel>();
        	fromDate=facture_Panel_View.tableModel.getValueAt(0, 0).toString();
        	toDate=facture_Panel_View.tableModel.getValueAt(0, 3).toString();
        	nextFromDate=facture_Panel_View.tableModel.getValueAt(0, 1).toString();
        	nbrJr=dateConvertor.getEnglishMonth(facture_Panel_View.tableModel.getValueAt(0, 1).toString());
        	nbrJr=nbrDayPerMonth.getNbrDayForMonth(nbrJr);
        	nextToDate=nbrJr+facture_Panel_View.tableModel.getValueAt(0, 1).toString().substring(2);
        	listDRTMontantBillInvoiceDetail=dartyDao.getMontantBillInvoiceDetail(numFacture,fromDate,nextFromDate);			
        	dartyDao.closeConnection();
        	try {
        		generationFactureDRT.fillBill_Invoice_Detail(listDRTMontantBillInvoiceDetail);
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        		//ajout liste subtypecode
        	ArrayList<SubtypeCodeModel> listSubtypeCodeModels =new ArrayList<SubtypeCodeModel>();
        	listSubtypeCodeModels=dartyDao.getListSubtypeCode(numFacture);
        	dartyDao.closeConnection();
        	try {
        		generationFactureDRT.fillSubTypeCode(listSubtypeCodeModels);
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}     	
        	Thread.sleep(1000);	
        	rapport_DRT_View.lbTempFileRes.setText("[ ok ]");
        	rapport_DRT_View.lbTempFileRes.setForeground(Color.green);
        		
        		//recuparation de la liste des element_id
        		listEIdModels= new ArrayList<ElementIDModel>();
        		try {
        			listEIdModels=generationFactureDRT.elmtInfoFactuIntoInputs();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
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
        rapport_DRT_View.lbInput.setVisible(true);
    	rapport_DRT_View.lbEquiv2.setVisible(true);
    	rapport_DRT_View.lbInputRes.setVisible(true);
    	int jr=dateConvertor.getEnglishMonth(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
    	nbrJrMoisFact=nbrDayPerMonth.getNbrDayForMonth(jr);
    	try {
    		generationFactureDRT.ongletInputs(nbrJrMoisFact, numFacture, mois, annee,fileName);
    	} catch (IOException e) {
    		suite=true;
    		String openFile = "Veuillez fermer les fichiers de factures\nouvertes puis reappuyer sur 'Generer Facture.xls'";
    		JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
    		rapport_Panel_View.center_north.removeAll();
    		setOpen(true);
    	}		
    	if(suite==false){
    		Thread.sleep(1000);
    		rapport_DRT_View.lbInputRes.setText("[ ok ]");
    		rapport_DRT_View.lbInputRes.setForeground(Color.green);	
    		}
    	facture_Panel_View.btValider.setEnabled(true);
		facture_Panel_View.cbFacture.setEnabled(true);
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
            rapport_DRT_View.lbAboFile.setVisible(true);
        	rapport_DRT_View.lbEquiv3.setVisible(true);
        	rapport_DRT_View.lbAboFileRes.setVisible(true);		
        	try {
        		generationFactureDRT.ongletAbonnements(fromDate,toDate,nextFromDate,nextToDate,fileName,listEIdModels);
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        	Thread.sleep(1000);
        	rapport_DRT_View.lbAboFileRes.setText("[ ok ]");
        	rapport_DRT_View.lbAboFileRes.setForeground(Color.green);
        	facture_Panel_View.btValider.setEnabled(true);
    		facture_Panel_View.cbFacture.setEnabled(true);
          } catch (InterruptedException Te) {
        	  System.out.println(Te);
          }	
	}
	
	public void getNRC(){
	//Mis a jour des données 'NRC' en cours
		try {			
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
            Thread.sleep(1000);
            rapport_DRT_View.lbFASFile.setVisible(true);
        	rapport_DRT_View.lbEquiv4.setVisible(true);
        	rapport_DRT_View.lbFASFileRes.setVisible(true);
        	ArrayList<OngletNrcModel> listNrcDarty=new ArrayList<OngletNrcModel>();
        	listNrcDarty=dartyDao.getListNRC(numFacture, fromDate, nextFromDate);
        	try {
        		generationFactureDRT.ongletNRC(listNrcDarty);
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        	Thread.sleep(1000);
        	rapport_DRT_View.lbFASFileRes.setText("[ ok ]");
        	rapport_DRT_View.lbFASFileRes.setForeground(Color.green);
        	facture_Panel_View.btValider.setEnabled(true);
    		facture_Panel_View.cbFacture.setEnabled(true);
          } catch (InterruptedException Te) {
        	  System.out.println(Te);
          }	
	}
	
	public void getUsage(){
	//Mis a jour des données 'USAGE' en cours
		try {			
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
            Thread.sleep(1000);
            rapport_DRT_View.lbUsageFile.setVisible(true);
        	rapport_DRT_View.lbEquiv5.setVisible(true);
        	rapport_DRT_View.lbUsageFileRes.setVisible(true);
        	ArrayList<OngletUsageModelDarty> listUsageDarty=new ArrayList<OngletUsageModelDarty>();
        	listUsageDarty=dartyDao.getListUsage(numFacture);
        	try {
        		generationFactureDRT.ongletUsage(listUsageDarty);
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}		
        	Thread.sleep(1000);
        	rapport_DRT_View.lbUsageFileRes.setText("[ ok ]");
        	rapport_DRT_View.lbUsageFileRes.setForeground(Color.green);
        	facture_Panel_View.btValider.setEnabled(true);
    		facture_Panel_View.cbFacture.setEnabled(true);
          } catch (InterruptedException Te) {
        	  System.out.println(Te);
          }	
	}
	
	public void getRecapitulatif(){
		try {			
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
            Thread.sleep(1000);
            rapport_DRT_View.lbRecapFile.setVisible(true);
        	rapport_DRT_View.lbEquiv6.setVisible(true);
        	rapport_DRT_View.lbRecapFileRes.setVisible(true);
          //Recapitulatif terminé
        	try {
        		generationFactureDRT.ongletRecap();
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}       	
          } catch (InterruptedException Te) {
        	  System.out.println(Te);
          }
	}
	
	public void getRecapRapport(){
	//Recapitulatif facture
		try {			
            Thread.sleep(1000);
            try {
        		generationFactureDRT.ongletRecapFacture(numFacture,toDate);
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}        			
        	rapport_Panel_View.center_south.add(rapport_DRT_View.remarqueSuccess(fileName,generationFactureDRT.repertoireSortie()),BorderLayout.WEST);
        	rapport_DRT_View.lbRecapFileRes.setText("[ ok ]");
        	rapport_DRT_View.lbRecapFileRes.setForeground(Color.green);
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


