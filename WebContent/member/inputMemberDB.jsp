<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@page import="board.member.MemberDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:useBean id="member" class="board.member.MemberDTO"/>
<%-- <jsp:setProperty name="member" property="*" --%>
</head>
<script type="text/javascript">
</script>
<body>
<%
	try {
		member.setId(request.getParameter("id"));
		member.setPwd(request.getParameter("pwd"));
		member.setName(request.getParameter("name"));
		member.setEmail(request.getParameter("phone1"));
		member.setEmail(request.getParameter("phone2"));
		member.setEmail(request.getParameter("phone3"));
		
		MemberDAO dao = new MemberDAO();
		dao.insertMember(member);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
</body>
</html>