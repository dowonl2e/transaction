package com.study.transaction.board.service;

import com.study.transaction.board.dto.BoardDto;

public interface BoardService {

  void save(BoardDto boardDto);

  void multiSave() throws Exception;

}
