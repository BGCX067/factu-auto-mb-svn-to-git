package factu_mb_param.modele;

import java.util.Date;
import java.util.GregorianCalendar;

public class NbrDayPerMonth {
	
	public NbrDayPerMonth(){
		
	}	
	
	public int getNbrDayForMonth(int mois){
		Date date = new Date();
		int year=date.getYear();
		int fev=0;
		GregorianCalendar cal = new GregorianCalendar();
	    if(cal.isLeapYear(year)==true)
		fev=29;
		else
		fev=28;
		int[] nbrJour={31,fev,31,30,31,30,31,31,30,31,30,31};
		int nbJrMonht=nbrJour[mois];
		return nbJrMonht;
	}
}

