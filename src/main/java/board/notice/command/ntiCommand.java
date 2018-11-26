package board.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ntiCommand {
	
	void execute(HttpServletRequest request, HttpServletResponse response);

}
