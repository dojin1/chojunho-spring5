package com.edu.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 이 클래스는 오라클과 연동해서 CRUD를 테스트 하는 클래스 입니다.
 * 과장(이사,팀장)급 사원이 JUnit CRUD까지 만들어서 일반사원에게 공개 + 회원관리
 * @author 조준호
 *
 */
//RunWith인터페이스 현재클래스가 JUnit실행 클래스라고 명시.
@RunWith(SpringJUnit4ClassRunner.class)
//경로에서 **(모든폴더명시),*(모든파일명을명시)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})

@WebAppConfiguration
public class DataSourceTest {
	//디버그용 로그 객체변수 생성.
	private Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
			
	@Test		
	public void junittest() {
		//logger의 장점: 조건에 따라서 출력을 조절할 수 있음.
		logger.debug("Junit테스트 시작 입니다.");
	}
}
