import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Fabre.Bean.UC_SettingBean;
import Fabre.Bean.UserBean;

public class DeleteUC_SettingServlet {
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		
		DatabaseManager dm = null;
		
		UC_SettingBean uc_setting = (UC_SettingBean) request.getAttribute("uc_setting");
		
		try {
			
			dm = new DatabaseManager();

			dm.deleteUC_Setting(uc_setting);
			
			dm.close();

		} catch (Exception e) {
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}