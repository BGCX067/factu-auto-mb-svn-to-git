package factu_mb_param.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import factu_mb_param.GenerateFact.GenerationFactureMTVC;
import factu_mb_param.dao.LeCableDao;
import factu_mb_param.modele.ChargesReccurentesModel;
import factu_mb_param.modele.DateConvertor;
import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.modele.NbrDayPerMonth;
import factu_mb_param.modele.OngletNrcModel;
import factu_mb_param.modele.OngletUsageModelMTVC;
import factu_mb_param.modele.TempInfosFactuModel;
import factu_mb_param.view.Facture_Panel_View;
import factu_mb_param.view.Rapport_LeCable_View;
import factu_mb_param.view.Rapport_Panel_View;



public class Rapport_MTVC_Controller{
	
	private Facture_Panel_View facture_Panel_View;
	private Rapport_Panel_View rapport_Panel_View;
	private Rapport_LeCable_View rapport_MTVC_View;
	private LeCableDao leCableDao;
	private DateConvertor dateConvertor;
	private NbrDayPerMonth nbrDayPerMonth;
	private GenerationFactureMTVC generationFactureMTVC;
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
	private TempInfosFactuModel tempInfosFactuModel;
	private MontantBillInvoiceDetailModel montantBillInvoiceDetailModel;
	public JLabel test;
    boolean isOpen = false;
    boolean suite =false;
	
	public Rapport_MTVC_Controller(){
		
	}	
	
	public void rapport_mtvc(Facture_Panel_View facture_Panel_View,Rapport_Panel_View rapport_Panel_View,String filename,String chemin,String mois,String annee) {	
		this.facture_Panel_View=facture_Panel_View;
		this.rapport_Panel_View=rapport_Panel_View;	
		this.fileName=filename;
		this.chemin=chemin;
		this.mois=mois;
		this.annee=annee;
		dateConvertor=new DateConvertor();
		nbrDayPerMonth=new NbrDayPerMonth();
		rapport_MTVC_View= new Rapport_LeCable_View();
		leCableDao=new LeCableDao();
		tempInfosFactuModel=new TempInfosFactuModel();
		montantBillInvoiceDetailModel=new MontantBillInvoiceDetailModel();
		generationFactureMTVC=new GenerationFactureMTVC();
		chargesReccurentesModel= new ChargesReccurentesModel();
		rapport_Panel_View.center_north.removeAll();
		rapport_Panel_View.center_south.removeAll();
		rapport_Panel_View.center_north.repaint();
		rapport_Panel_View.center_south.repaint();
		rapport_Panel_View.center_north.add(rapport_MTVC_View.rapport_Panel,BorderLayout.WEST);
		rapport_MTVC_View.lbTitle.setVisible(true);
		numFacture=facture_Panel_View.txtNumFact.getText();
		rapport_MTVC_View.lbFile.setText(fileName);
		rapport_MTVC_View.lbFile.setVisible(true);
		
		Thread t = new Thread() {
            public void run() {             
            getMtvc_infos_facture();
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
				
	public void getMtvc_infos_facture(){
	//Modification fichier 'MTVC_INFOS_FACTURE' en cours
	try {			
		facture_Panel_View.btValider.setEnabled(false);
		facture_Panel_View.cbFacture.setEnabled(false);
        Thread.sleep(1000);
        rapport_MTVC_View.lbTempFile.setVisible(true);
    	rapport_MTVC_View.lbEquiv1.setVisible(true);
    	rapport_MTVC_View.lbTempFileRes.setVisible(true);
    	ArrayList<TempInfosFactuModel> listInfosFactuMTVC =new ArrayList<TempInfosFactuModel>();
    	listInfosFactuMTVC=leCableDao.getLC_INFO_FATU(numFacture);
    	month=dateConvertor.getMonthFact(facture_Panel_View.tableModel.getValueAt(0, 0).toString());;
    	year=dateConvertor.getYearFact(facture_Panel_View.tableModel.getValueAt(0, 0).toString());;
    	try {
    		generationFactureMTVC.fillLC_INFOS_FACTU(listInfosFactuMTVC,month,year,fileName,chemin);
    	} catch (IOException e) {
    		suite=true;
    		String openFile = "Veuillez fermer les fichiers de factures\nouvertes puis reappuyer sur 'Generer Facture.xls'";
    		JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
    		rapport_Panel_View.center_north.removeAll();
    		setOpen(true);
    	}
    	if(suite==false){
    	ArrayList<MontantBillInvoiceDetailModel> listMontantBillInvoiceDetailEchu =new ArrayList<MontantBillInvoiceDetailModel>();
    	ArrayList<MontantBillInvoiceDetailModel> listMontantBillInvoiceDetailEchoir =new ArrayList<MontantBillInvoiceDetailModel>();
    	listMontantBillInvoiceDetailEchu=leCableDao.getMontantBillInvoiceDetailEchu(numFacture);	
    	listMontantBillInvoiceDetailEchoir=leCableDao.getMontantBillInvoiceDetailEchoir(numFacture);			
    	fromDate=facture_Panel_View.tableModel.getValueAt(0, 0).toString();
    	toDate=facture_Panel_View.tableModel.getValueAt(0, 3).toString();
    	nextFromDate=facture_Panel_View.tableModel.getValueAt(0, 1).toString();
    	nbrJr=dateConvertor.getEnglishMonth(facture_Panel_View.tableModel.getValueAt(0, 1).toString());
    	nbrJr=nbrDayPerMonth.getNbrDayForMonth(nbrJr);
    	nextToDate=nbrJr+facture_Panel_View.tableModel.getValueAt(0, 1).toString().substring(2);
    	try {
    		generationFactureMTVC.fillBill_Invoice_Detail(listMontantBillInvoiceDetailEchu,listMontantBillInvoiceDetailEchoir,fromDate,toDate,nextFromDate,nextToDate);
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	Thread.sleep(1000);	
    	rapport_MTVC_View.lbTempFileRes.setText("[ ok ]");
    	rapport_MTVC_View.lbTempFileRes.setForeground(Color.green);
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
            rapport_MTVC_View.lbInput.setVisible(true);
    		rapport_MTVC_View.lbEquiv2.setVisible(true);
    		rapport_MTVC_View.lbInputRes.setVisible(true);
    		int jr=dateConvertor.getEnglishMonth(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
    		nbrJrMoisFact=nbrDayPerMonth.getNbrDayForMonth(jr);
    		try {
    			generationFactureMTVC.ongletInputs(nbrJrMoisFact, numFacture, mois, annee,fileName);
    		} catch (IOException e) {
    			suite=true;
    			String openFile = "Veuillez fermer les fichiers de factures\nouvertes puis reappuyer sur 'Générer Facture.xls'";
    			JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
    			rapport_Panel_View.center_north.removeAll();
        		setOpen(true);
    		}	    		
    		if(suite==false){
    		Thread.sleep(1000);
    		rapport_MTVC_View.lbInputRes.setText("[ ok ]");
    		rapport_MTVC_View.lbInputRes.setForeground(Color.green);	
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
            rapport_MTVC_View.lbAboFile.setVisible(true);
    		rapport_MTVC_View.lbEquiv3.setVisible(true);
    		rapport_MTVC_View.lbAboFileRes.setVisible(true);
    		ArrayList<ChargesReccurentesModel> listChargesReccurentes= new ArrayList<ChargesReccurentesModel>();
    		try {
    			listChargesReccurentes=generationFactureMTVC.listChargesReccurentes();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		try {
    			generationFactureMTVC.ongletAbonnements(listChargesReccurentes);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		Thread.sleep(1000);
    		rapport_MTVC_View.lbAboFileRes.setText("[ ok ]");
    		rapport_MTVC_View.lbAboFileRes.setForeground(Color.green);
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
            rapport_MTVC_View.lbFASFile.setVisible(true);
    		rapport_MTVC_View.lbEquiv4.setVisible(true);
    		rapport_MTVC_View.lbFASFileRes.setVisible(true);
    		ArrayList<OngletNrcModel> listNrcMTVC=new ArrayList<OngletNrcModel>();
    		listNrcMTVC=leCableDao.getListNRC(numFacture,fromDate,nextFromDate);
    		try {
    			generationFactureMTVC.ongletNRC(listNrcMTVC);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		Thread.sleep(1000);
    		rapport_MTVC_View.lbFASFileRes.setText("[ ok ]");
    		rapport_MTVC_View.lbFASFileRes.setForeground(Color.green);
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
            rapport_MTVC_View.lbUsageFile.setVisible(true);
    		rapport_MTVC_View.lbEquiv5.setVisible(true);
    		rapport_MTVC_View.lbUsageFileRes.setVisible(true);
    		ArrayList<OngletUsageModelMTVC> listUsageMTVC=new ArrayList<OngletUsageModelMTVC>();
    		listUsageMTVC=leCableDao.getListUsage(numFacture);
    		try {
    			generationFactureMTVC.ongletUsage(listUsageMTVC);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}			
    		Thread.sleep(1000);
    		rapport_MTVC_View.lbUsageFileRes.setText("[ ok ]");
    		rapport_MTVC_View.lbUsageFileRes.setForeground(Color.green);
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
            rapport_MTVC_View.lbRecapFile.setVisible(true);
			rapport_MTVC_View.lbEquiv6.setVisible(true);
			rapport_MTVC_View.lbRecapFileRes.setVisible(true);	
		try {
			generationFactureMTVC.ongletRecapFacture(numFacture,toDate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			generationFactureMTVC.ongletRecap();
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
    		rapport_Panel_View.center_south.add(rapport_MTVC_View.remarqueSuccess(fileName,generationFactureMTVC.repertoireSortie()),BorderLayout.WEST);
    		rapport_MTVC_View.lbRecapFileRes.setText("[ ok ]");
    		rapport_MTVC_View.lbRecapFileRes.setForeground(Color.green);
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


