package factu_mb_param.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import factu_mb_param.modele.FormatDate;
import factu_mb_param.modele.TempInfosFactuModel;
import factu_mb_param.modele.ResumeFactModel;



public class BillRefNo3PDao {
	
	public TempInfosFactuModel ouverturePointModel;
	private String userid = "arbor";
    private String password = "arbor123";
    private String pilote = "oracle.jdbc.driver.OracleDriver";
    private String url = "jdbc:oracle:thin:@10.1.3.136:1522:cust3p";    	

	private static Connection con;
	private ResumeFactModel resumeFactModel;
	private FormatDate formatDate;

     // constructor 
	public BillRefNo3PDao(){
		this.ouverturePointModel= new TempInfosFactuModel();
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
	
	//Recherche du bill_ref_no du compte selectionner
	public int getBillRefNo(int cpt){
		getConnection();
			int bill_ref_no=0;
			System.out.println("CPT = "+cpt);
			try{
				String sql="SELECT BILL_REF_NO FROM BILL_INVOICE "
						+"WHERE account_no = "+cpt +" "
						+"AND ROWNUM=1 "
						+"ORDER BY BILL_REF_NO DESC";
				
				// Create a prepared statement
	 			Statement s = con.createStatement();

				ResultSet rs = s.executeQuery(sql);	
				while(rs.next()){ 
					bill_ref_no = rs.getInt("BILL_REF_NO");
				}
			}
			catch(Exception e){
				System.out.println(e);
			}
			return bill_ref_no;
	}
	
	//Recherche detail de date sur le compte selectionner
	public ResumeFactModel getBillRefNoDetail(int billRefNo){
		getConnection();
		resumeFactModel= new ResumeFactModel();
		String fromDate=null;
		String toDate=null;
		String prepDate=null;		
		String statDate=null;
		
		formatDate= new FormatDate();
		
			System.out.println("BILL_REF_NO = "+billRefNo);
			try{
				String sql="SELECT from_date,to_date,prep_date,statement_date " 
						+"FROM BILL_INVOICE "
						+"WHERE BILL_REF_NO = "+billRefNo +" ";
				
				// Create a prepared statement
	 			Statement s = con.createStatement();

				ResultSet rs = s.executeQuery(sql);	
				while(rs.next()){ 
					fromDate = rs.getString("from_date");
					toDate = rs.getString("to_date");
					prepDate = rs.getString("prep_date");
					statDate = rs.getString("statement_date");
				}
				fromDate= formatDate.getFormatDate(fromDate);
				toDate= formatDate.getFormatDate(toDate);
				prepDate= formatDate.getFormatDateDetail(prepDate);
				statDate= formatDate.getFormatDate(statDate);
				resumeFactModel= new ResumeFactModel(fromDate, toDate, prepDate, statDate);
			}
			catch(Exception e){
				System.out.println(e);
			}
			return resumeFactModel;
	}
}
	
