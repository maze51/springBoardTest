package kr.or.ddit.login.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IuserService;

@Controller
public class LoginController {
	
	@Resource(name="userService")
	private IuserService userService;
	
	@Resource(name="boardService")
	private IboardService boardService; 
	
	/**
	 * 
	* Method : loginView
	* 작성자 : PC10
	* 변경이력 :
	* @param session
	* @return
	* Method 설명 : 사용자 로그인 화면 요청
	 */
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginView(HttpSession session) {
		if(session.getAttribute("USER_INFO")!=null)
			return "main";
		else
			return "login/login";
	}
	
	/**
	 * 
	* Method : loginProcess
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @param password
	* @param request
	* @param session
	* @return
	* Method 설명 : 사용자 로그인 요청 처리
	 */
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginProcess(String userId, String password, 
			HttpServletRequest request, HttpSession session) {
		
		String encryptPassword = KISA_SHA256.encrypt(password);
		
		UserVo userVo = userService.getUser(userId);
		
		if(userVo != null && encryptPassword.equals(userVo.getPass())) {
			List<BoardVO> boardList = boardService.showBoardList();
			
			request.getServletContext().setAttribute("BOARD_LIST", boardList);
			session.setAttribute("USER_INFO", userVo);
			
			return "main";
		}
		else
			return "login/login";
	}
	
	/**
	 * 
	* Method : logoutProcess
	* 작성자 : PC10
	* 변경이력 :
	* @param session
	* @return
	* Method 설명 : 사용자 로그아웃 요청 처리
	 */
	@RequestMapping(path = "/logout")
	public String logoutProcess(HttpSession session) {
		session.invalidate();
		
		return "login/login";
	}
}
