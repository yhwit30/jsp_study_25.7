package koreaIT.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home/main")
public class HomeMainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Map<String, Object> loginedMember = null;
		
		boolean isLogined = false;
		
		loginedMember = (Map<String, Object>)session.getAttribute("loginedMember");
		
		request.setAttribute("loginedMember", loginedMember);
		
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}

}
