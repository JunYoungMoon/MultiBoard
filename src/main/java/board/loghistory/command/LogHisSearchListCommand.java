package board.loghistory.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.loghistory.ex.LogHistoryDAO;
import board.loghistory.ex.LogHistoryDTO;


public class LogHisSearchListCommand implements LogHisCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		LogHistoryDAO dao = new LogHistoryDAO();
		List<LogHistoryDTO> list = new ArrayList<LogHistoryDTO>();
		String pageNum =(String)request.getParameter("pageNum");
		String content =(String)request.getParameter("content");
		
		list = dao.searchLogHis(pageNum,content);
		
		String totalCount = dao.searchCountList(content);
		
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("content", content);		
	}
}
