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

	private boolean isLogined() {
		return request.getSession().getAttribute("loginedMemberId") != null;
	}

	private int getLoginedMemberId() {
		return (int) request.getSession().getAttribute("loginedMemberId");
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

	public void showDetail() throws ServletException, IOException {

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

	}

	public void showWrite() throws ServletException, IOException {
		
		// 로그인 체크
		if (!isLogined()) {
			response.getWriter().append("<script>alert('로그인 하고 와');location.replace('../home/main'); </script>");
			return;
		}

		request.getRequestDispatcher("/jsp/article/write.jsp").forward(request, response);

	}

	public void doWrite() throws ServletException, IOException {

		// 로그인 체크
		if (!isLogined()) {
			response.getWriter().append("<script>alert('로그인 하고 와');location.replace('../home/main'); </script>");
			return;
		}

		String title = request.getParameter("title");
		String body = request.getParameter("body");
		System.out.println("title : " + title);
		System.out.println("body : " + body);

		DBUtil dbUtil = new DBUtil(request, response);

		SecSql sql = new SecSql();
		sql.append("INSERT INTO `article`");
		sql.append("SET `regDate` = NOW(),");
		sql.append("`updateDate` = NOW(),");
		sql.append("`memberId` = ?,", getLoginedMemberId());
		sql.append("`title` = ?,", title);
		sql.append("`body` = ?", body);

		int id = dbUtil.insert(conn, sql);
		System.out.println("id : " + id);

		response.getWriter()
				.append(String.format("<script>alert('%d번 글이 등록됨');location.replace('list'); </script>", id));

	}

	public void showModify() throws ServletException, IOException {

		// 로그인 체크
		if (!isLogined()) {
			response.getWriter().append("<script>alert('로그인 하고 와');location.replace('../home/main'); </script>");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("modify id : " + id);

		DBUtil dbUtil = new DBUtil(request, response);

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `article`");
		sql.append("WHERE `id` = ?", id);

		Map<String, Object> articleRow = dbUtil.selectRow(conn, sql);

		if (articleRow.isEmpty()) {
			response.getWriter().append(
					String.format("<script>alert('%d번 글은 존재하지 않습니다.');location.replace('list'); </script>", id));
			return;
		}

		System.out.println("modify articleRow : " + articleRow.toString());

		request.setAttribute("articleRow", articleRow);

		request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);

	}

	public void doModify() throws ServletException, IOException {

		// 로그인 체크
		if (!isLogined()) {
			response.getWriter().append("<script>alert('로그인 하고 와');location.replace('../home/main'); </script>");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		System.out.println("title : " + title);
		System.out.println("body : " + body);

		DBUtil dbUtil = new DBUtil(request, response);

		SecSql sql = new SecSql();
		sql.append("UPDATE `article`");
		sql.append("SET `updateDate` = NOW(),");
		sql.append("`title` = ?,", title);
		sql.append("`body` = ?", body);
		sql.append("WHERE `id` = ?", id);

		dbUtil.update(conn, sql);

		response.getWriter()
				.append(String.format("<script>alert('%d번 글이 수정됨');location.replace('list'); </script>", id));

	}

	public void doDelete() throws ServletException, IOException {

		// 로그인 체크
		if (!isLogined()) {
			response.getWriter().append("<script>alert('로그인 하고 와');location.replace('../home/main'); </script>");
			return;
		}

		int id = Integer.parseInt(request.getParameter("id"));

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `article`");
		sql.append("WHERE `id` = ?", id);

		DBUtil dbUtil = new DBUtil(request, response);
		Map<String, Object> articleRow = dbUtil.selectRow(conn, sql);
		int articleMemberId = (int) articleRow.get("memberId");
		int loginedMemberId = getLoginedMemberId();

		if (articleMemberId != loginedMemberId) {
			response.getWriter().append(
					String.format("<script>alert('%d번 글에 대한 권한이 없습니다.');location.replace('list'); </script>", id));
			return;
		}
		;

		sql = new SecSql();
		sql.append("DELETE FROM `article`");
		sql.append("WHERE `id` = ?;", id);

		int affectedRow = dbUtil.delete(conn, sql);

		response.getWriter()
				.append(String.format("<script>alert('%d번 글이 삭제됨');location.replace('list'); </script>", id));

	}

}
