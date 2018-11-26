<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">


<title>Blog Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900"
	rel="stylesheet">
<link href="blog.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>

<body>
	<div class="container">
		<header class="blog-header py-3">
		<div
			class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-4 pt-1">
				<a class="text-muted" href="#"></a>
			</div>
			<div class="col-4 text-center">
				<a class="blog-header-logo text-dark"
					href="<%=request.getContextPath()%>/ntiList.mjy"> <img
					src="<%=request.getContextPath()%>/imgButton/yedam.png" width="125"
					height="45" border="0">
			</div>

			<div class="col-4 d-flex justify-content-end align-items-center">
				<%if(session.getAttribute("id") == null) {%>
				<a class="btn btn-sm btn-outline-secondary"
					href="<%=request.getContextPath()%>/member/login.jsp">로그인</a>
				<%} else if (("admin").equals(session.getAttribute("id"))) 				
				{ out.print(session.getAttribute("name")+"님 안녕하세요"); %>&nbsp; <a
					class="btn btn-sm btn-outline-secondary"
					href="<%=request.getContextPath()%>/loghistory.do">LogHistory</a>&nbsp;
				<a class="btn btn-sm btn-outline-secondary"
					href="<%=request.getContextPath()%>/member/logout.jsp">로그아웃</a>
				<%} else { %>
				<%out.print(session.getAttribute("name")+"님 안녕하세요"); %>&nbsp; <a
					class="text-muted" href="#"> </a> <a
					class="btn btn-sm btn-outline-secondary"
					href="<%=request.getContextPath()%>/member/updateMember.jsp">회원정보수정</a>&nbsp;
				<a class="btn btn-sm btn-outline-secondary"
					href="<%=request.getContextPath()%>/member/logout.jsp">로그아웃</a>
				<%}%>
			</div>
			<hr>
		</header>
	</div>
	<hr>
	<div class="container">
		<nav class="nav d-flex justify-content-between"> <a
			class="p-2 text-muted"
			href="<%=request.getContextPath()%>/ntiList.mjy">Notice</a> <a
			class="p-2 text-muted"
			href="<%=request.getContextPath()%>/qaList.khy">Q & A</a> <a
			class="p-2 text-muted"
			href="<%=request.getContextPath()%>/image.fmjy">Image</a> </nav>
	</div>
	<hr>
	
	

</body>
</html>