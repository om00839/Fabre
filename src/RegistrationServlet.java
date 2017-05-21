



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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class RegistrationServlet extends HttpServlet {
	private static final Lock lock = new ReentrantLock();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{	
		   String database = "F:\\workspace\\Pabre\\web.db";
		   String table = "user";
		   
		   response.setContentType("text/html");
		   PrintWriter out;
		   try {
//				
				String user_ID = request.getParameter("user_ID");
				String Nick_Name=request.getParameter("Nick_Name");
				String password=request.getParameter("password");
//				String passwordCheck=request.getParameter("passwordCheck");
				
				//call insertUserTable function
				//user_ID,password,Nick_Name, passwordCheck, age)
				boolean success= insertUserTable(user_ID,Nick_Name, password);
				String message= "";
				
				if(success){
//					message="You were successfully registered";
					try {
						request.getRequestDispatcher("/login.html").forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
//					message="You were not successfully registered";
					try {
						request.getRequestDispatcher("/registration.html").forward(request, response); //하나 새로 만들어서 색변화 하면 좋지 않을까.
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		
//		 		request.setAttribute("message", message);	//이거 선언을 해주어야 한다.
//				request.setAttribute("auth", "ok");
					
				
						
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{	
		doGet(request,response);
	}

		public boolean insertUserTable(String user_ID,String Nick_Name,String password) throws Exception
	{

			String database="F:\\workspace\\Pabre\\web.db";
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"
					+ database);
		boolean success=false;
//		if(!(password==passwordCheck)){
//			success=false;
//		}else{
			try {
			
			PreparedStatement prepared = conn
					.prepareStatement("insert into user (user_ID,Nick_Name, password) values (?1,?2,?3);");//?=다이나믹하게 외부에서 정보를 준다.

			prepared.setString(1, user_ID); //문자
			prepared.setString(2, Nick_Name);
			prepared.setString(3, password);
			prepared.addBatch();
			conn.setAutoCommit(false);

			prepared.executeBatch();
			prepared.close();
			conn.commit();
		    conn.close();
		    success=true;  
		    return success;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//언락을 시켜야하
			
		}
			
			return success;

	}
}
