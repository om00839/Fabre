


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class RegisterationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{	
		   String database = "F:\\workspace\\Pabre\\web.db";
		   String table = "user";
		   
		   response.setContentType("text/html");
		   PrintWriter out;
		   try {
//				
				String user_ID = request.getParameter("user_ID");
				String password=request.getParameter("password");
				
				//call insertUserTable function
				//userId,password,lastname, firstname, age)
				boolean success= insertUserTable(user_ID, password);
				String message= "";
				if(success){
					message="You were successfully registered";
					try {
						request.getRequestDispatcher("/login.html").forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					message="You were not successfully registered";
					try {
						request.getRequestDispatcher("/registeration.html").forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		
		 		request.setAttribute("message", message);	//이거 선언을 해주어야 한다.
				request.setAttribute("auth", "ok");
//				
				
						
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{	
		//		
	}

		public boolean insertUserTable(String user_ID, String password) throws Exception
	{
		boolean success=false;
		
		String database="F:\\workspace\\Pabre\\web.db";
		try {
			Class.forName("org.sqlite.JDBC");
			
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"
					+ database);
			PreparedStatement prepared = conn
					.prepareStatement("insert into user (user_ID, password) values (?1, ?2);");//?=다이나믹하게 외부에서 정보를 준다.

			prepared.setString(1, user_ID); //문자
			prepared.setString(2, password);
			prepared.addBatch();

			conn.setAutoCommit(false);
			prepared.executeBatch();
			
			prepared.close();
		    conn.commit();
		    conn.close();
		    success=true;  
		    
		} catch(Exception e) {
			return success;
		}
		return success;
	}

}
