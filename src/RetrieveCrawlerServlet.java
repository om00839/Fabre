import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Fabre.Bean.CrawlerBean;

public class RetrieveCrawlerServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {

		response.setContentType("text/html; charset=UTF-8");
		DatabaseManager dm = null;

		
		try {

			
			String query = request.getParameter("query");
			
			dm = new DatabaseManager();

			ArrayList<CrawlerBean> rList = dm.retrieveCrawler(query);
			
			request.setAttribute("rList", rList);
			
			request.getRequestDispatcher("/setting_crawler.jsp").forward(request, response);
			
			

		} catch (Exception e) {
			
			request.getRequestDispatcher("/setting_crawler.jsp").forward(request, response);
			
		}finally{
			
			dm.close();
			
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		doGet(request, response);
	}

}
