package factu_mb_param.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ReadFile {
	
	private String path="";
	private GetDesktopPath getDesktopPath;
	
	public ReadFile(){
		getDesktopPath=new GetDesktopPath();
		path=getDesktopPath.getApplicationPath();
	}
	
	//BID NC,LE CABLE ,OMT,DARTY,BYTEL
	//NC
	public String getBIDRequestNC(String numfact){	
		File fileIn=new File(path+"NC/requeteNC/nc_bid_requete.sql");		
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("numFact", numfact);	
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		return chaine;
	}
	
	//OMT echu
	public String getBIDRequestOMTEchu(String numfact){		
		
		File fileIn=new File(path+"OMT/requeteOMT/omt_bid_requete_echu.sql");
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("numFact", numfact);
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		return chaine;
	}
	
	//OMT echoir
		public String getBIDRequestOMTEchoir(String numfact){
			
			File fileIn=new File(path+"OMT/requeteOMT/omt_bid_requete_echoir.sql");
			String chaine="";
			//lecture du fichier texte	
			try{
				InputStream ips=new FileInputStream(fileIn); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					chaine+=ligne+" \n";
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			chaine=chaine.replaceAll("numFact", numfact);
			chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
			return chaine;
		}
	
	//le cable echu
	public String getBIDRequestLeCableEchu(String numfact){	
		
		File fileIn=new File(path+"LE CABLE/requeteMTVC/mtvc_bid_requete_echu.sql");
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("numFact", numfact);
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		return chaine;
	}
	//le cable echoir
	public String getBIDRequestLeCableEchoir(String numfact){
		
		File fileIn=new File(path+"LE CABLE/requeteMTVC/mtvc_bid_requete_echoir.sql");
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("numFact", numfact);
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		return chaine;
	}
	
	//DARTY echu echoir
		public String getBIDRequestDARTY(String numfact,String fromDate,String toDate){
			
			File fileIn=new File(path+"DARTY/requeteDRT/drt_bid_requete.sql");
			String chaine="";
			//lecture du fichier texte	
			try{
				InputStream ips=new FileInputStream(fileIn); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					chaine+=ligne+" \n";
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			chaine=chaine.replaceAll("numFact", numfact);
			chaine=chaine.replaceAll("fromDate", fromDate);
			chaine=chaine.replaceAll("toDate", toDate);
			chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
			return chaine;
		}
	
	//requete info NRC,NC,OMT,LE CABLE ,DARTY,BYTEL
	//NC
	public String getNRCRequestNC(String numFact,String fromDate,String toDate){
		
		File fileIn=new File(path+"NC/requeteNC/nc_recup_infos_nrc.sql");
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("numFact", numFact);
		chaine=chaine.replaceAll("fromDate", fromDate);
		chaine=chaine.replaceAll("toDate", toDate);
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		return chaine;
	}
	
	//OMT
	public String getNRCRequestOMT(String numFact){	
		
		File fileIn=new File(path+"OMT/requeteOMT/omt_recup_infos_nrc.sql");
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("numFact", numFact);
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		return chaine;
	}
	
	//LE CABLE
	public String getNRCRequestMTVC(String numFact, String fromDate, String toDate){
		
		File fileIn=new File(path+"LE CABLE/requeteMTVC/mtvc_recup_infos_nrc.sql");
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("numFact", numFact);
		chaine=chaine.replaceAll("fromDate", fromDate);
		chaine=chaine.replaceAll("toDate", toDate);
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		return chaine;
	}
	
	//DARTY
		public String getNRCRequestDARTY(String numFact, String fromDate, String toDate){
			
			File fileIn=new File(path+"DARTY/requeteDRT/drt_recup_infos_nrc.sql");
			String chaine="";
			//lecture du fichier texte	
			try{
				InputStream ips=new FileInputStream(fileIn); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					chaine+=ligne+" \n";
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			chaine=chaine.replaceAll("numFact", numFact);
			chaine=chaine.replaceAll("fromDate", fromDate);
			chaine=chaine.replaceAll("toDate", toDate);
			chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
			return chaine;
		}
	
	//requete info usage NRC,NC,OMT,LE CABLE ,DARTY,BYTEL
	public String getUSAGERequestNC(String numfact){	
		File fileIn=new File(path+"NC/requeteNC/nc_recup_infos_usage.sql");
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("numFact", numfact);
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		return chaine;
	}
	
	//OMT
	public String getUSAGERequestOMT(String numfact){	
		
		File fileIn=new File(path+"OMT/requeteOMT/omt_recup_infos_usage.sql");
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("numFact", numfact);
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		return chaine;
	}
	
	//OMT NOMBRE NDS ACTIFS
		public String getNB_NDS_ACTIFS(){
				
				File fileIn=new File(path+"OMT/requeteOMT/omt_recup_nbr_nds_actifs.sql");
				String chaine="";
				//lecture du fichier texte	
				try{
					InputStream ips=new FileInputStream(fileIn); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						chaine+=ligne+" \n";
					}
					br.close(); 
				}		
				catch (Exception e){
					System.out.println(e.toString());
				}
				chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));			
				return chaine;
		}			
	
	//LE CABLE
	public String getUSAGERequestMTVC(String numfact){	
		
		File fileIn=new File(path+"LE CABLE/requeteMTVC/mtvc_recup_infos_usage.sql");
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("numFact", numfact);
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		return chaine;
	}
	
	//DARTY
		public String getUSAGERequestDARTY(String numfact){	
			
			File fileIn=new File(path+"DARTY/requeteDRT/drt_recup_infos_usage.sql");
			String chaine="";
			//lecture du fichier texte	
			try{
				InputStream ips=new FileInputStream(fileIn); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					chaine+=ligne+" \n";
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			chaine=chaine.replaceAll("numFact", numfact);
			chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
			return chaine;
		}
	
	//DARTY
		public String getSubtypeCodeRequestDARTY(String numfact){	
			
			File fileIn=new File(path+"DARTY/requeteDRT/drt_subtype_code.sql");
			String chaine="";
			//lecture du fichier texte	
			try{
				InputStream ips=new FileInputStream(fileIn); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					chaine+=ligne+" \n";
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			chaine=chaine.replaceAll("numFact", numfact);
			chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
			return chaine;
		}
	
	//DARTY ANCIEN AVOIR
		public String updateAncienAvoirAboDRT(int mois, int nbrJrs){	
			
			File fileIn=new File(path+"DARTY/requeteDRT/requetes_pour_ANCIEN_avoir_2.5E_sur_fact_avec_prorata.sql");
			String chaine="";
			//lecture du fichier texte	
			try{
				InputStream ips=new FileInputStream(fileIn); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					chaine+=ligne+" \n";
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			chaine=chaine.replaceAll("varMois", String.valueOf(mois));
			chaine=chaine.replaceAll("varNbJr", String.valueOf(nbrJrs));
			chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
			return chaine;
		}
	
	//DARTY ANCIEN AVOIR
		public String updateNouvelAvoirAboDRT(int mois, int nbrJrs){	
			
			File fileIn=new File(path+"DARTY/requeteDRT/requetes_pour_NOUVEL_avoir_2.5E_sur_fact_avec_prorata.sql");
			String chaine="";
			//lecture du fichier texte	
			try{
				InputStream ips=new FileInputStream(fileIn); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					chaine+=ligne+" \n";
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			chaine=chaine.replaceAll("varMois", String.valueOf(mois));
			chaine=chaine.replaceAll("varNbJr", String.valueOf(nbrJrs));
			chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
			return chaine;
		}
		
	//BYTEL echu
		public String getBIDRequestBYTELEchu(String numFact){
			
			File fileIn=new File(path+"BYTEL/requeteBYTEL/bytel_bid_requete_echu.sql");
			String chaine="";
			//lecture du fichier texte	
			try{
				InputStream ips=new FileInputStream(fileIn); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					chaine+=ligne+" \n";
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			chaine=chaine.replaceAll("numFact", numFact);
			chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
			return chaine;
		}
		
		//BYTEL echoir
		public String getBIDRequestBYTELEchoir(String numFact){
			
			File fileIn=new File(path+"BYTEL/requeteBYTEL/bytel_bid_requete_echoir.sql");
			String chaine="";
			//lecture du fichier texte	
			try{
				InputStream ips=new FileInputStream(fileIn); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					chaine+=ligne+" \n";
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			chaine=chaine.replaceAll("numFact", numFact);
			chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
			return chaine;
		}
		
	//BYTEL nrc
	public String getNRCRequestBYTEL(String numFact, String fromDate, String toDate){
		
		File fileIn=new File(path+"BYTEL/requeteBYTEL/bytel_recup_infos_nrc.sql");
		String chaine="";
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("numFact", numFact);
		chaine=chaine.replaceAll("fromDate", fromDate);
		chaine=chaine.replaceAll("toDate", toDate);
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		return chaine;
	}
	
	//BYTEL nrc
	public String getAboDetailRequestBYTEL(String toDate){
		
		File fileIn=new File(path+"BYTEL/requeteBYTEL/bytel_recup_infos_abonnements_details.sql");
		String chaine="";
		System.out.println("recuperation nrc bouygues");
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fileIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+" \n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		chaine=chaine.replaceAll("toDate", toDate);
		chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));
		System.out.println("fin recuperation nrc bouygues");
		return chaine;
	}
		
	//BYTEL Reco Sans fas
	public String getRecoSansFasRequestBYTEL(String fromDate, String toDate){
			
			File fileIn=new File(path+"BYTEL/requeteBYTEL/bytel_reco_sans_fas.sql");
			String chaine="";
			//lecture du fichier texte	
			try{
				InputStream ips=new FileInputStream(fileIn); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					chaine+=ligne+" \n";
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
			chaine=chaine.replaceAll("fromDate", fromDate);
			chaine=chaine.replaceAll("toDate", toDate);
			chaine=new String(chaine.getBytes(),Charset.forName("UTF-8"));			
			return chaine;
	}			
	
	
}