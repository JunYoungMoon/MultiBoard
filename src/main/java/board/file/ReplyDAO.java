package board.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReplyDAO {

	FileDTO dto = null;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int rn;

	DataSource dataSource;

	public ReplyDAO() {

		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {

			e.printStackTrace();
		}		
	}

	public void insertReply(int count, String name, String content) {

		try {
			conn = dataSource.getConnection();


			String sql = "insert into rBoard values(re_board_seq.nextval,?,sysdate,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, content);
			pstmt.setInt(3, count);

			rn = pstmt.executeUpdate();

			if(rn > 0)
				System.out.println(rn + "개의 행이 insert되었습니다.");
			else
				System.out.println("실패");



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


	public List<ReplyDTO> listReply(String replyCount){

		List<ReplyDTO> list = new ArrayList<ReplyDTO>();
		ReplyDTO dto = null;
		
		int count = Integer.parseInt(replyCount);

		try {
			conn = dataSource.getConnection();

			String sql = "select i_rid, v_rname, to_char(d_rdate, 'yyyy-MM-dd HH:mm') as d_date, v_rcontent from rBoard where i_file_count = "+ count;

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {

				dto = new ReplyDTO();

				dto.setRid(rs.getInt("i_rid"));
				dto.setRname(rs.getString("v_rname"));
				dto.setD_date(rs.getString("d_date"));
				dto.setRcontent(rs.getString("v_rcontent"));

				list.add(dto);
			}

		}catch (SQLException e) {

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
}




