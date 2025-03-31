<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배너 리스트 페이지!</title>
</head>
<body>
	<script>
		function spage() {
			if (sform.search.value == "") {
				alert("배너명을 입력하세요")
				return false;
			} else {
				return;
			}
		}
	</script>
	<form action="./bannerlist" method="get" id="sform"
		onsubmit="return spage()">
		<p>
			<input type="text" name="search" placeholder="배너명으로 검색"
				value="${search}"> <input type="submit" value="검색">
			<input type="button" value="전체목록"
				onclick="location.href='./bannerlist';">
		</p>
	</form>

	<table border="1" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th><input type="checkbox" id="allck"
					onclick="check_all(this.checked)"></th>
				<th width="80">번호</th>
				<th width="300">배너명</th>
				<th width="100">이미지</th>
				<th width="150">파일명</th>
				<th width="150">등록일</th>
			</tr>
		</thead>
		<tbody>
			<cr:set var="ino" value="${total-userpage}" />
			<!-- 게시물 일련번호 세팅 -->
			<!-- 배열값을 조건문으로 처리 시 function 사용해 length로 처리 -->
			<cr:if test="${fn:length(all)==0}">

				<tr height="50">
					<td colspan="6" align="center">검색된 내용이 없습니다</td>
				</tr>

			</cr:if>
			<!-- 반복문은 절대 중복 id 넣으면 안됨.. -->
			<!-- 			<form> -->
			<cr:forEach var="bn" items="${all}" varStatus="idx">
				<tr height="50">
					<!-- 			bidx : DB에서 사용된 auto_increment 값 -->
					<td><input type="checkbox" name="ckbox" value="${bn.bidx}"
						onclick="checkdata()"></td>
					<td align="center">${ino-idx.index}</td>
					<td align="center">${bn.bname}</td>
					<td align="center"><cr:if test="${bn.file_url == null}">
				No Img
				</cr:if> <cr:if test="${bn.file_url != null}">
							<img src="..${bn.file_url}" style="width: 100px; height: 50px">
						</cr:if></td>
					<td align="center"><a href="..${bn.file_url}" target="_blank"
						title="${bn.file_new}">${bn.file_ori}</a></td>
					<!-- 				아래와 같이 사용해도 됨 -->
					<%-- 				<td><a href="../upload/${bn.file_new}">${bn.file_ori}</a> </td> --%>
					<td align="center">${fn:substring(bn.bdate,0,10)}</td>
				</tr>
			</cr:forEach>
			<!-- 			</form> -->

		</tbody>
	</table>
	<!-- 삭제기능 -->
	<!-- form전송으로 선택된 값을 삭제하는 프로세서 -->
	<form id="dform" action="./bannerdel" method="post">
		<input type="hidden" name="ckdel" value="">
	</form>
	<input type="button" value="선택삭제" onclick="check_del()">


	<!-- Paging -->
	<div
		style="width: 100%; display: flex; justify-content: center; padding-top: 10px;">
		<table border="1" cellpadding="0" cellspacing="0">
			<tbody>
				<tr height="30">
					<!-- Controller에서 데이터의 전체 갯수를 받음 해당 값을 한 페이지당 5개씩 출력하는 구조
	수식을 입력해 총 페이지 번호를 생성하여 출력 -->

					<cr:set var="pageidx" value="${total / 5 + 1-((total/5)%1)%1}" />


					<cr:forEach var="no" begin="1" end="${pageidx}" step="1">
						<td style="cursor: pointer;" width="30" align="center"
							onclick="pg('${no}')">${no}</td>
					</cr:forEach>

				</tr>
			</tbody>
		</table>
	</div>
</body>

<script>
	function pg(no) {
		location.href = './bannerlist?pageno=' + no;
	}

	//버튼 전체선택 함수
	function check_all(ck) {
		// 	Element : Id사용 시 , Elements : Name, Class 처럼 중복 가능한 속성일 때
		var ea = document.getElementsByName("ckbox");
		ea[0].checked = true;

		//줄여서 이렇게 사용해도됩니다~
		// 	var w = 0;
		// 	while(w<ea.length){
		// 		ea[w].checked = ck; // "checked" 써도 됨
		// 		w++;

		if (ck == true) {//전체선택 시
			var w = 0;
			while (w < ea.length) {
				ea[w].checked = true; // "checked" 써도 됨
				w++;
			}
		} else { //해제 시
			var w = 0;
			while (w < ea.length) {
				ea[w].checked = false; // "checked" 써도 됨
				w++;
			}
		}

	}

	//삭제(배열화 하여 hidden으로 핸들링)
	function check_del() {

		var ar = new Array(); //script 배열
		var ob = document.getElementsByName("ckbox");
		var w = 0;
		while (w < ob.length) {
			if (ob[w].checked == true) {
				ar.push(ob[w].value);
			}
			w++;
		}
		dform.ckdel.value = (ar);
		
		if(confirm("해당 데이터를 삭제 시 복구 불가능합니다.")){
			dform.submit();
		}
	}

	//전체선택 중 일부 체크박스 헤제 시 전체선택 해제
	function checkdata() {
	}
</script>
</html>