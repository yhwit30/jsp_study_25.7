package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import koreaIT.util.DBUtil;
import koreaIT.util.SecSql;

public class ArticleController {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;

	public ArticleController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.request = request;
		this.response = response;
		this.conn = conn;
	}

	public void showList() throws ServletException, IOException {

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
		int totalPage = (int) Math.ceil(totalCnt / (double) itemsInAPage);

		sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `article` a");
		sql.append("INNER JOIN `member` m");
		sql.append("ON a.memberId = m.id");
		sql.append("ORDER BY a.`id` DESC");
		sql.append("LIMIT ?, ?", limitFrom, itemsInAPage);

		List<Map<String, Object>> articleRows = dbUtil.selectRows(conn, sql);

		System.out.println("totalCnt : " + totalCnt);
		System.out.println("totalPage : " + totalPage);

		// 로그인정보
		HttpSession session = request.getSession();
		Map<String, Object> loginedMember = (Map<String, Object>) session.getAttribute("loginedMember");
		int loginedMemberId = -1;
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		request.setAttribute("articleRows", articleRows); // jsp에 데이터를 넘겨준다.
		request.setAttribute("page", page);
		request.setAttribute("totalCnt", totalCnt);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("loginedMember", loginedMember);

		request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
	}

}
