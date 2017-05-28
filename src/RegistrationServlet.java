




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

	String email="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{	
		   String database = "D:\\workspace\\Fabre\\web.db";
		   String table = "user";
		   response.setContentType("text/html");
		   PrintWriter out;
		   try {
//				
				String u_email = request.getParameter("u_email");
				String u_nickname=request.getParameter("u_nickname");
				String u_password=request.getParameter("u_password");
				String u_passwordCheck=request.getParameter("u_passwordCheck");
				
				//call insertUserTable function
				//user_ID,password,Nick_Name, passwordCheck, age)
				boolean success= insertUserTable(u_email,u_nickname, u_password, u_passwordCheck);
				String message= "";
				
				if(success){
//					message="You were successfully registered";
					try {
						request.getRequestDispatcher("/index.html").forward(request, response);
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
					}finally{
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

		public boolean insertUserTable(String u_email,String u_nickname, String u_password,String u_passwordCheck) throws Exception
	{
			final Lock lock = new ReentrantLock();

			String database="D:\\workspace\\Fabre\\web.db";
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"
					+ database);
		boolean success=false;
//		if(!(password==passwordCheck)){
//			success=false;
//		}else{
//		
		if((u_email.matches(email))){
//			if(!(password==passwordCheck)){
//				return success;}
			
			if(u_password.equals(u_passwordCheck)){
				try {
					
			PreparedStatement prepared = conn
					.prepareStatement("insert into user (u_email, u_nickname, u_password) values (?1,?2,?3);");//?=다이나믹하게 외부에서 정보를 준다.
			prepared.setString(1, u_email); //문자
			prepared.setString(2, u_nickname);
			prepared.setString(3, u_password);
//			prepared.setString(4, passwordCheck);
			
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
			conn.rollback();
		} 
				conn.rollback();
	}
		else{
			return success;
		}
			
			
			
	}else{
		return success;
	}
		return success;
	}
}

