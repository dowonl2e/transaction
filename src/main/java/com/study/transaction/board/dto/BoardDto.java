package com.study.transaction.board.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Alias("boardDto")
@Getter
@Setter
public class BoardDto {

  private Long id;
  private String title;
  private String contents;
  private LocalDateTime writeDate;
  private LocalDateTime modifyDate;

}
