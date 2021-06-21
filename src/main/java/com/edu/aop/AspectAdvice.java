package com.edu.aop;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.edu.service.IF_BoardTypeService;
import com.edu.vo.BoardTypeVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 AOP기능중  @Aspect 와 @ControllerAdvice 로 구현됩니다.
 * @author 조준호
 *
 */
@Aspect
@ControllerAdvice
public class AspectAdvice {
	@Inject
	private IF_BoardTypeService boardTypeService;
	@ModelAttribute("listBoardTypeVO")
	
	//나중에 게시물관리 기능 만들때 @Aspect로 AOP기능 추가 =>목적:  board_type값을 항상 가져 가도록 처리(세션)
	//세션? 서버-PC구조상에서 클라이언트가 서버에 접속할때 서버에 발생되는 정보를 세션이라고 함 (서버에 저장됨)
	//쿠키? 서버-PC 구조상에서 클라이언트가 서버에 접속할때 클라이언트에 발생되는 정보를 쿠키라고 함 (PC에 저장됨)
	//옛날에는 쿠키로 로그인 체크를 했음. ->보안상 PC에 로그인정보가 저장되기 때문에 위험(인터넷광고에만 사용)-> 세션으로변경
	//Aspect로 AOP를 구현할때는 포인트컷(Advice참견이 실행될 위치)이 필요합니다.
	//@Around=@Before+@After = @Around(포인트컷 전+후*(..)모든메서드)
	//@Around는 콜백함수 매개변수로 조인포인트 객체(포인트컷에서 실행되는 메서드) 를 필수로 받습니다.
	@Around("execution(* com.edu.controller.*Controller.*(..))")
	public Object sessionManager(ProceedingJoinPoint pjp) throws Throwable {
		//board_type변수값을 세션에 저정하려고 함. 클라이언트별 세션이 발생됨.
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		//일반적인 컨트롤러에서는 매개변수 HttpServletRequest를 사용가능함. 위처럼 복잡하게 구하지않음.
		//컨트롤러 클래스에서 매개변수로 받을값 (board_type) < pageVO
		PageVO pageVO = null;
		//조인포인트리스트의 객체들의 메서드 Arguments(매개변수)를 뽑아냄.
		for(Object object:pjp.getArgs()) {
			
		}
		return null;
	}
	
	//이 메서드는 컨트롤러의 메서드가 실행되기 전에 값을 생성해서 model객체에 담아 jsp로 데이터 전송
	//위 @ControllerAdvice 를 이용해서 컨트롤러의 모든 메서드가 실행되기전에 호출만되면 아래 메서드가 자동실행(콜백함수)
	public List<BoardTypeVO> listBoardTypeVO() throws Exception {
		
		return boardTypeService.selectBoardType();
	}
}
