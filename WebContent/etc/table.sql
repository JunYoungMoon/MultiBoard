create table member (
code number(20) not null,
id varchar2(30) primary key,
pwd varchar2(30) not null,
name varchar2(30) not null,
email varchar2(40) not null,
phone1 varchar2(10) not null, 
phone2 varchar2(10) not null, 
phone3 varchar2(10) not null);

create table QnAboard(
  qa_id NUMBER(4) primary key,
  qa_name VARCHAR2(20),
  qa_subject varchar2(100),
  qa_content varchar2(300),
  qa_date Timestamp default sysdate,
  qa_read number(4),
  qa_replyCount number(4),
  qa_pwd varchar2(12));
  
create table ReplyBoard(
  re_id NUMBER(4) PRIMARY KEY,
  re_name varchar2(20),
  re_content varchar2(300),
  re_date   Timestamp default sysdate,
  re_group NUMBER(4),
  re_step NUMBER(4),
  re_indent NUMBER(4),
  qa_id NUMBER(4),
  constraint qa_id FOREIGN KEY(qa_id) REFERENCES QnAboard(qa_id));
   
create table image
(
  file_count NUMBER(38),
  file_path VARCHAR2(4000),
  file_name VARCHAR2(4000),
  file_title VARCHAR2(4000),
  file_content VARCHAR2(4000),
  file_name2 VARCHAR2(4000)
);

create table board
(
  i_count number(18),
  v_name varchar2(40),
  d_date date,
  v_title varchar2(4000),
  v_content varchar2(4000),
  file_name varchar2(4000),
  file_path varchar2(4000)
);

create table rBoard
(
  i_rid number(4),
  v_rname varchar2(40),
  d_rdate date,
  v_rcontent varchar2(1000),
  i_file_count number(38)
);

create table logrec (
lognum number(10),
id varchar2(30),
name varchar2(30), 
record varchar2(30),
logtime date
);

create sequence qna_board_sequence;
create sequence reply_board_sequence;
create sequence log_seq;
create sequence member_seq;
create sequence re_board_seq;
create sequence board_SEQ;
create sequence image_SEQ;
alter table logrec add(ip varchar2(4000));
alter table QnAboard add(write_id varchar2(20));

insert into member values(
member_seq.nextval, 'admin', 'admin', '관리자', 'admin@xxx.xx', '010', '0000', '0000');


  