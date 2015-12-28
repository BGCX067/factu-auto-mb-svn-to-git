package factu_mb_param.modele;

import java.util.Date;

public class AvoirNouvelAboModel {
		
	//Attributs
	private String ctr;	
	private String dateSouscription;
	private String dateActivation;
	private String dateInactivation;
	private int mois;
	private int nbJrs;
	
	public AvoirNouvelAboModel(){
		
	}

	public AvoirNouvelAboModel(String ctr, String dateSouscription,
			String dateActivation, String dateInactivation, int mois, int nbJrs) {
		this.ctr = ctr;
		this.dateSouscription = dateSouscription;
		this.dateActivation = dateActivation;
		this.dateInactivation = dateInactivation;
		this.mois = mois;
		this.nbJrs = nbJrs;
	}
	
	public String getCtr() {
		return ctr;
	}

	public void setCtr(String ctr) {
		this.ctr = ctr;
	}

	public String getDateSouscription() {
		return dateSouscription;
	}

	public void setDateSouscription(String dateSouscription) {
		this.dateSouscription = dateSouscription;
	}

	public String getDateActivation() {
		return dateActivation;
	}

	public void setDateActivation(String dateActivation) {
		this.dateActivation = dateActivation;
	}

	public String getDateInactivation() {
		return dateInactivation;
	}

	public void setDateInactivation(String dateInactivation) {
		this.dateInactivation = dateInactivation;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public int getNbJrs() {
		return nbJrs;
	}

	public void setNbJrs(int nbJrs) {
		this.nbJrs = nbJrs;
	}
	
}

