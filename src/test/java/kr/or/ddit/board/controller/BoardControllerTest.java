package kr.or.ddit.board.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.ReplyVO;
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
	public void writeArticleTest() throws Exception{
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/ryan.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/writeArticle").file(file)
				.param("boardId", "b2")
				.param("title", "test제목")
				.param("content", "test내용")
				.sessionAttr("USER_INFO", new UserVo("cony")))
			.andExpect(view().name("redirect:/showArticle"));
		/***Then***/
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
	public void modifyArticleTest() throws Exception{
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/ryan.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/modifyArticle").file(file)
				.param("articleNumber", "53")
				.param("title", "test제목")
				.param("content", "test내용"))
			.andExpect(view().name("redirect:/showArticle"));
		/***Then***/
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
	
	/**
	 * 
	* Method : downloadFileTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 첨부파일 다운로드 테스트
	 */
	@Test
	public void downloadFileTest() throws Exception {
		mockMvc.perform(post("/downloadFile")
				.param("aId", "a6")
				).andExpect(status().isOk());
	}
	
	/**
	 * 
	* Method : deleteAppendTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 첨부파일 삭제 테스트
	 * @throws Exception 
	 */
	@Test
	public void deleteAppendTest() throws Exception {
		mockMvc.perform(get("/deleteAppend")
				.param("appendId", "a11")
				.param("aNumber", "60")
				).andExpect(view().name("redirect:/modifyArticle"));
	}
	
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
	
	/**
	 * 
	* Method : writeReplyArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 답글 작성 테스트
	 */
	@Test
	public void writeReplyArticleTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/ryan.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/writeReplyArticle").file(file)
				.param("boardId", "b2")
				.param("pId", "52")
				.param("title", "제목")
				.param("content", "내용내용")
				.param("groupId", "52")
				.sessionAttr("USER_INFO", new UserVo("cony")))
			.andExpect(view().name("redirect:/showArticle"));
		/***Then***/
	}
	
	/**
	 * 
	* Method : writeReplyTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 댓글 작성 테스트
	 */
	@Test
	public void writeReplyTest() throws Exception {
		mockMvc.perform(post("/writeReply")
				.param("reply", "댓글댓글")
				.param("aNum", "52")
				.sessionAttr("USER_INFO", new UserVo("cony")
				)).andExpect(view().name("redirect:/showArticle"));
	}
	
	/**
	 * 
	* Method : deleteReplyTest
	* 작성자 : PC10
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 댓글 삭제 테스트
	 */
	@Test
	public void deleteReplyTest() throws Exception {
		mockMvc.perform(get("/deleteReply")
				.param("replyId", "r37")
				.param("aNumber", "52")
				).andExpect(view().name("redirect:/showArticle"));
	}
}
