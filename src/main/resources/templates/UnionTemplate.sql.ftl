<#-- @ftlvariable name="doUnion" type="java.lang.Boolean" -->

select * from
(
select t1.id from table1 t1
<#if doUnion>
  union all
  <#include ("templates/IncludedTemplate.sql.vm")>
</#if>
);
