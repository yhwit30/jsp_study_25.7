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

import koreaIT.util.DBUtil;
import koreaIT.util.SecSql;

@WebServlet("/article/modify")
public class ArticleModifyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3307/AM_jsp_2025_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "");

			response.getWriter().append("연결성공");

			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("modify id : " + id);

			DBUtil dbUtil = new DBUtil(request, response);

			SecSql sql = new SecSql();
			sql.append("SELECT *");
			sql.append("FROM `article`");
			sql.append("WHERE `id` = ?", id);

			Map<String, Object> articleRow = dbUtil.selectRow(conn, sql);
			
			if (articleRow.isEmpty()) {
				response.getWriter().append(String.format("<script>alert('%d번 글은 존재하지 않습니다.');location.replace('list'); </script>", id));
				return;
			}
			
			System.out.println("modify articleRow : " + articleRow.toString());
			
			request.setAttribute("articleRow", articleRow);

			request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);
			
			
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


}
