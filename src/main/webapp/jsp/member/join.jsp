<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member join</title>
</head>
<body>

<a href="../home/main">메인으로 이동</a>

<script type = "text/javascript">
function JoinForm_submit(form){
	let loginId = form.loginId.value.trim();
	let loginPw = form.loginPw.value.trim();
	let loginPwConfirm = form.loginPwConfirm.value.trim();
	let name = form.name.value.trim();
	console.log("loginPw : ", loginPw);
	console.log("loginPwConfirm : ", loginPwConfirm);
	
	if(loginId.length == 0){
		alert('아이디 작성하세요');
		form.loginId.focus();
		return;
	}
	if(loginPw.length == 0){
		alert('비밀번호 작성하세요');
		form.loginPw.focus();
		return;
	}
	if(loginPwConfirm.length == 0){
		alert('비밀번호 확인란 작성하세요');
		form.loginPwConfirm.focus();
		return;
	}
	if(name.length == 0){
		alert('이름 작성하세요');
		form.name.focus();
		return;
	}
	
	if (loginPw != loginPwConfirm){
		alert('비밀번호 일치하지 않음');
		form.loginPw.focus();
		return;
	}
	
	form.submit();
	
}

</script>


<h2>회원가입</h2>

<form onsubmit="JoinForm_submit(this); return false;" action="doJoin" method = "POST">
	<div>아이디 : <input type="text" name="loginId" placeholder="아이디 입력"/></div>
	<div>비밀번호 : <input type="text" name="loginPw"placeholder="비밀번호 입력" autocomplete="off"/></div>
	<div>비밀번호 확인 : <input type="text" name="loginPwConfirm"placeholder="비밀번호 확인 입력" autocomplete="off"/></div>
	<div>이름 : <input type="text" name="name" placeholder="이름 입력"/></div>
	<button type = "submit">가입</button>
</form>




</body>
</html>