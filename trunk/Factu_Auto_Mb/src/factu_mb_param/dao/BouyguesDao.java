package factu_mb_param.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.modele.OngletAboDetailModelBYTEL;
import factu_mb_param.modele.OngletNrcModel;
import factu_mb_param.modele.OngletRecoSansFasModelBYTEL;
import factu_mb_param.modele.ReadFile;
import factu_mb_param.modele.TempInfosFactuModel;



public class BouyguesDao {
	
	public TempInfosFactuModel tempInfosFactuModel;
	private MontantBillInvoiceDetailModel montantBillInvoiceDetailModel;
	private String userid = "arbor";
    private String password = "arbor123";
    private String pilote = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@10.1.3.136:1522:custnc";      	

	private static Connection con;

     // constructor 
	public BouyguesDao(){
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
	
			
	public ArrayList<TempInfosFactuModel> getBT_INFO_FATU(String numFact){
		getConnection();
		ArrayList<TempInfosFactuModel> listOMTInfosFactuModel=new ArrayList<TempInfosFactuModel>();
		try{
			String sql="SELECT * FROM BT_INFOS_FACTU "
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
			String sql=readFile.getBIDRequestBYTELEchu(numFact);
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
			String sql=readFile.getBIDRequestBYTELEchoir(numFact);
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
		ArrayList<OngletNrcModel> listNrcBYTEL=new ArrayList<OngletNrcModel>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getNRCRequestBYTEL(numFact, fromDate, toDate);
			// Create a prepared statement
			System.out.println("debut traitement");
			System.out.println("SQL = "+sql);
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			System.out.println("fin traitement");
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
				listNrcBYTEL.add(ongletNrcModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listNrcBYTEL;
	}
	
	public ArrayList<OngletAboDetailModelBYTEL> getListAboDetail(String toDate){
		getConnection();
		OngletAboDetailModelBYTEL ongletAboDetailModelBYTEL;
		ArrayList<OngletAboDetailModelBYTEL> listAboDetailBYTEL=new ArrayList<OngletAboDetailModelBYTEL>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getAboDetailRequestBYTEL(toDate);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				ongletAboDetailModelBYTEL=new OngletAboDetailModelBYTEL();
				ongletAboDetailModelBYTEL.setId_client(rs.getString("Identifiant client"));
				ongletAboDetailModelBYTEL.setType_abo(rs.getString("Type d'abonnement"));
				ongletAboDetailModelBYTEL.setActive_dt(rs.getString("Date d'activation abonnement"));
				ongletAboDetailModelBYTEL.setInactive_dt(rs.getString("Date d'inactivation abonnement"));
				listAboDetailBYTEL.add(ongletAboDetailModelBYTEL);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listAboDetailBYTEL;
	}
	
	public ArrayList<OngletRecoSansFasModelBYTEL> getListRecoSansFas(String fromDate,String toDate){
		getConnection();
		OngletRecoSansFasModelBYTEL ongletRecoSansFasModelBYTEL;
		ArrayList<OngletRecoSansFasModelBYTEL> listNrcBYTEL=new ArrayList<OngletRecoSansFasModelBYTEL>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getRecoSansFasRequestBYTEL(fromDate, toDate);
			System.out.println("chaine : "+sql);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				ongletRecoSansFasModelBYTEL=new OngletRecoSansFasModelBYTEL();
				ongletRecoSansFasModelBYTEL.setExternalId(rs.getString("EXTERNAL_ID"));
				ongletRecoSansFasModelBYTEL.setParentSubscrNo(rs.getString("PARENT_SUBSCR_NO"));
				ongletRecoSansFasModelBYTEL.setTrackingId(rs.getString("TRACKING_ID"));
				ongletRecoSansFasModelBYTEL.setTrackingIdServ(rs.getString("TRACKING_ID_SERV"));
				listNrcBYTEL.add(ongletRecoSansFasModelBYTEL);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listNrcBYTEL;
	}
	
}
	
