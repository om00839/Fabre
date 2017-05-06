package Beans;

public class UserBean {
	
	private String user_ID;
	private String password;
	
	public void setUser_ID(String user_ID){
		this.user_ID = user_ID;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getUser_ID(){
		return user_ID;
	}
	public String getPassword(){
		return password;
	}
}
