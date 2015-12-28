package factu_mb_param.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.modele.OngletNrcModel;
import factu_mb_param.modele.OngletUsageModelMTVC;
import factu_mb_param.modele.ReadFile;
import factu_mb_param.modele.TempInfosFactuModel;



public class LeCableDao {
	
	public TempInfosFactuModel tempInfosFactuModel;
	private MontantBillInvoiceDetailModel montantBillInvoiceDetailModel;
	private String userid = "arbor";
    private String password = "arbor123";
    private String pilote = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@10.1.3.136:1522:custnc";  

	private static Connection con;

     // constructor 
	public LeCableDao(){
		this.tempInfosFactuModel= new TempInfosFactuModel();
		this.montantBillInvoiceDetailModel=new MontantBillInvoiceDetailModel();
	}

	public Connection getConnection(){
				
		try {
			Class.forName(pilote);	

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(url, userid, password);
			System.out.println ("Ouverture Connection");     
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		
		return con;
	}
	
	static  public  void  closeConnection(){         
		System.out.println ("Close DBConnection.");          
		        
				
		try {
			con.close();             
			System.out.println ("Fermeture Connection");        
			}         
		catch (Exception ex) {             
			System.out.println ("ERROR :"+ex.getMessage());             
			ex.printStackTrace();        
			}    
		} 
	
	
	public ArrayList<TempInfosFactuModel> getLC_INFO_FATU(String numFact){
		getConnection();
		ArrayList<TempInfosFactuModel> listOMTInfosFactuModel=new ArrayList<TempInfosFactuModel>();
		try{
			String sql="SELECT * FROM LC_INFOS_FACTU "
					+"WHERE ref_facture = "+numFact;
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				tempInfosFactuModel=new TempInfosFactuModel();
				tempInfosFactuModel.setDate(rs.getString("DATE_TRT"));
				tempInfosFactuModel.setRunId(rs.getInt("RUN_ID"));
				tempInfosFactuModel.setMois(rs.getString("MOIS"));
				tempInfosFactuModel.setNbrJrMois(rs.getInt("NBRE_JRS_MOIS"));
				tempInfosFactuModel.setRefFacture(rs.getInt("REF_FACTURE"));
				tempInfosFactuModel.setElmtIdArrears(rs.getInt("ELEMENT_ID_ARREARS"));
				tempInfosFactuModel.setElmtIdAvance(rs.getInt("ELEMENT_ID_AVANCE"));
				tempInfosFactuModel.setDescription(rs.getString("DESCRIPTION"));
				tempInfosFactuModel.setNf(rs.getInt("NF"));
				tempInfosFactuModel.setNa(rs.getInt("NA"));
				tempInfosFactuModel.setNd1(rs.getInt("ND1"));
				tempInfosFactuModel.setNd2(rs.getInt("ND2"));
				tempInfosFactuModel.setNp1(rs.getInt("NP1"));
				tempInfosFactuModel.setNp2(rs.getInt("NP2"));
				tempInfosFactuModel.setNbrJrsEchu(rs.getInt("NBRE_JRS_ECHU"));
				tempInfosFactuModel.setAboMoisAvance(rs.getInt("ABO_MOIS_AVANCE"));
				tempInfosFactuModel.setRate(rs.getInt("RATE"));
				listOMTInfosFactuModel.add(tempInfosFactuModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listOMTInfosFactuModel;
	}
	
	public ArrayList<MontantBillInvoiceDetailModel> getMontantBillInvoiceDetailEchu(String numFact){
		getConnection();
		ArrayList<MontantBillInvoiceDetailModel> listMontantBillInvoiceDetailEchu=new ArrayList<MontantBillInvoiceDetailModel>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getBIDRequestLeCableEchu(numFact);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				montantBillInvoiceDetailModel=new MontantBillInvoiceDetailModel();
				montantBillInvoiceDetailModel.setEchu(rs.getString("'échu'"));
				montantBillInvoiceDetailModel.setElementId(rs.getString("ELEMENT_ID"));
				montantBillInvoiceDetailModel.setMontant(rs.getString("MONTANT"));
				listMontantBillInvoiceDetailEchu.add(montantBillInvoiceDetailModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listMontantBillInvoiceDetailEchu;
	}
	
	public ArrayList<MontantBillInvoiceDetailModel> getMontantBillInvoiceDetailEchoir(String numFact){
		getConnection();
		ArrayList<MontantBillInvoiceDetailModel> listMontantBillInvoiceDetailEchoir=new ArrayList<MontantBillInvoiceDetailModel>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getBIDRequestLeCableEchoir(numFact);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				montantBillInvoiceDetailModel=new MontantBillInvoiceDetailModel();
				montantBillInvoiceDetailModel.setEchu(rs.getString("'échoir'"));
				montantBillInvoiceDetailModel.setElementId(rs.getString("ELEMENT_ID"));
				montantBillInvoiceDetailModel.setMontant(rs.getString("MONTANT"));
				listMontantBillInvoiceDetailEchoir.add(montantBillInvoiceDetailModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listMontantBillInvoiceDetailEchoir;
	}
	
	public ArrayList<OngletNrcModel> getListNRC(String numFact,String fromDate,String toDate){
		getConnection();
		OngletNrcModel ongletNrcModel;
		ArrayList<OngletNrcModel> listNrcOMT=new ArrayList<OngletNrcModel>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getNRCRequestMTVC(numFact, fromDate, toDate);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				ongletNrcModel=new OngletNrcModel();
				ongletNrcModel.setTrackingId(rs.getInt("TRACKING_ID"));
				ongletNrcModel.setxID(rs.getString("XID"));
				ongletNrcModel.setiDContrat(rs.getString("ID_CONTRAT"));
				ongletNrcModel.setOffre(rs.getString("OFFRE"));
				ongletNrcModel.setDateActivationService(rs.getString("DATE_ACTIVATION_SERVICE"));
				ongletNrcModel.setTypeIdNrc(rs.getInt("TYPE_ID_NRC"));
				ongletNrcModel.setLibelleFacturation(rs.getString("LIBELLE_FACTURATION"));
				ongletNrcModel.setEffectiveDate(rs.getString("EFFECTIVE_DATE"));
				ongletNrcModel.setMontant(rs.getDouble("MONTANT"));
				listNrcOMT.add(ongletNrcModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listNrcOMT;
	}
	
	public ArrayList<OngletUsageModelMTVC> getListUsage(String numFact){
		getConnection();
		OngletUsageModelMTVC ongletUsageModelMTVC;
		ArrayList<OngletUsageModelMTVC> listUsageMTVC=new ArrayList<OngletUsageModelMTVC>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getUSAGERequestMTVC(numFact);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				ongletUsageModelMTVC=new OngletUsageModelMTVC();
				ongletUsageModelMTVC.setTypeTrafic(rs.getString("TYPE_TRAFIC"));
				ongletUsageModelMTVC.setClasseJuris(rs.getString("CLASSE_JURIDICTION"));
				ongletUsageModelMTVC.setCodeJuris(rs.getInt("CODE_JURIDICTION"));
				ongletUsageModelMTVC.setLibJuris(rs.getString("LIBELLÉ_JURIDICTION"));
				ongletUsageModelMTVC.setEuroHT(rs.getDouble("EURO_HT"));
				ongletUsageModelMTVC.setRemise(rs.getDouble("REMISE"));
				ongletUsageModelMTVC.sethTRemise(rs.getDouble("HT_REMISÉ"));
				ongletUsageModelMTVC.setNbrAppels(rs.getInt("NBRE_APPELS"));
				ongletUsageModelMTVC.setDureeSecOuUt(rs.getInt("DURÉE_EN_S_OU_EN_UT"));
				ongletUsageModelMTVC.setDureeSec(rs.getInt("DURÉE_EN_S"));
				listUsageMTVC.add(ongletUsageModelMTVC);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listUsageMTVC;
	}

}
	
