<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="board.loghistory.ex.LogHistoryDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.td_color {
	background-color: #EAEAEA;
	font-size: 13px;
}

.centered {
	display: table;
	margin-left: auto;
	margin-right: auto;
}

a:hover {
	text-decoration: underline;
}

a {
	color: grey;
}

tr.b {
	border-bottom: 1px solid lightgrey;
}
</style>
<title>Insert title here</title>
</head>
<body style="text-align: center">
	<jsp:include page="../header.jsp"></jsp:include>
	<%
	String pageNum = (String) request.getParameter("pageNum");
	System.out.println("pageNum : "+pageNum);
	int pageNo;
	if (pageNum == null) {
		pageNo = 1;
	} else {
		pageNo = Integer.parseInt(pageNum);
	}
	
	LogHistoryDAO dao = new LogHistoryDAO();
	String tc = (String) request.getAttribute("totalCount");
	String content = (String) request.getAttribute("content");
	String check = (String) request.getAttribute("check");
	
	int TotalCount = 0;
	if (tc != null) {
		TotalCount = Integer.parseInt(tc);
	}
	System.out.println("TotalCount : "+TotalCount);
	int No = TotalCount - (pageNo - 1) * 10;
	
	int pageCount,pre,next;
	
	if(pageNo > 1)
		pre = pageNo - 1;
	else
		pre = pageNo;
	
	if(TotalCount != pageNo*10)
		pageCount = TotalCount/10 + 1;
	else
		pageCount = TotalCount/10;
	
	if((pageNo+1)<=pageCount)
		next = pageNo + 1;
	else
		next = pageCount;
	
%>
	<br />
	<h3>LogHistory</h3>
	<br>
	<div class="centered">
		<table style="width: 1000px; text-align: center;">
			<tr style="color: grey; height: 50px" class="b">
				<td style="width: 30px">순번</td>
				<td style="width: 30px">id</td>
				<td style="width: 50px">이름</td>
				<td style="width: 50px">상태</td>
				<td style="width: 50px">시간</td>
				<td style="width: 50px">ip</td>
			</tr>
			<c:forEach var="l" items="${list}">
				<tr class="b">
					<td style="width: 100px; height: 65px">${l.log_num}</td>
					<td style="width: 100px; height: 65px">${l.log_id}</td>
					<td style="width: 100px">${l.log_name}</td>

					<c:if test="${l.record eq 'login'}">
						<td style="color: green">${l.record}</td>
					</c:if>
					<c:if test="${l.record eq 'logout'}">
						<td style="color: red">${l.record}</td>
					</c:if>

					<td style="width: 100px">${l.log_time}</td>
					<td style="width: 100px">${l.log_ip}</td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<div>
			<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link"
					href="/board2/loghistory.do?pageNum=<%=pre%>&content=<%=content%>">Previous</a></li>
				<%for (int i = 1; i<=pageCount;i++){ %>
				<li class="page-item"><a class="page-link"
					href="/board2/loghistory.do?pageNum=<%=i%>&content=<%=content%>">&nbsp;<%= i %>&nbsp;
				</a></li>
				<% } %>
				<li class="page-item"><a class="page-link"
					href="/board2/loghistory.do?pageNum=<%=next%>&content=<%=content%>">Next</a></li>
			</ul>
			</nav>
		</div>

		<DIV class='aside_menu'>
			<FORM name='frm' method='post' action="/board2/loghistory.do">
				<ASIDE style='float: right;'> 
				<SELECT name='col'>
					<OPTION value='none'>전체 목록</OPTION>
					<OPTION value='searchId'>아이디</OPTION>
				</SELECT> 
				<input type='text' name="content" size='20'> 
				<input type="hidden" name="pageNum" value="<%=pageNo%>"> </ASIDE>
				<input type="hidden" name="pageNum" value="<%=pageNo%>"> 
					<button type="submit">검색</button>
					</ASIDE>
			</FORM>
			<DIV class='menu_line' style='clear: both;'></DIV>
		</DIV>

		<form method='post' action="/board2/excelDownData.do">
			<button type='submit'>엑셀 다운로드</button>
		</form>
	</div>
</body>
</html>