package board.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.ex.ntiDAO;
import board.notice.ex.ntiDTO;

public class ntiViewCommand implements ntiCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.getParameter("");
		
		int no = Integer.parseInt(request.getParameter("key"));

		ntiDAO dao = new ntiDAO();
		ntiDTO dto = new ntiDTO();
		
		dto = dao.contentView(no);			
		
		request.setAttribute("dto",dto);
	}
}
