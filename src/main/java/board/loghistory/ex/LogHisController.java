package board.loghistory.ex;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.loghistory.command.LogHisCommand;
import board.loghistory.command.LogHisListCommand;
import board.loghistory.command.LogHisSearchListCommand;
import board.notice.command.ntiCommand;
import board.notice.command.ntiListCommand;
import board.notice.command.ntiSearchCommand;
import board.notice.command.ntiViewCommand;

/**
 * Servlet implementation class LogHisController
 */
@WebServlet("*.do")
public class LogHisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   
    public LogHisController() {
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
		LogHisCommand lhc = null;
		
		String uri = req.getRequestURI();
		String conPath = req.getContextPath();
		String com = uri.substring(conPath.length());
		
		String content = req.getParameter("content");	
		System.out.println("content의 값 : "+content);
		
		if(content == null || content.trim().equals("") || ("null").equals(content)) {
			content = "0";
			System.out.println("content null 여부 체크 : "+content);
		}	
		

		if(com.equals("/loghistory.do")) {
			if(content.equals("0")) {
				lhc = new LogHisListCommand();
				System.out.println("LogHisListCommand 객체 생성 완료.");
				lhc.execute(req, res);
				viewPage = "loghislist.jsp";
			}else {
				lhc = new LogHisSearchListCommand();
				System.out.println("LogHisSearchListCommand 객체 생성 완료.");
				lhc.execute(req, res);
				viewPage = "loghislist.jsp";				
			}			
		}else if(com.equals("/excelDownData.do")){
			lhc = new LogHisListCommand();
			System.out.println("LogHisListCommand 객체 생성 완료.");
			lhc.execute(req, res);
			viewPage = "excelDownData.jsp";
		}		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/logHistory/"+viewPage);
		
		dispatcher.forward(req, res);	
	}

}
