select * from tab;
select * from test_tbl;
-- 한줄 주석
/* 
여러줄 주석
*/

-- DDL (create, drop, alter)
create table memo(
no number(4) primary key,
name varchar2(30) not null,
msg varchar2(100),
wdate date default sysdate
);
desc memo;
drop table memo;

--DML (insert, delete, update) ==> commit or rollback
--DQL (select)dml이긴 하지만 따로 분류

insert into memo (no, name, msg) values(2, '고길동', '두번째 글을 씁니다');

select * from memo;
commit;

-- 일련번호를 생성해주는 객체: sequence
--sequence를 이용하면 일정한 방향으로 증가 또는 감소시킬 수 있다

--create sequence 시퀀스명
--start with 시작값
--increment by 증가(감소)치 +-
--min_value 최소값
--max_value 최대값
--cache 값 | nocache
--nocycle | cycle

--drop sequence 시퀀스명; ==> 삭제
drop sequence memo_seq;

create sequence memo_seq
start with 3
increment by 1
nocache;

-- 시퀀스 활용 글쓰기
--시퀀스 속성: nextval, currval (다음값, 현재값)
--주의 nextval을 호출하지 않고 curval을 사용하면 에러가 난다


select memo_seq.currval from dual;
/*
ORA-08002: 시퀀스 MEMO_SEQ.CURRVAL은 이 세션에서는 정의 되어 있지 않습니다
08002. 00000 -  "sequence %s.CURRVAL is not yet defined in this session"
*Cause:    sequence CURRVAL has been selected before sequence NEXTVAL
*Action:   select NEXTVAL from the sequence before selecting CURRVAL
*/

insert into memo(no, name, msg)
values(memo_seq.nextval, '김철수', '안녕하세요');

select * from memo;

select memo_seq.currval from dual;

rollback;

commit;

CRUD SQL문
c: create insert문
r: read select문
u: update update문
d: delete delete문

select * from memo;

-- 밑처럼 하면 다바뀜 where 조건절 주고 수정해야함
--update memo set name = '홍미나', wdate = '24/10/22';

rollback;

update memo set name = '홍미나', wdate = '24/10/22' where no = 2;
select * from memo;
commit;

--delete from 테이블명; ==> 모든 레코드 삭제
--delete from 테이블명 where 조건절 ==> 조건에 부합하는 레코드를 삭제

delete from memo;
select * from memo;
rollback;

delete from memo where name = '김철수';
commit;
