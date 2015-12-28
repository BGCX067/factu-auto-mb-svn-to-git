package factu_mb_param.modele;

public class OngletUsageModelMTVC {
		
	//Attributs
	private String typeTrafic;
	private String classeJuris;
	private int codeJuris;
	private String libJuris;
	private double euroHT;
	private double remise;
	private double hTRemise;
	private int nbrAppels;
	private int dureeSecOuUt;
	private int dureeSec;
	
	public OngletUsageModelMTVC(){
		
	}

	public OngletUsageModelMTVC(String typeTrafic, String classeJuris,
			int codeJuris, String libJuris, double euroHT, double remise,
			double hTRemise, int nbrAppels, int dureeSecOuUt, int dureeSec) {
		this.typeTrafic = typeTrafic;
		this.classeJuris = classeJuris;
		this.codeJuris = codeJuris;
		this.libJuris = libJuris;
		this.euroHT = euroHT;
		this.remise = remise;
		this.hTRemise = hTRemise;
		this.nbrAppels = nbrAppels;
		this.dureeSecOuUt = dureeSecOuUt;
		this.dureeSec = dureeSec;
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

	public int getCodeJuris() {
		return codeJuris;
	}

	public void setCodeJuris(int codeJuris) {
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

	public int getDureeSecOuUt() {
		return dureeSecOuUt;
	}

	public void setDureeSecOuUt(int dureeSecOuUt) {
		this.dureeSecOuUt = dureeSecOuUt;
	}

	public int getDureeSec() {
		return dureeSec;
	}

	public void setDureeSec(int dureeSec) {
		this.dureeSec = dureeSec;
	}
	
}

