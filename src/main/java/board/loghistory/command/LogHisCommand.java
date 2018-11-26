package board.loghistory.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogHisCommand {
	void execute(HttpServletRequest request, HttpServletResponse response);

}
