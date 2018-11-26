package board.loghistory.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LogHistoryDAO {

	DataSource dataSource;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	LogHistoryDTO dto;

	public LogHistoryDAO() {

		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public List<LogHistoryDTO> LogHislist(String pageNum) {
		List<LogHistoryDTO> list = new ArrayList<LogHistoryDTO>();

		int page;

		if (pageNum == null) {
			page = 1;
		} else {
			page = Integer.parseInt(pageNum);
		} 

		int countPage = 10;

		int query_startPage = (page - 1) * countPage + 1; 
		int query_endPage = page * countPage; 

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {

			String sql = "select X.rnum, X.lognum, X.id, X.name, to_char(X.logtime,'yyyy-MM-dd HH24:mm:ss') as logtime, X.record, X.ip from ("

					+"select rownum as rnum, A.lognum, A.id, A.name, A.logtime, A.record, A.ip " 

					+"from ("

					+"select lognum, id, name, logtime, record, ip " 

					+"from logrec "

    				+"order by lognum desc) A " 

					+"where rownum <= "+query_endPage+") X " 

					+"where X.rnum >= "+ query_startPage+ "";

			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();

			while(rs.next()) {
				dto = new LogHistoryDTO();
				dto.setLog_num(rs.getString("lognum"));
				dto.setLog_id(rs.getString("id"));
				dto.setLog_name(rs.getString("name"));
				dto.setLog_time(rs.getString("logtime"));
				dto.setRecord(rs.getString("record"));
				dto.setLog_ip(rs.getString("ip"));
				list.add(dto);
			}

		}catch(SQLException e) {
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

	public String CountList() {

		String count = null;

		String sql = "select count(*) as count from logrec";

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

	public List<LogHistoryDTO> searchLogHis(String pageNum,String content){
		List<LogHistoryDTO> list = new ArrayList<LogHistoryDTO>();

		int page;

		if (pageNum == null) {
			page = 1;
		} else {
			page = Integer.parseInt(pageNum);
		} 

		int countPage = 10;

		int query_startPage = (page - 1) * countPage + 1; 
		int query_endPage = page * countPage; 

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {

			String sql = "select X.rnum, X.lognum, X.id, X.name, to_char(X.logtime,'yyyy-MM-dd HH24:mm:ss') as logtime, X.record, X.ip from ("

					+"select rownum as rnum, A.lognum, A.id, A.name, A.logtime, A.record, A.ip " 

					+"from ("

					+"select lognum, id, name, logtime, record, ip " 

					+"from logrec "

					+"where id LIKE '%" + content + "%'"

    				+"order by lognum desc) A " 

					+"where rownum <= "+query_endPage+") X " 

					+"where X.rnum >= "+ query_startPage+ "";

			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();

			while(rs.next()) {
				dto = new LogHistoryDTO();
				dto.setLog_num(rs.getString("lognum"));
				dto.setLog_id(rs.getString("id"));
				dto.setLog_name(rs.getString("name"));
				dto.setLog_time(rs.getString("logtime"));
				dto.setRecord(rs.getString("record"));
				dto.setLog_ip(rs.getString("ip"));
				list.add(dto);
			}

		}catch(SQLException e) {
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

	public String searchCountList(String content) {

		String count = null;

		String sql = "select count(*) as count from logrec where id like '%" + content + "%'";

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
}
