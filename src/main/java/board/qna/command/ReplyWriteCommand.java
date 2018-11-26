package board.qna.command;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qna.ex.rDao;

public class ReplyWriteCommand implements qaCommand{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		//답글 작성 command 
		try {
			
			request.setCharacterEncoding("UTF-8");
			String no = (String)request.getParameter("qa_id");
			
			System.out.println("qa_id:"+no);
			int qa_id=0;
			
			if(no!=null)
				qa_id = Integer.parseInt(no);
			
			
			String con = request.getParameter("comment");
			String reId = request.getParameter("re_id");
			
			/*int re_id = Integer.parseInt(reId); */
			String re_name = request.getParameter("re_name");

			rDao rdao = new rDao();
			System.out.println(re_name);
			if(re_name != null)
				rdao.rereR(qa_id, reId,re_name,con);
			else
				rdao.writeR(qa_id, con);
			
			//dmlOk.jsp 에서 구분 
			request.setAttribute("test", "rw");
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		
	};

}
