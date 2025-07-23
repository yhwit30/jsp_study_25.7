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

@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3307/AM_jsp_2025_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "");

			response.getWriter().append("연결성공");

			HttpSession session = request.getSession();
			Map<String, Object> loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
						
			int loginedMemberId = (int) loginedMember.get("id");
			
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			System.out.println("title : " + title);
			System.out.println("body : " + body);

			DBUtil dbUtil = new DBUtil(request, response);

			SecSql sql = new SecSql();
			sql.append("INSERT INTO `article`");
			sql.append("SET `regDate` = NOW(),");
			sql.append("`updateDate` = NOW(),");
			sql.append("`memberId` = ?,", loginedMemberId);
			sql.append("`title` = ?,", title);
			sql.append("`body` = ?", body);

			int id = dbUtil.insert(conn, sql);
			System.out.println("id : " + id);

            response.getWriter().append(String.format("<script>alert('%d번 글이 등록됨');location.replace('list'); </script>", id));

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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
