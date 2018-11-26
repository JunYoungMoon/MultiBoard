package board.qna.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qna.ex.qaDao;
import board.qna.ex.qaDto;

public class qaListCommand implements qaCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//QnA 목록 command
		qaDao dao = new qaDao();
		List<qaDto> list = new ArrayList<qaDto>();
		String pageNum =(String)request.getParameter("pageNum");
		
		list = dao.listQ(pageNum);
		
		String totalCount = dao.CountList();
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
	};

}