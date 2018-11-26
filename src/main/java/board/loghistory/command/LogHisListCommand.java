package board.loghistory.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.loghistory.ex.LogHistoryDAO;
import board.loghistory.ex.LogHistoryDTO;


public class LogHisListCommand implements LogHisCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		LogHistoryDAO dao = new LogHistoryDAO();
		List<LogHistoryDTO> list = new ArrayList<LogHistoryDTO>();

		String count = request.getParameter("pageNum");

		list = dao.LogHislist(count);	
		String totalCount = dao.CountList();
		
		request.setAttribute("list", list);		
		request.setAttribute("totalCount", totalCount);
	}		
}
