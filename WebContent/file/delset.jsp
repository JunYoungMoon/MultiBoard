<%@page import="board.file.FileDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE>
<meta http-equiv="refresh" content="1;url=http://exampleURL.com">
<jsp:useBean id="b" class="board.file.FileDTO" />
<jsp:setProperty name="b" property="*" />
<html>
<head>
<script type="text/javascript">
	//window.location.replace('http://localhost/board2/file/list.jsp') //입력후 다시 돌아감.
	document.location.href="<%=request.getContextPath()%>/image.fmjy";
</script>
</head>
<body>
	<%
		String count = request.getParameter("key");
		b.setCount(Integer.parseInt(count));

		board.file.FileDAO dao = new board.file.FileDAO();

		dao.fileDelete(Integer.parseInt(count));
	%>
</body>
</html>