package factu_mb_param.modele;

public class OngletRecoSansFasModelBYTEL {
		
	//Attributs
	private String externalId;
	private String parentSubscrNo;
	private String trackingId;
	private String trackingIdServ;
	
	public OngletRecoSansFasModelBYTEL(){
		
	}

	public OngletRecoSansFasModelBYTEL(String externalId,
			String parentSubscrNo, String trackingId, String trackingIdServ) {
		this.externalId = externalId;
		this.parentSubscrNo = parentSubscrNo;
		this.trackingId = trackingId;
		this.trackingIdServ = trackingIdServ;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getParentSubscrNo() {
		return parentSubscrNo;
	}

	public void setParentSubscrNo(String parentSubscrNo) {
		this.parentSubscrNo = parentSubscrNo;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public String getTrackingIdServ() {
		return trackingIdServ;
	}

	public void setTrackingIdServ(String trackingIdServ) {
		this.trackingIdServ = trackingIdServ;
	}
	
}

