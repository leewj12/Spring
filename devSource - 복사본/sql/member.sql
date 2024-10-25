drop table member;

create table member(
    idx number(4) primary key,
    name varchar2(30) not null,
    userId varchar2(20) unique not null,
    userPw varchar2(20) not null,
    email varchar2(100),
    mstate number(1) default 1, 
    --회원 상태 (0: 정지, 1: 활동, -1: 탈퇴, 9: 관리자)
    indate date default sysdate
);

drop sequence member_seq;

create sequence member_seq
start with 1
increment by 1
nocache;

insert into member (idx, name, userId, userPw, mstate)
values (member_seq.nextval, '김관리', 'admin', '111', 9);
commit;

select * from member;

update member set email = 'admin.kosmo.com' where idx = 1;
commit;

