package factu_mb_param.modele;
public class GetDesktopPath{
		 
	public GetDesktopPath(){
	}
	
	public String getPath(){
		String path = System.getProperty("user.home") + "/Bureau";
		
		path=path.replace("\\", "/");
		return path;
	 }
	
	public String getApplicationPath(){
		String path="C:/Program Files/Facture_MB/";
		return path;
	}
}

