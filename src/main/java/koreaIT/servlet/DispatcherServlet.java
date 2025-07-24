package koreaIT.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ArticleController;
import koreaIT.util.DBUtil;

@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// DB 연결
		response.setContentType("text/html;charset=UTF-8");
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3307/AM_jsp_2025_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "");

			response.getWriter().append("연결성공");

			DBUtil dbUtil = new DBUtil(request, response);

			// 로그인정보
			HttpSession session = request.getSession();
			Map<String, Object> loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
			int loginedMemberId = -1;
			if (session.getAttribute("loginedMemberId") != null) {
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
			}

			String requestUri = request.getRequestURI();

			response.getWriter().append("Served at: ").append(requestUri);

			String[] reqUriBits = requestUri.split("/");
			String controllerName = reqUriBits[3];
			String actionMethodName = reqUriBits[4];

			System.out.println("controllerName : " + controllerName);
			System.out.println("actionMethodName : " + actionMethodName);

//		System.out.println(reqUriBits[0]); // 공백
//		System.out.println(reqUriBits[1]); // servlet
//		System.out.println(reqUriBits[2]); // s
//		System.out.println(reqUriBits[3]); // article
//		System.out.println(reqUriBits[4]); // list

			if (controllerName.equals("article")) {
				ArticleController articleController = new ArticleController(request, response, conn);

				if (actionMethodName.equals("list")) {
					articleController.showList();
				}

			}

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("에러 : " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
