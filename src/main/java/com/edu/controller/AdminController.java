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
	public String selectMember(PageVO pageVO) throws Exception {
		//jsp의 검색버튼 클릭시 search_type, search_keyword 내용이 PageVO클래스에 Set됩니다.
		
		//역방향 검색한 결과를 jsp 보내줍니다. (아래)
		if(pageVO.getPage() == null) {
			pageVO.setPage(1);
		}
		//pageVO의 calcPage메서드를 실행하려면, 필수 변수값입력(아래)
		pageVO.setQueryPerPageNum(10);
		pageVO.setPerPageNum(10);//하단 UI에 보여지는 페이지 번호 개수.
		pageVO.setTotalCount(memberService.countMember(pageVO));//검색된 결과의 전체 카운트값.
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		logger.info("디버그" + pageVO.toString());//지금까지 jsp->컨트롤러 일방향 자료이동.
		return "admin/member/member_list";//jsp파일 상대경로
	}
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) throws Exception {//에러 발생시 Exception을 스프링으로 보내게 됩니다.
		
		//아래 상대경로에서 /WEB-INF/views/ 폴더가 최상위 루트
		//아래 상대경로 home.jsp에서 .jsp(생략suffix접미어) 입니다.
		return "admin/home";//리턴 경로=접근경로는 반드시 상대경로로 표시
	}
}

