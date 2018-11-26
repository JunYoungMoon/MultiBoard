<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<%// MS excel로 다운로드/실행, filename에 저장될 파일명을 적어준다.
response.setHeader("Content-Disposition","attachment;filename=loghistory.xls");
response.setHeader("Content-Description", "JSP Generated Data");

// ↓ 이걸 풀어주면 열기/저장 선택창이 뜨는 게 아니라 그냥 바로 저장된다.
	response.setContentType("application/vnd.ms-excel"); 
%>
	
		<table style="width: 1000px; text-align: center;">
			<tr style="color: grey; height: 50px" class="b">
				<td >순번</td>
				<td >id</td>
				<td >이름</td>
				<td >상태</td>
				<td >시간</td>
				<td >ip</td>
			</tr>
			<c:forEach var="l" items="${list}">
				<tr class="b">
					<td style="width: 100px; height: 65px">${l.log_num}</td>
					<td style="width: 100px; height: 65px">${l.log_id}</td>
					<td style="width: 100px">${l.log_name}</td>

					<c:if test="${l.record eq 'login'}">
						<td style="color: green">${l.record}</td>
					</c:if>
					<c:if test="${l.record eq 'logout'}">
						<td style="color: red">${l.record}</td>
					</c:if>

					<td style="width: 100px">${l.log_time}</td>
					<td style="width: 100px">${l.log_ip}</td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>