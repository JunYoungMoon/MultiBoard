package board.qna.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qna.ex.qaDao;
import board.qna.ex.qaDto;
import board.qna.ex.rDao;
import board.qna.ex.rDto;

public class qaConCommand implements qaCommand{
	
	//QnA 내용 보기 , 해당 내용의 Reply 보기 command  
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int no = Integer.parseInt(request.getParameter("qa_id"));
		String check = (String) request.getParameter("check");
		
		
		
		System.out.println(no);
		
		qaDao dao = new qaDao();
		qaDto dto = dao.contentQ(no,check);
		
		request.setAttribute("dto",dto);
		
		rDao rdao = new rDao();
		ArrayList<rDto> list = new ArrayList<rDto>();
		
		list = rdao.listR(no);		
		
		request.setAttribute("rList", list);
	};
}

