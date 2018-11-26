package board.qna.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qna.ex.qaDao;
import board.qna.ex.qaDto;

public class qaListSearchCommand implements qaCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		qaDao dao = new qaDao();
		List<qaDto> list = new ArrayList<qaDto>();
		String pageNum =(String)request.getParameter("pageNum");
		String content =(String)request.getParameter("search");
		
		list = dao.searchQ(pageNum,content);
		
		String totalCount = dao.searchCountList(content);
		
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		
	}

}
