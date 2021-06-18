package com.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 게시물 CRUD를 구현하는 클래스 입니다.
 * @author 조준호
 *
 */
@Repository
public class BoardDAOImpl implements IF_BoardDAO {
	@Inject
	private SqlSession sqlSession;//sql세션 템플릿
	@Override
	public void deleteAttachAll(Integer bno) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행
		sqlSession.delete("boardMapper.deleteAttachAll", bno);
	}

	@Override
	public void deleteAttach(String save_file_name) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행
		sqlSession.delete("boardMapper.deleteAttach", save_file_name);
	}

	@Override
	public void updateAttach(AttachVO attachVO) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행 =첨부파일 ,주의)메서드명은 update지만 쿼리는 insert.
		sqlSession.insert("boardMapper.updateAttach", attachVO);
	}

	@Override
	public void insertAttach(AttachVO attachVO) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행 =첨부파일
		sqlSession.insert("boardMapper.insertAttach", attachVO);
	}

	@Override
	public List<AttachVO> readAttach(Integer bno) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행 =첨부파일
		return sqlSession.selectList("boardMapper.readAttach", bno);
	}

	@Override
	public void updateViewCount(Integer bno) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행
		sqlSession.update("boardMapper.updateViewCount", bno);
	}

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행
		return sqlSession.selectOne("boardMapper.countBoard", pageVO);
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행
		sqlSession.delete("boardMapper.deleteBoard", bno);
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행
		sqlSession.update("boardMapper.updateBoard", boardVO);
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행
		return sqlSession.selectOne("boardMapper.readBoard", bno);
	}

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행
		sqlSession.insert("boardMapper.insertBoard", boardVO);
	}

	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		// sqlSession 템플릿을 사용해서 매퍼쿼리 실행
		return sqlSession.selectList("boardMapper.selectBoard", "pageVO");
	}

}
