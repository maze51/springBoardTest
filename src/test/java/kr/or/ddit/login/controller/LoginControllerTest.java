package kr.or.ddit.login.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVo;

public class LoginControllerTest extends ControllerTestEnv{
	
	/**
	 * 
	* Method : loginViewWithoutLoginedTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 접속하지 않은 상황에서 loginView 요청 테스트
	 */
	@Test
	public void loginViewWithoutLoginedTest() throws Exception{
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("login/login", viewName);
	}
	
	/**
	 * 
	* Method : loginViewLoginedTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 접속한 상황에서 loginView 요청 테스트
	 */
	@Test
	public void loginViewLoginedTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login").sessionAttr("USER_INFO", new UserVo()))
								.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("main", viewName);
	}
	
	/**
	 * 
	* Method : loginProcessSuccessTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 요청 처리 성공 테스트
	 */
	@Test
	public void loginProcessSuccessTest() throws Exception {
		/***Given***/
		String userId = "cony";
		String password = "cony1234";

		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login")
				.param("userId", userId).param("password", password))
				.andReturn();

		ModelAndView mav = mvcResult.getModelAndView();
		HttpSession session = mvcResult.getRequest().getSession();
		
		String viewName = mav.getViewName();
		UserVo userVo = (UserVo) session.getAttribute("USER_INFO");
		List<BoardVO> boardList = (List<BoardVO>) mvcResult.getRequest().getServletContext().getAttribute("BOARD_LIST");
		
		/***Then***/
		assertNotNull(userVo);
		assertNotNull(boardList);
	}
	
	/**
	 * 
	* Method : loginProcessFailTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 요청 실패 테스트
	 */
	@Test
	public void loginProcessFailTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String password = "5678"; // 틀린 비밀번호

		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login")
									.param("userId", userId)
									.param("password", password))
								.andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("login/login", viewName);
	}
	
	/**
	 * 
	* Method : logoutTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그아웃 요청 테스트
	 */
	@Test
	public void logoutTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/logout")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("login/login", viewName);
	}

}
