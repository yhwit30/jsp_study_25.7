package koreaIT.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import koreaIT.util.DBUtil;
import koreaIT.util.SecSql;

@WebServlet("/article/doDelete")
public class ArticleDoDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		// 로그인 체크
		HttpSession session = request.getSession();
		if (session.getAttribute("loginedMemberId") == null) {
			response.getWriter().append(String.format("<script>alert('로그인 하고 와');location.replace('list'); </script>"));
			return;
		}
		
		
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3307/AM_jsp_2025_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("연결 성공!");

			response.getWriter().append("연결성공");
			DBUtil dbUtil = new DBUtil(request, response);

			int id = Integer.parseInt(request.getParameter("id"));
			
			SecSql sql = new SecSql();
			sql.append("SELECT *");
			sql.append("FROM `article`");
			sql.append("WHERE `id` = ?", id);
			
			Map<String, Object> articleRow = dbUtil.selectRow(conn, sql);
			int articleMemberId = (int) articleRow.get("memberId");
			int loginedMemberId = (int) session.getAttribute("loginedMemberId");
			
			if(articleMemberId != loginedMemberId) {
				response.getWriter().append(String.format("<script>alert('%d번 글에 대한 권한이 없습니다.');location.replace('list'); </script>", id));
				return;
			};


			sql = new SecSql();
			sql.append("DELETE FROM `article`");
			sql.append("WHERE `id` = ?;", id);

			int affectedRow = dbUtil.delete(conn, sql);

            response.getWriter().append(String.format("<script>alert('%d번 글이 삭제됨');location.replace('list'); </script>", id));

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
