<%@page import="board.notice.ex.ntiDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedOutputStream"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.BufferedInputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.net.URLEncoder"%>


<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");

	String count = request.getParameter("key");
	int cast = Integer.parseInt(count);

	System.out.print(cast);
	board.notice.ex.ntiDAO dao = new board.notice.ex.ntiDAO();

	board.notice.ex.ntiDTO dto = dao.fileDown(cast);

	System.out.print(dto.getFilePath());
	String filename = dto.getFilePath();

	File file = new File(filename);

	System.out.println(filename);
	System.out.println(file.getAbsoluteFile());
	System.out.println(file.length());

	byte b[] = new byte[(int) file.length()];
	if (file.length() > 0 && file.isFile()) {
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment;filename=" + file.getName() + ";");

		BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream());
		int read = 0;
		try {
			while ((read = input.read(b)) != -1) {
				output.write(b, 0, read);
			}
			output.close();
			input.close();
			out.clear();
			out = pageContext.pushBody();

		} catch (Exception e) {
			System.out.println("에러메세지: " + e.getMessage());
		} finally {
			if (output != null) {
				output.close();
			}
			if (input != null) {
				input.close();
			}
		}
	} else {
%>
<script>
	alert("파일이 존재 하지 않습니다.");
	window.location.replace('http://localhost/board2/ntiList.mjy')
	self.close();
	
</script>
<%
	}
%>
<script type="text/javascript">
	window.location.replace('http://localhost/board2/notice/view.jsp') //입력후 다시 돌아감.
</script>
