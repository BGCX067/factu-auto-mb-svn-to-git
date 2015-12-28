package factu_mb_param.modele;

public class OngletNrcModel {
		
	//Attributs
	private int trackingId;	
	private String xID;	
	private String iDContrat;	
	private String offre;
	private String dateActivationService;	
	private int typeIdNrc;	
	private String libelleFacturation;	
	private String effectiveDate;	
	private double montant;
	
	public OngletNrcModel(){
		
	}

	public OngletNrcModel(int trackingId, String xID, String iDContrat,
			String offre, String dateActivationService, int typeIdNrc,
			String libelleFacturation, String effectiveDate, double montant) {
		this.trackingId = trackingId;
		this.xID = xID;
		this.offre=offre;
		this.iDContrat = iDContrat;
		this.dateActivationService = dateActivationService;
		this.typeIdNrc = typeIdNrc;
		this.libelleFacturation = libelleFacturation;
		this.effectiveDate = effectiveDate;
		this.montant = montant;
	}	

	public int getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(int trackingId) {
		this.trackingId = trackingId;
	}

	public String getxID() {
		return xID;
	}

	public void setxID(String xID) {
		this.xID = xID;
	}

	public String getiDContrat() {
		return iDContrat;
	}

	public void setiDContrat(String iDContrat) {
		this.iDContrat = iDContrat;
	}
	
	public String getOffre() {
		return offre;
	}

	public void setOffre(String offre) {
		this.offre = offre;
	}

	public String getDateActivationService() {
		return dateActivationService;
	}

	public void setDateActivationService(String dateActivationService) {
		this.dateActivationService = dateActivationService;
	}

	public int getTypeIdNrc() {
		return typeIdNrc;
	}

	public void setTypeIdNrc(int typeIdNrc) {
		this.typeIdNrc = typeIdNrc;
	}

	public String getLibelleFacturation() {
		return libelleFacturation;
	}

	public void setLibelleFacturation(String libelleFacturation) {
		this.libelleFacturation = libelleFacturation;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}	
}

