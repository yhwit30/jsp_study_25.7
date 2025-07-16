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

	<h2>게시글 목록</h2>

	<ul>
		<li><%=articleRows.get(0).get("id")%>번, <%=articleRows.get(0).get("title")%>,
			<%=articleRows.get(0).get("body")%></li>
		<li><%=articleRows.get(1).get("id")%>번, <%=articleRows.get(0).get("title")%>,
			<%=articleRows.get(0).get("body")%></li>
		<li><%=articleRows.get(2).get("id")%>번, <%=articleRows.get(0).get("title")%>,
			<%=articleRows.get(0).get("body")%></li>
	</ul>






</body>
</html>