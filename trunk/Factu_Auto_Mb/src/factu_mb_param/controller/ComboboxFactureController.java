package factu_mb_param.controller;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import factu_mb_param.dao.BillRefNo3PDao;
import factu_mb_param.dao.BillRefNoNCDao;
import factu_mb_param.modele.Config;
import factu_mb_param.modele.ResumeFactModel;
import factu_mb_param.view.Facture_Panel_View;
import factu_mb_param.view.Rapport_Panel_View;



public class ComboboxFactureController implements ItemListener{
	
	private Facture_Panel_View facture_Panel_View;
	private Rapport_Panel_View rapport_Panel_View;
	private BillRefNo3PDao billRefNo3PDao;
	private BillRefNoNCDao billRefNoNCDao;
	private Config config;
	private String cpt="";
	private int numFact=0;
	private ResumeFactModel resumeFactModel;
	
	
	public ComboboxFactureController(Facture_Panel_View facture_Panel_View,Rapport_Panel_View rapport_Panel_View){
		this.facture_Panel_View= facture_Panel_View;
		this.rapport_Panel_View=rapport_Panel_View;
		config=new Config();
		billRefNo3PDao=new BillRefNo3PDao();
		billRefNoNCDao=new BillRefNoNCDao();
		resumeFactModel=new ResumeFactModel();
	}	
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getStateChange()==ItemEvent.SELECTED){
			if(e.getItem().equals(facture_Panel_View.mb[0])){
				System.out.println("VIDE");
				facture_Panel_View.txtNumCompte.setText(null);
				facture_Panel_View.txtNumFact.setText(null);
				facture_Panel_View.dateEditorEchoir.setText(facture_Panel_View.selectDate);
				facture_Panel_View.dateEditorEchoir.setForeground(Color.gray);
				facture_Panel_View.dateEditorEchu.setText(facture_Panel_View.selectDate);
				facture_Panel_View.dateEditorEchu.setForeground(Color.gray);
				if(facture_Panel_View.tableModel.getRowCount()>0){
					facture_Panel_View.tableModel.removeRow(0);
					}
				//Enleve le panel Recap Facture
				rapport_Panel_View.lbDB.setText(null);
				rapport_Panel_View.lbDBResult.setText(null);
				rapport_Panel_View.lbFact.setText(null);
				rapport_Panel_View.lbFactResult.setText(null);
				rapport_Panel_View.lbActNo.setText(null);
				rapport_Panel_View.lbActNoResult.setText(null);
				rapport_Panel_View.lbBillRefNo.setText(null);
				rapport_Panel_View.lbBillRefNoResult.setText(null);
				rapport_Panel_View.lbEquiv1.setVisible(false);
				rapport_Panel_View.lbEquiv2.setVisible(false);
				rapport_Panel_View.lbEquiv3.setVisible(false);
				rapport_Panel_View.lbEquiv4.setVisible(false);
				facture_Panel_View.cbAvoirs.setVisible(false);
				facture_Panel_View.lbAvoirs.setVisible(false);
			}
			if(e.getItem().equals(facture_Panel_View.mb[1])){
				System.out.println("NUMERICABLE");
				rapport_Panel_View.center_south.removeAll();
				rapport_Panel_View.center_north.removeAll();
				if(facture_Panel_View.tableModel.getRowCount()>0){
				facture_Panel_View.tableModel.removeRow(0);
				}
				cpt=config.getCptNC();
				facture_Panel_View.txtNumCompte.setText(cpt);
				numFact=billRefNoNCDao.getBillRefNo(Integer.valueOf(cpt));
				facture_Panel_View.txtNumFact.setText(String.valueOf(numFact));
				resumeFactModel=billRefNoNCDao.getBillRefNoDetail(numFact);
				Object[] columnValues = {resumeFactModel.getFromDate(),resumeFactModel.getToDate(),
						resumeFactModel.getPrepDate(),resumeFactModel.getStatDate()};			
				facture_Panel_View.tableModel.addRow(columnValues);	
				facture_Panel_View.dateEditorEchu.setText(resumeFactModel.getFromDate());
				facture_Panel_View.dateEditorEchu.setForeground(Color.black);
				facture_Panel_View.dateEditorEchoir.setText(resumeFactModel.getToDate());
				facture_Panel_View.dateEditorEchoir.setForeground(Color.black);
				billRefNoNCDao.closeConnection();
				//Remplissage du panel Recap Facture
				rapport_Panel_View.lbDB.setText("Database Connect");
				rapport_Panel_View.lbDBResult.setText("CUSTNC");
				rapport_Panel_View.lbFact.setText("Facture");
				rapport_Panel_View.lbFactResult.setText(facture_Panel_View.mb[1]);
				rapport_Panel_View.lbActNo.setText("Account_No");
				rapport_Panel_View.lbActNoResult.setText(cpt);
				rapport_Panel_View.lbBillRefNo.setText("Bill_Ref_No");
				rapport_Panel_View.lbBillRefNoResult.setText(String.valueOf(numFact));
				rapport_Panel_View.lbEquiv1.setVisible(true);
				rapport_Panel_View.lbEquiv2.setVisible(true);
				rapport_Panel_View.lbEquiv3.setVisible(true);
				rapport_Panel_View.lbEquiv4.setVisible(true);
				facture_Panel_View.cbAvoirs.setVisible(false);
				facture_Panel_View.lbAvoirs.setVisible(false);
				rapport_Panel_View.center_south.repaint();
				rapport_Panel_View.center_north.repaint();
			}
			if(e.getItem().equals(facture_Panel_View.mb[2])){
				System.out.println("BOUYGUES");
				rapport_Panel_View.center_south.removeAll();
				rapport_Panel_View.center_north.removeAll();
				if(facture_Panel_View.tableModel.getRowCount()>0){
					facture_Panel_View.tableModel.removeRow(0);
					}
				cpt=config.getCptBytel();
				facture_Panel_View.txtNumCompte.setText(cpt);
				numFact=billRefNoNCDao.getBillRefNo(Integer.valueOf(cpt));
				facture_Panel_View.txtNumFact.setText(String.valueOf(numFact));
				resumeFactModel=billRefNoNCDao.getBillRefNoDetail(numFact);
				Object[] columnValues = {resumeFactModel.getFromDate(),resumeFactModel.getToDate(),
						resumeFactModel.getPrepDate(),resumeFactModel.getStatDate()};			
				facture_Panel_View.tableModel.addRow(columnValues);	
				facture_Panel_View.dateEditorEchu.setText(resumeFactModel.getFromDate());
				facture_Panel_View.dateEditorEchu.setForeground(Color.black);
				facture_Panel_View.dateEditorEchoir.setText(resumeFactModel.getToDate());
				facture_Panel_View.dateEditorEchoir.setForeground(Color.black);
				billRefNoNCDao.closeConnection();
				//Remplissage du panel Recap Facture
				rapport_Panel_View.lbDB.setText("Database Connect");
				rapport_Panel_View.lbDBResult.setText("CUSTNC");
				rapport_Panel_View.lbFact.setText("Facture");
				rapport_Panel_View.lbFactResult.setText(facture_Panel_View.mb[2]);
				rapport_Panel_View.lbActNo.setText("Account_No");
				rapport_Panel_View.lbActNoResult.setText(cpt);
				rapport_Panel_View.lbBillRefNo.setText("Bill_Ref_No");
				rapport_Panel_View.lbBillRefNoResult.setText(String.valueOf(numFact));
				rapport_Panel_View.lbEquiv1.setVisible(true);
				rapport_Panel_View.lbEquiv2.setVisible(true);
				rapport_Panel_View.lbEquiv3.setVisible(true);
				rapport_Panel_View.lbEquiv4.setVisible(true);
				facture_Panel_View.cbAvoirs.setVisible(false);
				facture_Panel_View.lbAvoirs.setVisible(false);
				rapport_Panel_View.center_south.repaint();
				rapport_Panel_View.center_north.repaint();
			}
			if(e.getItem().equals(facture_Panel_View.mb[3])){
				System.out.println("LE CABLE");
				rapport_Panel_View.center_south.removeAll();
				rapport_Panel_View.center_north.removeAll();
				if(facture_Panel_View.tableModel.getRowCount()>0){
					facture_Panel_View.tableModel.removeRow(0);
					}
				cpt=config.getCptLeCable();
				facture_Panel_View.txtNumCompte.setText(cpt);
				numFact=billRefNoNCDao.getBillRefNo(Integer.valueOf(cpt));
				facture_Panel_View.txtNumFact.setText(String.valueOf(numFact));
				resumeFactModel=billRefNoNCDao.getBillRefNoDetail(numFact);
				Object[] columnValues = {resumeFactModel.getFromDate(),resumeFactModel.getToDate(),
						resumeFactModel.getPrepDate(),resumeFactModel.getStatDate()};			
				facture_Panel_View.tableModel.addRow(columnValues);	
				facture_Panel_View.dateEditorEchu.setText(resumeFactModel.getFromDate());
				facture_Panel_View.dateEditorEchu.setForeground(Color.black);
				facture_Panel_View.dateEditorEchoir.setText(resumeFactModel.getToDate());
				facture_Panel_View.dateEditorEchoir.setForeground(Color.black);
				billRefNoNCDao.closeConnection();
				//Remplissage du panel Recap Facture
				rapport_Panel_View.lbDB.setText("Database Connect");
				rapport_Panel_View.lbDBResult.setText("CUSTNC");
				rapport_Panel_View.lbFact.setText("Facture");
				rapport_Panel_View.lbFactResult.setText(facture_Panel_View.mb[3]);
				rapport_Panel_View.lbActNo.setText("Account_No");
				rapport_Panel_View.lbActNoResult.setText(cpt);
				rapport_Panel_View.lbBillRefNo.setText("Bill_Ref_No");
				rapport_Panel_View.lbBillRefNoResult.setText(String.valueOf(numFact));
				rapport_Panel_View.lbEquiv1.setVisible(true);
				rapport_Panel_View.lbEquiv2.setVisible(true);
				rapport_Panel_View.lbEquiv3.setVisible(true);
				rapport_Panel_View.lbEquiv4.setVisible(true);
				facture_Panel_View.cbAvoirs.setVisible(false);
				facture_Panel_View.lbAvoirs.setVisible(false);
				rapport_Panel_View.center_south.repaint();
				rapport_Panel_View.center_north.repaint();
			}
			if(e.getItem().equals(facture_Panel_View.mb[4])){
				System.out.println("OMT");
				rapport_Panel_View.center_south.removeAll();
				rapport_Panel_View.center_north.removeAll();
				if(facture_Panel_View.tableModel.getRowCount()>0){
					facture_Panel_View.tableModel.removeRow(0);
					}
				cpt=config.getCptOmt();
				facture_Panel_View.txtNumCompte.setText(cpt);
				numFact=billRefNoNCDao.getBillRefNo(Integer.valueOf(cpt));
				facture_Panel_View.txtNumFact.setText(String.valueOf(numFact));
				resumeFactModel=billRefNoNCDao.getBillRefNoDetail(numFact);
				Object[] columnValues = {resumeFactModel.getFromDate(),resumeFactModel.getToDate(),
						resumeFactModel.getPrepDate(),resumeFactModel.getStatDate()};			
				facture_Panel_View.tableModel.addRow(columnValues);
				facture_Panel_View.dateEditorEchu.setText(resumeFactModel.getFromDate());
				facture_Panel_View.dateEditorEchu.setForeground(Color.black);
				facture_Panel_View.dateEditorEchoir.setText(resumeFactModel.getToDate());
				facture_Panel_View.dateEditorEchoir.setForeground(Color.black);
				billRefNoNCDao.closeConnection();
				//Remplissage du panel Recap Facture
				rapport_Panel_View.lbDB.setText("Database Connect");
				rapport_Panel_View.lbDBResult.setText("CUSTNC");
				rapport_Panel_View.lbFact.setText("Facture");
				rapport_Panel_View.lbFactResult.setText(facture_Panel_View.mb[4]);
				rapport_Panel_View.lbActNo.setText("Account_No");
				rapport_Panel_View.lbActNoResult.setText(cpt);
				rapport_Panel_View.lbBillRefNo.setText("Bill_Ref_No");
				rapport_Panel_View.lbBillRefNoResult.setText(String.valueOf(numFact));
				rapport_Panel_View.lbEquiv1.setVisible(true);
				rapport_Panel_View.lbEquiv2.setVisible(true);
				rapport_Panel_View.lbEquiv3.setVisible(true);
				rapport_Panel_View.lbEquiv4.setVisible(true);
				facture_Panel_View.cbAvoirs.setVisible(false);
				facture_Panel_View.lbAvoirs.setVisible(false);
				rapport_Panel_View.center_south.repaint();
				rapport_Panel_View.center_north.repaint();
			}
			if(e.getItem().equals(facture_Panel_View.mb[5])){
				System.out.println("DARTY");
				rapport_Panel_View.center_south.removeAll();
				rapport_Panel_View.center_north.removeAll();
				if(facture_Panel_View.tableModel.getRowCount()>0){
					facture_Panel_View.tableModel.removeRow(0);
					}
				cpt=config.getCptDarty();
				facture_Panel_View.txtNumCompte.setText(cpt);
				numFact=billRefNo3PDao.getBillRefNo(Integer.valueOf(cpt));
				facture_Panel_View.txtNumFact.setText(String.valueOf(numFact));
				resumeFactModel=billRefNo3PDao.getBillRefNoDetail(numFact);
				Object[] columnValues = {resumeFactModel.getFromDate(),resumeFactModel.getToDate(),
						resumeFactModel.getPrepDate(),resumeFactModel.getStatDate()};			
				facture_Panel_View.tableModel.addRow(columnValues);
				facture_Panel_View.dateEditorEchu.setText(resumeFactModel.getFromDate());
				facture_Panel_View.dateEditorEchu.setForeground(Color.black);
				facture_Panel_View.dateEditorEchoir.setText(resumeFactModel.getToDate());
				facture_Panel_View.dateEditorEchoir.setForeground(Color.black);
				billRefNo3PDao.closeConnection();
				//Remplissage du panel Recap Facture
				rapport_Panel_View.lbDB.setText("Database Connect");
				rapport_Panel_View.lbDBResult.setText("CUST3P");
				rapport_Panel_View.lbFact.setText("Facture");
				rapport_Panel_View.lbFactResult.setText(facture_Panel_View.mb[5]);
				rapport_Panel_View.lbActNo.setText("Account_No");
				rapport_Panel_View.lbActNoResult.setText(cpt);
				rapport_Panel_View.lbBillRefNo.setText("Bill_Ref_No");
				rapport_Panel_View.lbBillRefNoResult.setText(String.valueOf(numFact));
				rapport_Panel_View.lbEquiv1.setVisible(true);
				rapport_Panel_View.lbEquiv2.setVisible(true);
				rapport_Panel_View.lbEquiv3.setVisible(true);
				rapport_Panel_View.lbEquiv4.setVisible(true);
				facture_Panel_View.cbAvoirs.setVisible(true);
				facture_Panel_View.lbAvoirs.setVisible(true);
				rapport_Panel_View.center_south.repaint();
				rapport_Panel_View.center_north.repaint();
			}
		}
		
	}
}


