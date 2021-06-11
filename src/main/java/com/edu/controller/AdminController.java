package com.edu.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;
/**
 *  이 클래스는 Admin관리자단을 접근하는 클래스.
 *  변수 Object를 만들어서 jsp로 전송 <-> jsp에서 값을 받아서 Object로 처리
 * @author ana27
 * 
 *
 */
@Controller

public class AdminController {
	//컨트롤러 수정하면 자동로딩(auto컴파일)
	//디버그용 로그객체 생성
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	//이 메서드는 회원목록을 출력하는 jsp와 매핑이 됩니다.
	@Inject
	private IF_MemberService memberService;
	
	@RequestMapping(value="/admin/member/member_list", method = RequestMethod.GET)
	public String selectMember(PageVO pageVO, Model model) throws Exception {
		/*
		이 메서드는 2개 객체 생성하는 로직이 필요. 결과를 JSP로 보내는 기능을수행
		1객체 : memberList객체를 생성해서 model을 통해서 jsp로 전송
		2객체 : pageVO객체를 생성해서 model을 통해서 jsp로 전송
		2번 객체부터 로직이 필요 = memberList구하는 쿼리변수가 만들어지기 때문에 이것부터 구현
		 * 
		*/
		if(pageVO.getPage() == null) {//jsp에서 전송값이 없을떄만 초기값 입력
			pageVO.setPage(1);//초기값 1페이지 입력
		}
		//학습포인트: calcPage()로직 < 변수(객체)값의 이동확인(코딩)
		pageVO.setQueryPerPageNum(10);//memberList객체 + endPage쿼리에 필요
		pageVO.setPerPageNum(5);//startPage 구할때 필요.-UI하단 페이지개수
		//위 2개 변수값을 이용해서 아래 setTotalCount메서드에서 calcPage()호출됨.
		pageVO.setTotalCount(memberService.countMember(pageVO));
		//calcPage 실행되면 , prev, next변수 값이 입력됩니다.
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		//위 setPerPageNum 20이면 next가 false(비활성화), 5이면 next가 true(활성화)
		//100명의 회원에서는 하단 페이징개수가1...10까지면 next false가 정상입니다.
		logger.info("디버그" + pageVO.toString());//지금까지 jsp->컨트롤러 일방향 자료이동.
		//컨트롤러 에서 jsp로 자료를 Model에 담아서 보내게 됩니다.
		model.addAttribute("listMember", listMember);
		model.addAttribute
		return "admin/member/member_list";//jsp파일 상대경로
		
	}
	//URL요청 경로는 @RequestMapping 반드시 절대경로로 표시
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) throws Exception {//에러 발생시 Exception을 스프링으로 보내게 됩니다.
		
		//아래 상대경로에서 /WEB-INF/views/ 폴더가 최상위 루트
		//아래 상대경로 home.jsp에서 .jsp(생략suffix접미어) 입니다.
		return "admin/home";//리턴 경로=접근경로는 반드시 상대경로로 표시
	}
}

