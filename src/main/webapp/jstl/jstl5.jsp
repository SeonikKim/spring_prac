<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초 = (DB연결)</title>
</head>
<body>
<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver"
url="jdbc:mysql://localhost:3306/mrp" user="project" password="a123456"
/>
<!-- ddl1 -->
<%-- <sql:query var="ps" sql="select * from event" dataSource="${db}"></sql:query> --%>

<!-- ddl2 -->
<sql:query var="ps" dataSource="${db}">
select * from event
</sql:query>

${ps.rows}<br>
<!-- rows : db의 rows의 전체값을 말합니다.  -->
<cr:forEach var="row" items="${ps.rows}">
<!-- 원시배열 형태가 아닌 키 형식으로도 가능 -->
고객명 : ${row.ename}, 이메일 : ${row['email']}, 등록일 : ${fn:substring(row['ejoin'],0,10)}<br>
<!-- fn은 태그없이 결과값에 옵션 형태로 위의 형식처럼 사용한다 -->
</cr:forEach>
</body>
</html>