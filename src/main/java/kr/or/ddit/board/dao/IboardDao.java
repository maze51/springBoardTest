package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.AppendVO;
import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.ReplyVO;

public interface IboardDao {
	
	/**
	 * 
	* Method : showBoardList
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 현재 게시판 목록 출력
	 */
	List<BoardVO> showBoardList();
	
	/**
	 * 
	* Method : selectAllArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param articleBoard
	* @return
	* Method 설명 : 게시판 아이디와 일치하는 전체 게시글 목록 출력
	 */
	List<ArticleVO> selectAllArticle(String article_board);
	
	/**
	 * 
	* Method : boardCnt
	* 작성자 : PC10
	* 변경이력 :
	* @param boardId
	* @return
	* Method 설명 : 한 게시판의 전체 게시글수 조회
	 */
	int boardCnt(String boardId);
	
	/**
	 * 
	* Method : articlePagingList
	* 작성자 : PC10
	* 변경이력 :
	* @param searchMap
	* @return
	* Method 설명 : 게시글 페이징 리스트 조회
	 */
	List<ArticleVO> articlePagingList(Map<String, Object> searchMap);
	
	/**
	 * 
	* Method : createBoard
	* 작성자 : PC10
	* 변경이력 :
	* @param cMap
	* @return
	* Method 설명 : 신규 게시판 생성
	 */
	int createBoard(Map<String, Object> cMap);
	
	/**
	 * 
	* Method : modifyBoard
	* 작성자 : PC10
	* 변경이력 :
	* @param mMap
	* @return
	* Method 설명 : 게시판 수정
	 */
	int modifyBoard(Map<String, Object> mMap);
	
	/**
	 * 
	* Method : readArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param articleNumber
	* @return
	* Method 설명 : 개별 게시글 확인
	 */
	ArticleVO readArticle(int articleNumber);
	
	/**
	 * 
	* Method : readReply
	* 작성자 : PC10
	* 변경이력 :
	* @param articleNumber
	* @return
	* Method 설명 : 게시글에 해당하는 댓글 확인
	 */
	List<ReplyVO> readReply(int articleNumber);
	
	/**
	 * 
	* Method : deleteArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param articleNumber
	* @return
	* Method 설명 : 개별 게시글 삭제
	 */
	int deleteArticle(int articleNumber);
	
	/**
	 * 
	* Method : writeReply
	* 작성자 : PC10
	* 변경이력 :
	* @param reply
	* @return
	* Method 설명 : 새 댓글 작성
	 */
	int writeReply(ReplyVO reply);
	
	/**
	 * 
	* Method : deleteReply
	* 작성자 : PC10
	* 변경이력 :
	* @param replyId
	* @return
	* Method 설명 : 특정 댓글 삭제
	 */
	int deleteReply(String replyId);
	
	/**
	 * 
	* Method : writeArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param article
	* @return
	* Method 설명 : 새 게시글 작성
	 */
	int writeArticle(ArticleVO article);
	
	/**
	 * 
	* Method : getNextArticleNumber
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 다음 게시글 번호 확인
	 */
	int getNextArticleNumber();
	
	/**
	 * 
	* Method : insertAppend
	* 작성자 : PC10
	* 변경이력 :
	* @param append
	* @return
	* Method 설명 : 첨부파일 저장
	 */
	int insertAppend(AppendVO append);
	
	/**
	 * 
	* Method : readAppend
	* 작성자 : PC10
	* 변경이력 :
	* @param appendArticle
	* @return
	* Method 설명 : 첨부파일 확인
	 */
	List<AppendVO> readAppend(int appendArticle);
	
	/**
	 * 
	* Method : modifyArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param articleVo
	* @return
	* Method 설명 : 특정 게시글 수정
	 */
	int modifyArticle(ArticleVO articleVo);
	
	/**
	 * 
	* Method : getDownloadFile
	* 작성자 : PC10
	* 변경이력 :
	* @param appendId
	* @return
	* Method 설명 : 다운로드할 파일 정보 확인
	 */
	AppendVO getDownloadFile(String appendId);
	
	/**
	 * 
	* Method : readBoardName
	* 작성자 : PC10
	* 변경이력 :
	* @param boardId
	* @return
	* Method 설명 : 게시판 아이디에 해당하는 게시판 이름 확인
	 */
	String readBoardName(String boardId);
	
	/**
	 * 
	* Method : deleteAppend
	* 작성자 : PC10
	* 변경이력 :
	* @param appendId
	* @return
	* Method 설명 : 첨부파일 삭제
	 */
	int deleteAppend(String appendId);
}
