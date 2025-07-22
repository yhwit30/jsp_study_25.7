package koreaIT.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import koreaIT.util.DBUtil;
import koreaIT.util.SecSql;

@WebServlet("/member/doJoin")
public class MemberDoJoinServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3307/AM_jsp_2025_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "");

			response.getWriter().append("연결성공");

			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			String name = request.getParameter("name");
			System.out.println("loginId : " + loginId);
			System.out.println("loginPw : " + loginPw);
			System.out.println("name : " + name);

			DBUtil dbUtil = new DBUtil(request, response);

			// 아이디 중복체크
			SecSql sql = new SecSql();
			sql.append("SELECT count(*)");
			sql.append("FROM `member`");
			sql.append("WHERE `loginId` = ?", loginId);
			
			boolean isJoinableId = dbUtil.selectRowBooleanValue(conn, sql) == false;
			
			System.out.println("isJoinableId : " + isJoinableId );
			
			if (!isJoinableId) {
				response.getWriter().append(String.format("<script>alert('%s는 이미 사용중');location.replace('join'); </script>", loginId));
				return;
			}
			
			sql = new SecSql();
			sql.append("INSERT INTO `member`");
			sql.append("SET `regDate` = NOW(),");
			sql.append("`updateDate` = NOW(),");
			sql.append("`loginId` = ?,", loginId);
			sql.append("`loginPw` = ?,", loginPw);
			sql.append("`name` = ?", name);

			int id = dbUtil.insert(conn, sql);
			System.out.println("id : " + id);

            response.getWriter().append(String.format("<script>alert('%d번 회원이 가입됨');location.replace('../article/list'); </script>", id));

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
