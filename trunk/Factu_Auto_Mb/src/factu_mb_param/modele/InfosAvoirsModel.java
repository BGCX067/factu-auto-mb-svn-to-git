package factu_mb_param.modele;

public class InfosAvoirsModel {
		
	//Attributs
	private double prixFacture;	
	private double montantAvoir;	
	
	public InfosAvoirsModel(){
		
	}
	
	public InfosAvoirsModel(double prixFacture, double montantAvoir) {
		this.prixFacture = prixFacture;
		this.montantAvoir = montantAvoir;
	}

	public double getPrixFacture() {
		return prixFacture;
	}

	public void setPrixFacture(double prixFacture) {
		this.prixFacture = prixFacture;
	}

	public double getMontantAvoir() {
		return montantAvoir;
	}

	public void setMontantAvoir(double montantAvoir) {
		this.montantAvoir = montantAvoir;
	}

	
}

