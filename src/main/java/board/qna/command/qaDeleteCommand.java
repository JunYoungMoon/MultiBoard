package board.qna.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qna.ex.qaDao;
import board.qna.ex.rDao;



public class qaDeleteCommand implements qaCommand{

	//QnA 삭제 command
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int qa_id = Integer.parseInt(request.getParameter("qa_id"));
		
		qaDao dao = new qaDao();
		dao.deleteQ(qa_id);
		
		//dmlOk.jsp 에서 구분 
		request.setAttribute("test", "qd");
		
	}
	
	

}
