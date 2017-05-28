


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{	
		   String database = "D:\\workspace\\Fabre\\web.db"; 
		   String table = "user";
		   response.setContentType("text/html");
		   PrintWriter out;
		   try {
				
				String param1 = request.getParameter("u_email");//이거 조심
				String param2 = request.getParameter("u_password");

				out = response.getWriter();
				String res = "";
				
				if (retrieveUser(database,table,param1,param2)) {
					ArrayList<ArticleBean> list = retrieveArticle(database,param1);
					ArrayList<CrawlerBean> list2 = retrieveCrawler(database,param1);

					request.setAttribute("u_email", param1);
					request.setAttribute("article", list);					
					request.setAttribute("c_name", list2);	 //더 추가할 것이 있을까?
					request.setAttribute("auth", "ok");
					
					try {
						request.getRequestDispatcher("/index.html").forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						request.getRequestDispatcher("/registration.html").forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					
			} catch (Exception e) {
				
			}
		   
		   
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{	
		doGet(request,response);
	}


	
	public boolean retrieveUser(String database, String table, String param1, String param2)
		throws SQLException
	{
			boolean matched = false;
		    //Statement stmt = null;
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;
			
		    try {
		      Class.forName("org.sqlite.JDBC");
		      conn = DriverManager.getConnection("jdbc:sqlite:"
						+ database);
		      conn.setAutoCommit(false);

		      String query =  "select * from " + table + " where u_email = ? and u_password = ?";
		      //stmt = conn.createStatement();
		     
		      //  create prepared statement
			  ps = conn.prepareStatement( query );

			  ps.setString(1, param1);	   	
			  ps.setString(2, param2);
			
			  rs = ps.executeQuery();
			  
		      while ( rs.next() ) {
		         String u_email = rs.getString("u_email");
		         String u_password = rs.getString("u_password");
		         
		         
		         matched = true;
		         		         
		      }
		      
		      rs.close();
		      conn.close();
		    } catch ( Exception e ) {
		      	e.printStackTrace();
//		    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//		        System.exit(0);
		    }		      
		    System.out.println("Operation done successfully");
		    
		    return matched;
	}
	
	
	
	public ArrayList retrieveArticle(String database, String param1) //서브쿼리를 이용해서 블러오는게 가능할까 근데 확실히 가능할 것 같습니다.
	{
			String table="article";
			ArrayList<ArticleBean> list = new ArrayList();

		    Statement stmt = null;
		    PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      conn = DriverManager.getConnection("jdbc:sqlite:"
						+ database);
		      conn.setAutoCommit(false);
		      
		      String query="select a_url, a_title, a_date from " + table + "where c_id IN (select distinct c_id from UC_Setting where u_email = ?";
			  ps = conn.prepareStatement( query );
		      ps.setString(1, param1);
		      
		      rs = ps.executeQuery(); //select * from article where c_id IN (select distinct c_id from UC_Setting where  //parameter(u_email))  
		      
		      while ( rs.next() ) {
//		         String a_id=rs.getString("a_id");
//		         String c_id=rs.getString("c_id");
		         String a_url = rs.getString("a_url");
		         String a_title = rs.getString("a_title");
		         String a_date = rs.getString("a_date");

		      }
		      rs.close();
		      conn.close();
		    } catch ( Exception e ) {
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//		        System.exit(0);
		    }
		      
			return list;
		    
	}
	
	public ArrayList retrieveCrawler(String database, String param1) //서브쿼리를 이용해서 블러오는게 가능할까 근데 확실히 가능할 것 같습니다.
	{
			String table="crawler";
			ArrayList<CrawlerBean> list2 = new ArrayList();

		    Statement stmt = null;
		    PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;
		    try {
		      Class.forName("org.sqlite.JDBC");
		      conn = DriverManager.getConnection("jdbc:sqlite:"
						+ database);
		      conn.setAutoCommit(false);
		      
		      String query="select c_name " + table + "where c_id IN (select distinct c_id from UC_Setting where u_email = ?";
			  ps = conn.prepareStatement( query );
		      ps.setString(1, param1);
		      
		      rs = ps.executeQuery(); 
		      
		      while ( rs.next() ) {
//		         String c_url=rs.getString("c_url");
//		         String c_id=rs.getString("c_id");
		         String c_name = rs.getString("c_name");
		        
		      }
		      rs.close();
		      conn.close();
		    } catch ( Exception e ) {
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//		        System.exit(0);
		    }
		      
			return list2;
		    
	}
}





































