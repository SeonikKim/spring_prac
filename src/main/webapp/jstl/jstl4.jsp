<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    String[][] member = {
    		{"홍길동","강감찬"},
    		{"A형","B형"},
    		{"40세","50세"}
    };
    ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
    ArrayList<String> data = new ArrayList<String>();
    data.add("에어프라이어");
    data.add("냉장고");
    data.add("에어컨");
    all.add(data);
    
    ArrayList<String> data2 = new ArrayList<String>();
    data2.add("250000");
    data2.add("350000");
    data2.add("550000");
    all.add(data2);
    
//     out.print(all);
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초- (반복문 2차)</title>
</head>
<body>
<!-- varStatus : 배열번호 0~ => 이름.index  -->
<cr:set var="aa" value="<%=member[1]%>"/><!-- 각 배열의 각 그룹번호를 세팅 -->
<cr:set var="bb" value="<%=member[2]%>"/><!-- 각 배열의 각 그룹번호를 세팅 -->
<cr:forEach var="cc" items="<%=member[0]%>" varStatus="no">
고객명 :${cc} , 혈액형 : ${aa[no.index]}, 나이 : ${bb[no.index]} <br>
</cr:forEach>
<br><br><br>
<cr:set var="dd" value="<%=all.get(1)%>"/>
<cr:forEach var="ee" items="<%=all.get(0)%>" varStatus="no">
번호 : ${no.index}, 제품명 : ${ee} , 가격 : ${dd.get(no.index)}<br>
</cr:forEach>

</body>
</html>