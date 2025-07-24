<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
Map<String, Object> loginedMember = (Map<String, Object>) request.getAttribute("loginedMember");

int cPage = (int) request.getAttribute("page");
int totalCnt = (int) request.getAttribute("totalCnt");
int totalPage = (int) request.getAttribute("totalPage");
int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>article list</title>
</head>

<style>
table>thead>tr>th, table>tbody>tr>td {
	padding: 5px;
}
</style>

<body>

	<%@include file="../part/top_bar.jspf"%>


	<a href="../home/main">메인으로 이동</a>
	<%
	if (loginedMemberId != -1) {
	%>
	<div>
		<a href="write">글쓰기</a>
	</div>
	<%
	}
	%>

	<h2>게시글 목록</h2>


	<div>
		총 게시글 수 :
		<%=totalCnt%></div>


	<table
		style="border-collapse: collapse; border-color: black; margin: auto;"
		border="1px";>
		<thead>
			<tr>
				<th>번호</th>
				<th>등록날짜</th>
				<th>수정날짜</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>삭제(todo)</th>
				<th>수정(todo)</th>
			</tr>
		</thead>

		<tbody>
			<%
			for (Map<String, Object> articleRow : articleRows) {
			%>
			<tr>
				<td><%=articleRow.get("id")%>번</td>
				<td><%=articleRow.get("regDate")%></td>
				<td><%=articleRow.get("updateDate")%></td>
				<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a></td>
				<td><%=articleRow.get("body")%></td>
				<td><%=articleRow.get("name")%></td>
				<!-- 이후 상세보기로 이동 todo -->
				<td><a
					onclick="if(confirm('정말 삭제하시겠습니까?') == false){return false;}"
					href="doDelete?id=<%=articleRow.get("id")%>">삭제</a></td>
				<td><a href="modify?id=<%=articleRow.get("id")%>">수정</a></td>

			</tr>

			<%
			}
			%>


		</tbody>

	</table>

	<style>
.page {
	text-align: center;
	margin-top: 20px;
}

.page>a {
	color: black;
	text-decoration: none;
}

.page>a.cPage {
	color: red;
	text-decoration: underline;
}
</style>

	<div class="page">
		<%
		for (int i = 1; i <= totalPage; i++) {
		%>
		<a class="<%=cPage == i ? "cPage" : ""%>" href="list?page=<%=i%>"><%=i%></a>
		<%
		}
		%>
	</div>

</body>
</html>