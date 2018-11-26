<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
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
<title>Insert title here</title>
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumpenscript.css);

a:hover {
	text-decoration: underline;
}

table.a {
	width: 1000px;
	text-align: left;
	border: 1px solid lightgrey;
}

tr {
	border-bottom: 1px solid lightgrey;
}

p.a {
	padding: 5px 25px 5px 25px;
	border: 1px solid lightgrey;
	display: inline;
}

.centered {
	display: table;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body style="text-align: center">
	<jsp:include page="../header.jsp"></jsp:include>
	<%
		String count = request.getParameter("key");
		int cast = Integer.parseInt(count);
		
		 %>
	<h3 style="text-decoration: underline">Notice</h3>
	<div class="centered">
		<table class="a">
			<tr style="border-top: 1px solid lightgrey; height: 50px">
				<td colspan="2">&nbsp;&nbsp; Subject ${dto.title}</td>
			</tr>
			<tr style="height: 50px;">
				<td style="width: 150px;">&nbsp;&nbsp; Name ${dto.name}</td>
				<td>Date ${dto.date}</td>
			</tr>
			<tr style="height: 300px; text-align: center">
				<td colspan="2"><p>${dto.content}</p></td>
			</tr>

			<tr style="height: 50px;">
				<td style="width: 150px">&nbsp;&nbsp; file</td>
				<td><a href="notice/filedown.jsp?key=<%=cast%>">${dto.fileName}</a></td>
			</tr>

		</table>
		<table style="width: 1000px; text-align: right;">
				<tr style="border: 0px">
					<td><br />
						<p class="a">
							<a href="ntiList.mjy" style="color: grey">LIST</a>
						</p>&nbsp;

					<%
					 if(("admin").equals(session.getAttribute("id"))) {%>
						<p class="a">
							<a href="notice/update.jsp?key=<%=cast%>">
							EDIT</a>
						</p>&nbsp;
						<p class="a">
							<a href="notice/delset.jsp?key=<%=cast%>">DELETE
						</p></td>
					<%}%>
				</tr>
			</table>
	</div>
	<jsp:include page="../footer.html"></jsp:include>
</body>
</html>