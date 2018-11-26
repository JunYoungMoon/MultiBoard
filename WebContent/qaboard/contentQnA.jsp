<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="board.qna.ex.rDto" %>
<%@ page import="board.qna.ex.rDao" %>

<%@ page language="java" import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<style>
<!--스타일 시트 -->
input:hover[type="submit"] {
	text-decoration: underline;
}

a:hover {
	text-decoration: underline;
}

/* div {
	position: absolute;
	width: 1000px;
	height: 500px;
	left: 50%;
	top: 50%;
	margin: -250px 0 0 -500px;
} */

table.a {
	width: 1000px;
	text-align: left;
	border: 1px solid lightgrey;
	margin:5px 0px 5px 0px;
}
table.b {
	width: 950px; 
	border: 1px solid lightgrey; 
	margin:5px 0px 5px 0px;
}

tr {
	border-bottom: 1px solid lightgrey;
}

p.a {
	padding: 5px 25px 5px 25px;
	border: 1px solid lightgrey;
	display:inline;
	
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
	String re_id = (String)request.getParameter("re_id");
	
	
%>
	<br />
	<h3 style="text-decoration: underline">Q & A</h3>
	<div class="centered">
		<!--  QnA 내용보기  -->
		<form action="qaboard/pwdOk.jsp?check=del&qa_id=${dto.qa_id}&write_id=${dto.write_id}" method="post">
			<table class="a">
				<tr style="border-top: 1px solid lightgrey; height: 50px">
					<td colspan="2">&nbsp;&nbsp; Subject ${dto.qa_sub} </td>
				</tr>
				<tr style="height: 50px;">
					<td style="width: 150px;">&nbsp;&nbsp; Name ${dto.qa_name} </td>
					<td>Date ${dto.qa_date}</td>
				</tr>
				<tr style="height: 300px; text-align:center">
					<td colspan="2"><p>${dto.qa_con}</p></td>
				</tr>
				<tr style="height: 50px;">
					<td style="width: 150px">&nbsp;&nbsp; Password</td>
					<td><input type="password" name="delPwd">&nbsp;&nbsp;
						삭제하려면 비밀번호를 입력하세요. </td>
				</tr>
				<tr style="height: 50px;" class="b">
					<td style="width: 150px">&nbsp&nbsp File</td>
					<td><a href="">${dto.fileName}</a>
					</td>
				</tr>
			</table>
			<!-- 버튼 , delete 누르면  form태그로 전송 비밀번호 체크  -->
			<table style="width: 1000px; text-align: right;">
				<tr style="border: 0px">
					<td><br />
						<p class="a">
							<a href="/board2/qaList.khy" style="color: grey">LIST</a>
						</p>&nbsp;
						<p class="a">
							<a href="qaboard/pwdOk.jsp?check=mod&qa_id=${dto.qa_id}
									&qa_con=${dto.qa_con}&write_id=${dto.write_id}" style="color: grey">
							EDIT</a>
						</p>&nbsp;
						<p class="a">
							<input type="submit" value="DELETE" name="submit"
								style="background-color: white; border: 0px; color: grey;">
						</p></td>
				</tr>
			</table>
		</form>
		<br />
		<!-- 답글 목록 보기 ,id가 admin이면 삭제 버튼 생김  -->
		<c:if test="${rList.size()>0}">
		<div class="centered">
		<c:forEach var="i" begin="0" end="${rList.size()-1}">
		<c:if test="${rList[i].re_group==0}">
		
		
			<table style="width: 1000px; border:1px solid lightgrey;">
			<tr style="height: 35px; text-align: left;padding-right:0px">
					<c:if test="${rList[i].re_group>0}">
					<td style="color:blue;width: 150px;">
					<img src="folder/arrow.png" width="25" height="21" border="0">&nbsp;&nbsp;REPLY
					</td>
					</c:if>
					<td style="width: 150px;">&nbsp;&nbsp; Name &nbsp;&nbsp;
						${rList[i].re_name}</td>
					<td>Date &nbsp;&nbsp; ${rList[i].re_date}</td>
					<td style="text-align: right;border-right:1px solid lightgrey">
						<c:if test="${id==dto.write_id || id=='admin'}">
						<c:if test="${i==rList.size()-1}">
						<p class="a" style="padding: 3px 25px 5px 25px;">
							<a href="/board2/qaContent.khy?qa_id=${rList[i].qa_id}&check=false&reply=ok&re_id=${rList[i].re_id}" 
								style="color: grey">REPLY </a>
						</p>
						</c:if>
						</c:if>
						<c:if test="${id=='admin'}">
						<p class="a" style="padding: 3px 25px 5px 25px;">
							<a href="ReplyDelete.khy?re_id=${rList[i].re_id}&qa_id=${dto.qa_id}"
								style="color: grey">DELETE</a>
						</p> </c:if>
					</td>
				</tr>
				<tr style="height: 100px;">
				<td colspan="4">${rList[i].re_con}</td>
				</tr>
			</table>
			<br />
			</c:if>
			<c:if test="${rList[i].re_group>0}">
		<div align="right">	
			<table style="width: 950px; border:1px solid lightgrey;">
			<tr style="height: 35px; text-align: left;">
					<c:if test="${rList[i].re_group>0}">
					<td style="color:blue;width: 150px;">
					<img src="folder/arrow.png" width="25" height="21" border="0">&nbsp;&nbsp;REPLY
					</td>
					</c:if>
					<td style="width: 150px;">&nbsp;&nbsp; Name &nbsp;&nbsp;
						${rList[i].re_name}</td>
					<td>Date &nbsp;&nbsp; ${rList[i].re_date}</td>
					<td style="text-align: right; ">
						<c:if test="${id==dto.write_id || id=='admin'}">
						<c:if test="${i==rList.size()-1}">
						<p class="a" style="padding: 3px 25px 5px 25px;">
							<a href="/board2/qaContent.khy?qa_id=${rList[i].qa_id}&check=false&reply=ok&re_id=${rList[i].re_id}" 
								style="color: grey">REPLY </a>
						</p>
						</c:if>
						</c:if>
						<c:if test="${id=='admin'}">
						<p class="a" style="padding: 3px 25px 5px 25px;">
							<a href="ReplyDelete.khy?re_id=${rList[i].re_id}&qa_id=${dto.qa_id}"
								style="color: grey">DELETE</a>
						</p> </c:if>
					</td>
				</tr>
				<tr style="height: 100px;text-align:center">
				<td colspan="4">${rList[i].re_con}</td>
				</tr>
			</table>
			<br />
			</div></c:if></c:forEach></div></c:if>
			
		
		
			<div align="right">
			<%
				String reply = (String)request.getParameter("reply");
			
				if(("ok").equals(reply)){
					
			%>
				<form action="/board2/writeReply.khy" method="post" >
				<table class="b">
				
				<tr style="height: 35px; text-align: left;">
					<td style="color:blue;width: 150px;">
					<img src="folder/arrow.png" width="25" height="21" border="0">&nbsp;&nbsp;REPLY
					</td>
					<td >&nbsp;&nbsp; Name &nbsp;&nbsp;
						${name}
					<input type="hidden" name="qa_id" value="${dto.qa_id}">
					<input type="hidden" name="re_name" value="${name}">
					<input type="hidden" name="re_id" value="<%=re_id%>">
					</td>
					<td style="text-align:right">
					<input type="submit" value="WRITE"
						style="padding: 3px 25px 3px 25px; border: 1px solid lightgrey; background-color: white; color: grey">
					</td>
					
				</tr>
				<tr style="height: 100px;">
					<td colspan="3" align="center">
					<textarea name="comment"
					style="width: 850px; height: 90px; margin: -10px; resize: none"></textarea></td>
				</tr>
			</table>
			<%} %>
			</form>
			
			</div>
			
			</div>
				
				
			
			
		
		<!--  답글 작성 현재 admin만 작성 가능, 답글에 답글 가능하도록 추후 변경  -->
		<br />
		<br />
		<div class="centered">
		<form action="/board2/writeReply.khy" method="post">
		<% if(("admin").equals(session.getAttribute("id"))) {%>
		<c:if test="${dto.qa_count<=1}">
			<fieldset style="border: 1px solid lightgrey;width:1000px">
				<p style="padding: 10px 0px 0px 5px;">
					<strong> COMMENT</strong>
				</p>
				<input type="hidden" name="qa_id" value="${dto.qa_id}">
				<textarea name="comment"
					style="width: 900px; height: 100px; margin: -10px; resize: none"></textarea>
				<p style="text-align: right; padding: 20px 30px 5px 5px;">
					<input type="submit" value="WRITE"
						style="padding: 5px 25px 5px 25px; border: 1px solid lightgrey; background-color: white; color: grey">
				</p>
			</fieldset>
		
		</c:if><%}%></form></div> 
	

<jsp:include page="../footer.html"/>


</body>
</html>