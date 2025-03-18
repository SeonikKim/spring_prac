<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 
    servlet -> jsp
    spring -> jstl
    spring-boot -> jstl, thymeleaf
     -->
    <%
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--  controller(ModelAndView) -> jstl 출력${} 주석도 %로 써야함 --%>
상품명 : ${pdnm}<br>
상품코드 : ${pcode}<br>
상품가격 : ${pmoney}<br>
</body>
</html>