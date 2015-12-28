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
import factu_mb_param.modele.NbrDayPerMonth;
import factu_mb_param.view.Facture_Panel_View;
import factu_mb_param.view.Rapport_Panel_View;



public class GenerateAvoirFactureDRTController implements ActionListener{
	
	private Facture_Panel_View facture_Panel_View;	
	private Rapport_Panel_View rapport_Panel_View;	
	private DartyDao dartyDao;
	private BillRefNo3PDao billRefNo3PDao;
	private NbrDayPerMonth nbrDayPerMonth;
	private Config config;
	private String cpt="";
	private int numFact=0;
	private Rapport_AVOIR_DRT_Controller rapport_AVOIR_DRT_Controller;
	private FormatDate formatDate;
	private JFileChooser filechoose;
	private GetDesktopPath getDesktopPath;
	private DateConvertor dateConvertor;
	private String label="Génération avoir DARTY ";
	private JLabel generateFactDRT= new JLabel("");
	private JLabel enCoursDRT= new JLabel("en cours");
	
	public GenerateAvoirFactureDRTController(Facture_Panel_View facture_Panel_View,Rapport_Panel_View rapport_Panel_View){
		this.facture_Panel_View= facture_Panel_View;
		this.rapport_Panel_View= rapport_Panel_View;
		dartyDao= new DartyDao();
		config=new Config();
		billRefNo3PDao=new BillRefNo3PDao();
		formatDate=new FormatDate();
		getDesktopPath= new GetDesktopPath();
		dateConvertor= new DateConvertor();
		nbrDayPerMonth=new NbrDayPerMonth();
	}	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		facture_Panel_View.cbFacture.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		facture_Panel_View.dateChooserEchu.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		facture_Panel_View.dateChooserEchoir.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		rapport_Panel_View.center_north.removeAll();
		rapport_Panel_View.center_south.removeAll();
		rapport_Panel_View.center_north.repaint();
		rapport_Panel_View.center_south.repaint();
		rapport_Panel_View.center_north.add(generateFactDRT,BorderLayout.WEST);
		rapport_Panel_View.center_north.add(enCoursDRT,BorderLayout.EAST);
		generateFactDRT.setText(label);						
		enCoursDRT.setForeground(Color.red);
		generateFactDRT.setVisible(true);
		enCoursDRT.setVisible(true);
		System.out.println("rapport avoir DARTY");
		rapport_AVOIR_DRT_Controller= new Rapport_AVOIR_DRT_Controller();
		filechoose = new JFileChooser();
        filechoose.setCurrentDirectory(new File(getDesktopPath.getPath()));
        filechoose.setDialogTitle("Enregistrement de l'avoir");
        String approve = new String("Enregistrer");
        Date date= new Date();
        String moisNumerique=formatDate.dateToString(date).substring(3,5);
        int nbrJr=nbrDayPerMonth.getNbrDayForMonth(Integer.valueOf(moisNumerique)-1);
		String mois=dateConvertor.getConvertMonth(formatDate.dateToString(date));
		String annee=dateConvertor.getConvertYear(facture_Panel_View.tableModel.getValueAt(0, 0).toString());
		String fileName="Avoir_Darty_pour_facture_"+mois+"_v"+annee+moisNumerique+nbrJr+".xls";
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
            rapport_AVOIR_DRT_Controller.rapport_avoir_drt(facture_Panel_View,rapport_Panel_View,fileName,chemin);	
        }
        if(resultatEnregistrer == 1){
        	System.out.println("cancel");
        	generateFactDRT.setVisible(false);
        	enCoursDRT.setVisible(false);
        	rapport_Panel_View.center_north.removeAll();
			rapport_Panel_View.center_south.removeAll();
			rapport_Panel_View.center_north.repaint();
			rapport_Panel_View.center_south.repaint();
		}else{
			System.out.println("autre");
		}
	}								
}


