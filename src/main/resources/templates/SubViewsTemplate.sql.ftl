with sub_view1 as (
<#include ("templates/SubView1.sql.vm")>
)
select t1.*
from table1 t1
join sub_view1
on t1.key = sub_view1.key
