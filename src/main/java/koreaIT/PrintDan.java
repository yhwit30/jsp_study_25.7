package koreaIT;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PrintDan")
public class PrintDan extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		response.getWriter().append("== 8단 ==<br>");

		int dan = 8;

		for (int i = 1; i < 10; i++) {
			response.getWriter().append(String.format("%d * %d = %d<br>", dan, i, (dan * i)));

		}

	}

}
