package factu_mb_param.modele;


public class MontantBillInvoiceDetailModel {
	
	//Attributs
	private String echu;
	private String elementId;
	private String montant;
	
	public MontantBillInvoiceDetailModel(){
		
	}

	public MontantBillInvoiceDetailModel(String echu, String elementId,String montant) {
		this.echu = echu;
		this.elementId = elementId;
		this.montant = montant;
	}

	public String getEchu() {
		return echu;
	}

	public void setEchu(String echu) {
		this.echu = echu;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getMontant() {
		return montant;
	}

	public void setMontant(String montant) {
		this.montant = montant;
	}
	
}

