package kr.or.ddit.user.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;

@Repository
public class UserDao implements IuserDao{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 
	* Method : getUser
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 특정 사용자 조회
	 */
	@Override
	public UserVo getUser(String userId) {
		return sqlSession.selectOne("user.getUser", userId);
	}

}
