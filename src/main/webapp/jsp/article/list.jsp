<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
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

	<a href="../home/main">메인으로 이동</a>

	<h2>게시글 목록</h2>


	<table style="border-collapse: collapse; border-color: black;"
		border="1px";>
		<thead>
			<tr>
				<th>번호</th>
				<th>등록날짜</th>
				<th>수정날짜</th>
				<th>제목</th>
				<th>내용</th>
				<th>삭제</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<td><%=articleRows.get(0).get("id")%>번</td>
				<td><%=articleRows.get(0).get("regDate")%></td>
				<td><%=articleRows.get(0).get("updateDate")%></td>
				<td><%=articleRows.get(0).get("title")%></td>
				<td><%=articleRows.get(0).get("body")%></td>
				<td><a href="#">삭제</a></td>
			</tr>
			<tr>
				<td><%=articleRows.get(1).get("id")%>번</td>
				<td><%=articleRows.get(1).get("regDate")%></td>
				<td><%=articleRows.get(1).get("updateDate")%></td>
				<td><%=articleRows.get(1).get("title")%></td>
				<td><%=articleRows.get(1).get("body")%></td>
				<td><a href="#">삭제</a></td>
			</tr>
		</tbody>

	</table>

	<!-- 
	<ul>
	<%--	<%
		for (Map<String, Object> articleRow : articleRows) {
		%>
		<li><%=articleRow.get("id")%>번, <a
			href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a>
			, <%=articleRow.get("body")%></li>
		<%
		}
		%>
	</ul>  --%>
 -->

</body>
</html>