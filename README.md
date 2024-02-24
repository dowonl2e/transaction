# Transaction Study
Spring Boot MyBatis Transaction Study

## Environment
- OS : Mac OS
- IDE : IntelliJ, MySQLWorkbench
- Framework : Spring Boot, MyBatis
- DB : MySQL 8.0

## Settins
### application.yml
1. 데이터 베이스 관련 값들을 변경 후 사용

### DB Table
CREATE tb_board (
  ID bigint auto_increment,
  TITLE varchar(300) not null,
  CONTENTS varchar(4000),
  WRITE_DATE datetime default current_timestamp,
  MODIFY_DATE datetime
);
