package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.AppendVO;
import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.ReplyVO;

@Repository
public class BoardDao implements IboardDao {
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BoardVO> showBoardList() {
		return sqlSession.selectList("board.showBoardList");
	}

	@Override
	public List<ArticleVO> selectAllArticle(String article_board) {
		return sqlSession.selectList("board.selectAllArticle", article_board);
	}

	@Override
	public int boardCnt(String boardId) {
		return sqlSession.selectOne("board.boardCnt", boardId);
	}

	@Override
	public List<ArticleVO> articlePagingList(Map<String, Object> searchMap) {
		return sqlSession.selectList("board.articlePagingList", searchMap);
	}

	@Override
	public int createBoard(Map<String, Object> cMap) {
		return sqlSession.insert("board.createBoard", cMap);
	}

	@Override
	public int modifyBoard(Map<String, Object> mMap) {
		return sqlSession.update("board.modifyBoard", mMap);
	}

	@Override
	public ArticleVO readArticle(int articleNumber) {
		return sqlSession.selectOne("board.readArticle", articleNumber);
	}

	@Override
	public List<ReplyVO> readReply(int articleNumber) {
		return sqlSession.selectList("board.readReply", articleNumber);
	}

	@Override
	public int deleteArticle(int articleNumber) {
		return sqlSession.update("board.deleteArticle", articleNumber);
	}

	@Override
	public int writeReply(ReplyVO reply) {
		return sqlSession.insert("board.writeReply", reply);
	}

	@Override
	public int deleteReply(String replyId) {
		return sqlSession.update("board.deleteReply", replyId);
	}

	@Override
	public int writeArticle(ArticleVO article) {
		return sqlSession.insert("board.writeArticle", article);
	}

	@Override
	public int getNextArticleNumber() {
		return sqlSession.selectOne("board.getNextArticleNumber");
	}

	@Override
	public int insertAppend(AppendVO append) {
		return sqlSession.insert("board.insertAppend", append);
	}

	@Override
	public List<AppendVO> readAppend(int appendArticle) {
		return sqlSession.selectList("board.readAppend", appendArticle);
	}

	@Override
	public int modifyArticle(ArticleVO articleVo) {
		return sqlSession.update("board.modifyArticle", articleVo);
	}

	@Override
	public AppendVO getDownloadFile(String appendId) {
		return sqlSession.selectOne("board.getDownloadFile", appendId);
	}

	@Override
	public String readBoardName(String boardId) {
		return sqlSession.selectOne("board.readBoardName", boardId);
	}

	@Override
	public int deleteAppend(String appendId) {
		return sqlSession.delete("board.deleteAppend", appendId);
	}

}
