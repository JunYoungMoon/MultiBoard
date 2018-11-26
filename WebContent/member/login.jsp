<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 	if (session.getAttribute("ValidMem") != null) {
%>

<% 	} %>
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
<title>login</title>
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumpenscript.css);

.font2 {
	font-family: 'Gaegu', cursive;
	font-size: 20px;
}

.font {
	font-family: 'Nanum Pen Script', cursive;
	font-size: 20px;
}

label, select {
	font-family: 'Gaegu', cursive;
	font-size: 20px;
}

.td_color {
	background-color: #EAEAEA;
	font-size: 13px;
}

td, th {
	border: 0.2px solid gray;
	margin: 10px;
	padding: 10px;
}

.centered {
	display: table;
	margin-left: auto;
	margin-right: auto;
}


input::placeholder {
	font-family: 'Nanum Pen Script', cursive;
	font-size: 18px;
}

.intitle {
	font-size: 30px;
	padding: 0.5em;
}
</style>
</head>
<body>
	<form action="loginOk.jsp" method="post">
		<br> <br>
		<div class="centered">
			<div class="shadow p-3 mb-5 bg-white rounded">
				<p class="text-center font2 intitle">로그인</p>
				<br>
				<div class="form-group">
					<div class="row">
						<div class="col"></div>
						<div class="col-6">
							<label for="exampleInputPassword1">ID</label> <input type="id"
								class="form-control" name="id" placeholder="id" size="50">
						</div>
						<div class="col"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col"></div>
						<div class="col-6">
							<label for="exampleInputPassword1">Password</label> <input
								type="password" class="form-control" name="pwd"
								placeholder="Password">
						</div>
						<div class="col"></div>
					</div>
				</div>

				<div align="center">
					<button type="submit" class="btn btn-outline-secondary">로그인</button>
				</div>
				<br>

				<div align="left">
					<a href="join.jsp" class="font2">회원가입
						</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>