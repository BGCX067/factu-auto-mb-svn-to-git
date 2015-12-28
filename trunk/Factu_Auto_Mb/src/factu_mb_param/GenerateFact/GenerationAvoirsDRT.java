package factu_mb_param.GenerateFact;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import factu_mb_param.modele.AvoirAncienAboModel;
import factu_mb_param.modele.AvoirNouvelAboModel;
import factu_mb_param.modele.FormatDate;
import factu_mb_param.modele.GetDesktopPath;
import factu_mb_param.modele.InfosAvoirsModel;


public class GenerationAvoirsDRT{

    private FormatDate formatDate;
    private InfosAvoirsModel infosAvoirsModel;
    private boolean isOpen ;
    private String month,year;
    private String fileName;
    private GetDesktopPath getDesktopPath;
    private String path="";
    private String newChemin="";
    private String repertoire="";
    private String cheminR="";
    private String text2="";
    private Font font=null;
    private double fraisResiliation=0;
    private double avoirAncienAbo=0;
    private double avoirNouvelAbo=0;
    private double totalAvoirs=0;
    private String monthFact="";
    
    public GenerationAvoirsDRT(){
    	infosAvoirsModel=new InfosAvoirsModel();
    	getDesktopPath=new GetDesktopPath();
    	path=getDesktopPath.getApplicationPath()+"DARTY";
    }

    
    
    public InfosAvoirsModel infos_Avoir_Darty() throws IOException{
    	FileInputStream fileIn= new FileInputStream(new File(path+"/AAE DARTY frais de résiliation.xls"));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("analyse"); 
		
        HSSFRow rowBillPrice = sheet.getRow(40);
        HSSFRow rowAvoir = sheet.getRow(53);
        HSSFRow rowTotal = sheet.getRow(51);
        int cellAvoir=0;
        boolean trouver=false;
        int i=rowTotal.getLastCellNum();
        while (!trouver){
        	System.out.println("n° colonne : "+i);
        	if(rowTotal.getCell(i)!=null && rowTotal.getCell(i).getCellType()==Cell.CELL_TYPE_STRING && rowTotal.getCell(i).getStringCellValue().equalsIgnoreCase("Total")){
        		System.out.println("n° colonne trouvée: "+i);
        		cellAvoir=i-1;
        		trouver=true;
        	}
        	i--;
        }
        
        HSSFCell prixFacture = rowBillPrice.getCell(cellAvoir);
        HSSFCell montantAvoir = rowAvoir.getCell(cellAvoir);
       
        System.out.println(" prix facturé : "+prixFacture.getNumericCellValue());
        System.out.println(" montant avoir : "+montantAvoir.getNumericCellValue());       
        
        infosAvoirsModel.setMontantAvoir(montantAvoir.getNumericCellValue());
        infosAvoirsModel.setPrixFacture(prixFacture.getNumericCellValue());
        
		System.out.println("Fin recuperation info avoir darty");
		return infosAvoirsModel;
    }
    
    public void fraisResiliationDSL(InfosAvoirsModel infosAvoirsModel, String month1,String month2,String year,String filename,String chemin,String moisDecimal) throws IOException{
    	this.fileName=filename;
    	this.month=moisDecimal;
    	this.year=year;
    	FileInputStream fileIn= new FileInputStream(new File(path+"/Avoirs_Darty.xls"));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("1 - Frais de résiliation DSL");   
    	CellStyle style=wb.createCellStyle();
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	
    	HSSFRow rowStyle = sheet.getRow(7);        
    	HSSFCellStyle cellStyleMonetaire = rowStyle.getCell(5).getCellStyle();
        HSSFCellStyle cellStyleMonetaireRouge = rowStyle.getCell(6).getCellStyle();
        HSSFCellStyle cellStyleMonetaireGras = rowStyle.getCell(7).getCellStyle();       
        
        HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);  
        month1=month1.replaceFirst(".",(month1.charAt(0)+"").toUpperCase());
        month2=month2.replaceFirst(".",(month2.charAt(0)+"").toUpperCase());
        String textChiffres="Chiffres "+month1+" "+year;
        String text1="Retenue frais de résiliation "+month1+" "+year.substring(2)+" (Calcul pour la facture de "+month2+" "+year+")";
        String text2="Total des avoirs "+month1+" "+year.substring(2)+" à mettre sur la facture de "+month2+" "+year;
        		
    		//Remplissage du fichier excell
        double difference=infosAvoirsModel.getPrixFacture()-infosAvoirsModel.getMontantAvoir();
        HSSFRow row = sheet.getRow(7);
		    HSSFCell cellTextChiffres = row.createCell(4);
		    cellTextChiffres.setCellStyle(row.getCell(4).getCellStyle());
		    cellTextChiffres.setCellValue(textChiffres);
		    cellTextChiffres.setCellStyle(style);
		    
        	HSSFCell cellPrixFacture = row.createCell(5);
        	cellPrixFacture.setCellStyle(cellStyleMonetaire);
        	cellPrixFacture.setCellValue(infosAvoirsModel.getPrixFacture());
        	HSSFCell cellPrixRealFacture = row.createCell(6);
        	cellPrixRealFacture.setCellStyle(cellStyleMonetaireRouge);
        	cellPrixRealFacture.setCellValue(difference);
        	HSSFCell cellMontantAvoir = row.getCell(7);
        	cellMontantAvoir.setCellStyle(cellStyleMonetaireGras);
        	formulaEvaluator.evaluateFormulaCell(row.getCell(7));
            
        HSSFRow row4 = sheet.getRow(4);
        HSSFCell MontantHT4 = row4.getCell(1);
        HSSFCell MontantTTC4 = row4.getCell(2);
        MontantHT4.setCellStyle(cellStyleMonetaire);
        MontantTTC4.setCellStyle(cellStyleMonetaire);
        formulaEvaluator.evaluateFormulaCell(row4.getCell(1));
        formulaEvaluator.evaluateFormulaCell(row4.getCell(2));
        HSSFCell cellText1 = row4.createCell(0);
        cellText1.setCellStyle(row4.getCell(0).getCellStyle());
        cellText1.setCellValue(text1);
        cellText1.setCellStyle(style);
        
        HSSFRow row10 = sheet.getRow(10);
        HSSFCell MontantHT10 = row10.getCell(1);
        HSSFCell MontantTTC10 = row10.getCell(2);
        MontantHT10.setCellStyle(cellStyleMonetaireGras);
        MontantTTC10.setCellStyle(cellStyleMonetaire);
        formulaEvaluator.evaluateFormulaCell(row10.getCell(1));
        formulaEvaluator.evaluateFormulaCell(row10.getCell(2));
        HSSFCell cellText2 = row10.createCell(0);
        cellText2.setCellStyle(row10.getCell(0).getCellStyle());
        cellText2.setCellValue(text2);
        cellText2.setCellStyle(style);
            
            FileOutputStream fileOut;
            newChemin=chemin+"/"+filename;
            cheminR=chemin;
        	fileOut = new FileOutputStream(newChemin);
        	wb.write(fileOut);
        	fileOut.close(); 
        	 System.out.println("Fin remplissage onglet '1 - Frais de résiliation DSL'");       	
    }        
    
    public void ancienAbo(ArrayList<AvoirAncienAboModel> listAvoirAncienAboModel,int nbJrs) throws IOException{
    	
    	FileInputStream fileIn= new FileInputStream(new File(newChemin));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("2 - Ancien abo 2P THD"); 
    	CellStyle style=wb.createCellStyle();
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb); 
    	
            int i=7;
            //LISTE_CTR_DRT_THD_2P
            formatDate=new FormatDate();
            for(AvoirAncienAboModel avoirAncienAboModel:listAvoirAncienAboModel){            	
            	HSSFRow row = sheet.getRow(i);
            	HSSFCell cellCTR = row.createCell(0);
            	cellCTR.setCellValue(avoirAncienAboModel.getCtr());     
            	cellCTR.setCellStyle(style);
            	String dateSouscr;
            	if(avoirAncienAboModel.getDateSouscription()==null || avoirAncienAboModel.getDateSouscription().isEmpty()){
            		dateSouscr="";
            	}else{
            	 dateSouscr=formatDate.getFormatDate(avoirAncienAboModel.getDateSouscription());           	
            	}
            	HSSFCell cellDateSouscr = row.createCell(1);
            	cellDateSouscr.setCellValue(dateSouscr);
            	cellDateSouscr.setCellStyle(style);
            	
            	String dateActiv;
            	if(avoirAncienAboModel.getDateActivation()==null || avoirAncienAboModel.getDateActivation().isEmpty()){
            		dateActiv="";
            	}else{
            	dateActiv=formatDate.getFormatDate(avoirAncienAboModel.getDateActivation());
            	}
            	HSSFCell cellDateActiv = row.createCell(2);
            	cellDateActiv.setCellValue(dateActiv);
            	cellDateActiv.setCellStyle(style);
            	
            	String dateInactiv;
            	if(avoirAncienAboModel.getDateInactivation()==null || avoirAncienAboModel.getDateInactivation().isEmpty()){
            		dateInactiv="";
            	}else{
            	dateInactiv=formatDate.getFormatDate(avoirAncienAboModel.getDateInactivation());
            	}
            	HSSFCell cellDateInactiv = row.createCell(3);
            	cellDateInactiv.setCellValue(dateInactiv);
            	cellDateInactiv.setCellStyle(style);
            	
            	HSSFCell cellMois = row.createCell(4);
            	cellMois.setCellValue(avoirAncienAboModel.getMois());
            	cellMois.setCellStyle(style);
            	
            	HSSFCell cellNbJrs = row.createCell(5);
            	cellNbJrs.setCellValue(avoirAncienAboModel.getNbJrs());
            	cellNbJrs.setCellStyle(style);
                i+=1;
            }         
            
            HSSFRow row9 = sheet.getRow(4);
        	HSSFCell cellNbrJours = row9.createCell(9);
        	cellNbrJours.setCellValue(nbJrs);
  	
            HSSFRow rowTotal = sheet.getRow(3083);
        	formulaEvaluator.evaluateFormulaCell(rowTotal.getCell(5));
        	
        	HSSFRow rowAvoir = sheet.getRow(5);
        	formulaEvaluator.evaluateFormulaCell(rowAvoir.getCell(9));
        	String sheetName="2 - Ancien abo 2P THD "+month+"-"+year;
        	wb.setSheetName(1, sheetName);
        	
            FileOutputStream fileOut;
        	fileOut = new FileOutputStream(newChemin);
        	wb.write(fileOut);
        	fileOut.close(); 
        	  System.out.println("Fin remplissage onglet '2 - Ancien abo 2P THD'");       	
    }  
    
    public void nouvelAbo(ArrayList<AvoirNouvelAboModel> listAvoirNouvelAboModel,int nbJrs) throws IOException{

    	FileInputStream fileIn= new FileInputStream(new File(newChemin));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("3 - Nouvel abo 2P THD"); 
    	CellStyle style=wb.createCellStyle();
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb); 
    	
    	int i=7;
    	//LISTE_CTR_DRT_THD_2P_AJOUTS
    	formatDate=new FormatDate();
    	for(AvoirNouvelAboModel avoirNouvelAboModel:listAvoirNouvelAboModel){            	
        	HSSFRow row = sheet.getRow(i);
        	HSSFCell cellCTR = row.createCell(0);
        	cellCTR.setCellValue(avoirNouvelAboModel.getCtr());   
        	cellCTR.setCellStyle(style);
        	
        	String dateSouscr;
        	if(avoirNouvelAboModel.getDateSouscription()==null || avoirNouvelAboModel.getDateSouscription().isEmpty()){
        		dateSouscr="";
        	}else{
        	 dateSouscr=formatDate.getFormatDate(avoirNouvelAboModel.getDateSouscription());           	
        	}
        	HSSFCell cellDateSouscr = row.createCell(1);
        	cellDateSouscr.setCellValue(dateSouscr);
        	cellDateSouscr.setCellStyle(style);
        	
        	String dateActiv;
        	if(avoirNouvelAboModel.getDateActivation()==null || avoirNouvelAboModel.getDateActivation().isEmpty()){
        		dateActiv="";
        	}else{
        	dateActiv=formatDate.getFormatDate(avoirNouvelAboModel.getDateActivation());
        	}
        	HSSFCell cellDateActiv = row.createCell(2);
        	cellDateActiv.setCellValue(dateActiv);
        	cellDateActiv.setCellStyle(style);
        	
        	String dateInactiv;
        	if(avoirNouvelAboModel.getDateInactivation()==null || avoirNouvelAboModel.getDateInactivation().isEmpty()){
        		dateInactiv="";
        	}else{
        	dateInactiv=formatDate.getFormatDate(avoirNouvelAboModel.getDateInactivation());
        	}
        	HSSFCell cellDateInactiv = row.createCell(3);
        	cellDateInactiv.setCellValue(dateInactiv);
        	cellDateInactiv.setCellStyle(style);
        	
        	HSSFCell cellMois = row.createCell(4);
        	cellMois.setCellValue(avoirNouvelAboModel.getMois());
        	cellMois.setCellStyle(style);
        	
        	HSSFCell cellNbJrs = row.createCell(5);
        	cellNbJrs.setCellValue(avoirNouvelAboModel.getNbJrs());
        	cellNbJrs.setCellStyle(style);
            i+=1;
        }         
        
        HSSFRow row = sheet.getRow(4);
    	HSSFCell cellNbrJours = row.createCell(9);
    	cellNbrJours.setCellValue(nbJrs);
	
        HSSFRow rowTotal = sheet.getRow(13183);
    	formulaEvaluator.evaluateFormulaCell(rowTotal.getCell(5));
    	
    	HSSFRow rowAvoir = sheet.getRow(5);
    	formulaEvaluator.evaluateFormulaCell(rowAvoir.getCell(9));
            
    	String sheetName="3 - Nouvel abo 2P THD "+month+"-"+year;
    	wb.setSheetName(2, sheetName);
    	
	    FileOutputStream fileOut;
		fileOut = new FileOutputStream(newChemin);
		wb.write(fileOut);
		fileOut.close(); 
		System.out.println("Fin remplissage onglet '3 - Nouvel abo 2P THD'");
            
    } 
    
	public void ongletRecapAvoirs(String fromDate,String toDate,String year) throws IOException{		
		FileInputStream fileIn= new FileInputStream(new File(newChemin));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("Recap avoirs");     	
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);
    	fromDate=fromDate.replaceFirst(".",(fromDate.charAt(0)+"").toUpperCase());
    	toDate=toDate.replaceFirst(".",(toDate.charAt(0)+"").toUpperCase());
    	this.monthFact=toDate;
    	String text="Avoirs et reste à facturer pour la facture de "+toDate+" "+year;
    	text2="Données internes pour les chiffres du mois de "+fromDate+" "+year+" (ADH)";

    	HSSFRow row = sheet.getRow(0);
    	HSSFCellStyle cellStyle = row.getCell(0).getCellStyle();
    	HSSFCell cellText = row.createCell(0);
    	cellText.setCellStyle(cellStyle);
    	cellText.setCellValue(text);
    	
    	HSSFRow row2 = sheet.getRow(3);
    	HSSFCellStyle cellStyle2 = row2.getCell(3).getCellStyle();
    	HSSFCell cellText2 = row2.createCell(3);    	
    	cellText2.setCellStyle(cellStyle2);
    	cellText2.setCellValue(text2);
    	
    	HSSFRow row3 = sheet.getRow(3);
    	formulaEvaluator.evaluateFormulaCell(row3.getCell(1));
    	fraisResiliation=row3.getCell(1).getNumericCellValue();
    	
    	HSSFRow row4 = sheet.getRow(4);
    	formulaEvaluator.evaluateFormulaCell(row4.getCell(1));
    	avoirAncienAbo=row4.getCell(1).getNumericCellValue();
    	
    	HSSFRow row5 = sheet.getRow(5);
    	formulaEvaluator.evaluateFormulaCell(row5.getCell(1));
    	avoirNouvelAbo=row5.getCell(1).getNumericCellValue();
    	
    	HSSFRow row6 = sheet.getRow(6);
    	formulaEvaluator.evaluateFormulaCell(row6.getCell(1));
    	formulaEvaluator.evaluateFormulaCell(row6.getCell(2));
    	totalAvoirs=row6.getCell(1).getNumericCellValue();
    	
        FileOutputStream fileOut;
        fileOut = new FileOutputStream(newChemin);
    	wb.write(fileOut);
    	fileOut.close();  
        System.out.println("FIN remplissage onglet Recap Avoirs");
            
    }	
	
	public void modifRecapAvoirFichierFactureDarty() throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(path+"/DARTY_Facture.xls"));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("Récap avoirs"); 
    	
    	HSSFRow rowStyle = sheet.getRow(5);
    	HSSFCellStyle style=rowStyle.getCell(2).getCellStyle();
    	
    	
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);
    	
    	
    	HSSFRow row5 = sheet.getRow(5);
    	HSSFCell cellFraisRes = row5.createCell(2); 
    	cellFraisRes.setCellValue(decimalNombre(fraisResiliation));
    	cellFraisRes.setCellStyle(style);
    	HSSFCell cellText = row5.createCell(4);
    	cellText.setCellValue(text2);
    	cellText.setCellStyle(style);
    	
    	HSSFRow row6 = sheet.getRow(6);
    	HSSFCell cellAncienAbo = row6.createCell(2);   
    	cellAncienAbo.setCellValue(decimalNombre(avoirAncienAbo));
    	cellAncienAbo.setCellStyle(style);
    	
    	HSSFRow row7 = sheet.getRow(7);
    	HSSFCell cellNouvelAbo = row7.createCell(2); 
    	cellNouvelAbo.setCellValue(decimalNombre(avoirNouvelAbo));
    	cellNouvelAbo.setCellStyle(style);
    	
    	formulaEvaluator.evaluateAllFormulaCells(wb);
    	
    	
    	
        FileOutputStream fileOut1;
        FileOutputStream fileOut2;
        String fileTemp="Darty_Facture_"+monthFact+"_Temp.xls";
        fileOut1 = new FileOutputStream(cheminR+"/"+fileTemp);
        fileOut2 = new FileOutputStream(path+"/DARTY_Facture.xls");
    	wb.write(fileOut1);
    	wb.write(fileOut2);
    	fileOut1.close();
    	fileOut2.close();
        System.out.println("FIN copie onglet Réacp avoirs");
            
    }	
	
	public String repertoireSortie(){
		return cheminR;
	}
	
	public double decimalNombre(double monChiffre){
		DecimalFormat df = new DecimalFormat("########.00");
		String str = df.format(monChiffre);
		monChiffre = Double.parseDouble(str.replace(',', '.'));
		return monChiffre;		
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
