<%@page import="board.file.FileDAO"%>
<%@page import="board.file.FileDTO"%>
<%@page import="board.file.ReplyDAO"%>
<%@page import="board.file.ReplyDTO"%>
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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Gaegu|Rancho"
	rel="stylesheet">
<title>Image View</title>
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumpenscript.css);
.font2 {
	font-family: 'Gaegu', cursive;
	font-size: 18px;
}
.font {
	font-family: 'Nanum Pen Script', cursive;
	font-size: 20px;
}
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
a.jcm-top {
	position: fixed;
	right: 15px;
	bottom: 15px;
	border-radius: 5px;
	color: #ffffff;
	text-align: center;
	width: 45px;
	height: 45px;
	font-size: 40px;
	background-color: #323232;
	opacity: 0.5;
	filter: alpha(opacity = 50);
	z-index: 999;
	display: none;
}
a.jcm-top:hover {
	background-color: #000
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<br>
	<br>

	<%
		String key = request.getParameter("key");
		FileDAO dao = new FileDAO();
		System.out.println("view count: " + key);
		FileDTO dto = dao.contentView(key);		
	%>
	<div class="centered">
		<table
			style="width: 1000px; text-align: center; border: 1px solid lightgrey;">
			<tr>
				<td><br> <img
					src="<%=request.getContextPath()%>/image/<%=dto.getFileName()%>"
					class="rounded"></td>
				<td class="font"><%=dto.getTitle()%><br> <br class="font2">
					<p class="text-danger"><%=dto.getContent()%></p> <br>
					<div class="de_popup_wrap">
						<div class="d-flex justify-content-center">
							<div class="p-1 mr-3" style="margin-left: 45px;">부가정보</div>
							<div class="p-1">
								<button type="button" class="btn btn-outline-secondary">상품교환&amp;반품안내
									보기</button>
							</div>
							<div class="p-1">
								<button type="button" class="btn btn-outline-secondary">소재별
									세탁방법 보기</button>
							</div>
							<div class="p-1"></div>
						</div>
					</div> <br>
					<div class="input-group mb-3 container">
						<div class="input-group-prepend">
							<label class="input-group-text" for="inputGroupSelect01"
								style="margin-left: 60px;">사이즈</label>
						</div>
						<select class="custom-select col-8" id="inputGroupSelect01">
							<option selected>Choose...</option>
							<option value="1">사이즈1</option>
							<option value="2">사이즈2</option>
						</select>
					</div> <br>
					<div>
						<img src="<%=request.getContextPath()%>/imgButton/buy.gif"
							class="img-fluid"> <img
							src="<%=request.getContextPath()%>/imgButton/cart.gif"
							class="img-fluid">
						<%--  
						<img src="<%=request.getContextPath()%>/imgButton/wish.gif"
							class="img-fluid"> --%>
					</div></td>
			</tr>
			<tr>
				<td colspan="2"><img
					src="<%=request.getContextPath()%>/image/<%=dto.getFileName2()%>"
					class="img-fluid"></td>
			</tr>
		</table>

		<div align="right">
			<p
				style="padding: 2px 25px 3px 25px; border: 1px solid lightgrey; display: inline;">
				<a href="<%=request.getContextPath()%>/file/list.jsp">List</a>
			</p>

			<%
				if (("admin").equals(session.getAttribute("id"))) {
			%>
			<p
				style="padding: 2px 25px 3px 25px; border: 1px solid lightgrey; display: inline;">
				<a href="<%=request.getContextPath()%>/file/update.jsp?key=<%=key%>">수정</a>
			</p>

			<p
				style="padding: 2px 25px 3px 25px; border: 1px solid lightgrey; display: inline;">
				<a href="<%=request.getContextPath()%>/file/delset.jsp?key=<%=key%>">삭제</a>
			</p>
			<%
				}
			%>
		</div>
		<br> <br> <br>


		<%
			ReplyDAO daoReply = new ReplyDAO();
			List<ReplyDTO> list = daoReply.listReply(key);
			for (ReplyDTO dto2 : list) {
		%>
		<table style="width: 1000px; border: 1px solid lightgrey;">
			<tr style="height: 35px; text-align: left; padding-right: 0px">
				<td style="width: 150px;">&nbsp;&nbsp; Name &nbsp;&nbsp; <%=dto2.getRname()%></td>
				<td>Date &nbsp;&nbsp; <%=dto2.getD_date()%></td>
				<td style="text-align: right">
					<%
							if (("admin").equals(session.getAttribute("id"))) {
						%>
					<p class="a" style="padding: 3px 25px 5px 25px;">
						<a href="" style="color: grey">DELETE</a>
					</p><%
							}
						%>
				</td>
			</tr>
			<tr style="height: 100px;">
				<td colspan="3"><%=dto2.getRcontent()%></td>
			</tr>
		</table>
		<br/>
		<%}%>


		<form action="<%=request.getContextPath()%>/rinput.fmjy">
			<input type="hidden" name="key" value="<%=dto.getCount()%>">
			<input type="hidden" name="name"
				value="<%=session.getAttribute("name")%>">
			<fieldset style="border: 1px solid lightgrey">
				<p style="padding: 10px 0px 0px 5px;">
					<strong> COMMENT</strong>
				</p>
				<textarea name="comment"
					style="width: 900px; height: 100px; margin-left: 45px; resize: none"></textarea>
				<p style="text-align: right; padding: 20px 30px 5px 5px;">
					<input type="submit" value="WRITE"
						style="padding: 5px 25px 5px 25px; border: 1px solid lightgrey; background-color: white; color: grey">
				</p>
			</fieldset>
		</form>
	</div>

	<!--  	<div id="SP_goTopEnd" style="opacity: 1; display: block;">
		<a class="goKakaoTalk" onclick="window.open('https://lc1.lunasoft.co.kr/lunachat/api-connect/@secretlabel/main');" href="#">
		<img src="http://cdn1-aka.makeshop.co.kr/design/label55/smartpc/custom_img/top_down_bt1.png"></a>
		<a class="goTop" href="#header"><img src="http://cdn1-aka.makeshop.co.kr/design/label55/smartpc/custom_img/top_down_bt2.png"></a>
		<a class="goEnd" href="#footer"><img src="http://cdn1-aka.makeshop.co.kr/design/label55/smartpc/custom_img/top_down_bt3.png"></a>
	</div>  -->
	<!-- 맨위로 가기 이미지 버튼 시작 -->

	<a href="#" class="jcm-top hidden-xs hidden-sm"><i
		class="icon icon-angle-up"></i></a>

	<script>
		$(document).ready(function() {
			$(window).scroll(function() {
				if ($(this).scrollTop() > 200) {
					$('.jcm-top').fadeIn();
				} else {
					$('.jcm-top').fadeOut();
				}
			});
			$('.jcm-top').click(function() {
				$('html, body').animate({
					scrollTop : 0
					}, 400);
				return false;
			});
		});
	</script>

</body>
</html>