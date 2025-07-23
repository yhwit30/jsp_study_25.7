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

import koreaIT.util.DBUtil;
import koreaIT.util.SecSql;

@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3307/AM_jsp_2025_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("연결 성공!");

			response.getWriter().append("연결성공");

			int id = Integer.parseInt(request.getParameter("id"));

			DBUtil dbUtil = new DBUtil(request, response);

			SecSql sql = new SecSql();
			sql.append("SELECT *");
			sql.append("FROM `article` a");
			sql.append("INNER JOIN `member` m");
			sql.append("ON a.memberId = m.id");
			sql.append("where a.`id` = ?;", id);

			Map<String, Object> articleRow = dbUtil.selectRow(conn, sql);

			request.setAttribute("articleRow", articleRow); // jsp에 데이터를 넘겨준다.
			request.getRequestDispatcher("/jsp/article/detail.jsp").forward(request, response);

//            response.getWriter().append(articleRows.toString());

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
