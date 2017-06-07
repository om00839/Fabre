package Fabre.Bean;

import java.io.Serializable;

public class CrawlerBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String c_id;
	private String c_url;
	private String c_name;

	

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public void setC_id(String c_id){
		this.c_id = c_id;
	}
	
	public void setC_url(String c_url){
		this.c_url = c_url;
	}
	
	public String getC_name() {
		return c_name;
	}
	
	public String getC_id(){
		return c_id;
	}
	
	public String getC_url(){
		return c_url;
	}


}
