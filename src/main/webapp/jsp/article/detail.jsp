<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>article detail</title>
</head>
<body>
	<a href="../home/main">메인으로 이동</a>
	<h2>게시글 상세보기</h2>

	<ul>
		<li><%=articleRow.get("id")%>번</li>
		<li>등록날짜 : <%=articleRow.get("regDate")%></li>
		<li>수정날짜 : <%=articleRow.get("updateDate")%></li>
		<li>작성자 : <%=articleRow.get("name") %></li>
		<li>제목 : <%=articleRow.get("title")%></li>
		<li>내용 : <%=articleRow.get("body")%></li>
	</ul>

	<div>
		<a href="list">리스트로 돌아가기</a>
	</div>


</body>
</html>


