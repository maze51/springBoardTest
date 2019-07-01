package kr.or.ddit.board.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleVO {
	private int article_number;
	private String article_user;
	private String article_board;
	private int article_pid;
	private String article_title;
	private String article_content;
	private Date article_date;
	private String article_use;
	private int article_group;
	private int lv;
	
	public ArticleVO() {
		
	}
	
	public ArticleVO(int article_number, String article_title, String article_content) {
		super();
		this.article_number = article_number;
		this.article_title = article_title;
		this.article_content = article_content;
	}

	public ArticleVO(int article_number, String article_user,
			String article_board, String article_title,
			String article_content, int article_group) {
		this.article_number = article_number;
		this.article_user = article_user;
		this.article_board = article_board;
		this.article_title = article_title;
		this.article_content = article_content;
		this.article_group = article_group;
	}

	public ArticleVO(int article_number, String article_user,
			String article_board, int article_pid, String article_title,
			String article_content, int article_group) {
		this.article_number = article_number;
		this.article_user = article_user;
		this.article_board = article_board;
		this.article_pid = article_pid;
		this.article_title = article_title;
		this.article_content = article_content;
		this.article_group = article_group;
	}

	public int getArticle_number() {
		return article_number;
	}

	public void setArticle_number(int article_number) {
		this.article_number = article_number;
	}

	public String getArticle_user() {
		return article_user;
	}

	public void setArticle_user(String article_user) {
		this.article_user = article_user;
	}

	public String getArticle_board() {
		return article_board;
	}

	public void setArticle_board(String article_board) {
		this.article_board = article_board;
	}

	public int getArticle_pid() {
		return article_pid;
	}

	public void setArticle_pid(int article_pid) {
		this.article_pid = article_pid;
	}

	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	public Date getArticle_date() {
		return article_date;
	}
	//${list.Article_dateStr}
	public String getArticle_dateStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.article_date);
	}

	public void setArticle_date(Date article_date) {
		this.article_date = article_date;
	}

	public String getArticle_use() {
		return article_use;
	}

	public void setArticle_use(String article_use) {
		this.article_use = article_use;
	}
	
	public int getArticle_group() {
		return article_group;
	}

	public void setArticle_group(int article_group) {
		this.article_group = article_group;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}
	
	
}
