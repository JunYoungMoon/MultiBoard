package board.qna.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.qna.ex.qaDao;
import board.qna.ex.qaDto;


public class qaWriteCommand implements qaCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String uploadPath = "C:\\Users\\User\\Desktop\\JAVA\\workspace\\board2\\WebContent\\upfile";

		int maxSize = 1024 * 1024 * 10;
		
		MultipartRequest multi = null;
		
		String filepath = "";
		
		
		//QnA 작성 하기 
		try {
			request.setCharacterEncoding("UTF-8");
			
			multi = new MultipartRequest(request, uploadPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			
			qaDto dto = new qaDto();
			
			dto.setQa_sub(multi.getParameter("subject"));
			System.out.println(multi.getParameter("subject"));
			dto.setQa_name(multi.getParameter("name"));
			dto.setQa_con(multi.getParameter("content"));
			dto.setQa_pwd(multi.getParameter("qa_pwd"));
			dto.setWrite_id(multi.getParameter("id"));
			String fName = multi.getOriginalFileName("file");
			
			System.out.println("fileName: " + fName);
			
			if(fName== null) {
				dto.setFileName("");
				dto.setFilePath("");
			}
				
			else {
				dto.setFileName(fName);
				dto.setFilePath(uploadPath + "\\" + fName);
			}
		
			qaDao dao = new qaDao();
			
			dao.writeQ(dto);
			dao.fileUpload(multi);
			
			//dmlOk.jsp 에서 구분 
			request.setAttribute("test", "qw");
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};
}

