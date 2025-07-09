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
		
		String inputDan = request.getParameter("dan");
		String inputLimit = request.getParameter("limit");
		System.out.println(inputDan);
		System.out.println(inputLimit);
		

		if (inputDan == null) {
			inputDan = "2";
		}
		if (inputLimit == null) {
			inputLimit = "1";
		}
		
		
		int dan = Integer.parseInt(inputDan);
		int limit = Integer.parseInt(inputLimit);
		
		response.getWriter().append("== " + dan + "단 ==<br>");


		for (int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("%d * %d = %d<br>", dan, i, (dan * i)));

		}

	}

}
