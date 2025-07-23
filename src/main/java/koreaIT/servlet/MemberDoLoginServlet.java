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

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {

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
			System.out.println("loginId : " + loginId);
			System.out.println("loginPw : " + loginPw);

			DBUtil dbUtil = new DBUtil(request, response);

			SecSql sql = new SecSql();
			sql.append("SELECT *");
			sql.append("FROM `member`");
			sql.append("WHERE `loginId` = ?", loginId);

			Map<String, Object> memberRow = dbUtil.selectRow(conn, sql);
			System.out.println(memberRow.toString());
			
			if (memberRow.isEmpty()) {
//				없는 아이디
				response.getWriter().append(String.format("<script>alert('%s는 없는 회원입니다.');location.replace('../member/login'); </script>", loginId));
				return;
			}
			if (memberRow.get("loginPw").equals(loginPw) == false) {
				response.getWriter().append(String.format("<script>alert('비밀번호 불일치');location.replace('../member/login'); </script>"));
				return;
			}
			
			HttpSession session = request.getSession();
			
			session.setAttribute("loginedMember", memberRow); // 로그인 정보가 세션에 저장됨
			session.setAttribute("loginedMemberId", (int)memberRow.get("id"));
			
			
			response.getWriter().append(String.format("<script>alert('%s 회원 로그인됨');location.replace('../article/list'); </script>", loginId));
			


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
