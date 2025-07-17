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
				<th>삭제</th>
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
				<td><a href="delete?id=<%=articleRow.get("id")%>">삭제</a></td>
			</tr>

			<%
			}
			%>


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