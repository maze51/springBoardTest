package kr.or.ddit.board.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.model.AppendVO;
import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class BoardServiceTest extends LogicTestEnv{
	
	@Resource(name="boardService")
	private IboardService boardService;
	
	/**
	* Method : showBoardListTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 현재 게시판 목록 출력 테스트
	*/
	@Test
	public void showBoardListTest() {
		/***Given***/

		/***When***/
		List<BoardVO> boardVo = boardService.showBoardList();
		
		/***Then***/
		assertEquals("취미게시판", boardVo.get(2).getBoard_name());
		
	}
	
	/**
	* Method : selectAllArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시판 아이디와 일치하는 전체 게시글 목록 출력 테스트
	*/
	@Test
	public void selectAllArticleTest(){
		/***Given***/
		String article_board = "b2";

		/***When***/
		List<ArticleVO> articleList = boardService.selectAllArticle(article_board);

		/***Then***/
		assertEquals(5, articleList.size());

	}
	
	/**
	* Method : articlePagingListTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시글 페이징 리스트 조회 테스트
	*/
	@Test
	public void articlePagingListTest(){
		/***Given***/
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("page", 1);
		searchMap.put("pageSize", 10);
		searchMap.put("boardId", "b3");

		/***When***/
		Map<String, Object> resultMap = boardService.articlePagingList(searchMap);
		List<ArticleVO> articleList = (List<ArticleVO>) resultMap.get("articleList");

		/***Then***/
		assertEquals(10, articleList.size());
	}
	
	/**
	* Method : createBoardTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 신규 게시판 생성 테스트
	*/
	@Test
	public void createBoardTest(){
		/***Given***/
		Map<String, Object> cMap = new HashMap<String, Object>();
		cMap.put("userId", "aTestId");
		cMap.put("boardName", "junitTestBoard");
		cMap.put("useSelect", "0");

		/***When***/
		int insertCnt = boardService.createBoard(cMap);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	* Method : modifyBoardTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시판 수정 테스트
	*/
	@Test
	public void modifyBoardTest(){
		/***Given***/
		Map<String, Object> mMap = new HashMap<String, Object>();
		mMap.put("boardId", "b526");
		mMap.put("boardName", "안노는게시판");
		mMap.put("useSelect", "0");

		/***When***/
		int updateCnt = boardService.modifyBoard(mMap);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	/**
	* Method : readArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 개별 게시글 확인 테스트
	*/
	@Test
	public void readArticleTest(){
		/***Given***/
		int articleNumber = 52;

		/***When***/
		Map<String, Object> resultMap = boardService.readArticle(articleNumber);
		ArticleVO articleVo = (ArticleVO) resultMap.get("article");

		/***Then***/
		assertEquals("본문이미지테스트", articleVo.getArticle_title());
		assertEquals("cony", articleVo.getArticle_user());
	}
	
	/**
	* Method : readReplyTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 댓글 확인 테스트
	*/
	@Test
	public void readReplyTest(){
		/***Given***/
		int articleNumber = 11;

		/***When***/
		List<ReplyVO> rList = boardService.readReply(articleNumber);

		/***Then***/
		assertEquals(4, rList.size());
	}
	
	/**
	* Method : deleteArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시글 삭제 테스트
	*/
	@Test
	public void deleteArticleTest(){
		/***Given***/
		int articleNumber = 27;

		/***When***/
		int updateCnt = boardService.deleteArticle(articleNumber);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	/**
	* Method : writeReplyTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 새 댓글 작성 테스트
	*/
	@Test
	public void writeReplyTest(){
		/***Given***/
		ReplyVO reply = new ReplyVO();
		reply.setReply_user("aTestId");
		reply.setReply_article(45);
		reply.setReply_content("JUnit테스트댓글달기");

		/***When***/
		int insertCnt = boardService.writeReply(reply);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	* Method : deleteReplyTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 댓글 삭제 테스트
	*/
	@Test
	public void deleteReplyTest(){
		/***Given***/
		String replyId = "r8";

		/***When***/
		int updateCnt = boardService.deleteReply(replyId);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	/**
	* Method : writeArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 새 게시글 작성 테스트
	*/
	@Test
	public void writeArticleTest(){
		/***Given***/
		ArticleVO articleVo = new ArticleVO(93, "aTestId", "b3", "JUnitTest제목", "내용내용", 93);
		
		/***When***/
		int insertCnt = boardService.writeArticle(articleVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	* Method : getNextArticleNumberTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 다음 게시글 번호 확인 테스트
	*/
	@Test
	public void getNextArticleNumberTest(){
		/***Given***/
		/***When***/
		int articleNumber = boardService.getNextArticleNumber();

		/***Then***/
		assertNotNull(articleNumber);
	}
	
	/**
	* Method : insertAppendTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 첨부파일 저장 테스트
	*/
	@Test
	public void insertAppendTest() {
		/***Given***/
		AppendVO append = new AppendVO();
		append.setAppend_article(39);
		append.setAppend_path("d:\boardFile\2019\06\7b0ec4dd-b170-4aa1-bb53-58e465d057db.png");
		append.setAppend_filename("brown.png");
		
		/***When***/
		int insertCnt = boardService.insertAppend(append);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	* Method : readAppendTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 첨부파일 확인 테스트
	*/
	@Test
	public void readAppendTest(){
		/***Given***/
		int appendArticle = 42;

		/***When***/
		List<AppendVO> list = boardService.readAppend(appendArticle);

		/***Then***/
		assertEquals(1, list.size());
	}
	
	/**
	* Method : modifyArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 특정 게시글 수정 테스트
	*/
	@Test
	public void modifyArticleTest(){
		/***Given***/
		ArticleVO articleVo = new ArticleVO();
		articleVo.setArticle_number(40);
		articleVo.setArticle_title("JUinitTest수정한글제목");
		articleVo.setArticle_content("111수정한글내용");

		/***When***/
		int updateCnt = boardService.modifyArticle(articleVo);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	/**
	* Method : getDownloadFileTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 다운로드할 파일 정보 확인 테스트
	*/
	@Test
	public void getDownloadFileTest(){
		/***Given***/
		String appendId = "a75";

		/***When***/
		AppendVO appendVo = boardService.getDownloadFile(appendId);

		/***Then***/
		assertEquals("brown.png", appendVo.getAppend_filename());
	}
	
	/**
	* Method : readBoardNameTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시판 아이디에 해당하는 게시판 이름 확인 테스트
	*/
	@Test
	public void readBoardNameTest(){
		/***Given***/
		String boardId = "b10";

		/***When***/
		String boardName = boardService.readBoardName(boardId);

		/***Then***/
		assertEquals("공지게시판", boardName);
	}
	
	/**
	* Method : deleteAppendTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 첨부파일 삭제 테스트
	*/
	@Test
	public void deleteAppendTest() {
		/***Given***/
		String appendId = "a10";
		
		/***When***/
		int cnt = boardService.deleteAppend(appendId);

		/***Then***/
		assertEquals(1, cnt);
	}
}
