package com.edu.service;

import java.util.List;

import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 인터페이스는 DAO를 호출하는 서비스 입니다. 나머지 5개 메서드는 구현클래스에서 사용 합니다.
 * DAO클래스의 12개 메서드중 7개 사용
 * @author 조준호
 *
 */
public interface IF_BoardService {
	//첨부파일 CRUD 아래
	//DAO클래스에 있는 CRUD메서드 제외 => 대신 구현클래스에서 사용만 합니다.
	public List<AttachVO> readAttach(Integer bno) throws Exception;
	//게시물 상세보기시 조회수 증가시키는 메서드 제외 -> 대신 구현클래스에서 사용만 합니다.
	//페이징 없는 검색된(board_type포함된) 게시물 개수(아래)
	public int countBoard(PageVO pageVO) throws Exception;
	//기본 CRUD(아래)
	public void deleteBoard(int bno) throws Exception;
	public void updateBoard(BoardVO boardVO) throws Exception;
	public BoardVO readBoard(int bno) throws Exception;
	public void insertBoard(BoardVO boardVO) throws Exception;
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception;
}

