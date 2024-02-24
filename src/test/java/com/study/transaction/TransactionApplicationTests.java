package com.study.transaction;

import com.study.transaction.board.dto.BoardDto;
import com.study.transaction.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@SpringBootTest
class TransactionApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlSessionFactory sessionFactory;

	@Autowired
	private BoardService boardService;

	@Test
	void contextLoads() {

	}

	/**
	 * 데이터 소스 테스트
	 * @throws SQLException
	 */
	@Test
	public void testToDatasource() throws SQLException {
		System.out.println("testToDatasource() : " + dataSource);
	}

	/**
	 * MyBatis Session Factory 테스트
	 */
	@Test
	public void testToSessionFactory(){
		System.out.println("testToSessionFactory() : " + sessionFactory);
	}

	/**
	 * 데이터 추가 Transaction 테스트
	 */
	@Test
	public void testToInsert(){
		BoardDto boardDto = new BoardDto();
		boardDto.setId((long)1);
		boardDto.setTitle("트랜젝션 테스트 제목1");
		boardDto.setContents("트랜젝션 테스트 내용1");
		boardService.save(boardDto);
	}

	/**
	 * 데이터 멀티추가 예외 발생시 Transaction 테스트
	 */
	@Test
	public void testToMultiInsert() throws Exception {
		boardService.multiSave();
	}
}
