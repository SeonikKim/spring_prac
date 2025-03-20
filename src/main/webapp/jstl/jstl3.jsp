<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%
// String[] data = new String[] {"hong","kim","park","lee"};
// request.setAttribute("data", data);
ArrayList<String> data = new ArrayList();
data.add("hong");
data.add("kim");
data.add("park");
data.add("lee");
request.setAttribute("data", data);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초3(반복문 - for each)</title>
</head>
<body>
	<table border="1">
		
		<tr>
		<!--
		 forEach : JSTL 내 유일한 반복문
		 begin : 시작값
		 end : 종료값		
		 -->
		 
<%-- 	기본형태	
			<cr:forEach var="z" begin="1" end="5"> 
			<td>${z}</td>
			</cr:forEach>--%>
<!-- 			jsp 객체 바로 쓴거 -->
<%-- 			<cr:forEach var="z" items="<%=data %>"> --%>
<%-- items : 배열값을 받는 속성 -> ${data} setAttribute의 값 
배열값을 사용하여 출력하는 역할 begin="0" 으로 시작해야 함 (배열번호 0이니까)--%>
			<cr:forEach var="z" items="${data}" begin="0" end="3">
			<cr:if test="${z != 'kim' }">
			<td>${z}</td>
			</cr:if>
			</cr:forEach>
			
		</tr>
	</table>
</body>
</html>