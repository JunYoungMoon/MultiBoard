
package board.qna.ex;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class rDao {
	
	DataSource dataSource;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	int rn = 0;
	int rn2 = 0;
	ResultSet rs = null;
	
	public rDao() {

		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	//답글 목록보기 
	public ArrayList<rDto> listR(int qId) {

		ArrayList<rDto> list = new ArrayList<rDto>();
		
		rDto dto = null;

		try {
			conn = dataSource.getConnection();

			String sql = "select re_id, re_name, re_content, to_char(re_date,'yyyy-MM-dd HH24:mm') re_date , re_group,re_step, re_indent,qa_id from ReplyBoard "
						+ "where qa_id = ? order by re_id"; 
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qId);
			rs= pstmt.executeQuery();

			while(rs.next()) {
				
				dto = new rDto();
				
				dto.setRe_id(rs.getInt("re_id"));
				dto.setRe_name(rs.getString("re_name"));
				dto.setRe_con(rs.getString("re_content"));
				dto.setRe_date(rs.getString("re_date"));
				dto.setRe_group(rs.getInt("re_group"));
				dto.setRe_indent(rs.getInt("re_indent"));
				dto.setQa_id(rs.getInt("qa_id"));
				
				list.add(dto);
				
							}
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

		return list;
	}
	
	//답글 작성 (현재 코드는 admin만 작성 가능), 추후 답글에 답글 작성 위해 코드 추가 
	public void writeR(int no, String content) {
		try {
			conn = dataSource.getConnection();
			
			CallableStatement cstmt = conn.prepareCall("{call writeR(?,?)}");
			 
			cstmt.setInt(1, no);
			cstmt.setString(2, content);
			
			int rn = cstmt.executeUpdate();
			
			if(rn > 0)
				System.out.println(rn + "개의 행이 QnAboard와 ReplyBoard에서 수정되었습니다.");
			else
				System.out.println("실패");
			
		}catch (SQLException e) {

			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
			} catch (Exception e2) {
		
				e2.printStackTrace();
			}
		}
	}
	
	public void rereR(int no, String re_id ,String name, String content) {
	      
	      System.out.println("re_id:"+re_id);
	   
	      try {
	    	  conn = dataSource.getConnection();
				
			  CallableStatement cstmt = conn.prepareCall("{call rereR(?,?,?,?)}");
			  cstmt.setInt(1, no);
			  cstmt.setString(2, re_id);
			  cstmt.setString(3, name);
			  cstmt.setString(4, content);
			  
			  int rn = cstmt.executeUpdate();
				
				if(rn > 0)
					System.out.println(rn + "개의 행이 ReplyBoard에서 insert되었습니다.");
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


	//답글 삭제 (admin만 삭제 가능)
	public void deleteR(int reNo, int qaNo) {
		
		try {
			conn = dataSource.getConnection();
			
			CallableStatement cstmt = conn.prepareCall("{call deleteR(?,?)}");
			cstmt.setInt(1, reNo);
			cstmt.setInt(2, qaNo);
			
			rn = cstmt.executeUpdate();
			
			
			if(rn == 1) {
				System.out.println(rn + "deleteQ 실행 Ok");
			    System.out.println("reindent 가" + reNo +"인 행 삭제되었습니다. ");}
			else
				System.out.println("실패");
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				
				if(conn != null) conn.close();
			} catch (Exception e2) {
		
				e2.printStackTrace();
			}
		}
	}		
	
	
}