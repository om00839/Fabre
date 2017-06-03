import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Fabre.Bean.CrawlerBean;
import Fabre.Bean.UC_SettingBean;
import Fabre.Bean.UserBean;

public class UpdateUC_SettingServlet {
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		
		DatabaseManager dm = null;
		
		UserBean user = (UserBean) request.getAttribute("user");
		
		CrawlerBean crawler = (CrawlerBean) request.getAttribute("crawler");
		boolean uc_favorite = (boolean) request.getAttribute("uc_favorite");
		
		try {
			
			dm = new DatabaseManager();

			UC_SettingBean uc_setting = dm.retrieveUC_Setting(user);
			uc_setting.setUC_favorite(uc_favorite);
			dm.updateUC_Setting(uc_setting);
			
			dm.close();

		} catch (Exception e) {
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}



}
