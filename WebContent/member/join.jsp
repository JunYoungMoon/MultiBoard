<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page language="java" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Gaegu|Rancho"
	rel="stylesheet">
<title>회원가입</title>
<script language="JavaScript" src="members.js" ></script>
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
	<br>
	<br>
	<div class="centered">
		<form action="joinOk.jsp" method="post" name="reg_frm" onSubmit="return infoConfirm();">		
			<div class="shadow p-3 mb-5 bg-white rounded">
				<p class="text-right font2 intitle">회원가입</p>
				<br>
				<div class="form-group">
					<div class="row">
						<div class="col">
							<label for="exampleInputPassword1">NAME</label> 
							<input type="name" class="form-control" name="name" placeholder="name">
						</div>
						<div class="col"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col">

							<label for="exampleInputPassword1">ID</label> <input type="text"
								class="form-control" name="id" placeholder="id">
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
								aria-describedby="emailHelp" placeholder="Enter email">
						</div>
						<div class="col-4"></div>
					</div>


					<small id="emailHelp" class="form-text text-muted">We'll
						never share your email with anyone else.</small>
				</div>
				
<!-- 				<div class="div-addr">
						<input type="text" id="addrnum" name="addrnum">&nbsp;&nbsp;<input
							type="button" onclick="SearchPostcode()" value="우편번호검색"
							class=" btn btn-primary  btn-xs btn1"><br>
						<div id="layer"
							style="display: none; position: fixed; overflow: hidden; z-index: 1; -webkit-overflow-scrolling: touch;">
							<img
								src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png"
								id="btnCloseLayer"
								style="cursor: pointer; position: absolute; right: -3px; top: -3px; z-index: 1"
								onclick="closeDaumPostcode()" alt="닫기 버튼">
						</div>
						<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
						<script src="SearchAddr.js"></script>
					</div>
					<div class="div-addr">
						<input type="text" id="addr" name="addr" class="addr">
					</div>
					<div class="div-addr">
						<input type="text" name="addrdetail" class="addrdetail">
					</div> -->

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
							<input type="tel" class="form-control " name="phone2"
								placeholder="앞번호" size="15">
						</div>
						-
						<div class="col-sm">
							<input type="tel" class="form-control" name="phone3"
								placeholder="뒷번호">
						</div>
						<div class="col-sm"></div>
					</div>
				</div>
				<br> <br>
				<div align="right">
					<input type="submit" class="btn btn-outline-primary" value="회원가입">
					<a href="login.jsp">
						<button type="button" class="btn btn-outline-secondary">취소</button>
						</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>