package com.study.transaction.board.mapper;

import com.study.transaction.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

  void insertBoard(BoardDto boardDto);

}
