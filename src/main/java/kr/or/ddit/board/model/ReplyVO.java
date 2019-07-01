package kr.or.ddit.board.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplyVO {
	private String reply_id;
	private String reply_user;
	private int reply_article;
	private String reply_content;
	private Date reply_date;
	private String reply_use;
	
	public ReplyVO() {
		
	}

	public ReplyVO(String reply_id, String reply_user, int reply_article,
			String reply_content, Date reply_date, String reply_use) {
		super();
		this.reply_id = reply_id;
		this.reply_user = reply_user;
		this.reply_article = reply_article;
		this.reply_content = reply_content;
		this.reply_date = reply_date;
		this.reply_use = reply_use;
	}

	public String getReply_id() {
		return reply_id;
	}

	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}

	public String getReply_user() {
		return reply_user;
	}

	public void setReply_user(String reply_user) {
		this.reply_user = reply_user;
	}

	public int getReply_article() {
		return reply_article;
	}

	public void setReply_article(int reply_article) {
		this.reply_article = reply_article;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Date getReply_date() {
		return reply_date;
	}
	
	public String getReply_dateStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.reply_date);
	}

	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}

	public String getReply_use() {
		return reply_use;
	}

	public void setReply_use(String reply_use) {
		this.reply_use = reply_use;
	}
	
	
}
