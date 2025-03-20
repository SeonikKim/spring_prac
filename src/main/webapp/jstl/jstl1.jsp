<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- jstl 엔진 -->
    <%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- jstl 각종 함수 -->
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!-- jstl DB관련사항 -->
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
    <!-- jstl 부가옵션(금액, 날짜정보, 시간, 통화기호 등) -->
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
    
    <%
    String user = "홍길동"; //.JSP 변수
    
    HttpSession hs = request.getSession();
    hs.setAttribute("ssdata", "15881004");
	String se = (String)hs.getAttribute("ssdata");
	out.print(se);
	
	String tels = (String)hs.getAttribute("tel");
	out.print("////"+tels);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초문법1</title>
</head>
<body>
<!-- jstl 태그 형태 -->
<!-- core 태그 속성 중 set(=setAttribute), var : 변수명, value : 값 -->
<cr:set var="a" value="강감찬"/>
<input type="text" name="mname" value="${a}"><br>
<cr:set var="nm" value="<%=user%>"/>
고객명 : ${nm}
<cr:out value="값 출력합니다."/> <!-- out.print와 같음 근데 잘 안씀.. -->
<br><br><br>
<p>JSP Session 값</p><br>
<!-- jstl로 세션을 생성하는 방식 scope : session, request, page ... var가 중복되면 초기화됨  -->
<cr:set var = "tel" value="021004" scope="session"/>
JSP에 Session데이터 : ${ssdata}<br>
세션 데이터 : ${tel}
<br><br><br><br>
<% String money = "500000"; %>
<cr:set var = "kk" scope="request" <%=money%> /><!-- scope="request" 는 생략 가능 -->
${kk}
</body>
</html>