<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<String> fnms = (ArrayList) request.getAttribute("fnms");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹디렉토리 파일리스트 출력</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>파일명</th>
			<th>삭제</th>
		</tr>
		<%
		int w = 0;
		while (w < fnms.size()) {
		%>
		<tr>
			<td><%=fnms.get(w)%></td>
			<td><input type="button" value="삭제"
				onclick="file_del('<%=fnms.get(w)%>')"></td>
		</tr>
		<%
		w++;
		}
		%>

	</table>
	<!-- 	선택한 파일명만 post 전송 -->
	<form method="post" action="./filedel.do" id="fm">
		<input type="hidden" name="fnm" value="">
	</form>
</body>
<script>
	function file_del(fnm) {
		fm.fnm.value = fnm;
		fm.submit();
	}
</script>
</html>