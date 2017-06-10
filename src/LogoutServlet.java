
import java.io.IOException;

import java.io.PrintWriter;

import java.util.Enumeration;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import Fabre.Bean.UserBean;

public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		if (session != null) {

			session.removeAttribute("user");
			session.removeAttribute("auth");
			session.removeAttribute("cList");
			session.removeAttribute("aList");

			session.invalidate();
			response.sendRedirect("./login.html");

		} else {

			session.invalidate();
			response.sendRedirect("./login.html");

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		doGet(request, response);
	}

}
