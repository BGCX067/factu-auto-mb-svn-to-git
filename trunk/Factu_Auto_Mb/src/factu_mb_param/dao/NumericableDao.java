package factu_mb_param.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import factu_mb_param.modele.OngletNrcModel;
import factu_mb_param.modele.ReadFile;
import factu_mb_param.modele.TempInfosFactuModel;
import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.modele.OngletUsageModel;



public class NumericableDao {
	
	public TempInfosFactuModel ncInfosFactuModel;
	private MontantBillInvoiceDetailModel ncMontantBillInvoiceDetailModel;
	private String userid = "arbor";
    private String password = "arbor123";
    private String pilote = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@10.1.3.136:1522:custnc";  

	private static Connection con;

     // constructor 
	public NumericableDao(){
		this.ncInfosFactuModel= new TempInfosFactuModel();
		this.ncMontantBillInvoiceDetailModel=new MontantBillInvoiceDetailModel();
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
	
	
	public ArrayList<TempInfosFactuModel> getNC_INFO_FATU(String numFact){
		getConnection();
		ArrayList<TempInfosFactuModel> listNcInfosFactuModel=new ArrayList<TempInfosFactuModel>();
		try{
			String sql="SELECT * FROM NC_INFOS_FACTU "
					+"WHERE ref_facture = "+numFact;
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				ncInfosFactuModel=new TempInfosFactuModel();
				ncInfosFactuModel.setDate(rs.getString("DATE_TRT"));
				ncInfosFactuModel.setRunId(rs.getInt("RUN_ID"));
				ncInfosFactuModel.setMois(rs.getString("MOIS"));
				ncInfosFactuModel.setNbrJrMois(rs.getInt("NBRE_JRS_MOIS"));
				ncInfosFactuModel.setRefFacture(rs.getInt("REF_FACTURE"));
				ncInfosFactuModel.setElmtIdArrears(rs.getInt("ELEMENT_ID_ARREARS"));
				ncInfosFactuModel.setElmtIdAvance(rs.getInt("ELEMENT_ID_AVANCE"));
				ncInfosFactuModel.setDescription(rs.getString("DESCRIPTION"));
				ncInfosFactuModel.setNf(rs.getInt("NF"));
				ncInfosFactuModel.setNa(rs.getInt("NA"));
				ncInfosFactuModel.setNd1(rs.getInt("ND1"));
				ncInfosFactuModel.setNd2(rs.getInt("ND2"));
				ncInfosFactuModel.setNp1(rs.getInt("NP1"));
				ncInfosFactuModel.setNp2(rs.getInt("NP2"));
				ncInfosFactuModel.setNbrJrsEchu(rs.getInt("NBRE_JRS_ECHU"));
				ncInfosFactuModel.setAboMoisAvance(rs.getInt("ABO_MOIS_AVANCE"));
				ncInfosFactuModel.setRate(rs.getInt("RATE"));
				listNcInfosFactuModel.add(ncInfosFactuModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listNcInfosFactuModel;
	}
	
	public ArrayList<MontantBillInvoiceDetailModel> getMontantBillInvoiceDetail(String numFact){
		getConnection();
		ArrayList<MontantBillInvoiceDetailModel> listMontantBillInvoiceDetail=new ArrayList<MontantBillInvoiceDetailModel>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getBIDRequestNC(numFact);
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				ncMontantBillInvoiceDetailModel=new MontantBillInvoiceDetailModel();
				ncMontantBillInvoiceDetailModel.setEchu(rs.getString("'échu'"));
				ncMontantBillInvoiceDetailModel.setElementId(rs.getString("ELEMENT_ID"));
				ncMontantBillInvoiceDetailModel.setMontant(rs.getString("MONTANT"));
				listMontantBillInvoiceDetail.add(ncMontantBillInvoiceDetailModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listMontantBillInvoiceDetail;
	}
	
	public ArrayList<OngletNrcModel> getListNRC(String numFact,String fromDate,String toDate){
		getConnection();
		OngletNrcModel ncFasNrcModel;
		ArrayList<OngletNrcModel> listNrcNumericable=new ArrayList<OngletNrcModel>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getNRCRequestNC(numFact, fromDate, toDate);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				ncFasNrcModel=new OngletNrcModel();
				ncFasNrcModel.setTrackingId(rs.getInt("TRACKING_ID"));
				ncFasNrcModel.setxID(rs.getString("XID"));
				ncFasNrcModel.setiDContrat(rs.getString("ID_CONTRAT"));
				ncFasNrcModel.setOffre(rs.getString("OFFRE"));
				ncFasNrcModel.setDateActivationService(rs.getString("DATE_ACTIVATION_SERVICE"));
				ncFasNrcModel.setTypeIdNrc(rs.getInt("TYPE_ID_NRC"));
				ncFasNrcModel.setLibelleFacturation(rs.getString("LIBELLE_FACTURATION"));
				ncFasNrcModel.setEffectiveDate(rs.getString("EFFECTIVE_DATE"));
				ncFasNrcModel.setMontant(rs.getDouble("MONTANT"));
				listNrcNumericable.add(ncFasNrcModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listNrcNumericable;
	}
	
	public ArrayList<OngletUsageModel> getListUsage(String numFact){
		getConnection();
		OngletUsageModel ncUsageNrcModel;
		ArrayList<OngletUsageModel> listUsageNumericable=new ArrayList<OngletUsageModel>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getUSAGERequestNC(numFact);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				ncUsageNrcModel=new OngletUsageModel();
				ncUsageNrcModel.setTypeTrafic(rs.getString("TYPE_TRAFIC"));
				ncUsageNrcModel.setClasseJuris(rs.getString("CLASSE_JURIDICTION"));
				ncUsageNrcModel.setCodeJuris(rs.getString("CODE_JURIDICTION"));
				ncUsageNrcModel.setLibJuris(rs.getString("LIBELLÉ_JURIDICTION"));
				ncUsageNrcModel.setEuroHT(rs.getDouble("EURO_HT"));
				ncUsageNrcModel.setRemise(rs.getDouble("REMISE"));
				ncUsageNrcModel.sethTRemise(rs.getDouble("HT_REMISÉ"));
				ncUsageNrcModel.setNbrAppels(rs.getInt("NBRE_APPELS"));
				ncUsageNrcModel.setDureeFacture(rs.getInt("DURÉE_FACTURÉE"));
				listUsageNumericable.add(ncUsageNrcModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listUsageNumericable;
	}

}
	
