$(document).ready(function(){
    	//모바일용 메뉴 보이기/숨기기 액션 처리
	$('.openMOgnb').click(function(){
		// alert('메뉴보이기 액션클릭');
		// $('.header_cont').css('display','block');
		$('.header_cont').slideDown("slow");
		$('#header').addClass('on');
	});
	$('.closePop').click(function(){
		// $('.header_cont').css('display','none');
		$('.header_cont').slideUp("fast");
		$('#header').removeClass('on');
	});
    $('.to_top a').smoothScroll();
});

	// PC대메뉴 마우스 롤아웃 이벤트 만들기 (아래)
	//첫번째대메뉴(1) 서브메뉴(21) = .gnb_depth2_1 마우스 롤아웃 상태처리
	var isOver1 = false; //대메뉴1이 false 일때 서브메뉴(21)가 사라지는 조건
	var isOver21 = false;//서브메뉴1(21)이 false 일때 서브메뉴가 사라지는 조건
		// 1번 서브메뉴 숨기는 함수(아래)
		function goHide1() {
		if(!isOver1 && !isOver21) {//2개변수 모두 false 일때 내용구현
			//jQuery는 뒤에서 앞으로 해석(영어와 같음)
			$('.gnb_depth2_1').stop().fadeOut('fast');
			//대메뉴1번의 2차메뉴가 .gnb_depth2_1영역
		}
	}
	//두번째대메뉴(2) 서브메뉴(22) = .gnb_depth2_2 마우스 롤아웃 상태처리
	var isOver2 = false;//대메뉴2가 false  일때 서브메뉴(22)가 사라지는 조건
	var isOver22 = false;//서브메뉴2(22)가 false 일때 서브메뉴가 사라지는 조건
	// 2번 서브메뉴 숨기는 함수(아래)
	function goHide2() {
		if(!isOver2 && !isOver22) {
			$('.gnb_depth2_2').stop().fadeOut('fast');
			//대메뉴2번의 2차메뉴가 .gnb_depth2_2영역
		}
	}
	//PC대메뉴 마우스 롤오버 이벤트 만들기 (아래)
	$(document).ready(function(){
		// [대메뉴1번]의 마우스 오버 이벤트 => .gnb_depth2_1 서브메뉴 보이기
		$('.openAll1').mouseover(function(){
			// alert($('header').css('width'));
			// parseInt= 문자를 숫자로 변환하는 형변환 함수 1066(윈도우) = 1049(CSS)
			if(parseInt($('header').css('width'))>=1049) { //header태그의 가로크기가 1055 이상이라면
				$('.gnb_depth2_1').fadeIn('fast');
			}
			isOver1 = true;
		});
		// [대메뉴1번]의 마우스 아웃 이벤트 => .gnb_depth2_1 서브메뉴 숨기기
			$('.openAll1').mouseout(function(){
			isOver1 = false;
			setTimeout('goHide1()',200);//0.2마다 함수를 실행해 서브메뉴를 숨기기 처리
		});
		// (대메뉴1번)의 [서브메뉴21] 마우스 오버 했을때  =>'샘플홈페이지' 안에 '반응형홈페이지'
			$('.gnb_depth2_1').mouseover(function(){
				isOver21 = true; //fadeOut 작동을 하지 않겠다는 명시적인 변수값.
			});
		// (대메뉴1번)의 [서브메뉴21] 마우스 아웃 했을때	=>'샘플홈페이지' 안에 '반응형홈페이지'
			$('.gnb_depth2_1').mouseout(function(){
				isOver21 = false;//fadeOut 을 작동하겠다는 명시적인 변수값.
				setTimeout("goHide1()",200);
			});
	//======================================================================================	
		// [대메뉴2번]의 마우스 오버 이벤트 => .gnb_depth2_2 서브메뉴 보이기
		$('.openAll2').mouseover(function(){
			if(parseInt($('header').css('width'))>=1049) {
				$('.gnb_depth2_2').fadeIn('fast');
			}
			isOver2 = true;
		});
		// [대메뉴2번]의 마우스 아웃 이벤트 => .gnb_depth2_2 서브메뉴 숨기기
		$('.openAll2').mouseout(function(){
			isOver2 =false;
			setTimeout('goHide2()',200);//서브메뉴를 숨기기 처리
		});
		//(대메뉴2번)의 [서브메뉴22] 마우스 오버했을때 => '커뮤니티' 안에 '공지사항', '갤러리게시판'
		$('.gnb_depth2_2').mouseover(function(){
			isOver22 = true; //fadeOut 작동을 하지않겠다는 명시적인 변수값.
		});
		//(대메뉴2번)의 [서브메뉴22] 마우스 아웃했을때 => '커뮤니티' 안에 '공지사항', '갤러리게시판'
		$('.gnb_depth2_2').mouseout(function(){
			isOver22 = false; //fadeOut 을 작동하겠다는 명시적인 변수값.
			setTimeout("goHide2()",200);
		});
	});