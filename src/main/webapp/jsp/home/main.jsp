<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>

<%
Map<String, Object> loginedMember = (Map<String, Object>) request.getAttribute("loginedMember");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>


	<h1>Main page</h1>
	<%
	if (loginedMember != null) {
	%>
	<div><%=loginedMember.get("name")%>
		회원 로그인 중
	</div>
	<%
	}
	%>


	<ul>
		<li><a href="../article/list">리스트로 이동</a></li>
		<li><a href="../member/join">회원가입</a></li>

		<%
		if (loginedMember == null) {
		%>
		<li><a href="../member/login">로그인</a></li>

		<%
		} else {
		%>

		<li><a href="../member/doLogout">로그아웃</a></li>
		<%
		}
		%>
	</ul>


</body>
</html>




