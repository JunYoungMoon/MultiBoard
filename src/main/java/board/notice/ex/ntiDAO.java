package board.notice.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;


public class ntiDAO {

	DataSource dataSource;

	public ntiDAO() {

		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public void ntilistDelete(int cast) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String count = null;

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "delete from board where i_count = " + cast;
		
		try {	
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			       			
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
		
	}
	
	public String ntilistTotalcount() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String count = null;

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql2 = "select count(*) as count from board";

		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
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
	
	public String ntilistSearchcount(String count,String content) {
		
		List<ntiDTO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ntiDTO dto = null;

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		

		int page1;

		if (count == null) {
			page1 = 1;
		} else {
			page1 = Integer.parseInt(count);
		} //�럹�씠吏� 珥덇린�솕

		int countPage = 10;

		int query_startPage = (page1 - 1) * countPage + 1; //荑쇰━臾몄뿉 �뱾�뼱媛� �떆�옉媛� 
		int query_endPage = page1 * countPage; //荑쇰━臾몄뿉 �뱾�뼱媛� �븻�뱶媛� 


		try {
			conn = dataSource.getConnection();
			
			String sql = "select count(*) as count " + "from (" +

							"select rownum as rnum, A.i_Count, A.v_Name, A.v_Title, A.d_Date " +

							"from ("

							+ "select i_Count, v_Name, v_Title, d_Date "

							+ "from board " 
							
							+ "where v_Title LIKE '%" + content + "%' "							

							+"order by i_Count desc) A " 

							+ "where rownum <= " + query_endPage + ") X "

							+ "where X.rnum >= " + query_startPage + "";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
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

	public List<ntiDTO> ntilist(String count) {

		List<ntiDTO> list = new ArrayList<ntiDTO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ntiDTO dto = null;

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql2 = "select count(*) as count from board";

		dto = new ntiDTO();

		try {
			pstmt = conn.prepareStatement(sql2);
			rs= pstmt.executeQuery();
			rs.next();
			dto.setCount(rs.getInt("count"));

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

		int totalCount = dto.getCount();

		int page1;

		if (count == null) {
			page1 = 1;
		} else {
			page1 = Integer.parseInt(count);
		} //�럹�씠吏� 珥덇린�솕

		int countPage = 10;

		int query_startPage = (page1 - 1) * countPage + 1; //荑쇰━臾몄뿉 �뱾�뼱媛� �떆�옉媛� 
		int query_endPage = page1 * countPage; //荑쇰━臾몄뿉 �뱾�뼱媛� �븻�뱶媛� 

		int r_num = totalCount - (page1 - 1) * countPage; //�럹�씠吏� �닚踰� �뿭�닚�쑝濡� �굹�삤寃� �븯湲�
		
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			String sql = "select X.rnum, X.i_Count, X.v_Name, X.v_Title, to_char(X.d_Date) as d_Date " + "from (" +

							"select rownum as rnum, A.i_Count, A.v_Name, A.v_Title, A.d_Date " +

							"from ("

							+ "select i_Count, v_Name, v_Title, d_Date "

							+ "from board " +

							"order by i_Count desc) A " +

							"where rownum <= " + query_endPage + ") X "

							+ "where X.rnum >= " + query_startPage + "";

			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();

			while(rs.next()) {
				dto = new ntiDTO();
				dto.setCount(rs.getInt("i_Count"));
				dto.setName(rs.getString("v_Name"));
				dto.setDate(rs.getString("d_Date"));
				dto.setTitle(rs.getString("v_Title"));
				dto.setR_num(r_num);
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

	public ntiDTO contentView(int cast) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ntiDTO dto = null;

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}			

		try {	
			String sql = "select i_count,v_name,to_char(d_date) as d_date,v_title,v_content,file_name,file_path from board where i_count = "+ cast;

			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();	

			while(rs.next()) {	
				dto = new ntiDTO();					
				dto.setCount(rs.getInt("i_Count"));	
				dto.setName(rs.getString("v_Name"));	
				dto.setDate(rs.getString("d_Date"));	
				dto.setTitle(rs.getString("v_Title"));	
				dto.setContent(rs.getString("v_content"));
				dto.setFileName(rs.getString("file_name"));
				dto.setFilePath(rs.getString("file_path"));
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
		return dto;
	}



	public ntiNextListDTO ntiNextList(String count){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ntiDTO dto = null;

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql2 = "select count(*) as count from board";

		dto = new ntiDTO();

		try {
			pstmt = conn.prepareStatement(sql2);
			rs= pstmt.executeQuery();
			rs.next();
			dto.setCount(rs.getInt("count"));

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

		int page1;

		if (count == null) {
			page1 = 1;
		} else {
			page1 = Integer.parseInt(count);
		} //�럹�씠吏� 珥덇린�솕

		int countPage = 10;
		int countList = 10; //10媛쒖쓽 寃뚯떆湲� 由ъ뒪�듃 
		int totalPage = dto.getCount() / countList; // 珥� �럹�씠吏��쓽 �닔	 13/10 1 

		int startPage = ((page1 - 1) / 10) * 10 + 1; // �뒪���듃 吏��젏 1~10 �� 紐⑤몢 1, 11~20�� 紐⑤몢 11 �뒪���듃 吏��젏 1
		int endPage = startPage + countPage - 1; // �걹�굹�뒗 吏��젏 1~10 �� 紐⑤몢 10, 11~20�� 紐⑤몢 20 �걹�굹�뒗 吏��젏 1+10-1 10

		if (dto.getCount() % countList > 0) {
			totalPage++;
		} //totalCount�쓽 �옄�닾由� 遺�遺� �럹�씠吏� +1 異붽� 泥섎━ 13%10 �굹癒몄� 3 �넗�깉 �럹�씠吏� 2媛쒕줈 利앷�				
		if (endPage > totalPage) {
			endPage = totalPage;
		} //10 > 2 �걹�굹�뒗 �럹�씠吏�媛� �쟾泥� �럹�씠吏� 蹂대떎 �겢�뻹

		ntiNextListDTO nnldto = new ntiNextListDTO();

		nnldto.setStartPage(startPage);
		nnldto.setEndPage(endPage);
		nnldto.setPage1(page1);
		nnldto.setTotalPage(totalPage);

		return nnldto;		
	}
	
	
	public ntiNextListDTO ntiSearchNextList(String count,String content){

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ntiDTO dto = null;

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql2 = "select count(*) from board where v_Title LIKE '%"+ content +"%'";

		dto = new ntiDTO();

		try {
			pstmt = conn.prepareStatement(sql2);
			rs= pstmt.executeQuery();
			rs.next();
			dto.setCount(rs.getInt("count"));

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

		int page1;

		if (count == null) {
			page1 = 1;
		} else {
			page1 = Integer.parseInt(count);
		} //�럹�씠吏� 珥덇린�솕

		int countPage = 10;
		int countList = 10; //10媛쒖쓽 寃뚯떆湲� 由ъ뒪�듃 
		int totalPage = dto.getCount() / countList; // 珥� �럹�씠吏��쓽 �닔	 13/10 1 
		
		if (dto.getCount() % countList > 0) {
			totalPage++;
		} //totalCount�쓽 �옄�닾由� 遺�遺� �럹�씠吏� +1 異붽� 泥섎━ 13%10 �굹癒몄� 3 �넗�깉 �럹�씠吏� 2媛쒕줈 利앷�				


		int startPage = ((totalPage - 1) / 10) * 10 + 1; // �뒪���듃 吏��젏 1~10 �� 紐⑤몢 1, 11~20�� 紐⑤몢 11 �뒪���듃 吏��젏 1
		int endPage = startPage + countPage - 1; // �걹�굹�뒗 吏��젏 1~10 �� 紐⑤몢 10, 11~20�� 紐⑤몢 20 �걹�굹�뒗 吏��젏 1+10-1 10
		
		if (endPage > totalPage) {
			endPage = totalPage;
		} 

		ntiNextListDTO nnldto = new ntiNextListDTO();

		nnldto.setStartPage(startPage);
		nnldto.setEndPage(endPage);
		nnldto.setPage1(page1);
		nnldto.setTotalPage(totalPage);

		return nnldto;		
	}


	public List<ntiDTO> ntiSearchList(String count,String content){

		List<ntiDTO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ntiDTO dto = null;

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql2 = "select count(*) as count from board";

		dto = new ntiDTO();

		try {
			pstmt = conn.prepareStatement(sql2);
			rs= pstmt.executeQuery();
			rs.next();
			dto.setCount(rs.getInt("count"));

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

		int totalCount = dto.getCount();

		int page1;

		if (count == null) {
			page1 = 1;
		} else {
			page1 = Integer.parseInt(count);
		} //�럹�씠吏� 珥덇린�솕

		int countPage = 10;

		int query_startPage = (page1 - 1) * countPage + 1; //荑쇰━臾몄뿉 �뱾�뼱媛� �떆�옉媛� 
		int query_endPage = page1 * countPage; //荑쇰━臾몄뿉 �뱾�뼱媛� �븻�뱶媛� 

		int r_num = totalCount - (page1 - 1) * countPage; //�럹�씠吏� �닚踰� �뿭�닚�쑝濡� �굹�삤寃� �븯湲�


		try {
			conn = dataSource.getConnection();
			
			String sql = "select X.rnum, X.i_Count, X.v_Name, X.v_Title, to_char(X.d_Date) as d_Date " + "from (" +

							"select rownum as rnum, A.i_Count, A.v_Name, A.v_Title, A.d_Date " +

							"from ("

							+ "select i_Count, v_Name, v_Title, d_Date "

							+ "from board " 
							
							+ "where v_Title LIKE '%" + content + "%' "							

							+"order by i_Count desc) A " 

							+ "where rownum <= " + query_endPage + ") X "

							+ "where X.rnum >= " + query_startPage + "";

			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();

			while(rs.next()) {
				dto = new ntiDTO();
				dto.setCount(rs.getInt("i_Count"));
				dto.setName(rs.getString("v_Name"));
				dto.setDate(rs.getString("d_Date"));
				dto.setTitle(rs.getString("v_Title"));
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
	
	public void ntiInsert(ntiDTO dto) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
		try {
	 		String sql = "insert into board values(board_seq.nextval,?,?,?,?,?,?)";	
	 		pstmt = conn.prepareStatement(sql);
	 		pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getDate());	
			pstmt.setString(3, dto.getTitle());	
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getFileName());	
			pstmt.setString(6, dto.getFilePath());
	 		pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	public ntiDTO fileDown(int count) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ntiDTO dto = null;

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql2 = "select file_path from board where i_Count = "+count;

		dto = new ntiDTO();

		try {
			pstmt = conn.prepareStatement(sql2);
			rs= pstmt.executeQuery();
			rs.next();
			dto.setFilePath(rs.getString("file_path"));
			System.out.println(dto.getFilePath());

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
		
		return dto;
	}
	
	public void ntiUpdate(ntiDTO dto, int count) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		
		try {
	 		String sql = "update board set v_name = ?, d_date = ?, v_title = ?, v_content = ?, file_name = ?, file_path = ? where i_count = "+ count;	
	 		pstmt = conn.prepareStatement(sql);	
	 		pstmt.setString(1, dto.getName());	
			pstmt.setString(2, dto.getDate());	
			pstmt.setString(3, dto.getTitle());	
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getFileName());
			pstmt.setString(6, dto.getFilePath());
	 		pstmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
}
