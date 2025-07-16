package koreaIT;

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

@WebServlet("/ArticleListServlet")
public class ArticleListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/AM_jsp_2025_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
			conn = DriverManager.getConnection(url, "root", "");
			System.out.println("연결 성공!");
			
			response.getWriter().append("연결성공");
			  // select 테스트

            DBUtil dbUtil = new DBUtil(request, response);
            
            SecSql sql = new SecSql();
            sql.append("SELECT *");
            sql.append("FROM `article`");
            
            List<Map<String, Object>> articleRows = dbUtil.selectRows(conn, sql);
            
            request.setAttribute("articleRows", articleRows); // jsp에 데이터를 넘겨준다.
            request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
            
            
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
