<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
input:hover {
	text-decoration: underline;
}

a:hover {
	text-decoration: underline;
}

p {
	padding: 5px 25px 5px 25px;
	border: 1px solid lightgrey;
	display: inline;
}

tr.b {
	border-bottom: 1px solid lightgrey
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
	<br>
	<br />
	<h3 style="text-decoration: underline">Notice</h3>
	<div class="centered">
		<form action="inset.jsp" method="post" enctype="multipart/form-data">
			<table style="width: 1000px; text-align: left; border: 1px solid lightgrey;">

				<tr style="border-top: 1px solid lightgrey; height: 50px" class="b">
					<td colspan="2">&nbsp;&nbsp; Subject <input type="text"
						name="title" autofozus required size="80">
					</td>
				</tr>

				<tr style="height: 50px;" class="b">
					<td style="width: 250px;">&nbsp;&nbsp; Name&nbsp;&nbsp;&nbsp;${name}<input type="hidden" name="name" value="${name}"></td>
					<td>&nbsp;&nbsp; Date <input type="date" name="date" id="datepicker"></td>
				</tr>


				<tr style="height: 300px; text-align: center" class="b">
					<td colspan="2"><textarea name="content"
							style="width: 900px; height: 250px; resize: none;"
							placeholder="내용을 입력하세요." autofozus required style="resize: none"></textarea></td>
				</tr>

				<tr style="height: 50px;" class="b">
					<td align="center">&nbsp;&nbsp; File</td>
					<td><input type="file"
							onchange="javascript:document.getElementById('file_route').value=this.value"
							name="FileName">
					<input type="text" readonly="readonly" title="File Route"
						id="file_route" size="50"></td>
				</tr>

			</table>
			<table style="width: 1000px; text-align: right;">
				<tr>
					<td><br />
						<p>
							<a href="../ntiList.mjy" style="color: grey">LIST</a>
						</p>
						<p>
							<input type="submit" value="WRITE"
								style="background-color: white; border: 0px; color: grey">
						</p></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>