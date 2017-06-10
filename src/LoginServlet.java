

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

import Fabre.Bean.ArticleBean;
import Fabre.Bean.CrawlerBean;
import Fabre.Bean.UserBean;

public class LoginServlet extends HttpServlet {

	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		DatabaseManager dm = null;
		
		UserBean user = new UserBean();
		ArrayList<CrawlerBean> cList = new ArrayList<CrawlerBean>();
		ArrayList<ArticleBean> aList = new ArrayList<ArticleBean>();
		
		
		try {
			
			dm = new DatabaseManager();
			
			String u_email = request.getParameter("u_email");// 이거 조심
			String u_password = request.getParameter("u_password");
			
			if (u_email==null || u_password == null){
				
				response.sendRedirect("./login.html");
				
			}else{
				
				user = dm.retrieveUser(u_email);
				
			}
			
			
			
			if (user!=null && user.getU_password().equals(u_password)) {
				
				cList = dm.retrieveDisplay_C(user);
				aList = dm.retrieveDisplay_A(user);

				request.setAttribute("user", user);
				request.setAttribute("cList", cList);
				request.setAttribute("aList", aList);
				request.setAttribute("auth", "ok");

				try {
					request.getRequestDispatcher("/main.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					
					request.getRequestDispatcher("/login.html").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			

		} catch (Exception e) {
			
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}
