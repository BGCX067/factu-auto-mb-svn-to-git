package factu_mb_param.modele;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class FormatDate {

	//Attributs
	private Date date;
	private String point;
	private String pays ;
	
	public FormatDate(){
		
	}
	
	public String getFormatDate(String dateIn){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date date=new Date();
        String dateformat = "";
        try {
            date = sdf.parse(dateIn);
            sdf.applyPattern("dd/MM/yyyy");
            dateformat = sdf.format(date);
            System.err.println(dateformat);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateformat;
	}
	
	public String getFormatDateDetail(String dateIn){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date date=new Date();
        String dateformat = "";
        try {
            date = sdf.parse(dateIn);
            sdf.applyPattern("dd/MM/yyyy HH:mm:ss");
            dateformat = sdf.format(date);
            System.err.println(dateformat);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateformat;
	}
	
	public String getFormatDateDetailWithoutSecond(String dateIn){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date date=new Date();
        String dateformat = "";
        try {
            date = sdf.parse(dateIn);
            sdf.applyPattern("dd/MM/yyyy HH:mm");
            dateformat = sdf.format(date);
            System.err.println(dateformat);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateformat;
	}
	
	public String dateToString(Date dateIn){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 
	    String date= "";
	    date =formatter.format(dateIn);
	    
		return date;
		
	}
	
}

