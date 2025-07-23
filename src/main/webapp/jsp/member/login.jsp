<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member login</title>
</head>
<body>

<a href="../home/main">메인으로 이동</a>

<script type = "text/javascript">
function LoginForm_submit(form){

	
	form.submit();
}

</script>


<h2>로그인</h2>

<form onsubmit="LoginForm_submit(this); return false;" action="doLogin" method = "POST">
	<div>아이디 : <input type="text" name="loginId" placeholder="아이디 입력"/></div>
	<div>비밀번호 : <input type="text" name="loginPw"placeholder="비밀번호 입력" autocomplete="off"/></div>
	<button type = "submit">로그인</button>
</form>




</body>
</html>