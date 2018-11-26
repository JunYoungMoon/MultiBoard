
package board.qna.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface qaCommand {
	
	void execute(HttpServletRequest request, HttpServletResponse response);

}

