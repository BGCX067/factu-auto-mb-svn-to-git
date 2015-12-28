package factu_mb_param.modele;

public class ConnexionModel {
	
	//regex
	private String LOGIN_REGEX_MESSAGE = "Le login ou mot de passe\nest incorrect";
	
	//Attributs
	private String userName;
	private String password;
	
	
	public ConnexionModel(){
		
	}
	
	public ConnexionModel(String userName,String password) {		
		this.userName = userName;
		this.password = password;
		
	}

	public String getLOGIN_REGEX_MESSAGE() {
		return LOGIN_REGEX_MESSAGE;
	}

	public void setLOGIN_REGEX_MESSAGE(String lOGINREGEXMESSAGE) {
		LOGIN_REGEX_MESSAGE = lOGINREGEXMESSAGE;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

