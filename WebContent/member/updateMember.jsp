<%@page import="board.member.MemberDTO"%>
<%@page import="board.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	String id = (String)session.getAttribute("id");
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = dao.getMember(id);
%>    
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
<title>회원정보 수정</title>
<script language="JavaScript" src="members.js"></script>
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

input[type="date"]::-webkit-calendar-picker-indicator {
	color: rgba(0, 0, 0, 0); //
	숨긴다 opacity: 1;
	display: block;
	background:
		url(https://mywildalberta.ca/images/GFX-MWA-Parks-Reservations.png)
		no-repeat; // 대체할 아이콘 width : 20px;
	height: 20px;
	border-width: thin;
}

.centered {
	display: table;
	margin-left: auto;
	margin-right: auto;
}

.file_input label {
	position: relative;
	cursor: pointer;
	display: inline-block;
	vertical-align: middle;
	overflow: hidden;
	width: 100px;
	height: 30px;
	background: #777;
	color: #fff;
	text-align: center;
	line-height: 30px;
	font-family: 'Rancho', cursive;
}

.file_input label input {
	position: absolute;
	width: 0;
	height: 0;
	overflow: hidden;
}

.file_input input[type=text] {
	vertical-align: middle;
	display: inline-block;
	width: 400px;
	height: 28px;
	line-height: 28px;
	font-size: 15px;
	padding: 0;
	border: 0;
	border: 1px solid #777;
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
<br>
	<br>
	<div class="centered">
		<form action="updateMemberOk.jsp" method="post">

			<div class="shadow p-3 mb-5 bg-white rounded">
				<p class="text-right font2 intitle">회원정보 수정</p>
				<br>
				<div class="form-group">
					<div class="row">
						<div class="col">

							<label for="exampleInputPassword1">NAME</label>
							<%= dto.getName() %>
						</div>
						<div class="col"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col">

							<label for="exampleInputPassword1">ID</label>
							<%= dto.getId() %>
						</div>
						<div class="col"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col">
							<label for="exampleInputPassword1">Password</label> <input
								type="password" class="form-control" name="pwd"
								placeholder="Password">
						</div>
						<div class="col"></div>
					</div>
				</div>

				<div class="form-group">

					<label for="exampleInputEmail1">Email address</label>
					<div class="row">
						<div class="col-8">
							<input type="email" class="form-control" name="email"
								value="<%= dto.getEmail()%>">
						</div>
						<div class="col-4"></div>
					</div>


					<small id="emailHelp" class="form-text text-muted">We'll
						never share your email with anyone else.</small>
				</div>

				<div class="form-group">
					<label for="exampleFormControlSelect1">핸드폰 번호</label>

					<div class="row">
						<div class="col-sm">
							<select class="form-control" name="phone1">
								<option>010</option>
								<option>011</option>
								<option>016</option>
								<option>017</option>
							</select>
						</div>
						-
						<div class="col-sm">
							<input type="text" class="form-control" name="phone2"
							 value="<%= dto.getPhone2()%>">
						</div>
						-
						<div class="col-sm">
							<input type=text" class="form-control" name="phone3"
							 value="<%= dto.getPhone3()%>">
						</div>
						<div class="col-sm"></div>
					</div>
				</div>
				<br> <br>
				<div align="right">
					<button type="submit" onclick="infoConfirm()" class="btn btn-outline-primary">회원정보 수정</button>
					<a href="<%=request.getContextPath()%>/ntiList.mjy">
						<button type="button" class="btn btn-outline-secondary">취소</button>
					</a>
				</div>
				<div align="left">
				<a href="deleteMember.jsp">회원탈퇴</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>