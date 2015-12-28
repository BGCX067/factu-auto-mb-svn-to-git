package factu_mb_param.modele;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Config {
	
	private String path="";
	private GetDesktopPath getDesktopPath;
	private String chemin = "properties/factu_mb.properties";
	public Config(){
		getDesktopPath=new GetDesktopPath();
		path=getDesktopPath.getApplicationPath()+chemin;
	}
		Properties prop = new Properties();
		
		public String getLogin(){
			try {
	               //load a properties file
	    		prop.load(new FileInputStream(path));
	              //get the property value and print it out
	    		System.out.println("LOGIN : " +prop.getProperty("login"));
	 
	    	} catch (IOException ex) {
	    		JOptionPane.showMessageDialog(null,"Le fichier 'factu_mb.properties'\nest introuvable","Attention",JOptionPane.WARNING_MESSAGE);
	        }
			return prop.getProperty("login");
		}
		
		public String getPassword(){
			try {
	               //load a properties file
	    		prop.load(new FileInputStream(path));
	              //get the property value and print it out
	    		System.out.println("PASSWORD : "+prop.getProperty("password"));
	 
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
			return prop.getProperty("password");
		}		
		
		public String getCptNC(){
			try {
	               //load a properties file
	    		prop.load(new FileInputStream(path));
	              //get the property value and print it out
	    		System.out.println("NUMERICABLE CPT N° : "+prop.getProperty("cpt_numericable"));
	 
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
			return prop.getProperty("cpt_numericable");
		}		
		
		public String getCptBytel(){
			try {
	               //load a properties file
	    		prop.load(new FileInputStream(path));
	              //get the property value and print it out
	    		System.out.println("BOUYGUES CPT N° : "+prop.getProperty("cpt_bouygues"));
	 
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
			return prop.getProperty("cpt_bouygues");
		}		
		
		public String getCptLeCable(){
			try {
	               //load a properties file
	    		prop.load(new FileInputStream(path));
	              //get the property value and print it out
	    		System.out.println("LE CABLE CPT N° : "+prop.getProperty("cpt_le_cable"));
	 
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
			return prop.getProperty("cpt_le_cable");
		}		
		
		public String getCptOmt(){
			try {
	               //load a properties file
	    		prop.load(new FileInputStream(path));
	              //get the property value and print it out
	    		System.out.println("OMT CPT N° : "+prop.getProperty("cpt_omt"));
	 
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
			return prop.getProperty("cpt_omt");
		}		
		
		public String getCptDarty(){
			try {
	               //load a properties file
	    		prop.load(new FileInputStream(path));
	              //get the property value and print it out
	    		System.out.println("DARTY CPT N° : "+prop.getProperty("cpt_darty"));
	 
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	        }
			return prop.getProperty("cpt_darty");
		}		
    }
	    	 

