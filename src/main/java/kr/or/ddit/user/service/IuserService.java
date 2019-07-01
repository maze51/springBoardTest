package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVo;

public interface IuserService {
	
	/**
	 * 
	* Method : getUser
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 특정 사용자 조회
	 */
	UserVo getUser(String userId);
}
