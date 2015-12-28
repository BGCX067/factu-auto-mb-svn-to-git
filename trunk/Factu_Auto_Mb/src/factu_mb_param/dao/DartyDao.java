package factu_mb_param.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import factu_mb_param.modele.AvoirAncienAboModel;
import factu_mb_param.modele.AvoirNouvelAboModel;
import factu_mb_param.modele.MontantBillInvoiceDetailModel;
import factu_mb_param.modele.OngletNrcModel;
import factu_mb_param.modele.OngletUsageModelDarty;
import factu_mb_param.modele.ReadFile;
import factu_mb_param.modele.SubtypeCodeModel;
import factu_mb_param.modele.TempInfosFactuModel;



public class DartyDao {
	
	public TempInfosFactuModel drtInfosFactuModel;
	private MontantBillInvoiceDetailModel drtMontantBillInvoiceDetailModel;
	private String userid = "arbor";
    private String password = "arbor123";
    private String pilote = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@10.1.3.136:1522:cust3p";
    private SubtypeCodeModel subtypeCodeModel;

	private static Connection con;

     // constructor 
	public DartyDao(){
		this.drtInfosFactuModel= new TempInfosFactuModel();
		this.drtMontantBillInvoiceDetailModel=new MontantBillInvoiceDetailModel();
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
	
	
	public ArrayList<TempInfosFactuModel> getDRT_INFO_FATU(String numFact){
		getConnection();
		ArrayList<TempInfosFactuModel> listDRTInfosFactuModel=new ArrayList<TempInfosFactuModel>();
		try{
			String sql="SELECT * FROM DRT_INFOS_FACTU "
					+"WHERE ref_facture = "+numFact;
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				drtInfosFactuModel=new TempInfosFactuModel();
				drtInfosFactuModel.setDate(rs.getString("DATE_TRT"));
				drtInfosFactuModel.setRunId(rs.getInt("RUN_ID"));
				drtInfosFactuModel.setMois(rs.getString("MOIS"));
				drtInfosFactuModel.setNbrJrMois(rs.getInt("NBRE_JRS_MOIS"));
				drtInfosFactuModel.setRefFacture(rs.getInt("REF_FACTURE"));
				drtInfosFactuModel.setElmtIdArrears(rs.getInt("ELEMENT_ID_ARREARS"));
				drtInfosFactuModel.setElmtIdAvance(rs.getInt("ELEMENT_ID_AVANCE"));
				drtInfosFactuModel.setDescription(rs.getString("DESCRIPTION"));
				drtInfosFactuModel.setNf(rs.getInt("NF"));
				drtInfosFactuModel.setNa(rs.getInt("NA"));
				drtInfosFactuModel.setNd1(rs.getInt("ND1"));
				drtInfosFactuModel.setNd2(rs.getInt("ND2"));
				drtInfosFactuModel.setNp1(rs.getInt("NP1"));
				drtInfosFactuModel.setNp2(rs.getInt("NP2"));
				drtInfosFactuModel.setNbrJrsEchu(rs.getInt("NBRE_JRS_ECHU"));
				drtInfosFactuModel.setAboMoisAvance(rs.getInt("ABO_MOIS_AVANCE"));
				drtInfosFactuModel.setRate(rs.getInt("RATE"));
				listDRTInfosFactuModel.add(drtInfosFactuModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listDRTInfosFactuModel;
	}
	
	public ArrayList<MontantBillInvoiceDetailModel> getMontantBillInvoiceDetail(String numFact,String fromDate, String toDate){
		getConnection();
		ArrayList<MontantBillInvoiceDetailModel> listMontantBillInvoiceDetail=new ArrayList<MontantBillInvoiceDetailModel>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getBIDRequestDARTY(numFact, fromDate, toDate);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				drtMontantBillInvoiceDetailModel=new MontantBillInvoiceDetailModel();
				drtMontantBillInvoiceDetailModel.setEchu(rs.getString("'échu'"));
				drtMontantBillInvoiceDetailModel.setElementId(rs.getString("ELEMENT_ID"));
				drtMontantBillInvoiceDetailModel.setMontant(rs.getString("MONTANT"));
				listMontantBillInvoiceDetail.add(drtMontantBillInvoiceDetailModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listMontantBillInvoiceDetail;
	}
	
	public ArrayList<SubtypeCodeModel> getListSubtypeCode(String numFact){
		getConnection();
		ArrayList<SubtypeCodeModel> listSubtypeCodeModels= new ArrayList<SubtypeCodeModel>();
		
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getSubtypeCodeRequestDARTY(numFact);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				subtypeCodeModel= new SubtypeCodeModel();
				subtypeCodeModel.setSubtypeCode(rs.getInt("subtype_code"));
				subtypeCodeModel.setSumAmount(rs.getInt("SUM(amount)"));
				listSubtypeCodeModels.add(subtypeCodeModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}		
		return listSubtypeCodeModels;		
	}
	
	public ArrayList<OngletNrcModel> getListNRC(String numFact,String fromDate,String toDate){
		getConnection();
		OngletNrcModel drtNrcModel;
		ArrayList<OngletNrcModel> listNrcDarty=new ArrayList<OngletNrcModel>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getNRCRequestDARTY(numFact, fromDate, toDate);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				drtNrcModel=new OngletNrcModel();
				drtNrcModel.setTrackingId(rs.getInt("TRACKING_ID"));
				drtNrcModel.setxID(rs.getString("XID"));
				drtNrcModel.setiDContrat(rs.getString("ID_CONTRAT"));
				drtNrcModel.setOffre(rs.getString("OFFRE"));
				drtNrcModel.setDateActivationService(rs.getString("DATE_ACTIVATION_SERVICE"));
				drtNrcModel.setTypeIdNrc(rs.getInt("TYPE_ID_NRC"));
				drtNrcModel.setLibelleFacturation(rs.getString("LIBELLE_FACTURATION"));
				drtNrcModel.setEffectiveDate(rs.getString("EFFECTIVE_DATE"));
				drtNrcModel.setMontant(rs.getDouble("MONTANT"));
				listNrcDarty.add(drtNrcModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listNrcDarty;
	}
	
	public ArrayList<OngletUsageModelDarty> getListUsage(String numFact){
		getConnection();
		OngletUsageModelDarty drtUsageNrcModel;
		ArrayList<OngletUsageModelDarty> listUsageDrt=new ArrayList<OngletUsageModelDarty>();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.getUSAGERequestDARTY(numFact);
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				drtUsageNrcModel=new OngletUsageModelDarty();
				drtUsageNrcModel.setTypeTrafic(rs.getString("TYPE_TRAFIC"));
				drtUsageNrcModel.setClasseJuris(rs.getString("CLASSE_JURIDICTION"));
				drtUsageNrcModel.setCodeJuris(rs.getInt("CODE_JURIDICTION"));
				drtUsageNrcModel.setLibJuris(rs.getString("LIBELLÉ_JURIDICTION"));
				drtUsageNrcModel.setNumLignFact(rs.getInt("NUM_LIGNE_FACT"));
				drtUsageNrcModel.setEuroHT(rs.getDouble("EURO_HT"));
				drtUsageNrcModel.setRemise(rs.getDouble("REMISE"));
				drtUsageNrcModel.sethTRemise(rs.getDouble("HT_REMISÉ"));
				drtUsageNrcModel.setNbrAppels(rs.getInt("NBRE_APPELS"));
				drtUsageNrcModel.setDureeSecOuUt(rs.getInt("DURÉE_EN_S_OU_EN_UT"));
				drtUsageNrcModel.setDureeSec(rs.getInt("DURÉE_EN_S"));
				listUsageDrt.add(drtUsageNrcModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listUsageDrt;
	}
	
	public void updateAncienAbo(int mois,int nbJrs){
		getConnection();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.updateAncienAvoirAboDRT(mois,nbJrs);
			String delimiter = ";";
			String[] requetes = sql.split(delimiter);
			for(int i=0;i<requetes.length-1;i++){
				// Create a prepared statement
				System.out.println("je suis dans la boucle");
				String req=requetes[i];
				PreparedStatement ps = con.prepareStatement(req);
				System.out.println("j'ai ma requete");
				System.out.println(req);
				ps.executeUpdate();
				System.out.println("je l'execute");
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public ArrayList<AvoirAncienAboModel> getListAvoirAncienAbo(){
		getConnection();
		ArrayList<AvoirAncienAboModel> listAvoirAncienAboModel=new ArrayList<AvoirAncienAboModel>();
		AvoirAncienAboModel avoirAncienAboModel;
		try{
			String sql="SELECT * FROM LISTE_CTR_DRT_THD_2P ORDER BY CTR";
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				avoirAncienAboModel=new AvoirAncienAboModel();
				avoirAncienAboModel.setCtr(rs.getString("CTR"));
				avoirAncienAboModel.setDateSouscription(rs.getString("DATE_SOUSCRIPTION"));
				avoirAncienAboModel.setDateActivation(rs.getString("DATE_ACTIVATION"));
				avoirAncienAboModel.setDateInactivation(rs.getString("DATE_INACTIVATION"));
				avoirAncienAboModel.setMois(rs.getInt("MOIS"));
				avoirAncienAboModel.setNbJrs(rs.getInt("NB_JOURS"));
				listAvoirAncienAboModel.add(avoirAncienAboModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listAvoirAncienAboModel;
	}
	
	public void updateNouvelAbo(int mois,int nbJrs){
		getConnection();
		try{
			ReadFile readFile= new ReadFile();
			String sql=readFile.updateNouvelAvoirAboDRT(mois,nbJrs);
			String delimiter = ";";
			String[] requetes = sql.split(delimiter);
			for(int i=0;i<requetes.length-1;i++){
				// Create a prepared statement
				String req=requetes[i];
				PreparedStatement ps = con.prepareStatement(req);
				ps.executeUpdate();
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public ArrayList<AvoirNouvelAboModel> getListAvoirNouvelAbo(){
		getConnection();
		ArrayList<AvoirNouvelAboModel> listAvoirNouvelAboModel=new ArrayList<AvoirNouvelAboModel>();
		AvoirNouvelAboModel avoirNouvelAboModel;
		try{
			String sql="SELECT * FROM LISTE_CTR_DRT_THD_2P_AJOUTS ORDER BY CTR";
			// Create a prepared statement
 			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){ 
				avoirNouvelAboModel=new AvoirNouvelAboModel();
				avoirNouvelAboModel.setCtr(rs.getString("CTR"));
				avoirNouvelAboModel.setDateSouscription(rs.getString("DATE_SOUSCRIPTION"));
				avoirNouvelAboModel.setDateActivation(rs.getString("DATE_ACTIVATION"));
				avoirNouvelAboModel.setDateInactivation(rs.getString("DATE_INACTIVATION"));
				avoirNouvelAboModel.setMois(rs.getInt("MOIS"));
				avoirNouvelAboModel.setNbJrs(rs.getInt("NB_JOURS"));
				listAvoirNouvelAboModel.add(avoirNouvelAboModel);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return listAvoirNouvelAboModel;
	}

}
	
