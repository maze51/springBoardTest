package kr.or.ddit.board.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVo;

public class BoardControllerTest extends ControllerTestEnv{
	
	/**
	 * 
	* Method : showBoardManageTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시판 관리 화면 요청 테스트
	 */
	@Test
	public void showBoardManageTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/manageBoard")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("board/createBoard", viewName);
	}
	
	/**
	 * 
	* Method : createBoardTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 새 게시판 생성 테스트
	 */
	@Test
	public void createBoardTest() throws Exception {
		mockMvc.perform(post("/createBoard")
				.param("boardName", "JUnitTest게시판이름")
				.param("useSelect", "1")
				.sessionAttr("USER_INFO", new UserVo("cony")))
				.andExpect(view().name("board/createBoard"));
	}
	
	/**
	 * 
	* Method : modifyBoardTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시판 정보 수정 테스트
	 * @throws Exception 
	 */
	@Test
	public void modifyBoardTest() throws Exception {
		mockMvc.perform(post("/modifyBoard").param("boardName", "JUnit수정테스트")
				.param("useSelect", "1")
				.param("boardId", "b526"))
				.andExpect(view().name("board/createBoard"));
	}
	
	/**
	 * 
	* Method : showBoard
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시글 페이징 리스트 테스트
	 * @throws Exception 
	 */
	@Test
	public void showBoardTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/showBoard").param("page", "1").param("pageSize", "10")
						.param("boardId", "b3").param("boardName", "자유게시판")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		List<ArticleVO> aList = (List<ArticleVO>) mav.getModelMap().get("articleList");
		int pSize = (Integer) mav.getModelMap().get("paginationSize");
		
		/***Then***/
		assertEquals("board/showBoard", viewName);
		assertEquals(10, aList.size());
		assertEquals(3, pSize);
	}
	
	/**
	 * 
	* Method : showArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시글 읽기 테스트
	 * @throws Exception 
	 */
	@Test
	public void showArticleTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/showArticle").param("aNumber", "39")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		String viewName = mav.getViewName();
		ArticleVO article = (ArticleVO) mav.getModelMap().get("article");
		
		/***Then***/
		assertNotNull(article);
		assertEquals("article/showArticle", viewName);
	}
	
	/**
	* Method : showWriteArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시글 작성 화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void showWriteArticleTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/writeArticle")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("article/writeArticle", viewName);
	}
	
	/**
	 * 
	* Method : writeArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시글 작성 테스트
	 */
	@Test
	public void writeArticleTest() {
		
	}
	
	/**
	 * 
	* Method : showModifyArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시글 수정 화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void showModifyArticleTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/modifyArticle").param("arNum", "39")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("article/modifyArticle", viewName);
	}
	
	/**
	* Method : modifyArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시글 수정 테스트
	*/
	@Test
	public void modifyArticleTest() {
		
	}
	
	/**
	* Method : deleteArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시글 삭제 테스트
	 * @throws Exception 
	*/
	@Test
	public void deleteArticleTest() throws Exception {
		mockMvc.perform(get("/deleteArticle").param("articleNum", "51")
					.sessionAttr("boardId", "b11")).andExpect(view().name("redirect:/showBoard"));
	}
	
	// 첨부파일 다운로드, 삭제
	
	/**
	 * 
	* Method : showWriteReplyArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 답글 작성 화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void showWriteReplyArticleTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/writeReplyArticle").param("articleNumRA", "52")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("article/writeReplyArticle", viewName);
	}
	
	// 답글 작성
}
