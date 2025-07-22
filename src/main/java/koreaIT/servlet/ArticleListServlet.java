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

import koreaIT.util.DBUtil;
import koreaIT.util.SecSql;

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {

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

			// 파라미터
			int page = 1;
			
			if (request.getParameter("page") != null && request.getParameter("page").length() != 0) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			System.out.println("page : " + page);

			int itemsInAPage = 10;
			int limitFrom = (page - 1) * itemsInAPage;
			
			System.out.println("itemsInAPage : " + itemsInAPage);
			System.out.println("limitFrom : " + limitFrom);
			
            DBUtil dbUtil = new DBUtil(request, response);
            
            SecSql sql = new SecSql();
            sql.append("SELECT count(*)");
            sql.append("FROM `article`;");
            
            int totalCnt = DBUtil.selectRowIntValue(conn, sql);
            int totalPage = (int)Math.ceil(totalCnt/(double)itemsInAPage);

            
            sql = new SecSql();
            sql.append("SELECT *");
            sql.append("FROM `article`");
            sql.append("ORDER BY `id` DESC");
            sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);
            
            List<Map<String, Object>> articleRows = dbUtil.selectRows(conn, sql);
            
            
            System.out.println("totalCnt : "+totalCnt);
			System.out.println("totalPage : "+totalPage);
            
            request.setAttribute("articleRows", articleRows); // jsp에 데이터를 넘겨준다.
            request.setAttribute("page", page); 
            request.setAttribute("totalCnt", totalCnt); 
            request.setAttribute("totalPage", totalPage); 
            
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
