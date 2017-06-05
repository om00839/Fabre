
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
import Fabre.Bean.UC_SettingBean;
import Fabre.Bean.UserBean;

public class DeleteUC_SettingServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		DatabaseManager dm = null;
		
		UserBean user = new UserBean();

		try{
			
			dm = new DatabaseManager();
			String u_email = request.getParameter("u_email");
			String id = request.getParameter("c_id");
			int c_id=Integer.parseInt(id);
			
			dm.deleteUC_setting(u_email, c_id);
			ArrayList cList = dm.retrieveDisplay_C(user);
			
			request.setAttribute("cList", cList);
			
			request.getRequestDispatcher("/setting_uc_setting.jsp").forward(request, response);
			

			
		}catch(Exception e){
			
			request.getRequestDispatcher("/setting_uc_setting.jsp").forward(request, response);
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

}
