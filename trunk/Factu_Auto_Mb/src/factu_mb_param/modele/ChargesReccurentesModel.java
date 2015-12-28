package factu_mb_param.modele;

public class ChargesReccurentesModel {
	
	//Attributs
	private String date;
	private int nbrJour;
	private String labelJour;
	private double prixAbo;
	private double montant;
	private int id;
	
	public ChargesReccurentesModel(){
		
	}

	public ChargesReccurentesModel(String date, int nbrJour, String labelJour,
			double prixAbo, double montant, int id) {
		this.date = date;
		this.nbrJour = nbrJour;
		this.labelJour = labelJour;
		this.prixAbo = prixAbo;
		this.montant = montant;
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNbrJour() {
		return nbrJour;
	}

	public void setNbrJour(int nbrJour) {
		this.nbrJour = nbrJour;
	}

	public String getLabelJour() {
		return labelJour;
	}

	public void setLabelJour(String labelJour) {
		this.labelJour = labelJour;
	}

	public double getPrixAbo() {
		return prixAbo;
	}

	public void setPrixAbo(double prixAbo) {
		this.prixAbo = prixAbo;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

