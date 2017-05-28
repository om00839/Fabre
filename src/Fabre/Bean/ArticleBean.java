package Fabre.Bean;

import java.io.Serializable;

public class ArticleBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int a_id;
	private int c_id;
	private String a_url;
	private String a_title;
	private String a_date;
	
	
	public ArticleBean(){
		CrawlerBean cb = new CrawlerBean();
		this.c_id = cb.getC_id();
	}
	
	public void setA_id (int a_id){
		this.a_id = a_id;
	}
	
	public void setA_url(String a_url){
		this.a_url = a_url;
	}
	
	public void setA_title(String a_title){
		this.a_title = a_title;
	}
	
	public void setA_date(String a_date){
		this.a_date = a_date;
	}
	
	public int getA_id(){
		return a_id;
	}
	
	public int getC_id(){
		return c_id;
	}
	
	public String getA_title(){
		return a_title;
	}
	
	public String getA_date(){
		return a_date;
	}
	
	public String getA_url(){
		return a_url;
	}
	
	
	

}
