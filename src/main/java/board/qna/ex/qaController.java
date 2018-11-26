package board.qna.ex;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qna.command.ReplyDeleteCommand;
import board.qna.command.ReplyWriteCommand;
import board.qna.command.qaCommand;
import board.qna.command.qaConCommand;
import board.qna.command.qaDeleteCommand;
import board.qna.command.qaListCommand;
import board.qna.command.qaListSearchCommand;
import board.qna.command.qaModifyCommand;
import board.qna.command.qaWriteCommand;

/**
 * Servlet implementation class qaController
 */
@WebServlet("*.khy")
public class qaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public qaController() {
        super();
    
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("doGet");
		actionDo(req,res);
	
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		System.out.println("doPost");
		actionDo(req,res);
	}

	protected void actionDo(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String viewPage = null;
		qaCommand qac = null;
		
		String uri = req.getRequestURI();
		String conPath = req.getContextPath();
		String com = uri.substring(conPath.length());
		System.out.println(com);
		
		if(com.equals("/qaList.khy")) {				//QnA 목록 
			qac = new qaListCommand();
			qac.execute(req, res);
			viewPage = "listQnA.jsp";
		}else if(com.equals("/qaContent.khy")) {	//QnA 내용
			
			String check = (String)req.getParameter("check");
			if(("true").equals(check)) {
				qac = new qaConCommand();
				qac.execute(req, res);
				req.setAttribute("test","con");
				viewPage = "dmlOk.jsp";					//새로고침으로 인한 조회수 증가 방지 				
			}
			if(("false").equals(check)) {						
				qac = new qaConCommand();
				qac.execute(req, res);
				viewPage = "contentQnA.jsp"; }	
			
		}else if(com.equals("/writeReply.khy")) {	//답글 작성 
			qac = new ReplyWriteCommand();
			qac.execute(req,res);
			viewPage = "dmlOk.jsp";
		}else if(com.equals("/writeQnA.khy")) {		//QnA 작성 
			qac = new qaWriteCommand();
			qac.execute(req,res);
			viewPage = "dmlOk.jsp";
		}else if(com.equals("/qaDelete.khy")) {		//QnA 삭제 (비밀번호 체크 후 삭제)
			qac = new qaDeleteCommand();
			qac.execute(req,res);
			viewPage = "dmlOk.jsp";	
		}else if(com.equals("/modifyQnA.khy")) {		//QnA 수정 (현재 아무나 수정가능...)
			qac = new qaModifyCommand();
			qac.execute(req,res);
			viewPage = "dmlOk.jsp";	
		}else if(com.equals("/ReplyDelete.khy")) {		//답글 삭제 (admin만 가능)W
			qac = new ReplyDeleteCommand();
			qac.execute(req,res);
			viewPage = "dmlOk.jsp";	
		}else if(com.equals("/searchQnA.khy")) {
			qac = new qaListSearchCommand();
			qac.execute(req, res);
			viewPage = "listQnA.jsp";
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/qaboard/"+viewPage);
		dispatcher.forward(req, res);
		
		
	}
}


