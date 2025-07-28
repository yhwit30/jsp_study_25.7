package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeController {
	private HttpServletRequest request;
	private HttpServletResponse response;

	public HomeController(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void showMain() throws ServletException, IOException{
		HttpSession session = request.getSession();
		Map<String, Object> loginedMember = null;
		
		boolean isLogined = false;
		
		loginedMember = (Map<String, Object>)session.getAttribute("loginedMember");
		
		request.setAttribute("loginedMember", loginedMember);
		
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);

		
	}
	
	

}
