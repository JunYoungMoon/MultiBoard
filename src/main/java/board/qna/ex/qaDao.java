package board.qna.ex;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;

public class qaDao {

	DataSource dataSource;
	Connection conn = null;
	Connection conn2 = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	int rn = 0;
	int rn2 = 0; 

	public qaDao() {

		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	
	//QnA 내용 보기 + 조회수 1 증가 
	public qaDto contentQ(int no,String check) {
		qaDto dto = new qaDto();

		try {
			conn = dataSource.getConnection();

			if(("true").equals(check)) {
				String sql = "update QnAboard set qa_read = qa_read+1 where qa_id =?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rn = pstmt.executeUpdate();

			}


			String sql2 = "select qa_id, qa_name, qa_subject,qa_content, qa_pwd, to_char(qa_date,'yyyy-MM-dd HH2424:mm') qa_date , qa_read, "
					      + "qa_replyCount,write_id,fileName, filePath from QnAboard" 
					      +" where qa_id = ?";

			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, no);
			rs2= pstmt2.executeQuery();


			while(rs2.next()) {

				dto.setQa_id(rs2.getInt("qa_id"));
				dto.setQa_name(rs2.getString("qa_name"));
				dto.setQa_sub(rs2.getString("qa_subject"));
				dto.setQa_con(rs2.getString("qa_content"));
				dto.setQa_pwd(rs2.getString("qa_pwd"));
				dto.setQa_date(rs2.getString("qa_date"));
				dto.setQa_read(rs2.getInt("qa_read"));
				dto.setQa_count(rs2.getInt("qa_replyCount"));
				dto.setWrite_id(rs2.getString("write_id"));
				dto.setFileName(rs2.getString("fileName"));
				dto.setFilePath(rs2.getString("filePath"));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(rs2 != null) rs2.close();
				if(pstmt2 != null) pstmt2.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}

		return dto;



	}

	//writeform 으로 QnAboard insert 
	public void writeQ(qaDto dto) {

		try {
			conn = dataSource.getConnection();

			String sql = "insert into QnAboard values(qna_board_sequence.nextval,?,?,?,sysdate,0,0,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getQa_name());
			pstmt.setString(2, dto.getQa_sub());
			pstmt.setString(3, dto.getQa_con());
			pstmt.setString(4, dto.getQa_pwd());
			pstmt.setString(5, dto.getWrite_id());
			pstmt.setString(6, dto.getFileName());
			pstmt.setString(7, dto.getFilePath());

			rn = pstmt.executeUpdate();

			if(rn == 1)
				System.out.println(rn + "개의 행이(가) QnAboard에 삽입되었습니다.");
			else
				System.out.println("실패");




		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt2 != null) pstmt2.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}


	}

	//삭제 시 비밀번호 체크 
	public int pwdCheck(int no, String pwd) {

		int CheckNum = 0;
		String qaPw;



		try {
			conn = dataSource.getConnection();

			String sql = "select qa_pwd from QnAboard where qa_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				qaPw = rs.getString("qa_pwd");
				if(qaPw.equals(pwd)) {
					CheckNum = 1;		
				} else {
					CheckNum = 0;		
				}
			} 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return CheckNum;
	}

	//QnA 삭제 
	public void deleteQ(int no) {

		try {
			
			conn = dataSource.getConnection(); 
			
			CallableStatement cstmt = conn.prepareCall("{call deleteQ(?)}");
			
			cstmt.setInt(1, no);
			
			rn = cstmt.executeUpdate();
			
			if(rn>0)
				System.out.println("deleteQ 실행 성공");
			else
				System.out.println("deleteQ 실행 실패");

		

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}

			
	}

	//QnA 내용 수정 
	public void modifyQ(int id, String sub, String cont) {

		try {
			conn = dataSource.getConnection();

			String sql = "update QnAboard set qa_subject = ?, qa_content = ?,qa_date = sysdate"
					+",qa_read = 0 where qa_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sub);
			pstmt.setString(2, cont);
			pstmt.setInt(3, id);

			rn = pstmt.executeUpdate();

			if(rn == 1)
				System.out.println(rn + "개의 행이(가) 수정되었습니다.");
			else
				System.out.println("실패");

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
	//QnAboard 총 column 개수 
	public String CountList() {

		String count = null;

		String sql = "select count(*) as count from QnAboard";

		try {

			conn = dataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			count=rs.getString("count");


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}



		return count;
	}
	
	//QnAboard 총 search column 개수 
	public String searchCountList(String content) {

		String count = null;

		String sql = "select count(*) as count from QnAboard where qa_subject like '%" + content + "%'";

		try {

			conn = dataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			count=rs.getString("count");


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}



		return count;
	}

	//QnAboard 목록
	public List<qaDto> listQ(String pageNum){

		int i = 1;
		int rCount = 0;
		qaDto dto = null;
		List<qaDto> list = new ArrayList<>();

		try {

			conn = dataSource.getConnection();

			int page;

			if (pageNum == null) {
				page = 1;
			} else {
				page = Integer.parseInt(pageNum);
			} 

			int countPage = 10;

			int query_startPage = (page - 1) * countPage + 1; 
			int query_endPage = page * countPage; 



			String sql2 = "select * from ("

				+ "select rownum as rnum, A.qa_id, A.qa_name, A.qa_subject,A.qa_pwd,A.qa_date,"

				+ "A.qa_read,A.qa_replyCount"

				+ " from ("

				+ "select qa_id, qa_name, qa_subject,qa_pwd,to_char(qa_date,'yyyy-MM-dd HH24:mm') as qa_date, qa_read, qa_replyCount"

				+ " from QnAboard"

				+ " order by qa_id desc) A"

				+ " where rownum <= " + query_endPage + ") X"

				+ " where X.rnum >= " + query_startPage + "";



			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			ResultSet rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				dto =new qaDto();

				dto.setQa_id(rs2.getInt("qa_id"));
				dto.setQa_name(rs2.getString("qa_name"));
				dto.setQa_sub(rs2.getString("qa_subject"));
				dto.setQa_pwd(rs2.getString("qa_pwd"));
				dto.setQa_date(rs2.getString("qa_date"));
				dto.setQa_read(rs2.getInt("qa_read"));
				rCount = rs2.getInt("qa_replyCount");
				
				if(rCount < 0 )
					dto.setQa_count(0);
				else
					dto.setQa_count(rCount);

				list.add(dto);
				System.out.println(i++);
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs2 != null) rs2.close();
				if(pstmt2 != null) pstmt2.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}

		return list;
	}


	public List<qaDto> searchQ(String pageNum,String content){

		int i = 1;
		qaDto dto = null;
		List<qaDto> list = new ArrayList<>();
		System.out.println("content:"+content);

		try {

			conn = dataSource.getConnection();

			int page;

			if (pageNum == null) {
				page = 1;
			} else {
				page = Integer.parseInt(pageNum);
			} 

			int countPage = 10;

			int query_startPage = (page - 1) * countPage + 1; 
			int query_endPage = page * countPage; 



			String sql2 = "select * from ("

				+ "select rownum as rnum, A.qa_id, A.qa_name, A.qa_subject,A.qa_pwd,A.qa_date,"

				+ "A.qa_read,A.qa_replyCount"

				+ " from ("

				+ "select qa_id, qa_name, qa_subject,qa_pwd, to_char(qa_date,'yyyy-MM-dd HH24:mm') qa_date , qa_read, qa_replyCount"

				+ " from QnAboard"

				+ " where qa_subject LIKE '%" + content + "%'"

				+ " order by qa_id desc) A"

				+ " where rownum <= " + query_endPage + ") X"

				+ " where X.rnum >= " + query_startPage + "";



			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			ResultSet rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				dto =new qaDto();

				dto.setQa_id(rs2.getInt("qa_id"));
				dto.setQa_name(rs2.getString("qa_name"));
				dto.setQa_sub(rs2.getString("qa_subject"));
				dto.setQa_pwd(rs2.getString("qa_pwd"));
				dto.setQa_date(rs2.getString("qa_date"));
				dto.setQa_read(rs2.getInt("qa_read"));
				dto.setQa_count(rs2.getInt("qa_replyCount"));

				list.add(dto);
				System.out.println(i++);
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs2 != null) rs2.close();
				if(pstmt2 != null) pstmt2.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}

		return list;
	}
	
	public void fileUpload(MultipartRequest multi) {
		
		String FileName = ""; // 중복처리된 이름
		String originalName1 = ""; // 중복 처리전 실제 원본 이름
		long fileSize = 0; // 파일 사이즈
		String fileType = ""; // 파일 타입
		
		
		Enumeration files = multi.getFileNames();
		
		System.out.println(files);
		while (files.hasMoreElements()) {
			// form 태그에서 <input type="file" name="여기에 지정한 이름" />을 가져온다.
			String file1 = (String) files.nextElement(); // 파일 input에 지정한 이름을 가져옴
			// 그에 해당하는 실제 파일 이름을 가져옴
			originalName1 = multi.getOriginalFileName(file1);
			// 파일명이 중복될 경우 중복 정책에 의해 뒤에 1,2,3 처럼 붙어 unique하게 파일명을 생성하는데
			// 이때 생성된 이름을 filesystemName이라 하여 그 이름 정보를 가져온다.(중복에 대한 처리)
			FileName = multi.getFilesystemName(file1);
			// 파일 타입 정보를 가져옴
			fileType = multi.getContentType(file1);
			// input file name에 해당하는 실제 파일을 가져옴
			File file = multi.getFile(file1);
			// 그 파일 객체의 크기를 알아냄
//			fileSize = file.length();
			
			
		}
			
	}
}