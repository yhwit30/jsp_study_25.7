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
<body>

<a href="../home/main">메인으로 이동</a>

	<h2>게시글 목록</h2>

	<ul>
		<%
		for (Map<String, Object> articleRow : articleRows) {
		%>
		<li><%=articleRow.get("id")%>번, 
		<a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a>
		, <%=articleRow.get("body")%></li>
		<%
		}
		%>

	</ul>


</body>
</html>