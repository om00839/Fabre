package src.Servlet;


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
		   String database = "F:\\workspace\\Fabre\\web.db"; 
		   String table = "user";
		   response.setContentType("text/html");
		   PrintWriter out;
		   try {
				
				String param1 = request.getParameter("user_ID");//이거 조심
				String param2 = request.getParameter("password");
				out = response.getWriter();
				String res = "";
				
				if (retrieveUser(database,table,param1,param2)) {
//					ArrayList<UserBean> list = retrieveProduct(database);
//					request.setAttribute("user_ID", param1);
//					request.setAttribute("products", list);					
//					request.setAttribute("auth", "ok");
//					
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
		      System.out.println("Opened database successfully");

		      String query =  "select * from " + table + " where user_ID = ? and password = ?";
		      //stmt = conn.createStatement();
		     
		      //  create prepared statement
			  ps = conn.prepareStatement( query );

			  ps.setString(1, param1);	   	
			  ps.setString(2, param2);
			
			  rs = ps.executeQuery();
			  
		      while ( rs.next() ) {
		         String user_ID = rs.getString("user_ID");
		         String password = rs.getString("password");
		         
		         
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
}





































