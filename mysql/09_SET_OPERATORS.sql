-- 09. SET_OPERATORS
-- SET 연산자는 두 개 이상의 SELECT문의 결과 집합을 결합하는데 사용한다.
-- SET 연산자를 통해 결합하는 결과 집합의 컬럼이 동일해야 한다.

-- UNION
select
      menu_code
      , menu_name
      , menu_price
      , category_code
      , orderable_status
      from tb1_menu
      where category_code = 10
      union
      select
      menu_code
      , menu_name
      , menu_price
      ,category_code
      ,orderable_status
      from tbl_menu
	where menu_price < 9000;
    
    
    select
           menu_code
           ,menu_name
           ,menu_price
           ,category_code
           ,orderable_status
	  from tbl_menu
      where category_code = 10
      union all
      select
      menu_code
      , menu_name
      , menu_price
      ,category_code
      ,orderable_status
      from tbl_menu
      where menu_price < 9000;
      
      
           
    
    
      