package factu_mb_param.modele;

public class ResumeFactModel {
	
	//Attributs
	private String fromDate;
	private String toDate;
	private String prepDate;
	private String statDate;
	
	public ResumeFactModel(){
		
	}

	public ResumeFactModel(java.lang.String fromDate, java.lang.String toDate,
			java.lang.String prepDate, java.lang.String statDate) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.prepDate = prepDate;
		this.statDate = statDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getPrepDate() {
		return prepDate;
	}

	public void setPrepDate(String prepDate) {
		this.prepDate = prepDate;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}
	
}

