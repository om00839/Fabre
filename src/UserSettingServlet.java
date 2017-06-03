import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Fabre.Bean.ArticleBean;
import Fabre.Bean.CrawlerBean;
import Fabre.Bean.UserBean;

public class UserSettingServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		DatabaseManager dm = null;
		
		UserBean m_user = (UserBean) request.getAttribute("m_user");
		
		
		try {
			
			dm = new DatabaseManager();
			
			if (m_user!=null) {
				
				//dm.updateUser(m_user);

				request.setAttribute("user", m_user);

				try {
					request.getRequestDispatcher("/usersetting.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					request.getRequestDispatcher("/usersetting.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			dm.close();

		} catch (Exception e) {
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}


}
