package factu_mb_param.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.FileChooserUI;

import factu_mb_param.dao.BillRefNo3PDao;
import factu_mb_param.dao.BillRefNoNCDao;
import factu_mb_param.dao.BouyguesDao;
import factu_mb_param.dao.DartyDao;
import factu_mb_param.dao.LeCableDao;
import factu_mb_param.dao.NumericableDao;
import factu_mb_param.dao.OMTDao;
import factu_mb_param.modele.Config;
import factu_mb_param.modele.DateConvertor;
import factu_mb_param.modele.FormatDate;
import factu_mb_param.modele.GetDesktopPath;
import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.view.Facture_Panel_View;
import factu_mb_param.view.Rapport_Panel_View;



public class GenerateFactureController implements ActionListener{
	
	private Facture_Panel_View facture_Panel_View;	
	private Rapport_Panel_View rapport_Panel_View;	
	private NumericableDao numericableDao;
	private BouyguesDao bouyguesDao;
	private LeCableDao leCableDao;
	private OMTDao omtDao;
	private DartyDao dartyDao;
	private BillRefNo3PDao billRefNo3PDao;
	private BillRefNoNCDao billRefNoNCDao;
	private Config config;
	private String cpt="";
	private int numFact=0;
	private MontantBillInvoiceDetailModel resumeFactModel;
	private Rapport_NC_Controller rapport_NC_Controller;
	private Rapport_OMT_Controller rapport_OMT_Controller;
	private Rapport_DRT_Controller rapport_DRT_Controller;
	private Rapport_BYTEL_Controller rapport_BYTEL_Controller;
	private Rapport_MTVC_Controller rapport_MTVC_Controller;
	private FormatDate formatDate;
	private JFileChooser filechoose;
	private GetDesktopPath getDesktopPath;
	private DateConvertor dateConvertor;
	private String label="Génération facture Marque Blanche ";
	private JLabel generateFactNC= new JLabel("");
	private JLabel generateFactMTVC= new JLabel("");
	private JLabel generateFactOMT= new JLabel("");
	private JLabel generateFactBytel= new JLabel("");
	private JLabel generateFactDRT= new JLabel("");
	private JLabel enCoursNC= new JLabel("en cours");
	private JLabel enCoursMTVC= new JLabel("en cours");
	private JLabel enCoursOMT= new JLabel("en cours");
	private JLabel enCoursBytel= new JLabel("en cours");
	private JLabel enCoursDRT= new JLabel("en cours");
	
	public GenerateFactureController(Facture_Panel_View facture_Panel_View,Rapport_Panel_View rapport_Panel_View){
		this.facture_Panel_View= facture_Panel_View;
		this.rapport_Panel_View= rapport_Panel_View;
		numericableDao= new NumericableDao();
		bouyguesDao= new BouyguesDao();
		leCableDao= new LeCableDao();
		omtDao= new OMTDao();
		dartyDao= new DartyDao();
		config=new Config();
		billRefNo3PDao=new BillRefNo3PDao();
		billRefNoNCDao=new BillRefNoNCDao();
		resumeFactModel=new MontantBillInvoiceDetailModel();
		formatDate=new FormatDate();
		getDesktopPath= new GetDesktopPath();
		dateConvertor= new DateConvertor();
	}	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		facture_Panel_View.cbFacture.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		facture_Panel_View.dateChooserEchu.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		facture_Panel_View.dateChooserEchoir.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
			// Verification si un des champs obligatoire est vide
			if(facture_Panel_View.cbFacture.getSelectedItem().equals(facture_Panel_View.mb[0]) || 
				facture_Panel_View.dateChooserEchu.getDate()==null ||
				facture_Panel_View.dateChooserEchoir.getDate()==null){
				System.out.println("VIDE");
				if(facture_Panel_View.cbFacture.getSelectedItem().equals(facture_Panel_View.mb[0])){
					facture_Panel_View.cbFacture.setBorder(BorderFactory.createLineBorder(Color.red));
				}
				if(facture_Panel_View.dateChooserEchu.getDate()==null){
					facture_Panel_View.dateChooserEchu.setBorder(BorderFactory.createLineBorder(Color.red));
				}
				if(facture_Panel_View.dateChooserEchoir.getDate()==null){
					facture_Panel_View.dateChooserEchoir.setBorder(BorderFactory.createLineBorder(Color.red));
				}
				JOptionPane.showMessageDialog(null,"Tous les champs précédés de '*'\nsont obligatoires.\nMerci de les renseigner.",
						"Attention",JOptionPane.WARNING_MESSAGE);
			
			}
			
			//Verification si tout les champs sont renseignés
			if(!facture_Panel_View.cbFacture.getSelectedItem().equals(facture_Panel_View.mb[0]) && 
				facture_Panel_View.dateChooserEchu.getDate()!=null &&
				facture_Panel_View.dateChooserEchoir.getDate()!=null){
				//Verification si les dates correspondent a ceux du detail du tableau 
				//SI != ALORS
				Date bill_date=facture_Panel_View.dateChooserEchu.getDate();
				Date next_bill_date=facture_Panel_View.dateChooserEchoir.getDate();
				String billDate="";
				String nextBillDate="";
				billDate = formatDate.dateToString(bill_date);
				nextBillDate = formatDate.dateToString(next_bill_date);				
				if(!billDate.equalsIgnoreCase(facture_Panel_View.tableModel.getValueAt(0, 0).toString())||
						!nextBillDate.equalsIgnoreCase(facture_Panel_View.tableModel.getValueAt(0, 1).toString())){
					
					if(!billDate.equalsIgnoreCase(facture_Panel_View.tableModel.getValueAt(0, 0).toString())){
					facture_Panel_View.dateChooserEchu.setBorder(BorderFactory.createLineBorder(Color.red));						
					JOptionPane.showMessageDialog(null,"La 'Bill Date' ne correspond pas\n à la celui du tableau 'From Date' ",
							"Attention",JOptionPane.WARNING_MESSAGE);					
					}
					
					if(!nextBillDate.equalsIgnoreCase(facture_Panel_View.tableModel.getValueAt(0, 1).toString())){
					facture_Panel_View.dateChooserEchoir.setBorder(BorderFactory.createLineBorder(Color.red));
					JOptionPane.showMessageDialog(null,"La 'Next Bill Date' ne correspond pas\n à la celui du tableau 'To Date' ",
							"Attention",JOptionPane.WARNING_MESSAGE);					
					}
				}
				//SINON
				else{
					
					//FACTURE NUMERICABLE
					if(facture_Panel_View.cbFacture.getSelectedItem().equals(facture_Panel_View.mb[1])){														
						rapport_Panel_View.center_north.removeAll();
						rapport_Panel_View.center_south.removeAll();
						rapport_Panel_View.center_north.repaint();
						rapport_Panel_View.center_south.repaint();
						rapport_Panel_View.center_north.add(generateFactNC,BorderLayout.WEST);
						rapport_Panel_View.center_north.add(enCoursNC,BorderLayout.EAST);
						generateFactNC.setText(label+"'Numéricable'");						
						enCoursNC.setForeground(Color.red);
						generateFactNC.setVisible(true);
						enCoursNC.setVisible(true);
						System.out.println("rapport NUMERICABLE");
						rapport_NC_Controller= new Rapport_NC_Controller();
						filechoose = new JFileChooser();
				        filechoose.setCurrentDirectory(new File(getDesktopPath.getPath()));
				        filechoose.setDialogTitle("Enregistrement de la facture");
				        String approve = new String("Enregistrer");
				        String numFacture=facture_Panel_View.txtNumFact.getText();
						String mois=dateConvertor.getConvertMonth(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
						String annee=dateConvertor.getConvertYear(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
						String fileName="NUMERICABLE_Facture_"+numFacture+"_("+mois+" "+annee+").xls";
				        //method permettant de setter par default le nom du fichier de JFileChooser
				        FileChooserUI fcUi = filechoose.getUI();
				    	Class<? extends FileChooserUI> fcClass = fcUi.getClass();
				    	Method setFileName=null;
						try {
							setFileName = fcClass.getMethod("setFileName", String.class);
						} catch (NoSuchMethodException | SecurityException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						}
				    	Object defaultDirectoryName=fileName;
				    	try {
							setFileName.invoke(fcUi, defaultDirectoryName);
						} catch (IllegalAccessException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						} catch (IllegalArgumentException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						} catch (InvocationTargetException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						}
				        int resultatEnregistrer = filechoose.showDialog(filechoose, approve);
				        if(resultatEnregistrer == 0){
				        	String chemin = filechoose.getCurrentDirectory().toString();
				            chemin=chemin.replace("\\", "/");
				            System.out.println("CHEMIN = "+chemin);
				        	File file = filechoose.getSelectedFile();
				            fileName = file.getName();
						rapport_NC_Controller.rapport_nc(facture_Panel_View,rapport_Panel_View,fileName,chemin,mois,annee);	
				        }
				        if(resultatEnregistrer == 1){
				        	System.out.println("cancel");
				        	generateFactNC.setVisible(false);
				        	enCoursNC.setVisible(false);
				        	rapport_Panel_View.center_north.removeAll();
							rapport_Panel_View.center_south.removeAll();
							rapport_Panel_View.center_north.repaint();
							rapport_Panel_View.center_south.repaint();
							}
					}
					
					
					//FACTURE BOUYGUES
					if(facture_Panel_View.cbFacture.getSelectedItem().equals(facture_Panel_View.mb[2])){														
						rapport_Panel_View.center_north.removeAll();
						rapport_Panel_View.center_south.removeAll();
						rapport_Panel_View.center_north.repaint();
						rapport_Panel_View.center_south.repaint();
						rapport_Panel_View.center_north.add(generateFactBytel,BorderLayout.WEST);
						rapport_Panel_View.center_north.add(enCoursNC,BorderLayout.EAST);
						generateFactBytel.setText(label+"'Bouygues'");						
						enCoursBytel.setForeground(Color.red);
						generateFactBytel.setVisible(true);
						enCoursBytel.setVisible(true);
						System.out.println("rapport BOUYGUES");
						rapport_BYTEL_Controller= new Rapport_BYTEL_Controller();
						filechoose = new JFileChooser();
				        filechoose.setCurrentDirectory(new File(getDesktopPath.getPath()));
				        filechoose.setDialogTitle("Enregistrement de la facture");
				        String approve = new String("Enregistrer");
				        String numFacture=facture_Panel_View.txtNumFact.getText();
						String mois=dateConvertor.getConvertMonth(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
						String annee=dateConvertor.getConvertYear(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
						String fileName="BYTEL_Facture_"+numFacture+"_("+mois+" "+annee+").xls";
				        //method permettant de setter par default le nom du fichier de JFileChooser
				        FileChooserUI fcUi = filechoose.getUI();
				    	Class<? extends FileChooserUI> fcClass = fcUi.getClass();
				    	Method setFileName=null;
						try {
							setFileName = fcClass.getMethod("setFileName", String.class);
						} catch (NoSuchMethodException | SecurityException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						}
				    	Object defaultDirectoryName=fileName;
				    	try {
							setFileName.invoke(fcUi, defaultDirectoryName);
						} catch (IllegalAccessException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						} catch (IllegalArgumentException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						} catch (InvocationTargetException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						}
				        int resultatEnregistrer = filechoose.showDialog(filechoose, approve);
				        if(resultatEnregistrer == 0){
				        	String chemin = filechoose.getCurrentDirectory().toString();
				            chemin=chemin.replace("\\", "/");
				            System.out.println("CHEMIN = "+chemin);
				        	File file = filechoose.getSelectedFile();
				            fileName = file.getName();
				            rapport_BYTEL_Controller.rapport_bytel(facture_Panel_View,rapport_Panel_View,fileName,chemin,mois,annee);	
				        }
				        if(resultatEnregistrer == 1){
				        	System.out.println("cancel");
				        	generateFactBytel.setVisible(false);
				        	enCoursBytel.setVisible(false);
				        	rapport_Panel_View.center_north.removeAll();
							rapport_Panel_View.center_south.removeAll();
							rapport_Panel_View.center_north.repaint();
							rapport_Panel_View.center_south.repaint();
							}
					}
					
					//FACTURE LE CABLE
					if(facture_Panel_View.cbFacture.getSelectedItem().equals(facture_Panel_View.mb[3])){						
						rapport_Panel_View.center_north.removeAll();
						rapport_Panel_View.center_south.removeAll();
						rapport_Panel_View.center_north.repaint();
						rapport_Panel_View.center_south.repaint();
						rapport_Panel_View.center_north.add(generateFactMTVC,BorderLayout.WEST);
						rapport_Panel_View.center_north.add(enCoursMTVC,BorderLayout.EAST);
						generateFactMTVC.setText(label+"'Le Câble'");	
						enCoursMTVC.setForeground(Color.red);
						generateFactMTVC.setVisible(true);
						enCoursMTVC.setVisible(true);
						System.out.println("rapport MTVC Le Cable");
						rapport_MTVC_Controller= new Rapport_MTVC_Controller();
						filechoose = new JFileChooser();
				        filechoose.setCurrentDirectory(new File(getDesktopPath.getPath()));
				        filechoose.setDialogTitle("Enregistrement de la facture");
				        String approve = new String("Enregistrer");
				        String numFacture=facture_Panel_View.txtNumFact.getText();
						String mois=dateConvertor.getConvertMonth(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
						String annee=dateConvertor.getConvertYear(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
						String fileName="MTVC_Facture_"+numFacture+"_("+mois+" "+annee+").xls";
				        //method permettant de setter par default le nom du fichier de JFileChooser
				        FileChooserUI fcUi = filechoose.getUI();
				    	Class<? extends FileChooserUI> fcClass = fcUi.getClass();
				    	Method setFileName=null;
						try {
							setFileName = fcClass.getMethod("setFileName", String.class);
						} catch (NoSuchMethodException | SecurityException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						}
				    	Object defaultDirectoryName=fileName;
				    	try {
							setFileName.invoke(fcUi, defaultDirectoryName);
						} catch (IllegalAccessException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						} catch (IllegalArgumentException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						} catch (InvocationTargetException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						}
				        int resultatEnregistrer = filechoose.showDialog(filechoose, approve);
				        if(resultatEnregistrer == 0){
				        	String chemin = filechoose.getCurrentDirectory().toString();
				            chemin=chemin.replace("\\", "/");
				            System.out.println("CHEMIN = "+chemin);
				        	File file = filechoose.getSelectedFile();
				            fileName = file.getName();
				            rapport_MTVC_Controller.rapport_mtvc(facture_Panel_View,rapport_Panel_View,fileName,chemin,mois,annee);	
				        }
				        if(resultatEnregistrer == 1){
				        	System.out.println("cancel");
				        	generateFactMTVC.setVisible(false);
				        	enCoursMTVC.setVisible(false);
				        	rapport_Panel_View.center_north.removeAll();
							rapport_Panel_View.center_south.removeAll();
							rapport_Panel_View.center_north.repaint();
							rapport_Panel_View.center_south.repaint();
							}
						}
					}
				
					//FACTURE OMT
					if(facture_Panel_View.cbFacture.getSelectedItem().equals(facture_Panel_View.mb[4])){												
						rapport_Panel_View.center_north.removeAll();
						rapport_Panel_View.center_south.removeAll();
						rapport_Panel_View.center_north.repaint();
						rapport_Panel_View.center_south.repaint();
						rapport_Panel_View.center_north.add(generateFactOMT,BorderLayout.WEST);
						rapport_Panel_View.center_north.add(enCoursOMT,BorderLayout.EAST);
						generateFactOMT.setText(label+"'OMT'");						
						enCoursOMT.setForeground(Color.red);
						generateFactOMT.setVisible(true);
						enCoursOMT.setVisible(true);
						System.out.println("rapport OMT");
						rapport_OMT_Controller= new Rapport_OMT_Controller();
						filechoose = new JFileChooser();
				        filechoose.setCurrentDirectory(new File(getDesktopPath.getPath()));
				        filechoose.setDialogTitle("Enregistrement de la facture");
				        String approve = new String("Enregistrer");
				        String numFacture=facture_Panel_View.txtNumFact.getText();
						String mois=dateConvertor.getConvertMonth(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
						String annee=dateConvertor.getConvertYear(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
						String fileName="OMT_Facture_"+numFacture+"_("+mois+" "+annee+").xls";
				        //method permettant de setter par default le nom du fichier de JFileChooser
				        FileChooserUI fcUi = filechoose.getUI();
				    	Class<? extends FileChooserUI> fcClass = fcUi.getClass();
				    	Method setFileName=null;
						try {
							setFileName = fcClass.getMethod("setFileName", String.class);
						} catch (NoSuchMethodException | SecurityException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						}
				    	Object defaultDirectoryName=fileName;
				    	try {
							setFileName.invoke(fcUi, defaultDirectoryName);
						} catch (IllegalAccessException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						} catch (IllegalArgumentException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						} catch (InvocationTargetException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						}
				        int resultatEnregistrer = filechoose.showDialog(filechoose, approve);
				        if(resultatEnregistrer == 0){
				        	String chemin = filechoose.getCurrentDirectory().toString();
				            chemin=chemin.replace("\\", "/");
				            System.out.println("CHEMIN = "+chemin);
				        	File file = filechoose.getSelectedFile();
				            fileName = file.getName();
				            rapport_OMT_Controller.rapport_omt(facture_Panel_View,rapport_Panel_View,fileName,chemin,mois,annee);	
				        }
				        if(resultatEnregistrer == 1){
				        	System.out.println("cancel");
				        	generateFactOMT.setVisible(false);
				        	enCoursOMT.setVisible(false);
				        	rapport_Panel_View.center_north.removeAll();
							rapport_Panel_View.center_south.removeAll();
							rapport_Panel_View.center_north.repaint();
							rapport_Panel_View.center_south.repaint();
							}
				        }
					
					//FACTURE DARTY
					if(facture_Panel_View.cbFacture.getSelectedItem().equals(facture_Panel_View.mb[5])){												
						rapport_Panel_View.center_north.removeAll();
						rapport_Panel_View.center_south.removeAll();
						rapport_Panel_View.center_north.repaint();
						rapport_Panel_View.center_south.repaint();
						rapport_Panel_View.center_north.add(generateFactDRT,BorderLayout.WEST);
						rapport_Panel_View.center_north.add(enCoursDRT,BorderLayout.EAST);
						generateFactDRT.setText(label+"'DARTY'");						
						enCoursDRT.setForeground(Color.red);
						generateFactDRT.setVisible(true);
						enCoursDRT.setVisible(true);
						System.out.println("rapport DARTY");
						rapport_DRT_Controller= new Rapport_DRT_Controller();
						filechoose = new JFileChooser();
				        filechoose.setCurrentDirectory(new File(getDesktopPath.getPath()));
				        filechoose.setDialogTitle("Enregistrement de la facture");
				        String approve = new String("Enregistrer");
				        String numFacture=facture_Panel_View.txtNumFact.getText();
						String mois=dateConvertor.getConvertMonth(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
						String annee=dateConvertor.getConvertYear(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
						String fileName="DARTY_Facture_"+numFacture+"_("+mois+" "+annee+").xls";
				        //method permettant de setter par default le nom du fichier de JFileChooser
				        FileChooserUI fcUi = filechoose.getUI();
				    	Class<? extends FileChooserUI> fcClass = fcUi.getClass();
				    	Method setFileName=null;
						try {
							setFileName = fcClass.getMethod("setFileName", String.class);
						} catch (NoSuchMethodException | SecurityException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						}
				    	Object defaultDirectoryName=fileName;
				    	try {
							setFileName.invoke(fcUi, defaultDirectoryName);
						} catch (IllegalAccessException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						} catch (IllegalArgumentException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						} catch (InvocationTargetException es) {
							// TODO Auto-generated catch block
							es.printStackTrace();
						}
				        int resultatEnregistrer = filechoose.showDialog(filechoose, approve);
				        if(resultatEnregistrer == 0){
				        	String chemin = filechoose.getCurrentDirectory().toString();
				            chemin=chemin.replace("\\", "/");
				            System.out.println("CHEMIN = "+chemin);
				        	File file = filechoose.getSelectedFile();
				            fileName = file.getName();
				            rapport_DRT_Controller.rapport_drt(facture_Panel_View,rapport_Panel_View,fileName,chemin,mois,annee);	
				        }
				        if(resultatEnregistrer == 1){
				        	System.out.println("cancel");
				        	generateFactDRT.setVisible(false);
				        	enCoursDRT.setVisible(false);
				        	rapport_Panel_View.center_north.removeAll();
							rapport_Panel_View.center_south.removeAll();
							rapport_Panel_View.center_north.repaint();
							rapport_Panel_View.center_south.repaint();
							}
				        }
					else{
						System.out.println("autre");
					}
					
				}
			}			
}


