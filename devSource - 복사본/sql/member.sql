drop table member;

create table member(
    idx number(4) primary key,
    name varchar2(30) not null,
    userId varchar2(20) unique not null,
    userPw varchar2(20) not null,
    email varchar2(100),
    mstate number(1) default 1, 
    --ȸ�� ���� (0: ����, 1: Ȱ��, -1: Ż��, 9: ������)
    indate date default sysdate
);

drop sequence member_seq;

create sequence member_seq
start with 1
increment by 1
nocache;

insert into member (idx, name, userId, userPw, mstate)
values (member_seq.nextval, '�����', 'admin', '111', 9);
commit;

select * from member;

update member set email = 'admin.kosmo.com' where idx = 1;
commit;

