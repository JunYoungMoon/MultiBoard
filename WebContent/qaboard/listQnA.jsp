<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "board.qna.ex.qaDao" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<style>
input:hover {
	text-decoration: underline;
}

a:hover {
	text-decoration: underline;
}
a { color:grey;}
/* div {
	position: absolute;
	width: 1000px;
	height: 500px;
	left: 50%;
	top: 50%;
	margin: -250px 0 0 -500px;
} */
tr.b { border-bottom: 1px solid lightgrey; }
.centered {
	display: table;
	margin-left: auto;
	margin-right: auto;
}
</style>
<title>Insert title here</title>
</head>
<body style="text-align: center">
<jsp:include page="../header.jsp"></jsp:include>
<% 

	String content = (String)request.getParameter("search");
	System.out.println("content : "+ content);
 
	String pageNum = (String)request.getParameter("pageNum");
	System.out.println(pageNum);
	int pageNo;

	if (pageNum == null) {
	 pageNo = 1;
	} else {
	pageNo = Integer.parseInt(pageNum);
	}	  
	
	String tc = (String)request.getAttribute("totalCount");
	int TotalCount = 0;
	
	if(tc!=null){
		TotalCount = Integer.parseInt(tc);
	}
	System.out.println(TotalCount);
	int No = TotalCount - (pageNo-1) * 10 ;
	
	
	int pageCount;
	
	int pre;
	int next;
	
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
	<h3>Q & A</h3>
	<div class="centered">
		<table style="width: 1000px; text-align: center;">
		<tr style="color:grey; height: 50px" class="b">
				<td style="width: 150px">No</td>
				<td>Subject</td>
				<td style="width: 150px">Date</td>
				<td style="width: 150px">Name</td>
				<td style="width: 100px">Read</td>
			</tr>
			<c:forEach var="l" items="${list}">
				<tr class="b"  style="height: 50px">
					<td style="width: 150px"><%=No%></td>
					<td>
					<a href="/board2/qaContent.khy?qa_id=${l.qa_id}&check=true">${l.qa_sub}&nbsp;&nbsp;</a> 
					<c:if test="${l.qa_count>0}">
					[답변 <p style="display: inline; vertical-align:bottom">완료</p>]
					</c:if>
					<c:if test="${l.qa_read>=10}">
							<p style="color: red; display: inline; vertical-align: bottom">hot</p>
					</c:if>
					</td>
					<td style="width: 150px">${l.qa_date}</td>
					<td style="width: 150px">${l.qa_name}</td>
					<td style="width: 100px">${l.qa_read}</td>
					<% No = No - 1; %>
				</tr>
			</c:forEach>
			</table>
			<br>
			<div align="right">
				<c:if test="${id != null}"> <!-- id가 없으면 write 안뜸 --> 
					<p style="padding: 2px 25px 3px 25px; border: 1px solid lightgrey; display: inline;">
						<a href="qaboard/writeQnA.jsp">WRITE</a>
					</p>
			</c:if>
			</div>
		
			<% if(content == null) {%>
			<div>
				<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item">
					<a class="page-link" href="/board2/qaList.khy?pageNum=<%=pre%>">Previous</a></li>
					<%for (int i = 1; i<=pageCount;i++){ %>
					<li class="page-item">
					<a class="page-link" href="/board2/qaList.khy?pageNum=<%=i%>">&nbsp;<%= i %>&nbsp;</a></li>
					<% } %>
					<li class="page-item">
					<a class="page-link" href="/board2/qaList.khy?pageNum=<%=next%>">Next</a></li>
				</ul>
				</nav>
			</div>
			<% }else {%>
			<div>
				<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item">
					<a class="page-link" href="/board2/searchQnA.khy?pageNum=<%=pre%>&search=<%=content%>">Previous</a></li>
					<%for (int i = 1; i<=pageCount;i++){ %>
					<li class="page-item">
					<a class="page-link" href="/board2/searchQnA.khy?pageNum=<%=i%>&search=<%=content%>">&nbsp;<%= i %>&nbsp;</a></li>
					<% } %>
					<li class="page-item">
					<a class="page-link" href="/board2/searchQnA.khy?pageNum=<%=next%>&search=<%=content%>">Next</a></li>
				</ul>
				</nav>
			</div>
			<% } %>
			<DIV class='aside_menu'>
			<FORM name='frm' method='post' action="/board2/searchQnA.khy">
				<ASIDE style='float: right;'> <SELECT name='col'>
					<!-- 검색 컬럼 -->
					<OPTION value='none'>전체 목록</OPTION>
					<OPTION value='content'>제목</OPTION>
				</SELECT> <input type='text' name="search" size='20'>
				<input type="hidden" name="pageNum" value="<%=pageNo%>">
				<button type='submit'>검색</button>
				</ASIDE>
			</FORM>
			<DIV class='menu_line' style='clear: both;'></DIV>
		</DIV>
		
</div>	
		<p style="height: 500px">
			<br />
			
		</p>
<jsp:include page="../footer.html"/>
</body>
</html>