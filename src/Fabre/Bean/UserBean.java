package Fabre.Bean;

import java.io.Serializable;

public class UserBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String u_email;
	private String u_password;
	private String u_nickname;
	
	public void setU_email(String u_email){
		this.u_email = u_email;
	}
	
	public void setU_password(String u_password){
		this.u_password = u_password;
	}
	
	public void setU_nickname(String u_nickname){
		this.u_nickname = u_nickname;
	}
	
	public String getU_email(){
		return u_email;
	}
	
	public String getU_password(){
		return u_password;
	}
	
	public String getU_nickname(){
		return u_nickname;
	}
}
