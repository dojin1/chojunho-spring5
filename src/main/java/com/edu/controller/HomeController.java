package com.edu.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//외부 라이브러리(모듈) 사용 = import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.service.IF_BoardService;
import com.edu.service.IF_MemberService;
import com.edu.util.CommonUtil;
import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 MVC웹프로젝트를 최초로 생성시 자동으로 생성되는 클래스
 * 웹브라우저의 요청사항을 view단(jsp)에 연결시켜주는 클래스 @Controller.
 * 스프링에서 관리하는 클래스를 스프링빈(콩) = 스프링빈을 명시 @Controller 애노테이션
 * Beans(콩들) 그래프로 이 프로젝트의 규모를 확인가능.
 * 스프링이 관리하는 클래스=스프링빈은 파일아이콘에 S가 붙습니다. 
 */

@Controller
public class HomeController {
	//스프링빈(클래스) 에서는 로거로 디버그를 합니다.=로거객체를 만듭니다.
	// 로그중 slf4j(Spring Log For Java)
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * 사용자요청(웹브라우저)을 받아서=@RequestMapping인테페이스를 사용해서 메서드명을 스프링이 구현합니다.
	 *  ,router(루트rootX)
	 * return 값으로 view(jsp)를 선택해서 작업한 결과를 변수로 담아서 화면에 전송 후 결과를 표시(렌더링) 합니다.
	 * 폼(자료)전송시 post(자료숨김), get(자료노출-URL쿼리스트링?있는자료전송)
	 */
	//이제부터 일반적인 개발방시 VO->쿼리->DAO->Service(관리자단에서 여기까지끝)
	//관리자단에서 작성한 Service 사용자단에서 그대로 이용, 컨트롤러부터 분리해작업->jsp
	@Inject
	private IF_MemberService memberService;
	@Autowired
	private IF_BoardService boardService;
	@Inject
	private CommonUtil commonUtil;
	//MVC구조 기본서식
	//@RequestMapping요청URL값
	//public 뷰단jsp파일명리턴형식 콜백함수(자동실행)
	//return "파일명";
	
	//게시물 상세보기 호출 GET 추가
	@RequestMapping(value="/home/board/board_view",method=RequestMethod.GET)
	public String board_view(Model model,@RequestParam("bno")Integer bno,@ModelAttribute("pageVO")PageVO pageVO) throws Exception {
		//첨부파일내용 가져오기
		List<AttachVO> listAttachVO = boardService.readAttach(bno);
		//첨부파일이 있다면 save_file_names, real_file_names 2개를 만듬
		String[] save_file_names = new String[listAttachVO.size()];
		String[] real_file_names = new String[listAttachVO.size()];
		int index = 0;
		for(AttachVO file:listAttachVO) {//세로데이터를 가로데이터로 변경처리
			save_file_names[index] = file.getSave_file_name();
			real_file_names[index] = file.getReal_file_name();
			index = index + 1;
		}
		BoardVO boardVO = boardService.readBoard(bno);//1개 레코드 입력됨.
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		//dB테이블 데이터 가져오기
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("checkImgArray", commonUtil.getCheckImgArray());
		return "home/board/board_view";//.jsp생략
	}
	//게시물 등록 처리 호출 POST 추가
	@RequestMapping(value="/home/board/board_insert",method=RequestMethod.POST)
	public String board_insert(RedirectAttributes rdat, @RequestParam("file")MultipartFile[] files,BoardVO boardVO) throws Exception {
		//첨부파일 처리
		String[] save_file_names = new String[files.length];
		String[] real_file_names = new String[files.length];
		int index = 0;//위 String[]배열의 인덱스 값으로 사용할 변수선언
		for(MultipartFile file:files) {
			//첨부파일이 존재하면 실행조건
			if(file.getOriginalFilename()!="") {
				real_file_names[index] = file.getOriginalFilename();
				save_file_names[index] = commonUtil.fileUpload(file);//UUID를 반환
			}
			index = index + 1;
		}
		//Attach테이블에 insert할 첨부파일 가상변수값을 입력
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		//타이틀,content 내용 시큐어코딩 처리(아래4줄)
		String rawTitle = boardVO.getTitle();
		String rawContent = boardVO.getContent();
		boardVO.setTitle(commonUtil.unScript(rawTitle));
		boardVO.setContent(commonUtil.unScript(rawContent));
		//DB테이블 처리
		boardService.insertBoard(boardVO);
		rdat.addFlashAttribute("msg", "게시물 등록");//출력:게시물 등록 이(가) 성공~
		return "redirect:/home/board/board_list";
	}
	//게시물 등록 폼 호출 GET 추가
	@RequestMapping(value="/home/board/board_insert_form",method=RequestMethod.GET)
	public String board_insert_form() throws Exception {
		
		return "home/board/board_insert";//뷰단.jsp생략
	}
	//게시물 리스트 페이지 호출 GET 추가
	@RequestMapping(value="/home/board/board_list",method=RequestMethod.GET)
	public String board_list(@ModelAttribute("pageVO") PageVO pageVO, Model model) throws Exception {
		if(pageVO.getPage() == null) {
			pageVO.setPage(1);
		}
		//pageVO의 2개변수값을 필수로 입력해야지만 페이징처리가 가능
		pageVO.setQueryPerPageNum(5);
		pageVO.setPerPageNum(5);
		int totalCount = boardService.countBoard(pageVO);
		pageVO.setTotalCount(totalCount);//여기에서 startPage,endPage,prev,next변수값이 발생됨
		List<BoardVO> boardList = boardService.selectBoard(pageVO);
		model.addAttribute("boardList", boardList);
		return "home/board/board_list";//.jps생략
	}
	//404파일 에러 처리 GET 호출 추가
	@RequestMapping(value="/home/error/error_404", method=RequestMethod.GET)
	public String error_404() {
		return "home/error/error_404";//.jsp생략
	}
	//회원가입 처리 호출 POST방식
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String join(MemberVO memberVO, RedirectAttributes rdat) throws Exception {
		//rawPassword암호를 스프링시큐리티로 인코딩 합니다.(아래)
		String rawPassword = memberVO.getUser_pw();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberVO.setUser_pw(passwordEncoder.encode(rawPassword));//암호화 실행.
		//사용자레벨은 UI단에서 보내는 값 무시하고 강제로 입력(해킹위험때문에)
		memberVO.setLevels("ROLE_USER");
		memberService.insertMember(memberVO);
		rdat.addFlashAttribute("msg", "회원가입");//회원가입 가(이) 성공했습니다. 출력
		return "redirect:/login_form";//페이지 리다이렉트로 이동
	}
	//회원가입폼 호출 Get방식
	@RequestMapping(value="/join_form",method=RequestMethod.GET)
	public String join_form() throws Exception {
		
		return "home/join";//.jsp생략
	}
	//마이페이지에서 회원탈퇴 POST방식 처리만.
	@RequestMapping(value="/member/mypage_leave", method=RequestMethod.POST)
	public String mypage_leave(MemberVO memberVO) throws Exception {
		memberService.updateMember(memberVO);
		//rdat.addFlashAttribute("msg", "회원탈퇴");//스프링내장된logout을 사용시X
		return "redirect:/logout";
	}
	//마이페이지 회원정보수정 POST방식. 처리 후 msg를 히든값으로 jsp로 전송합니다.
	@RequestMapping(value="/member/mypage", method=RequestMethod.POST)
	public String mypage(MemberVO memberVO, RedirectAttributes rdat) throws Exception {
		//암호를 인코딩 처리합니다. 조건, 암호를 변경하는 값이 있을때
		if(!memberVO.getUser_pw().isEmpty()) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String rawPassword = memberVO.getUser_pw();
			memberVO.setUser_pw(passwordEncoder.encode(rawPassword));
		}
		memberService.updateMember(memberVO);
		rdat.addFlashAttribute("msg", "회원정보수정");//회원정보수정 가(이) 성공했습니다. 출력용
		return "redirect:/member/mypage_form";
	}
	//마이페이지 폼호출 GET방식, 회원수정폼이기때문에 model담아서 변수값을 전송이 필요
	@RequestMapping(value="/member/mypage_form", method=RequestMethod.GET)
	public String mypage_form(HttpServletRequest request, Model model) throws Exception {
		//로그인 한 사용자 세션을 session_userid로 memberService의 readMember를 호출하면됨.
		//jsp에서 발생된 세션을 가져오려고 하기 때문에 HttpServletRequest객체가 사용됩니다.
		HttpSession session = request.getSession();//싱클톤 객체
		String user_id = (String) session.getAttribute("session_userid");
		//memberService에서 1개의 레코드를 가져옵니다. model담아서 jsp로 보냅니다.
		model.addAttribute("memberVO", memberService.readMember(user_id));
		return "home/member/mypage";//.jsp생략
	}
	//사용자단 로그인 폼호출 GET, 로그인POST처리는 컨트롤러에서 하지않고 스프링시큐리티로 처리
	@RequestMapping(value="/login_form", method=RequestMethod.GET)
	public String login_form() throws Exception {
		
		return "home/login";//.jsp생략
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage(Model model) { //콜백메스드,자동실행됨.
		String jspVar = "@서비스(DB)에서 처리한 결과";
		model.addAttribute("jspObject", jspVar);
		logger.info("디버그 스프링로고사용: " + jspVar);//System.out 대신 logger 객체를 사용
		//home.jsp파일로 자료를 전송(스프링)하는 기능= model인터페이스 객체(스프링이처리)에 내용만 채우면됨
		return "home/index";//확장자가 생략 .jsp가 생략되어 있음.
	}
	
}