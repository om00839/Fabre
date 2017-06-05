
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Fabre.Bean.UserBean;

public class UpdateUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		DatabaseManager dm = null;
		
		UserBean user = (UserBean) request.getAttribute("user");

		try{
			
			dm = new DatabaseManager();
			String u_email = request.getParameter("u_email");
			String u_password = request.getParameter("u_password");
			
			dm.updateUser(u_password, u_email);
			
			user.setU_password(u_password);
			
			request.getRequestDispatcher("/setting_user.jsp").forward(request, response);
			

			
			
		}catch(Exception e){
			
			request.getRequestDispatcher("/setting_user.jsp").forward(request, response);
			e.printStackTrace();
			
		}finally{
			
			
			try {
				dm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
