<%@page import="board.file.FileDAO"%>
<%@page import="board.file.FileDTO"%>
<%@page import="board.file.FileNextListDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<style>
.centered {
	display: table;
	margin-left: auto;
	margin-right: auto;
}
</style>
<body style="text-align: center">
	<jsp:include page="../header.jsp"></jsp:include>
	<br />
	<h3>Image</h3>
	<br>
	<%
		String word = request.getParameter("word");
		String col = request.getParameter("col");

		session.getAttribute("id");
		session.getAttribute("name");

		String pageNum = request.getParameter("key");

		System.out.println("현재 페이지 번호 : " + pageNum);

		int pageNo;

		if (pageNum == null) {
			pageNo = 1;
		} else {
			pageNo = Integer.parseInt(pageNum);
		}

		//"검색 한 내용"이 없을때 그냥 리스트만 뿌려줌
		if (col == null && word == null) {
	%>
	<div class="centered">
		<div class="row">
			<%
				FileDAO daoImage = new FileDAO();
				List<FileDTO> list = daoImage.fileList(pageNum);

				for (FileDTO dto : list) { //현재 페이지(1~8)개의 내용을 뿌려줌
			%>
			<div class="col-3">
				<a href="<%=request.getContextPath()%>/file/View.jsp?key=<%=dto.getCount()%>">
					<img class="img-thumbnail" src="<%=request.getContextPath()%>/image/<%=dto.getFileName()%>">
				</a>
			</div>
			<%}%>
		</div>

		<div>
			<br>
			<%
				if (("admin").equals(session.getAttribute("id"))) {	//관리자 id 일때 이미지 리스트의 글쓰기 버튼을 활성화
			%>
			<div align="right">
				<p style="padding: 2px 25px 3px 25px; border: 1px solid lightgrey; display: inline;">
					<a href="<%=request.getContextPath()%>/file/input.jsp">WRITE</a>
				</p>
			</div>
			<%}%>
			
			<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item">
					<%
						//페이징 버튼
						FileDAO daoPaging = new FileDAO();
						FileNextListDTO fnldto = daoPaging.fileNextList(pageNum);

							int page1 = fnldto.getPage1();
							int startPage = fnldto.getStartPage();
							int endPage = fnldto.getEndPage();
							int totalPage = fnldto.getTotalPage();

							int No = totalPage - (pageNo - 1) * 10;

							int pageCount = totalPage / 10 + 1;

							int pre;
							int next;

							if (pageNo > 1)
								pre = pageNo - 1;
							else
								pre = pageNo;

							if ((pageNo + 1) <= pageCount)
								next = pageNo + 1;
							else
								next = pageCount;
					%> <a class="page-link" href="image.fmjy?key=<%=pre%>">Previous</a>
				</li>
				<%
					for (int iCount = startPage; iCount <= endPage; iCount++) { //1~10 11~20 .... 이부분에 html 와야함
				%>
				<li class="page-item"><a class="page-link"
					href="image.fmjy?key=<%=iCount%>"><%=iCount%></a></li>
				<%
					}
				%>

				<li class="page-item"><a class="page-link"
					href="image.fmjy?key=<%=next%>">Next</a></li>

			</ul>
			</nav>
		</div>
		<%
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			} else {
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		%>

		<div class="row">

			<%
				board.file.FileDAO dao = new board.file.FileDAO();
					String count = request.getParameter("key");
					List<FileDTO> list = dao.fileSearchList(count, word);

					for (board.file.FileDTO dto : list) {
			%>
			<div class="col-3">
				<a
					href="<%=request.getContextPath()%>/file/View.jsp?key=<%=dto.getCount()%>">
					<img class="img-thumbnail"
					src="<%=request.getContextPath()%>/image/<%=dto.getFileName()%>"
					alt="Card image cap">
				</a>
			</div>
			<%
				}
			%>
		</div>

		<div>
			<br>
			<%
				if (("admin").equals(session.getAttribute("id"))) {
			%>
			<div align="right">
				<p
					style="padding: 2px 25px 3px 25px; border: 1px solid lightgrey; display: inline;">
					<a href="<%=request.getContextPath()%>/file/input.jsp">WRITE</a>
				</p>
			</div>
			<%
				}
			%>

			<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item">
					<%
						board.file.FileDAO dao2 = new board.file.FileDAO();
							board.file.FileNextListDTO fnldto = dao2.fileNextListTwo(count, list.size());
							int page1 = fnldto.getPage1();
							int startPage = fnldto.getStartPage();
							int endPage = fnldto.getEndPage();
							int totalPage = fnldto.getTotalPage();

							int No = totalPage - (pageNo - 1) * 10;

							int pageCount = totalPage / 10 + 1;

							int pre;
							int next;

							if (pageNo > 1)
								pre = pageNo - 1;
							else
								pre = pageNo;

							if ((pageNo + 1) <= pageCount)
								next = pageNo + 1;
							else
								next = pageCount;
					%> <a class="page-link" href="image.fmjy?key=<%=pre%>">Previous</a>
				</li>


				<%
					for (int iCount = startPage; iCount <= endPage; iCount++) { //1~10 11~20 .... 이부분에 html 와야함
				%>
				<li class="page-item"><a class="page-link"
					href="image.fmjy?key=<%=iCount%>"><%=iCount%></a></li>
				<%
					}
				%>

				<li class="page-item"><a class="page-link"
					href="image.fmjy?key=<%=next%>">Next</a></li>

			</ul>
			</nav>
		</div>
		<%
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		%>


		<DIV class="aside_menu">
			<FORM name="frm" method="GET" action="../board2/image.fmjy?">
				<ASIDE style="float: right;"> <SELECT name="col">
					<!-- 검색 컬럼 -->
					<OPTION value="none">전체 목록</OPTION>
					<OPTION value="content">내용</OPTION>
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