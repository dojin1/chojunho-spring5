package com.edu.aop;

import java.util.List;

import javax.inject.Inject;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.edu.service.IF_BoardTypeService;
import com.edu.vo.BoardTypeVO;

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
	//이 메서드는 컨트롤러의 메서드가 실행되기 전에 값을 생성해서 model객체에 담아 jsp로 데이터 전송
	//위 @ControllerAdvice 를 이용해서 컨트롤러의 모든 메서드가 실행되기전에 호출만되면 아래 메서드가 자동실행(콜백함수)
	public List<BoardTypeVO> listBoardTypeVO() throws Exception {
		
		return boardTypeService.selectBoardType();
	}
}
