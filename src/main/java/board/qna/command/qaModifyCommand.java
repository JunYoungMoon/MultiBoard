package board.qna.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qna.ex.qaDao;

public class qaModifyCommand implements qaCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			int id = 0;
		//QnA 내용 수정 : 제목, 내용만 수정가능 
		try {
			
			request.setCharacterEncoding("UTF-8");
			String testId = request.getParameter("qa_id");
			if(testId == "" || testId == null) {
				System.out.println("null값입니다");
			}
			else {
			 id = Integer.parseInt(testId);}
			
			
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			qaDao dao = new qaDao();
			
			dao.modifyQ(id, subject, content);
			
			//dmlOk.jsp 에서 구분 
			request.setAttribute("test", "qm");
			
		} catch (UnsupportedEncodingException e) {
		
			e.printStackTrace();
		}
		
		
		
		
	}
	
	

}
