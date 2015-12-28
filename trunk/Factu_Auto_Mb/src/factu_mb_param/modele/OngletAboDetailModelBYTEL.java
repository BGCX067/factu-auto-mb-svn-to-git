package factu_mb_param.modele;

public class OngletAboDetailModelBYTEL {
		
	//Attributs
	private String id_client;
	private String type_abo;
	private String active_dt;
	private String inactive_dt;
	
	public OngletAboDetailModelBYTEL(){
		
	}

	public OngletAboDetailModelBYTEL(String id_client, String type_abo,
			String active_dt, String inactive_dt) {
		super();
		this.id_client = id_client;
		this.type_abo = type_abo;
		this.active_dt = active_dt;
		this.inactive_dt = inactive_dt;
	}

	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
	}

	public String getType_abo() {
		return type_abo;
	}

	public void setType_abo(String type_abo) {
		this.type_abo = type_abo;
	}

	public String getActive_dt() {
		return active_dt;
	}

	public void setActive_dt(String active_dt) {
		this.active_dt = active_dt;
	}

	public String getInactive_dt() {
		return inactive_dt;
	}

	public void setInactive_dt(String inactive_dt) {
		this.inactive_dt = inactive_dt;
	}
	
}

