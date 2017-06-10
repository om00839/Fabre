
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

import Fabre.Bean.UserBean;

public class RegistrationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out;
		String msg = "";
		try {

			//
			String u_email = request.getParameter("u_email");
			String u_nickname = request.getParameter("u_nickname");
			String u_password = request.getParameter("u_password");
			String u_passwordCheck = request.getParameter("u_passwordCheck");

			// call insertUserTable function
			// user_ID,password,Nick_Name, passwordCheck, age)
			boolean success = checkEmail(u_email) && checkPassword(u_password, u_passwordCheck);

			DatabaseManager dm = null;

			if (success) {

				try {
					UserBean user = new UserBean();

					user.setU_email(u_email);
					user.setU_password(u_password);
					user.setU_nickname(u_nickname);
					dm = new DatabaseManager();

					if (dm.retrieveUser(u_email) != null) {

						dm.insertUser(user);

						msg = "회원가입에 성공했습니다.";
						request.setAttribute("msg", msg);
						request.getRequestDispatcher("/login.html").forward(request, response);

					} else {
						System.out.println("email 중복");
					}

				} catch (ServletException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {

					dm.close();

				}

			} else {

				try {

					msg = "회원가입에 실패했습니다.";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("/registration.html").forward(request, response);

				} catch (ServletException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public boolean checkEmail(String u_email) throws SQLException, ClassNotFoundException {

		boolean success = false;

		String emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

		if (u_email.matches(emailRegex)) {

			success = true;

		}

		return success;

	}

	public boolean checkPassword(String u_password, String u_passwordcheck) {

		boolean success = false;

		if (u_password.equals(u_passwordcheck)) {
			success = true;
		}

		return success;

	}

}
