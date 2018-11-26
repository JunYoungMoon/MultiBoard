package board.file;

import java.awt.Transparency;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class FileDAO {

	FileDTO dto = null;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	DataSource dataSource;

	public FileDAO() {

		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void fileInsert(FileDTO dto) {

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			String sql = "insert into image values(image_seq.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFilePath());
			pstmt.setString(2, dto.getFileName());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getFileName2());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}
	}

	public FileDTO contentView(String cast) {
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "select file_name, file_title, file_content, file_name2, file_count from image where file_count = " + cast;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new FileDTO();
				dto.setFileName(rs.getString("file_name"));
				dto.setContent(rs.getString("file_content"));
				dto.setTitle(rs.getString("file_title"));
				dto.setFileName2(rs.getString("file_name2"));
				dto.setCount(rs.getInt("file_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}
		System.out.println(cast);

		return dto;
	}

	public void fileUpdate(FileDTO dto) {

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			String sql = "update image set file_name = ?, file_path = ?, file_title = ?, file_content = ?, file_name2 = ? where file_count = "
					+ dto.count;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFileName());
			pstmt.setString(2, dto.getFilePath());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getFileName2());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}
	}

	public void fileDelete(int cast) {
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "delete from image where file_count = " + cast;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}
	}

	public FileDTO fileCount() {
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "select count(*) as count from image";

		dto = new FileDTO();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			dto.setCount(rs.getInt("count"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}

		return dto;
	}

	public List<FileDTO> fileList(String count) {

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql2 = "select count(*) as count from image";

		dto = new FileDTO();

		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			rs.next();
			dto.setCount(rs.getInt("count"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}

		int totalCount = dto.getCount();	//이미지의 총 갯수 ex)13

		int nowPage;	// 현재 페이지를 나타냄

		if (count == null) {	//아무값이 입력되지 않으면 현재 페이지를 1로 만듬
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(count);	// 2페이지라고 하면 현재 페이지를 2로 만듬
		} 

		int countPage = 8;	// 리스트에서 8개 까지만 나타나게 할 예정

		// 1~8 , 9~16 을 나타냄
		int query_startPage = (nowPage - 1) * countPage + 1; //	리스트에 뿌려줄 값의 시작을 나타냄 1페이지는 시작값이 1 2페이지의 시작값은 8 부터 나타냄	
		int query_endPage = nowPage * countPage; //	리스트에 뿌려줄 값의 마지막을 나타냄 1페이지는 마지막 값이 8 2페이지의 마지막값은 16

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = "select X.rnum, X.file_count, X.file_title, X.file_content, X.file_name from ("

				+ "select rownum as rnum, A.file_count, A.file_title, A.file_content, A.file_name"

				+ " from ("

				+ "select file_count, file_title, file_content, file_name"

				+ " from image"

				+ " order by file_count desc) A"

				+ " where rownum <= " + query_endPage + ") X"

				+ " where X.rnum >= " + query_startPage + "";

		List<FileDTO> list = new ArrayList<>();

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new FileDTO();
				dto.setCount(rs.getInt("file_count"));
				dto.setTitle(rs.getString("file_title"));
				dto.setContent(rs.getString("file_content"));
				dto.setFileName(rs.getString("file_name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}
		return list;
	}

	public FileNextListDTO fileNextList(String count) {

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql2 = "select count(*) as count from image";

		dto = new FileDTO();

		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			rs.next();
			dto.setCount(rs.getInt("count"));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}

		int nowPage;

		if (count == null) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(count);
		}

		int countPage = 10;	//넥스트 버튼을 10개로 지정
		int countList = 8; // 8개를 리스트에 뿌려줌
		int totalPage = dto.getCount() / countList; // 만약 13개의 이미지가 있으면 13/8 로 몫을 구함

		int startPage = ((nowPage - 1) / 10) * 10 + 1; // 페이징 시작 버튼을 1,11,21 로 나타냄
		int endPage = startPage + countPage - 1; // 페이지 끝 버튼을 10,20,30을 나타냄

		if (dto.getCount() % countList > 0) {
			totalPage++;
		} // 페이지 자투리 부분을 담당함
		if (endPage > totalPage) {
			endPage = totalPage;
		} // 10 > 2 

		FileNextListDTO fnldto = new FileNextListDTO();

		fnldto.setStartPage(startPage);
		fnldto.setEndPage(endPage);
		fnldto.setPage1(nowPage);
		fnldto.setTotalPage(dto.getCount());

		return fnldto;

	}

	public FileNextListDTO fileNextListTwo(String count, int listsize) {

		int page1;

		if (count == null) {
			page1 = 1;
		} else {
			page1 = Integer.parseInt(count);
		} // �럹�씠吏� 珥덇린�솕

		int countPage = 10;
		int countList = 8; // 10媛쒖쓽 寃뚯떆湲� 由ъ뒪�듃
		int totalPage = listsize / countList; // 珥� �럹�씠吏��쓽 �닔 13/10 1

		int startPage = ((page1 - 1) / 10) * 10 + 1; // �뒪���듃 吏��젏 1~10 �� 紐⑤몢 1, 11~20�� 紐⑤몢 11 �뒪���듃 吏��젏 1
		int endPage = startPage + countPage - 1; // �걹�굹�뒗 吏��젏 1~10 �� 紐⑤몢 10, 11~20�� 紐⑤몢 20 �걹�굹�뒗 吏��젏 1+10-1 10

		if (listsize % countList > 0) {
			totalPage++;
		} // totalCount�쓽 �옄�닾由� 遺�遺� �럹�씠吏� +1 異붽� 泥섎━ 13%10 �굹癒몄� 3 �넗�깉 �럹�씠吏� 2媛쒕줈 利앷�
		if (endPage > totalPage) {
			endPage = totalPage;
		} // 10 > 2 �걹�굹�뒗 �럹�씠吏�媛� �쟾泥� �럹�씠吏� 蹂대떎 �겢�뻹

		FileNextListDTO fnldto = new FileNextListDTO();

		fnldto.setStartPage(startPage);
		fnldto.setEndPage(endPage);
		fnldto.setPage1(page1);
		fnldto.setTotalPage(totalPage);

		return fnldto;
	}

	public List<FileDTO> fileSearchList(String count, String content) {

		try {
			conn = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		/*
		 * String sql2 = "select count(*) as count from image";
		 */
		dto = new FileDTO();

		int page1;

		if (count == null) {
			page1 = 1;
		} else {
			page1 = Integer.parseInt(count);
		} // �럹�씠吏� 珥덇린�솕

		int countPage = 8;

		int query_startPage = (page1 - 1) * countPage + 1; // 荑쇰━臾몄뿉 �뱾�뼱媛� �떆�옉媛�
		int query_endPage = page1 * countPage; // 荑쇰━臾몄뿉 �뱾�뼱媛� �븻�뱶媛�
		/*
		 * int r_num = totalCount - (page1 - 1) * countPage; //�럹�씠吏� �닚踰� �뿭�닚�쑝濡�
		 * �굹�삤寃� �븯湲�
		 */
		String sql = "select X.rnum, X.file_count, X.file_title, X.file_content, X.file_name from ("

				+ "select rownum as rnum, A.file_count, A.file_title, A.file_content, A.file_name"

				+ " from ("

				+ "select file_count, file_title, file_content, file_name"

				+ " from image"

				+ " where file_title LIKE '%" + content + "%'"

				+ " order by file_count desc) A"

				+ " where rownum <= " + query_endPage + ") X"

				+ " where X.rnum >= " + query_startPage + "";

		List<FileDTO> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new FileDTO();
				dto.setCount(rs.getInt("file_count"));
				dto.setTitle(rs.getString("file_title"));
				dto.setContent(rs.getString("file_content"));
				dto.setFileName(rs.getString("file_name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {

				e2.printStackTrace();
			}
		}
		return list;
	}

}
