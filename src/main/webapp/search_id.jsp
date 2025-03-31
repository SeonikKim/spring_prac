<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 기능(member_mapper.xml)</title>
</head>
<body>
<form action="./idsearch.do" method="post" id="frm">
고객명 : <input type="text" name="mname"> <br>
이메일 : <input type="text" name="memail" > <br>
<label>
<input type="checkbox" value="Y" name="mcheck">개인정보 약관동의
</label><br>
<input type="button" value="아이디 찾기" onclick="gopage()">
</form>
</body>
<script>
function gopage(){
	//검토 했다고 치고 한다..
	frm.submit();
}
</script>
</html>