package factu_mb_param.GenerateFact;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import factu_mb_param.modele.ChargesReccurentesModel;
import factu_mb_param.modele.FormatDate;
import factu_mb_param.modele.GetDesktopPath;
import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.modele.OngletNrcModel;
import factu_mb_param.modele.OngletUsageModelMTVC;
import factu_mb_param.modele.TempInfosFactuModel;


public class GenerationFactureMTVC{

    private String openFile;
    private FormatDate formatDate;
    private boolean isOpen ;
    String month;
    private String year;
    private String fileName;
    private int taille=0;
    private String factReel="";
    private ChargesReccurentesModel chargesReccurentesModel;
    private Font font=null;
    private GetDesktopPath getDesktopPath;
    private String path="";
    private String newChemin="";
    private String repertoire="";
    private String cheminR="";
    
    public GenerationFactureMTVC(){
    	getDesktopPath=new GetDesktopPath();
    	path=getDesktopPath.getApplicationPath()+"LE CABLE";
    }

    
    
    public void fillLC_INFOS_FACTU(ArrayList<TempInfosFactuModel> listInfosFactuModel,String month,String year,String fileName,String chemin) throws IOException{
    	this.month=month;
    	this.year=year;     	
    	FileInputStream fileIn= new FileInputStream(new File(path+"/MTVC_Rapport_Facturation_réelle.xls"));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("LC_INFOS_FACTU"); 
    	CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);
		factReel="MTVC_Rapport_Facturation_réelle_"+year+month+".xls";
		
		HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb); 
		
        int i=1;
        for(TempInfosFactuModel infosFactuModel:listInfosFactuModel){
        	formatDate=new FormatDate();
        	HSSFRow row = sheet.getRow(i);
        	HSSFCell date = row.createCell(0);
        	date.setCellValue(formatDate.getFormatDateDetailWithoutSecond(infosFactuModel.getDate()));
        	date.setCellStyle(style);
        	HSSFCell nbRunId = row.createCell(1);
        	nbRunId.setCellStyle(style);
        	nbRunId.setCellValue(infosFactuModel.getRunId());
            HSSFCell labelMois = row.createCell(2);
            labelMois.setCellStyle(style);
            labelMois.setCellValue(infosFactuModel.getMois());
            HSSFCell nbNbrJrMois = row.createCell(3);
            nbNbrJrMois.setCellStyle(style);
            nbNbrJrMois.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNbrJrMois.setCellValue(infosFactuModel.getNbrJrMois());
            HSSFCell nbRefFacture = row.createCell(4);
            nbRefFacture.setCellStyle(style);
            nbRefFacture.setCellValue(infosFactuModel.getRefFacture());
            HSSFCell nbElmtIdArrears = row.createCell(5);
            nbElmtIdArrears.setCellStyle(style);
            nbElmtIdArrears.setCellValue(infosFactuModel.getElmtIdArrears());
            HSSFCell nbElmtIdAvance = row.createCell(6);
            nbElmtIdAvance.setCellStyle(style);
            nbElmtIdAvance.setCellValue(infosFactuModel.getElmtIdAvance());
            HSSFCell labelDescription = row.createCell(7);
            labelDescription.setCellStyle(style);
            labelDescription.setCellValue(infosFactuModel.getDescription());
            HSSFCell nbNf = row.createCell(8);
            nbNf.setCellStyle(style);
            nbNf.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNf.setCellValue(infosFactuModel.getNf());
            HSSFCell nbNa = row.createCell(9);
            nbNa.setCellStyle(style);
            nbNa.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNa.setCellValue(infosFactuModel.getNa());
            HSSFCell nbNd1 = row.createCell(10);
            nbNd1.setCellStyle(style);
            nbNd1.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNd1.setCellValue(infosFactuModel.getNd1());
            HSSFCell nbNd2 = row.createCell(11);
            nbNd2.setCellStyle(style);
            nbNd2.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNd2.setCellValue(infosFactuModel.getNd2());
            HSSFCell nbNp1 = row.createCell(12);
            nbNp1.setCellStyle(style);
            nbNp1.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNp1.setCellValue(infosFactuModel.getNp1());
            HSSFCell nbNp2 = row.createCell(13);
            nbNp2.setCellStyle(style);
            nbNp2.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNp2.setCellValue(infosFactuModel.getNp2());
            HSSFCell nbNbrJrsEchu = row.createCell(14);
            nbNbrJrsEchu.setCellStyle(style);
            nbNbrJrsEchu.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNbrJrsEchu.setCellValue(infosFactuModel.getNbrJrsEchu());
            HSSFCell nbAboMoisAvance = row.createCell(15);
            nbAboMoisAvance.setCellStyle(style);
            nbAboMoisAvance.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbAboMoisAvance.setCellValue(infosFactuModel.getAboMoisAvance());
            HSSFCell nbRate = row.createCell(16);
            nbRate.setCellStyle(style);
            nbRate.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbRate.setCellValue(infosFactuModel.getRate());           
            i+=1;
        }          
        
        formulaEvaluator.evaluateAllFormulaCells(wb);
        
    
	FileOutputStream fileOut;
	fileOut = new FileOutputStream(chemin+"/"+factReel);
	newChemin=chemin+"/"+factReel;
	repertoire=chemin;
	cheminR=chemin;
	wb.write(fileOut);
	fileOut.close(); 
	System.out.println("Fin remplissage MTVC_INFO_FACTU");
    }
    
    public void fillBill_Invoice_Detail(ArrayList<MontantBillInvoiceDetailModel> listMontantBillInvoiceDetailEchu,
    		ArrayList<MontantBillInvoiceDetailModel> listMontantBillInvoiceDetailEchoir,
    		String fromDate,String toDate,String nextFromDate,String nextToDate) throws IOException{
    	
    	FileInputStream fileIn= new FileInputStream(new File(newChemin));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("LC_INFOS_FACTU"); 
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);    	
    	CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);
            int i=8;
            for(MontantBillInvoiceDetailModel montantBIDModel:listMontantBillInvoiceDetailEchu){
            	Double valElmtId=Double.valueOf(montantBIDModel.getElementId());
            	Double valMontant=Double.valueOf(montantBIDModel.getMontant());
            	HSSFRow row = sheet.getRow(i);
            	HSSFCell labelEchu = row.createCell(4);
            	labelEchu.setCellStyle(style);
            	labelEchu.setCellValue(montantBIDModel.getEchu());
            	HSSFCell nbElementId = row.createCell(5);
            	nbElementId.setCellStyle(style);
            	nbElementId.setCellValue(valElmtId);
            	HSSFCell nbMontant = row.createCell(6);
            	nbMontant.setCellStyle(style);
            	nbMontant.setCellValue(valMontant);
                i+=1;
            }
            
            int h=13;
            for(MontantBillInvoiceDetailModel montantBIDModel:listMontantBillInvoiceDetailEchoir){
            	Double valElmtId=Double.valueOf(montantBIDModel.getElementId());
            	Double valMontant=Double.valueOf(montantBIDModel.getMontant());
            	HSSFRow row = sheet.getRow(h);
            	HSSFCell labelEchu = row.createCell(4);
            	labelEchu.setCellStyle(style);
            	labelEchu.setCellValue(montantBIDModel.getEchu());
            	HSSFCell nbElementId = row.createCell(5);
            	nbElementId.setCellStyle(style);
            	nbElementId.setCellValue(valElmtId);
            	HSSFCell nbMontant = row.createCell(6);
            	nbMontant.setCellStyle(style);
            	nbMontant.setCellValue(valMontant);
                h+=1;
            }
            
            //Tableau Echeances
            HSSFRow rowEchu = sheet.getRow(21);
            HSSFCell dateEchu = rowEchu.createCell(5);
            dateEchu.setCellStyle(style);
            dateEchu.setCellValue("Du "+fromDate+" au "+toDate);
            HSSFRow rowEchoir = sheet.getRow(22);
            HSSFCell dateEchoir = rowEchoir.createCell(5);
            dateEchoir.setCellStyle(style);
            dateEchoir.setCellValue("Du "+nextFromDate+" au "+nextToDate);

            
            formulaEvaluator.evaluateAllFormulaCells(wb);
            
            FileOutputStream fileOut;
        	fileOut = new FileOutputStream(newChemin);
        	wb.write(fileOut);
        	fileOut.close(); 
        	  System.out.println("Fin remplissage BILL_INVOICE_FACT");       	
    }        
    	    	
	public void ongletInputs(int nbrJour,String numFact,String mois,String annee,String fileName) throws IOException{
		this.fileName=fileName;
			
		FileInputStream fileIn= new FileInputStream(new File(path+"/MTVC_Facture.xls"));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("Inputs");     	
    	CellStyle style=wb.createCellStyle();
    	CellStyle style2=wb.createCellStyle();
    	//Style 1
    	style.setBorderBottom(CellStyle.BORDER_THICK);
    	style.setBorderLeft(CellStyle.BORDER_THICK);
    	style.setBorderRight(CellStyle.BORDER_THICK);
    	style.setBorderTop(CellStyle.BORDER_THICK);
    	style.setAlignment(CellStyle.ALIGN_CENTER);
    	style.setVerticalAlignment(CellStyle.ALIGN_FILL);
    	style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    	
    	//Style 2
    	style2.setAlignment(CellStyle.ALIGN_LEFT);
    	style2.setFillBackgroundColor(HSSFColor.RED.index);
    	
		//remplissage des cellules.
	    String titre="FACTURATION MTVC – DU 1er AU ";
	    String descr1="Nous avons pris en compte les NRC's facturées sur la facture ";
	    String descr2="Nous avons pris les tickets facturés sur la facture ";
	    
	    HSSFRow rowTitle = sheet.getRow(0);    
	    HSSFCell cellStylefontGris = rowTitle.getCell(0);
        HSSFCellStyle stylecellGris= cellStylefontGris.getCellStyle();
        stylecellGris.setBorderBottom(CellStyle.BORDER_THICK);
        stylecellGris.setBorderLeft(CellStyle.BORDER_THICK);
        stylecellGris.setBorderRight(CellStyle.BORDER_THICK);
        stylecellGris.setBorderTop(CellStyle.BORDER_THICK);
        stylecellGris.setAlignment(CellStyle.ALIGN_CENTER);
        stylecellGris.setVerticalAlignment(CellStyle.ALIGN_FILL);
	    HSSFCell title = rowTitle.createCell(0);
	    title.setCellValue(titre+nbrJour+" "+mois.toUpperCase()+" "+annee);
	    title.setCellStyle(stylecellGris);
	    
	    HSSFCell contour1 = rowTitle.createCell(1);
	    contour1.setCellStyle(style);
	    HSSFCell contour2 = rowTitle.createCell(2);
	    contour2.setCellStyle(style);
	    HSSFCell contour3 = rowTitle.createCell(3);
	    contour3.setCellStyle(style);
	    
	    HSSFRow rowNbJr = sheet.getRow(2);
        HSSFCell cellJour = rowNbJr.createCell(1);
        cellJour.setCellStyle(style2);
        cellJour.setCellValue(nbrJour);
        
        
        HSSFRow rowDescr1 = sheet.getRow(24);
        HSSFCell labDescr1= rowDescr1.createCell(0);        
        labDescr1.setCellValue(descr1+numFact+" ("+mois+" "+annee+")");  
        HSSFRow rowDescr2 = sheet.getRow(28);
        HSSFCell labDescr2 = rowDescr2.createCell(0);
        labDescr2.setCellValue(descr2+numFact);
        
        FileOutputStream fileOut;
        repertoire=repertoire+"/"+fileName;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close();  
        System.out.println("FIN remplissage onglet INPUTS");
            
    }
	

	public ArrayList<ChargesReccurentesModel> listChargesReccurentes() throws IOException{
		ArrayList<ChargesReccurentesModel> allListChargesReccurentes = new ArrayList<ChargesReccurentesModel>();
		FileInputStream fileIn= new FileInputStream(new File(newChemin));
		HSSFWorkbook wb = new HSSFWorkbook(fileIn);  
		HSSFSheet sheet = wb.getSheet("LC_INFOS_FACTU"); 
		
    		//recuparations des cellules.
		    for(int i=21;i<23;i++){
		    	HSSFRow row = sheet.getRow(i);
		    	HSSFCell labDate= row.getCell(5); 
	    		String date=labDate.getStringCellValue();
	    		System.out.println("date : "+date);
	    		HSSFCell cellNjt=row.getCell(6);
	    		int nbrJourTotal=(int) cellNjt.getNumericCellValue();
	    		System.out.println("nombre de jour total : "+nbrJourTotal);
	    		HSSFCell cellLbJour=row.getCell(7);
	    		String labelJour=cellLbJour.toString();
	    		System.out.println("label jour : "+labelJour);
	    		HSSFCell cellPrixAbo=row.getCell(8);
	    		double prixAbo=cellPrixAbo.getNumericCellValue();
	    		System.out.println("prix abonnements : "+prixAbo);
	    		HSSFCell cellMontant=row.getCell(9);
	    		double montant=cellMontant.getNumericCellValue();
	    		HSSFCell cellId=row.getCell(10);
	    		int id=(int) cellId.getNumericCellValue();
	    		System.out.println("id : "+id);
	    		chargesReccurentesModel=new ChargesReccurentesModel(date, nbrJourTotal, labelJour, prixAbo, montant, id);		    		
	    		allListChargesReccurentes.add(chargesReccurentesModel);	    		
		    }	
		    fileIn.close();
            System.out.println("FIN recuperations des charges recurrentes");
            
    	return allListChargesReccurentes;
    }
	
	public void ongletAbonnements(ArrayList<ChargesReccurentesModel> listChargesReccurentes) throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  		  		    	
		HSSFWorkbook wb = new HSSFWorkbook(fileIn);  
		HSSFSheet sheet = wb.getSheet("Abonnements"); 
		CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);
		
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);
	    //remplissage des cellules.
    	DecimalFormat df = new DecimalFormat("0.##");
    	HSSFRow rowStyle = sheet.getRow(2);
    	HSSFCell cellStylefNombre1 = rowStyle.getCell(4);
    	HSSFCell cellStylefNombre2 = rowStyle.getCell(6);
        HSSFCellStyle stylecellNumber1= cellStylefNombre1.getCellStyle();
        HSSFCellStyle stylecellNumber2= cellStylefNombre2.getCellStyle();
    	int i=listChargesReccurentes.size();
		    for(ChargesReccurentesModel crm:listChargesReccurentes){
		    		String amount="";
		    		HSSFRow row = sheet.getRow(i);
		    		HSSFCell lbDate = row.createCell(3);
		    		lbDate.setCellStyle(style);
		    		lbDate.setCellValue(crm.getDate());
		            HSSFCell nbJour = row.createCell(4);
		            nbJour.setCellStyle(stylecellNumber1);
		            nbJour.setCellValue(crm.getNbrJour());
		            HSSFCell lbJour = row.createCell(5);
		            lbJour.setCellStyle(style);
		            lbJour.setCellValue(crm.getLabelJour());
		            HSSFCell nbPrixAbo= row.createCell(6);
		            nbPrixAbo.setCellStyle(stylecellNumber2);
		            nbPrixAbo.setCellValue(crm.getPrixAbo());  
		            HSSFCell nbMontat = row.createCell(7);
		            nbMontat.setCellStyle(style);	
		            nbMontat.setCellType(Cell.CELL_TYPE_NUMERIC);
		            amount=df.format(crm.getMontant()).replaceAll(",", "\\.");
		            nbMontat.setCellValue(new Double(amount));
		            HSSFCell lbId = row.createCell(8);
		            lbId.setCellStyle(style);
		            lbId.setCellValue(crm.getId());
		            i+=1;
	    }
		    
		formulaEvaluator.evaluateAllFormulaCells(wb);
		
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	    
        System.out.println("FIN modification onglet Abonnements");
        
    }
	
	public void ongletNRC(ArrayList<OngletNrcModel> listNrcMTVC) throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  		  		    	
		HSSFWorkbook wb = new HSSFWorkbook(fileIn);  
		HSSFSheet sheet = wb.getSheet("NRC"); 
		CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);
    	
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);
    	
	    //remplissage des cellules.	
	    int i=10;
	    for(OngletNrcModel ongletNrcModel:listNrcMTVC){
	    	formatDate=new FormatDate();
	    	HSSFRow row = sheet.getRow(i);
	    		HSSFCell lbTracking = row.createCell(1);
	    		lbTracking.setCellStyle(style);
	    		lbTracking.setCellValue(ongletNrcModel.getTrackingId());
	            HSSFCell nbXID = row.createCell(2);
	            nbXID.setCellStyle(style);
	            nbXID.setCellValue(ongletNrcModel.getxID());
	            HSSFCell lbIdContrat = row.createCell(3);
	            lbIdContrat.setCellStyle(style);
	            lbIdContrat.setCellValue(ongletNrcModel.getiDContrat());
	            HSSFCell lbOffre= row.createCell(4);
	            lbOffre.setCellStyle(style);
	            lbOffre.setCellValue(ongletNrcModel.getOffre());  
	            HSSFCell lbDtActiveServ = row.createCell(5);
	            lbDtActiveServ.setCellStyle(style);
	            lbDtActiveServ.setCellValue(formatDate.getFormatDate(ongletNrcModel.getDateActivationService()));
	            HSSFCell lbTypeNRC = row.createCell(6);
	            lbTypeNRC.setCellStyle(style);
	            lbTypeNRC.setCellValue(ongletNrcModel.getTypeIdNrc());
	            HSSFCell lbFact = row.createCell(7);
	            lbFact.setCellStyle(style);
	            lbFact.setCellValue(ongletNrcModel.getLibelleFacturation());
	            HSSFCell lbEffectDt= row.createCell(8);
	            lbEffectDt.setCellStyle(style);
	            lbEffectDt.setCellValue(formatDate.getFormatDate(ongletNrcModel.getEffectiveDate()));  
	            HSSFCell lbMontat = row.createCell(9);
	            lbMontat.setCellStyle(style);
	            lbMontat.setCellValue(ongletNrcModel.getMontant());			            
	    	i+=1;	
	    }
	    
	    formulaEvaluator.evaluateAllFormulaCells(wb);
	    
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	
        System.out.println("FIN modification onglet FAS");
	}
	
	public void ongletUsage(ArrayList<OngletUsageModelMTVC> listUsageNumericable) throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  		  		    	
		HSSFWorkbook wb = new HSSFWorkbook(fileIn);  
		HSSFSheet sheet = wb.getSheet("Usage"); 
		CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);
    	
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);
	    //remplissage des cellules.		
	    int i=10;
	    for(OngletUsageModelMTVC num:listUsageNumericable){
	    	HSSFRow row = sheet.getRow(i);
    		HSSFCell lbTypeTrafic = row.createCell(1);
    		lbTypeTrafic.setCellStyle(style);
    		lbTypeTrafic.setCellValue(num.getTypeTrafic());
    		HSSFCell lbClassJuris = row.createCell(2);
    		lbClassJuris.setCellStyle(style);
    		lbClassJuris.setCellValue(num.getClasseJuris());
            HSSFCell lbCodeJuris = row.createCell(3);
            lbCodeJuris.setCellStyle(style);
            lbCodeJuris.setCellValue(num.getCodeJuris());
            HSSFCell lbJuris= row.createCell(4);
            lbJuris.setCellStyle(style);
            lbJuris.setCellValue(num.getLibJuris());  
            HSSFCell nbEurosHT = row.createCell(5);
            nbEurosHT.setCellStyle(style);
            nbEurosHT.setCellValue(num.getEuroHT());
            HSSFCell nbRemise = row.createCell(6);
            nbRemise.setCellStyle(style);
            nbRemise.setCellValue(num.getRemise());
            HSSFCell nbHtRemise = row.createCell(7);
            nbHtRemise.setCellStyle(style);
            nbHtRemise.setCellValue(num.gethTRemise());
            HSSFCell nbAppels= row.createCell(8);
            nbAppels.setCellStyle(style);
            nbAppels.setCellValue(num.getNbrAppels());  
            HSSFCell nbDureeAppelFactSecUt = row.createCell(9);
            nbDureeAppelFactSecUt.setCellStyle(style);
            nbDureeAppelFactSecUt.setCellValue(num.getDureeSecOuUt());
            HSSFCell nbDureeAppelFactSec = row.createCell(10);
            nbDureeAppelFactSec.setCellStyle(style);
            nbDureeAppelFactSec.setCellValue(num.getDureeSec());
	    	i+=1;	
	    }
	    
	    formulaEvaluator.evaluateAllFormulaCells(wb);
		
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	
        System.out.println("FIN modification onglet Usage");
	}
	
	
	public void ongletRecap() throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  		  		    	
		HSSFWorkbook wb = new HSSFWorkbook(fileIn);  
		HSSFSheet sheet = wb.getSheet("Récapitulatif"); 
		   	
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);
	    //remplissage des cellules.		
    		//ROW 1
//	    	HSSFRow row1 = sheet.getRow(1);
//	    	formulaEvaluator.evaluateFormulaCell(row1.getCell(2));	
//	    	formulaEvaluator.evaluateFormulaCell(row1.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row1.getCell(4));
//	    	
//	    	//ROW 2
//	    	HSSFRow row2 = sheet.getRow(2);
//	    	formulaEvaluator.evaluateFormulaCell(row2.getCell(2));	
//	    	formulaEvaluator.evaluateFormulaCell(row2.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row2.getCell(4));
//	    	
//	    	//ROW 3
//	    	HSSFRow row3 = sheet.getRow(3);
//	    	formulaEvaluator.evaluateFormulaCell(row3.getCell(2));	
//	    	formulaEvaluator.evaluateFormulaCell(row3.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row3.getCell(4));
//	    	
//	    	//ROW 5
//	    	HSSFRow row5 = sheet.getRow(5);
//	    	formulaEvaluator.evaluateFormulaCell(row5.getCell(2));	
//	    	formulaEvaluator.evaluateFormulaCell(row5.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row5.getCell(4));
//	    	
//	    	//ROW 6
//	    	HSSFRow row6 = sheet.getRow(6);	
//	    	formulaEvaluator.evaluateFormulaCell(row6.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row6.getCell(4));
//	    	
//	    	//ROW 8
//	    	HSSFRow row8 = sheet.getRow(8);	
//	    	formulaEvaluator.evaluateFormulaCell(row8.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row8.getCell(4));
    	
    	formulaEvaluator.evaluateAllFormulaCells(wb);
	    		
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	
        System.out.println("FIN modification onglet Usage");
	}
	
	public void ongletRecapFacture(String numFact,String date) throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  		  		    	
		HSSFWorkbook wb = new HSSFWorkbook(fileIn);  
		HSSFSheet sheet = wb.getSheet("Récap facture"); 
		CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 10);
    	style.setFont(font);
    	CellStyle dateStyle = wb.createCellStyle();
    	HSSFCreationHelper createHelper = wb.getCreationHelper();
    	dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
    	dateStyle.setFont(font);
   
		   	
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	Date maDate=null;
    	try {
			maDate = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //remplissage des cellules.	
    	//num Facture
    	HSSFRow rowNumFact = sheet.getRow(23);
		HSSFCell numeroFact = rowNumFact.createCell(6);
		numeroFact.setCellValue(Integer.valueOf(numFact));
		numeroFact.setCellStyle(style);
		//Date facture
		HSSFRow rowDateFact = sheet.getRow(24);
		HSSFCell dateFact = rowDateFact.createCell(6);
		dateFact.setCellValue(maDate);
		dateFact.setCellStyle(dateStyle);

		//ROW 36
    	HSSFRow row36 = sheet.getRow(36);
    	formulaEvaluator.evaluateFormulaCell(row36.getCell(4));	
    	formulaEvaluator.evaluateFormulaCell(row36.getCell(5));
    	formulaEvaluator.evaluateFormulaCell(row36.getCell(6));
    	
    	//ROW 38
    	HSSFRow row38 = sheet.getRow(38);
    	formulaEvaluator.evaluateFormulaCell(row38.getCell(4));	
    	formulaEvaluator.evaluateFormulaCell(row38.getCell(5));
    	formulaEvaluator.evaluateFormulaCell(row38.getCell(6));
    	
    	//ROW 40
    	HSSFRow row40 = sheet.getRow(40);
    	formulaEvaluator.evaluateFormulaCell(row40.getCell(4));	
    	formulaEvaluator.evaluateFormulaCell(row40.getCell(5));
    	formulaEvaluator.evaluateFormulaCell(row40.getCell(6));
    	
    	//ROW 42
    	HSSFRow row42 = sheet.getRow(42);
    	formulaEvaluator.evaluateFormulaCell(row42.getCell(4));	
    	formulaEvaluator.evaluateFormulaCell(row42.getCell(5));
    	formulaEvaluator.evaluateFormulaCell(row42.getCell(6));
    	
    	//ROW 45
    	HSSFRow row45 = sheet.getRow(45);	
    	formulaEvaluator.evaluateFormulaCell(row45.getCell(6));
    	
    	//ROW 47
    	HSSFRow row47 = sheet.getRow(47);	
    	formulaEvaluator.evaluateFormulaCell(row47.getCell(6));
    	
    	//ROW 49
    	HSSFRow row49 = sheet.getRow(49);	
    	formulaEvaluator.evaluateFormulaCell(row49.getCell(6));
    	
    	//ROW 69
    	HSSFRow row69 = sheet.getRow(69);	
    	formulaEvaluator.evaluateFormulaCell(row69.getCell(6));
    	
    	//ROW 70
    	HSSFRow row70 = sheet.getRow(70);	
    	formulaEvaluator.evaluateFormulaCell(row70.getCell(6));
    	
    	//ROW 71
    	HSSFRow row71 = sheet.getRow(71);	
    	formulaEvaluator.evaluateFormulaCell(row71.getCell(6));
    	
    	//ROW 72
    	HSSFRow row72 = sheet.getRow(72);	
    	formulaEvaluator.evaluateFormulaCell(row72.getCell(6));   	
	    		
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	
        System.out.println("FIN modification onglet Usage");
	}

	public String repertoireSortie(){
		return cheminR;
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
