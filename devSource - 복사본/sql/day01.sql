select * from tab;
select * from test_tbl;
-- ���� �ּ�
/* 
������ �ּ�
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
--DQL (select)dml�̱� ������ ���� �з�

insert into memo (no, name, msg) values(2, '��浿', '�ι�° ���� ���ϴ�');

select * from memo;
commit;

-- �Ϸù�ȣ�� �������ִ� ��ü: sequence
--sequence�� �̿��ϸ� ������ �������� ���� �Ǵ� ���ҽ�ų �� �ִ�

--create sequence ��������
--start with ���۰�
--increment by ����(����)ġ +-
--min_value �ּҰ�
--max_value �ִ밪
--cache �� | nocache
--nocycle | cycle

--drop sequence ��������; ==> ����
drop sequence memo_seq;

create sequence memo_seq
start with 3
increment by 1
nocache;

-- ������ Ȱ�� �۾���
--������ �Ӽ�: nextval, currval (������, ���簪)
--���� nextval�� ȣ������ �ʰ� curval�� ����ϸ� ������ ����


select memo_seq.currval from dual;
/*
ORA-08002: ������ MEMO_SEQ.CURRVAL�� �� ���ǿ����� ���� �Ǿ� ���� �ʽ��ϴ�
08002. 00000 -  "sequence %s.CURRVAL is not yet defined in this session"
*Cause:    sequence CURRVAL has been selected before sequence NEXTVAL
*Action:   select NEXTVAL from the sequence before selecting CURRVAL
*/

insert into memo(no, name, msg)
values(memo_seq.nextval, '��ö��', '�ȳ��ϼ���');

select * from memo;

select memo_seq.currval from dual;

rollback;

commit;

CRUD SQL��
c: create insert��
r: read select��
u: update update��
d: delete delete��

select * from memo;

-- ��ó�� �ϸ� �ٹٲ� where ������ �ְ� �����ؾ���
--update memo set name = 'ȫ�̳�', wdate = '24/10/22';

rollback;

update memo set name = 'ȫ�̳�', wdate = '24/10/22' where no = 2;
select * from memo;
commit;

--delete from ���̺��; ==> ��� ���ڵ� ����
--delete from ���̺�� where ������ ==> ���ǿ� �����ϴ� ���ڵ带 ����

delete from memo;
select * from memo;
rollback;

delete from memo where name = '��ö��';
commit;
