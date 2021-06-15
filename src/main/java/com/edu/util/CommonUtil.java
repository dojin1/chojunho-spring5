package com.edu.util;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;

/**
 * 이 클래스는 이 프로젝트에서 공통으로 사용하는 유틸리티기능을 모아놓은 클래스 입니다.
 * @author 조준호
 * 컨트롤러 기능 @Controller는 jsp와 바인딩명시.
 * 컴포넌트 @Component는 MVC가 아닌 기능들을 모아놓은 스프링빈 메서드.
 */
@Controller
public class CommonUtil {
	//멤버변수생성(아래)
	private Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	@Inject
	private IF_MemberService memberService;//객체준비
	
	//RestAPI서버 맛보기 ID중복체크 (제대로 만들면 @RestController 사용)
	@RequestMapping(value="/id_check", method=RequestMethod.GET)
	@ResponseBody //반환받은 값의 헤더값을 제외하고 내용(body)만 반환하겠다는 명시.
	public String id_check(@RequestParam("user_id")String user_id) throws Exception {
		//중복아이디를 체크로지(아래)
		String memberCnt = "0";//중복ID가 없을때, 기본값 0
		MemberVO memberVO =memberService.readMember(user_id);
		if(memberVO != null) { //!주의 중복아이디가 존재하면 {}내용 실행.
			//isEmpty는 null체크+ ""공백체크 모두 포함
			memberCnt = "1";
		}
		return memberCnt;// 0.jsp 이렇게 작동하지 않습니다. 이유는 @ResponseBody때문이고 RestAPI는 값만 반환
	}
}
