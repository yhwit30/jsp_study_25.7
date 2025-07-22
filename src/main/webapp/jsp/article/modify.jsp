<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>

<%  
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>article modify</title>
</head>
<body>
<a href="../home/main">메인으로 이동</a>
<a href="list">리스트로 돌아가기</a>
<h2><%=articleRow.get("id") %>번 글 수정하기</h2>



<form action="doModify" method = "POST">
	<input type="hidden" name = "id" value=<%=articleRow.get("id") %>>
	<div>제목 : <input type="text" name="title" placeholder="<%=articleRow.get("title") %>"></div>
	<div>내용 : <input style="width:500px; height:500px;" type="text" name="body" value=<%=articleRow.get("body") %>></div>
	<button type = "submit">수정</button>
</form>





</body>
</html>
