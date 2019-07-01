package kr.or.ddit.board.dao;

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

public class BoardDaoTest extends LogicTestEnv{

	@Resource(name="boardDao")
	private IboardDao boardDao;
	
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
		List<BoardVO> boardList = boardDao.showBoardList();
		/***Then***/
		assertEquals(16, boardList.size());
	}
	
	/**
	* Method : selectAllArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시판 아이디와 일치하는 전체 게시글 목록 출력 테스트
	*/
	@Test
	public void selectAllArticleTest() {
		/***Given***/
		String article_board = "b11";

		/***When***/
		List<ArticleVO> articleList = boardDao.selectAllArticle(article_board);
		
		/***Then***/
		assertNotNull(articleList);
		assertEquals(5, articleList.size());
	}
	
	/**
	* Method : boardCntTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 한 게시판의 전체 게시글수 조회 테스트
	*/
	@Test
	public void boardCntTest() {
		/***Given***/
		String boardId = "b12";
		
		/***When***/
		int cnt = boardDao.boardCnt(boardId);
		
		/***Then***/
		assertEquals(5, cnt);
	}
	
	/**
	* Method : articlePagingListTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시글 페이징 리스트 조회 테스트
	*/
	@Test
	public void articlePagingListTest() {
		/***Given***/
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("page", 1);
		searchMap.put("pageSize", 10);
		searchMap.put("boardId", "b3");

		/***When***/
		List<ArticleVO> articleList = boardDao.articlePagingList(searchMap);

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
	public void createBoardTest() {
		/***Given***/
		Map<String, Object> cMap = new HashMap<String, Object>();
		cMap.put("userId", "sally");
		cMap.put("boardName", "JUnitTest신규게시판");
		cMap.put("useSelect", 1);
		
		/***When***/
		int insertCnt = boardDao.createBoard(cMap);
		
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
	public void modifyBoardTest() {
		/***Given***/
		Map<String, Object> mMap = new HashMap<String, Object>();
		mMap.put("boardName", "임시게시판JUnitTest");
		mMap.put("useSelect", 1);
		mMap.put("boardId", "b12");
		
		/***When***/
		int modifyCnt = boardDao.modifyBoard(mMap);
		
		/***Then***/
		assertEquals(1, modifyCnt);
	}
	
	/**
	* Method : readArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 개별 게시글 확인 테스트
	*/
	@Test
	public void readArticleTest() {
		/***Given***/
		int articleNumber = 6;

		/***When***/
		ArticleVO article = boardDao.readArticle(articleNumber); 

		/***Then***/
		assertNotNull(article);
		assertEquals("지워지나 볼까", article.getArticle_title());
	}
	
	/**
	* Method : readReplyTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시글의 댓글 확인 테스트
	*/
	@Test
	public void readReplyTest() {
		/***Given***/
		int articleNumber = 11;

		/***When***/
		List<ReplyVO> replyList = boardDao.readReply(articleNumber);
		
		/***Then***/
		assertNotNull(replyList);
		assertEquals(4, replyList.size());
		assertEquals("ryan", replyList.get(0).getReply_user());
	}
	
	/**
	* Method : deleteArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 개별 게시글 삭제 테스트
	*/
	@Test
	public void deleteArticleTest() {
		/***Given***/
		int articleNumber = 24;

		/***When***/
		int cnt = boardDao.deleteArticle(articleNumber);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	/**
	* Method : writeReplyTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 새 댓글 작성 테스트
	*/
	@Test
	public void writeReplyTest() {
		/***Given***/
		ReplyVO replyVo = new ReplyVO("ryan", 52, "JUnit테스트댓글내용입니다");

		/***When***/
		int cnt = boardDao.writeReply(replyVo);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	/**
	* Method : deleteReplyTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 특정 댓글 삭제 테스트
	*/
	@Test
	public void deleteReplyTest() {
		/***Given***/
		String replyId = "r9";

		/***When***/
		int cnt = boardDao.deleteReply(replyId);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	/**
	* Method : writeArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 새 게시글 작성 테스트
	*/
	@Test
	public void writeArticleTest() {
		/***Given***/
		ArticleVO articleVo = new ArticleVO(62, "moon", "b3", "제목", "내용내용", 62);

		/***When***/
		int insertCnt = boardDao.writeArticle(articleVo);
		
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
	public void getNextArticleNumberTest() {
		/***Given***/
		/***When***/
		int articleNumber = boardDao.getNextArticleNumber();

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
		int insertCnt = boardDao.insertAppend(append);
		
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
	public void readAppendTest() {
		/***Given***/
		int appendArticle = 43;
		/***When***/
		List<AppendVO> appendList = boardDao.readAppend(appendArticle);
		
		/***Then***/
		assertEquals(1, appendList.size());
		assertEquals("js-cookie-master.zip", appendList.get(0).getAppend_filename());
	}
	
	/**
	* Method : modifyArticleTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 특정 게시글 수정 테스트
	*/
	@Test
	public void modifyArticleTest() {
		/***Given***/
		ArticleVO articleVo = new ArticleVO();
		
		articleVo.setArticle_number(40);
		articleVo.setArticle_title("수정한글제목");
		articleVo.setArticle_content("수정한글내용");

		/***When***/
		int updateCnt = boardDao.modifyArticle(articleVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	/**
	* Method : getDownloadFileTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 다운로드할 파일 정보 확인
	*/
	@Test
	public void getDownloadFileTest() {
		/***Given***/
		String appendId = "a10";

		/***When***/
		AppendVO appendVo = boardDao.getDownloadFile(appendId);

		/***Then***/
		assertEquals("Tulips.jpg", appendVo.getAppend_filename());
	}
	
	/**
	* Method : readBoardNameTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 게시판 아이디에 해당하는 게시판 이름 확인 테스트
	*/
	@Test
	public void readBoardNameTest() {
		/***Given***/
		String boardId = "b3";

		/***When***/
		String boardName = boardDao.readBoardName(boardId);

		/***Then***/
		assertEquals("자유게시판", boardName);
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
		String appendId = "a73";

		/***When***/
		int cnt = boardDao.deleteAppend(appendId);

		/***Then***/
		assertEquals(1, cnt);
	}
}
