package com.study.transaction.board.service.impl;

import com.study.transaction.board.dto.BoardDto;
import com.study.transaction.board.mapper.BoardMapper;
import com.study.transaction.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardMapper boardMapper;

  @Override
  @Transactional
  public void save(BoardDto boardDto) {
    try {
      boardMapper.insertBoard(boardDto);
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  @Transactional
  public void multiSave() {
    for (int i = 0; i < 10 ; i++) {
      BoardDto boardDto = new BoardDto();
      boardDto.setTitle("트랜젝션 테스트 제목" + (i + 1));
      boardDto.setContents("트랜젝션 테스트 내용" + (i + 1));
      boardMapper.insertBoard(boardDto);
    }
  }
}
