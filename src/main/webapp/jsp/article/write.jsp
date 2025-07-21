<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>article write</title>
</head>
<body>

<h2>글쓰기</h2>

<form action="doWrite" method = "POST">
	<div>제목 : <input type="text" name="title"/></div>
	<div>내용 : <input type="text" name="body"/></div>
	<button type = "submit">작성</button>
</form>




</body>
</html>