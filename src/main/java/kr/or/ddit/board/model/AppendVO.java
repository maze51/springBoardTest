package kr.or.ddit.board.model;

public class AppendVO {
	
	private String append_id;
	private int append_article;
	private String append_path;
	private String append_filename;
	
	public AppendVO() {
		
	}
	
	public AppendVO(String append_id, int append_article, String append_path,
			String append_filename) {
		super();
		this.append_id = append_id;
		this.append_article = append_article;
		this.append_path = append_path;
		this.append_filename = append_filename;
	}

	public String getAppend_id() {
		return append_id;
	}

	public void setAppend_id(String append_id) {
		this.append_id = append_id;
	}

	public int getAppend_article() {
		return append_article;
	}

	public void setAppend_article(int append_article) {
		this.append_article = append_article;
	}

	public String getAppend_path() {
		return append_path;
	}

	public void setAppend_path(String append_path) {
		this.append_path = append_path;
	}

	public String getAppend_filename() {
		return append_filename;
	}

	public void setAppend_filename(String append_filename) {
		this.append_filename = append_filename;
	}
	
	
}
