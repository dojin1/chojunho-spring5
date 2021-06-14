package com.edu.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이클래스는 admin관리자단을 접근하는 클래스입니다.
 * 변수 Object를 만들어서 jsp로 전송 + jsp에서 폼값을 받아서 Object로 처리
 * @author 조준호
 *
 */
@Controller
public class AdminController {
   //컨트롤러 수정하면 자동로딩(Auto컴파일)
   //디버그용 로그객체 생성
   private Logger logger = LoggerFactory.getLogger(AdminController.class);
   //이 메소드는 회원목록을 출력하는 jsp와 매핑이 됩니다.
   @Inject
   private IF_MemberService memberService;
   //아래 경로는 수정처리를 호출=DB를 변경처리함
   @RequestMapping(value="admin/member/member_update", method=RequestMethod.POST)
   public String updateMember() throws Exception{
	   
	   return null;
   }
   //아래경로는 수정폼을 호출=화면에 렌더링만 출력
   @RequestMapping(value="/admin/member/member_update_form", method=RequestMethod.POST)
   public String updateMemberForm(MemberVO memberVO, Model model,@ModelAttribute("pageVO")PageVO pageVO) throws Exception {
	   //사용자 1명의 레코드를 가져오는 멤버서비스를(쿼리)를 실행(아래)
	   MemberVO memberView = memberService.readMember(memberVO.getUser_id());
	   //사용자 1명의 레코드를 model에 담아서 + @ModelAttribute에담아서 jsp로 보냅니다.
	   model.addAttribute("memberVO", memberView);
	   return "admin/member/member_update";//상대경로
   }
   @RequestMapping(value="admin/member/member_delete", method=RequestMethod.POST)
   public String deleteMember(MemberVO memberVO) throws Exception {
	   logger.info("디버그: " + memberVO.toString());
	   //MemberVO memberVO 클래스형 변수: String user_id스트링형변수 와 같은방식.
	   String user_id = memberVO.getUser_id();
	   //이 메서드는 회원상세보기 페이지에서 삭제버튼을 클릭시 전송받은 memberVO값을 이용해서 삭제를 구현(아래)
	   memberService.deleteMember(user_id);//삭제쿼리가 실행됨.
	   //return "admin/member/member_list";//삭제후 이동할 jsp경로지정
	   //위 방식대로 새로고침하면, /admin/member/member_insert 계속 실행됩니다.
	   //게시판테러 상황을 방지하기 위해서, 쿼리를 작업 후 이동할때는 redirect(재접속)라는 명령을 사용합니다.
	   return "redirect:/admin/member/member_list";//단,redirect는 절대경로를 사용.
   }
   @RequestMapping(value="/admin/member/member_view", method=RequestMethod.GET)
   public String viewMemberForm(Model model, @RequestParam("user_id")String user_id, @ModelAttribute("pageVO")PageVO pageVO) throws Exception {
   //페이지 진입 시 받은 클래스 변수 값 PageVO pageVO
      /*
       * 이 메소드는 리스트페이지에서 상세보기로 이동할때 보여주는  1개 레코드값을 보여주는것을 구현.
       * JUnit에서 테스트했던 readMember 방식을 이용.
       * 다른점은 JUnit에서는 식별자 ID를 상제로 지정했지만 이 메서드에서는 @RequestParam인터페이스를 이용해서 식별자 값을 받음
       */
      
      //위 출력값 memberVO 의 1개의 레코드를 model을 이용해서 member_view.jsp로 보냅니다.(아래)
      model.addAttribute("memberVO",memberService.readMember(user_id));
   //아래 페이지 반환시(렌더링) @ModelAttribute("pageVO")에 의해서 pageVO(소문자)로 jps에 보냄.
      return "admin/member/member_view"; //상대경로 폴더 파일 위치
   }
   
   @RequestMapping(value="/admin/member/member_list", method=RequestMethod.GET)
   public String selectMember(@ModelAttribute("pageVO")PageVO pageVO, Model model) throws Exception {
      /*
        이 메소드는 2개의 객체를 생성해서 jsp로 보내줘야합니다.
       1객체: memberList를 생성, model 통해서 jsp로 전송
       2객체: pageVO객체(prev,next,startPage,endPage)를 생성, model 통해서 jsp로 전송
       2번객체부터 로직이 필요: 2번객체에서 memberList를 구하는 쿼리변수가 만들어지기 때문에.
       */
      if(pageVO.getPage() == null) { //jsp에서 전송값이 없을때만 초기값 강제로 입력.
         pageVO.setPage(1);
      }
      //pageVO의 clacPage()로직 < 변수(객체)값의 이동 확인
      pageVO.setQueryPerPageNum(5); //memberList쿼리에 필요.
      pageVO.setPerPageNum(5); //startPage구할때
      //위 두 변수가 있어야 totalCount를 구할 수 있음.
      //위 두 변수값을 이용해서 아래 setTotalCount메소드에서 calsPage() 호출.
      pageVO.setTotalCount(memberService.countMember(pageVO)); //검색되든 안되든 결과의 전체카운트값.
      //calcPage() 실행되면 prev,next 변수값이 입력됩니다.
      List<MemberVO> listMember = memberService.selectMember(pageVO);
      //위 setPerPageNum이 20이면 next가 false, 5면 true.
      //100명의 회원에서는 하단 페이지번호가 10까지 일 때 nest가 false인게 정상.
      logger.info("디버그"+pageVO.toString()); //지금까지 jsp -> 컨트롤러 일방향 자료이동.
      model.addAttribute("listMember",listMember);
      //model.addAttribute("pageVO", pageVO);
      return "admin/member/member_list"; //jsp파일 상대경로
   }
   //URL요청 경로=리퀘스트맵핑 는 반드시 *절대경로*로 표시.
   @RequestMapping(value="/admin", method = RequestMethod.GET)
   public String admin(Model model) throws Exception { //에러 발생시 Exception클래스의 정보를 스프링으로 보내게 됩니다.
      
      //아래 상대경로에서 /WEB-INF/views/폴더가 루트(최상위)입니다. prefix(접두어)처리 되어있어서 생략가능.
      //아래 상대경로 home.jsp에서 .jsp는 suffix(접미어)처리되어있어서 생략가능. 
      return "admin/home"; //return 경로=접근경로 는 반드시 *상대경로*로 표시.
   }
}