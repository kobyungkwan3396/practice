-- 14. VIEW
-- SELECT 쿼리문을 저장한 객체로 가상테이블이라고 불린다.
-- 실질적인 데이터를 물리적으로 저장하고 있지 않고, 쿼리만 저장했지만
-- 테이블을 사용하는 것과 동일하게 사용할 수 있다.
-- VIEW는 데이터를 쉽게 읽고 이해할 수 있도록 돕는 동시에,
-- 원본데이터의 보안을 유지하는데 도움이 된다.

-- view 생성
create view hansik as
select 
menu_code
,menu_name
,menu_price
,category_code
,orderable_status
from tbl_menu
where category_code_code = 4;

select * from hansik;

insert into tbl_menu values (null, '식혜', 5500, 4, 'y');

select * from tbl_menu;

-- view를 통한 dml
update hansik
set menu_name = '식혜2',
menu_price = 5700
where menu_code = 24;

delete from hansik where menu_code = 24;

-- view 삭제
drop view hansik;