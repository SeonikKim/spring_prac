<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과정 개설 리스트</title>
</head>
<body>
	<p>개설된 과목 갯수 : ${ea}</p>
	<!-- DTO에 있는 변수명으로 JSTL 출력 -->
	<!-- form안에 form X, 반복문 안에 form X -->
	<cr:forEach var="cdata" items="${classList}" varStatus="idx">
과정명 : ${cdata['class_name']}, 강사명 : ${cdata.class_teacher}, 수강료 :${cdata.class_price} 
<input type="button" value="수정" onclick="mac_modi('${cdata.midx}')">
		<input type="button" value="삭제" onclick="mac_del('${cdata.midx}')">
		<br>
	</cr:forEach>

	<!-- 해당 게시물의 고유값을 POST backend 전송 보안상 위험함...-->
	 <form id="frm" method="post" >
		<input type="hidden" name="midx" value="">
	</form>  
	<div id = "msg">
	</div>

</body>
<script>
	function mac_modi(n) {
		//이렇게 해야 보안 좋음 ~ 
		/*
		var m = document.getElementById("msg");
		m.innerHTML = `<form id="frm" method="post" action="./macbook_modify.do">
		<input type="hidden" name="midx" value="`+n+`">
		</form> `;
		frm.submit();
		*/
		
		
		//POST (form통신)
		frm.midx.value=n;
		frm.action = "./macbook_modify.do"; //한 폼에 여러 동작을 만들 때에는 action을 자바스크립트에서 핸들링 해준다.
		frm.submit();

		
// 		frm.midx.value=n;
		// 	console.log(n);
		//GET
// 		location.href = './macbook_modify.do?midx=' + n;

	}

	function mac_del(n) {
		if(confirm("해당 과정을 삭제하시겠습니까? \n삭제 시 데이터는 복구되지 않습니다,")){
			
		frm.midx.value=n;
		frm.action = "./macbook_delete.do";
		frm.submit();
		}

	}
</script>
</html>