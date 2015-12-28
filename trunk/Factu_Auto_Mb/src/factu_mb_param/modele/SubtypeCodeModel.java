package factu_mb_param.modele;

public class SubtypeCodeModel {
		
	//Attributs
	private int subtypeCode;		
	private int sumAmount;	
	
	public SubtypeCodeModel(){
		
	}

	public SubtypeCodeModel(int subtypeCode, int sumAmount) {
		this.subtypeCode = subtypeCode;
		this.sumAmount = sumAmount;
	}

	public int getSubtypeCode() {
		return subtypeCode;
	}

	public void setSubtypeCode(int subtypeCode) {
		this.subtypeCode = subtypeCode;
	}

	public int getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(int sumAmount) {
		this.sumAmount = sumAmount;
	}

}

