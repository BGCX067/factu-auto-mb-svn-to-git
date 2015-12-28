package factu_mb_param.modele;

public class ElementIDModel {
		
	//Attributs
	int elmtIdArrears;	
	int elmtIdAvance;	
	int nbrJrsEchu;	
	int aboMoisAvance;	
	
	
	public ElementIDModel(){
		
	}

	public ElementIDModel(int elmtIdArrears, int elmtIdAvance, int nbrJrsEchu,
			int aboMoisAvance) {
		this.elmtIdArrears = elmtIdArrears;
		this.elmtIdAvance = elmtIdAvance;
		this.nbrJrsEchu = nbrJrsEchu;
		this.aboMoisAvance = aboMoisAvance;
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
	
}

