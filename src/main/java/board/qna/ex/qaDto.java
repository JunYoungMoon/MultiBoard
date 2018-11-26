package board.qna.ex;

public class qaDto {
	
	private int qa_id;
	private String qa_name;
	private String qa_sub;
	private String qa_con;
	private String qa_date;
	private int qa_read;
	private int qa_count;
	private String qa_pwd;
	private String write_id;
	private int count;
	private int rnum;
	private String fileName;
	private String filePath;
	
	
	
	public qaDto() {
		
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getQa_id() {
		return qa_id;
	}
	public void setQa_id(int qa_id) {
		this.qa_id = qa_id;
	}
	public String getQa_name() {
		return qa_name;
	}
	public void setQa_name(String qa_name) {
		this.qa_name = qa_name;
	}
	public String getQa_sub() {
		return qa_sub;
	}
	public void setQa_sub(String qa_sub) {
		this.qa_sub = qa_sub;
	}
	public String getQa_con() {
		return qa_con;
	}
	public void setQa_con(String qa_con) {
		this.qa_con = qa_con;
	}
	public String getQa_date() {
		return qa_date;
	}
	public void setQa_date(String qa_date) {
		this.qa_date = qa_date;
	}
	public int getQa_read() {
		return qa_read;
	}
	public void setQa_read(int qa_read) {
		this.qa_read = qa_read;
	}
	public int getQa_count() {
		return qa_count;
	}
	public void setQa_count(int qa_count) {
		this.qa_count = qa_count;
	}
	public String getQa_pwd() {
		return qa_pwd;
	}
	public void setQa_pwd(String qa_pwd) {
		this.qa_pwd = qa_pwd;
	}
	public String getWrite_id() {
		return write_id;
	}

	public void setWrite_id(String write_id) {
		this.write_id = write_id;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

		
	

}
