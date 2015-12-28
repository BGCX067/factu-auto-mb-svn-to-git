package factu_mb_param.modele;

public class DateConvertor {
	
	public DateConvertor(){
		
	}
	
	public String getConvertMonth(String date){
		String mois=date.substring(3,5);
		String[] map={"01","02","03","04","05","06","07","08","09","10","11","12"};
		int cpt=0;
		for (int i=0;i<map.length;i++){
			if(map[i].equalsIgnoreCase(mois)){
				cpt=i;
			}
		}
		String[] month={"janvier","fevrier","mars","avril","mai","juin","juillet","aout","septembre",
				"octobre","novembre","decembre"};
		
		return month[cpt];		
	}	
	
	public int getEnglishMonth(String date){
		String mois=date.substring(4,5);
		String[] map={"0","2","3","4","5","6","7","8","9","10","11","12"};
		int cpt=0;
		for (int i=0;i<map.length;i++){
			if(map[i].equalsIgnoreCase(mois)){
				cpt=Integer.valueOf(mois)-1;
			}
		}		
		return cpt;		
	}	
	
	public String getMonthFact(String month){
		String mois=month.substring(3,5);		
		return mois;		
	}	
	
	public String getYearFact(String year){
		String annee=year.substring(6);				
		return annee;		
	}	
	
	public String getConvertYear(String date){
		String annee=date.substring(6);				
		return annee;		
	}	
}

