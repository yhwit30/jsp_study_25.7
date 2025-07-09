package koreaIT;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomeMain2")
public class HomeMain2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().append("homeMain2!!!!!!!!!!").append(request.getContextPath());
		response.getWriter().append("<a href=\"http://localhost:8080/yhw_servlet/HomeMainServlet\">home으로 가기</a>");
	}

}
