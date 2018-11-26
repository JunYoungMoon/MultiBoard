package board.member;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.member.*;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet set = null;
	DataSource dataSource;
	
	public MemberDAO() {		

			try {
				Context context = new InitialContext();
				dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
			} catch (Exception e) {

				e.printStackTrace();
			}		
	}
	
	public static MemberDAO getInstance(){
		return instance;
	}
	
	public int confirmId(String id) {
		
		int ri = 0;
		
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "select id from member where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()){
				ri = 1;
			} else {
				ri = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public MemberDTO getMember(String id) {
		
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "select id,pwd,name,email,phone1,phone2,phone3 from member where id = ?";
		MemberDTO dto = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				dto = new MemberDTO();
				dto.setId(set.getString("id"));
				dto.setPwd(set.getString("pwd"));
				dto.setName(set.getString("name"));
				dto.setEmail(set.getString("email"));
				dto.setPhone1(set.getString("phone1"));
				dto.setPhone2(set.getString("phone2"));
				dto.setPhone3(set.getString("phone3"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}		
		return dto;	
	}
	
	public int insertMember(MemberDTO dto) {
		int ri = 0;
		
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "insert into member values (member_seq.nextval,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhone1());
			pstmt.setString(6, dto.getPhone2());
			pstmt.setString(7, dto.getPhone3());
			pstmt.executeUpdate();
			ri = 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	public int userCheck(MemberDTO dto,String pwd,String ip) {
		
		int check = 0;
		
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		try {
			CallableStatement cstmt = conn.prepareCall("{call pro_log(?,?,?,?,?)}");
			
			cstmt.setString(1, dto.getId());
			cstmt.setString(2, pwd);			
			cstmt.setString(3, dto.getName());
			cstmt.setString(4, ip);
			cstmt.registerOutParameter(5,java.sql.Types.FLOAT);
			
			int r = cstmt.executeUpdate();
			
			check = cstmt.getInt(5);	
			System.out.println("try 안의 check : " + check);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}
		System.out.println("try 밖의 check :" + check);
		return check;
	}
	 
	
	public int updateMember(MemberDTO dto) {
		int ri = 0;
		
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql = "update member set pwd=?, email=?, phone1=?, phone2=?, phone3=? where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getPhone1());
			pstmt.setString(4, dto.getPhone2());
			pstmt.setString(5, dto.getPhone3());
			pstmt.setString(6, dto.getId());
			ri = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(set != null) set.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	public int deleteMember(MemberDTO dto) {
		int ri = 0;   

		try {
			conn = dataSource.getConnection();
			String sql = "delete from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			ri = pstmt.executeUpdate();
			
			if(ri > 0 )
				System.out.println(ri+"개의 행 삭제 성공");
			else
				System.out.println("실패");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	
	public void logout(MemberDTO dto,String ip) {
		
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql =  "insert into logrec values (log_seq.nextval, ?, ?, 'logout', sysdate, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, ip);

			pstmt.executeUpdate();
			
			System.out.println("logout insert 완료.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
	}
}
