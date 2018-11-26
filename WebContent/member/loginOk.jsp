<%@page import="board.member.MemberDTO"%>
<%@page import="board.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String ip = request.getRemoteAddr();	
	
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = new MemberDTO();
	
	dto = dao.getMember(id);
	
	int checkNum = dao.userCheck(dto,pwd,ip); 
	
	if(dto == null) {
		%>
				<script language="javascript">
					alert("아이디가 존재하지 않습니다.");
					history.go(-1);
				</script>
		<%
	}else{
		 if(checkNum == 0) {
			%>
					<script language="javascript">
						alert("비밀번호가 틀렸습니다.");
						history.go(-1);
					</script>
			<%
		} else if(checkNum == 1) {					

						String name = dto.getName();
						session.setAttribute("id", id);
						session.setAttribute("name", name);
						response.sendRedirect("../ntiList.mjy");
				}	
			%>
<%
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>