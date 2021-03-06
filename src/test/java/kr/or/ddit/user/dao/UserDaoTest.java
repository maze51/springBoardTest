package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVo;

public class UserDaoTest extends LogicTestEnv{
	
	@Resource(name="userDao")
	private IuserDao userDao;
	
	/**
	 * 
	* Method : getUserTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 특정 사용자 조회
	 */
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UserVo userVo = userDao.getUser(userId);

		/***Then***/
		assertNotNull(userVo);
		assertEquals("브라운", userVo.getName());
	}

}
