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
<div><a href="join">회원가입</a></div>

<script type = "text/javascript">
function LoginForm_submit(form){

	let loginId = form.loginId.value.trim();
	let loginPw = form.loginPw.value.trim();
	
	if(loginId.length == 0){
		alert('아이디 작성하세요.');
		form.loginId.focus();
		return;
	}
	if(loginPw.length == 0){
		alert('비밀번호 작성하세요.');
		form.loginPw.focus();
		return;
	}
	
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