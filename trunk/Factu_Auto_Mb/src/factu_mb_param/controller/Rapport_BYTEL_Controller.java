package factu_mb_param.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import factu_mb_param.GenerateFact.GenerationFactureBOUYGUES;
import factu_mb_param.GenerateFact.GenerationFactureBOUYGUESXls;
import factu_mb_param.dao.BouyguesDao;
import factu_mb_param.modele.ChargesReccurentesModel;
import factu_mb_param.modele.DateConvertor;
import factu_mb_param.modele.ElementIDModel;
import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.modele.NbrDayPerMonth;
import factu_mb_param.modele.OngletAboDetailModelBYTEL;
import factu_mb_param.modele.OngletNrcModel;
import factu_mb_param.modele.OngletRecoSansFasModelBYTEL;
import factu_mb_param.modele.SubtypeCodeModel;
import factu_mb_param.modele.TempInfosFactuModel;
import factu_mb_param.view.Facture_Panel_View;
import factu_mb_param.view.Rapport_Bytel_View;
import factu_mb_param.view.Rapport_Panel_View;



public class Rapport_BYTEL_Controller{
	
	private Facture_Panel_View facture_Panel_View;
	private Rapport_Panel_View rapport_Panel_View;
	private Rapport_Bytel_View rapport_Bytel_View;
	private BouyguesDao bouyguesDao;
	private DateConvertor dateConvertor;
	private NbrDayPerMonth nbrDayPerMonth;
	private GenerationFactureBOUYGUES generationFactureBouygues;
	private GenerationFactureBOUYGUESXls generationFactureBOUYGUESXls;
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
	private TempInfosFactuModel bytelInfosFactuModel;
	private MontantBillInvoiceDetailModel bytelMontantBillInvoiceDetailModel;
	public JLabel test;
    boolean isOpen = false;
    boolean suite =false;
	
	public Rapport_BYTEL_Controller(){
		
	}	
	
	public void rapport_bytel(Facture_Panel_View facture_Panel_View,Rapport_Panel_View rapport_Panel_View,String filename,String chemin,String mois,String annee) {	
		this.facture_Panel_View=facture_Panel_View;
		this.rapport_Panel_View=rapport_Panel_View;	
		this.fileName=filename;
		this.chemin=chemin;
		this.mois=mois;
		this.annee=annee;
		dateConvertor=new DateConvertor();
		nbrDayPerMonth=new NbrDayPerMonth();
		rapport_Bytel_View= new Rapport_Bytel_View();
		bouyguesDao=new BouyguesDao();
		bytelInfosFactuModel=new TempInfosFactuModel();
		subtypeCodeModel=new SubtypeCodeModel();
		bytelMontantBillInvoiceDetailModel=new MontantBillInvoiceDetailModel();
		generationFactureBouygues=new GenerationFactureBOUYGUES();
		generationFactureBOUYGUESXls=new GenerationFactureBOUYGUESXls();
		chargesReccurentesModel= new ChargesReccurentesModel();
		elementIDModel= new ElementIDModel();
		rapport_Panel_View.center_north.removeAll();
		rapport_Panel_View.center_south.removeAll();
		rapport_Panel_View.center_north.repaint();
		rapport_Panel_View.center_south.repaint();
		rapport_Panel_View.center_north.add(rapport_Bytel_View.rapport_Panel,BorderLayout.WEST);
		rapport_Bytel_View.lbTitle.setVisible(true);
		numFacture=facture_Panel_View.txtNumFact.getText();
		rapport_Bytel_View.lbFile.setText(fileName);
		rapport_Bytel_View.lbFile.setVisible(true);
		
		Thread t = new Thread() {
            public void run() {             
            getDrt_infos_facture();
            if(suite==false){
            	getInfoInputs();
            		if(suite==false){
            			getTempAbo();
//            			getAboDetail();
            			getRecoSansFas();
			            getNRC();			           
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
		    rapport_Bytel_View.lbTempFile.setVisible(true);
			rapport_Bytel_View.lbEquiv1.setVisible(true);
			rapport_Bytel_View.lbTempFileRes.setVisible(true);
			ArrayList<TempInfosFactuModel> listInfosFactuBYTEL =new ArrayList<TempInfosFactuModel>();
			listInfosFactuBYTEL=bouyguesDao.getBT_INFO_FATU(numFacture);
			bouyguesDao.closeConnection();
			month=dateConvertor.getMonthFact(facture_Panel_View.tableModel.getValueAt(0, 0).toString());;
			year=dateConvertor.getYearFact(facture_Panel_View.tableModel.getValueAt(0, 0).toString());;
			try {
				generationFactureBOUYGUESXls.fillDRT_INFOS_FACTU(listInfosFactuBYTEL,month,year,mois,fileName,chemin);
			}catch (IOException e) {
				suite=true;
				String openFile = "Veuillez fermer les fichiers de factures\nouvertes puis reappuyer sur 'Generer Facture.xls'";
				JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
				rapport_Panel_View.center_north.removeAll();
	    		setOpen(true);
			}
			if(suite==false){
			//ajout liste BID
			ArrayList<MontantBillInvoiceDetailModel> listBYTELMontantBillInvoiceDetailEchu =new ArrayList<MontantBillInvoiceDetailModel>();
			ArrayList<MontantBillInvoiceDetailModel> listBYTELMontantBillInvoiceDetailEchoir =new ArrayList<MontantBillInvoiceDetailModel>();
			fromDate=facture_Panel_View.tableModel.getValueAt(0, 0).toString();
			toDate=facture_Panel_View.tableModel.getValueAt(0, 3).toString();
			nextFromDate=facture_Panel_View.tableModel.getValueAt(0, 1).toString();
			nbrJr=dateConvertor.getEnglishMonth(facture_Panel_View.tableModel.getValueAt(0, 1).toString());
			nbrJr=nbrDayPerMonth.getNbrDayForMonth(nbrJr);
			nextToDate=nbrJr+facture_Panel_View.tableModel.getValueAt(0, 1).toString().substring(2);
			listBYTELMontantBillInvoiceDetailEchu=bouyguesDao.getMontantBillInvoiceDetailEchu(numFacture);	
			listBYTELMontantBillInvoiceDetailEchoir=bouyguesDao.getMontantBillInvoiceDetailEchoir(numFacture);			
			bouyguesDao.closeConnection();
			//echu,echoir
			try {
				generationFactureBOUYGUESXls.fillBill_Invoice_Detail(listBYTELMontantBillInvoiceDetailEchu,listBYTELMontantBillInvoiceDetailEchoir,fromDate,toDate,nextFromDate,nextToDate);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(1000);	
			rapport_Bytel_View.lbTempFileRes.setText("[ ok ]");
			rapport_Bytel_View.lbTempFileRes.setForeground(Color.green);
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
		    rapport_Bytel_View.lbInput.setVisible(true);
			rapport_Bytel_View.lbEquiv2.setVisible(true);
			rapport_Bytel_View.lbInputRes.setVisible(true);
			int jr=dateConvertor.getEnglishMonth(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
			nbrJrMoisFact=nbrDayPerMonth.getNbrDayForMonth(jr);
			try {
				generationFactureBOUYGUESXls.ongletInputs(nbrJrMoisFact, numFacture, mois, annee,fileName);
			} catch (IOException e) {
				suite=true;
				String openFile = "Veuillez fermer les fichiers de factures\nouvertes puis reappuyer sur 'Generer Facture.xls'";
				JOptionPane.showMessageDialog(null, openFile, "Attention", 2);
				rapport_Panel_View.center_north.removeAll();
	    		setOpen(true);
			}			
			Thread.sleep(1000);	
			rapport_Bytel_View.lbInputRes.setText("[ ok ]");
			rapport_Bytel_View.lbInputRes.setForeground(Color.green);
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
		    rapport_Bytel_View.lbAboFile.setVisible(true);
			rapport_Bytel_View.lbEquiv3.setVisible(true);
			rapport_Bytel_View.lbAboFileRes.setVisible(true);
		ArrayList<ChargesReccurentesModel> listChargesReccurentes= new ArrayList<ChargesReccurentesModel>();
		try {
			listChargesReccurentes=generationFactureBOUYGUESXls.listChargesReccurentes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			generationFactureBOUYGUESXls.ongletAbonnements(listChargesReccurentes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(1000);	
		rapport_Bytel_View.lbAboFileRes.setText("[ ok ]");
		rapport_Bytel_View.lbAboFileRes.setForeground(Color.green);		
		facture_Panel_View.btValider.setEnabled(true);
		facture_Panel_View.cbFacture.setEnabled(true);
		} catch (InterruptedException Te) {
			  System.out.println(Te);
		}			
	}
	
	public void getAboDetail(){
		//Mis a jour des données 'Abonnements Détails' en cours
		try {			
			facture_Panel_View.btValider.setEnabled(false);
			facture_Panel_View.cbFacture.setEnabled(false);
		    Thread.sleep(1000);	
		    rapport_Bytel_View.lbAboDetail.setVisible(true);
			rapport_Bytel_View.lbEquiv4.setVisible(true);
			rapport_Bytel_View.lbAboDetailRes.setVisible(true);
			ArrayList<OngletAboDetailModelBYTEL> listAboDetailBytel=new ArrayList<OngletAboDetailModelBYTEL>();
			listAboDetailBytel=bouyguesDao.getListAboDetail(nextFromDate);
			System.out.println("TAILLE ABO DETAIL  : "+listAboDetailBytel.size());
			try {
				generationFactureBOUYGUESXls.ongletAbonnementDetail(listAboDetailBytel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(1000);	
			rapport_Bytel_View.lbAboDetailRes.setText("[ ok ]");
			rapport_Bytel_View.lbAboDetailRes.setForeground(Color.green);	
			facture_Panel_View.btValider.setEnabled(true);
			facture_Panel_View.cbFacture.setEnabled(true);
		} catch (InterruptedException Te) {
			  System.out.println(Te);
		}		
	}
	
	public void getRecoSansFas(){
		//Mis a jour des données 'Recos 100M - 200M sans FAS' en cours
				try {			
					facture_Panel_View.btValider.setEnabled(false);
					facture_Panel_View.cbFacture.setEnabled(false);
				    Thread.sleep(1000);	
				    rapport_Bytel_View.lbRecoSansFas.setVisible(true);
					rapport_Bytel_View.lbEquiv5.setVisible(true);
					rapport_Bytel_View.lbRecoSansFasRes.setVisible(true);
					ArrayList<OngletRecoSansFasModelBYTEL> listRecoSansFasBytel=new ArrayList<OngletRecoSansFasModelBYTEL>();
					listRecoSansFasBytel=bouyguesDao.getListRecoSansFas(fromDate,nextFromDate);
					System.out.println("TAILLE LISTE RECO SANS FAS : "+listRecoSansFasBytel.size());
					try {
						generationFactureBOUYGUESXls.ongletRecoSansFas(listRecoSansFasBytel);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Thread.sleep(1000);	
					rapport_Bytel_View.lbRecoSansFasRes.setText("[ ok ]");
					rapport_Bytel_View.lbRecoSansFasRes.setForeground(Color.green);	
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
		    rapport_Bytel_View.lbNRCFile.setVisible(true);
			rapport_Bytel_View.lbEquiv6.setVisible(true);
			rapport_Bytel_View.lbNRCFileRes.setVisible(true);
			ArrayList<OngletNrcModel> listNrcBytel=new ArrayList<OngletNrcModel>();
			listNrcBytel=bouyguesDao.getListNRC(numFacture, fromDate, nextFromDate);
			System.out.println("TAILLE LISTE NRC BOUYGUES : "+listNrcBytel.size());
			try {
				generationFactureBOUYGUESXls.ongletNRC(listNrcBytel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(1000);	
			rapport_Bytel_View.lbNRCFileRes.setText("[ ok ]");
			rapport_Bytel_View.lbNRCFileRes.setForeground(Color.green);	
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
		    try {
				rapport_Bytel_View.lbRecapFile.setVisible(true);
				rapport_Bytel_View.lbEquiv7.setVisible(true);
				rapport_Bytel_View.lbRecapFileRes.setVisible(true);
				generationFactureBOUYGUESXls.ongletRecap();
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
		    rapport_Bytel_View.lbEquiv7.setVisible(true);
		    try {
		    	generationFactureBOUYGUESXls.ongletRecapFacture(numFacture,toDate);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
			rapport_Panel_View.center_south.add(rapport_Bytel_View.remarqueSuccess(fileName,generationFactureBOUYGUESXls.repertoireSortie()),BorderLayout.WEST);
			rapport_Bytel_View.lbRecapFileRes.setText("[ ok ]");
			rapport_Bytel_View.lbRecapFileRes.setForeground(Color.green);
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


