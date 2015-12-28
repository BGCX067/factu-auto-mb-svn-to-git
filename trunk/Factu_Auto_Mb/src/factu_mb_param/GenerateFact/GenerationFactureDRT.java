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
import factu_mb_param.modele.ElementIDModel;
import factu_mb_param.modele.FormatDate;
import factu_mb_param.modele.GetDesktopPath;
import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.modele.OngletNrcModel;
import factu_mb_param.modele.OngletUsageModelDarty;
import factu_mb_param.modele.SubtypeCodeModel;
import factu_mb_param.modele.TempInfosFactuModel;


public class GenerationFactureDRT{

    private String openFile;
    private FormatDate formatDate;
    private boolean isOpen ;
    String month;
    private String year;
    private String fileName;
    private int taille=0;
    private int rowDebut=0;
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
    
    public GenerationFactureDRT(){
    	getDesktopPath=new GetDesktopPath();
    	path=getDesktopPath.getApplicationPath()+"DARTY";
    }

    
    
    public void fillDRT_INFOS_FACTU(ArrayList<TempInfosFactuModel> listDRTInfosFactuModel,String month,String year,String mois,String fileName,String chemin) throws IOException{
    	this.month=month;
    	this.year=year;     	
    	FileInputStream fileIn= new FileInputStream(new File(path+"/Rapport_Facturation_réelle.xls"));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("DRT_INFOS_FACTU"); 
    	CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);
		factReel="Rapport_Facturation_réelle_"+year+month+" ("+mois+" "+year+").xls";
		
        int i=1;
        //recherche ligne debut
        for(int a=0;a<50;a++){
        	HSSFRow rowA = sheet.getRow(a);
        	if (rowA.getCell(5).getStringCellValue().equalsIgnoreCase("'ÉCHU'")){
        		rowDebut=a+1;
                a=50;
        	}
        }
        System.out.println("ROW DEBUT "+rowDebut);
        HSSFRow rowStyle = sheet.getRow(rowDebut+1);
        HSSFCell cellStylefontJaune = rowStyle.getCell(16);
        HSSFCell cellStylefontCyan = rowStyle.getCell(19);
        jaune= cellStylefontJaune.getCellStyle();
        cyan= cellStylefontCyan.getCellStyle();
        for(TempInfosFactuModel drtInfosFactuModel:listDRTInfosFactuModel){
        	formatDate=new FormatDate();
        	HSSFRow row = sheet.getRow(i);
        	HSSFCell date = row.createCell(0);
        	date.setCellValue(formatDate.getFormatDateDetailWithoutSecond(drtInfosFactuModel.getDate()));
        	date.setCellStyle(style);
        	HSSFCell nbRunId = row.createCell(1);
        	nbRunId.setCellStyle(style);
        	nbRunId.setCellValue(drtInfosFactuModel.getRunId());
            HSSFCell labelMois = row.createCell(2);
            labelMois.setCellStyle(style);
            labelMois.setCellValue(drtInfosFactuModel.getMois());
            HSSFCell nbNbrJrMois = row.createCell(3);
            nbNbrJrMois.setCellStyle(style);
            nbNbrJrMois.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNbrJrMois.setCellValue(drtInfosFactuModel.getNbrJrMois());
            HSSFCell nbRefFacture = row.createCell(4);
            nbRefFacture.setCellStyle(style);
            nbRefFacture.setCellValue(drtInfosFactuModel.getRefFacture());
            HSSFCell nbElmtIdArrears = row.createCell(5);
            nbElmtIdArrears.setCellStyle(jaune);
            nbElmtIdArrears.setCellValue(drtInfosFactuModel.getElmtIdArrears());
            HSSFCell nbElmtIdAvance = row.createCell(6);
            nbElmtIdAvance.setCellStyle(cyan);
            nbElmtIdAvance.setCellValue(drtInfosFactuModel.getElmtIdAvance());
            HSSFCell labelDescription = row.createCell(7);
            labelDescription.setCellStyle(style);
            labelDescription.setCellValue(drtInfosFactuModel.getDescription());
            HSSFCell nbNf = row.createCell(8);
            nbNf.setCellStyle(style);
            nbNf.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNf.setCellValue(drtInfosFactuModel.getNf());
            HSSFCell nbNa = row.createCell(9);
            nbNa.setCellStyle(style);
            nbNa.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNa.setCellValue(drtInfosFactuModel.getNa());
            HSSFCell nbNd1 = row.createCell(10);
            nbNd1.setCellStyle(style);
            nbNd1.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNd1.setCellValue(drtInfosFactuModel.getNd1());
            HSSFCell nbNd2 = row.createCell(11);
            nbNd2.setCellStyle(style);
            nbNd2.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNd2.setCellValue(drtInfosFactuModel.getNd2());
            HSSFCell nbNp1 = row.createCell(12);
            nbNp1.setCellStyle(style);
            nbNp1.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNp1.setCellValue(drtInfosFactuModel.getNp1());
            HSSFCell nbNp2 = row.createCell(13);
            nbNp2.setCellStyle(style);
            nbNp2.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNp2.setCellValue(drtInfosFactuModel.getNp2());
            HSSFCell nbNbrJrsEchu = row.createCell(14);
            nbNbrJrsEchu.setCellStyle(jaune);
            nbNbrJrsEchu.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbNbrJrsEchu.setCellValue(drtInfosFactuModel.getNbrJrsEchu());
            HSSFCell nbAboMoisAvance = row.createCell(15);
            nbAboMoisAvance.setCellStyle(cyan);
            nbAboMoisAvance.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbAboMoisAvance.setCellValue(drtInfosFactuModel.getAboMoisAvance());
            HSSFCell nbRate = row.createCell(16);
            nbRate.setCellStyle(style);
            nbRate.setCellType(Cell.CELL_TYPE_NUMERIC);
            nbRate.setCellValue(drtInfosFactuModel.getRate());           
            i+=1;
        }          
        
        
        taille=listDRTInfosFactuModel.size();
        HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);
        
        int j=taille+1;
        for(int x=1;x<j;x++){
        	HSSFRow row = sheet.getRow(x);
        	formulaEvaluator.evaluateFormulaCell(row.getCell(17));
            formulaEvaluator.evaluateFormulaCell(row.getCell(20));
        }
        
        for(int d=1;d<50;d++){
        	HSSFRow row0 = sheet.getRow(d);
        	HSSFCell cell5 = row0.getCell(5);
        	if (cell5.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
	        	if (decimalNombre(cell5.getNumericCellValue()) == 1364){
	        		System.out.println("ajustement ecart 1");
	        		ecart1=d;
	                HSSFCell cell0 = row0.createCell(23);
	                cell0.setCellValue(row0.getCell(15).getNumericCellValue());
	                System.out.println("VALEAUR : "+row0.getCell(15).getNumericCellValue());
	                formulaEvaluator.evaluateFormulaCell(row0.getCell(24));
	                d=50;
	        	}
        	}
        }
        
        for(int a=1;a<50;a++){
        	HSSFRow rowA = sheet.getRow(a);
        	HSSFCell cell5 = rowA.getCell(5);
        	if (cell5.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
	        	if (decimalNombre(cell5.getNumericCellValue()) == 1370){
	        		System.out.println("ajustement ecart 2");
	        		ecart2=a;
	                HSSFCell cell1 = rowA.createCell(16);
	                cell1.setCellValue(200);
	                formulaEvaluator.evaluateFormulaCell(rowA.getCell(24));
	                a=50;
	        	}
        	}
        }
        
        for(int b=1;b<50;b++){
        	HSSFRow rowB = sheet.getRow(b);
        	HSSFCell cell5 = rowB.getCell(5);
        	if (cell5.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
	        	if (decimalNombre(cell5.getNumericCellValue()) == 11420){
	        		System.out.println("ajustement ecart 3");
	        		ecart3=b;
	                HSSFCell cell2 = rowB.createCell(23);
	                cell2.setCellValue(rowB.getCell(15).getNumericCellValue());
	                System.out.println("VALEAUR : "+rowB.getCell(15).getNumericCellValue());
	                formulaEvaluator.evaluateFormulaCell(rowB.getCell(24));
	                b=50;
	        	}
        	}
        }
        
        for(int c=1;c<50;c++){
        	HSSFRow rowC = sheet.getRow(c);
        	HSSFCell cell5 = rowC.getCell(5);
        	if (cell5.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
	        	if (decimalNombre(cell5.getNumericCellValue()) == 11440){
	        		System.out.println("ajustement ecart 4");
	        		ecart4=c;
	                HSSFCell cell3 = rowC.createCell(23);
	                cell3.setCellValue(rowC.getCell(15).getNumericCellValue());
	                System.out.println("VALEAUR : "+rowC.getCell(15).getNumericCellValue());
	                formulaEvaluator.evaluateFormulaCell(rowC.getCell(24));
	                c=50;
	        	}
        	}
        }
        
    
	FileOutputStream fileOut;
	fileOut = new FileOutputStream(chemin+"/"+factReel);
	newChemin=chemin+"/"+factReel;
	repertoire=chemin;
	cheminR=chemin;
	wb.write(fileOut);
	fileOut.close(); 
	System.out.println("Fin remplissage DRT_INFO_FACTU");
    }
    
    public void fillBill_Invoice_Detail(ArrayList<MontantBillInvoiceDetailModel> listDRTMontantBillInvoiceDetail) throws IOException{
    	
    	FileInputStream fileIn= new FileInputStream(new File(newChemin));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("DRT_INFOS_FACTU");     	
    	CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 8);
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	style.setFont(font);
    		//numero du debut de la ligne du tableau BID    
            int i=rowDebut;
            for(MontantBillInvoiceDetailModel drtMontantBIDModel:listDRTMontantBillInvoiceDetail){
            	Double valElmtId=Double.valueOf(drtMontantBIDModel.getElementId());
            	Double valMontant=Double.valueOf(drtMontantBIDModel.getMontant());
            	HSSFRow row = sheet.getRow(i);
            	HSSFCell labelEchu = row.createCell(5);
            	labelEchu.setCellStyle(style);
            	labelEchu.setCellValue(drtMontantBIDModel.getEchu());
            	HSSFCell nbElementId = row.createCell(6);
            	nbElementId.setCellStyle(style);
            	nbElementId.setCellValue(valElmtId);
            	HSSFCell nbMontant = row.createCell(7);
            	nbMontant.setCellStyle(style);
            	nbMontant.setCellValue(valMontant);
                i+=1;
            }
            
            FileOutputStream fileOut;
        	fileOut = new FileOutputStream(newChemin);
        	wb.write(fileOut);
        	fileOut.close(); 
        	  System.out.println("Fin remplissage BILL_INVOICE_FACT");       	
    }        
    
    public void fillSubTypeCode(ArrayList<SubtypeCodeModel> listSubtypeCodeModels) throws IOException{
    	
    	FileInputStream fileIn= new FileInputStream(new File(newChemin));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("DRT_INFOS_FACTU"); 
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);    	
    	HSSFRow rowStyle = sheet.getRow(rowDebut+1);
        HSSFCell cellStylefontViolet = rowStyle.getCell(12);
        HSSFCellStyle stylecellViolet= cellStylefontViolet.getCellStyle();
        HSSFCell cellStylefontJaune = rowStyle.getCell(16);
        HSSFCell cellStylefontCyan = rowStyle.getCell(19);
        jaune= cellStylefontJaune.getCellStyle();
        cyan= cellStylefontCyan.getCellStyle();
            int i=rowDebut;
            for(SubtypeCodeModel subtypeCodeModel:listSubtypeCodeModels){
            	HSSFRow row = sheet.getRow(i);
            	HSSFCell nbSubType = row.createCell(11);
            	nbSubType.setCellValue(subtypeCodeModel.getSubtypeCode());
            	HSSFCell nbSum = row.createCell(12);
            	nbSum.setCellValue(subtypeCodeModel.getSumAmount());
            	nbSum.setCellStyle(stylecellViolet);
                i+=1;
            }         
            
            //remplissage tableau SUBTYPE CODE
//            int size=rowDebut+listSubtypeCodeModels.size();
//            for(int x=rowDebut;x<size;x++){
//            	HSSFRow rowSubTypeCode = sheet.getRow(x);
//            	formulaEvaluator.evaluateFormulaCell(rowSubTypeCode.getCell(14));
//                formulaEvaluator.evaluateFormulaCell(rowSubTypeCode.getCell(15));
//            }
//            
//            //remplissage tableau echu echoir
//            for(int b=rowDebut+1;b<53;b++){
//            	HSSFRow rowEchuEchoir = sheet.getRow(b);
//            	formulaEvaluator.evaluateFormulaCell(rowEchuEchoir.getCell(17));
//                formulaEvaluator.evaluateFormulaCell(rowEchuEchoir.getCell(18));
//                formulaEvaluator.evaluateFormulaCell(rowEchuEchoir.getCell(20));
//                formulaEvaluator.evaluateFormulaCell(rowEchuEchoir.getCell(21));
//            }
            
            formulaEvaluator.evaluateAllFormulaCells(wb);
            
            //verification des ecarts echu echoir                        
            //ajustement ecart 1
            for(int b=rowDebut+1;b<60;b++){
            	HSSFRow rowA = sheet.getRow(b);
            	HSSFCell cell5 = rowA.getCell(16);
            	if(cell5.getCellType()== HSSFCell.CELL_TYPE_NUMERIC){
	            	if (decimalNombre(cell5.getNumericCellValue()) == 1370){
	            		System.out.println("ajustement ecart ");
	                    formulaEvaluator.evaluateFormulaCell(rowA.getCell(22));
	                    b=60;
	            	}
            	}
            }
            
            //ajustement ecart 2
            for(int c=rowDebut+1;c<60;c++){
            	HSSFRow rowB = sheet.getRow(c);
            	HSSFCell cell5 = rowB.getCell(16);            	
            	if(cell5.getCellType()== HSSFCell.CELL_TYPE_NUMERIC){
	            	if (decimalNombre(cell5.getNumericCellValue()) == 11420){
	            		HSSFRow rowEcart = sheet.getRow((int)ecart3);
	            		System.out.println("ajustement ecart ");
	            		if(decimalNombre(rowB.getCell(21).getNumericCellValue())<0){	            			
	            			double valeur=decimalNombre(rowEcart.getCell(15).getNumericCellValue());
	            			System.out.println("Ancienne valeur : "+valeur);
	            			HSSFCell cellEcart = rowEcart.createCell(15);
	            			cellEcart.setCellValue(valeur-1);
	            			cellEcart.setCellStyle(cyan);
	            			formulaEvaluator.evaluateAllFormulaCells(wb);
	            			while(decimalNombre(rowB.getCell(21).getNumericCellValue())<0){
	                			valeur=decimalNombre(rowEcart.getCell(15).getNumericCellValue());
	                			cellEcart.setCellValue(valeur-1);
	                			cellEcart.setCellStyle(cyan);
	                			formulaEvaluator.evaluateAllFormulaCells(wb);
	            			}
	            		}else if(decimalNombre(rowB.getCell(21).getNumericCellValue())>0){
	            			double valeur=decimalNombre(rowEcart.getCell(15).getNumericCellValue());
	            			HSSFCell cellEcart = rowEcart.createCell(15);
	            			cellEcart.setCellValue(valeur+1);
	            			cellEcart.setCellStyle(cyan);
	            			formulaEvaluator.evaluateAllFormulaCells(wb);
	            			while(decimalNombre(rowB.getCell(21).getNumericCellValue())<0){
	                			valeur=decimalNombre(rowEcart.getCell(15).getNumericCellValue());
	                			cellEcart.setCellValue(valeur+1);
	                			cellEcart.setCellStyle(cyan);
	                			formulaEvaluator.evaluateAllFormulaCells(wb);
	            			}
	            		}
	                    c=60;
	                    formulaEvaluator.evaluateAllFormulaCells(wb);
	                    System.out.println("VALEUR CELULLE "+rowEcart.getCell(20).getNumericCellValue());
	            	}
            	}
            }
            
          //ajustement ecart 3
            for(int c=rowDebut+1;c<60;c++){
            	HSSFRow rowC = sheet.getRow(c);
            	HSSFCell cell5 = rowC.getCell(16);           	
            	if(cell5.getCellType()== HSSFCell.CELL_TYPE_NUMERIC){
	            	if (decimalNombre(cell5.getNumericCellValue()) == 11440){
            			HSSFRow rowEcart = sheet.getRow((int)ecart4);
	            		System.out.println("ajustement ecart ");
	            		if(decimalNombre(rowC.getCell(21).getNumericCellValue())<0){
	            			double valeur=decimalNombre(rowEcart.getCell(15).getNumericCellValue());
	            			HSSFCell cellEcart = rowEcart.createCell(15);
	            			cellEcart.setCellValue(valeur-1);
	            			cellEcart.setCellStyle(cyan);
	            			formulaEvaluator.evaluateAllFormulaCells(wb);
	            			while(decimalNombre(rowC.getCell(21).getNumericCellValue())<0){
	                			valeur=decimalNombre(rowEcart.getCell(15).getNumericCellValue());
	                			cellEcart.setCellValue(valeur-1);
	                			cellEcart.setCellStyle(cyan);
	                			formulaEvaluator.evaluateAllFormulaCells(wb);
	            			}
	            		}else if(decimalNombre(rowC.getCell(21).getNumericCellValue())>0){
	            			double valeur=decimalNombre(rowEcart.getCell(15).getNumericCellValue());
	            			HSSFCell cellEcart = rowEcart.createCell(15);
	            			cellEcart.setCellValue(valeur+1);
	            			cellEcart.setCellStyle(cyan);
	            			formulaEvaluator.evaluateAllFormulaCells(wb);
	            			while(decimalNombre(rowC.getCell(21).getNumericCellValue())<0){
	                			valeur=decimalNombre(rowEcart.getCell(15).getNumericCellValue());
	                			cellEcart.setCellValue(valeur+1);
	                			cellEcart.setCellStyle(cyan);
	                			formulaEvaluator.evaluateAllFormulaCells(wb);
	            			}
	            		}
	                    c=60;
	                    formulaEvaluator.evaluateAllFormulaCells(wb);
	            	}
            	}
            } 
                       
            
            FileOutputStream fileOut;
        	fileOut = new FileOutputStream(newChemin);
        	wb.write(fileOut);
        	fileOut.close(); 
        	  System.out.println("Fin remplissage SUBTYPE CODE");       	
    }  
    
    public ArrayList<ElementIDModel> elmtInfoFactuIntoInputs() throws IOException{
    	ArrayList<ElementIDModel> listElementIDModels= new ArrayList<ElementIDModel>();
    	FileInputStream fileIn= new FileInputStream(new File(newChemin));
    	HSSFWorkbook wb = new HSSFWorkbook(fileIn);
    	HSSFSheet sheet = wb.getSheet("DRT_INFOS_FACTU");  
            for(int i=1;i<27;i++){
            	ElementIDModel eIdm=new ElementIDModel();
            	HSSFRow row = sheet.getRow(i);
            	HSSFCell nbElmtIdArrears = row.getCell(5);
            	eIdm.setElmtIdArrears((int) nbElmtIdArrears.getNumericCellValue());
            	HSSFCell nbElmtIdAvance = row.getCell(6);
            	eIdm.setElmtIdAvance((int) nbElmtIdAvance.getNumericCellValue());
            	HSSFCell nbJrsEchu = row.getCell(14);
            	eIdm.setNbrJrsEchu((int) nbJrsEchu.getNumericCellValue());
            	HSSFCell nbAboMoisAvance = row.getCell(15);
            	eIdm.setAboMoisAvance((int) nbAboMoisAvance.getNumericCellValue());           	
            	listElementIDModels.add(eIdm);
            }         
            return listElementIDModels; 	
            
    } 
    
	public void ongletInputs(int nbrJour,String numFact,String mois,String annee,String fileName) throws IOException{
		this.fileName=fileName;
		FileInputStream fileIn= new FileInputStream(new File(path+"/DARTY_Facture.xls"));
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
	    String titre="FACTURATION DARTY – DU 1er AU ";
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
	
	public void ongletAbonnements(String fromDate,String toDate,String nextFromDate,String nextToDate,String fileName,ArrayList<ElementIDModel>listEIdModels) throws IOException{
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
    	HSSFRow rowStyle = sheet.getRow(3);
        HSSFCell cellStylefontJaune = rowStyle.getCell(12);
        HSSFCell cellStylefontCyan = rowStyle.getCell(13);
        HSSFCellStyle stylecellJaune= cellStylefontJaune.getCellStyle();
        HSSFCellStyle stylecellCyan= cellStylefontCyan.getCellStyle();
    	int i=3;
		    for(ElementIDModel eIdm:listEIdModels){
		    		HSSFRow row = sheet.getRow(i);
		    		HSSFCell lbDate = row.createCell(12);
		    		lbDate.setCellStyle(stylecellJaune);
		    		lbDate.setCellValue(eIdm.getElmtIdArrears());
		            HSSFCell nbJour = row.createCell(13);
		            nbJour.setCellStyle(stylecellCyan);
		            nbJour.setCellValue(eIdm.getElmtIdAvance());
		            HSSFCell lbJour = row.createCell(14);
		            lbJour.setCellStyle(stylecellJaune);
		            lbJour.setCellValue(eIdm.getNbrJrsEchu());
		            HSSFCell nbPrixAbo= row.createCell(15);
		            nbPrixAbo.setCellStyle(stylecellCyan);
		            nbPrixAbo.setCellValue(eIdm.getAboMoisAvance());  	            
		            i+=1;
	    }
		//Tableau Echeances
        HSSFRow rowEchu = sheet.getRow(2);
        HSSFCell dateEchu = rowEchu.createCell(4);
        dateEchu.setCellStyle(style);
        dateEchu.setCellValue("Du "+fromDate+" au "+toDate);
        HSSFRow rowEchoir = sheet.getRow(3);
        HSSFCell dateEchoir = rowEchoir.createCell(4);
        dateEchoir.setCellStyle(style);
        dateEchoir.setCellValue("Du "+nextFromDate+" au "+nextToDate);
        
      //remplissage tableau charge reccurentes
        //colonne date
//        for(int b=4;b<50;b++){
//        	HSSFRow rowEchuEchoir = sheet.getRow(b);
//        	formulaEvaluator.evaluateFormulaCell(rowEchuEchoir.getCell(4));
//        }
//        //colonne nbr de jour
//        for(int b=2;b<50;b++){
//        	HSSFRow rownbrJours = sheet.getRow(b);
//        	formulaEvaluator.evaluateFormulaCell(rownbrJours.getCell(5));
//        }
//        //colonne montant
//        for(int b=2;b<51;b++){
//        	HSSFRow rowMontant = sheet.getRow(b);
//        	formulaEvaluator.evaluateFormulaCell(rowMontant.getCell(8));
//        }
		
        formulaEvaluator.evaluateAllFormulaCells(wb);
        
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	    
        System.out.println("FIN modification onglet Abonnements");
        
    }
	
	public void ongletNRC(ArrayList<OngletNrcModel> listNrcDarty) throws IOException{
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
	    int i=5;
	    for(OngletNrcModel nnm:listNrcDarty){
	    	formatDate=new FormatDate();
	    	HSSFRow row = sheet.getRow(i);
	    		HSSFCell lbTracking = row.createCell(1);
	    		lbTracking.setCellStyle(style);
	    		lbTracking.setCellValue(nnm.getTrackingId());
	            HSSFCell nbXID = row.createCell(2);
	            nbXID.setCellStyle(style);
	            nbXID.setCellValue(nnm.getxID());
	            HSSFCell lbIdContrat = row.createCell(3);
	            lbIdContrat.setCellStyle(style);
	            lbIdContrat.setCellValue(nnm.getiDContrat());
	            HSSFCell lbOffre= row.createCell(4);
	            lbOffre.setCellStyle(style);
	            lbOffre.setCellValue(nnm.getOffre());  
	            HSSFCell lbDtActiveServ = row.createCell(5);
	            lbDtActiveServ.setCellStyle(style);
	            lbDtActiveServ.setCellValue(formatDate.getFormatDate(nnm.getDateActivationService()));
	            HSSFCell lbTypeNRC = row.createCell(6);
	            lbTypeNRC.setCellStyle(style);
	            lbTypeNRC.setCellValue(nnm.getTypeIdNrc());
	            HSSFCell lbFact = row.createCell(7);
	            lbFact.setCellStyle(style);
	            lbFact.setCellValue(nnm.getLibelleFacturation());
	            HSSFCell lbEffectDt= row.createCell(8);
	            lbEffectDt.setCellStyle(style);
	            lbEffectDt.setCellValue(formatDate.getFormatDateDetailWithoutSecond(nnm.getEffectiveDate()));  
	            HSSFCell lbMontat = row.createCell(9);
	            lbMontat.setCellStyle(style);
	            lbMontat.setCellValue(nnm.getMontant());			            
	    	i+=1;	
	    }

	    formulaEvaluator.evaluateAllFormulaCells(wb);
	    
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	
        System.out.println("FIN modification onglet NRC");
	}
	
	public void ongletUsage(ArrayList<OngletUsageModelDarty> listUsageDarty) throws IOException{
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
	    int i=7;
	    
	    for(OngletUsageModelDarty num:listUsageDarty){	    	
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
            HSSFCell nbLignFact = row.createCell(5);
            nbLignFact.setCellStyle(style);
            nbLignFact.setCellValue(num.getNumLignFact());
            HSSFCell nbEurosHT = row.createCell(6);
            nbEurosHT.setCellStyle(style);
            nbEurosHT.setCellValue(num.getEuroHT());
            HSSFCell nbRemise = row.createCell(7);
            nbRemise.setCellStyle(style);
            nbRemise.setCellValue(num.getRemise());
            HSSFCell nbHtRemise = row.createCell(8);
            nbHtRemise.setCellStyle(style);
            nbHtRemise.setCellValue(num.gethTRemise());
            HSSFCell nbAppels= row.createCell(9);
            nbAppels.setCellStyle(style);
            nbAppels.setCellValue(num.getNbrAppels());  
            HSSFCell nbDureeAppelFactSecUt = row.createCell(10);
            nbDureeAppelFactSecUt.setCellStyle(style);
            nbDureeAppelFactSecUt.setCellValue(num.getDureeSecOuUt());
            HSSFCell nbDureeAppelFactSec = row.createCell(11);
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
//    		//ROW 1
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
//	    	//ROW 4
//	    	HSSFRow row4 = sheet.getRow(4);
//	    	formulaEvaluator.evaluateFormulaCell(row4.getCell(2));	
//	    	formulaEvaluator.evaluateFormulaCell(row4.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row4.getCell(4));
//	    	
//	    	//ROW 5
//	    	HSSFRow row5 = sheet.getRow(5);
//	    	formulaEvaluator.evaluateFormulaCell(row5.getCell(2));	
//	    	formulaEvaluator.evaluateFormulaCell(row5.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row5.getCell(4));
//	    	
//	    	//ROW 6
//	    	HSSFRow row6 = sheet.getRow(6);	
//	    	formulaEvaluator.evaluateFormulaCell(row6.getCell(2));
//	    	formulaEvaluator.evaluateFormulaCell(row6.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row6.getCell(4));
//	    	
//	    	//ROW 7
//	    	HSSFRow row7 = sheet.getRow(7);
//	    	formulaEvaluator.evaluateFormulaCell(row7.getCell(2));	
//	    	formulaEvaluator.evaluateFormulaCell(row7.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row7.getCell(4));
//	    	
//	    	//ROW 8
//	    	HSSFRow row8 = sheet.getRow(8);
//	    	formulaEvaluator.evaluateFormulaCell(row8.getCell(2));	
//	    	formulaEvaluator.evaluateFormulaCell(row8.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row8.getCell(4));
//	    	
//	    	//ROW 9
//	    	HSSFRow row9 = sheet.getRow(9);	
//	    	formulaEvaluator.evaluateFormulaCell(row9.getCell(3));
//	    	
//	    	//ROW 11
//	    	HSSFRow row11 = sheet.getRow(11);
//	    	formulaEvaluator.evaluateFormulaCell(row11.getCell(2));	
//	    	formulaEvaluator.evaluateFormulaCell(row11.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row11.getCell(4));
//	    	
//	    	//ROW 12
//	    	HSSFRow row12 = sheet.getRow(12);
//	    	formulaEvaluator.evaluateFormulaCell(row12.getCell(3));
//	    	
//	    	//ROW 14
//	    	HSSFRow row14 = sheet.getRow(14);
//	    	formulaEvaluator.evaluateFormulaCell(row14.getCell(3));
//	    	formulaEvaluator.evaluateFormulaCell(row14.getCell(4));
    	
    	formulaEvaluator.evaluateAllFormulaCells(wb);
	    		
	    FileOutputStream fileOut;
    	fileOut = new FileOutputStream(repertoire);
    	wb.write(fileOut);
    	fileOut.close(); 	
        System.out.println("FIN modification onglet Récapitulatif");
	}
	
	public void ongletRecapFacture(String numFact,String date) throws IOException{
		FileInputStream fileIn= new FileInputStream(new File(repertoire));  		  		    	
		HSSFWorkbook wb = new HSSFWorkbook(fileIn);  
		HSSFSheet sheet = wb.getSheet("Récap facture"); 
		CellStyle style=wb.createCellStyle();
    	font=wb.createFont();
    	font.setFontHeightInPoints((short) 10);
    	style.setAlignment(CellStyle.ALIGN_RIGHT);
    	style.setFont(font);
    	CellStyle dateStyle = wb.createCellStyle();
    	HSSFCreationHelper createHelper = wb.getCreationHelper();
    	dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
    	dateStyle.setFont(font);
   
		   	
    	HSSFFormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator(sheet, wb);
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
    	HSSFRow rowNumFact = sheet.getRow(23);
		HSSFCell numeroFact = rowNumFact.createCell(6);
		numeroFact.setCellValue(Integer.valueOf(numFact)+"/1");
		numeroFact.setCellStyle(style);
		//Date facture
		HSSFRow rowDateFact = sheet.getRow(24);
		HSSFCell dateFact = rowDateFact.createCell(6);
		dateFact.setCellValue(maDate);
		dateFact.setCellStyle(dateStyle);
		   	
	    //remplissage des cellules.		
		//ROW 34
//    	HSSFRow row34 = sheet.getRow(34);
//    	formulaEvaluator.evaluateFormulaCell(row34.getCell(4));	
//    	formulaEvaluator.evaluateFormulaCell(row34.getCell(5));
//    	formulaEvaluator.evaluateFormulaCell(row34.getCell(6));
//    	
//    	//ROW 35
//    	HSSFRow row35 = sheet.getRow(35);
//    	formulaEvaluator.evaluateFormulaCell(row35.getCell(4));	
//    	formulaEvaluator.evaluateFormulaCell(row35.getCell(5));
//    	formulaEvaluator.evaluateFormulaCell(row35.getCell(6));
//    	
//    	//ROW 36
//    	HSSFRow row36 = sheet.getRow(36);
//    	formulaEvaluator.evaluateFormulaCell(row36.getCell(4));	
//    	formulaEvaluator.evaluateFormulaCell(row36.getCell(5));
//    	formulaEvaluator.evaluateFormulaCell(row36.getCell(6));
//    	
//    	//ROW 37
//    	HSSFRow row37 = sheet.getRow(37);
//    	formulaEvaluator.evaluateFormulaCell(row37.getCell(4));	
//    	formulaEvaluator.evaluateFormulaCell(row37.getCell(5));
//    	formulaEvaluator.evaluateFormulaCell(row37.getCell(6));
//    	
//    	//ROW 38
//    	HSSFRow row38 = sheet.getRow(38);	
//    	formulaEvaluator.evaluateFormulaCell(row38.getCell(4));
//    	formulaEvaluator.evaluateFormulaCell(row38.getCell(5));
//    	formulaEvaluator.evaluateFormulaCell(row38.getCell(6));
//    	
//    	//ROW 39
//    	HSSFRow row39 = sheet.getRow(39);	
//    	formulaEvaluator.evaluateFormulaCell(row39.getCell(4));
//    	formulaEvaluator.evaluateFormulaCell(row39.getCell(5));
//    	formulaEvaluator.evaluateFormulaCell(row38.getCell(6));
//    	
//    	//ROW 40
//    	HSSFRow row40 = sheet.getRow(40);
//    	formulaEvaluator.evaluateFormulaCell(row40.getCell(4));	
//    	formulaEvaluator.evaluateFormulaCell(row40.getCell(5));
//    	formulaEvaluator.evaluateFormulaCell(row40.getCell(6));
//    	
//    	//ROW 41
//    	HSSFRow row41 = sheet.getRow(41);
//    	formulaEvaluator.evaluateFormulaCell(row41.getCell(4));	
//    	formulaEvaluator.evaluateFormulaCell(row41.getCell(5));
//    	formulaEvaluator.evaluateFormulaCell(row41.getCell(6));
//    	
//    	//ROW 42
//    	HSSFRow row42 = sheet.getRow(42);
//    	formulaEvaluator.evaluateFormulaCell(row42.getCell(4));	
//    	formulaEvaluator.evaluateFormulaCell(row42.getCell(5));
//    	formulaEvaluator.evaluateFormulaCell(row42.getCell(6));
//    	
//    	//ROW 45
//    	HSSFRow row45 = sheet.getRow(45);
//    	formulaEvaluator.evaluateFormulaCell(row45.getCell(6));
//    	
//    	//ROW 47
//    	HSSFRow row47 = sheet.getRow(47);
//    	formulaEvaluator.evaluateFormulaCell(row47.getCell(6));
//    	
//    	//ROW 49
//    	HSSFRow row49 = sheet.getRow(49);
//    	formulaEvaluator.evaluateFormulaCell(row49.getCell(6));
//    	
//    	//ROW 69
//    	HSSFRow row69 = sheet.getRow(69);
//    	formulaEvaluator.evaluateFormulaCell(row69.getCell(6));
//    	
//    	//ROW 70
//    	HSSFRow row70 = sheet.getRow(70);
//    	formulaEvaluator.evaluateFormulaCell(row70.getCell(6));
//    	
//    	//ROW 71
//    	HSSFRow row71 = sheet.getRow(71);
//    	formulaEvaluator.evaluateFormulaCell(row71.getCell(6));
//    	
//    	//ROW 72
//    	HSSFRow row72 = sheet.getRow(72);
//    	formulaEvaluator.evaluateFormulaCell(row72.getCell(6));
    	
    	formulaEvaluator.evaluateAllFormulaCells(wb);
	    		
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
