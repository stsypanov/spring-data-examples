<#-- @ftlvariable name="fetchParent" type="java.lang.Boolean" -->
<#-- @ftlvariable name="$fetchToys" type="java.lang.Boolean" -->
<#-- @ftlvariable name="$orderByAge" type="java.lang.Boolean" -->

select child
from Child child
<#if fetchParent>
    left join fetch child.parent
</#if>
<#if fetchToys>
    left join fetch child.toys
</#if>

<#if orderByAge>
    order by child.age
</#if>