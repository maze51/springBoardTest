package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.model.AppendVO;
import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.PartUtil;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name = "boardService")
	private IboardService boardService;
	
	/**
	 * 
	* Method : showBoardManage
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 관리 화면 요청
	 */
	@RequestMapping(path = "/manageBoard", method = RequestMethod.GET)
	public String showBoardManage() {
		return "board/createBoard";
	}
	
	/**
	 * 
	* Method : createBoard
	* 작성자 : PC10
	* 변경이력 :
	* @param boardName
	* @param useSelect
	* @param session
	* @param request
	* @return
	* Method 설명 : 새 게시판 생성
	 */
	@RequestMapping(path = "/createBoard", method = RequestMethod.POST)
	public String createBoard(String boardName, String useSelect, HttpSession session,
						HttpServletRequest request) {
		UserVo userVo = (UserVo) session.getAttribute("USER_INFO");
		String userId = userVo.getUserId(); 
		
		Map<String, Object> cMap = new HashMap<String, Object>();
		
		cMap.put("userId", userId);
		cMap.put("boardName", boardName);
		cMap.put("useSelect", useSelect);
		
		int insertCnt = boardService.createBoard(cMap);
		List<BoardVO> boardList = boardService.showBoardList();
		
		request.getServletContext().setAttribute("BOARD_LIST", boardList);
		
		return "board/createBoard";
	}
	
	/**
	 * 
	* Method : modifyBoard
	* 작성자 : PC10
	* 변경이력 :
	* @param boardName
	* @param useSelect
	* @param boardId
	* @param request
	* @return
	* Method 설명 : 게시판 정보 수정
	 */
	@RequestMapping(path = "/modifyBoard", method = RequestMethod.POST)
	public String modifyBoard(String boardName, String useSelect, String boardId,
			HttpServletRequest request) {
		
		Map<String, Object> mMap = new HashMap<String, Object>();
		
		mMap.put("boardName", boardName);
		mMap.put("useSelect", useSelect);
		mMap.put("boardId", boardId);
		
		int modifyCnt = boardService.modifyBoard(mMap);
		List<BoardVO> boardList = boardService.showBoardList();
		
		request.getServletContext().setAttribute("BOARD_LIST", boardList);
		
		return "board/createBoard";
	}
	
	/**
	 * 
	* Method : showBoard
	* 작성자 : PC10
	* 변경이력 :
	* @param pageVo
	* @param boardId
	* @param boardName
	* @param model
	* @param session
	* @return
	* Method 설명 : 게시글 페이징 리스트
	 */
	@RequestMapping("/showBoard")
	public String showBoard(PageVo pageVo, String boardId, String boardName, Model model,
			HttpSession session) {
		
		boardId = boardId == null ? (String) session.getAttribute("boardId") : boardId;
		boardName = boardName == null ? (String) session.getAttribute("boardName") : boardName;
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("page", pageVo.getPage());
		searchMap.put("pageSize", pageVo.getPageSize());
		searchMap.put("boardId", boardId);
		
		Map<String, Object> resultMap = boardService.articlePagingList(searchMap);
		List<ArticleVO> articleList = (List<ArticleVO>) resultMap.get("articleList");
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		model.addAttribute("articleList", articleList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("PageVo", pageVo);
		
		session.setAttribute("boardId", boardId);
		session.setAttribute("boardName", boardName);
		return "board/showBoard";
	}
	
	/**
	 * 
	* Method : showArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param aNumber
	* @param model
	* @return
	* Method 설명 : 게시글 읽기
	 */
	@RequestMapping("/showArticle")
	public String showArticle(int aNumber, Model model) {
		Map<String, Object> resultMap = boardService.readArticle(aNumber);
		
		model.addAttribute("article", resultMap.get("article"));
		model.addAttribute("reply", resultMap.get("reply"));
		model.addAttribute("append", resultMap.get("append"));
		
		return "article/showArticle";
	}
	
	/**
	* Method : writeArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param boardId
	* @return
	* Method 설명 : 게시글 작성 화면 요청
	*/
	@RequestMapping(path = "/writeArticle", method = RequestMethod.GET)
	public String showWriteArticle(String boardId) {
		return "article/writeArticle";
	}
	
	/**
	 * 
	* Method : writeArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param title
	* @param content
	* @param session
	* @param redirectAttributes
	* @param mtfRequest
	* @return
	* Method 설명 : 게시글 작성
	 */
	@RequestMapping(path = "/writeArticle", method = RequestMethod.POST)
	public String writeArticle(String title, String content, HttpSession session,
								RedirectAttributes redirectAttributes, MultipartHttpServletRequest mtfRequest) {
		
		int articleNumber = boardService.getNextArticleNumber(); // 게시글 번호
		UserVo userVo = (UserVo) session.getAttribute("USER_INFO");
		String boardId = (String) session.getAttribute("boardId");
		String userId = userVo.getUserId(); // 작성자
		
		ArticleVO articleVo = new ArticleVO(articleNumber, userId, boardId, title, content, articleNumber);
		
		int insertCnt = boardService.writeArticle(articleVo);
		
		fileUpload(mtfRequest, articleNumber); // 파일처리
		
		redirectAttributes.addAttribute("aNumber", articleNumber);
		return "redirect:/showArticle";
	}
	
	/**
	* Method : modifyArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param arNum
	* @param model
	* @return
	* Method 설명 : 게시글 수정 화면 요청
	*/
	@RequestMapping(path = "/modifyArticle", method = RequestMethod.GET)
	public String showModifyArticle(int arNum, Model model) {
		
		Map<String, Object> resultMap = boardService.readArticle(arNum);
		
		model.addAttribute("article", (ArticleVO) resultMap.get("article"));
		List<AppendVO> appendList = (List<AppendVO>) resultMap.get("append"); // 게시글의 첨부파일 정보
		model.addAttribute("append", appendList);
		model.addAttribute("appendSize", appendList.size());
		
		return "article/modifyArticle";
	}
	
	/**
	 * 
	* Method : modifyArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param articleNum
	* @param title
	* @param content
	* @param redirectAttributes
	* @param mtfRequest
	* @return
	* Method 설명 : 게시글 수정
	 */
	@RequestMapping(path = "/modifyArticle", method = RequestMethod.POST)
	public String modifyArticle(int articleNumber, String title, String content,
					RedirectAttributes redirectAttributes, MultipartHttpServletRequest mtfRequest) {
		
		ArticleVO articleVo = new ArticleVO(articleNumber, title, content);
		
		int modifyCnt = boardService.modifyArticle(articleVo);
		
		fileUpload(mtfRequest, articleNumber); // 파일처리
		redirectAttributes.addAttribute("aNumber", articleNumber);
		return "redirect:/showArticle";
	}
	
	/**
	* Method : deleteArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param articleNum
	* @return
	* Method 설명 : 게시글 삭제
	*/
	@RequestMapping("/deleteArticle")
	public String deleteArticle(int articleNum) {
		int deleteCnt = boardService.deleteArticle(articleNum);
		return "redirect:/showBoard";
	}
	
	/**
	* Method : downloadFile
	* 작성자 : PC10
	* 변경이력 :
	* @param aId
	* @param request
	* @param response
	* @throws IOException
	* Method 설명 : 첨부파일 다운로드
	*/
	@RequestMapping(path = "/downloadFile", method = RequestMethod.POST)
	public void downloadFile(String aId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		AppendVO appendVo = boardService.getDownloadFile(aId);
		
		String savePath = appendVo.getAppend_path();
		String orgFileName = appendVo.getAppend_filename();
		
		InputStream in = null;
	    OutputStream os = null;
	    File file = null;
	    boolean skip = false;
	    String client = "";
	    
	    try {
	    	file = new File(savePath);
	    	in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			skip = true;
		}
	    
	    client = request.getHeader("User-Agent");
	    
	    response.reset() ;
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Description", "JSP Generated Data");
        
        if(!skip){
        	orgFileName = new String(orgFileName.getBytes("utf-8"),"iso-8859-1");
 
            response.setHeader("Content-Disposition", "attachment; filename=\"" + orgFileName + "\"");
            response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
            response.setHeader("Content-Length", ""+file.length());
       
            os = response.getOutputStream();
            byte b[] = new byte[(int)file.length()];
            int leng = 0;
             
            while( (leng = in.read(b)) > 0 ){
                os.write(b,0,leng);
            }
        }else{
            response.setContentType("text/html;charset=UTF-8");
            logger.debug("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
        }
        in.close();
        os.close();
	}
	
	/**
	 * 
	* Method : deleteAppend
	* 작성자 : PC10
	* 변경이력 :
	* @param appendId
	* @param aNumber
	* @param redirectAttributes
	* @return
	* Method 설명 : 첨부파일 삭제
	 */
	@RequestMapping("/deleteAppend")
	public String deleteAppend(String appendId, int aNumber, RedirectAttributes redirectAttributes) {
		
		int deleteCnt = boardService.deleteAppend(appendId);
		redirectAttributes.addAttribute("arNum", aNumber);
		return "redirect:/modifyArticle";
	}
	
	
	
	/**
	* Method : showWriteReplyArticle
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 답글 작성 화면 요청
	*/
	@RequestMapping(path = "/writeReplyArticle", method = RequestMethod.GET)
	public String showWriteReplyArticle() {
		return "article/writeReplyArticle";
	}
	
	/**
	 * 
	* Method : writeReplyArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param groupId
	* @param pId
	* @param title
	* @param content
	* @param session
	* @param mtfRequest
	* @param redirectAttributes
	* @return
	* Method 설명 : 답글 작성
	 */
	@RequestMapping(path = "/writeReplyArticle", method = RequestMethod.POST)
	public String writeReplyArticle(int groupId, int pId, String title, String content, HttpSession session,
							MultipartHttpServletRequest mtfRequest, RedirectAttributes redirectAttributes) {
		int articleNumber = boardService.getNextArticleNumber();
		UserVo userVo = (UserVo) session.getAttribute("USER_INFO");
		String boardId = (String) session.getAttribute("boardId");
		String userId = userVo.getUserId(); // 작성자
		
		ArticleVO articleVo = new ArticleVO(articleNumber, userId, boardId, pId, title, content, groupId);
		
		int insertCnt = boardService.writeArticle(articleVo);
		
		fileUpload(mtfRequest, articleNumber); // 파일처리
		redirectAttributes.addAttribute("aNumber", articleNumber);
		return "redirect:/showArticle";
	}
	
	/**
	 * 
	* Method : writeReply
	* 작성자 : PC10
	* 변경이력 :
	* @param reply
	* @param aNum
	* @param session
	* @param redirectAttributes
	* @return
	* Method 설명 : 댓글 작성
	 */
	@RequestMapping(path = "/writeReply", method = RequestMethod.POST)
	public String writeReply(String reply, int aNum, HttpSession session,
							RedirectAttributes redirectAttributes) {
		UserVo userVo = (UserVo) session.getAttribute("USER_INFO");
		String userId = userVo.getUserId();
		
		ReplyVO replyVo = new ReplyVO(userId, aNum, reply);
		
		int insertCnt = boardService.writeReply(replyVo);
		
		if(insertCnt==1) {
			redirectAttributes.addAttribute("aNumber", aNum);
			return "redirect:/showArticle";
		}
		return "redirect:/showArticle";
	}
	
	/**
	 * 
	* Method : deleteReply
	* 작성자 : PC10
	* 변경이력 :
	* @param replyId
	* @param aNumber
	* @param redirectAttributes
	* @return
	* Method 설명 : 댓글 삭제
	 */
	@RequestMapping("/deleteReply")
	public String deleteReply(String replyId, int aNumber, RedirectAttributes redirectAttributes) {
		
		int deleteCnt = boardService.deleteReply(replyId);
		
		if(deleteCnt==1) {
			redirectAttributes.addAttribute("aNumber", aNumber);
			return "redirect:/showArticle";
		}
		return "redirect:/showArticle";
	}
	
	/**
	* Method : fileUpload
	* 작성자 : PC10
	* 변경이력 :
	* @param mtfRequest
	* @param articleNumber
	* Method 설명 : 파일 업로드 method
	*/
	private void fileUpload(MultipartHttpServletRequest mtfRequest, int articleNumber) {
		AppendVO append = new AppendVO();
		List<MultipartFile> fileList = mtfRequest.getFiles("profile");
		
		if(fileList.get(0).getSize() != 0) {
			for (MultipartFile mf : fileList) {
				String path = PartUtil.getUploadPath();
				String ext = PartUtil.getExt(mf.getOriginalFilename());
				String fileName = UUID.randomUUID().toString();
				String filePath = path + File.separator + fileName + ext;
				
				File uploadFile = new File(filePath);
				try {
					mf.transferTo(uploadFile);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				
				append.setAppend_path(filePath);
				append.setAppend_filename(mf.getOriginalFilename());
				append.setAppend_article(articleNumber);
				
				boardService.insertAppend(append);
			}
		}
	}
}
