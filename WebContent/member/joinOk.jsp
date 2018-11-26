<%@page import="java.sql.Timestamp"%>
<%@page import="board.member.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@page import="board.member.MemberDAO"%>
<jsp:useBean id="dto" class="board.member.MemberDTO"/>
<jsp:setProperty name="dto" property="*" />

<%
		MemberDAO dao = MemberDAO.getInstance();
		if(dao.confirmId(dto.getId()) == 1) {
%>
		<script language="javascript">
			alert("이미 존재하는 아이디입니다.");
			history.go(-1);
		</script>
<%
		} else {
			int ri = dao.insertMember(dto);
			if(ri == 1) {
				session.setAttribute("id", dto.getId());
%>
			<script language="javascript">
				alert("회원가입을 축하 합니다.");
				document.location.href="login.jsp";
			</script>
<%
			} else {
%>
			<script language="javascript">
				alert("회원가입에 실패했습니다.");
				document.location.href="login.jsp";
			</script>
<%
			}
		}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>	
</body>
</html>