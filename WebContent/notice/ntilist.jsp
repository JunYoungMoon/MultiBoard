<%@page import="board.notice.ex.ntiDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Gaegu|Rancho"
	rel="stylesheet">
<title>게시판</title>
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumpenscript.css);

/* .font2 {
	font-family: 'Gaegu', cursive;
	font-size: 20px;
}

.font {
	font-family: 'Nanum Pen Script', cursive;
	font-size: 20px;
} */

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
</head>
<body style="text-align: center">
	<jsp:include page="../header.jsp"></jsp:include>
	<br />
	<h3>Notice</h3>
	<br>
	<div class="centered">
		<%
		String word = request.getParameter("word");		
		String col = request.getParameter("col");
		String pageNum = request.getParameter("key");
		
		String ip = request.getRemoteAddr();
		
		System.out.print(ip);
		
		session.getAttribute("id");
		session.getAttribute("name");
		
		System.out.println(pageNum);
		int pageNo;

		if (pageNum == null) {
		 pageNo = 1;
		} else {
		pageNo = Integer.parseInt(pageNum);
		}	  
		
		ntiDAO dao = new ntiDAO();
		
		String tc = (String)request.getAttribute("totalCount");
		int TotalCount = 0;
		
		if(tc!=null){
			TotalCount = Integer.parseInt(tc);
		}
		
		System.out.println(TotalCount);
		int No = TotalCount - (pageNo-1) * 10 ;

		int pageCount = TotalCount/10 + 1; 
		
		int pre;
		int next;
		
		if(pageNo > 1)
			pre = pageNo - 1;
		else
			pre = pageNo;
		
		if((pageNo+1)<=pageCount)
			next = pageNo + 1;
		else
			next = pageCount;
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		if (col == null && word == null)
		{
		%>
		<table style="width: 1000px; text-align: center;">
			<tr style="color: grey; height: 50px" class="b">
				<td style="width: 30px">순번</td>
				<td class="font">제목</td>
				<td style="width: 50px">작성자</td>
				<td style="width: 50px">작성일자</td>
			</tr>
			<c:forEach var="l" items="${list}">
				<tr class="b">
					<td style="width: 100px; height: 65px"><%=No%></td>
					<td class="font2"><a href="ntiView.mjy?key=${l.count}">${l.title}</td>
					<td style="width: 100px">${l.name}</td>
					<td style="width: 100px">${l.date}</td>
					<% No = No - 1; %>
				</tr>
			</c:forEach>
		</table>
		<br>
		<div align="right">
			<% if(("admin").equals(session.getAttribute("id"))) {%>
			<p
				style="padding: 2px 25px 3px 25px; border: 1px solid lightgrey; display: inline;">
				<a href="../board2/notice/input.jsp">WRITE</a>
			</p>
			<%}%>
		</div>
		<div>
			<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link"
					href="../board2/ntiList.mjy?key=<%=pre%>">Previous</a></li>


				<c:forEach var="i" begin="${dto.startPage}" end="${dto.endPage}"
					step="1">
					<li class="page-item"><a class="page-link"
						href="../board2/ntiList.mjy?key=${i}">${i}</a></li>
				</c:forEach>

				<li class="page-item"><a class="page-link"
					href="../board2/ntiList.mjy?key=<%=next%>">Next</a></li>

			</ul>
			</nav>
		</div>
		<%}	else{ %>

		<table style="width: 1000px; text-align: center;">
			<tr style="color: grey; height: 50px" class="b">
				<td style="width: 150px">순번</td>
				<td style="width: 150px">제목</td>
				<td style="width: 150px">작성자</td>
				<td style="width: 100px">작성일자</td>
			</tr>

			<c:forEach var="l" items="${list}">
				<tr class="b">
					<td style="width: 150px; height: 65px"><%=No%></td>
					<td style="width: 150px"><a
						href="ntiView.mjy?key=${l.count}">${l.title}</td>
					<td style="width: 150px">${l.name}</td>
					<td style="width: 100px">${l.date}</td>
					<% No = No - 1; %>
				</tr>
			</c:forEach>
		</table>
		<br>
		<div align="right">
			<% if(("admin").equals(session.getAttribute("id"))) {%>
			<p
				style="padding: 2px 25px 3px 25px; border: 1px solid lightgrey; display: inline;">
				<a href="../board2/notice/input.jsp">WRITE</a>
			</p>
			<%}%>
		</div>

		<div>
			<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link"
					href="../board2/ntiList.mjy?key=<%=pre%>">Previous</a></li>


				<c:forEach var="i" begin="${dto.startPage}" end="${dto.endPage}"
					step="1">
					<li class="page-item"><a class="page-link"
						href="../board2/ntiList.mjy?key=${i}">${i}</a></li>
				</c:forEach>

				<li class="page-item"><a class="page-link"
					href="../board2/ntiList.mjy?key=<%=next%>">Next</a></li>
			</ul>
			</nav>
		</div>

		<%} %>
		<DIV class="aside_menu">
			<FORM name="frm" method="GET" action="../board2/ntiSearch.mjy?">
				<ASIDE style="float: right;"> <SELECT name="col">
					<!-- 검색 컬럼 -->
					<OPTION value="none">전체 목록</OPTION>
					<OPTION value="content">제목</OPTION>
				</SELECT> <input type="text" name="word" size="20" value="">
				<button type="submit">검색</button>
				</ASIDE>
			</FORM>
			<DIV class="menu_line" style="clear: both;"></DIV>
		</DIV>
	</div>
	<jsp:include page="../footer.html" />
</body>
</html>