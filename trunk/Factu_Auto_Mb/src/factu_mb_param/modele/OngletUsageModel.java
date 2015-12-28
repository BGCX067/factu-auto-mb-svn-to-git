package factu_mb_param.modele;

public class OngletUsageModel {
		
	//Attributs
	private String typeTrafic;
	private String classeJuris;
	private String codeJuris;
	private String libJuris;
	private double euroHT;
	private double remise;
	private double hTRemise;
	private int nbrAppels;
	private int dureeFacture;
	
	public OngletUsageModel(){
		
	}

	public OngletUsageModel(String typeTrafic, String classeJuris,
			String codeJuris, String libJuris, double euroHT, double remise,
			double hTRemise, int nbrAppels, int dureeFacture) {
		this.typeTrafic = typeTrafic;
		this.classeJuris = classeJuris;
		this.codeJuris = codeJuris;
		this.libJuris = libJuris;
		this.euroHT = euroHT;
		this.remise = remise;
		this.hTRemise = hTRemise;
		this.nbrAppels = nbrAppels;
		this.dureeFacture = dureeFacture;
	}

	public String getTypeTrafic() {
		return typeTrafic;
	}

	public void setTypeTrafic(String typeTrafic) {
		this.typeTrafic = typeTrafic;
	}

	public String getClasseJuris() {
		return classeJuris;
	}

	public void setClasseJuris(String classeJuris) {
		this.classeJuris = classeJuris;
	}

	public String getCodeJuris() {
		return codeJuris;
	}

	public void setCodeJuris(String codeJuris) {
		this.codeJuris = codeJuris;
	}

	public String getLibJuris() {
		return libJuris;
	}

	public void setLibJuris(String libJuris) {
		this.libJuris = libJuris;
	}

	public double getEuroHT() {
		return euroHT;
	}

	public void setEuroHT(double euroHT) {
		this.euroHT = euroHT;
	}

	public double getRemise() {
		return remise;
	}

	public void setRemise(double remise) {
		this.remise = remise;
	}

	public double gethTRemise() {
		return hTRemise;
	}

	public void sethTRemise(double hTRemise) {
		this.hTRemise = hTRemise;
	}

	public int getNbrAppels() {
		return nbrAppels;
	}

	public void setNbrAppels(int nbrAppels) {
		this.nbrAppels = nbrAppels;
	}

	public int getDureeFacture() {
		return dureeFacture;
	}

	public void setDureeFacture(int dureeFacture) {
		this.dureeFacture = dureeFacture;
	}
}

