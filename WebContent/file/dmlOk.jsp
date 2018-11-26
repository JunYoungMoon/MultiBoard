<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
			<%System.out.println(request.getParameter("key"));%>
			<script language="javascript">
			alert("정상적으로 등록되었습니다.");
			document.location.href="/board2/view.fmjy?key=<%=request.getParameter("key")%>"; 
			</script>
</body>
</html>