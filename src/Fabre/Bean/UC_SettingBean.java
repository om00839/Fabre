package Fabre.Bean;

import java.io.Serializable;

public class UC_SettingBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String u_email;
	private int c_id;
	private boolean uc_favorite;
	private String[] uc_keywords;
	// uc_keywords는 comma separated 형식으로 저장 할 예정
	
	public UC_SettingBean(){	
		
		UserBean ub = new UserBean();
		this.u_email = ub.getU_email();

		CrawlerBean cb = new CrawlerBean();
		this.c_id = cb.getC_id();
		
	}	
	
	public void setUC_favorite (Boolean uc_favorite){
		this.uc_favorite = uc_favorite;
	}
	
	public void setUC_keywords(String uc_keywords){
		this.uc_keywords = uc_keywords.split(",");
	}
	
	public String getU_email(){
		return u_email;
	}
	
	public int getC_id(){
		return c_id;
	}
	
	public boolean UC_favorite(){
		return uc_favorite;
	}
	
	public String[] getUC_keywords(){
		return uc_keywords;
	}
	

}
