package board.file;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.command.ntiCommand;


@WebServlet("*.fmjy")
public class ImgController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ImgController() {
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
		
		String uri = req.getRequestURI();
		String conPath = req.getContextPath();
		String com = uri.substring(conPath.length());
		
		req.setCharacterEncoding("UTF-8");
		String viewPage = null;
		ntiCommand qac = null;
		
		ReplyDAO dao = new ReplyDAO();
		
		String count = req.getParameter("key");
		if(count == null || count.trim().equals("")) {
			count = "0";
		}
		System.out.println("action Do : " +count);
		int num =Integer.parseInt(count);		
		
		if(com.equals("/image.fmjy")) {
			System.out.println("con /image.fmjy : ");			
			viewPage = "list.jsp";
		}else if(com.equals("/view.fmjy")) {
			System.out.println("view.fmjy 호출");			
			System.out.println("현재 키 " + count);	
			viewPage = "View.jsp";
		}else if(com.equals("/rinput.fmjy")) {
			System.out.println("rinput.fmjy 호출");			
			System.out.println("현재 키 " + count);	
			String name = req.getParameter("name"); 
			String comment = req.getParameter("comment"); 
			dao.insertReply(num,name,comment);
						
			req.setAttribute("key", req.getParameter("key"));
			viewPage = "dmlOk.jsp";
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/file/"+viewPage);
		
		dispatcher.forward(req, res);		
	}	

}
