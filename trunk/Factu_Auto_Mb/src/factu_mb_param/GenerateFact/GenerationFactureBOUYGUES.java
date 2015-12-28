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
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import factu_mb_param.modele.ChargesReccurentesModel;
import factu_mb_param.modele.FormatDate;
import factu_mb_param.modele.GetDesktopPath;
import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.modele.OngletAboDetailModelBYTEL;
import factu_mb_param.modele.OngletNrcModel;
import factu_mb_param.modele.OngletRecoSansFasModelBYTEL;
import factu_mb_param.modele.TempInfosFactuModel;


public class GenerationFactureBOUYGUES{

    private String openFile;
    private FormatDate formatDate;
    private boolean isOpen ;
    String month;
    private String year;
    private String fileName;
    private int taille=0;
    private int rowEchu=0;
    private int rowEchoir=0;
    private double ecart1,ecart2,ecart3,ecart4;
    private String factReel="";
    private ChargesReccurentesModel chargesReccurentesModel;
    private Font font=null;
    private GetDesktopPath getDesktopPath;
    private String path="";
    private String newChemin="";
    private String repertoire="";
    private String cheminR="";
    private HSSFCellStyle jaune, cyan;
    
    public GenerationFactureBOUYGUES(){
    	getDesktopPath=new GetDesktopPath();
    	path=getDesktopPath.getApplicationPath()+"BYTEL";
    }

    
    
    public void fillDRT_INFOS_FACTU(ArrayList<TempInfosFactuModel> listBytelInfosFactuModel,String month,String year,String mois,String fileName,String chemin) throws IOException{
    	this.month=month;
    	this.year=year;     	
    	FileInputStream fileIn= new FileInputStream(new File(path+"/BYTEL_Rapport_Facturation_réelle.xls"));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("BT_INFOS_FACTU"); 
    	CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);
		factReel="BYTEL_Rapport_Facturation_réelle_"+year+month+".xls";
		
		HSSFRow rowStyle = sheet.getRow(2);
        HSSFCell cellStylefontJaune = rowStyle.getCell(14);
        jaune= cellStylefontJaune.getCellStyle();
        int i=1;      
        for(TempInfosFactuModel bytelInfosFactuModel:listBytelInfosFactuModel){
        	formatDate=new FormatDate();
        	HSSFRow row = sheet.getRow(i);
        	HSSFCell date = row.createCell(0);
        	date.setCellValue(formatDate.getFormatDateDetailWithoutSecond(bytelInfosFactuModel.getDate()));
        	date.setCellStyle(style);
        	HSSFCell nbRunId = row.createCell(1);
        	nbRunId.setCellStyle(style);
        	nbRunId.setCellValue(bytelInfosFactuModel.getRunId());
            HSSFCell labelMois = row.createCell(2);
            labelMois.setCellStyle(style);
            labelMois.setCellValue(bytelInfosFactuModel.getMois());
            HSSFCell nbNbrJrMois = row.createCell(3);
            nbNbrJrMois.setCellStyle(style);
            nbNbrJrMois.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNbrJrMois.setCellValue(bytelInfosFactuModel.getNbrJrMois());
            HSSFCell nbRefFacture = row.createCell(4);
            nbRefFacture.setCellStyle(style);
            nbRefFacture.setCellValue(bytelInfosFactuModel.getRefFacture());
            HSSFCell nbElmtIdArrears = row.createCell(5);
            nbElmtIdArrears.setCellStyle(style);
            nbElmtIdArrears.setCellValue(bytelInfosFactuModel.getElmtIdArrears());
            HSSFCell nbElmtIdAvance = row.createCell(6);
            nbElmtIdAvance.setCellStyle(style);
            nbElmtIdAvance.setCellValue(bytelInfosFactuModel.getElmtIdAvance());
            HSSFCell labelDescription = row.createCell(7);
            labelDescription.setCellStyle(style);
            labelDescription.setCellValue(bytelInfosFactuModel.getDescription());
            HSSFCell nbNf = row.createCell(8);
            nbNf.setCellStyle(style);
            nbNf.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNf.setCellValue(bytelInfosFactuModel.getNf());
            HSSFCell nbNa = row.createCell(9);
            nbNa.setCellStyle(style);
            nbNa.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNa.setCellValue(bytelInfosFactuModel.getNa());
            HSSFCell nbNd1 = row.createCell(10);
            nbNd1.setCellStyle(style);
            nbNd1.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNd1.setCellValue(bytelInfosFactuModel.getNd1());
            HSSFCell nbNd2 = row.createCell(11);
            nbNd2.setCellStyle(style);
            nbNd2.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNd2.setCellValue(bytelInfosFactuModel.getNd2());
            HSSFCell nbNp1 = row.createCell(12);
            nbNp1.setCellStyle(style);
            nbNp1.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNp1.setCellValue(bytelInfosFactuModel.getNp1());
            HSSFCell nbNp2 = row.createCell(13);
            nbNp2.setCellStyle(style);
            nbNp2.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNp2.setCellValue(bytelInfosFactuModel.getNp2());
            HSSFCell nbNbrJrsEchu = row.createCell(14);
            nbNbrJrsEchu.setCellStyle(jaune);
            nbNbrJrsEchu.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNbrJrsEchu.setCellValue(bytelInfosFactuModel.getNbrJrsEchu());
            HSSFCell nbAboMoisAvance = row.createCell(15);
            nbAboMoisAvance.setCellStyle(style);
            nbAboMoisAvance.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbAboMoisAvance.setCellValue(bytelInfosFactuModel.getAboMoisAvance());
            HSSFCell nbRate = row.createCell(16);
            nbRate.setCellStyle(style);
            nbRate.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbRate.setCellValue(bytelInfosFactuModel.getRate());           
            i+=1;
        }          
         
        HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);
        formulaEvaluator.evaluateAllFormulaCells(wb);
     
	FileOutputStream fileOut;
	fileOut = new FileOutputStream(chemin+"/"+factReel);
	newChemin=chemin+"/"+factReel;
	repertoire=chemin;
	cheminR=chemin;
	wb.write(fileOut);
	fileOut.close(); 
	System.out.println("Fin remplissage BT_INFO_FACTU");
    }
    
    public void fillBill_Invoice_Detail(ArrayList<MontantBillInvoiceDetailModel> listDRTMontantBillInvoiceDetailEchu,
    		ArrayList<MontantBillInvoiceDetailModel> listDRTMontantBillInvoiceDetailEchoir,
    		String fromDate,String toDate,String nextFromDate,String nextToDate) throws IOException{
    	
    	FileInputStream fileIn= new FileInputStream(new File(newChemin));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("BT_INFOS_FACTU");     	
    	CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);
    	
    	//recherche ligne debut echu
        rowEchu=15;    
      //recherche ligne debut echoir
        rowEchoir=26;
        
        //ECHU
        for(MontantBillInvoiceDetailModel bytelMontantBIDModel:listDRTMontantBillInvoiceDetailEchu){
        	Double valElmtId=Double.valueOf(bytelMontantBIDModel.getElementId());
        	Double valMontant=Double.valueOf(bytelMontantBIDModel.getMontant());
        	HSSFRow row = sheet.getRow(rowEchu);
        	HSSFCell labelEchu = row.createCell(4);
        	labelEchu.setCellStyle(style);
        	labelEchu.setCellValue(bytelMontantBIDModel.getEchu());
        	HSSFCell nbElementId = row.createCell(5);
        	nbElementId.setCellStyle(style);
        	nbElementId.setCellValue(valElmtId);
        	HSSFCell nbMontant = row.createCell(6);
        	nbMontant.setCellStyle(style);
        	nbMontant.setCellValue(valMontant);
            rowEchu+=1;
        }
        
        //ECHOIR
        for(MontantBillInvoiceDetailModel bytelMontantBIDModel:listDRTMontantBillInvoiceDetailEchoir){
        	Double valElmtId=Double.valueOf(bytelMontantBIDModel.getElementId());
        	Double valMontant=Double.valueOf(bytelMontantBIDModel.getMontant());
        	HSSFRow row = sheet.getRow(rowEchoir);
        	HSSFCell labelEchu = row.createCell(4);
        	labelEchu.setCellStyle(style);
        	labelEchu.setCellValue(bytelMontantBIDModel.getEchu());
        	HSSFCell nbElementId = row.createCell(5);
        	nbElementId.setCellStyle(style);
        	nbElementId.setCellValue(valElmtId);
        	HSSFCell nbMontant = row.createCell(6);
        	nbMontant.setCellStyle(style);
        	nbMontant.setCellValue(valMontant);
            rowEchoir+=1;
        }
        
      //Tableau Echeances
        HSSFRow rowEchu = sheet.getRow(39);
        HSSFCell dateEchu = rowEchu.createCell(5);
        dateEchu.setCellStyle(style);
        dateEchu.setCellValue("Du "+fromDate+" au "+toDate);
        HSSFRow rowEchoir = sheet.getRow(40);
        HSSFCell dateEchoir = rowEchoir.createCell(5);
        dateEchoir.setCellStyle(style);
        dateEchoir.setCellValue("Du "+nextFromDate+" au "+nextToDate);
        
        HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);
        formulaEvaluator.evaluateAllFormulaCells(wb);
            
        FileOutputStream fileOut;
    	fileOut = new FileOutputStream(newChemin);
    	wb.write(fileOut);
    	fileOut.close(); 
    	 System.out.println("Fin remplissage BILL_INVOICE_FACT");       	
    }        
        
    
	public void ongletInputs(int nbrJour,String numFact,String mois,String annee,String fileName) throws IOException{
		this.fileName=fileName;
		FileInputStream fileIn= new FileInputStream(new File(path+"/BYTEL_Facture.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(fileIn);
    	Sheet sheet = wb.getSheet("Inputs");  
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
	    String titre="FACTURATION BOUYGUES TELECOM – DU 1er AU ";
	    String descr1="Nous avons pris en compte les NRC's facturées sur la facture ";
	    String descr2="Nous avons pris les tickets facturés sur la facture ";
	    
	    Row rowTitle = sheet.getRow(0); 
	    Cell cellStylefontGris = rowTitle.getCell(0);
        CellStyle stylecellGris= cellStylefontGris.getCellStyle();
        stylecellGris.setBorderBottom(CellStyle.BORDER_THICK);
        stylecellGris.setBorderLeft(CellStyle.BORDER_THICK);
        stylecellGris.setBorderRight(CellStyle.BORDER_THICK);
        stylecellGris.setBorderTop(CellStyle.BORDER_THICK);
        stylecellGris.setAlignment(CellStyle.ALIGN_CENTER);
        stylecellGris.setVerticalAlignment(CellStyle.ALIGN_FILL);
	    Cell title = rowTitle.createCell(0);
	    title.setCellValue(titre+nbrJour+" "+mois.toUpperCase()+" "+annee);
	    title.setCellStyle(stylecellGris);
	    
	    Cell contour1 = rowTitle.createCell(1);
	    contour1.setCellStyle(style);
	    Cell contour2 = rowTitle.createCell(2);
	    contour2.setCellStyle(style);
	    
	    Row rowNbJr = sheet.getRow(2);
        Cell cellJour = rowNbJr.createCell(1);
        cellJour.setCellStyle(style2);
        cellJour.setCellValue(nbrJour);
        
        
        Row rowDescr1 = sheet.getRow(24);
        Cell labDescr1= rowDescr1.createCell(0);        
        labDescr1.setCellValue(descr1+numFact+" ("+mois+" "+annee+")");  
        Row rowDescr2 = sheet.getRow(28);
        Cell labDescr2 = rowDescr2.createCell(0);
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
		HSSFSheet sheet = wb.getSheet("BT_INFOS_FACTU"); 
		
    		//recuparations des cellules.
		    for(int i=39;i<55;i++){
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
            System.out.println("TAILLE = "+allListChargesReccurentes.size());
            
    	return allListChargesReccurentes;
    }
	
	public void ongletAbonnements(ArrayList<ChargesReccurentesModel> listChargesReccurentes) throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  		  		    	
		XSSFWorkbook wb = new XSSFWorkbook(fileIn);
		Sheet sheet = wb.getSheet("Abonnements"); 
		CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);
		
	    //remplissage des cellules.
    	DecimalFormat df = new DecimalFormat("0.##");
    	Row rowStyle = sheet.getRow(2);
    	Cell cellStylefNombre1 = rowStyle.getCell(4);
    	Cell cellStylefNombre2 = rowStyle.getCell(6);
        CellStyle stylecellNumber1= cellStylefNombre1.getCellStyle();
        CellStyle stylecellNumber2= cellStylefNombre2.getCellStyle();
        int i=2;
		    for(ChargesReccurentesModel crm:listChargesReccurentes){
		    		String amount="";
		    		Row row = sheet.getRow(i);
		    		Cell lbDate = row.createCell(3);
		    		lbDate.setCellStyle(style);
		    		lbDate.setCellValue(crm.getDate());
		    		Cell nbJour = row.createCell(4);
		            nbJour.setCellStyle(stylecellNumber1);
		            nbJour.setCellValue(crm.getNbrJour());
		            Cell lbJour = row.createCell(5);
		            lbJour.setCellStyle(style);
		            lbJour.setCellValue(crm.getLabelJour());
		            Cell nbPrixAbo= row.createCell(6);
		            nbPrixAbo.setCellStyle(stylecellNumber2);
		            nbPrixAbo.setCellValue(crm.getPrixAbo());  
		            Cell nbMontat = row.createCell(7);
		            nbMontat.setCellStyle(style);	
		            nbMontat.setCellType(Cell.CELL_TYPE_NUMERIC);
		            amount=df.format(crm.getMontant()).replaceAll(",", "\\.");
		            nbMontat.setCellValue(new Double(amount));
		            Cell lbId = row.createCell(8);
		            lbId.setCellStyle(style);
		            lbId.setCellValue(crm.getId());
		            i+=1;
	    }
		    
	    FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();	        
        for(Row r : sheet) {
            for(Cell c : r) {
                if(c.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    evaluator.evaluateFormulaCell(c);
                }
            }
        }

		
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	    
        System.out.println("FIN modification onglet 'Abonnements'");
        
    }
	
	public void ongletAbonnementDetail(ArrayList<OngletAboDetailModelBYTEL> listAboDetail) throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  
		XSSFWorkbook wb = new XSSFWorkbook(fileIn); 
		Sheet sheet = wb.getSheet("Abonnements - Détails"); 
		CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);    	
    	
	    //remplissage des cellules.		
	    int i=3;	    
	    for(OngletAboDetailModelBYTEL aboDetail:listAboDetail){	    	
	    	Row row = sheet.getRow(i);
	    	Cell lbTypeTrafic = row.createCell(0);
    		lbTypeTrafic.setCellStyle(style);
    		lbTypeTrafic.setCellValue(aboDetail.getId_client());
    		Cell lbClassJuris = row.createCell(1);
    		lbClassJuris.setCellStyle(style);
    		lbClassJuris.setCellValue(aboDetail.getType_abo());
    		Cell lbCodeJuris = row.createCell(2);
            lbCodeJuris.setCellStyle(style);
            lbCodeJuris.setCellValue(aboDetail.getActive_dt());
            Cell lbJuris= row.createCell(3);
            lbJuris.setCellStyle(style);
            lbJuris.setCellValue(aboDetail.getInactive_dt());             
          i+=1;
	    }

	    FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();	        
        for(Row r : sheet) {
            for(Cell c : r) {
                if(c.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    evaluator.evaluateFormulaCell(c);
                }
            }
        }
		
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	
        System.out.println("FIN modification onglet 'Abonnements - Détails'");
	}
	
	
	public void ongletRecoSansFas(ArrayList<OngletRecoSansFasModelBYTEL> listRecoSansFas) throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  
		XSSFWorkbook wb = new XSSFWorkbook(fileIn); 
		Sheet sheet = wb.getSheet("Recos 100M-200M (sans FAS)"); 
		CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);    	
    	
	    //remplissage des cellules.		
	    int i=1;	    
	    for(OngletRecoSansFasModelBYTEL recoSansFas:listRecoSansFas){	    	
	    	Row row = sheet.getRow(i);
	    	Cell lbTypeTrafic = row.createCell(0);
    		lbTypeTrafic.setCellStyle(style);
    		lbTypeTrafic.setCellValue(recoSansFas.getExternalId());
    		Cell lbClassJuris = row.createCell(1);
    		lbClassJuris.setCellStyle(style);   		
    		lbClassJuris.setCellValue(recoSansFas.getParentSubscrNo());
    		Cell lbCodeJuris = row.createCell(2);
            lbCodeJuris.setCellStyle(style);          
            lbCodeJuris.setCellValue(recoSansFas.getTrackingId());
            Cell lbJuris= row.createCell(3);
            lbJuris.setCellStyle(style);           
            lbJuris.setCellValue(recoSansFas.getTrackingIdServ());    
          i+=1;
	    }

	    Row rowTotal = sheet.getRow(0);
	    Cell cellTotal = rowTotal.createCell(6);
		cellTotal.setCellValue(listRecoSansFas.size());
		
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	
        System.out.println("FIN modification onglet 'Recos 100M-200M (sans FAS)'");
	}
	
	public void ongletNRC(ArrayList<OngletNrcModel> listNrcBytel) throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  		  		    	
		XSSFWorkbook wb = new XSSFWorkbook(fileIn); 
		Sheet sheet = wb.getSheet("NRC"); 
		CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);
    	
	    //remplissage des cellules.	
	    int i=10;
	    for(OngletNrcModel nnm:listNrcBytel){
	    	formatDate=new FormatDate();
	    	Row row = sheet.getRow(i);
	    		Cell lbTracking = row.createCell(1);
	    		lbTracking.setCellStyle(style);
	    		lbTracking.setCellValue(nnm.getTrackingId());
	    		Cell nbXID = row.createCell(2);
	            nbXID.setCellStyle(style);
	            nbXID.setCellValue(nnm.getxID());
	            Cell lbIdContrat = row.createCell(3);
	            lbIdContrat.setCellStyle(style);
	            lbIdContrat.setCellValue(nnm.getiDContrat());
	            Cell lbOffre= row.createCell(4);
	            lbOffre.setCellStyle(style);
	            lbOffre.setCellValue(nnm.getOffre());  
	            Cell lbDtActiveServ = row.createCell(5);
	            lbDtActiveServ.setCellStyle(style);
	            lbDtActiveServ.setCellValue(formatDate.getFormatDate(nnm.getDateActivationService()));
	            Cell lbTypeNRC = row.createCell(6);
	            lbTypeNRC.setCellStyle(style);
	            lbTypeNRC.setCellValue(nnm.getTypeIdNrc());
	            Cell lbFact = row.createCell(7);
	            lbFact.setCellStyle(style);
	            lbFact.setCellValue(nnm.getLibelleFacturation());
	            Cell lbEffectDt= row.createCell(8);
	            lbEffectDt.setCellStyle(style);
	            lbEffectDt.setCellValue(formatDate.getFormatDateDetailWithoutSecond(nnm.getEffectiveDate()));  
	            Cell lbMontat = row.createCell(9);
	            lbMontat.setCellStyle(style);
	            lbMontat.setCellValue(nnm.getMontant());			            
	    	i+=1;	
	    }

	    FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();	        
        for(Row r : sheet) {
            for(Cell c : r) {
                if(c.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    evaluator.evaluateFormulaCell(c);
                }
            }
        }
	    
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	
        System.out.println("FIN modification onglet NRC");
	}
	
	
	
	
	public void ongletRecap() throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  		  		    	
		XSSFWorkbook wb = new XSSFWorkbook(fileIn);  
		Sheet sheet = wb.getSheet("Récapitulatif"); 
		   	
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();	        
        for(Row r : sheet) {
            for(Cell c : r) {
                if(c.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    evaluator.evaluateFormulaCell(c);
                }
            }
        }
	    		
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	
        System.out.println("FIN modification onglet Récapitulatif");
	}
	
	public void ongletRecapFacture(String numFact,String date) throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  		  		    	
		XSSFWorkbook wb = new XSSFWorkbook(fileIn);   
		Sheet sheet = wb.getSheet("Récap facture"); 
		CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 10);
    	style.setAlignment(CellStyle.ALIGN_RIGHT);
    	style.setFont(font);
    	CellStyle dateStyle = wb.createCellStyle();
    	XSSFCreationHelper createHelper = wb.getCreationHelper();
    	dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
    	dateStyle.setFont(font);
 		   	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
    	Date maDate=null;
    	try {
			maDate = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //remplissage des cellules.	
    	//num Facture
    	Row rowNumFact = sheet.getRow(23);
		Cell numeroFact = rowNumFact.createCell(6);
		numeroFact.setCellValue(Integer.valueOf(numFact)+"/1");
		numeroFact.setCellStyle(style);
		//Date facture
		Row rowDateFact = sheet.getRow(24);
		Cell dateFact = rowDateFact.createCell(6);
		dateFact.setCellValue(maDate);
		dateFact.setCellStyle(dateStyle);
		   	
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();	        
        for(Row r : sheet) {
            for(Cell c : r) {
                if(c.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    evaluator.evaluateFormulaCell(c);
                }
            }
        }
	    		
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	
        System.out.println("FIN modification onglet Recap Facture");
	}
	
	public double decimalNombre(double monChiffre){
		DecimalFormat df = new DecimalFormat("########.00");
		String str = df.format(monChiffre);
		monChiffre = Double.parseDouble(str.replace(',', '.'));
		return monChiffre;		
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
