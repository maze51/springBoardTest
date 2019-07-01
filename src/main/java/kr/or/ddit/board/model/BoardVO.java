package kr.or.ddit.board.model;

import java.util.Date;

public class BoardVO {
	private String board_id;
	private String board_user;
	private String board_name;
	private String board_use;
	private Date board_reg;
	
	public BoardVO() {
		
	}
	
	public BoardVO(String board_id, String board_user, String board_name,
			String board_use, Date board_reg) {
		super();
		this.board_id = board_id;
		this.board_user = board_user;
		this.board_name = board_name;
		this.board_use = board_use;
		this.board_reg = board_reg;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	public String getBoard_user() {
		return board_user;
	}

	public void setBoard_user(String board_user) {
		this.board_user = board_user;
	}

	public String getBoard_name() {
		return board_name;
	}

	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}

	public String getBoard_use() {
		return board_use;
	}

	public void setBoard_use(String board_use) {
		this.board_use = board_use;
	}

	public Date getBoard_reg() {
		return board_reg;
	}

	public void setBoard_reg(Date board_reg) {
		this.board_reg = board_reg;
	}
	
	
	
}
