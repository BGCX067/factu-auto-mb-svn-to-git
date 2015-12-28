package factu_mb_param.modele;

public class TempInfosFactuModel {
		
	//Attributs
	String date;	
	int runId;
	String mois;	
	int nbrJrMois;	
	int refFacture;	
	int elmtIdArrears;	
	int elmtIdAvance;	
	String description;	
	int nf;	
	int na;	
	int nd1;	
	int nd2;
	int np1;	
	int np2;	
	int nbrJrsEchu;	
	int aboMoisAvance;	
	int rate;
	
	
	public TempInfosFactuModel(){
		
	}

	public TempInfosFactuModel(String date, int runId, String mois,
			int nbrJrMois, int refFacture, int elmtIdArrears, int elmtIdAvance,
			String description, int nf, int na, int nd1, int nd2, int np1,
			int np2, int nbrJrsEchu, int aboMoisAvance, int rate) {		
		this.date = date;
		this.runId = runId;
		this.mois = mois;
		this.nbrJrMois = nbrJrMois;
		this.refFacture = refFacture;
		this.elmtIdArrears = elmtIdArrears;
		this.elmtIdAvance = elmtIdAvance;
		this.description = description;
		this.nf = nf;
		this.na = na;
		this.nd1 = nd1;
		this.nd2 = nd2;
		this.np1 = np1;
		this.np2 = np2;
		this.nbrJrsEchu = nbrJrsEchu;
		this.aboMoisAvance = aboMoisAvance;
		this.rate = rate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRunId() {
		return runId;
	}

	public void setRunId(int runId) {
		this.runId = runId;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public int getNbrJrMois() {
		return nbrJrMois;
	}

	public void setNbrJrMois(int nbrJrMois) {
		this.nbrJrMois = nbrJrMois;
	}

	public int getRefFacture() {
		return refFacture;
	}

	public void setRefFacture(int refFacture) {
		this.refFacture = refFacture;
	}

	public int getElmtIdArrears() {
		return elmtIdArrears;
	}

	public void setElmtIdArrears(int elmtIdArrears) {
		this.elmtIdArrears = elmtIdArrears;
	}

	public int getElmtIdAvance() {
		return elmtIdAvance;
	}

	public void setElmtIdAvance(int elmtIdAvance) {
		this.elmtIdAvance = elmtIdAvance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNf() {
		return nf;
	}

	public void setNf(int nf) {
		this.nf = nf;
	}

	public int getNa() {
		return na;
	}

	public void setNa(int na) {
		this.na = na;
	}

	public int getNd1() {
		return nd1;
	}

	public void setNd1(int nd1) {
		this.nd1 = nd1;
	}

	public int getNd2() {
		return nd2;
	}

	public void setNd2(int nd2) {
		this.nd2 = nd2;
	}

	public int getNp1() {
		return np1;
	}

	public void setNp1(int np1) {
		this.np1 = np1;
	}

	public int getNp2() {
		return np2;
	}

	public void setNp2(int np2) {
		this.np2 = np2;
	}

	public int getNbrJrsEchu() {
		return nbrJrsEchu;
	}

	public void setNbrJrsEchu(int nbrJrsEchu) {
		this.nbrJrsEchu = nbrJrsEchu;
	}

	public int getAboMoisAvance() {
		return aboMoisAvance;
	}

	public void setAboMoisAvance(int aboMoisAvance) {
		this.aboMoisAvance = aboMoisAvance;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
}

